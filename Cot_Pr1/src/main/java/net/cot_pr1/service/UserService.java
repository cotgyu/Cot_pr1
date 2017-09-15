package net.cot_pr1.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.cot_pr1.dao.UserDao;
import net.cot_pr1.domain.User;
import net.cot_pr1.security.Role;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	@Autowired 
	private PasswordEncoder passwordEncoder;

	public void create(User user) {
		//암호화는 다시 찾아서 할것!
		//String password = user.getPassword();
		//password = passwordEncoder.encode(password);
		//user.setPassword(password);
		
		userDao.create(user);
	}

	public User findByID(String userId) {
		return userDao.findByID(userId);
	}

	public void imgmodify(User vo) {
		userDao.imgmodify(vo);
		
	}

	public void update(User user) {
		userDao.update(user);
		
	}

	public int checkId(User vo) {
		return userDao.checkId(vo);
	}

	public String findByprofile(String userId) {	
		return userDao.findByprofile(userId);
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {	
		//이부분에  dao 랑 연결해서 네임 등등 가져와야하는 듯 싶다!  >> 비밀번호 오휴 뜸;; >>복호화 풀어서 입력해서 일단 성공 	
		//User_s user = new User_s();
		User user = new User();
		user = userDao.findname(username);
		//아이디가 없을때....
		if(user ==null){
			return user;
		}
		user.setUserId(user.getUsername());
		user.setPassword(user.getPassword());
		Role role = new Role();
		role.setName("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setAuthorities(roles);
		/*
		
		String password = "aabcb987e4b425751e210413562e78f776de6285";
		User_s user = new User_s();
		user.setUsername(username);
		user.setPassword(password);
		Role role = new Role();
		role.setName("ROLE_USER");
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		user.setAuthorities(roles);
		*/
		if (user == null) throw new UsernameNotFoundException("접속자 정보를 찾을 수 없습니다.");

		
		return user;
	}
	
	
}
