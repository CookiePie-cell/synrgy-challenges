package challenge;

public class FoodItem extends Menu{

    private Integer quantity;

    public FoodItem(String food, Integer price, Boolean isOrdered) {
        super(food, price, isOrdered);
        this.quantity = 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public Integer getTotalPrice() {
        return this.price * this.quantity;
    }
}
