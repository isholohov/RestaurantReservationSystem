package DAO.Impl;

import DAO.DishTagDAO;
import DAO.Factory;
import core.DishTag;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by GlAz on 18.10.2015.
 */
public class DishTagDAOImplTest {
    //
//    @Test
//    public void testAddDishTag() throws Exception {
//        dishTagDAO.addDishTag(dishTag);
//    }
//
//    @Test
//    public void testUpdateDishTag() throws Exception {
//        dishTag.setDishTag(dishes[1]);
//        dishTagDAO.updateDishTag(dishTag);
//    }

    //
//    private DishTag dishTag;
//
    private ArrayList<DishTag> dishTags = new ArrayList<DishTag>();
    private DishTagDAO dishTagDAO;
    private String[] dishes;

    @Before
    public void before() throws Exception {
        dishTagDAO = Factory.getDishTagDAO();
        dishes = new String[]{"Russian", "Italian", "Chinese", "Finnish", "French"};
    }

    @Test
    public void testDishTag() throws Exception {

        DishTag dishTag;

        for (int i = 1; i <= 20; i++) {
            for (String dish : dishes) {
                dishTag = new DishTag(i, dish);
                dishTags.add(dishTag);

                try {
                    dishTagDAO.addDishTag(dishTag);
                } catch (SQLException e) {
                    System.out.println("SQLException: " + e.getMessage());
                }
            }
        }

        dishTag = new DishTag(11, "Korean");
        dishTagDAO.addDishTag(dishTag);

        List dishTagsFromDB = dishTagDAO.getAllDishTags();

        assertTrue(dishTagsFromDB.contains(dishTag));



        for (DishTag d : dishTags) {
            assertTrue(dishTagsFromDB.contains(d));
        }


        int restaurantId = 3;

        dishTagsFromDB = dishTagDAO.getAllDishTagsByIdRestaurant(restaurantId);
        for (DishTag d : dishTags) {
            if (d.getIdRestaurant() == restaurantId) {
                assertTrue(dishTagsFromDB.contains(d));
            }
        }

        for (DishTag d : dishTags) {
            dishTagDAO.deleteDishTag(d);
        }

        dishTagDAO.deleteDishTag(dishTag);
        assertFalse(dishTagDAO.getAllDishTags().contains(dishTag));

    }
}