import library.*;

import javax.swing.text.html.parser.Entity;
import java.io.*;
import java.util.*;

public class LibraryManagementSystem {
    private static final String LINE_ERROR = "The data specified in the current line is incorrect or has a wrong format";
    private Map<String, Book> books;
    private Map<String, Borrower> borrowers;
    private Map<String, Author> authors;

    //Initializer
    public LibraryManagementSystem(){
        this.borrowers=new TreeMap<>();
        this.authors=new TreeMap<>();
        this.books=new TreeMap<>();
    }

    //Book Management methods

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("The given book cannot be null");
        }
        if (books.containsKey(book.bookID())) {
            updateBook(book);
        } else {
            books.put(book.bookID(), book);
        }
    }

    public void updateBook(Book book) {
        if(!books.containsKey(book.bookID())){
            throw new NoSuchElementException("There is no book with the ID "+book.bookID());
        }
        books.replace(book.bookID(), book);
    }

    public void deleteBook(String bookId){
        if(!books.containsKey(bookId)){
            throw new NoSuchElementException("There is no book with the ID "+bookId);
        }
        books.remove(bookId);
    }

    public Book searchBook(String bookId) {
        if (!books.containsKey(bookId)) {
            throw new NoSuchElementException("There is no book with the ID " + bookId);
        }
        return books.get(bookId);
    }

    //Author management methods
    public void addAuthor(Author author){
        if(author==null){
            throw new IllegalArgumentException("The author to be added cannot be null");
        }
        if(authors.containsKey(author.getAuthorID())){
            updateAuthor(author);
        }
        authors.put(author.getAuthorID(), author);
    }

    public void updateAuthor(Author author) {
        if(!authors.containsKey(author.getAuthorID())){
            throw new NoSuchElementException("There is no author with the ID "+author.getAuthorID());
        }
        authors.replace(author.getAuthorID(), author);
    }

    public void deleteAuthor(String authorID){
        if(!authors.containsKey(authorID)){
            throw new NoSuchElementException("There is no author with the ID "+authorID);
        }
        authors.remove(authorID);
    }

    public Author searchAuthor(String authorID){
        if(!authors.containsKey(authorID)){
            throw new NoSuchElementException("There is no author with the ID "+authorID);
        }
        return authors.get(authorID);
    }

    //Borrower management code
    public void addBorrower(Borrower borrower){
        if(borrower==null){
            throw new IllegalArgumentException("The borrower to be added cannot be null");
        }
        if(borrowers.containsKey(borrower.getBorrowerID())){
            updateBorrower(borrower);
        }
        borrowers.put(borrower.getBorrowerID(), borrower);
    }

    public void updateBorrower(Borrower borrower) {
        if(!borrowers.containsKey(borrower.getBorrowerID())){
            throw new NoSuchElementException("There is no author with the ID "+borrower.getBorrowerID());
        }
        borrowers.replace(borrower.getBorrowerID(), borrower);
    }

    public void deleteBorrower(String borrowerID){
        if(!borrowers.containsKey(borrowerID)){
            throw new NoSuchElementException("There is no author with the ID "+borrowerID);
        }
        borrowers.remove(borrowerID);
    }

    public Borrower searchBorrower(String borrowerID){
        if(!borrowers.containsKey(borrowerID)){
            throw new NoSuchElementException("There is no author with the ID "+borrowerID);
        }
        return borrowers.get(borrowerID);
    }

    // Borrowing and Returning Books
    public void borrowBook(String bookId, String borrowerID) {
        Borrower borrower = searchBorrower(borrowerID);
        Book book = searchBook(bookId);
        book.borrowers().add(borrower.getName());
    }

    public void returnBook(String bookID, String borrowerId) {
        Borrower borrower = searchBorrower(borrowerId);
        Book book = searchBook(bookID);
        if (!book.borrowers().contains(borrower.getName())) {
            throw new NoSuchElementException("There is no borrower with the ID " + borrowerId + " for the book " + book.title());
        }
        book.borrowers().remove(borrower.getName());
    }

    //File operations
    private <T> void saveToFile(Map<String, T>map, String filename)throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            for(T element : map.values()){
                bw.write(element.toString());
                bw.newLine();
            }
        }
    }

    private <Y> void loadFromFile(Map<String, Y>map, String filename, EntityCreator<Y> creator)throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line= br.readLine())!=null){
                try {
                    Y entity = creator.createEntity(line);
                    map.put(getId(entity), entity);
                }catch (InvalidPropertiesFormatException e){
                    System.err.println(e.getMessage());
                }
            }
        }catch (IOException e){
            throw new FileNotFoundException("The file "+filename+" was not found");
        }
        //Code so the file used to load from is deleted after loading
        File file = new File(filename);
        file.delete();
    }

    @FunctionalInterface
    interface EntityCreator<Y>{
        Y createEntity(String line) throws InvalidPropertiesFormatException;
    }

    private String getId(Object entity){
        if(entity instanceof Book){
            return ((Book) entity).bookID(); 
        } else if (entity instanceof  Author) {
            return ((Author) entity).getAuthorID(); 
        } else if (entity instanceof  Borrower) {
            return ((Borrower) entity).getBorrowerID();
        }else{
            throw new IllegalArgumentException("Unknown entity type");
        }
    }

    public void saveBooksToFile(String filename)throws IOException{
        saveToFile(books, filename);
    }

    public void loadBooksFromFile(String filename)throws IOException{
        loadFromFile(books, filename, line->{
            String[] parts =line.split(",");
            if(parts.length<4){
                throw new InvalidPropertiesFormatException(LINE_ERROR); 
            }
            List<String> borrowers = parts.length==4?List.of(parts[3].split(";")) : new ArrayList<>();
            return new Book(parts[0], parts[1], parts[2], borrowers);
        });
    }

    public void saveAuthorsToFile(String filename)throws IOException{
        saveToFile(authors, filename);
    }

    public void loadAuthorsFromFile(String filename)throws IOException{
        loadFromFile(authors, filename, line -> {
            String[] parts = line.split(",");
            if(parts.length<2){
                throw new InvalidPropertiesFormatException(LINE_ERROR);
            }
            return new Author(parts[0], parts[1]);
        });
    }

    public void saveBorrowersToFile(String filename)throws IOException{
        saveToFile(borrowers, filename);
    }

    public void loadBorrowersFromFile(String filename)throws IOException{
        loadFromFile(borrowers, filename, line -> {
            String[] parts = line.split(",");
            if(parts.length<2){
                throw new InvalidPropertiesFormatException(LINE_ERROR);
            }
            return new Borrower(parts[0], parts[1]);
        });
    }
}