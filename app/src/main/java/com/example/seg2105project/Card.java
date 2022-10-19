package com.example.seg2105project;

public class Card {

    private String nameOnCard;
    private String cardNum;
    private String securityCode;
    private String expiryDate;

    /**
     *
     * @param nameOnCard
     * @param cardNum
     * @param securityCode
     * @param expiryDate
     *
     * This class is responsible for holding the information of a client's payment card
     * Precondition: securityCode is a 3 digit number, cardNum is a 16 digit string.
     * The Card class cannot be created with no parameters
     *
     */

    public Card(String nameOnCard, String cardNum, String securityCode, String expiryDate) {
        this.cardNum = cardNum;
        this.nameOnCard = nameOnCard;
        this.securityCode = securityCode;
        this.expiryDate = expiryDate;
    }

    public void setCardOnName(String nameOnCard){
        this.nameOnCard = nameOnCard;
    }

    public String getNameOnCard(){
        return nameOnCard;
    }

    public void setCardNum(String cardNum){
        this.cardNum = cardNum;
    }

    public String getCardNum(){
        return cardNum;
    }

    public void setSecurityCode(String securityCode){
        this.securityCode = securityCode;
    }

    public String getSecurityCode(){
        return securityCode;
    }

    public void setExpiryDate(String expiryDate){
        this.expiryDate = expiryDate;
    }

    public String getExpiryDate(){
        return expiryDate;
    }
}
