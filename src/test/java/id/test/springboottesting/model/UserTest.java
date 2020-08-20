package id.test.springboottesting.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {
	
	@Test
	public void testSetId() {
	    
	    long id = 1L;
	    User user = new User(10L, "a", "a", "a");
	    user.setId(id);
	    // TODO review the generated test code and remove the default call to fail.
	    assertEquals(user.getId(), id);
	}
	@Test
	public void testSetEmail() {
	    
	    String email = "a@a.com";
	    User user = new User(1L, "c@a.com", "a", "a");
	    user.setEmail(email);
	    // TODO review the generated test code and remove the default call to fail.
	    assertEquals(user.getEmail(), email);
	}
	@Test
	public void testSetName() {
	    
	    String name = "aName";
	    User user = new User(1L, "a", "a", "a");
	    user.setName(name);
	    // TODO review the generated test code and remove the default call to fail.
	    assertEquals(user.getName(), name);
	}
	@Test
	public void testSetPassword() {
	    
	    String password = "abc123";
	    User user = new User(1L, "a", "a", "a");
	    user.setPassword(password);
	    // TODO review the generated test code and remove the default call to fail.
	    assertEquals(user.getPassword(), password);
	}

}
