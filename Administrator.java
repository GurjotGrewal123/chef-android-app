public class Administrator extends Account {

    /**
     *
     * @param username
     * @param password
     * @param name
     * @param address
     * @param accountType
     *
     * This class extends our abstract Account class, and is the class responsible for allowing users
     * of the Admin instance to moderate our application
     *
     */

    public Administrator(String username, String password, String name, Address address, AccountType accountType) {
        super(username, password, name, address, accountType);
    }

    public void deleteAccount() {
        //WORK IN PROGRESS
    }

    public void reviewComplaint() {
        //WORK IN PROGRESS
    }

    public void createSuspension() {
        //WORK IN PROGRESS
    }

    public void approveCook() {
        //WORK IN PROGRESS
    }

    public void banAccount() {
        //WORK IN PROGRESS
    }

}
