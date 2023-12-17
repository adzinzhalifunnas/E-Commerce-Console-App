package main.database;

import java.util.ArrayList;
import java.util.List;

import main.model.User;

public class Database {
    public static List<User> users = new ArrayList<User>();

    public static Boolean isLogin = false;
    public static User loggedInUser = null;
}
