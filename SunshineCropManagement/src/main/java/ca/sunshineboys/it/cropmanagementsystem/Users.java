package ca.sunshineboys.it.cropmanagementsystem;

public class Users {


    public Users(String name, String phone, String email, String comment, Float rating) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.comment = comment;
        this.rating = rating;
    }

    public Users(){}
    private String name;
    private String phone;
    private String email;
    private String comment;
    private float rating;


    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}