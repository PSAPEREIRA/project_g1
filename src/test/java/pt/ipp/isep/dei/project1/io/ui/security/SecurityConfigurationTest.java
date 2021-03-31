package pt.ipp.isep.dei.project1.io.ui.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;
@ExtendWith(MockitoExtension.class)
public class SecurityConfigurationTest {

    @Mock
    UserPrincipalDetailsService userPrincipalDetailsService;

    @Mock
    UserRepository userRepository;

    SecurityConfiguration securityConfiguration;

    @BeforeEach
    void initUseCase() {
        securityConfiguration = new SecurityConfiguration(userPrincipalDetailsService,userRepository);
    }

    @Test
    public void configure() {

        ObjectPostProcessor objectPostProcessor = new ObjectPostProcessor() {
            @Override
            public Object postProcess(Object o) {
                return null;
            }
        };

        AuthenticationManagerBuilder authenticationManagerBuilder =
                new AuthenticationManagerBuilder(objectPostProcessor);

    securityConfiguration.configure(authenticationManagerBuilder);

        assertTrue(authenticationManagerBuilder.isConfigured());
    }
/*
    @Test
    public void configureHttp() throws Exception {


    }

    @Test
    public void corsConfigurationSource() {
    }
*/
    @Test
    public void passwordEncoder() {

        PasswordEncoder result = securityConfiguration.passwordEncoder();

        PasswordEncoder expectedResult = new BCryptPasswordEncoder();

      //  System.out.println(expectedResult.equals(result));

      //  System.out.println(result.);

        String password = "password";

        String hashedPassword = result.encode(password);

        String hashedPassword2 = expectedResult.encode(password);


     //   System.out.println(result.matches(password,hashedPassword2));

        assertTrue(result.matches(password,hashedPassword2));
    }

    @Test
    public void authenticationProvider() {

        DaoAuthenticationProvider result = securityConfiguration.authenticationProvider();

        DaoAuthenticationProvider expectedResult = new DaoAuthenticationProvider();


        assertFalse(result.isForcePrincipalAsString());

    }
}