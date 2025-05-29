-- Create Database
CREATE DATABASE IF NOT EXISTS inventory_app;
USE inventory_app;

-- Table: products
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    description TEXT,
    unit_price DECIMAL(10,2) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Table: stock
CREATE TABLE IF NOT EXISTS stock (
     product_id INT PRIMARY KEY,
     current_stock INT NOT NULL DEFAULT 0,
     FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- Table: stock_log
CREATE TABLE IF NOT EXISTS stock_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    action_type ENUM('IN', 'OUT') NOT NULL,
    quantity INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- test products
INSERT INTO products (name, category, description, unit_price) VALUES
    ('Laptop', 'Electronics', 'Gaming Laptop', 1500.00),
    ('Notebook', 'Stationery', '200 pages ruled', 1.50),
    ('Pen', 'Stationery', 'Blue ink ballpoint', 0.50),
    ('Chair', 'Furniture', 'Office chair with cushion', 45.00),
    ('Table', 'Furniture', 'Wooden office table', 80.00);

select * from products;