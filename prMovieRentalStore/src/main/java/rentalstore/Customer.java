package rentalstore;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private int membershipId;
    private List<Movie> rentedMovies;

    public Customer(String name, int membershipId, List<Movie> rentedMovies){
        if(name.isBlank()){
            throw new IllegalArgumentException("The name of the customer cannot be blank");
        }else if (membershipId<0){
            throw new IllegalArgumentException("The membershipId cannot be negative");
        }else{
            this.name=name;
            this.membershipId=membershipId;
            this.rentedMovies=new ArrayList<>(rentedMovies);
        }
    }

    public String getName() {
        return name;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public List<Movie> getRentedMovies() {
        return rentedMovies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public void setRentedMovies(List<Movie> rentedMovies) {
        this.rentedMovies = rentedMovies;
    }
}
