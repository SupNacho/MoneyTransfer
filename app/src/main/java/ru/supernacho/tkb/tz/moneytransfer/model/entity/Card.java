package ru.supernacho.tkb.tz.moneytransfer.model.entity;

public class Card {
    private String number;
    private String expire;
    private String bankName;

    public Card(String number) {
        this.number = number;
    }

    public Card(String number, String expire, String bankName) {
        this(number);
        this.expire = expire;
        this.bankName = bankName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
