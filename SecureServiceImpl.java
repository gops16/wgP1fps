/**
 *
 */
package fte.rascan.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fte.rascan.dao.UserDao;
import fte.rascan.enums.RoleE;

/**
 * @author ferox
 *
 */
@Service("Security")
@Transactional(readOnly = true)
public class SecureServiceImpl implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	/**
	 *
	 */
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		try {
			return (UserDetails) userDao.get(Integer.parseInt(arg0))
					.map(optUser -> 
						new User(new Integer(optUser.getId()).toString(), optUser.getPassword(), 
								 true, true, true, true, grantAuthority(optUser.getUser_roles().getRoles())))
					.orElseThrow(() -> new UsernameNotFoundException("Invalid Identification number or Password!"));			
		} catch (NumberFormatException nfe) {
        	throw new UsernameNotFoundException("Invalid Identification number!");
		} 
	}
	
	public Collection<? extends GrantedAuthority> grantAuthority(Collection<RoleE> role) {
		System.out.println(role);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(RoleE r: role) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+r.toString()));
		}
		return authorities;
	}

}
