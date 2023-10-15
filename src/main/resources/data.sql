INSERT INTO category_tab(cat_name,createdOn) VALUES ('category1',CURDATE());
INSERT INTO category_tab(cat_name,createdOn) VALUES ('category2',CURDATE());
INSERT INTO category_tab(cat_name,createdOn) VALUES ('category3',CURDATE());
INSERT INTO category_tab(cat_name,createdOn) VALUES ('category4',CURDATE());
INSERT INTO category_tab(cat_name,createdOn) VALUES ('category5',CURDATE());
INSERT INTO admin_tab(admin_name,admin_password) VALUES ('admin','admin');
INSERT INTO product_tab( product_category, product_price, product_name, addedOn) VALUES (1,123.45,'product1',CURDATE());
INSERT INTO user_tab( firstName,lastName,emailId,password,address,age,createOn) VALUES ('john','doe','john@ex.com','john','123 st,west bank',25,CURDATE());