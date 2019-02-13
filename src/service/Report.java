package service;

import model.Dish;
import model.Order;
import service.promotion.Promotion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Report {
    public static StringBuilder generateReport(Order order, Promotion promotion) {
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

    public static StringBuilder generateOrderDetailReport(Order order) {
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

    public static StringBuilder generatePromotionDetailReport(Order order, Promotion promotion) {
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
