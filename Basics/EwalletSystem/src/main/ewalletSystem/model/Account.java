package main.ewalletSystem.model;

public class Account {
    private String username;
    private String password;
    private double balance;
    private String phoneNumber;
    private String address;
    private float age;
    private boolean isAdmin;
    private boolean isActive;
    private boolean isDeleted;


    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account() {
    }

    public Account(String username, String password, boolean isAdmin, boolean isActive , boolean isDeleted) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
    }

    public Account(String username, String password, String phoneNumber, String address, float age, boolean isAdmin, boolean isActive) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.balance=0;
        this.address = address;
        this.age = age;
        this.isAdmin = isAdmin;
        this.isActive = isActive;
    }

    public Account(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", isAdmin=" + isAdmin +
                ", isActive=" + isActive +
                '}';
    }
}
