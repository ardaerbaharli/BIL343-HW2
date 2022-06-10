package com.company;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {
    static final String DB_URL = "jdbc:h2:~/userInfos";

    static final String USER = "bil343";
    static final String PASS = "";

    public Database() {
        System.out.println("Initializing the database.");

//        createSubscriptionTable();
//        createUserTable();
    }

    public static void printSQLException(SQLException ex) {
//        for (Throwable e : ex) {
//            if (e instanceof SQLException) {
//                e.printStackTrace(System.err);
//                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
//                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
//                System.err.println("Message: " + e.getMessage());
//                Throwable t = ex.getCause();
//                while (t != null) {
//                    System.out.println("Cause: " + t);
//                    t = t.getCause();
//                }
//            }
//        }
    }

    public static Connection getConnection() {
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(DB_URL, USER, PASS);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
        return null;
    }

    private boolean createSubscriptionTable() {
//        System.out.println("Creating the subscription table.");
//
//        final String createTableSQL = "create table  if not exists subscription (\r\n" + "  subscriptionPlan  varchar(20),\r\n" +
//                "  username varchar(20),\r\n" + "  subDate date (30)\r\n" + " );";
//
//        try (Connection connection = getConnection();
//
//             Statement statement = connection.createStatement()) {
//
//            statement.execute(createTableSQL);
//
//        } catch (SQLException e) {
//            printSQLException(e);
//            return false;
//        } finally {
//            try {
//                getConnection().close();
//            } catch (SQLException e) {
//                printSQLException(e);
//            }
//        }
        return true;
    }

    public boolean insertSubscription(Subscription subscription) {
//        System.out.println("Inserting a record to subscription table.");
//        final String INSERT_USERS_SQL = "INSERT INTO subscription" +
//                "  (subscriptionPlan, username, subDate) VALUES " +
//                " (?, ?, ?);";
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
//            preparedStatement.setString(1, subscription.getSubscriptionPlan().toString());
//            preparedStatement.setString(2, subscription.getUsername());
//            preparedStatement.setDate(3, Date.valueOf(subscription.getDate()));
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//
//            printSQLException(e);
//            return false;
//        } finally {
//            try {
//                getConnection().close();
//            } catch (SQLException e) {
//                printSQLException(e);
//            }
//        }
//        return true;
        return true;

    }

    public boolean removeSubscription(String username) {
//        System.out.println("Removing a sub from user table.");
//        final String deleteTableSQL = "delete from subscription where username = ?";
//
//        try (Connection connection = getConnection();
//
//             PreparedStatement preparedStatement = connection.prepareStatement(deleteTableSQL)) {
//            preparedStatement.setString(1, username);
//
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            printSQLException(e);
//            return false;
//        } finally {
//            try {
//                getConnection().close();
//            } catch (SQLException e) {
//                printSQLException(e);
//            }
//        }
        return true;
    }

    public List<Subscription> getAllSubscriptions() {
//        System.out.println("Retrieving all subscriptions.");
//        final String QUERY = "select subscriptionPlan, username, subDate from subscription";
//        try (Connection connection = getConnection();
//
//             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
//
//            ResultSet rs = preparedStatement.executeQuery();
//            List<Subscription> subList = new ArrayList<>();
//
//            while (rs.next()) {
//                SubscriptionPlan sp = SubscriptionPlan.valueOf(rs.getString("subscriptionPlan"));
//                String username = rs.getString("username");
//                LocalDate subDate = rs.getDate("subDate").toLocalDate();
//                Subscription subscription = new Subscription(sp, username, subDate);
//                subList.add(subscription);
//            }
//            return subList;
//        } catch (SQLException e) {
//            printSQLException(e);
//            return null;
//        } finally {
//            try {
//                getConnection().close();
//            } catch (SQLException e) {
//                printSQLException(e);
//            }
//        }
        return null;
    }

    public List<Subscription> getSubscriptionsByUsername(String username) {
//        System.out.println("Retrieving a client.");
//        final String QUERY = "select subscriptionPlan, username, subDate from subscription where username =?";
//        try (Connection connection = getConnection();
//
//             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
//            preparedStatement.setString(1, username);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            List<Subscription> subList = new ArrayList<>();
//
//            while (rs.next()) {
//                SubscriptionPlan sp = SubscriptionPlan.valueOf(rs.getString("subscriptionPlan"));
//                LocalDate subDate = rs.getDate("subDate").toLocalDate();
//                Subscription subscription = new Subscription(sp, username, subDate);
//                subList.add(subscription);
//            }
//            return subList;
//        } catch (SQLException e) {
//            printSQLException(e);
//            return null;
//        } finally {
//            try {
//                getConnection().close();
//            } catch (SQLException e) {
//                printSQLException(e);
//            }
//        }
        return  null;
    }

    public boolean createUserTable() {
//        System.out.println("Creating the users table.");
//
//        final String createTableSQL = "create table  if not exists users (\r\n" + "  username  varchar(20) primary key,\r\n" +
//                "  password varchar(20),\r\n" + "  subscriptionPlan varchar(20),\r\n" + "  parentalControl varchar(10)\r\n" + " );";
//
//        try (Connection connection = getConnection();
//             Statement statement = connection.createStatement()) {
//
//            statement.execute(createTableSQL);
//
//        } catch (SQLException e) {
//            printSQLException(e);
//            return false;
//        } finally {
//            try {
//                getConnection().close();
//            } catch (SQLException e) {
//                printSQLException(e);
//            }
//        }
        return true;
    }

    public boolean insertUser(User user) {
//        System.out.println("Inserting a record to user table.");
//        final String INSERT_USERS_SQL = "INSERT INTO users" +
//                "  (username, password, subscriptionPlan, parentalControl) VALUES " +
//                " (?, ?, ?, ?);";
//        try (Connection connection = getConnection();
//
//             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
//            preparedStatement.setString(1, user.getUsername());
//            preparedStatement.setString(2, user.getPassword());
//            preparedStatement.setString(3, user.getLatestSubscription().getSubscriptionPlan().toString());
//            preparedStatement.setBoolean(4, user.isParentalControlOn());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            printSQLException(e);
//            return false;
//        } finally {
//            try {
//                getConnection().close();
//            } catch (SQLException e) {
//                printSQLException(e);
//            }
//        }
        return true;
    }


    public boolean removeUser(String username) {
//        System.out.println("Removing a record from user table.");
//        final String deleteTableSQL = "delete from users where username = ?";
//
//        try (Connection connection = getConnection();
//
//             PreparedStatement preparedStatement = connection.prepareStatement(deleteTableSQL)) {
//            preparedStatement.setString(1, username);
//
//            preparedStatement.execute();
//        } catch (SQLException e) {
//            printSQLException(e);
//            return false;
//        } finally {
//            try {
//                getConnection().close();
//            } catch (SQLException e) {
//                printSQLException(e);
//            }
//        }
        return true;
    }

    public boolean doesExistsInUsers(String username) {
//        System.out.println("Checking if the user exists in user table.");
//        final String QUERY = "select count(*) from users where username=?";
//        try (Connection connection = getConnection();
//
//             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
//            preparedStatement.setString(1, username);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            rs.next();
//            return rs.getInt("count(*)") >= 1;
//        } catch (SQLException e) {
//            printSQLException(e);
//            return false;
//        } finally {
//            try {
//                getConnection().close();
//            } catch (SQLException e) {
//                printSQLException(e);
//            }
//        }
        return false;

    }

    public List<User> getAllUsers() {
//        System.out.println("Retrieving a user.");
//        final String QUERY = "select * from users";
//        try (Connection connection = getConnection();
//
//             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
//
//            ResultSet rs = preparedStatement.executeQuery();
//            List<User> users = new ArrayList<>();
//            while (rs.next()) {
//                String username = rs.getString("username");
//                String password = rs.getString("password");
//                boolean parentalControl = Boolean.parseBoolean(rs.getString("parentalControl"));
//                User u = new User(username, password, parentalControl);
//                u.setSubscriptions(getSubscriptionsByUsername(username));
//                users.add(u);
//            }
//            return users;
//        } catch (SQLException e) {
//            printSQLException(e);
//            return null;
//        } finally {
//            try {
//                getConnection().close();
//            } catch (SQLException e) {
//                printSQLException(e);
//            }
//        }
        return null;
    }

    public User getUser(String username) {
//        System.out.println("Retrieving a user.");
//        final String QUERY = "select username, password, subscriptionPlan, parentalControl from users where username =?";
//        try (Connection connection = getConnection();
//
//             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
//            preparedStatement.setString(1, username);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            rs.next();
//
//            String password = rs.getString("password");
//            SubscriptionPlan subscriptionPlan = SubscriptionPlan.valueOf(rs.getString("subscriptionPlan"));
//            boolean parentalControl = Boolean.parseBoolean(rs.getString("parentalControl"));
//            Subscription subscription = new Subscription(subscriptionPlan, username);
//            return new User(username, password, subscription, parentalControl);
//
//        } catch (SQLException e) {
//            printSQLException(e);
//            return null;
//        } finally {
//            try {
//                getConnection().close();
//            } catch (SQLException e) {
//                printSQLException(e);
//            }
//        }
        return null;
    }

    public boolean updateUser(String username, User updatedUser) {
//        System.out.println("Updating user info.");
//
//        final String UPDATE_USERS_SQL = "update users set username = ?, password =?, subscriptionPlan =?, parentalControl=? where username = ?;";
//        try (Connection connection = getConnection();
//
//             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
//            preparedStatement.setString(1, updatedUser.getUsername());
//            preparedStatement.setString(2, updatedUser.getPassword());
//            preparedStatement.setString(3, updatedUser.getLatestSubscription().getSubscriptionPlan().toString());
//            preparedStatement.setBoolean(4, updatedUser.isParentalControlOn());
//            preparedStatement.setString(5, username);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//
//            printSQLException(e);
//            return false;
//        } finally {
//            try {
//                getConnection().close();
//            } catch (SQLException e) {
//                printSQLException(e);
//            }
//        }
        return true;
    }
}
