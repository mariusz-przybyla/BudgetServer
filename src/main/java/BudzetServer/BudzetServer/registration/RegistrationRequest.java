package BudzetServer.BudzetServer.registration;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String login;
    private String password;
    private String firstName;
    private String lastName;

}
