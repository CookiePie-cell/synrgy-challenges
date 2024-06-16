CREATE TABLE users(
	id UUID DEFAULT gen_random_uuid(),
	username VARCHAR(255),
	email_address VARCHAR(255),
	password VARCHAR(255),
	PRIMARY KEY(id)
);


CREATE TABLE merchants(
	id UUID DEFAULT gen_random_uuid(),
	merchant_name VARCHAR(255),
	merchant_location VARCHAR(255),
	open BOOLEAN,
	PRIMARY KEY(id)
);
 

CREATE TABLE products(
	id UUID DEFAULT gen_random_uuid(),
	product_name VARCHAR(255),
	price DECIMAL,
	merchant_id UUID,
	PRIMARY KEY(id),
	CONSTRAINT "fk_merchant_id_merchant.id" FOREIGN KEY(merchant_id) REFERENCES merchants(id)
);

CREATE TABLE orders(
	id UUID DEFAULT gen_random_uuid(),
	order_time TIMESTAMP,
	destination_address VARCHAR(255),
	user_id UUID,
	completed BOOLEAN,
	PRIMARY KEY(id),
	CONSTRAINT "fk_user_id_user.id" FOREIGN KEY(user_id) REFERENCES users(id)
);


CREATE TABLE orderdetail(
	id UUID DEFAULT gen_random_uuid(),
	order_id UUID,
	product_id UUID,
	quantity INTEGER,
	total_price DECIMAL,
	PRIMARY KEY(id),
	CONSTRAINT "fk_order_id_orders.id" FOREIGN KEY(order_id) REFERENCES orders(id),
	CONSTRAINT "fk_product_id_products.id" FOREIGN KEY(product_id) REFERENCES products(id)
);