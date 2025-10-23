package in.rahul.main.Service;

import java.util.List;

import in.rahul.main.entity.Books;

public interface BooksService {

	public Books addBooks(Books book);

	public void deleteBookById(long bookId);

	public List<Books> getAllBooks();

	public Books getBookById(long bookId);

	public Books updateBookById(long bookId, Books book);

}
