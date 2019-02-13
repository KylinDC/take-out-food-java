package tools;

import model.Dish;

import java.util.HashMap;

public class LoadDishes {
    private static HashMap<String, Dish> dishes = new HashMap<String, Dish>();

    private static void storageDishes() {
        Dish chicken = new Dish("ITEM0001", "黄焖鸡", 18.00);
        Dish rougamo = new Dish("ITEM0013", "肉夹馍", 6.00);
        Dish noodle = new Dish("ITEM0022", "凉皮", 8.00);
        Dish icecrown = new Dish("ITEM0030", "冰锋", 2.00);

        dishes.put(chicken.getId(), chicken);
        dishes.put(rougamo.getId(), rougamo);
        dishes.put(noodle.getId(), noodle);
        dishes.put(icecrown.getId(), icecrown);
    }

    public static HashMap<String, Dish> getDishes() {
        storageDishes();
        return dishes;
    }

    public static Dish getDishById(String id) {
        storageDishes();
        return dishes.get(id);
    }
}
