package ac.at.fhcampuswien.customeraccount.customeraccountms.controller;



import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.LoginDTO;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.LoginResponseDTO;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.RegistrationRequestDto;
import ac.at.fhcampuswien.customeraccount.customeraccountms.dtos.RegistrationResponseDto;
import ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions.CustomerAlreadyExistsException;
import ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions.CustomerNotFoundException;
import ac.at.fhcampuswien.customeraccount.customeraccountms.exceptions.InvalidPasswordException;
import ac.at.fhcampuswien.customeraccount.customeraccountms.service.CustomerRestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/customers/")
@Tag(name = "Customers", description = "Endpoints for managing customers")
public class CustomerController {
    @Autowired
    CustomerRestService customerRestService;

    @PostMapping("/auth/login")
    @Operation(
            summary = "Customer Login.",
            tags = {"Customers"},
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDTO.class))),
                    @ApiResponse(description = "Email or password is incorrect.", responseCode = "400", content = @Content)
            })
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginData) throws InvalidPasswordException, CustomerNotFoundException {
        LoginResponseDTO loginResponseDTO = customerRestService.customerLogin(loginData);
        return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/auth/registration")
    @Operation(
            summary = "Creates a customers in the database.",
            tags = {"Customers"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RegistrationResponseDto.class))),
                    @ApiResponse(description = "A user for this email is already existing. Please try to log in.", responseCode = "409", content = @Content)
            })
    public ResponseEntity<RegistrationResponseDto> register(@RequestBody RegistrationRequestDto registrationRequestDto) throws CustomerAlreadyExistsException {
        RegistrationResponseDto registrationResponseDto = customerRestService.createCustomer(registrationRequestDto);
        return new ResponseEntity<>(registrationResponseDto, HttpStatus.CREATED);
    }
}
