package ru.supernacho.tkb.tz.moneytransfer.model.entity;

public class User {
    private String token = "";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getToken() != null ? getToken().equals(user.getToken()) : user.getToken() == null;
    }

    @Override
    public int hashCode() {
        return getToken() != null ? getToken().hashCode() : 0;
    }
}
