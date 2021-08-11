package alkemy.challenge.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import alkemy.challenge.dto.UserDTO;
import alkemy.challenge.services.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/auth/login")
	public String logIn(@RequestBody Credential credential) {
		return userService.logIn(credential);
	}

	@PostMapping("/auth/register")
	public ResponseEntity<UserDTO> register(@RequestBody UserDTO user) {
		if (userService.createUser(user)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		}
		return ResponseEntity.status(400).body(user);
	}

}
