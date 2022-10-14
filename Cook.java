import java.util.ArrayList;

public class Cook extends Account {

    private ArrayList<String> complaintsAgainst;

    public Cook(String username, String password, String name, Address address, AccountType accountType) {
        super(username, password, name, address, accountType);
    }
}
