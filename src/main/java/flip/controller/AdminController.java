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
import flip.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

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

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        if(allUsers != null && !allUsers.isEmpty()){
            return new ResponseEntity<>(allUsers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
}
