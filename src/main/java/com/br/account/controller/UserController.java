package com.br.account.controller;

import com.br.account.entity.Feature;
import com.br.account.entity.News;
import com.br.account.entity.User;
import com.br.account.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate) {
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        var user = userService.updateUser(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        var users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<User> findByAccountNumber(@PathVariable String accountNumber) {
        var user = userService.findByAccountNumber(accountNumber);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{userId}/features")
    public ResponseEntity<User> addFeatureToUser(@PathVariable Long userId, @RequestBody Feature feature) {
        var updatedUser = userService.addFeatureToUser(userId, feature);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping("/{userId}/news")
    public ResponseEntity<User> addNewsToUser(@PathVariable Long userId, @RequestBody News news) {
        var updatedUser = userService.addNewsToUser(userId, news);
        return ResponseEntity.ok(updatedUser);
    }
}
