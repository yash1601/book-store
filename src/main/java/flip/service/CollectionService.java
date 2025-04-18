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

    public void addBooksToCollection(Integer collectionId, BookDto bookDto) {
        try {
            Collection collection = collectionRepository.findById(collectionId.toString()).orElseThrow(() -> new ClassNotFoundException("error"));
            List<Integer> bookList = Arrays.asList(bookDto.getBookIds());
            log.info(String.valueOf(bookList));
            bookList.forEach((book_id) -> {
                Book book = null;
                try {
                    book = bookRepository.findById(book_id.toString()).orElseThrow(() -> new ClassNotFoundException("error"));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                log.info(book.toString());
                collection.getBooks().add(book);
                collectionRepository.save(collection);
            });

        }
        catch (Exception e){
            throw new RuntimeException("Something went wrong", e);
        }
    }
}