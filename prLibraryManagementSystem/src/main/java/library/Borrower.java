package library;

public class Borrower {
    private String borrowerID;
    private String name;

    public Borrower(String borrowerID, String name){
        if(borrowerID.isBlank()||name.isBlank()){
            throw new IllegalArgumentException("Neither the borrower's ID or its name can be blank");
        }
        this.borrowerID=borrowerID;
        this.name=name;
    }

    //Getters

    public String getName() {
        return name;
    }

    public String getBorrowerID() {
        return borrowerID;
    }

    @Override
    public String toString() {
        return borrowerID+","+name;
    }
}
