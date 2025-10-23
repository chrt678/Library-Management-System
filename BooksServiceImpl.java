package in.rahul.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.rahul.main.Repository.BookRepository;
import in.rahul.main.entity.Books;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Books addBooks(Books book) {

		return bookRepository.save(book);
	}

	@Override
	public void deleteBookById(long bookId) {

		bookRepository.deleteById(bookId);

	}

	@Override
	public List<Books> getAllBooks() {

		return bookRepository.findAll();
	}

	@Override
	public Books getBookById(long bookId) {

		return bookRepository.findById(bookId)
				.orElseThrow(() -> new RuntimeException("book not found by bookId" + bookId));
	}

	@Override
	public Books updateBookById(long bookId, Books book) {

		Books newbook = bookRepository.findById(bookId)
				.orElseThrow(() -> new RuntimeException("book not found by bookId" + bookId));
		newbook.setBookTitle(book.getBookTitle());
		newbook.setBookAuthor(book.getBookAuthor());
		newbook.setBookCategory(book.getBookCategory());
		newbook.setBookAvailableCopies(book.getBookAvailableCopies());

		return bookRepository.save(newbook);
	}

}
