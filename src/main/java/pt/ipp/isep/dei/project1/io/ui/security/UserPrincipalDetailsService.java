package pt.ipp.isep.dei.project1.io.ui.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        User user = this.userRepository.findByUsername(s);
    /**    System.out.println(userRepository.findAll().toString());*/

        return new UserPrincipal(user);
    }
}