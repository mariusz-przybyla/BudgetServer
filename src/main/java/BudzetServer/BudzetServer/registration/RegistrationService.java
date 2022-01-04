package BudzetServer.BudzetServer.registration;


import BudzetServer.BudzetServer.exception.NotFoundException;
import BudzetServer.BudzetServer.model.User;
import BudzetServer.BudzetServer.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Data
@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private String clientUrl;


    public String register(RegistrationRequest request) {
        User user = new User();

        user.setLogin(request.getLogin());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        user = userRepository.save(user);

        userRepository.enableUser(user.getLogin());

        return "Registered";
    }
}
