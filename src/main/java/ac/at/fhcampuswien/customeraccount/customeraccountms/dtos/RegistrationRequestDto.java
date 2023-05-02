package ac.at.fhcampuswien.customeraccount.customeraccountms.dtos;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {

    private String eMail;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String dateOfBirth;
}
