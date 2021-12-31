package BudzetServer.BudzetServer.registration;


import BudzetServer.BudzetServer.exception.NotFoundException;
import BudzetServer.BudzetServer.model.User;
import BudzetServer.BudzetServer.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RegistrationService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ConfirmationTokenService confirmationTokenService;
    private String clientUrl;


    public String register(RegistrationRequest request) {
        User user = new User();

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        // generate registration token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, user);

        confirmationTokenService.saveToken(confirmationToken);

        // send email to confirm registration
//        String link = "http://localhost:8080/api/registration/confirm?token=" + token;
//        emailSender.send(request.getLogin(), buildEmail(request.getFirstName(), link));

        return token;
    }

    @Transactional
    public void confirmRegistration(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getConfirmationToken(token)
                .orElseThrow(() -> new NotFoundException("Token not found."));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Login already confirmed.");
        }

        confirmationTokenService.setConfirmedAt(token);
        enableUser(confirmationToken.getUser().getLogin());
    }

    // TODO: Move to a User service
    public int enableUser(String login) {
        return userRepository.enableUser(login);
    }

//    private String buildLogin(String name, String link) {
//        return "Hi " + name + "! Thank you for registering. Please click this" + "<a href='" + link + "'> link </a>" +
//                "to verify your email and activate your account.";
//    }
}
