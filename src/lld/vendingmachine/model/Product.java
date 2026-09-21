package lld.vendingmachine.model;

import lld.vendingmachine.enums.ProductType;

final public class Product {
    private final String id;
    private final String name;
    private final ProductType type;
    private final int price; // keeping it as int because our current price is 10, 20, 50, 100 only

    public Product(String id, String name, ProductType type, int price) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Invalid product ID");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Invalid product name");
        }

        if (type == null) {
            throw new IllegalArgumentException("Product type is required");
        }

        if (price <= 0 || price % 10 != 0) {
            throw new IllegalArgumentException("Invalid product price");
        }

        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }
}
