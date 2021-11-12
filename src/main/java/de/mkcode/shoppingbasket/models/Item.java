package de.mkcode.shoppingbasket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Item that is part of the shops inventory.
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Item {
    
    private String name;

    private Double price;
}
