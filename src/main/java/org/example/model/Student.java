package org.example.model;

public class Student {
    int id;
    String name,email;
    Teacher t1;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setT1(Teacher t1) {
        this.t1 = t1;
    }

    public Teacher getT1() {
        return t1;
    }
}
