package id.test.springboottesting.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.test.springboottesting.model.User;


/**
 * @author e1077326
 *
 */
@Service
@Transactional
public class UserService {

    

    
    public List<User> findAllUsers() {
    	List<User> datas = new ArrayList<>();
		String email = "ten@mail.com";
		String name = "myname";
		String pass = "my pass";
		datas.add(new User(1L, email, pass, name));
		datas.add(new User(2L, email, pass, name));
		datas.add(new User(3L, email, pass, name));
       return datas;
    }

    public User findUserById(Long id) {
        return new User(id, "a@a.com", "ABC", "abc");
    }

    
}
