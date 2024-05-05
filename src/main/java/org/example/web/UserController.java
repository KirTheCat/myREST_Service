package org.example.web;

import org.example.model.entity.Media;
import org.example.model.entity.User;
import org.example.service.*;
import org.example.service.impl.MediaServiceImpl;
import org.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractController<User> {
    private final UserServiceImpl userService;
    private final MediaServiceImpl mediaService;
    @Autowired
    public UserController(UserServiceImpl userService,MediaServiceImpl mediaService) {
        this.userService = userService;
        this.mediaService = mediaService;
    }
    @PostMapping("/{userId}/add_to_favorites/{mediaId}")
    public ResponseEntity<String> addFavorite(@PathVariable Long userId, @PathVariable Long mediaId) {
        User user = userService.read(userId);
        Media media = mediaService.read(mediaId);
        if (user == null || media == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.getFavoriteMedia().add(media);
        userService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @Override
    public Service<User> getService() {
        return userService;
    }
}