package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.JdbcRowSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDBRepository implements IUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SqlRowSet sqlRowSet;


    @Override
    public boolean create(User m) {
        String sql = "INSERT INTO user(username, password) VALUES ('" + m.getUsername() + "','" + m.getPassword() + "')";
        jdbcTemplate.execute(sql);
        return true;
    }

    @Override
    public User read(String username) {
        List<User> users = readAll();
        for (User user : users) {
            if (user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> readAll() {
        String sql = "SELECT * FROM user";
        sqlRowSet = jdbcTemplate.queryForRowSet(sql);

        ArrayList<User> tmp = new ArrayList<>();

        while (sqlRowSet.next()) {
            tmp.add(new User(sqlRowSet.getString("username"), sqlRowSet.getString("password")));
        }

        return tmp;
    }

    public void setup() {

        try {
            File f = new File("SetupHasBeenRun");
            if (f.exists()) {
                return;
            }

            new FileOutputStream(f).close();

            String dropTables1 = "DROP TABLE IF EXISTS user";
            String dropTables2 = "DROP TABLE IF EXISTS product";

            String userTable = "CREATE TABLE user (\n" +
                    "    ID INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    username VARCHAR(255) NOT NULL,\n" +
                    "    password VARCHAR(255) NOT NULL\n" +
                    ")";
            String productTable = "CREATE TABLE product(\n" +
                    "    ID INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                    "    name VARCHAR(255) NOT NULL,\n" +
                    "    image VARCHAR(500) NOT NULL,\n" +
                    "    price DOUBLE NOT NULL,\n" +
                    "    description VARCHAR(500) NOT NULL,\n" +
                    "    catagory VARCHAR(255) NOT NULL \n" +
                    ")";

            String productDummyData =
                    "INSERT INTO product(name, image, price, description, catagory)\n" +
                    "VALUES " + '\n' +
                    "(\"Orange\", \"https://i0.wp.com/www.agriculturenigeria.com/wp-content/uploads/2020/01/orange-1.jpg\", 25, \"Its a fruit, idk\", \"Fruit\")," + '\n' +
                    "(\"Apple\", \"https://raw.githubusercontent.com/Fyziik/Webshop-Exercise/master/src/main/resources/static/images/apple.png\", 5, \"Its a fruit, idk\", \"Fruit\")," + '\n' +
                    "(\"Kiwi\", \"https://raw.githubusercontent.com/Fyziik/Webshop-Exercise/master/src/main/resources/static/images/kiwi.png\", 500, \"Its a fruit, idk\", \"Fruit\")," + '\n' +
                    "(\"Pear\", \"https://raw.githubusercontent.com/Fyziik/Webshop-Exercise/master/src/main/resources/static/images/pear.png\", 15, \"Its a fruit, idk\", \"Fruit\")," + '\n' +
                    "(\"Mobile\", \"https://images-na.ssl-images-amazon.com/images/I/71wPwmxo2NL._SL1500_.jpg\", 150, \"A mobile phone for your everyday purposes\", \"Electronic\")," + '\n' +
                    "(\"Computer\", \"https://s3.eu-north-1.amazonaws.com/foeniks-component-storage-staging/product/49a8a2b3-2611-41c5-9e65-224fc1c3dfcf/5e78ae7df2566b50730cfecd/370x370.jpg\", 1500, \"A gamer pc\", \"Electronic\")," + '\n' +
                    "(\"Toaster\", \"https://images-na.ssl-images-amazon.com/images/I/81smEEgnhfL._AC_SL1500_.jpg\", 50, \"Pling\", \"Electronic\")," + '\n' +
                    "(\"Pirate\", \"https://techcrunch.com/wp-content/uploads/2010/10/pirate.jpg?w=315\", 199, \"We just valued a life\", \"Misc\")," + '\n' +
                    "(\"Monster energy drink (TM)\", \"https://superkulmedia.imgix.net/media/catalog/product/1/6/161830-monster-ultra-paradise-500-ml-energidrikk.jpg?auto=compress,format,strip\", 10, \"RAW ENERGY\", \"Misc\");";

            String userDummyData =
                    "INSERT INTO user(username, password) " +
                            "VALUES " + '\n' +
                            "(\"Test\", \"Test\")," + '\n' +
                            "(\"Fyziik\", \"1234\")," + '\n' +
                            "(\"Kaj\", \"Kaj\");";


            jdbcTemplate.execute(dropTables1);
            jdbcTemplate.execute(dropTables2);
            jdbcTemplate.execute(userTable);
            jdbcTemplate.execute(productTable);
            jdbcTemplate.execute(productDummyData);
            jdbcTemplate.execute(userDummyData);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
