import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rentalstore.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RentalStoreTest {
    private RentalStore rentalStore;
    private Movie mockMovie;

    @BeforeEach
    public void setUp(){
        mockMovie= mock(Movie.class);
        rentalStore = new RentalStore();
    }

    @Test
    void testAddMovie(){
        when(mockMovie.getTitle()).thenReturn("Among Us");
        rentalStore.addMovie(mockMovie);
        Movie foundMovie = rentalStore.searchMovieByTitle("Among Us");
        assertEquals(mockMovie, foundMovie);
    }

    @Test
    void removeMovie(){
        when(mockMovie.getTitle()).thenReturn("Fortnite");
        rentalStore.addMovie(mockMovie);
        rentalStore.removeMovie("Fortnite");
        Movie retrievedMovie = rentalStore.searchMovieByTitle("Fortnite");
        assertNull(retrievedMovie);
    }

    @Test
    void testCheckAvailability(){
        when(mockMovie.getTitle()).thenReturn("Monkey Island");
        when(mockMovie.isAvailable()).thenReturn(true);
        rentalStore.addMovie(mockMovie);
        assertTrue(rentalStore.checkAvailability("Monkey Island"));
    }

    @Test
    void testUpdateAvailability(){
        when(mockMovie.getTitle()).thenReturn("Baldurs Gate: The lore");
        rentalStore.addMovie(mockMovie);
        rentalStore.updateAvailability("Baldurs Gate: The lore",false);
        verify(mockMovie, times(1)).setAvailability(false);
    }

    @Test
    void testGenerateGenreStats(){
        Movie movie1 = mock(Movie.class);
        Movie movie = mock(Movie.class);
        when(movie.getTitle()).thenReturn("Depression");
        when(movie.getGenre()).thenReturn("Sad");
        when(movie1.getTitle()).thenReturn("Pokemon: Ultra Necrozma");
        when(movie1.getGenre()).thenReturn("Anime");
        when(mockMovie.getTitle()).thenReturn("Laezel & Manuty");
        when(mockMovie.getGenre()).thenReturn("Rom-com");
        rentalStore.addMovie(movie);
        rentalStore.addMovie(movie1);
        rentalStore.addMovie(mockMovie);

        Map<String, Integer> stats = rentalStore.generateGenreStatistics();
        assertEquals(1, stats.get("Sad"));
        assertEquals(1, stats.get("Anime"));
        assertEquals(1, stats.get("Rom-com"));
    }
}
