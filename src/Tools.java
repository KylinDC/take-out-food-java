import model.Dish;
import model.Order;
import service.promotion.Full30Break6;
import service.promotion.Promotion;
import service.promotion.SpecialDishHalf;
import tools.LoadDishes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Tools {
    public static void main(String[] args) {
        Tools tools = new Tools();
        String[] input = {"ITEM0001 x 1", "ITEM0013 x 2", "ITEM0022 x 1"};
        tools.bestCharge(input);
    }

    public void bestCharge(String[] input) {
        Order order = generateOrder(input);
        Promotion bestPromotion = getBestPromotion(order);
        StringBuilder report = generateReport(order, bestPromotion);
        System.out.println(report);
    }

    public Order generateOrder(String[] input) {
        Order order = new Order();
        for (String item : input) {
            String id = item.split(" x ")[0];
            int amount = Integer.parseInt(item.split(" x ")[1]);
            order.addDish(LoadDishes.getDishById(id), amount);
        }
        return order;
    }

    public Promotion getBestPromotion(Order order) {
        SpecialDishHalf specialDishHalf = new SpecialDishHalf();
        Full30Break6 full30break6 = new Full30Break6();

        double specialDishHalfDiscount = specialDishHalf.getDiscount(order);
        double full30break6Discount = full30break6.getDiscount(order);

        return full30break6Discount >= specialDishHalfDiscount ? full30break6 : specialDishHalf;
    }

    public StringBuilder generateReport(Order order, Promotion promotion) {
        StringBuilder report = new StringBuilder();
        double orderPrice = order.getOrderOriginPrice() - promotion.getDiscount(order);

        report.append(
                String.format(
                        "============= 订餐明细 =============\n%s-----------------------------------\n%s总计：%.2f元\n===================================",
                        generateOrderDetailReport(order),
                        generatePromotionDetailReport(order, promotion),
                        orderPrice));
        return report;
    }

    public StringBuilder generateOrderDetailReport(Order order) {
        StringBuilder orderDetailReport = new StringBuilder();
        for (Map.Entry<Dish, Integer> item : order.getDishes().entrySet()) {
            orderDetailReport.append(
                    String.format(
                            "%s x %d = %.2f元\n",
                            item.getKey().getName(),
                            item.getValue(),
                            item.getKey().getPrice() * item.getValue()));
        }
        return orderDetailReport;
    }

    public StringBuilder generatePromotionDetailReport(Order order, Promotion promotion) {
        StringBuilder promotionDetailReport = new StringBuilder();

        if (promotion.getDiscount(order) > 0) {
            switch (promotion.getPromotionType()) {
                case "满30减6元":
                    promotionDetailReport.append(
                            String.format(
                                    "使用优惠:\n%s，省%.2f元\n-----------------------------------\n",
                                    promotion.getPromotionType(), promotion.getDiscount(order)));
                    break;
                case "指定菜品半价":
                    HashSet<Dish> promotedDishes = promotion.getPromotedDishes();
                    ArrayList<String> promotedDishesName = new ArrayList<String>();
                    for (Dish dish : promotedDishes) {
                        promotedDishesName.add(dish.getName());
                    }
                    promotionDetailReport.append(
                            String.format(
                                    "使用优惠:\n%s(%s)，省%.2f元\n-----------------------------------\n",
                                    promotion.getPromotionType(),
                                    String.join("，", promotedDishesName),
                                    promotion.getDiscount(order)));
                    break;
                default:
                    break;
            }
        }
        return promotionDetailReport;
    }
}
