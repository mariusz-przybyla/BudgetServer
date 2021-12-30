package BudzetServer.BudzetServer.controller;

import BudzetServer.BudzetServer.dto.MessageResponse;
import BudzetServer.BudzetServer.registration.RegistrationRequest;
import BudzetServer.BudzetServer.registration.RegistrationResponse;
import BudzetServer.BudzetServer.registration.RegistrationService;
import BudzetServer.BudzetServer.repository.UserRepository;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Data
@RequestMapping("api/registration")
public class RegistrationController {

    private UserRepository userRepository;
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest registrationRequest) {
        if (userRepository.existsByLogin(registrationRequest.getLogin())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email already exists!"));
        }

        String token = registrationService.register(registrationRequest);
        return ResponseEntity.ok().body(new RegistrationResponse(token));
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam String token) {
        registrationService.confirmRegistration(token);
        return ResponseEntity.ok(new MessageResponse("Email confirmed!"));
    }
}