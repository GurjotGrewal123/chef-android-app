import java.util.ArrayList;

public class Meal {

    private ArrayList<CuisineTypes> types;
    private String name;
    private double price;

    public Meal(String name, double price, ArrayList<CuisineTypes> types) {
        this.name = name;
        this.price = price;
        this.types = types;
    }

    public void changePrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CuisineTypes> getTypes() {
        return types;
    }

    public void addType(CuisineTypes type) {
        types.add(type);
    }

}
