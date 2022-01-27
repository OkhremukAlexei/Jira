package com.jira.controllers;

import com.jira.configs.jwt.JwtUtils;
import com.jira.models.Account;
import com.jira.models.ERole;
import com.jira.models.Role;
import com.jira.models.User;
import com.jira.pojo.JwtResponse;
import com.jira.pojo.LoginRequest;
import com.jira.pojo.MessageResponse;
import com.jira.pojo.SignupRequest;
import com.jira.repos.AccountRepo;
import com.jira.repos.RoleRepo;
import com.jira.repos.UserRepo;
import com.jira.services.impl.UserDetailsImpl;
import com.jira.validator.LoginRequestValidator;
import com.jira.validator.SignUpRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {


    @Autowired
    LoginRequestValidator loginRequestValidator;

    @Autowired
    SignUpRequestValidator signUpRequestValidator;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    private static final Logger LOGGER= LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authUser(@RequestBody LoginRequest loginRequest, BindingResult bindingResult) {

        LOGGER.info("Work of authUser: ");
        loginRequestValidator.validate(loginRequest,bindingResult);
        if(bindingResult.hasErrors()) {
            LOGGER.info("Incorrect data in loginRequest ", loginRequest.getLogin(), " Password: ", loginRequest.getPassword());
            return  ResponseEntity.badRequest().build();
        }
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
            LOGGER.info("RegisterUser method ",  signupRequest.getLogin()," Password: ",signupRequest.getPassword()," name: ",signupRequest.getName()," surname: ",signupRequest.getSurname()," email: ",signupRequest.getEmail());


            signUpRequestValidator.validate(signupRequest,bindingResult);
            if(bindingResult.hasErrors()){
                LOGGER.info("Incorrect data in SignupRequest ",  signupRequest.getLogin()," Password: ",signupRequest.getPassword()," name: ",signupRequest.getName()," surname: ",signupRequest.getSurname()," email: ",signupRequest.getEmail());
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Invalid data"));
            }

            if (userRepo.existsByLogin(signupRequest.getLogin())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }

        User user = new User(signupRequest.getLogin(),
                passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> reqRoles = signupRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (reqRoles == null) {
            Role userRole = roleRepo
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "admin":
                        Role adminRole = roleRepo
                                .findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                        roles.add(adminRole);

                        break;
                    case "manager":
                        Role modRole = roleRepo
                                .findByName(ERole.ROLE_MANAGER)
                                .orElseThrow(() -> new RuntimeException("Error, Role MANAGER is not found"));
                        roles.add(modRole);

                        break;

                    default:
                        Role userRole = roleRepo
                                .findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);

        if (accountRepo.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: User with this email is exist"));
        }

        Account account = new Account(
                signupRequest.getName(),
                signupRequest.getSurname(),
                signupRequest.getEmail(),
                user
                );

        userRepo.save(user);
        accountRepo.save(account);

        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}
