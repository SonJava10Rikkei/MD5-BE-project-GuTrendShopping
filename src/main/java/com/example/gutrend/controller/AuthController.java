package com.example.gutrend.controller;

import com.example.gutrend.dto.request.ChangeAvatar;
import com.example.gutrend.dto.request.SignInForm;
import com.example.gutrend.dto.request.SignUpForm;
import com.example.gutrend.dto.request.UpdateUser;
import com.example.gutrend.dto.response.JwtResponse;
import com.example.gutrend.dto.response.ResponMessage;
import com.example.gutrend.model.Role;
import com.example.gutrend.model.RoleName;
import com.example.gutrend.model.User;
import com.example.gutrend.security.jwt.JwtProvider;
import com.example.gutrend.security.jwt.JwtTokenFilter;
import com.example.gutrend.security.userprincal.UserDetailService;
import com.example.gutrend.security.userprincal.UserPrinciple;
import com.example.gutrend.service.impl.RoleServiceImpl;
import com.example.gutrend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequestMapping("/")
@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (userService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponMessage("no_user"), HttpStatus.OK);
        }
        if (userService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponMessage("no_email"), HttpStatus.OK);
        }
        User user = new User(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(
                            () -> new RuntimeException("Role not found")
                    );
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponMessage("yes"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        String username = jwtProvider.getUerNameFromToken(token);
        User user = userService.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("user name not fond"));
        if (user.getStatus()){
            return new ResponseEntity<>(new ResponMessage("login_denied"),HttpStatus.UNAUTHORIZED);
        }
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(),userPrinciple.getAvatar(), userPrinciple.getAuthorities()));
    }

    @PutMapping("/change-avatar")
    public ResponseEntity<?> changeAvatar(HttpServletRequest request, @Valid @RequestBody ChangeAvatar changeAvatar) {
        String token = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUerNameFromToken(token);
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if (changeAvatar.getAvatar() == null || changeAvatar.getAvatar().trim().equals("")) {
            return new ResponseEntity<>(new ResponMessage("no"), HttpStatus.OK);
        } else {
            user.setAvatar(changeAvatar.getAvatar());
            userService.save(user);
            return new ResponseEntity<>(new ResponMessage("yes"), HttpStatus.OK);
        }
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(HttpServletRequest request, @Valid @RequestBody UpdateUser updateUser) {
        String token = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUerNameFromToken(token);
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User name not found"));
        if (user.getStatus()) {
            return new ResponseEntity<>(new ResponMessage("access_is_denied"), HttpStatus.OK);
        }
        if (updateUser.getAvatar() == null || updateUser.getAvatar().trim().equals("")) {
            return new ResponseEntity<>(new ResponMessage("avatar_failed"), HttpStatus.OK);
        } else {
            user.setName(updateUser.getName());
            user.setAvatar(updateUser.getAvatar());
            user.setPassword(passwordEncoder.encode(updateUser.getPassword()));
            userService.save(user);
            return new ResponseEntity<>(new ResponMessage("update_success"), HttpStatus.OK);
        }

    }

    @PutMapping("/change-role/{id}")
    public ResponseEntity<?> changeRoleOfUser(@PathVariable Long id) {
        Optional<User> user = userService.findByUserId(id);
        Set<Role> roles = new HashSet<>();
        String role = "";
        if (!user.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("no_found"), HttpStatus.OK);
        } else {
            User user1 = userDetailService.getCurrentUser();
            role = userService.getUserRole(user1);
            if (role.equals("ADMIN")) {
                return new ResponseEntity<>(new ResponMessage("access_is_denied"), HttpStatus.OK);
            } else {
                if (userService.getUserRole(user.get()).equals("USER")) {
                    Role pmRole = roleService.findByName(RoleName.PM).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(pmRole);
                }
                if (userService.getUserRole(user.get()).equals("PM")) {
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(userRole);
                }
                user.get().setRoles(roles);
                userService.save(user.get());
                return new ResponseEntity<>(new ResponMessage("update_success"), HttpStatus.OK);
            }

        }
    }

    @PutMapping("/block-user/{id}")
    public ResponseEntity<?> blockUser(@PathVariable Long id) {
        Optional<User> user = userService.findByUserId(id);
        String role = "";
        if (!user.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("no_found"), HttpStatus.OK);
        } else {
            User user1 = userDetailService.getCurrentUser();
            role = userService.getUserRole(user1);
            if (!role.equals("ADMIN")) {
                return new ResponseEntity<>(new ResponMessage("access_is_denied"), HttpStatus.OK);
            }
            if (userService.getUserRole(user.get()).equals("ADMIN")) {
                return new ResponseEntity<>(new ResponMessage("access_is_denied"), HttpStatus.OK);
            }
            user.get().setStatus(!user.get().getStatus());
            userService.save(user.get());
            return new ResponseEntity<>(new ResponMessage("update_success"), HttpStatus.OK);
        }
    }





}
