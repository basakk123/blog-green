package site.metacoding.red.web.dto.request.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckDto {

	private String username;

	public String getUsername() {
		return this.username;
	}
}
