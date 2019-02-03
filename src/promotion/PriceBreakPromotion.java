package promotion;

import order.Order;

public class PriceBreakPromotion extends Promotion {
    private double totalPrice;
    private double breakPrice;

    public PriceBreakPromotion(double totalPrice, double breakPrice) {
        this.totalPrice = totalPrice;
        this.breakPrice = breakPrice;
    }

    @Override
    public double getDiscount(Order order) {
        return order.getOrderOriginPrice() >= totalPrice ? breakPrice : 0;
    }
}
