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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import flip.service.UserService;

import jakarta.validation.Valid;
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
    private BookService bookService;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private BookRepository bookRepository;


    @PostMapping("/register/user")
    public ResponseEntity<User> postNewUser(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }


    @PostMapping("/register/book")
    public ResponseEntity<Book> postNewBook(@RequestBody @Valid Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @PostMapping("/register/collection")
    public ResponseEntity<Collection> postNewCollection(@RequestBody @Valid Collection collection) {
        return ResponseEntity.ok(collectionRepository.save(collection));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PutMapping("/books")
    public ResponseEntity<Book> editBook(@RequestParam(value = "Id", required = true) String Id, @RequestBody Book book){
        return ResponseEntity.ok(bookService.editBook(Id, book));
    }

    @GetMapping("/collections")
    public ResponseEntity<List<Collection>> getAllCollections(){
        return ResponseEntity.ok(collectionService.getAllCollections());
    }

    @GetMapping("/initiate")
    public ResponseEntity<List<Book>> Initiate(){
        return ResponseEntity.ok(collectionService.initiate());
    }

    @PutMapping("/collections")
    public ResponseEntity<HttpStatus> updateCollection(@RequestParam(required = true) Integer id, @RequestBody BookDto bookDto){
        collectionService.addBooksToCollection(id, bookDto);
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
}
