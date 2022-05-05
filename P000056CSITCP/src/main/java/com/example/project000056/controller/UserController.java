package com.example.project000056.controller;

import com.example.project000056.email.EmailSendBox;
import com.example.project000056.email.MailService;
import com.example.project000056.model.ERole;
import com.example.project000056.model.Role;
import com.example.project000056.model.Status;
import com.example.project000056.model.User;
import com.example.project000056.payload.request.LoginRequest;
import com.example.project000056.payload.request.SignupRequest;
import com.example.project000056.payload.response.JwtResponse;
import com.example.project000056.payload.response.MessageResponse;
import com.example.project000056.qrcode.QRCodeGenerator;
import com.example.project000056.qrcode.QRcodeMaker;
import com.example.project000056.qrcode.QrCodeUtil;
import com.example.project000056.repository.RoleRepository;
import com.example.project000056.repository.UserRepository;
import com.example.project000056.security.jwt.JwtUtils;
import com.example.project000056.security.services.UserDetailsImpl;
import com.example.project000056.singleton.userHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import com.example.project000056.email.MailService;

import javax.validation.Valid;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/")
public class UserController{
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private MailService MailService;

    QRcodeMaker qRcodeUtil = new QRcodeMaker();
    EmailSendBox emailSendBox = new EmailSendBox();

    @Autowired
    JwtUtils jwtUtils;
    private User user;
    private userHolder userHolder;

    @Autowired
    public UserController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        QRCodeGenerator qr = new QRCodeGenerator();
        userHolder = userHolder.getInstance();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        // set singleton
        User userSignin = new User(userDetails.getId(),userDetails.getUsername(),userDetails.getEmail());
        userHolder.setUser(userSignin);
        QRCodeGenerator.generateQRCodeImage("ssss",350,350,"test.png");
//        qRcodeUtil.getQRCodeImage("Xiaojie wants to eat eggs", 350, 350, "./src/main/resources");
//        QrCodeUtil.save("123",null,"./src/main/resources");
//        MailService.sendSimpleMail(userSignin.getEmail(),"New Order","QR code");
        MailService.sendAttachmentsMail(userSignin.getEmail(),"dasdas","dasda","test.png");
        MailService.sendAttachmentsMail("s3798551@student.rmit.edu.au","dasdas","dasda","test.png");

        MailService.sendAttachmentsMail("653745320@qq.com","dasdas","dasda","test.png");

//        MailService.sendInlineResourceMail("s3798551@student.rmit.edu.au","New Order","QR code","test.png","png");
//        MailService.sendInlineResourceMail("653745320@qq.com","New Order","QR code","test.png","png");
//        emailSendBox.send("test","qrcode",false,userSignin.getEmail(),null,new File("test.png"));
        System.out.println(userSignin.getId());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        userHolder = userHolder.getInstance();
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        // set singleton
        userHolder.setUser(user);
        System.out.println(user.getId());
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}