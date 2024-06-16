package challenge;

public abstract class Menu {
    protected  String food;
    protected  Integer price;
    protected  Boolean isOrdered;


    protected Menu(String food, Integer price, Boolean isOrdered) {
        this.food = food;
        this.price = price;
        this.isOrdered = isOrdered;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public abstract Integer getTotalPrice();

    public Boolean getIsOrdered() {
        return this.isOrdered;
    }

    public void setIsOrdered(Boolean isOrdered) {
        this.isOrdered = isOrdered;
    }
}
