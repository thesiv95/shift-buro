package ftc.shift.sample.services;

import ftc.shift.sample.models.Book;
import ftc.shift.sample.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService {

  private final BookRepository bookRepository;

  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Book provideBook(String userId, String bookId) {
    return bookRepository.fetchBook(userId, bookId);
  }

  public Book updateBook(String userId, String bookId, Book book) {
    return bookRepository.updateBook(userId, bookId, book);
  }

  public void deleteBook(String userId, String bookId) {
    bookRepository.deleteBook(userId, bookId);
  }

  public Book createBook(String userId, Book book) {
    return bookRepository.createBook(userId, book);
  }

  public Collection<Book> provideBooks(String userId) {
    return bookRepository.getAllBooks(userId);
  }
}
