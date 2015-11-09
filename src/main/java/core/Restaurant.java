package core;

import javax.persistence.*;

@Entity
public class Restaurant {
    private int idRestaurant;
    private String login;
    private String password;
    private String name;
    private String address;
    private String shortDescription;
    private String fullDescription;
    private String phoneNumber;
    private double averageBill;
    private String openingHours;
    private String closestStation;
    private double rating;

    public Restaurant() {
    }

    public Restaurant(String login, String password, String name, String address, String shortDescription, String fullDescription, String phoneNumber, double averageBill, String openingHours, String closestStation, double rating) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.address = address;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.phoneNumber = phoneNumber;
        this.averageBill = averageBill;
        this.openingHours = openingHours;
        this.closestStation = closestStation;
        this.rating = rating;
    }

    @Id
    @Column(name = "idRestaurant")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    @Basic
    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "shortDescription")
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Basic
    @Column(name = "fullDescription")
    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    @Basic
    @Column(name = "phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "averageBill")
    public double getAverageBill() {
        return averageBill;
    }

    public void setAverageBill(double averageBill) {
        this.averageBill = averageBill;
    }

    @Basic
    @Column(name = "openingHours")
    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    @Basic
    @Column(name = "closestStation")
    public String getClosestStation() {
        return closestStation;
    }

    public void setClosestStation(String closestStation) {
        this.closestStation = closestStation;
    }

    @Basic
    @Column(name = "rating")
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Restaurant that = (Restaurant) o;

        if (idRestaurant != that.idRestaurant) return false;
        if (Double.compare(that.averageBill, averageBill) != 0) return false;
        if (Double.compare(that.rating, rating) != 0) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (shortDescription != null ? !shortDescription.equals(that.shortDescription) : that.shortDescription != null)
            return false;
        if (fullDescription != null ? !fullDescription.equals(that.fullDescription) : that.fullDescription != null)
            return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (openingHours != null ? !openingHours.equals(that.openingHours) : that.openingHours != null) return false;
        if (closestStation != null ? !closestStation.equals(that.closestStation) : that.closestStation != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idRestaurant;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (fullDescription != null ? fullDescription.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        temp = Double.doubleToLongBits(averageBill);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (openingHours != null ? openingHours.hashCode() : 0);
        result = 31 * result + (closestStation != null ? closestStation.hashCode() : 0);
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
