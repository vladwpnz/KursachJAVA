package com.friends.sharing.repository;

import com.friends.sharing.model.Present;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresentRepository extends JpaRepository<Present,Long> {
    default List<Present> findHeldPresents(Long id) {
        return findAll().stream()
                .filter(present -> id.equals(present.getHolder().getUser_id()))
                .toList();
    }

    default List<Present> findOwnedPresents(Long id) {
        return findAll().stream()
                .filter(present -> id.equals(present.getOwner().getUser_id()))
                .toList();
    }
}
