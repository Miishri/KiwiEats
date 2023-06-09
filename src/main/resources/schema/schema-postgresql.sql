CREATE TABLE IF NOT EXISTS customer (
                                        customer_id UUID PRIMARY KEY,
                                        total_orders INTEGER,
                                        verified BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS customer_details (
                                                id SERIAL PRIMARY KEY,
                                                customer_id UUID REFERENCES customer (customer_id),
                                                first_name VARCHAR(255) NOT NULL,
                                                last_name VARCHAR(255),
                                                email VARCHAR(255) NOT NULL,
                                                password VARCHAR(255),
                                                phone INTEGER NOT NULL,
                                                street VARCHAR(255) NOT NULL,
                                                care_of VARCHAR(255),
                                                city VARCHAR(80) NOT NULL,
                                                country VARCHAR(60) NOT NULL,
                                                post_code INTEGER,
                                                registered_date TIMESTAMP,
                                                verified BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS rider (
                                     rider_id UUID PRIMARY KEY,
                                     tips NUMERIC,
                                     total_paid NUMERIC
);

CREATE TABLE IF NOT EXISTS rider_details (
                                             id SERIAL PRIMARY KEY,
                                             rider_id UUID REFERENCES rider (rider_id),
                                             first_name VARCHAR(255) NOT NULL,
                                             last_name VARCHAR(255),
                                             email VARCHAR(255) NOT NULL,
                                             password VARCHAR(255),
                                             phone INTEGER NOT NULL,
                                             street VARCHAR(255) NOT NULL,
                                             care_of VARCHAR(255),
                                             city VARCHAR(80) NOT NULL,
                                             country VARCHAR(60) NOT NULL,
                                             post_code INTEGER,
                                             registered_date TIMESTAMP,
                                             verified BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS seller (
                                      seller_id UUID PRIMARY KEY,
                                      total_customers INTEGER,
                                      revenue NUMERIC,
                                      verified BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS seller_details (
                                              id SERIAL PRIMARY KEY,
                                              seller_id UUID REFERENCES seller (seller_id),
                                              first_name VARCHAR(255) NOT NULL,
                                              last_name VARCHAR(255),
                                              email VARCHAR(255) NOT NULL,
                                              password VARCHAR(255),
                                              phone INTEGER NOT NULL,
                                              street VARCHAR(255) NOT NULL,
                                              care_of VARCHAR(255),
                                              city VARCHAR(80) NOT NULL,
                                              country VARCHAR(60) NOT NULL,
                                              post_code INTEGER,
                                              registered_date TIMESTAMP,
                                              verified BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
                                      id SERIAL PRIMARY KEY,
                                      order_details VARCHAR(255) NOT NULL,
                                      quantity INTEGER,
                                      active BOOLEAN,
                                      ordered_date TIMESTAMP,
                                      customer_id UUID REFERENCES customer (customer_id),
                                      rider_id UUID REFERENCES rider (rider_id)
);

CREATE TABLE IF NOT EXISTS product (
                                       id SERIAL PRIMARY KEY,
                                       product_name VARCHAR(255) NOT NULL,
                                       product_description VARCHAR(255) NOT NULL,
                                       product_image_link VARCHAR(255) NOT NULL,
                                       product_type VARCHAR(255),
                                       product_stock INTEGER,
                                       price NUMERIC,
                                       order_id BIGINT REFERENCES orders (id),
                                       seller_id UUID REFERENCES seller (seller_id),
                                       created_date TIMESTAMP,
                                       updated_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS discount (
                                        id SERIAL PRIMARY KEY,
                                        customer_id UUID REFERENCES customer (customer_id),
                                        product_type VARCHAR(255),
                                        discount_percentage NUMERIC,
                                        valid BOOLEAN
);
