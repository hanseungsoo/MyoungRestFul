package io.myoung.sample.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import io.myoung.sample.model.UserItem;

public class UserDetailsImpl implements UserDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3101273608882689809L;
	
	private UserItem item;
	
	public UserDetailsImpl() {
		
	}
	
	public UserDetailsImpl(UserItem item) {
		this.item = item;
	}
	
	public UserItem getItem() {
		return this.item;
	}
	
	public void setItem(UserItem item) {
		this.item = item;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		
		if (item.getRole().equals("ADMIN")) { 
			auth.add(new SimpleGrantedAuthority("ROLE_ADMIN")); 
		} else { 
			auth.add(new SimpleGrantedAuthority("ROLE_USER")); 
		}
		
        return auth;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return item.getPassword();
	}



	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return item.getEmail();
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
