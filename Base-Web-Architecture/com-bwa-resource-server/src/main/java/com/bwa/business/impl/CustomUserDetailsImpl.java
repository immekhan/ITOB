package com.bwa.business.impl;

import com.bwa.persistence.model.Customer;
import com.bwa.persistence.repository.CredentialRepository;
import com.bwa.persistence.repository.RolePrivilegeRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.*;

public class CustomUserDetailsImpl implements UserDetails ,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -694318736321199793L;

	@JsonIgnore
	transient private Customer customer;

	private Set<String> permissions;

	private Set<String> roles;

	private String userName;

	private String password;

	private String userId;

	@JsonIgnore
//	@Autowired
	transient private RolePrivilegeRepository rolePrivilegeRepository;
	@JsonIgnore
//	@Autowired
	transient private CredentialRepository credentialRepository;

	public CustomUserDetailsImpl(Customer customer,RolePrivilegeRepository rolePrivilegeRepository,CredentialRepository credentialRepository) {

		if (customer != null) {
			this.rolePrivilegeRepository=rolePrivilegeRepository;
			this.credentialRepository=credentialRepository;

			this.userName = customer.getDisplayName();
			this.userId=customer.getUserId();
			this.password=this.credentialRepository.findByCustomerIdAndCredentialType(customer.getId(),1).getCredential();
			/*this.setUserId(customer.getUserId());
			this.setUserName(customer.getUserName());
			this.setFirstName(customer.getFirstName());
			this.setLastName(customer.getLastName());
			this.setPassword(customer.getPassword());*/
			this.customer = customer;
		}
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		if (this.getRoles() == null) {
			this.roles = new HashSet<String>();
		}

		roles.add(customer.getCustomerType().getStrRole());

		if (this.getPermissions() == null) {
			this.permissions = new HashSet<String>();
		}

		List<String> rolePrivileges=rolePrivilegeRepository.findPrivilegeByRole(customer.getCustomerType().getStrRole());

		if(rolePrivileges!=null){
			for(String privilege:rolePrivileges){
				permissions.add(privilege);
			}
		}

		if (permissions != null) {
			for (String permission : permissions) {
				GrantedAuthority authority = new SimpleGrantedAuthority(
						permission);
				authorities.add(authority);
			}
		}

		/*List<UserRole> userRoles = this.getUser().getTbluserRoles();
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (userRoles != null) {
			for (UserRole role : userRoles) {

				if (this.getRoles() == null) {
					this.roles = new HashSet<String>();
				}

				roles.add(role.getTblrole().getName());

				if (this.getPermissions() == null) {
					this.permissions = new HashSet<String>();
				}

				for (RolePermission permission : role.getTblrole()
						.getTblrolePermissions()) {
					permissions.add(permission.getTblpermission().getKeyId());
				}
			}
		}

		if (permissions != null) {
			for (String permission : permissions) {
				GrantedAuthority authority = new SimpleGrantedAuthority(
						permission);
				authorities.add(authority);
			}
		}*/

		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
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

	/*public User getUser() {
		return customer;
	}

	public void setUser(User customer) {
		this.customer = customer;
	}*/

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserId() {
		return userId;
	}
}
