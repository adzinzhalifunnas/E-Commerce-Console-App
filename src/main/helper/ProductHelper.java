package main.helper;

import java.util.UUID;

import main.database.Database;
import main.model.Product;
import main.model.User;
import main.model.request.ProductRequestDTO;
import main.util.FileManagement;
import main.util.Util;

public class ProductHelper {
    public static Boolean isProductExist(UUID productSellerID, String productName) {
        for (Product product : Database.products) {
            if (product.getProductSellerID().equals(productSellerID) && product.getProductName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public static Boolean isProductOutOfStock(UUID productID) {
        for (Product product : Database.products) {
            if (product.getProductID().equals(productID) && product.getProductStock() == 0) {
                return true;
            }
        }
        return false;
    }

    public static User getUserDetail(UUID productSellerID) {
        for (User user : Database.users) {
            if (user.getUserID().equals(productSellerID)) {
                return user;
            }
        }
        return null;
    }

    public static void addProduct(ProductRequestDTO productRequestDTO) {
        Boolean isProductExist = isProductExist(productRequestDTO.getProductSellerID(), productRequestDTO.getProductName());
        if (!isProductExist) {
            Product product = new Product(
                productRequestDTO.getProductID() == null ? UUID.randomUUID() : productRequestDTO.getProductID(),
                productRequestDTO.getProductSellerID(),
                productRequestDTO.getProductName(),
                productRequestDTO.getProductDescription(),
                productRequestDTO.getProductPrice(),
                productRequestDTO.getProductStock(),
                productRequestDTO.getProductSold()
            );
            Database.products.add(product);
            FileManagement.writeToFile("products.csv", "products", product);
        }
    }

    public static void viewUserProducts(UUID productSellerID) {
        int no = 1;
        for (Product product : Database.products) {
            if (product.getProductSellerID().equals(productSellerID)) {
                System.out.printf("%d. - Product ID: %s\n", no++, product.getProductID());
                System.out.printf("   - Product Name: %s\n", product.getProductName());
                System.out.printf("   - Product Description: %s\n", product.getProductDescription());
                System.out.printf("   - Product Price: IDR %.2f\n", product.getProductPrice());
                System.out.printf("   - Product Stock: %s\n", product.getProductStock());
                System.out.printf("   - Product Sold: %s\n", product.getProductSold());
                System.out.println();
            }
        }
        if (no == 0) {
            System.out.println("[Info] You have no product yet.");
        }
    }

    public static void viewAllProducts() {
        int no = 1;
        for (Product product : Database.products) {
            System.out.printf("%d. - Product Name: %s\n", no++, product.getProductName());
            System.out.printf("   - Product Description: %s\n", product.getProductDescription());
            System.out.printf("   - Product Price: %s\n", Util.formatIDR(product.getProductPrice()));
            if (product.getProductStock() == 0) {
                System.out.printf("   - Product Stock: Out of Stock\n");
            } else {
                System.out.printf("   - Product Stock: %d\n", product.getProductStock());
            }
            System.out.printf("   - Product Sold: %d\n", product.getProductSold());
            String storeFullName = getUserDetail(product.getProductSellerID()).getFirstName() + " " + getUserDetail(product.getProductSellerID()).getLastName();
            System.out.printf("   - Product Seller: %s\n", storeFullName);
            System.out.println();
        }
        if (no == 0) {
            System.out.println("[Info] There is no product yet.");
        }
    }

    public static Product getProduct(UUID productID) {
        for (Product product : Database.products) {
            if (product.getProductID().equals(productID)) {
                return product;
            }
        }
        return null;
    }

    public static String getProductName(UUID productID) {
        for (Product product : Database.products) {
            if (product.getProductID().equals(productID)) {
                return product.getProductName();
            }
        }
        return null;
    }

    public static int getProductStock(UUID productID) {
        for (Product product : Database.products) {
            if (product.getProductID().equals(productID)) {
                return product.getProductStock();
            }
        }
        return 0;
    }
}
