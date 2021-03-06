package ca.ulaval.glo4003.service.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserNotFoundException;
import ca.ulaval.glo4003.domain.user.UserRepository;

@Service("UserDetailsService")
@Transactional(readOnly = true)
public class AuthenticationService implements UserDetailsService {

    private static final Integer ADMIN_ACCESS = 1;

    @Inject
    UserRepository userRepository;

    public AuthenticationService() {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        UserDetails springUser = null;

        try {

            User user = userRepository.getUser(username);

            springUser = new org.springframework.security.core.userdetails.User(user.getEmailAddress(),
                                                                                user.getPassword().toLowerCase(),
                                                                                true,
                                                                                true,
                                                                                true,
                                                                                true,
                                                                                getAuthorities(user.getAccess()));

        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException("Error in retrieving user");
        }

        return springUser;
    }

    public Collection<GrantedAuthority> getAuthorities(Integer access) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (access.compareTo(ADMIN_ACCESS) == 0) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return authList;
    }

    protected AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
