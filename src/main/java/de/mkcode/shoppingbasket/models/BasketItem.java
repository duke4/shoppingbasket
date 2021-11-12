package de.mkcode.shoppingbasket.models;

import lombok.EqualsAndHashCode.Exclude;
import lombok.ToString;

/**
 * BasketItem extending shop item.
 * It is used inside a basket to additionally hold the amount of the item.
 */
@ToString(callSuper = true)
public class BasketItem extends Item {
    
    @Exclude
    private int amount;

    public BasketItem(Item item, int amount) {
        super(item.getName(), item.getPrice());
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
