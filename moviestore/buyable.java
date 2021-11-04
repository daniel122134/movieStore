package moviestore;

public class buyable {
    public String name;
    public int ageRating;
    public int imdbScore;
    public String genre;
    public int length;
    public String price;

    public buyable(int ageRating, String name, String genre, int imdb, String price, int length){
        this.ageRating = ageRating;
        this.name = name;
        this.genre = genre;
        this.imdbScore = imdb;
        this.price = price;
        this.length = length;
    }
    public String offer() {
        return (this.name + "\n" + "\n" + "\n" + "\n" + "genre: " + this.genre + "\n" + "rating(from imdb)" + this.imdbScore + "\n" + "price" + this.price + "\n" + "\n" + "\n" + "\n" + "\n" + "\n" + "enter -9 to go baack or 1 to buy"
        );
    }

    public String getPrice() {
        return price;
    }
    public String getGenre() {
        return genre;
    }
    public int getLength() {
        return length;
    }
    public int getAgeRating() {
        return ageRating;
    }
    public String getName() {
        return name;
    }
}
