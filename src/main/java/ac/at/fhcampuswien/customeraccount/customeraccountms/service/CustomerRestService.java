package ac.at.fhcampuswien.customeraccount.customeraccountms.service;

import ac.at.fhcampuswien.customeraccount.customeraccountms.config.RabbitMQConfig;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.LoginDTO;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.LoginResponseDTO;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.RegistrationRequestDto;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.RegistrationResponseDto;
import ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions.CustomerNotFoundException;
import ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions.InvalidPasswordException;
import ac.at.fhcampuswien.customeraccount.customeraccountms.helper.Hashing;
import ac.at.fhcampuswien.customeraccount.customeraccountms.models.Customer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CustomerRestService {
    @NonNull

    CustomerEntityService customerEntityService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RegistrationResponseDto createCustomer(RegistrationRequestDto registrationRequestDto) throws CustomerAlreadyExistsException {
        return customerEntityService.addCustomer(registrationRequestDto);
    }

    public LoginResponseDTO customerLogin(LoginDTO loginData) throws InvalidPasswordException, CustomerNotFoundException {
        Customer customer = checkCustomerExistence(loginData.getEMail());
        checkPassword(loginData.getPassword(), customer);

        String accessToken = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfig.AUTH_EXCHANGE, RabbitMQConfig.GENERATE_TOKEN_MESSAGE_QUEUE, loginData.getEMail());
        String refreshToken = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfig.AUTH_EXCHANGE, RabbitMQConfig.GENERATE_REFRESH_TOKEN_MESSAGE_QUEUE, loginData.getEMail());

        return new LoginResponseDTO(accessToken, refreshToken);
    }

    private Customer checkCustomerExistence(String email) throws CustomerNotFoundException {
        Customer customer = customerEntityService.findCustomer(email);
        if (customer == null) {
            throw new CustomerNotFoundException("Email or password is incorrect.");
        }
        return customer;
    }

    private void checkPassword(String password, Customer customer) throws InvalidPasswordException {
        if (comparePassword(customer, password)) {
            return;
        }
        throw new InvalidPasswordException("Email or password is incorrect.");
    }

    public boolean comparePassword(Customer customer, String enteredPassword) {
        byte[] hash = Hashing.generateHash(enteredPassword, customer.getSalt());
        return Arrays.equals(hash, customer.getPassword());
    }

    /*public String refreshAccessToken(RefreshTokenDTO refreshTokenDTO) throws InvalidTokenException, CustomerNotFoundException {
        String token = refreshTokenDTO.getRefreshToken();
        String eMail = jwtService.extractUserEmail(token);

        jwtService.isTokenExpiredOrInvalid(token);

        return jwtService.generateToken(eMail, JwtService.Token.AccessToken);
    }*/

/*    public Boolean checkJWT(String refreshTokenDTO) throws CustomerNotFoundException {
        if (Boolean.TRUE.equals(jwtService.validateToken(String.valueOf(refreshTokenDTO))))
            return true;
        return false;
    }*/
}


