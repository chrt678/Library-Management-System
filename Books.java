package in.rahul.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Books {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookId;

	@NotBlank(message = "Book Title is Required.")
	private String bookTitle;

	@NotBlank(message = "Book Author is Required.")
	private String bookAuthor;

	// @NotBlank(message = "Book Category is Required.")
	private String bookCategory;

	@NotNull(message = "Book Available Copies is Required.")
	private int bookAvailableCopies;

}
