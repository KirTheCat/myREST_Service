package org.example.controller;

import org.example.service.*;
import org.example.model.entity.User;
import org.example.model.entity.Media;
import org.example.model.enums.RoleEnum;
import org.example.model.authDto.SignInRequest;
import org.springframework.http.HttpStatus;
import org.example.model.authDto.SignUpRequest;
import org.springframework.http.ResponseEntity;
import org.example.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.example.service.impl.MediaServiceImpl;
import org.example.model.authDto.JwtAuthenticationResponse;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User> {

    private final UserServiceImpl userService;
    private final MediaServiceImpl mediaService;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(UserServiceImpl userService, MediaServiceImpl mediaService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.mediaService = mediaService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/sign_in")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("/{userId}/add_to_favorites/{mediaId}")
    public ResponseEntity<String> addFavorite(@PathVariable Long userId, @PathVariable Long mediaId) {
        User user = userService.read(userId);
        Media media = mediaService.read(mediaId);

        if (user == null || media == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.getFavoriteMedia().add(media);
        userService.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        User user = userService.read(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User currentUser = userService.getByUsername(currentPrincipalName);

        if (currentUser.getId().equals(user.getId()) && currentUser.getRole().equals(RoleEnum.ROLE_USER)) {
            return new ResponseEntity<>(user, headers, HttpStatus.OK);
        } else if (currentUser.getRole().equals(RoleEnum.ROLE_ADMIN)) {
            return new ResponseEntity<>(user, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public Service<User> getService() {
        return userService;
    }
}