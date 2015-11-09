package DAO;

import DAO.Impl.*;

public class Factory {

    private static RestaurantDAO restaurantDAO = null;
    private static RegisteredUserDAO registeredUserDAO = null;
    private static DishTagDAO dishTagDAO = null;
    private static ReservationDAO reservationDAO = null;
    private static ReviewDAO reviewDAO = null;
    private static Factory instance = null;


//    public static synchronized Factory getInstance(){
//        if (instance == null){
//            instance = new Factory();
//        }
//        return instance;
//    }

    public static RegisteredUserDAO getRegisteredUserDAO() {
        if (registeredUserDAO == null) {
            registeredUserDAO = new RegisteredUserDAOImpl();
        }
        return registeredUserDAO;
    }

    public static RestaurantDAO getRestaurantDAO() {
        if (restaurantDAO == null) {
            restaurantDAO = new RestaurantDAOImpl();
        }
        return restaurantDAO;
    }

    public static DishTagDAO getDishTagDAO() {
        if (dishTagDAO == null) {
            dishTagDAO = new DishTagDAOImpl();
        }
        return dishTagDAO;
    }

    public static ReservationDAO getReservationDAO() {
        if (reservationDAO == null) {
            reservationDAO = new ReservationDAOImpl();
        }
        return reservationDAO;
    }

    public static ReviewDAO getReviewDAO() {
        if (reviewDAO == null) {
            reviewDAO = new ReviewDAOImpl();
        }
        return reviewDAO;
    }
}
