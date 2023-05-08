package ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    private final ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.of("Europe/Vienna"));

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiException> handleInvalidPasswordException(InvalidPasswordException invalidPasswordException) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                invalidPasswordException.getMessage(),
                httpStatus,
                timestamp
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiException> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                customerNotFoundException.getMessage(),
                httpStatus,
                timestamp
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ApiException> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException customerAlreadyExistsException) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;

        ApiException apiException = new ApiException(
                customerAlreadyExistsException.getMessage(),
                httpStatus,
                timestamp
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }

}
