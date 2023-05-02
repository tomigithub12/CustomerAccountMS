package ac.at.fhcampuswien.customeraccount.customeraccountms.service;

import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.RegistrationRequestDto;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.RegistrationResponseDto;
import ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions.CustomerNotFoundException;
import ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions.InvalidPasswordException;
import ac.at.fhcampuswien.customeraccount.customeraccountms.helper.Hashing;
import ac.at.fhcampuswien.customeraccount.customeraccountms.mapper.UserMapper;
import ac.at.fhcampuswien.customeraccount.customeraccountms.models.Customer;
import ac.at.fhcampuswien.customeraccount.customeraccountms.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerEntityService {


    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserMapper userMapper;

    public RegistrationResponseDto addCustomer(RegistrationRequestDto registrationRequestDto) throws CustomerAlreadyExistsException {
        checkCustomerExistence(registrationRequestDto.getEMail());

        Customer customer = userMapper.requestMapping(registrationRequestDto);
        Customer dbResponse = customerRepository.save(customer);

        System.out.print("Done");
        return userMapper.responseMapping(dbResponse);
    }

    public Customer findCustomer(String email) {
        return customerRepository.findByeMail(email);
    }

    public void checkCustomerExistence(String email) throws CustomerAlreadyExistsException {
        if (customerRepository.existsByeMail(email)) {
            throw new CustomerAlreadyExistsException("A user for this email is already existing. Please try to log in.");
        }
    }

    public void checkPassword(String password, Customer customer) throws InvalidPasswordException {
        if (comparePassword(customer, password)) {
            return;
        }
        throw new InvalidPasswordException("Email or password is incorrect.");
    }

    private boolean comparePassword(Customer customer, String enteredPassword) {
        byte[] hash = Hashing.generateHash(enteredPassword, customer.getSalt());
        return Arrays.equals(hash, customer.getPassword());
    }

}
