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

@RestController
@RequestMapping("/users")
public class UserResource {

	@GetMapping
	public ResponseEntity get() {
		return ResponseEntity.status(HttpStatus.OK).body("Aqui Virao os Usuarios");
	}


}
