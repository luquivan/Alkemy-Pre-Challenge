package alkemy.challenge.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import alkemy.challenge.dto.UserDTO;
import alkemy.challenge.model.User;
import alkemy.challenge.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserService {

	@Autowired
	MailService mailService;

	@Autowired
	UserRepository userRepository;

	public String logIn(Credential credential) {
		try {
			userRepository.findByUsername(credential.getUsername());
			String token = getJWTToken(credential.getUsername());
			return token;
		} catch (Exception e) {
			System.out.println("Usuario no registrado");
		}
		return null;
	}

	public boolean createUser(UserDTO user) {
		try {
			User newUser = user.buildEntity();
			userRepository.saveAndFlush(newUser);
			mailService.sendWelcomeEmail(user);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("softtekJWT").setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
