package rentalstore;

import java.time.LocalDate;

public class Movie {
    private String title;
    private String director;
    private LocalDate releaseDate;
    private String genre;
    private enum PEGIRating{
        PEGI_3, PEGI_7, PEGI_12, PEGI_16, PEGI_18
    }
    private PEGIRating rating;
    private boolean availability;

    public Movie(String t, String d, LocalDate rd, PEGIRating r, String g){
        if(t.isBlank()||d.isBlank()||g.isBlank()){
            throw new IllegalArgumentException("Neither the title, the director's name or the genre can be null or blank");
        }else{
            if(rd.isAfter(LocalDate.now())){
                this.availability=false;
            }else{
                this.availability=true;
            }
            this.title=t;
            this.director=d;
            this.releaseDate=rd;

            this.genre=g;
        }
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public PEGIRating getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRating(PEGIRating rating) {
        this.rating = rating;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Movie: ").append(title+", ");
        sb.append("Director: ").append(director+", ");
        sb.append("Release Date: ").append(releaseDate.toString()+", ");
        sb.append("Genre: ").append(genre+", ");
        sb.append("PEGI Rating: ").append(rating.toString()+", ");
        sb.append("Availability: ");
        if(availability){
            sb.append("AVAILABLE");
        }else{
            sb.append("UNAVAILABLE");
        }

        return sb.toString();
    }
}