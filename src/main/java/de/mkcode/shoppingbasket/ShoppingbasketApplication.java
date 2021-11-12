package de.mkcode.shoppingbasket;

import java.util.ArrayList;
import java.util.List;

import de.mkcode.shoppingbasket.commons.DiscountType;
import de.mkcode.shoppingbasket.controller.ShoppingbasketController;
import de.mkcode.shoppingbasket.models.Basket;
import de.mkcode.shoppingbasket.models.Discount;
import de.mkcode.shoppingbasket.models.Item;

public class ShoppingbasketApplication {

	public static void main(String[] args) {
		Basket basket1 = new Basket(new ArrayList<>());
		Basket basket2 = new Basket(new ArrayList<>());

		ShoppingbasketController controller = new ShoppingbasketController();
		controller.scanItem(basket1, "A0001");

		controller.scanItem(basket2, "A0002");
		controller.scanItem(basket2, "A0002");

		System.out.println("Basket 1: " + controller.total(basket1));
		System.out.println("Basket 2: " + controller.total(basket2));
	}

	/**
	 * Static method returning the shops inventory.
	 * 
	 * @return a list of items
	 */
	public static final List<Item> getInventory() {
		List<Item> inventory = new ArrayList<>();
		inventory.add(new Item("A0001", 12.99));
		inventory.add(new Item("A0002", 3.99));
		
		return inventory;
	}

	/**
	 * Static method returning discounts that should be considered.
	 * 
	 * @return a list of discounts
	 */
	public static final List<Discount> getDiscounts() {
		List<Discount> discounts = new ArrayList<>();
		discounts.add(new Discount(DiscountType.TEN_PERCENT_OFF, "A0001"));
		discounts.add(new Discount(DiscountType.BUY_ONE_GET_ONE_FREE, "A0002"));
		
		return discounts;
	}

}
