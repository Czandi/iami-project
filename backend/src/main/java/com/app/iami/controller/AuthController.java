package com.app.iami.controller;

import com.app.iami.model.ERole;
import com.app.iami.model.Role;
import com.app.iami.model.Teacher;
import com.app.iami.payload.request.LoginRequest;
import com.app.iami.payload.request.SignupRequest;
import com.app.iami.payload.response.JwtResponse;
import com.app.iami.payload.response.MessageResponse;
import com.app.iami.repository.RoleRepository;
import com.app.iami.repository.TeacherRepository;
import com.app.iami.security.jwt.JwtUtils;
import com.app.iami.security.service.TeacherDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final TeacherRepository teacherRepository;

    private final RoleRepository roleRepository;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, TeacherRepository teacherRepository, RoleRepository roleRepository, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.teacherRepository = teacherRepository;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        TeacherDetailsImpl teacherDetails = (TeacherDetailsImpl) authentication.getPrincipal();
        List<String> roles = teacherDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                teacherDetails.getId(),
                teacherDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (teacherRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        Teacher teacher = new Teacher(signUpRequest.getName(),
                signUpRequest.getSurname(),
                signUpRequest.getUsername(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName(ERole.ROLE_TEACHER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        teacher.setRoles(roles);
        teacherRepository.save(teacher);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!" + teacher.getRoles()));
    }
}
