package io.myoung.sample.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import io.myoung.sample.model.UserItem;

public class UserDetailsImpl extends User {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3101273608882689809L;

	public UserDetailsImpl(UserItem item) {
		super(item.getId(), item.getPassword(), authorities(item));
	}
	
	private static Collection<? extends GrantedAuthority> authorities(UserItem item) {
		List<GrantedAuthority> authorities = new ArrayList<>(); 
		
		if (item.getRole().equals("ADMIN")) { 
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); 
		} else { 
			authorities.add(new SimpleGrantedAuthority("ROLE_USER")); 
		}
		
		return authorities;
	}
}
