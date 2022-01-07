package BudzetServer.BudzetServer.security;

import BudzetServer.BudzetServer.model.CustomUserDetails;
import BudzetServer.BudzetServer.model.User;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static BudzetServer.BudzetServer.security.SecurityConstants.EXPIRATION_TIME;
import static BudzetServer.BudzetServer.security.SecurityConstants.SECRET_KEY;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;

    public String createToken(User userDetails)
    {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user", userDetails.getLogin());

        return Jwts.builder()
                .setSubject(userDetails.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token)
    {
        try
        {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex)
        {
            System.out.println("Invalid JWT Signature");
        }
        catch (MalformedJwtException ex)
        {
            System.out.println("Invalid JWT Token");
        }
        catch (ExpiredJwtException ex)
        {
            System.out.println("Expired JWT Token");
            System.out.println(ex.getMessage());
        }
        catch (UnsupportedJwtException ex)
        {
            System.out.println("Unsupported JWT Token");
        }
        catch (IllegalArgumentException ex)
        {
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token)
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getUsername(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }
}
