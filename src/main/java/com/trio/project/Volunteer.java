package com.trio.project;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "users")
public class Volunteer {

    @Id
    private String id;

    private String name;
    
    @Indexed(unique = true)
    private String email;

    private String password;

    private String note;

    public Volunteer() {
    }

    public Volunteer(String email, String name) {
        this.name = name;
        this.email = email;
        this.password = "pdw123";
    }

    @Override
    public String toString() {
        return String.format(
            "User[id=%s, email='%s', name='%s']", id, email, name);
    }

    public boolean pwdFormAlert() {

        return this.password.length() < 6;

    }

    public boolean pwdChecker(String password) {

        return this.password.equals(password);

    }

    public void emailLowerCase() {

        this.email = this.email.toLowerCase();

    }
    
}