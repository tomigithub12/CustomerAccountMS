package ac.at.fhcampuswien.customeraccount.customeraccountms.models;

import ac.at.fhcampuswien.customeraccount.customeraccountms.helper.Hashing;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Document(collection = "Customer")
public class Customer {

    @Id
    private String id;
    private String eMail;
    private String firstName;
    private String lastName;
    private byte[] password;
    private byte[] salt;
    private String phoneNumber;
    private String dateOfBirth;

    public Customer(String eMail, String firstName, String lastName, String password, String phoneNumber, String dateOfBirth) {
        this.eMail = eMail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        ArrayList<byte[]> list = Hashing.generateHash(password);
        this.salt = list.get(0);
    }

    public void setPassword(String password) {
        ArrayList<byte[]> list = Hashing.generateHash(password);
        this.salt = list.get(0);
        this.password = list.get(1);
    }
}
