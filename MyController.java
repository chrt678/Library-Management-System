package in.rahul.main.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.rahul.main.Repository.BookRepository;
import in.rahul.main.Repository.UserRepository;
import in.rahul.main.Service.BooksService;
import in.rahul.main.entity.Books;
import in.rahul.main.entity.User;

@Controller
public class MyController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private BooksService booksService;

	@Autowired
	private BookRepository bookRepository;
//......................................................................................................................

	@GetMapping("/addBook")
	public String addBookHandler() {
		return "addBook";
	}

	@PostMapping("/addBook")
	public String addBookHandler(Books book) {
		booksService.addBooks(book);
		return "redirect:/addBook";
	}

	@GetMapping("/admin")
	public String adminHandler() {
		return "admin.html";
	}

	@PostMapping("/deleteBook")
	public String deleteBookById(@RequestParam("bookId") long bookId) {

		Optional<Books> book = bookRepository.findById(bookId);
		if (book.isPresent()) {
			booksService.deleteBookById(bookId);
			return "redirect:/deleteSuccess";
		} else {
			return "redirect:/notFound";
		}

	}

	@GetMapping("/deleteBook")
	public String deleteBookHandler() {
		return "deleteBook";
	}

	@GetMapping("/home")
	public String homeHandler() {
		return "home.html";
	}

	@GetMapping("/login")
	public String loginHandler() {
		return "custom_login.html";
	}

	@GetMapping("/notFound")
	public String NotFounddeleteBookHandler() {
		return "notFound";
	}

	@GetMapping("/register")
	public String registerHandler() {
		return "register.html";
	}

	@PostMapping("/saveUser")
	public String SaveUser(@RequestParam String username, @RequestParam String email, @RequestParam String password,
			@RequestParam(required = false) String roles) {

		User newuser = new User();
		newuser.setUsername(username);
		newuser.setEmail(email);
		newuser.setPassword(passwordEncoder.encode(password));

		String roleToSave = (roles == null || roles.isEmpty()) ? "USER" : roles.replace("ROLE_", "");
		newuser.setRoles(roleToSave);
		userRepository.save(newuser);

		return "redirect:/login";
	}

//..................................................................................................................

	@GetMapping("/updateBook")
	public String showBookHandler() {
		return "updateBook";
	}

	@GetMapping("/deleteSuccess")
	public String SuccessdeleteBookHandler() {
		return "deleteSuccess";
	}

	@PostMapping("/updateBook")
	public String updateBookHandler(@RequestParam("bookId") long bookId, @RequestParam("bookTitle") String bookTitle,
			@RequestParam("bookAuthor") String bookAuthor, @RequestParam("bookCategory") String bookCategory,
			@RequestParam("bookAvailableCopies") Integer bookAvailableCopies) {
		Books newBook = new Books();

		newBook.setBookTitle(bookTitle);
		newBook.setBookAuthor(bookAuthor);
		newBook.setBookCategory(bookCategory);
		newBook.setBookAvailableCopies(bookAvailableCopies);
		booksService.updateBookById(bookId, newBook);
		return "redirect:/updateBook";
	}

	@GetMapping("/user")
	public String userHandler() {
		return "user.html";
	}

}
