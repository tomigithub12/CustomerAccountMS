package ac.at.fhcampuswien.customeraccount.customeraccountms;

import ac.at.fhcampuswien.customeraccount.customeraccountms.controller.CustomerController;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.LoginDTO;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.LoginResponseDTO;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.RegistrationRequestDto;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.RegistrationResponseDto;
import ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions.CustomerNotFoundException;
import ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions.InvalidPasswordException;
import ac.at.fhcampuswien.customeraccount.customeraccountms.models.Customer;
import ac.at.fhcampuswien.customeraccount.customeraccountms.repository.CustomerRepository;
import ac.at.fhcampuswien.customeraccount.customeraccountms.service.CustomerEntityService;
import ac.at.fhcampuswien.customeraccount.customeraccountms.service.CustomerRestService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.json.JSONObject;
import org.springframework.context.annotation.ComponentScan;

import java.util.Optional;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "CustomerAccoountMS API", version = "1.0"))
public class CustomerAccountMsApplication {

	/*@Autowired
	CustomerEntityService customerEntityService;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerRestService customerRestService;*/

	//Testing purposes, can be deleted
	/*@PostConstruct
	public void doo() throws CustomerAlreadyExistsException, CustomerNotFoundException, InvalidPasswordException {
		RegistrationRequestDto registrationRequestDto = new RegistrationRequestDto();
		registrationRequestDto.setEMail("test@gmail.com");
		registrationRequestDto.setPassword("test");
		registrationRequestDto.setDateOfBirth("2000-01-01");
		registrationRequestDto.setFirstName("test");
		registrationRequestDto.setLastName("test");
		customerEntityService.addCustomer(registrationRequestDto);
		Customer x = customerEntityService.findCustomer("test@gmail.com");
		customerEntityService.checkPassword("test", x);
		System.out.print("End");

		String a = customerRepository.findIdByeMail("test@gmail.com");

		// Parse the JSON string
		JSONObject jsonObject = new JSONObject(a);

		// Access the nested field "oid"
		String oidValue = jsonObject.getJSONObject("_id").getString("$oid");
		Optional<Customer> xn = customerRepository.findById(oidValue);
		System.out.print("s");

		LoginDTO loginDTO = new LoginDTO("apfel@saft.com", "abc123");
		RegistrationRequestDto registrationRequestDto = new RegistrationRequestDto();
		registrationRequestDto.setEMail("apfel@saft.com");
		registrationRequestDto.setPassword("abc123");
		registrationRequestDto.setDateOfBirth("2000-01-01");
		registrationRequestDto.setFirstName("test");
		registrationRequestDto.setLastName("test");

		RegistrationResponseDto registrationResponseDto = customerRestService.createCustomer(registrationRequestDto);
		LoginResponseDTO loginResponseDTO = customerRestService.customerLogin(loginDTO);
		System.out.print("End");
	}*/
	public static void main(String[] args) {
		SpringApplication.run(CustomerAccountMsApplication.class, args);
	}

}
