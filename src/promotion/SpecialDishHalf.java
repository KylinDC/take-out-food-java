package promotion;

public class SpecialDishHalf extends ItemPercentPromotion {

    private String[] promoteItemsId = {"ITEM0001", "ITEM0022"};

    public SpecialDishHalf() {
        super(0.5);
        setPromoteDishesId(promoteItemsId);
        setPromotionType("指定菜品半价");
    }

    public String[] getPromoteItemsId() {
        return promoteItemsId;
    }
}
