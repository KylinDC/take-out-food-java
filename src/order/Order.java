package order;

import dish.Dish;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private HashMap<Dish, Integer> dishes = new HashMap<Dish, Integer>();

    public HashMap<Dish, Integer> getDishes() {
        return dishes;
    }

    public double getOrderOriginPrice() {
        float orderOriginPrice = 0;
        for (Map.Entry<Dish, Integer> dish : dishes.entrySet()) {
            orderOriginPrice += dish.getKey().getPrice() * dish.getValue();
        }
        return orderOriginPrice;
    }

    public void addDish(Dish dish, int amount) {
        dishes.put(dish, amount);
    }
}
