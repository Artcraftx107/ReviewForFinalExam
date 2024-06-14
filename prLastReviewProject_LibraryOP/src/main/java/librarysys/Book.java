package librarysys;

import java.util.SplittableRandom;

public class Book implements Borrowable{
    private final String isbn;
    private final String title;
    private final String author;
    private boolean isAvailable;

    public Book(String isbn, String title, String author){
        if(isbn.isBlank()||title.isBlank()||author.isBlank()){
            throw new LibraryException("Neither the isbn, the title or the author can be blank");
        }
        this.isAvailable=false;
        this.title=title;
        this.isbn=isbn;
        this.author=author;
    }
    //Getters

    public String isbn() {
        return isbn;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return isbn+","+title+","+author;
    }
}