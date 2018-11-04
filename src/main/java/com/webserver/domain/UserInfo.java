package com.webserver.domain;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

public class UserInfo {

    @NotNull
    String password;

    @NotNull
    @Id
    String account;


    String accountType;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "password='" + password + '\'' +
                ", account='" + account + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
