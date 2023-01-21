package com.epam.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.epam.entity.AuthGroup;
import com.epam.entity.Employee;

public class EmployeePrincipal implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5527233865381726482L;

	private Employee employee;
	private List<AuthGroup> authGroup;

	public EmployeePrincipal(Employee employee, List<AuthGroup> authGroup) {
		super();
		this.employee = employee;
		this.authGroup = authGroup;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (null == authGroup) {
			return Collections.emptySet();
		}
		Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
		authGroup.forEach(group -> 
			grantedAuthorities.add(new SimpleGrantedAuthority(group.getRole())));
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		return employee.getEmpName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}