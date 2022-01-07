package BudzetServer.BudzetServer.controller;

import BudzetServer.BudzetServer.dto.MessageResponse;
import BudzetServer.BudzetServer.registration.RegistrationRequest;
import BudzetServer.BudzetServer.registration.RegistrationResponse;
import BudzetServer.BudzetServer.registration.RegistrationService;
import BudzetServer.BudzetServer.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Data
@RequiredArgsConstructor
@RequestMapping("api/registration")
public class RegistrationController {

    private final UserRepository userRepository;
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest registrationRequest)
    {
        if (userRepository != null && userRepository.existsByLogin(registrationRequest.getLogin()))
        {
            return ResponseEntity.badRequest().body(new MessageResponse("Login already exists!"));
        }
        String token = registrationService.register(registrationRequest);
        return ResponseEntity.ok().body(new RegistrationResponse(token));
    }
}
