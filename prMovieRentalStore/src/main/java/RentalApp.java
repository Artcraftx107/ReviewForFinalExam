import rentalstore.*;

import java.time.LocalDate;
import java.util.*;
public class RentalApp {
    private static RentalStore rentalStore = new RentalStore();
    private static Map<Integer, Customer> customers = new HashMap<>();
    private static final String ASK_FOR_ID = "Enter your membership ID: ";
    private static final String ID_NOT_FOUND = "No customer found with this membership ID";

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while(!exit){
            System.out.println("\nWelcome to the Rental Store!");
            System.out.println("1. Register as a new customer");
            System.out.println("2. Rent a movie");
            System.out.println("3. Return a movie");
            System.out.println("4. View your rented movies");
            System.out.println("5. Admin: Add a movie");
            System.out.println("6. Admin: Remove a movie");
            System.out.println("7. Admin: View all movies");
            System.out.println("8. Admin: Generate genre statistics");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    registerCustomer(scanner);
                    break;
                case 2:
                    rentMovie(scanner);
                    break;
                case 3:
                    returnMovie(scanner);
                    break;
                case 4:
                    viewRentedMovies(scanner);
                    break;
                case 5:
                    addMovie(scanner);
                    break;
                case 6:
                    removeMovie(scanner);
                    break;
                case 7:
                    rentalStore.displayAllMovies();
                    break;
                case 8:
                    generateGenreStatistics();
                    break;
                case 9:
                    exit=true;
                    break;
                default:
                    System.err.println("Invalid option. Please try again");
            }
        }
        scanner.close();
    }

    private static void generateGenreStatistics() {
        Map<String, Integer> stats = rentalStore.generateGenreStatistics();
        System.out.println("Genre statistics: ");
        for(Map.Entry<String, Integer> entry : stats.entrySet()){
            System.out.println(entry.getKey()+": "+entry.getValue());
        }
    }

    private static void removeMovie(Scanner scanner) {
        System.out.println("Enter the title of the movie to remove: ");
        String title = scanner.nextLine();

        try {
            rentalStore.removeMovie(title);
            System.out.println("Movie removed sucessfully");
        }catch (NoSuchElementException e){
            System.err.println(e.getMessage());
        }
    }

    private static void addMovie(Scanner scanner) {
        System.out.println("Enter the title: ");
        String title = scanner.nextLine();
        System.out.println("Enter the director: ");
        String director = scanner.nextLine();
        System.out.println("Enter the release date in the following format " +
                "(day/month/year) without the brackets: ");
        String[] aux = scanner.nextLine().split("/");
        if(aux.length<3){
            throw new IllegalArgumentException("There was an error with the entered date");
        }
        int day = Integer.parseInt(aux[0]);
        int month = Integer.parseInt(aux[1]);
        int year = Integer.parseInt(aux[2]);
        LocalDate releaseDate = LocalDate.of(year, month, day);
        System.out.println("Enter the genre: ");
        String genre = scanner.nextLine();
        System.out.println("Enter the rating (ALLOWED PEGIS; 3, 7, 12, 16, 18): ");
        String rating = scanner.nextLine();

        Movie movie = new Movie(title, director, releaseDate, rating, genre);
        rentalStore.addMovie(movie);
        System.out.println("Movie added successfully");
    }

    private static void viewRentedMovies(Scanner scanner) {
        System.out.println(ASK_FOR_ID);
        int membershipId = scanner.nextInt();
        Customer customer = customers.get(membershipId);
        if(customer==null){
            System.err.println(ID_NOT_FOUND);
            return;
        }

        System.out.println("Your rented movies: \n");
        for(Movie movie:customer.getRentedMovies()){
            System.out.println(movie+"\n");
        }

    }

    private static void returnMovie(Scanner scanner) {
        System.out.println(ASK_FOR_ID);
        int id = Integer.parseInt(scanner.nextLine());
        Customer customer = customers.get(id);
        if(customer==null){
            System.err.println(ID_NOT_FOUND);
            return;
        }

        System.out.println("Enter the title of the movie you want to return: ");
        String title = scanner.nextLine();

        try{
            if(!rentalStore.checkAvailability(title)){
                customer.returnMovie(rentalStore.searchMovieByTitle(title));
                rentalStore.updateAvailability(title, true);
                System.out.println("Movie returned successfully");
            }
        }catch (NoSuchElementException e){
            System.err.println(e.getMessage());
        }
    }

    private static void rentMovie(Scanner scanner) {
        System.out.println(ASK_FOR_ID);
        int id = Integer.parseInt(scanner.nextLine());

        Customer customer = customers.get(id);
        if(customer==null){
            System.err.println(ID_NOT_FOUND);
            return;
        }else{
            System.out.println("Enter the title of the movie you want to rent: ");
            String title = scanner.nextLine();

            try{
                if(rentalStore.checkAvailability(title)){
                    Movie movie = rentalStore.searchMovieByTitle(title);
                    customer.rentMovie(movie);
                    rentalStore.updateAvailability(title, false);
                    System.out.println("Movie rented successfully");
                }else{
                    System.err.println("The movie '"+title+"' is not available");
                }
            }catch (NoSuchElementException e){
                System.err.println(e.getMessage());
            }
        }
    }

    private static void registerCustomer(Scanner scanner) {
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println(ASK_FOR_ID);
        int id = scanner.nextInt();

        if(customers.containsKey(id)){
            System.err.println("Customer with this membership ID already exists");
        }else{
            Customer customer = new Customer(name, id);
            customers.put(id, customer);
            System.out.println("Customer registered successfully");
        }
    }
}
