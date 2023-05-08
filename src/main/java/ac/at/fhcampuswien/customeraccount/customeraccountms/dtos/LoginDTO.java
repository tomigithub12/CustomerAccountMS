package ac.at.fhcampuswien.customeraccount.customeraccountms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String eMail;
    private String password;
}
