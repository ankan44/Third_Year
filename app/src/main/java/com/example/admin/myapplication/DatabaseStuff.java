package com.example.admin.myapplication;

/**
 * Created by ankan on 30/03/18.
 */
public class DatabaseStuff {
    private String sex;
    private String name;
    private String blood_group;
    private String email;
    private String Emergency_Contact_number;
    private String Contact_number_user;

    public DatabaseStuff(String name, String email, String sex,String blood_group,String Emergency_Contact_number, String Contact_number_user) {
        this.email = email;
        this.name = name;
        this.sex = sex;
        this.blood_group=blood_group;
        this.Emergency_Contact_number = Emergency_Contact_number;
        this.Contact_number_user = Contact_number_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String name) {
        this.sex = name;
    }

    public String getBloodGroup()
    {
        return blood_group;
    }
    public void setBloodGroup(String blood_group)
    {
        this.blood_group = blood_group;
    }
    public String getEmergency_Contact_number() {
        return Emergency_Contact_number;
    }

    public void setEmergency_Contact_number(String Emergency_Contact_number) {
        this.Emergency_Contact_number = Emergency_Contact_number;
    }
    public String getContact_number_user() {
        return Contact_number_user;
    }
    public void setContact_number_user(String name) {
        this.Contact_number_user = name;
    }
}



