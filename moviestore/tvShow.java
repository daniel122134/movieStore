package moviestore;

public class tvShow extends buyable {
    private boolean isAnime;
    private int numOfepisodes;
    ;


    public tvShow(int ageRating, String name, String genre, int imdb, String price, int length, boolean anime, int eps) {
        super(ageRating, name, genre, imdb, price, length);
        this.isAnime = anime;
        this.numOfepisodes = eps;

    }
}
