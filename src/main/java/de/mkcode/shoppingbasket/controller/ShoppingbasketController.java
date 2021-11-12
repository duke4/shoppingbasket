package de.mkcode.shoppingbasket.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.mkcode.shoppingbasket.ShoppingbasketApplication;
import de.mkcode.shoppingbasket.models.Basket;
import de.mkcode.shoppingbasket.models.BasketItem;
import de.mkcode.shoppingbasket.models.Discount;
import de.mkcode.shoppingbasket.models.Item;

/**
 * Controller to add Items to Basket and calculate the total price considering possible discounts.
 */
public class ShoppingbasketController {
    
    /**
     * The given item is added to the given basket, if it is available in the shops invetory.
     * 
     * @param basket Basket the item should be added to
     * @param itemName Name of item that should be added to basket
     * @throws NullPointerException NullPointerException is thrown when basket or itemName are null or item cannot be found in inventory
     */
    public void scanItem(Basket basket, String itemName) throws NullPointerException {

        if (basket != null && itemName != null) {
            // Check inventory for given item
            Optional<Item> item = ShoppingbasketApplication.getInventory().stream().filter(i -> itemName.equals(i.getName())).findFirst();

            if(item.isPresent()) {
                // Check if basket already contains this item, else create a new one
                Optional<BasketItem> bitem = basket.getItems().stream().filter(bi -> bi.equals(item.get())).findFirst();
                if (bitem.isPresent()) {
                    bitem.get().setAmount(bitem.get().getAmount()+1);
                } else {
                    basket.getItems().add(new BasketItem(item.get(), 1));
                }
            } else {
                throw new NullPointerException("Given item cannot be found in inventory");
            }
        } else {
            if (basket == null && itemName == null) {
                throw new NullPointerException("Basket and item name must be given");
            }
            
            if (basket == null) {
                throw new NullPointerException("Basket must be given");
            }

            if (itemName == null) {
                throw new NullPointerException("Item name must be given");
            }
        }
    }

    /**
     * Calculate the given baskets total price considering possible discounts for each item.
     * 
     * @param basket Basket the total price should be calculated for
     * @return total price of basket considering possible discounts
     */
    public double total(Basket basket) {
        double total = 0.0;

        for (BasketItem bitem : basket.getItems()) {
            
            // Local variables to consider possible discounts
            double bitemPrice = bitem.getPrice();
            int amount = bitem.getAmount();
            
            // Check for discounts for current item
            List<Discount> itemDiscounts = ShoppingbasketApplication.getDiscounts().stream().filter(d -> bitem.getName().equals(d.getItemName())).collect(Collectors.toList());
            for (Discount discount : itemDiscounts) {
                switch (discount.getType()) {
                    case TEN_PERCENT_OFF: bitemPrice*=0.9; break;
                    case BUY_ONE_GET_ONE_FREE: amount = (amount + 1) / 2; break;
                    default: break;
                }
            }

            // add total item price with new price and amount to total basket price
            total += amount * bitemPrice;
        }

        return new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
