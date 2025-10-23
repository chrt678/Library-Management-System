package in.rahul.main.Service;

import java.util.List;

import in.rahul.main.entity.BorrowedBooks;

public interface BorrowedBooksService {

	BorrowedBooks borrowBook(long id, long bookId);

	List<BorrowedBooks> findAll();

	List<BorrowedBooks> findByUser(long id);

	BorrowedBooks returnBook(long borrowedBookId);

}
