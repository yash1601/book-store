package flip.service;

import flip.entity.Book;
import flip.entity.Collection;
import flip.entity.User;
import flip.repository.BookRepository;
import flip.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BookRepository bookRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
        return userRepository.findById(new ObjectId(id).toString()).orElseThrow();
    }

    public List<Book> getUserBooks(String id){
        User userEntity = userRepository.findById(id).orElseThrow();
        Set<Book> books = userEntity.getBooks();
        return new ArrayList<Book>(books);
    }

    public void addBookToUser(String bookId, String name){
        Book bookEntity = bookRepository.findById(bookId).orElseThrow();
        User userEntity = userRepository.findByName(name);
        userEntity.getBooks().add(bookEntity);
        userRepository.save(userEntity);
    }
}
