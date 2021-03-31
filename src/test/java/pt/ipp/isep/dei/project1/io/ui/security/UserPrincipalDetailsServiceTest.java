package pt.ipp.isep.dei.project1.io.ui.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserPrincipalDetailsServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserPrincipalDetailsService userPrincipalDetailsService;

    @Test
    public void loadUserByUsername() {

        String username = "yo";

        User user = new User(username,"123","boss,chefao","all,and more");


        when(userRepository.findByUsername(username)).thenReturn(user);

        UserPrincipal expectedResult = new UserPrincipal(user);

        UserDetails result = userPrincipalDetailsService.loadUserByUsername(username);

        assertEquals(expectedResult.getUsername(),result.getUsername());
    }
}