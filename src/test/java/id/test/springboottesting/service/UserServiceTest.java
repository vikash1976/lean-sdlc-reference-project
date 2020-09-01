package id.test.springboottesting.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import id.test.springboottesting.model.User;

/**
 * @author e1077326
 *
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Test
	void shouldReturnFindAll() {
		List<User> datas = new ArrayList<>();
		String email = "user@mail.com";
		String name = "myname";
		String pass = "my pass";
		datas.add(new User(1L, email, pass, name));
		datas.add(new User(2L, email, pass, name));
		datas.add(new User(3L, email, pass, name));

		// Mockito.when(userService.findAllUsers()).thenReturn(datas);

		List<User> expected = userService.findAllUsers();

		assertEquals(expected.size(), datas.size());
	}

	@Test
	void findUserById() {
		final Long id = 1L;
		
		final User expected = userService.findUserById(id);

		assertThat(expected).isNotNull();

	}

}