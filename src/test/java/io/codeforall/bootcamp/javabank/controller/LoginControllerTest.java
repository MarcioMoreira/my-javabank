package io.codeforall.bootcamp.javabank.controller;

import io.codeforall.bootcamp.javabank.services.AuthService;
import io.codeforall.bootcamp.javabank.view.LoginView;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LoginControllerTest extends TestCase {


    private LoginController loginController;
    private Controller nextController;
    private AuthService authService;

    @Before
    public void setUp() {

        loginController = new LoginController();
        nextController = mock(Controller.class);
        authService = mock(AuthService.class);
        LoginView view = mock(LoginView.class);

        // inject dependencies
        loginController.setNextController(nextController);
        loginController.authService = authService;
        loginController.view = view;
    }

    @Test
    public void testSetNextController() {

        int id = 1;
        Controller controller = mock(Controller.class);
        loginController.setNextController(controller);

        //  test it indirectly by simulating success
        when(authService.authenticate(id)).thenReturn(true);
        loginController.onLogin(id);

        //  its init() should be called
        verify(controller, times(id)).init();

    }

    @Test
    public void testOnLogin() {

        int id = 1;
        when(authService.authenticate(id)).thenReturn(true);
        loginController.onLogin(id);

        verify(authService, times(1)).authenticate(id);
    }

    @Test
    public void testIsAuthFailed() {

        int id = 1;
        assertFalse(loginController.isAuthFailed());

        when(authService.authenticate(id)).thenReturn(false);
        loginController.onLogin(id);
        assertTrue(loginController.isAuthFailed());

    }


}