package BudzetServer.BudzetServer.controller;

import BudzetServer.BudzetServer.dto.LoginRequest;
import BudzetServer.BudzetServer.dto.LoginResponse;
import BudzetServer.BudzetServer.model.CustomUserDetails;
import BudzetServer.BudzetServer.repository.UserRepository;
import BudzetServer.BudzetServer.security.JwtTokenProvider;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Data
@RequiredArgsConstructor
@CrossOrigin
@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getLogin();
        String password = loginRequest.getPassword();

//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////        String password = "dupa123";
//        String encodedPassword = passwordEncoder.encode(password);
//
//        System.out.println();
//        System.out.println("Password is         : " + password);
//        System.out.println("Encoded Password is : " + encodedPassword);
//        System.out.println();
//
//
//        boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
//        System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.createToken(authentication);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getLogin()));
    }
}
