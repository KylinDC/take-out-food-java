package service.promotion;

import model.Dish;
import model.Order;

import java.util.Arrays;
import java.util.Map;

public class ItemPercentPromotion extends Promotion {
    private String[] promoteDishesId;
    private double promotePercent = 1;

    public ItemPercentPromotion(double promotePercent) {
        this.promotePercent = promotePercent;
    }

    public void setPromoteDishesId(String[] promoteDishesId) {
        this.promoteDishesId = promoteDishesId;
    }

    @Override
    public double getDiscount(Order order) {
        double discount = 0;
        for (Map.Entry<Dish, Integer> item : order.getDishes().entrySet()) {
            if (Arrays.asList(promoteDishesId).contains(item.getKey().getId())) {
                addPromotedDish(item.getKey());
                discount += item.getKey().getPrice() * item.getValue() * promotePercent;
            }
        }
        return discount;
    }
}
