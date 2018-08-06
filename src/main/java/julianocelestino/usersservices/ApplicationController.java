package julianocelestino.usersservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@Controller
@RequestMapping(path="/complains")
public class ApplicationController {

	@Autowired
	private ComplainRepository repository;
	@Autowired
	private ResourceLoader resourceLoader;
	
	@PostMapping
	public ResponseEntity<?> ingestComplain (@RequestBody Complain complain,HttpServletRequest request) {
		final String cityName = new LocationRepository(resourceLoader).findCity(request.getRemoteAddr());
		complain.setLocale(cityName);
		if (!complain.valid()) {
			throw new IllegalArgumentException(Complain.MSG_INVALID);
		}
		repository.save(complain);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(complain.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping(params = {"company", "city"})
	public @ResponseBody Iterable<Complain> getComplains(@RequestParam("company") String company, @RequestParam("city") String city) {
		return repository.findByCompanyAndLocale(company,city);
	}

	@GetMapping(path="/{id}")
	public @ResponseBody Complain getComplain(@PathVariable Long id) {
		return repository.findOne(id);
	}

	@GetMapping
	public @ResponseBody Iterable<Complain> getAllComplains() {
		return repository.findAll();
	}

	@DeleteMapping
	public ResponseEntity<?> deleteAllComplains() {
		repository.deleteAll();
		return new ResponseEntity<Complain>(HttpStatus.NO_CONTENT);

	}

	@ExceptionHandler(IllegalArgumentException.class)
	void handleBadRequests(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), Complain.MSG_INVALID);
	}
}
