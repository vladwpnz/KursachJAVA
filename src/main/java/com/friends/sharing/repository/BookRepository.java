package com.friends.sharing.repository;

import com.friends.sharing.model.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    default List<Book> findHeldBooks(Long id) {
        return findAll().stream()
                .filter(book -> id.equals(book.getHolder().getUser_id()))
                .toList();
    }

    default List<Book> findOwnedBooks(Long id) {
        return findAll().stream()
                .filter(book -> id.equals(book.getOwner().getUser_id()))
                .toList();
    }
}
