package ac.at.fhcampuswien.customeraccount.customeraccountms.repository;

import ac.at.fhcampuswien.customeraccount.customeraccountms.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    boolean existsByeMail(String email);

    Customer findByeMail(String email);

    @Query(value = "{'eMail': ?0}", fields = "{'id': 1}")
    String findIdByeMail(String eMail);

}
