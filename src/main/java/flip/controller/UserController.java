package flip.controller;

import flip.Dto.BookDto;
import flip.entity.Book;
import flip.entity.Collection;
import flip.entity.User;
import flip.repository.BookRepository;
import flip.repository.CollectionRepository;
import flip.repository.UserRepository;
import flip.service.BookService;
import flip.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import flip.service.UserService;

import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private BookService bookService;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private BookRepository bookRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @PostMapping("/register/user")
    public ResponseEntity<User> postNewUser(@RequestBody User user) {
        user.setUser_id(new ObjectId().toString());
        log.info(user.getUser_id());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userEntity = userRepository.findByName(userName);
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(userEntity.getPassword());
        userEntity.setBooks(userEntity.getBooks());
        userEntity.setRoles(userEntity.getRoles());
        userRepository.save(userEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/books")
    public ResponseEntity<HttpStatus> postNewBook(@RequestBody @Valid List<Book> book) {
        bookService.saveBooks(book);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping("/books")
    public ResponseEntity<Book> editBook(@RequestParam(value = "Id", required = true) String Id, @RequestBody Book book){
        return ResponseEntity.ok(bookService.editBook(Id, book));
    }

    @PostMapping("/collection")
    public ResponseEntity<Collection> postNewCollection(@RequestBody @Valid Collection collection) {
        return ResponseEntity.ok(collectionRepository.save(collection));
    }

    @PutMapping("/add-to-collection")
    public ResponseEntity<HttpStatus> updateCollection(@RequestParam(required = true) Integer collectionId, @RequestBody BookDto bookDto){
        collectionService.addBooksToCollection(collectionId, bookDto);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @PutMapping("/discount")
    public ResponseEntity<String> addDiscount(@RequestParam(required = false) String author, @RequestParam(required = false) String category, @RequestParam Integer discount){
        try {
            bookService.addDiscount(author, category, discount);
        }
        catch (RuntimeException e){
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok("Discount added");
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/collections")
    public ResponseEntity<List<Collection>> getAllCollections(){
        return ResponseEntity.ok(collectionService.getAllCollections());
    }

    @PutMapping("add-to-user")
    public ResponseEntity<String> addBookToUser(@RequestParam(value = "bookId", required = true) String bookId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userService.addBookToUser(bookId, userName);
        return ResponseEntity.ok("User updated");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String Id){
        return ResponseEntity.ok(userService.getUserById(Id));
    }

    @GetMapping("/user/{id}/books")
    public ResponseEntity<List<Book>> getUserBooks(@PathVariable("id") String Id){
        return ResponseEntity.ok(userService.getUserBooks(Id));
    }

}
