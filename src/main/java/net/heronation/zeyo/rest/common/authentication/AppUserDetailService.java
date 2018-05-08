package net.heronation.zeyo.rest.common.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRepository;

@Slf4j
@Service
public class AppUserDetailService implements UserDetailsService {
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberRepository memberRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("AppUserDetailService loadUserByUsername");
  
		
		Member user = memberRepo.findByMemberId(username);
		
		
		AppUserDetails userDetail = new AppUserDetails();
		userDetail.setUsername(username);
		userDetail.setPassword(user.getPassword());
		//userDetail.setEmail(user.getEmail());
		userDetail.setId(user.getId());
		//userDetail.setManager(user.getManager());
		//userDetail.setManagerEmail(user.getManagerEmail());
		//userDetail.setManagerPhone(user.getManagerPhone());
		userDetail.setName(user.getName());
		//userDetail.setPhone(user.getPhone());
		//userDetail.setAdminYn(user.getAdminYn());
		
		
		List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		
		SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_CLIENT");
		roles.add(role);
		if(user.getAdminYn() != null & user.getAdminYn().equals("Y")) {
			role = new SimpleGrantedAuthority("ROLE_ADMIN");
			roles.add(role);
		}
		 
		userDetail.setAuthorities(roles);
		return userDetail; 

	}

}
