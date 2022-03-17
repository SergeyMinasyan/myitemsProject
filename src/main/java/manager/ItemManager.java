package manager;

import db.DBConnectionProvider;
import model.Item;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserManager userManager = new UserManager();
    private CategoryManager categoryManager = new CategoryManager();

    public void add(Item item) {
        String sql = "insert into item(title,price,description,pic_url,user_id,category_id) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getTitle());
            statement.setDouble(2, item.getPrice());
            statement.setString(3, item.getDescription());
            statement.setString(4, item.getPicUrl());
            statement.setInt(5, item.getUser().getId());
            statement.setInt(6, item.getCategory().getId());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                item.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public Item getItemById(int id) {
        String sql = "select * from item where id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .picUrl(resultSet.getString(5))
                        .User(userManager.getUserById(resultSet.getInt(6)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;

    }

    private User getUserFromResultSet(ResultSet resultSet) {

        try {
            return User.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .password(resultSet.getString(5))
                    .phone(resultSet.getString(6))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void edit(User user) {

        String sql = "update user Set name = ?,surname = ?,email = ?,password = ?,phone = ? where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhone());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }


    public User getByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);

        }
        return null;

    }

    public List<Item> getAllItems() {
        String sql = "select * from item";
        List<Item> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .picUrl(resultSet.getString(5))
                        .User(userManager.getUserById(resultSet.getInt(6)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;

    }

    public List<Item> getLast20Items() {
        String sql = "select * from item order by id desc limit 20 ";
        List<Item> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .picUrl(resultSet.getString(5))
                        .User(userManager.getUserById(resultSet.getInt(6)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;

    }

    public List<Item> getLast20ItemsByCategory(int categoryId) {
        String sql = "select * from item where category_id = " + categoryId + " order by id desc limit 20 ";
        List<Item> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .picUrl(resultSet.getString(5))
                        .User(userManager.getUserById(resultSet.getInt(6)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;

    }

    public List<Item> getAllUserItems(int userId) {
        String sql = "select * from item where user_id = " + userId;
        List<Item> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(Item.builder()
                        .id(resultSet.getInt(1))
                        .title(resultSet.getString(2))
                        .price(resultSet.getDouble(3))
                        .description(resultSet.getString(4))
                        .picUrl(resultSet.getString(5))
                        .User(userManager.getUserById(resultSet.getInt(6)))
                        .category(categoryManager.getCategoryById(resultSet.getInt(7)))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;

    }

    public void deleteItemById(int id) {
        String sql = "delete from item where id = " + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

}
