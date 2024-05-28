package com.friends.sharing;

import com.friends.sharing.configuration.security.Authorities;
import com.friends.sharing.dto.request.AddBookRequest;
import com.friends.sharing.dto.request.GiveBookRequest;
import com.friends.sharing.dto.request.ReturnBookRequest;
import com.friends.sharing.dto.response.*;
import com.friends.sharing.exception.ItemException;
import com.friends.sharing.model.Book;
import com.friends.sharing.model.User;
import com.friends.sharing.repository.BookRepository;
import com.friends.sharing.repository.UserRepository;
import com.friends.sharing.service.FriendsSharingService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class FriendsSharingServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    BookRepository bookRepository;

    @InjectMocks
    FriendsSharingService friendsSharingService;

    User user = User.builder().user_id(1L).name("vadim").email("email@gmail.com")
            .password("1234").authority(Authorities.USER).build();

    @Test
    @DisplayName("Test for addBook() method")
    void testAddBook() {
        var request = new AddBookRequest("Joshua Bloch", "Effective Java");

        var expect = BookWithUserDTO.builder()
                .author("Joshua Bloch")
                .title("Effective Java")
                .person(UserDTO.builder()
                        .name("vadim")
                        .email("email@gmail.com")
                        .build())
                .build();

        assertThat(friendsSharingService.addBook(request, user))
                .isEqualTo(expect);
    }

    @Test
    @DisplayName("Test for getHeldItems() method")
    void testGetHeldItems() {
        var expectOne = BookWithUserDTO.builder()
                .author("Joshua Bloch")
                .title("Effective Java")
                .person(UserDTO.builder()
                        .name("vadim")
                        .email("email@gmail.com")
                        .build())
                .build();
        var expectTwo = BookWithUserDTO.builder()
                .author("Hahaha")
                .title("Python")
                .person(UserDTO.builder()
                        .name("vlad")
                        .email("ignat@gmail.com")
                        .build())
                .build();
        var expect = ItemsWithUser.builder().books(List.of(expectOne, expectTwo)).build();

        when(bookRepository.findHeldBooks(1L)).thenReturn(List.of(
                Book.builder().author("Joshua Bloch").title("Effective Java").owner(user).build(),
                Book.builder().author("Hahaha").title("Python").owner(
                        User.builder().name("vlad").email("ignat@gmail.com").build()).build()
        ));

        assertThat(friendsSharingService.getHeldItems(user))
                .isEqualTo(expect);
    }

    @Test
    @DisplayName("Test for getOwnedItems() method")
    void testGetOwnedItems() {
        var expectOne = BookWithUserDTO.builder()
                .author("Joshua Bloch")
                .title("Effective Java")
                .person(UserDTO.builder()
                        .name("vadim")
                        .email("email@gmail.com")
                        .build())
                .build();
        var expect = ItemsWithUser.builder().books(List.of(expectOne)).build();

        when(bookRepository.findOwnedBooks(1L)).thenReturn(List.of(
                Book.builder().author("Joshua Bloch").title("Effective Java").holder(user).build()
        ));

        assertThat(friendsSharingService.getOwnedItems(user))
                .isEqualTo(expect);
    }

    @Test
    @DisplayName("Test for getItems() method")
    void testGetItems() {
        var expectOne = BookDTO.builder()
                .book_id(1L)
                .author("Joshua Bloch")
                .title("Effective Java")
                .holder_id(1L)
                .owner_id(1L)
                .build();
        var expectTwo = BookDTO.builder()
                .book_id(2L)
                .author("Someone")
                .title("Not Effective Java")
                .holder_id(1L)
                .owner_id(2L)
                .build();
        var expect = Items.builder().books(List.of(expectOne, expectTwo)).build();

        when(bookRepository.findAll()).thenReturn(List.of(
                Book.builder().book_id(1L).author("Joshua Bloch").title("Effective Java").holder(user).owner(user).build(),
                Book.builder().book_id(2L).author("Someone").title("Not Effective Java").holder(user).owner(
                        User.builder().user_id(2L).build()).build()
        ));

        assertThat(friendsSharingService.getItems())
                .isEqualTo(expect);
    }

    @Test
    @DisplayName("Test for shareBook() method")
    void testShareBook() {
        var request = new GiveBookRequest("Effective Java", "ignat@gmail.com");

        var expect = BookWithUserDTO.builder()
                .author("Joshua Bloch")
                .title("Effective Java")
                .person(UserDTO.builder()
                        .name("vlad")
                        .email("ignat@gmail.com")
                        .build())
                .build();

        when(bookRepository.findOwnedBooks(1L)).thenReturn(List.of(
                Book.builder().author("Joshua Bloch").title("Effective Java").holder(user).owner(user).build()
        ));
        when(userRepository.findUserByEmail("ignat@gmail.com")).thenReturn(Optional.ofNullable(
                User.builder().name("vlad").email("ignat@gmail.com").build()
        ));

        assertThat(friendsSharingService.shareBook(request, user))
                .isEqualTo(expect);
    }

    @Test
    @DisplayName("Test for shareBook() method(no books)")
    void testShareBook_NoBooks() {
        var request = new GiveBookRequest("Effective Java", "ignat@gmail.com");

        assertThatThrownBy(() -> friendsSharingService.shareBook(request, user))
                .isInstanceOf(ItemException.class)
                .hasMessage("You do not own a book with that title!");
    }

    @Test
    @DisplayName("Test for shareBook() method(book already given)")
    void testShareBook_AlreadyGiven() {
        var request = new GiveBookRequest("Effective Java", "ignat@gmail.com");

        when(bookRepository.findOwnedBooks(1L)).thenReturn(List.of(
                Book.builder().author("Joshua Bloch").title("Effective Java").holder(
                        User.builder().name("vlad").email("ignat@gmail.com").build()
                ).owner(user).build()
        ));

        assertThatThrownBy(() -> friendsSharingService.shareBook(request, user))
                .isInstanceOf(ItemException.class)
                .hasMessage("You have already given this book to someone!");
    }

    @Test
    @DisplayName("Test for shareBook() method(no user)")
    void testShareBook_NoUser() {
        var request = new GiveBookRequest("Effective Java", "ignat@gmail.com");

        when(bookRepository.findOwnedBooks(1L)).thenReturn(List.of(
                Book.builder().author("Joshua Bloch").title("Effective Java").holder(user).owner(user).build()
        ));

        assertThatThrownBy(() -> friendsSharingService.shareBook(request, user))
                .isInstanceOf(ItemException.class)
                .hasMessage("There are no users with that username!!");
    }

    @Test
    @DisplayName("Test for giveBook() method")
    void testGiveBook() {
        var request = new GiveBookRequest("Effective Java", "ignat@gmail.com");

        var expect = BookWithUserDTO.builder()
                .author("Joshua Bloch")
                .title("Effective Java")
                .person(UserDTO.builder()
                        .name("vlad")
                        .email("ignat@gmail.com")
                        .build())
                .build();

        when(bookRepository.findOwnedBooks(1L)).thenReturn(List.of(
                Book.builder().author("Joshua Bloch").title("Effective Java").holder(user).owner(user).build()
        ));
        when(userRepository.findUserByEmail("ignat@gmail.com")).thenReturn(Optional.ofNullable(
                User.builder().name("vlad").email("ignat@gmail.com").build()
        ));

        assertThat(friendsSharingService.giveBook(request, user))
                .isEqualTo(expect);
    }

    @Test
    @DisplayName("Test for giveBook() method(no books)")
    void testGiveBook_NoBooks() {
        var request = new GiveBookRequest("Effective Java", "ignat@gmail.com");

        assertThatThrownBy(() -> friendsSharingService.giveBook(request, user))
                .isInstanceOf(ItemException.class)
                .hasMessage("You do not own a book with that title!");
    }

    @Test
    @DisplayName("Test for giveBook() method(book already given)")
    void testGiveBook_AlreadyGiven() {
        var request = new GiveBookRequest("Effective Java", "ignat@gmail.com");

        when(bookRepository.findOwnedBooks(1L)).thenReturn(List.of(
                Book.builder().author("Joshua Bloch").title("Effective Java").holder(
                        User.builder().name("vlad").email("ignat@gmail.com").build()
                ).owner(user).build()
        ));

        assertThatThrownBy(() -> friendsSharingService.giveBook(request, user))
                .isInstanceOf(ItemException.class)
                .hasMessage("You have already given this book to someone!");
    }

    @Test
    @DisplayName("Test for giveBook() method(no user)")
    void testGiveBook_NoUser() {
        var request = new GiveBookRequest("Effective Java", "ignat@gmail.com");

        when(bookRepository.findOwnedBooks(1L)).thenReturn(List.of(
                Book.builder().author("Joshua Bloch").title("Effective Java").holder(user).owner(user).build()
        ));

        assertThatThrownBy(() -> friendsSharingService.giveBook(request, user))
                .isInstanceOf(ItemException.class)
                .hasMessage("There are no users with that username!!");
    }

    @Test
    @DisplayName("Test for returnBook() method")
    void testReturnBook() {
        var request = new ReturnBookRequest("Effective Java");

        when(bookRepository.findHeldBooks(1L)).thenReturn(List.of(
                Book.builder().author("Joshua Bloch").title("Effective Java").holder(user).owner(
                        User.builder().name("vlad").email("ignat@gmail.com").build()
                ).build()
        ));

        friendsSharingService.returnBook(request, user);
    }

    @Test
    @DisplayName("Test for returnBook() method(no books)")
    void testReturnBook_NoBooks() {
        var request = new ReturnBookRequest("Effective Java");

        assertThatThrownBy(() -> friendsSharingService.returnBook(request, user))
                .isInstanceOf(ItemException.class)
                .hasMessage("You do not hold a book with that title!");
    }

    @Test
    @DisplayName("Test for returnBook() method(already owner)")
    void testReturnBook_AlreadyOwner() {
        var request = new ReturnBookRequest("Effective Java");

        when(bookRepository.findHeldBooks(1L)).thenReturn(List.of(
                Book.builder().author("Joshua Bloch").title("Effective Java").holder(user).owner(user).build()
        ));

        assertThatThrownBy(() -> friendsSharingService.returnBook(request, user))
                .isInstanceOf(ItemException.class)
                .hasMessage("You are the owner of this book!");
    }

    @Test
    @DisplayName("Test for deleteBook() method")
    void testDeleteBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(
                Book.builder().author("Joshua Bloch").title("Effective Java").holder(user).owner(user).build()
        ));

        assertThat(friendsSharingService.deleteBook(1L))
                .isEqualTo(true);
    }

    @Test
    @DisplayName("Test for deleteBook() method(no book)")
    void testDeleteBook_NoBook() {
        assertThat(friendsSharingService.deleteBook(1L))
                .isEqualTo(false);
    }

    @Test
    @DisplayName("Test for forceReturnBook() method")
    void testForceReturnBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(
                Book.builder().author("Joshua Bloch").title("Effective Java").holder(user).owner(user).build()
        ));

        assertThat(friendsSharingService.deleteBook(1L))
                .isEqualTo(true);
    }

    @Test
    @DisplayName("Test for forceReturnBook() method(no book)")
    void testForceReturnBook_NoBook() {
        assertThat(friendsSharingService.deleteBook(1L))
                .isEqualTo(false);
    }
}
