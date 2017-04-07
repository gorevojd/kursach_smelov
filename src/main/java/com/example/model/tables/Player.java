package com.example.model.tables;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * Created by GOREVOJD on 03.04.2017.
 */


//???????????????????

//@Document(collection="players")
public class Player {
    @Id
    private String login;
    private String password;

    private int lastLevel;
    private int coinCount;
    private int redDiamondCount;
    private int greenDiamondCount;
    private int blueDiamondCount;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getLastLevel() {
        return lastLevel;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public int getRedDiamondCount() {
        return redDiamondCount;
    }

    public int getGreenDiamondCount() {
        return greenDiamondCount;
    }

    public int getBlueDiamondCount() {
        return blueDiamondCount;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLastLevel(int lastLevel) {
        this.lastLevel = lastLevel;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }

    public void setRedDiamondCount(int redDiamondCount) {
        this.redDiamondCount = redDiamondCount;
    }

    public void setGreenDiamondCount(int greenDiamondCount) {
        this.greenDiamondCount = greenDiamondCount;
    }

    public void setBlueDiamondCount(int blueDiamondCount) {
        this.blueDiamondCount = blueDiamondCount;
    }

    public Player(){

    }

    public Player(
            String login,
            String password,
            Integer lastLevel,
            Integer coinCount,
            Integer redDiamondCount,
            Integer greenDiamondCount,
            Integer blueDiamondCount)
    {
        this.login = login;
        this.password = password;
        this.lastLevel = lastLevel;
        this.coinCount = coinCount;
        this.redDiamondCount = redDiamondCount;
        this.greenDiamondCount = greenDiamondCount;
        this.blueDiamondCount = blueDiamondCount;
    }

    public Player(
            String login,
            String password)
    {
        this.login = login;
        this.password = password;
        this.lastLevel = 1;
        this.coinCount = 0;
        this.redDiamondCount = 0;
        this.greenDiamondCount = 0;
        this.blueDiamondCount = 0;
    }

    @Override public String toString(){
        return "Player{" +
                "\"login\":\"" + login + "\"," +
                "\"password\":\"" + password + "\"," +
                "\"lastLevel\":\"" + String.valueOf(lastLevel) + "\","+
                "\"coinCount\":\"" + String.valueOf(coinCount) + "\","+
                "\"redDiamondCount\":\"" + String.valueOf(redDiamondCount) + "\","+
                "\"greenDiamondCount\":\"" + String.valueOf(greenDiamondCount) + "\","+
                "\"blueDiamondCount\":\"" + String.valueOf(blueDiamondCount) + "\",";
    }
}
