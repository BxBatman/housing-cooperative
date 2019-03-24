package service;

import model.Authorities;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.AuthoritiesRepository;
import repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        List<Authorities> authoritiesList = authoritiesRepository.findByUsername(username);

        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;

        if (user!=null ){
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.disabled(!user.isEnabled());
            userBuilder.password(user.getPassword());
            String[] authorities = authoritiesList.stream().map(a->a.getAuthority()).toArray(String[]::new);
            userBuilder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return userBuilder.build();
    }

}
