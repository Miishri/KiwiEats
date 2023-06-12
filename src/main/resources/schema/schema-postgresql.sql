-- Customer table
CREATE TABLE customer (
    id UUID PRIMARY KEY
);

-- Order table
CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        order_details VARCHAR(255) NOT NULL,
                        quantity INT CHECK (quantity >= 1 AND quantity <= 50),
                        active BOOLEAN,
                        ordered_date TIMESTAMP,
                        customer_id UUID,
                        rider_id UUID,
                        FOREIGN KEY (customer_id) REFERENCES customer (id),
                        FOREIGN KEY (rider_id) REFERENCES riders (id)
);

-- Product table
CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          product_name VARCHAR(255) NOT NULL,
                          product_description VARCHAR(255) NOT NULL,
                          product_image_link VARCHAR(255) NOT NULL,
                          product_type VARCHAR(255),
                          product_stock INT CHECK (product_stock >= 1),
                          price NUMERIC CHECK (price >= 1),
                          order_id INT,
                          seller_id UUID,
                          created_date TIMESTAMP,
                          updated_date TIMESTAMP,
                          FOREIGN KEY (order_id) REFERENCES orders (id),
                          FOREIGN KEY (seller_id) REFERENCES sellers (id)
);

-- Rider table
CREATE TABLE riders (
                        id UUID PRIMARY KEY,
                        tips NUMERIC,
                        total_paid NUMERIC,
                        verified BOOLEAN NOT NULL
);

-- RiderDetails table
CREATE TABLE rider_details (
                               id SERIAL PRIMARY KEY,
                               rider_id UUID,
                               first_name VARCHAR(255) NOT NULL,
                               last_name VARCHAR(255),
                               email VARCHAR(255) NOT NULL,
                               password VARCHAR(255),
                               phone INT NOT NULL,
                               street VARCHAR(255) NOT NULL,
                               care_of VARCHAR(255),
                               city VARCHAR(80) NOT NULL,
                               country VARCHAR(60) NOT NULL,
                               post_code INT,
                               registered_date TIMESTAMP,
                               verified BOOLEAN NOT NULL,
                               FOREIGN KEY (rider_id) REFERENCES riders (id)
);

-- UserDetails table
CREATE TABLE user_details (
                              id SERIAL PRIMARY KEY,
                              user_id UUID,
                              first_name VARCHAR(255) NOT NULL,
                              last_name VARCHAR(255),
                              email VARCHAR(255) NOT NULL,
                              password VARCHAR(255),
                              phone INT NOT NULL,
                              street VARCHAR(255) NOT NULL,
                              care_of VARCHAR(255),
                              city VARCHAR(80) NOT NULL,
                              country VARCHAR(60) NOT NULL,
                              post_code INT,
                              registered_date TIMESTAMP
);
