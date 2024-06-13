package library;

import java.util.ArrayList;
import java.util.List;

public record Book(String bookID, String title, String authorID, List<String> borrowers) {
    public Book(String id, String title, String authorId) {
        this(id, title, authorId, new ArrayList<>());
    }

    public Book(String bookID, String title, String authorID, List<String> borrowers) {
        this.bookID = bookID;
        this.title = title;
        this.authorID = authorID;
        this.borrowers = new ArrayList<>(borrowers);
    }
    @Override
    public String toString() {
        return bookID + "," + title + "," +
                authorID + "," + borrowers;
    }
}
