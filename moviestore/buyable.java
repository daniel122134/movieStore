package moviestore;

public abstract class buyable {
    public String name;
    public String price;

    public buyable( String name, String price){
        this.name = name;
        this.price = price;

    }
    public String getPrice() {
        return price;
    }
    public String getName() {
        return name;
    }

    public String offer() {
        return FilmProduct.class.cast(this).offer();
    }
}
