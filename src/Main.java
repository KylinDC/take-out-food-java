import model.Order;
import service.Report;
import service.promotion.Promotion;
import tools.Tools;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.print("请以\"菜品名 x 数量\"为格式输入你要点的菜单，不同菜品之间用\"，\"隔开：");
        Scanner scanner = new Scanner(System.in);
        String[] input = (String[]) scanner.nextLine().split("，");
        System.out.println(input[0]);
        main.bestCharge(input);
    }

    public void bestCharge(String[] input) {
        Order order = Tools.generateOrder(input);
        Promotion bestPromotion = Tools.getBestPromotion(order);
        StringBuilder report = Report.generateReport(order, bestPromotion);
        System.out.println(report);
    }
}
