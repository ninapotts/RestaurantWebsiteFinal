
public class MyUserDetails implements UserDetails{
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private boolean enabled;
    private List<GrantedAuthority> authorities;

    public MyUserDetails() {
    }

    public MyUserDetails(String username) {
        this.username = username;
    }

    public MyUserDetails(User user){
        super();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRoles()))
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
    public boolean iscredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}