package librarysys;

public class LibraryException extends RuntimeException{
    public LibraryException(){
        super();
    }
    public LibraryException(String msg){
        super(msg);
    }
}
