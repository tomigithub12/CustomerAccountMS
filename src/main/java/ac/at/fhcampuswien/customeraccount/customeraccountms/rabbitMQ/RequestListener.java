package ac.at.fhcampuswien.customeraccount.customeraccountms.rabbitMQ;

import ac.at.fhcampuswien.customeraccount.customeraccountms.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ac.at.fhcampuswien.customeraccount.customeraccountms.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestListener {

    Logger logger = LoggerFactory.getLogger(RequestListener.class);
    @Autowired
    CustomerRepository customerRepository;

    @RabbitListener(queues = RabbitMQConfig.CUSTOMERID_MESSAGE_QUEUE)
    public String onCustomerIdRequest(String eMail) {
        logger.warn("Retrieved request from BookingMS to get Id from Customer");
        return customerRepository.findIdByeMail(eMail);
    }

    @RabbitListener(queues = RabbitMQConfig.CUSTOMER_EXISTENCE_MESSAGE_QUEUE)
    public boolean onCustomerExistenceCheckRequest(String eMail) {
        logger.warn("Retrieved request from BookingMS to check Customer Existence");
        boolean a = customerRepository.existsByeMail(eMail);
        return customerRepository.existsByeMail(eMail);
    }
}
