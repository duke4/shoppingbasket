package de.mkcode.shoppingbasket.models;

import de.mkcode.shoppingbasket.commons.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Discount {

    private DiscountType type;

    private String itemName;
}
