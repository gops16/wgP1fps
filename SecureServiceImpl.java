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
