package com.task.task.controller;

import com.task.task.domain.User;
import com.task.task.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class IndexController {

    private final UserService userService;

    @RequestMapping(value = "/greeting/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> welcome(@PathVariable("username") String username) {
        User user = userService.loadUserByUsername(username);
        if (user == null) {
            return new ResponseEntity<>("page not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("Welcome! " + user.getFirstName() + " " + user.getLastName());
    }
}
