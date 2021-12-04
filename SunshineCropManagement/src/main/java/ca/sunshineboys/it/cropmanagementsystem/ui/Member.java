package ca.sunshineboys.it.cropmanagementsystem.ui;

public class Member {

    private String Name;
    private Long Phone;
    private String Email;
    private String Comment;


    public Member() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getPh() {
        return Phone;
    }

    public void setPh(Long phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

}
