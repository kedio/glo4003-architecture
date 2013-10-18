package ca.ulaval.glo4003.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ca.ulaval.glo4003.repository.RepositoryException;
import ca.ulaval.glo4003.repository.UserRepository;

public class AuthenticationServiceTest {

    private static final String A_NON_EXISTING_USERNAME = "A_NON_EXISTING_USERNAME";
    private static final Integer USER = 0;

    private static final Integer ADMIN = 1;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authenticationService = new AuthenticationService(userRepository);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void whenUserIsNotFoundAUsernameNotFoundExceptionIsThrown() {
        doThrow(new RepositoryException("")).when(userRepository).getUser(A_NON_EXISTING_USERNAME);
        authenticationService.loadUserByUsername(A_NON_EXISTING_USERNAME);
    }

    @Test
    public void userDoesNotHaveAdminRoleAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = authenticationService.getAuthorities(USER);
        boolean userHasAdminRoleAuthorities = grantedAuthorities.contains(new SimpleGrantedAuthority("ADMIN_ROLE"));
        assertFalse(userHasAdminRoleAuthorities);
    }

    @Test
    public void adminHasUserAndAdminRoleAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = authenticationService.getAuthorities(ADMIN);
        boolean adminHasAdminRoleAuthorities = grantedAuthorities.size() == 2;
        assertTrue(adminHasAdminRoleAuthorities);
    }

}
