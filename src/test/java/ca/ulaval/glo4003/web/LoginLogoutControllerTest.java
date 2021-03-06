package ca.ulaval.glo4003.web;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

public class LoginLogoutControllerTest {

    private static final boolean ERROR = true;
    private static final boolean NO_ERROR = false;

    @Mock
    private ModelMap model;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private LoginLogoutController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doReturn(session).when(request).getSession();
    }

    @Test
    public void canGetDeniedPage() {
        assertEquals("deniedpage", controller.getDeniedPage());
    }

    @Test
    public void canGetLoginWhenThereIsAnError() {
        assertEquals("login", controller.getLoginPage(ERROR, model));
    }

    @Test
    public void canGetLoginWhenThereIsNoError() {
        assertEquals("login", controller.getLoginPage(NO_ERROR, model));
    }
}
