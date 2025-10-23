package in.rahul.main.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "borrowed_books")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BorrowedBooks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long borrowedBookId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "bookId")
	private Books book;

	@CreationTimestamp
	@Column(name = "borrow_date", updatable = false)
	private LocalDateTime borrowDate;

	@Column(name = "return_date")
	private LocalDateTime returnDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 20)
	private BorrowStatus status;

	@UpdateTimestamp
	@Column(name = "last_updated")
	private LocalDateTime lastUpdated;

	// Constructors

	public BorrowedBooks(User user, Books book, BorrowStatus status) {
		this.user = user;
		this.book = book;
		this.status = status;
	}

}
