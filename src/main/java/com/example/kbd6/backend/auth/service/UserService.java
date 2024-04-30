package com.example.kbd6.backend.auth.service;


import com.example.kbd6.backend.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
// code is taken from this video. code is predefined to work as required.
// https://www.youtube.com/watch?v=TeBt0Ike_Tk&ab_channel=UnknownKoder
// https://github.com/unknownkoder/spring-security-login-system

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("in the user details service");

//        if(!username.equals("krunaldemo")) throw new UsernameNotFoundException("not krunaldemo");
//        Set<Role> roles = new HashSet<>();
//        roles.add(new Role(1,"USER"));
//        return new ApplicationUser(1,"krunaldemo",encoder.encode("password"),roles);

        return userRepo.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not valid"));


    }
}
