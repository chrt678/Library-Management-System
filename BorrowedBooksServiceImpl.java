package in.rahul.main.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.rahul.main.Repository.BookRepository;
import in.rahul.main.Repository.BorrowedBooksRepository;
import in.rahul.main.Repository.UserRepository;
import in.rahul.main.entity.Books;
import in.rahul.main.entity.BorrowStatus;
import in.rahul.main.entity.BorrowedBooks;
import in.rahul.main.entity.User;
import jakarta.transaction.Transactional;

@Service
public class BorrowedBooksServiceImpl implements BorrowedBooksService {

	@Autowired
	private BorrowedBooksRepository borrowedBooksRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

//...................................................................................................................
	@Override
	@Transactional
	public BorrowedBooks borrowBook(long id, long bookId) {

		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

		Books book = bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));

		if (book.getBookAvailableCopies() <= 0) {
			throw new IllegalStateException("No copies Available");
		}

		book.setBookAvailableCopies(book.getBookAvailableCopies() - 1);
		bookRepository.save(book);

		BorrowedBooks borrow = new BorrowedBooks(user, book, BorrowStatus.BORROWED);

		return borrowedBooksRepository.save(borrow);
	}
//....................................................................................................................

	@Override
	public List<BorrowedBooks> findAll() {

		return borrowedBooksRepository.findAll();
	}

//.....................................................................................................................

	// ....................................................................................................................
	@Override
	public List<BorrowedBooks> findByUser(long id) {

		return borrowedBooksRepository.findByUserId(id);
	}

	@Override
	@Transactional
	public BorrowedBooks returnBook(long borrowedBookId) {
		BorrowedBooks record = borrowedBooksRepository.findById(borrowedBookId)
				.orElseThrow(() -> new IllegalArgumentException("Borrow record not Found"));
		if (record.getStatus() == BorrowStatus.RETURNED) {
			throw new IllegalStateException("Book already Returned");
		}

		record.setReturnDate(LocalDateTime.now());
		record.setStatus(BorrowStatus.RETURNED);

		Books book = record.getBook();
		book.setBookAvailableCopies(book.getBookAvailableCopies() + 1);
		bookRepository.save(book);

		return borrowedBooksRepository.save(record);
	}

}
