package flip.service;

import flip.entity.Book;
import flip.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book editBook(String book_id, Book book) {
        Optional<Book> receivedBook = bookRepository.findById(Long.valueOf(book_id));
        Book bookObject = null;
        if(receivedBook.isPresent()) {
            bookObject = receivedBook.get();
            bookObject.setCategory(book.getCategory());
            bookObject.setRating(book.getRating());
            bookObject.setPrice(book.getPrice());
            bookObject.setAuthor(book.getAuthor());
            bookObject.setName(book.getName());
            bookObject.setSales(book.getSales());
            bookRepository.save(bookObject);
            return bookObject;
        }
        else {
            throw new RuntimeException("Book not found");
        }
    }

    public void addDiscount(String author, String category, Integer discount){
        if(author == null && category == null){
            throw new RuntimeException("Book not found");
        }
        else {
            if(!author.isBlank()){
                List<Book> bookListByAuthor = bookRepository.findByAuthor(author);
                bookListByAuthor.forEach(book -> {
                    Float currentPrice = book.getPrice();
                    if(currentPrice.isNaN()) return;
                    book.setPrice((currentPrice*(100-discount))/100);
                    bookRepository.save(book);
                });
            }
            if(!category.isBlank()){
                List<Book> bookListByAuthor = bookRepository.findByCategory(category);
                bookListByAuthor.forEach(book -> {
                    Float currentPrice = book.getPrice();
                    if(currentPrice.isNaN()) return;
                    book.setPrice((currentPrice*(100-discount))/100);
                    bookRepository.save(book);
                });
            }
        }
    }
}