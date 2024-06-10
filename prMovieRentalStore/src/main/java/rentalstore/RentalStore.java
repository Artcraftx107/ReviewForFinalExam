package rentalstore;

import java.util.*;

public class RentalStore {
    private Map<String, Movie> movieCollection;

    public RentalStore(){
        this.movieCollection=new HashMap<>();
    }

    public void addMovie(Movie movie){
        movieCollection.put(movie.getTitle(), movie);
    }

    public void removeMovie(String title){
        movieCollection.remove(title);
    }

    public void displayAllMovies(){
        if(movieCollection.isEmpty()){
            System.out.println("No movies in the rental store");
        }else{
            System.out.println("Movies in the rental store: "+"\n");
            for(Movie movie : movieCollection.values()){
                System.out.println(movie);
            }
        }

    }

    public Movie searchMovieByTitle(String title){
        return movieCollection.get(title);
    }

    public List<Movie> searchMoviesByDirector(String director){
        List<Movie> moviesByDirector = new ArrayList<>();
        for(Movie movie : movieCollection.values()){
            if(movie.getDirector().equalsIgnoreCase(director)){
                moviesByDirector.add(movie);
            }
        }
        if(moviesByDirector.isEmpty()){
            throw new NoSuchElementException("There is no movie with the director "+director);
        }else{
            return moviesByDirector;
        }
    }

    public List<Movie> searchMoviesByGenre(String genre){
        List<Movie> moviesByGenre = new ArrayList<>();
        for(Movie movie : movieCollection.values()){
            if(movie.getGenre().equalsIgnoreCase(genre)){
                moviesByGenre.add(movie);
            }
        }
        if(moviesByGenre.isEmpty()){
            throw new NoSuchElementException("There are no movies with the genre: "+genre);
        }else{
            return moviesByGenre;
        }
    }

    public boolean checkAvailability(String title){
        Movie movie = movieCollection.get(title);
        if (movie!=null){
            return movie.isAvailable();
        }else{
            throw new NoSuchElementException("There is no movie with the title: "+title);
        }
    }

    public void updateAvailability(String title, boolean update){
        Movie movie = movieCollection.get(title);
        if(movie!=null){
            movie.setAvailability(update);
        }else{
            throw new NoSuchElementException("There is no movie with the title: "+title);
        }
    }

    public Map<String, Integer> generateGenreStatistics(){
        Map<String, Integer> genreStats = new TreeMap<>();
        for(Movie movie : movieCollection.values()){
            String genre = movie.getGenre();
            genreStats.put(genre, genreStats.getOrDefault(genre, 0)+1);
        }
        return genreStats;
    }
}