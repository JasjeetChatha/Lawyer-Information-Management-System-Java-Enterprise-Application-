package ca.sheridancollege.chatjasj.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ca.sheridancollege.chatjasj.repositories.UserRepository;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserDetailsServiceImp1 implements UserDetailsService {
	private UserRepository secRepo;
	@Override
	public UserDetails loadUserByUsername(String username)
	throws UsernameNotFoundException {
	//Find the user based on the user name
		ca.sheridancollege.chatjasj.beans.User user
	= secRepo.findUserByUsername(username);
	//If the user doesn't exist then throw an exception
	if (user == null) {
	System.out.println("User not found.");
	
	throw new UsernameNotFoundException("User Not Found");
	}
	//Get a list of the roles for that user
	List<String> roles = secRepo.getRolesById(user.getUserId());
	//Change the list of roles into a list of Granted Authorities
	List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
	for (String role : roles) {
	grantList.add(new SimpleGrantedAuthority(role));
	}
	//Create a Spring User based on the information above
	//import this user from spring security core
	User springUser = new User(user.getUserName(),
	user.getEncryptedPassword(), grantList);
	return (UserDetails)springUser;
	}
}

