package flip.controller;

import flip.entity.User;
import flip.repository.CollectionRepository;
import flip.repository.UserRepository;
import flip.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import flip.service.UserService;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/register")
    public User postNewUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/initiate")
    public ResponseEntity<String> Initiate(){
        return ResponseEntity.ok(collectionService.initiate().toString());
    }
}
