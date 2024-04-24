package spring.boot.swiggyBE.security.components;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.boot.swiggyBE.database_model.Users;
import spring.boot.swiggyBE.repository.UserRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> users = userRepository.findByEmail(email);
        UserDetailsImpl userDetails = UserDetailsImpl.builder()
                .id(users.get().getId())
                .email(users.get().getEmail())
                .password(users.get().getPassword())
                .roles(users.get().getRoles())
                .build();
        return userDetails;
    }
}
