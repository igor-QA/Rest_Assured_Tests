package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Композитная модель
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    private int id;
    private String email;
    private String lastname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}