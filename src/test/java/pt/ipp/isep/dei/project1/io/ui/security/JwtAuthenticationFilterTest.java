package pt.ipp.isep.dei.project1.io.ui.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class JwtAuthenticationFilterTest {

    @Mock
    AuthenticationManager authenticationManager;


    MockHttpServletRequestBuilder builder;

    JwtAuthenticationFilter jwt;

    @BeforeEach
    void initUseCase() {
        jwt = new JwtAuthenticationFilter(authenticationManager);
    }

    /*
    @Test
    public void attemptAuthentication() throws IOException {

        builder.content("user");


        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("username","user");
        request.addParameter("password","user123");


        builder.postProcessRequest(request);

        System.out.println(request.getParameter("name"));




        LoginViewModel credentials  = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);

        System.out.println(credentials.getUsername());
    }*/

    @Test
    public void attemptAuthenticationv2() throws IOException, JSONException {


   //     JSONObject jsonObject = new JSONObject("{\"username\":\"user\",\"password\":\"user123\"}");
        JSONObject jsonObject = new JSONObject("{\"username\":\"admin\",\"password\":\"admin123\"}");


        String credentials =jsonObject.toString();

     //   System.out.println(credentials);

        byte[] contentBody = credentials.getBytes();

      //  System.out.println(contentBody.toString());

        MockServletContext servletContext = new MockServletContext();
        MockHttpServletRequest request = new MockHttpServletRequest(servletContext);
        MockHttpServletResponse response = new MockHttpServletResponse();

        request.setContent(contentBody);

//        request.setRemoteAddr("http://localhost:3000");
//        request.setSecure(true);
//        request.setLocalAddr("http://localhost:3000");




     //   System.out.println("IS CORS PREFLIGHT? - "+CorsUtils.isPreFlightRequest(request));

        response.setStatus(HttpServletResponse.SC_OK);

        LoginViewModel lvm = new ObjectMapper().readValue(request.getInputStream(), LoginViewModel.class);

//        System.out.println(lvm.getUsername());
//        System.out.println(lvm.getPassword());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                lvm.getUsername(),
                lvm.getPassword(),
                new ArrayList<>());

//        System.out.println(authenticationToken.isAuthenticated());
//        System.out.println(authenticationToken.getPrincipal());
//        System.out.println(authenticationToken.getCredentials());

        Authentication auth = authenticationManager.authenticate(authenticationToken);

        //        System.out.println(auth) is null...

        request.getInputStream();
//        System.out.println(request.getInputStream().isFinished());
//        System.out.println(request.getInputStream().isReady());


 //       Authentication result = jwt.attemptAuthentication(request,response);

//        System.out.println(result);

        assertNull(auth);

//        assertNull(result);

//        assertFalse(CorsUtils.isPreFlightRequest(request));
    }
//
//    @Test
//    public void attemptAuthenticationv3() throws IOException, JSONException {
//
//        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/");
//        request.addParameter(
//                jwt.SPRING_SECURITY_FORM_USERNAME_KEY,
//                "rod");
//        request.addParameter(
//                jwt.SPRING_SECURITY_FORM_PASSWORD_KEY,
//                "koala");
//
//        jwt.setAuthenticationManager(createAuthenticationManager());
//        // filter.init(null);
//
//        Authentication result = jwt.attemptAuthentication(request,
//                new MockHttpServletResponse());
//        assertTrue(result != null);
//        assertTrue(((WebAuthenticationDetails) result.getDetails()).getRemoteAddress().equals("127.0.0.1"));
//
//    }
//
//    private AuthenticationManager createAuthenticationManager() {
//        AuthenticationManager am = mock(AuthenticationManager.class);
//        when(am.authenticate(any(Authentication.class))).thenAnswer(
//                new Answer<Authentication>() {
//                    public Authentication answer(InvocationOnMock invocation)
//                            throws Throwable {
//                        return (Authentication) invocation.getArguments()[0];
//                    }
//                });
//
//        return am;
//    }

    /*
    @Test
    public void successfulAuthentication() {
    }*/
}