package dataLayer;

import java.io.Serializable;

public class Utilizator implements Serializable {

    private String username;
    private String password;
    private int tip;

    public Utilizator(String username, String password, int tip){
        this.username = username;
        this.password = password;
        this.tip = tip;
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

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }
}
