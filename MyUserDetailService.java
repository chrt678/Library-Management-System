package in.rahul.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.rahul.main.Repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		in.rahul.main.entity.User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not Found."));

		String role = user.getRoles();
		if (!role.startsWith("ROLE_")) {
			role = "ROLE_" + role;
		}

		return User.builder().username(user.getUsername()).password(user.getPassword())
				.authorities(new SimpleGrantedAuthority(role)).build();

	}
}
