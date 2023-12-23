package main.services;

import java.util.UUID;

import main.database.Database;
import main.helper.ProductHelper;
import main.model.request.ProductRequestDTO;
import main.util.Util;

public class ProductService {
    public static void addProduct() {
        String productName, productDescription;
        Double productPrice;
        int productStock;
        do {
            System.out.print("Enter Product Name [3..50]: ");
            productName = Util.scanString();
        } while (productName.length() < 3 || productName.length() > 50);
        do {
            System.out.print("Enter Product Description [3..50]: ");
            productDescription = Util.scanString();
        } while (productDescription.length() < 3 || productDescription.length() > 50);
        do {
            System.out.print("Enter Product Price [1K..1B]: ");
            productPrice = Util.scanDouble();
        } while (productPrice < 1000 || productPrice > 1000000000);
        do {
            System.out.print("Enter Product Stock [1..1000]: ");
            productStock = Util.scanInt();
        } while (productStock < 1 || productStock > 1000);
        ProductRequestDTO productRequestDTO = new ProductRequestDTO(null, Database.loggedInUser.getUserID(), productName, productDescription, productPrice, productStock, 0);
        ProductHelper.addProduct(productRequestDTO);
    }

    public static void viewUserProducts() {
        ProductHelper.viewUserProducts(Database.loggedInUser.getUserID());
    }

    public static void viewAllProducts() {
        ProductHelper.viewAllProducts();
    }

    public static Boolean isProductOutOfStock(UUID productID) {
        return ProductHelper.isProductOutOfStock(productID);
    }
}