package pt.ipp.isep.dei.project1.io.ui.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    private User user;

    @BeforeEach
    void initUseCase() throws Exception {
         user = new User("yo","123","boss,chefao","all,and more");
    }

    @Test
    public void getId() {

        long expectedResult = 0;

        long result = user.getId();

        assertEquals(expectedResult,result);
    }

    @Test
    public void getActive() {

        long expectedResult = 1;

        long result = user.getActive();

        assertEquals(expectedResult,result);

    }

    @Test
    public void getRoles() {
        String expectedResult = "boss,chefao";

        String result = user.getRoles();

        assertEquals(expectedResult,result);
    }

    @Test
    public void getPermissions() {
        String expectedResult = "all,and more";

        String result = user.getPermissions();

        assertEquals(expectedResult,result);
    }

    @Test
    public void getRoleListFalseCondition() {

        User newUser = new User();

        List<String> result = newUser.getRoleList();

        List<?> expectedResult = new ArrayList<>();

        assertEquals(expectedResult,result);
    }

    @Test
    public void getPermissionListFalseCondition() {

        User newUser = new User();

        List<String> result = newUser.getPermissionList();

        List<?> expectedResult = new ArrayList<>();

        assertEquals(expectedResult,result);
    }
}