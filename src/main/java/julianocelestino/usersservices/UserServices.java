package julianocelestino.usersservices;

import julianocelestino.usersservices.domain.User;
import julianocelestino.usersservices.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;

@RestController
@RequestMapping("/users")
public class UserServices {

	@Autowired
	private UserRepository repository;

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(params = {"email"} , produces = "application/json")
	public @ResponseBody Iterable<User> getByEmail(@RequestParam("email") String email) {
		return repository.findByEmail(email);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(params = {"name"}, produces = "application/json")
	public @ResponseBody Iterable<User> getByName(@RequestParam("name") String name) {
		return repository.findByName(name);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(params = {"username"}, produces = "application/json")
	public @ResponseBody Iterable<User> getByUserName(@RequestParam("username") String username) {
		return repository.findByUsername(username);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<User> get() {
		return repository.findAll();
	}

	@PostMapping
	public ResponseEntity<?> post (@RequestBody User user) {
		user.setEnabled(true);
		user.setRegisterDate(new Date());
		repository.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping
	public ResponseEntity<?> deleteAll() {
		repository.deleteAll();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

}
