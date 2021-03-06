package BudzetServer.BudzetServer.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static BudzetServer.BudzetServer.security.SecurityConstants.HEADER_AUTH_KEY;
import static BudzetServer.BudzetServer.security.SecurityConstants.TOKEN_PREFIX;


public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);
    private JwtTokenProvider jwtTokenProvider;

    public JwtAuthFilter(JwtTokenProvider jwtTokenProvider)
    {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException
    {

        try
        {
            Optional.ofNullable(getJwtFromRequest(request))
            .ifPresent(jwt -> {
                UsernamePasswordAuthenticationToken authentication = jwtTokenProvider.getAuthentication(jwt);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        }
        catch (Exception exception)
        {
            logger.error("Could not set user authentication in security context", exception);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request)
    {
       String bearerToken = request.getHeader(HEADER_AUTH_KEY);

       if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX))
       {
           return bearerToken.substring(7, bearerToken.length());
       }
       return null;
    }
}
