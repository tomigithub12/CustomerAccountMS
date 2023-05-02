package ac.at.fhcampuswien.customeraccount.customeraccountms.mapper;


import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.RegistrationRequestDto;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.RegistrationResponseDto;
import ac.at.fhcampuswien.customeraccount.customeraccountms.models.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public class UserMapper {

    public Customer requestMapping(RegistrationRequestDto requestDto) {
        Customer customer = Customer.builder()
                .eMail(requestDto.getEMail())
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .dateOfBirth(requestDto.getDateOfBirth())
                .phoneNumber(requestDto.getPhoneNumber())
                .build();
        customer.setPassword(requestDto.getPassword());
        return customer;
    }

    public RegistrationResponseDto responseMapping(Customer customer) {
        return RegistrationResponseDto.builder()
                .id(customer.getId())
                .eMail(customer.getEMail())
                .build();
    }
}
