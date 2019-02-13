import model.Order;
import service.Report;
import service.promotion.Full30Break6;
import service.promotion.Promotion;
import service.promotion.SpecialDishHalf;
import tools.LoadDishes;

public class Tools {
    public static void main(String[] args) {
        Tools tools = new Tools();
        String[] input = {"ITEM0001 x 1", "ITEM0013 x 2", "ITEM0022 x 1"};
        tools.bestCharge(input);
    }

    public void bestCharge(String[] input) {
        Order order = generateOrder(input);
        Promotion bestPromotion = getBestPromotion(order);
        StringBuilder report = Report.generateReport(order, bestPromotion);
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
}
