package io.codeforall.bootcamp.javabank.view;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.controller.LoginController;
import io.codeforall.bootcamp.javabank.services.AuthService;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class LoginViewTest {

    private LoginView loginView;
    private LoginController loginController;
    private AuthService authService;
    private Controller nextController;
    private Prompt prompt;

    @Before
    public void setUp() {
        loginView = new LoginView();
        loginController = new LoginController();
        authService = mock(AuthService.class);
        nextController = mock(Controller.class);
        prompt = mock(Prompt.class);

        // Inject mocks and links
        loginController.setAuthService(authService);
        loginController.setNextController(nextController);
        loginView.setLoginController(loginController);
        loginView.prompt = prompt;

        // Link back (controller -> view) to avoid nulls when init() is called
        loginController.setView(loginView);


    }

    @Test
    public void testSetLoginController(){

        LoginController otherController = new LoginController();
        loginView.setLoginController(otherController);

    }

//    @Test
//    public void testShow() {
//
//        when(prompt.getUserInput(any(IntegerInputScanner.class))).thenReturn(1);
//
//       when(prompt.getUserInput()).thenReturn("1");
//       when(authService.authenticate(1)).thenReturn(true);
//
//       loginView.show();
//
//       verify(authService,times(1)).authenticate(1);
//       verify(nextController,times(1)).init();
//    }



}