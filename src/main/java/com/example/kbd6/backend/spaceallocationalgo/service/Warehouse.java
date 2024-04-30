package com.example.kbd6.backend.spaceallocationalgo.service;

import com.example.kbd6.backend.service.inventory.product.ProductService;
import com.example.kbd6.backend.spaceallocationalgo.model.Location;
import com.example.kbd6.backend.spaceallocationalgo.model.Product;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Warehouse {
    private final int numRows = 10; // Number of rows in the warehouse
    private final  int numColumnsPerRow = 10; // Number of columns per row in the warehouse
    private boolean[][] occupiedLocations = new boolean[numRows][numColumnsPerRow];
    private final Map<Integer, Location> productLocations = new HashMap<>();

    public Location[] addProducts(Product[] products) {
        Map<Integer, Location> productLocations = new HashMap<>();
        occupiedLocations = new boolean[numRows][numColumnsPerRow];
        Location[] locations = new Location[products.length];
        for (int i = 0; i < products.length; i++) {
            Product product = products[i];
            double volume = product.getVolume();
            double totalVolume = product.getTotalVolume();
            // Calculate the number of shelves and breadth required
            int noOfShelves = (int) Math.ceil(totalVolume / 450000); // 450000 is the default shelf volume
            int breadthRequired = (int) Math.ceil(noOfShelves / 5.0);
            breadthRequired = breadthRequired*15; // 15 minimum breadth required
            int columnIndex = findEmptyColumn();
            if (columnIndex != -1) {
                Location location = allocateProduct(columnIndex, product, noOfShelves, breadthRequired);
                locations[i] = location;
            }
        }
        return locations;
    }

    private int findEmptyColumn() {
        for (int row = 1; row <= numRows; row++) {
            for (int column = 1; column <= numColumnsPerRow; column++) {
                if (!occupiedLocations[row - 1][column - 1]) {
                    return (row - 1) * numColumnsPerRow + (column - 1);
                }
            }
        }
        return -1;
    }

    private Location allocateProduct(int columnIndex, Product product, int noOfShelves, int breadthRequired) {
        int row = columnIndex / numColumnsPerRow + 1;
        int column = columnIndex % numColumnsPerRow + 1;
        int identifier = (row * 1000) + column;
        Location location = new Location(row, column, noOfShelves, product.getTotalVolume(), breadthRequired, product.getProductid(), product.getQuantity(), true);
        occupiedLocations[row - 1][column - 1] = true;
        productLocations.put(identifier, location);
        return location;
    }

    public Location[] getAllLocations() {
        Location[] allLocations = new Location[numRows * numColumnsPerRow];
        int index = 0;
        for (int row = 1; row <= numRows; row++) {
            for (int column = 1; column <= numColumnsPerRow; column++) {
                long productId = -1L; // (not occupied)
                int quantity = 0;   // (not occupied)
                int columnIndex = (row - 1) * numColumnsPerRow + (column - 1);
                int identifier = (row * 1000) + column;
                Location location = productLocations.get(identifier);
                if (location != null ) {
                    productId = location.getProductId();
                    quantity = location.getQuantity();
                    allLocations[index] = new Location(row, column, location.getNoOfshelves(), location.getTotalVolume(),
                            location.getBreadthRequired(), productId, quantity, true);

                } else {
                    allLocations[index] = new Location(row, column, 0, 0.0, 15, -1, 0, false);
                }
                index++;
            }
        }
        return allLocations;
    }
}




