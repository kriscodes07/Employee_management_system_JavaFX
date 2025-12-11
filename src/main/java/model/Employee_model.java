package model;

public class Employee_model {

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String name;
    String department;
    String mail;
    String emcode;


    public Employee_model(String name, String department, String mail, String emcode) {
        this.name = name;
        this.department = department;
        this.mail = mail;
        this.emcode = emcode;
    }

    public Employee_model() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getEmcode() {
        return emcode;
    }

    public void setEmcode(String emcode) {
        this.emcode = emcode;
    }
}
