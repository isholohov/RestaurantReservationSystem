package core;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Entity
public class Reservation {
    private int idReservation;
    private int idRestaurant;
    private int idRegisteredUser;
    private int persons;
    private Date date;
    private Time time;
    private byte isAccepted;

    public Reservation() {
    }

    public Reservation(int idRestaurant, int idRegisteredUser, int persons, Date date, Time time, byte isAccepted) {
        this.idRestaurant = idRestaurant;
        this.idRegisteredUser = idRegisteredUser;
        this.persons = persons;
        this.date = date;
        this.time = time;
        this.isAccepted = isAccepted;
    }

    @Id
    @Column(name = "idReservation")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    @Basic
    @Column(name = "idRestaurant")
    public int getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(int idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    @Basic
    @Column(name = "idRegisteredUser")
    public int getIdRegisteredUser() {
        return idRegisteredUser;
    }

    public void setIdRegisteredUser(int idRegisteredUser) {
        this.idRegisteredUser = idRegisteredUser;
    }

    @Basic
    @Column(name = "persons")
    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    @Basic
    @Column(name = "time")
    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "isAccepted")
    public byte getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(byte isAccepted) {
        this.isAccepted = isAccepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (idReservation != that.idReservation) return false;
        if (persons != that.persons) return false;
        if (isAccepted != that.isAccepted) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReservation;
        result = 31 * result + persons;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (int) isAccepted;
        return result;
    }
}
