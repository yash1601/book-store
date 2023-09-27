package flip.service;

import flip.entity.Book;
import flip.entity.Collection;
import flip.repository.BookRepository;
import flip.repository.CollectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;

    private final BookRepository bookRepository;

    public List<Collection> getAllCollections(){
        return collectionRepository.findAll();
    }

    public Collection initiate(){
        Collection collection = new Collection();
        Book book = new Book();
        book.setName("name");
        book.setAuthor("name");
        book.setGenre("name");
        book.setPrice(10);
        bookRepository.save(book);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        collection.setBooks(bookList);
        collectionRepository.save(collection);
        return collection;
    }
}