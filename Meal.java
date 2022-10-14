public class Meal {

    private Pref type1;
    private Pref type2;
    private Pref type3;
    private String name;
    private double price;

    public Meal(String name, double price, Pref type1) {
        this.name = name;
        this.price = price;
        this.type1 = type1;
    }

    public Meal(String name, double price, Pref type1, Pref type2) {
        this.name = name;
        this.price = price;
        this.type1 = type1;
        this.type2 = type2;
    }

    public Meal(String name, double price, Pref type1, Pref type2, Pref type3) {
        this.name = name;
        this.price = price;
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
    }
}
