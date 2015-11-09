package DAO;

import core.Review;

import java.sql.SQLException;
import java.util.List;

public interface ReviewDAO {
    public void addReview(Review review) throws SQLException;

    public void updateReview(Review review) throws SQLException;

    public Review getReviewById(Integer id) throws SQLException;

    public List getAllReviewByIdRestaurant(Integer id) throws SQLException;

    public List getAllReviewByIdRegisteredUser(Integer id) throws SQLException;

    public List getAllReview() throws SQLException;

    public void deleteReview(Review review) throws SQLException;
}
