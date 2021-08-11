package alkemy.challenge.dto;

import alkemy.challenge.model.User;
import lombok.Data;

@Data
public class UserDTO {
	private String username;

	private String password;

	private String token;

	private String email;

	public User buildEntity() {
		return User.builder().username(this.username).password(this.password).email(this.email).build();
	}
}
