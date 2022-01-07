package BudzetServer.BudzetServer.registration;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@RequiredArgsConstructor
@CrossOrigin
public class RegistrationResponse {

    private final String login;
}