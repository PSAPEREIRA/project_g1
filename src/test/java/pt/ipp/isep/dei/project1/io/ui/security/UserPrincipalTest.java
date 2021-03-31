package pt.ipp.isep.dei.project1.io.ui.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class UserPrincipalTest {

    @Test
    public void getAuthorities() {

        User user = new User("yo","123","boss,chefao","all,and more");
        UserPrincipal userPrincipal = new UserPrincipal(user);

        Collection<GrantedAuthority> expectedResult = new ArrayList<>();

        List<String> permissions = user.getPermissionList();

        for (String p : permissions) {
            GrantedAuthority ga = new SimpleGrantedAuthority(p);
            expectedResult.add(ga);
        }

        List<String> roles = user.getRoleList();

        for (String r : roles) {
            GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_"+r);
            expectedResult.add(ga);
        }




        Collection<? extends GrantedAuthority> result = userPrincipal.getAuthorities();

        assertEquals(expectedResult,result);
    }

    @Test
    public void getPassword() {

        User user = new User("yo","123","admin","all");

        String expectedResult = user.getPassword();

        UserPrincipal userPrincipal = new UserPrincipal(user);

        String result = userPrincipal.getPassword();

        assertEquals(expectedResult,result);
    }

    @Test
    public void getUsername() {
        User user = new User("yo","123","admin","all");

        String expectedResult = user.getUsername();

        UserPrincipal userPrincipal = new UserPrincipal(user);

        String result = userPrincipal.getUsername();

        assertEquals(expectedResult,result);
    }

    @Test
    public void isAccountNonExpired() {
        User user = new User("yo","123","admin","all");
        UserPrincipal userPrincipal = new UserPrincipal(user);
        boolean expectedResult =userPrincipal.isAccountNonExpired();

        assertTrue(expectedResult);
    }

    @Test
    public void isAccountNonLocked() {
        User user = new User("yo","123","admin","all");
        UserPrincipal userPrincipal = new UserPrincipal(user);

        boolean expectedResult =userPrincipal.isAccountNonLocked();

        assertTrue(expectedResult);

    }

    @Test
    public void isCredentialsNonExpired() {
        User user = new User("yo","123","admin","all");
        UserPrincipal userPrincipal = new UserPrincipal(user);

        boolean expectedResult =userPrincipal.isCredentialsNonExpired();

        assertTrue(expectedResult);
    }

    @Test
    public void isEnabled() {
        User user = new User("yo","123","admin","all");
        UserPrincipal userPrincipal = new UserPrincipal(user);

        boolean expectedResult =userPrincipal.isEnabled();

        assertTrue(expectedResult);
    }
}