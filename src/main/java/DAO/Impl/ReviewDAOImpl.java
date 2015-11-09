package DAO.Impl;

import DAO.ReviewDAO;
import core.Review;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ilia_Sholokhov on 10/10/15.
 */
public class ReviewDAOImpl implements ReviewDAO {

    public void addReview(@NotNull Review review) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(review);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateReview(@NotNull Review review) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(review);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Review getReviewById(@NotNull Integer id) throws SQLException {
        Session session = null;
        Review review = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            review = (Review) session.load(Review.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return review;
    }

    public List getAllReviewByIdRestaurant(@NotNull Integer id) throws SQLException {
        List allReview = getAllReview();
        List<Review> reviewsForRestaurant = new ArrayList<Review>();
        for (Object objReview : allReview) {
            Review review = (Review) objReview;
            if (review.getIdRestaurant() == id) {
                reviewsForRestaurant.add(review);
            }
        }
        return reviewsForRestaurant;
    }

    public List getAllReviewByIdRegisteredUser(@NotNull Integer id) throws SQLException {
        List allReview = getAllReview();
        List<Review> reviewsForRegisteredUser = new ArrayList<Review>();
        for (Object objReview : allReview) {
            Review review = (Review) objReview;
            if (review.getIdRegisteredUser() == id) {
                reviewsForRegisteredUser.add(review);
            }
        }
        return reviewsForRegisteredUser;
    }

    public List getAllReview() throws SQLException {
        Session session = null;
        List reviews = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            reviews = session.createCriteria(Review.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return reviews;
    }

    public void deleteReview(@NotNull Review review) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(review);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
