package com.colinlo.joblisting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

//    @Autowired
//    private UserServiceImpl userService;
////    private UserRepositoryImpl userRepository;
//    @Bean
//    public UserDetailsService userDetailsService(){
////        return username -> userRepository.getUserByEmail(username)
////                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return email -> userService.getUserByEmail(email);
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
//        daoAuthProvider.setUserDetailsService(userDetailsService());
//        daoAuthProvider.setPasswordEncoder(this.passwordEncoder());
//        return daoAuthProvider;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }

    // create new class for password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
