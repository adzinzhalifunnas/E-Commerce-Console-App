import main.services.MenuService;
import main.util.FileManagement;

public class App {
    public static void initData() {
        FileManagement.readFromFile("transactions.csv", "transactions");
        FileManagement.readFromFile("products.csv", "products");
        FileManagement.readFromFile("users.csv", "users");
        FileManagement.readFromFile("addresses.csv", "addresses");
    }
    public static void main(String[] args) {
        initData();
        MenuService.showMainMenu();
    }
}
