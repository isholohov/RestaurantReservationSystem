package core;

import javax.persistence.*;

@Entity
public class DishTag {
    private int idDishTag;
    private int idRestaurant;
    private String dishTag;

    public DishTag() {
    }

    public DishTag(int idRestaurant, String dishTag) {

        this.idRestaurant = idRestaurant;
        this.dishTag = dishTag;
    }

    @Id
    @Column(name = "idDishTag")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdDishTag() {
        return idDishTag;
    }

    public void setIdDishTag(int idDishTag) {
        this.idDishTag = idDishTag;
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
    @Column(name = "dishTag")
    public String getDishTag() {
        return dishTag;
    }

    public void setDishTag(String dishTag) {
        this.dishTag = dishTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DishTag dishTag = (DishTag) o;

        if (idDishTag != dishTag.idDishTag) return false;
        if (idRestaurant != dishTag.idRestaurant) return false;
        if (this.dishTag != null ? !this.dishTag.equals(dishTag.dishTag) : dishTag.dishTag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDishTag;
        result = 31 * result + idRestaurant;
        result = 31 * result + (dishTag != null ? dishTag.hashCode() : 0);
        return result;
    }
}
