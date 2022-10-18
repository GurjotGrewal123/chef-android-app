public abstract class Account {
    private String username;
    private String password;
    private String name;
    private Address address;
    private AccountType accountType;

    /**
     *
     * @param username holds the account's username
     * @param password holds the account's password
     * @param name holds the personal name associated with its account instance
     * @param address holds the address associated with its account instance
     * @param accountType holds the account type of said account instance
     *
     * This class is an abstract class which the classes Client, Administrator and Cook are based off
     *
     */

    protected Account(String username, String password, String name, Address address,
            AccountType accountType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getType() {
        return accountType;
    }

    public void setType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Address getAddress() {
        return address;
    }

    public void setUsername(Address address) {
        this.address = address;
    }

}