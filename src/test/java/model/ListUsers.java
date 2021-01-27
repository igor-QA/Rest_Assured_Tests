package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListUsers {
    private List<User> data;

    public List<User> getData(){
        return data;
    }

    public void setData(List<User> data){
        this.data = data;
    }
}