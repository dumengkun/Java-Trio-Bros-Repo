package com.trio.project;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "developers")
public class Administrator {

    @Id
    private String id;

    private String name;
    
    @Indexed(unique = true)
    private String email;

    private String password;

    private String note;

    public Administrator() {
    }

    public Administrator(String email, String name) {
        this.name = name;
        this.email = email;
        this.password = "pdw123";
    }

    @Override
    public String toString() {
        return String.format(
            "Admin[id=%s, email='%s', name='%s']", id, email, name);
    }

    public boolean pwdChecker() {

        return password.length() > 6;

    }

    public void emailLowerCase() {

        this.email.toLowerCase();

    }
    
}