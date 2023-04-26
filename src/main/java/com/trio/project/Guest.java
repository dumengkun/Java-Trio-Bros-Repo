package com.trio.project;

import lombok.Data;

@Data
public class Guest {

    private String name;
    private String email;
    private String password;
    private String newEmail;
    private String newPassword;
    private String note;

    public boolean pwdFormAlert() {

        return this.newPassword.length() < 6;

    }

}
