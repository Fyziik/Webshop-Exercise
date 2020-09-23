DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS product;

CREATE TABLE user (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE product(
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(500) NOT NULL,
    price DOUBLE NOT NULL,
    description VARCHAR(500) NOT NULL,
    catagory VARCHAR(255) NOT NULL
);

INSERT INTO product(name, image, price, description, catagory)
VALUES ("Orange", "https://i0.wp.com/www.agriculturenigeria.com/wp-content/uploads/2020/01/orange-1.jpg", 25, "Its a fruit, idk", "Fruit");

INSERT INTO product(name, image, price, description, catagory)
VALUES ("Apple", "https://raw.githubusercontent.com/Fyziik/Webshop-Exercise/master/src/main/resources/static/images/apple.png", 5, "Its a fruit, idk", "Fruit");

INSERT INTO product(name, image, price, description, catagory)
VALUES ("Kiwi", "https://raw.githubusercontent.com/Fyziik/Webshop-Exercise/master/src/main/resources/static/images/kiwi.png", 500, "Its a fruit, idk", "Fruit");

INSERT INTO product(name, image, price, description, catagory)
VALUES ("Pear", "https://raw.githubusercontent.com/Fyziik/Webshop-Exercise/master/src/main/resources/static/images/pear.png", 15, "Its a fruit, idk", "Fruit");

INSERT INTO product(name, image, price, description, catagory)
VALUES ("Mobile", "https://images-na.ssl-images-amazon.com/images/I/71wPwmxo2NL._SL1500_.jpg", 150, "A mobile phone for your everyday purpose", "Electronic");

INSERT INTO product(name, image, price, description, catagory)
VALUES ("Computer", "https://s3.eu-north-1.amazonaws.com/foeniks-component-storage-staging/product/49a8a2b3-2611-41c5-9e65-224fc1c3dfcf/5e78ae7df2566b50730cfecd/370x370.jpg", 1500, "A gamer pc", "Electronic");

INSERT INTO product(name, image, price, description, catagory)
VALUES ("Toaster", "https://images-na.ssl-images-amazon.com/images/I/81smEEgnhfL._AC_SL1500_.jpg", 50, "Pling", "Electronic");

INSERT INTO product(name, image, price, description, catagory)
VALUES ("Pirate", "https://techcrunch.com/wp-content/uploads/2010/10/pirate.jpg?w=315", 199, "We just valued a life", "Misc");

INSERT INTO product(name, image, price, description, catagory)
VALUES ("Monster energy drink (TM)", "https://superkulmedia.imgix.net/media/catalog/product/1/6/161830-monster-ultra-paradise-500-ml-energidrikk.jpg?auto=compress,format,strip", 10, "RAW ENERGY", "Misc");

INSERT INTO user(username, password)
VALUES("Test", "Test");

INSERT INTO user(username, password)
VALUES("Fyziik", "1234");

INSERT INTO user(username, password)
VALUES("Kaj", "Kaj");