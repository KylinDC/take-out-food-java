package tools;

import model.Order;
import service.promotion.Full30Break6;
import service.promotion.Promotion;
import service.promotion.SpecialDishHalf;

public class Tools {
    public static Order generateOrder(String[] input) {
        Order order = new Order();
        for (String item : input) {
            String id = item.split(" x ")[0];
            int amount = Integer.parseInt(item.split(" x ")[1]);
            order.addDish(LoadDishes.getDishByName(id), amount);
        }
        return order;
    }

    public static Promotion getBestPromotion(Order order) {
        SpecialDishHalf specialDishHalf = new SpecialDishHalf();
        Full30Break6 full30break6 = new Full30Break6();

        double specialDishHalfDiscount = specialDishHalf.getDiscount(order);
        double full30break6Discount = full30break6.getDiscount(order);

        return full30break6Discount >= specialDishHalfDiscount ? full30break6 : specialDishHalf;
    }
}
