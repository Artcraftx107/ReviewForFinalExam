package librarysys;

import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private final Map<String, Book>books;
    private final Map<Integer, Member>members;
    private final Map<Member, List<Loan>>loans;

    public Library(){
        this.books=new TreeMap<>();
        this.loans=new TreeMap<>();
        this.members=new TreeMap<>();
    }
    //Methods
    public void addBook(Book book){
        if(!books.containsKey(book.isbn())){
            books.put(book.isbn(), book);
        }else{
            books.replace(book.isbn(), book);
        }
    }

    public Book getBook(String isbn){
        if(!books.containsKey(isbn)){
            throw new NoSuchElementException("There is no book with the isbn "+isbn);
        }
        return books.get(isbn);
    }

    public void addMember(Member member){
        if(!members.containsKey(member.id())){
            members.replace(member.id(), member);
        }else{
            members.put(member.id(), member);
        }
    }

    public Member getMember(int id){
        if(!members.containsKey(id)){
            throw new NoSuchElementException("There is no member with the id "+id);
        }
        return members.get(id);
    }

    public void borrowBook(int memberId, String isbn){
        Member member = getMember(memberId);
        Book book = getBook(isbn);

        if(!book.isAvailable()){
            throw new LibraryException("The book "+book.title()+ " is not available");
        }
        book.setAvailable(false);
        Loan loan = new Loan(member, book);
        loans.computeIfAbsent(member, k-> new ArrayList<>()).add(loan);
    }

    public void returnBook(int memberId, String isbn){
        Member member = getMember(memberId);
        Book book = getBook(isbn);

        List<Loan> memberLoans = loans.get(member);
        if(memberLoans.stream().noneMatch(loan -> loan.book().equals(book))||memberLoans==null){
            throw new LibraryException("No loan found for the book "+book.title()+" under the member "+memberId);
        }
        book.setAvailable(true);
        memberLoans.removeIf(loan -> loan.book().equals(book));
    }

    public List<Loan> getLoansByMember(int memberId){
        Member member = getMember(memberId);
        return loans.get(member);
    }

    //Utility code
    public List<Book> getAvailableBooks(){
        return books.values().stream().filter(Book::isAvailable).collect(Collectors.toList());
    }
}