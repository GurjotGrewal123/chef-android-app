public class Card {

    private String cardOnName;
    private String cardNum;
    private String securityCode;

    /**
     *
     * @param cardOnName
     * @param cardNum
     * @param securityCode
     *
     * This class is responsible for holding the information of a client's payment card
     * Precondition: securityCode is a 3 digit number, cardNum is a 16 digit string.
     * The Card class cannot be created with no parameters
     *
     */

    public Card(String cardOnName, String cardNum, String securityCode) {
        this.cardNum = cardNum;
        this.cardOnName = cardOnName;
        this.securityCode = securityCode;
    }
}
