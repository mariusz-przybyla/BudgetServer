package BudzetServer.BudzetServer.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableConfigurationProperties(H2ConsoleProperties.class)
@RequiredArgsConstructor
public class H2ConsoleBrowserSupportAutoConfiguration {

    private final H2ConsoleProperties h2ConsoleProperties;

    @Bean
    public H2ConsoleBrowserSupportConfigurer h2ConsoleBrowserSupportConfigurer()
    {
        return new H2ConsoleBrowserSupportConfigurer(h2ConsoleProperties);
    }

    @RequiredArgsConstructor
    public static class H2ConsoleBrowserSupportConfigurer extends AbstractHttpConfigurer<H2ConsoleBrowserSupportConfigurer, HttpSecurity>
    {
        private final H2ConsoleProperties h2ConsoleProperties;

        @Override
        public void init(final HttpSecurity httpSecurity) throws Exception
        {
            final var enabled = h2ConsoleProperties.getEnabled();
            if (enabled)
            {
                httpSecurity.headers().frameOptions().sameOrigin();
            }
        }
    }
}
