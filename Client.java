import java.util.ArrayList;

public class Client extends Account {

    private ArrayList<Pref> preferences;
    private Card cardInfo;
    private ArrayList<Meal> favMeals;

    public Client(String username, String password, String name, Address address, AccountType accountType) {
        super(username, password, name, address, accountType);
    }

    public void addPreferences(Pref preference) {
        preferences.add(preference);
    }

    public void addPayment(Card card) {
        cardInfo = card;
    }

    public void addFavMeal(Meal meal) {
        favMeals.add(meal);
    }

}
