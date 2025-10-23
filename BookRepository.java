package in.rahul.main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.rahul.main.entity.Books;

public interface BookRepository extends JpaRepository<Books, Long> {

}
