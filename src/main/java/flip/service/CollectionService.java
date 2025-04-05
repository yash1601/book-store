package flip.service;

import flip.Dto.BookDto;
import flip.entity.Book;
import flip.entity.Collection;
import flip.repository.BookRepository;
import flip.repository.CollectionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.Long.parseLong;

@Service
@Slf4j
@AllArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;

    private final BookRepository bookRepository;

    public List<Collection> getAllCollections(){
        return collectionRepository.findAll();
    }

    public List<Book> initiate(){
        Collection collection = new Collection();
        Book book = new Book();
        book.setName("name");
        book.setAuthor("yash");
        book.setCategory("author");
        book.setPrice(10.0f);

        Set<Book> bookList = new HashSet<>();
        bookList.add(book);
        Book book2 = new Book();
        bookRepository.save(book);

        List<Book> listBooks = new ArrayList<Book>();
        listBooks.add(book);
        listBooks.add(book2);
        collection.setBooks(bookList);
        collectionRepository.save(collection);
        return listBooks;
    }

    public void addBooksToCollection(Integer id, BookDto bookDto) {
        try {
            Collection collection = collectionRepository.findById(id.toString()).orElseThrow(() -> new ClassNotFoundException("error"));
            List<Integer> bookList = Arrays.asList(bookDto.getBookIds());
            log.info(String.valueOf(bookList));
            bookList.forEach((book_id) -> {
                Book book = null;
                try {
                    book = bookRepository.findById(book_id.toString()).orElseThrow(() -> new ClassNotFoundException("error"));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
//                book.setCollection(collection);
                log.info(book.toString());
//                collection.getBooks().add(book);
                collectionRepository.save(collection);
            });

        }
        catch (Exception e){

        }
    }
}