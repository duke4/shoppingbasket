package de.mkcode.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import de.mkcode.shoppingbasket.commons.DiscountType;
import de.mkcode.shoppingbasket.models.Discount;
import de.mkcode.shoppingbasket.models.Item;

@SpringBootTest
public class ShoppingbasketApplicationTests {

	@Test
	public void testGetInventory() {
		List<Item> inventory = ShoppingbasketApplication.getInventory();

		assertEquals(2, inventory.size());
		assertEquals(new Item("A0001", 12.99), inventory.get(0));
		assertEquals(new Item("A0002", 3.99), inventory.get(1));
	}

	@Test
	public void testGetDiscounts() {
		List<Discount> discounts = ShoppingbasketApplication.getDiscounts();

		assertEquals(2, discounts.size());
		assertEquals(new Discount(DiscountType.TEN_PERCENT_OFF, "A0001"), discounts.get(0));
		assertEquals(new Discount(DiscountType.BUY_ONE_GET_ONE_FREE, "A0002"), discounts.get(1));
	}
}
