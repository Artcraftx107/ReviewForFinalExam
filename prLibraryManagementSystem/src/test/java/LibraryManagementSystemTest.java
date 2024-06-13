import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import library.*;

public class LibraryManagementSystemTest {
    private LibraryManagementSystem system;

    @BeforeEach
    public void setUp() {
        system = new LibraryManagementSystem();
    }

    @Test
    public void testAddAndSearchBook() {
        Book book = new Book("1", "Effective Java", "100", List.of());
        system.addBook(book);
        Book foundBook = system.searchBook("1");
        assertEquals("Effective Java", foundBook.title());
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book("1", "Effective Java", "100", List.of());
        system.addBook(book);
        Book updatedBook = new Book("1", "Effective Java 2nd Edition", "100", List.of());
        system.updateBook(updatedBook);
        Book foundBook = system.searchBook("1");
        assertEquals("Effective Java 2nd Edition", foundBook.title());
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("1", "Effective Java", "100", List.of());
        system.addBook(book);
        system.deleteBook("1");
        assertThrows(NoSuchElementException.class, () -> system.searchBook("1"));
    }

    @Test
    public void testAddAndSearchAuthor() {
        Author author = new Author("100", "Joshua Bloch");
        system.addAuthor(author);
        Author foundAuthor = system.searchAuthor("100");
        assertEquals("Joshua Bloch", foundAuthor.getName());
    }

    @Test
    public void testUpdateAuthor() {
        Author author = new Author("100", "Joshua Bloch");
        system.addAuthor(author);
        Author updatedAuthor = new Author("100", "Joshua Bloch Updated");
        system.updateAuthor(updatedAuthor);
        Author foundAuthor = system.searchAuthor("100");
        assertEquals("Joshua Bloch Updated", foundAuthor.getName());
    }

    @Test
    public void testDeleteAuthor() {
        Author author = new Author("100", "Joshua Bloch");
        system.addAuthor(author);
        system.deleteAuthor("100");
        assertThrows(NoSuchElementException.class, () -> system.searchAuthor("100"));
    }

    @Test
    public void testAddAndSearchBorrower() {
        Borrower borrower = new Borrower("200", "John Doe");
        system.addBorrower(borrower);
        Borrower foundBorrower = system.searchBorrower("200");
        assertEquals("John Doe", foundBorrower.getName());
    }

    @Test
    public void testUpdateBorrower() {
        Borrower borrower = new Borrower("200", "John Doe");
        system.addBorrower(borrower);
        Borrower updatedBorrower = new Borrower("200", "John Doe Updated");
        system.updateBorrower(updatedBorrower);
        Borrower foundBorrower = system.searchBorrower("200");
        assertEquals("John Doe Updated", foundBorrower.getName());
    }

    @Test
    public void testDeleteBorrower() {
        Borrower borrower = new Borrower("200", "John Doe");
        system.addBorrower(borrower);
        system.deleteBorrower("200");
        assertThrows(NoSuchElementException.class, () -> system.searchBorrower("200"));
    }

    @Test
    public void testBorrowAndReturnBook() {
        Book book = new Book("1", "Effective Java", "100", List.of());
        Borrower borrower = new Borrower("200", "John Doe");
        system.addBook(book);
        system.addBorrower(borrower);

        system.borrowBook("1", "200");
        Book borrowedBook = system.searchBook("1");
        assertTrue(borrowedBook.borrowers().contains("John Doe"));

        system.returnBook("1", "200");
        Book returnedBook = system.searchBook("1");
        assertFalse(returnedBook.borrowers().contains("John Doe"));
    }

    @Test
    public void testSaveAndLoadBooks() throws IOException {
        Book book = new Book("1", "Effective Java", "100", List.of());
        system.addBook(book);
        system.saveBooksToFile("books_test.txt");

        LibraryManagementSystem newSystem = new LibraryManagementSystem();
        newSystem.loadBooksFromFile("books_test.txt");
        Book foundBook = newSystem.searchBook("1");
        assertEquals("Effective Java", foundBook.title());
    }

    @Test
    public void testSaveAndLoadAuthors() throws IOException {
        Author author = new Author("100", "Joshua Bloch");
        system.addAuthor(author);
        system.saveAuthorsToFile("authors_test.txt");

        LibraryManagementSystem newSystem = new LibraryManagementSystem();
        newSystem.loadAuthorsFromFile("authors_test.txt");
        Author foundAuthor = newSystem.searchAuthor("100");
        assertEquals("Joshua Bloch", foundAuthor.getName());
    }

    @Test
    public void testSaveAndLoadBorrowers() throws IOException {
        Borrower borrower = new Borrower("200", "John Doe");
        system.addBorrower(borrower);
        system.saveBorrowersToFile("borrowers_test.txt");

        LibraryManagementSystem newSystem = new LibraryManagementSystem();
        newSystem.loadBorrowersFromFile("borrowers_test.txt");
        Borrower foundBorrower = newSystem.searchBorrower("200");
        assertEquals("John Doe", foundBorrower.getName());
    }
}

