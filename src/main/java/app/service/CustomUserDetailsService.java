package app.service;

import app.domain.CustomUserDetails;
import app.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    @Transactional
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserEntity user = userService.findByLogin(username).get();
            return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
