package com.myemcu.atm;

/**
 * Created by Administrator on 2016/7/25.
 */
public class Transcation {

    // 设计属性
    String  account;    // 帐号
    String  date;       // 日期
    int     amount;     // 金额
    int     type;       // 类型


    // 通过对public class中的Transcation点Alt+Insert点Constructor生成
    public Transcation(String account, String date, int amount, int type) {
        this.account = account;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    // 通过对public class中的Transcation点Alt+Insert点Getter and Setter生成
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
