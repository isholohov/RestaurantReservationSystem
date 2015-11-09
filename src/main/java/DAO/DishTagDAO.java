package DAO;

import core.DishTag;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface DishTagDAO {
    public void addDishTag(DishTag dishTag) throws SQLException;

    public void updateDishTag(DishTag dishTag) throws SQLException;

    public List getAllDishTagsByIdRestaurant(Integer id) throws SQLException;

    public List getAllDishTags() throws SQLException;

    public Set getSetDishTags() throws SQLException;

    public DishTag deleteDishTag(DishTag dishTag) throws SQLException;
}
