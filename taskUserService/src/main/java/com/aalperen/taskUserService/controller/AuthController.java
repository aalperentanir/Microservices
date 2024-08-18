package com.aalperen.taskUserService.controller;


import com.aalperen.taskUserService.config.JwtProvider;
import com.aalperen.taskUserService.entity.User;
import com.aalperen.taskUserService.repository.UserRepository;
import com.aalperen.taskUserService.request.LoginRequest;
import com.aalperen.taskUserService.response.AuthResponse;
import com.aalperen.taskUserService.service.CustomUserService;
import com.aalperen.taskUserService.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passworEncoder;

    @Autowired
    private CustomUserService userService;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupRequest(@RequestBody User user) throws Exception{

        User isEmailExist = userRepository.findByEmail(user.getEmail());

        if(isEmailExist != null) {
            throw new Exception("Email is already exist");
        }


        User newUser = new User();

        newUser.setFullName(user.getFullName());
        newUser.setEmail(user.getEmail());
        newUser.setRole(user.getRole());
        newUser.setPassword(passworEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(newUser);

        Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("Register request success");
        res.setStatus("SUCCESS");

        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signinRequest (@RequestBody LoginRequest req) throws Exception{

        String username = req.getEmail();
        String password = req.getPassword();

        Authentication auth = authenticate(username, password);

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);

        AuthResponse res = new AuthResponse();
        res.setJwt(jwt);
        res.setMessage("SIGNIN REQUEST SUCCESS");
        res.setStatus("SUCCESS");

        return new ResponseEntity<>(res, HttpStatus.OK);

    }


    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = userService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username");
        }

        if (!passworEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }



}
