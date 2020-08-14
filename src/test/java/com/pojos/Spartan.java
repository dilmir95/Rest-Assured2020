package com.pojos;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/*
this class represents spartan pojo
Example:
        {
            "id": 134,              ->        private int id;
            "name": "Kacey",        ->        private String name;
            "gender": "Male",       ->       private String gender;
            "phone": 414305467464   ->        private long phone;
        }
 */
public class Spartan {
    private int id;
    private String name;
    private String gender;
    private long phone;

//    @SerializedName("phone")
//    private long phoneNumber;  (if original variable name cannot be used, we have to specify with annotation


    public Spartan(String name,String gender,long phone){
        this.name = name;
        this.gender = gender;
        this.phone = phone;

    }
    public Spartan(String name,String gender,long phone,int id){
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.id= id;
    }
    public Spartan(){

    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Spartan)) return false;
        Spartan spartan = (Spartan) o;
        return id == spartan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, phone);
    }
}
