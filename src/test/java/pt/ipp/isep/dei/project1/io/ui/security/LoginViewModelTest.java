package pt.ipp.isep.dei.project1.io.ui.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class LoginViewModelTest {

    @Test
    public void checkPassAndUser() {

        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.setPassword("123");
        loginViewModel.setUsername("yo");

        String expectedPass ="123";
        String expectedUser ="yo";

        String resultPass = loginViewModel.getPassword();
        String resultUser = loginViewModel.getUsername();

        assertTrue(expectedPass.equals(resultPass)&&expectedUser.equals(resultUser));

    }


}