package ac.at.fhcampuswien.customeraccount.customeraccountms.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResponseDto {
    private String id;
    private String eMail;
}
