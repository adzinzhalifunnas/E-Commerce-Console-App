package main.database;

import java.util.ArrayList;
import java.util.List;

import main.model.Address;
import main.model.User;

public class Database {
    public static List<User> users = new ArrayList<User>();
    public static List<Address> addresses = new ArrayList<Address>();

    public static Boolean isLogin = false;
    public static User loggedInUser = null;
}
