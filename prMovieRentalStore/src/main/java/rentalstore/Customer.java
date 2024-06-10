package rentalstore;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private int membershipId;
    private List<Movie> rentedMovies;

    public Customer(String name, int membershipId){
        if(name.isBlank()){
            throw new IllegalArgumentException("The name of the customer cannot be blank");
        }else if (membershipId<0){
            throw new IllegalArgumentException("The membershipId cannot be negative");
        }else{
            this.name=name;
            this.membershipId=membershipId;
            this.rentedMovies=new ArrayList<>();
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

    public void rentMovie(Movie movie) {
        rentedMovies.add(movie);
    }

    public void returnMovie(Movie movie) {
        rentedMovies.remove(movie);
    }
}
