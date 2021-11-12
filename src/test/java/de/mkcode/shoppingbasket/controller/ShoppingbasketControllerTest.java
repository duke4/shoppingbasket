package de.mkcode.shoppingbasket.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import de.mkcode.shoppingbasket.models.Basket;
import de.mkcode.shoppingbasket.models.BasketItem;
import de.mkcode.shoppingbasket.models.Item;

public class ShoppingbasketControllerTest {
    
    ShoppingbasketController controller = new ShoppingbasketController();

    @Test
    public void testScanItem_BasketNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            controller.scanItem(null, "A0001");
        });

        String expectedMessage = "Basket must be given";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testScanItem_ItemNameNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            Basket basket = new Basket(new ArrayList<BasketItem>());
            controller.scanItem(basket, null);
        });

        String expectedMessage = "Item name must be given";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testScanItem_BasketItemNameNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            controller.scanItem(null, null);
        });

        String expectedMessage = "Basket and item name must be given";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testScanItem_UnknownItem() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            Basket basket = new Basket(new ArrayList<BasketItem>());
            controller.scanItem(basket, "A0003");
        });

        String expectedMessage = "Given item cannot be found in inventory";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testScanItem_KnownItem() {
        Basket basket = new Basket(new ArrayList<BasketItem>());
        
        controller.scanItem(basket, "A0001");
        controller.scanItem(basket, "A0001");
        controller.scanItem(basket, "A0002");

        assertEquals(2, basket.getItems().size());
        
        assertEquals("A0001", basket.getItems().get(0).getName());
        assertEquals(2, basket.getItems().get(0).getAmount());
        
        assertEquals("A0002", basket.getItems().get(1).getName());
        assertEquals(1, basket.getItems().get(1).getAmount());
    }

    @Test
    public void testTotal_2For1() {
        Basket basket = new Basket(new ArrayList<>());
        basket.getItems().add(new BasketItem(new Item("A0002", 3.99), 2));

        double total = controller.total(basket);

        assertEquals(3.99, total);
    }

    @Test
    public void testTotal_10PercentOff() {
        Basket basket = new Basket(new ArrayList<>());
        basket.getItems().add(new BasketItem(new Item("A0001", 12.99), 1));

        double total = controller.total(basket);

        assertEquals(11.69, total);
    }

    @Test
    public void testTotal_10PercentOff_2For1() {
        Basket basket = new Basket(new ArrayList<>());
        basket.getItems().add(new BasketItem(new Item("A0001", 12.99), 1));
        basket.getItems().add(new BasketItem(new Item("A0002", 3.99), 2));

        double total = controller.total(basket);

        assertEquals(15.68, total);
    }
}
