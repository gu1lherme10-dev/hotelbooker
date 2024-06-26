CREATE TABLE rooms (
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    daily_price DECIMAL(10,2) NOT NULL,
    hotel_id INT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    image varchar(1000),
    FOREIGN KEY (hotel_id) REFERENCES hotels(id)
);
