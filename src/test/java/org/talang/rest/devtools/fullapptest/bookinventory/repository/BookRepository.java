package org.talang.rest.devtools.fullapptest.bookinventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.talang.rest.devtools.fullapptest.bookinventory.domain.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,String> {
    Book findByIsbn(String isbn);
    List<Book> findByAuthor(String author);
    List<Book> findByTitleLike(String title);
}
