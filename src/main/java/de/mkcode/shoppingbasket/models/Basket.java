package de.mkcode.shoppingbasket.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Basket that has a list of BasketItems.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Basket {

    List<BasketItem> items;
}
