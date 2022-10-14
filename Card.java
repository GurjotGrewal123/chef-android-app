public class Card {

    private String cardOnName;
    private String cardNum;
    private String securityCode;

    public Card(String cardOnName, String cardNum, String securityCode) {
        this.cardNum = cardNum;
        this.cardOnName = cardOnName;
        this.securityCode = securityCode;
    }
}
