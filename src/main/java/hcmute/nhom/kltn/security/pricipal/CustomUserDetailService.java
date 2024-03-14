package hcmute.nhom.kltn.security.pricipal;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import hcmute.nhom.kltn.dto.UserDTO;
import hcmute.nhom.kltn.model.User;
import hcmute.nhom.kltn.repository.UserRepository;
import hcmute.nhom.kltn.service.UserService;

/**
 * Class CustomUserDetailService.
 *
 * @author: ThanhTrong
 * @function_id:
 * @version:
 **/
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return UserPrincipal.create(user);
    }
}
