package core;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Review {
    private int idReview;
    private int idRestaurant;
    private int idRegisteredUser;
    private String comment;
    private int rating;
    private Date date;

    @Id
    @Column(name = "idReview")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdReview() {
        return idReview;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
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
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "rating")
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (idReview != review.idReview) return false;
        if (idRestaurant != review.idRestaurant) return false;
        if (idRegisteredUser != review.idRegisteredUser) return false;
        if (rating != review.rating) return false;
        if (comment != null ? !comment.equals(review.comment) : review.comment != null) return false;
        if (date != null ? !date.equals(review.date) : review.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idReview;
        result = 31 * result + idRestaurant;
        result = 31 * result + idRegisteredUser;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + rating;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public Review() {
    }

    public Review(int idRestaurant, int idRegisteredUser, String comment, int rating, Date date) {
        this.idRestaurant = idRestaurant;
        this.idRegisteredUser = idRegisteredUser;
        this.comment = comment;
        this.rating = rating;
        this.date = date;
    }
}
