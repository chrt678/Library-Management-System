package in.rahul.main.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.rahul.main.Repository.UserRepository;
import in.rahul.main.entity.User;

@RestController

public class UserController {

	@Autowired
	private UserRepository userRepository;

	@DeleteMapping("/register/delete")
	public void deleteUser(@PathVariable long id) {
		userRepository.deleteById(id);
	}

	@GetMapping("/register/getAll")
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@GetMapping("/register/get")
	public Optional<User> getById(@PathVariable long id) {
		return userRepository.findById(id);
	}

	@PutMapping("/register/update")
	public User updateUser(@PathVariable long id, @RequestBody User user) {
		User newUser = userRepository.findById(id).orElse(null);
		newUser.setUsername(user.getUsername());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setRoles(user.getRoles());

		return userRepository.save(user);
	}

}
