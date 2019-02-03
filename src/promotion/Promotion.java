package promotion;

import dish.Dish;
import order.Order;

import java.util.HashSet;

public abstract class Promotion {
    private String promotionType = "";
    private HashSet<Dish> promotedDishes = new HashSet<Dish>();

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public HashSet<Dish> getPromotedDishes() {
        return promotedDishes;
    }

    public void addPromotedDish(Dish dish) {
        promotedDishes.add(dish);
    }

    public abstract double getDiscount(Order order);
}
