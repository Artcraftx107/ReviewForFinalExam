package librarysys;

public record Loan(Member member, Book book) {
    @Override
    public String toString() {
        return "The member "+member.name()+" has a loan for "+book.title();
    }
}
