package com.healthcare.model;

public class Patient {

    private String firstName;
    private String middleName;
    private String familyName;
    private String gender;
    private String birthDay;
    private String birthMonth;
    private String birthYear;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String phoneNumber;
    private String relationshipType;
    private String relativeName;

    public Patient(String firstName, String middleName, String familyName, String gender,
                   String birthDay, String birthMonth, String birthYear, String address, String city, String country, String postalCode,
                   String phoneNumber, String relationshipType, String relativeName) {

        this.firstName = firstName;
        this.middleName = middleName;
        this.familyName = familyName;
        this.gender = gender;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.relationshipType = relationshipType;
        this.relativeName = relativeName;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public String getRelativeName() {
        return relativeName;
    }
}
