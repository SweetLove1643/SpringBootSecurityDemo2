package vn.iostar.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import vn.iostar.Entity.UserInfo;
import vn.iostar.Entity.UserInfoUserDetails;
import vn.iostar.Repository.UserInfoRepository;

public class UserInfoService implements UserDetailsService{
	
	@Autowired
	UserInfoRepository userrepo;
	
	public UserInfoService(UserInfoRepository userrepo) {
		this.userrepo = userrepo;
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Optional<UserInfo> userInfo = userrepo.findByName(username);
		return userInfo.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
	}

}
