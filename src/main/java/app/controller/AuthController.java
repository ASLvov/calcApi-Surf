package app.controller;

import app.controller.dto.AuthRequest;
import app.controller.dto.AuthResponse;
import app.controller.dto.SignUpRequest;
import app.domain.CustomUserDetails;
import app.domain.Role;
import app.domain.Roles;
import app.domain.UserEntity;
import app.repository.RoleRepository;
import app.security.jwt.JwtProvider;
import app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private RoleRepository roleRepository;
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid SignUpRequest signUpRequest) {
        UserEntity u = new UserEntity();
        u.setPassword(signUpRequest.getPassword());
        u.setUserName(signUpRequest.getUserName());
        Set<Role> roles = new HashSet<>();
        String[] roleArr = signUpRequest.getRoles();

        if (roleArr == null) {
            roles.add(roleRepository.findByRoleName(Roles.ROLE_USER).get());
        }
        for (String role : roleArr) {
            switch (role) {
                case "admin":
                    roles.add(roleRepository.findByRoleName(Roles.ROLE_ADMIN).get());
                    break;
                case "user":
                    roles.add(roleRepository.findByRoleName(Roles.ROLE_USER).get());
                    break;
                default:
                    return ResponseEntity.badRequest().body("Specified role not found");
            }
        }
        u.setRoles(roles);
        userService.saveUser(u);
        return ResponseEntity.ok("User signed up successfully");
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserEntity user = userService.findByLoginAndPassword(request.getUserName(), request.getPassword());
        String token = jwtProvider.generateToken(user.getUserName());
        CustomUserDetails customUser = CustomUserDetails.fromUserEntityToCustomUserDetails(user);
        List<String> roles = customUser.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());
        return new AuthResponse(token, roles);
    }
}