import main.helper.UserHelper;
import main.model.request.UserRequestDTO;
import main.services.MenuService;
import main.util.FileManagement;
import main.util.Password;

public class App {
    public static void initData() {
        FileManagement.readFromFile("users.csv", "users");
        FileManagement.readFromFile("addresses.csv", "addresses");
    }
    public static void main(String[] args) {
        initData();
        // Default user (customer)
        UserRequestDTO userRequestDTO = new UserRequestDTO(
            null,
            "x@x.xi",
            Password.hash("12345"),
            "John",
            "Doe",
            "0123456789",
            1
        );
        UserHelper.addUser(userRequestDTO);
        // Default user (seller)
        UserRequestDTO userRequestDTO2 = new UserRequestDTO(
            null,
            "a@a.ai",
            Password.hash("12345"),
            "Toko Buku",
            "Palugada",
            "012345678911",
            2
        );
        UserHelper.addUser(userRequestDTO2);
        MenuService.showMainMenu();
    }
}
