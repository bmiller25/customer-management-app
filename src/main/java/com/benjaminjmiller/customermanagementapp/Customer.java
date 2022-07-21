package com.benjaminjmiller.customermanagementapp;

/**
 * The Customer entity class represents the customer table.
 */
public final class Customer {

    Long id;
    String firstName;
    String lastName;
    String gender;
    String address;
    String phoneNumber;
    String nameOnCreditCard;
    String creditCardNumber;
    String expirationDate;
    String csc;
    Boolean rewardsMember;
    Long rewardsPoints;

    public Customer() {

    }

    public Customer(String firstName, String lastName, String gender, String address, String phoneNumber, String nameOnCreditCard, String creditCardNumber, String expirationDate, String csc, Boolean rewardsMember, Long rewardsPoints) {
        setFirstName(firstName);
        setLastName(lastName);
        setGender(gender);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setNameOnCreditCard(nameOnCreditCard);
        setCreditCardNumber(creditCardNumber);
        setExpirationDate(expirationDate);
        setCsc(csc);
        setRewardsMember(rewardsMember);
        setRewardsPoints(rewardsPoints);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null && firstName.equals("")) {
            firstName = null;
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null && lastName.equals("")) {
            lastName = null;
        }
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender != null && gender.equals("")) {
            gender = null;
        }
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null && address.equals("")) {
            address = null;
        }
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && phoneNumber.equals("")) {
            phoneNumber = null;
        }
        this.phoneNumber = phoneNumber;
    }

    public String getNameOnCreditCard() {
        return nameOnCreditCard;
    }

    public void setNameOnCreditCard(String nameOnCreditCard) {
        if (nameOnCreditCard != null && nameOnCreditCard.equals("")) {
            nameOnCreditCard = null;
        }
        this.nameOnCreditCard = nameOnCreditCard;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        if (creditCardNumber != null && creditCardNumber.equals("")) {
            creditCardNumber = null;
        }
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        if (expirationDate != null && expirationDate.equals("")) {
            expirationDate = null;
        }
        this.expirationDate = expirationDate;
    }

    public String getCsc() {
        return csc;
    }

    public void setCsc(String csc) {
        if (csc != null && csc.equals("")) {
            csc = null;
        }
        this.csc = csc;
    }

    public Boolean isRewardsMember() {
        return rewardsMember;
    }

    public void setRewardsMember(Boolean rewardsMember) {
        this.rewardsMember = rewardsMember;
    }

    public Long getRewardsPoints() {
        return rewardsPoints;
    }

    public void setRewardsPoints(Long rewardsPoints) {
        this.rewardsPoints = rewardsPoints;
    }

}
