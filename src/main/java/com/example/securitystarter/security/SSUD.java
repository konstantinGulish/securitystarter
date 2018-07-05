package com.example.securitystarter.security;

import com.example.securitystarter.entity.AppUser;
import com.example.securitystarter.entity.Role;
import com.example.securitystarter.repositories.AppUserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUD implements UserDetailsService {

    private AppUserRepository userRepository;

    public SSUD (AppUserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppUser theUser = userRepository.findByUsername(s);
        if(theUser==null) {
            throw new UsernameNotFoundException("Unable to find that user!!!!");
        }
        return new User(theUser.getUsername(),theUser.getPassword(),myAuthorities(theUser));
    }

    private Set<GrantedAuthority> myAuthorities(AppUser user){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for(Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }
}