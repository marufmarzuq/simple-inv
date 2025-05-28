### DATABASE SCHEMA

### 1. `products`

| Field       | Type           | Notes                          |
| ----------- | -------------- | ------------------------------ |
| id          | INT (PK, AUTO) | Primary key                    |
| name        | VARCHAR(100)   | Product name                   |
| category    | VARCHAR(50)    | Electronics, Grocery, etc.     |
| description | TEXT           | Optional                       |
| unit\_price | DECIMAL(10, 2) | Selling price per unit         |
| created\_at | DATETIME       | Default NOW()                  |
| updated\_at | DATETIME       | Updated when product is edited |

---

### 2. `stock_log`

| Field        | Type             | Notes                     |
| ------------ | ---------------- | ------------------------- |
| id           | INT (PK, AUTO)   | Primary key               |
| product\_id  | INT (FK)         | References `products(id)` |
| action\_type | ENUM('IN','OUT') | Stock in or out           |
| quantity     | INT              | Units                     |
| created\_at  | DATETIME         | Date of action            |

---

### 3. `stock`

| Field          | Type         | Notes                     |
| -------------- | ------------ | ------------------------- |
| product\_id    | INT (PK, FK) | References `products(id)` |
| current\_stock | INT          | Derived from stock logs   |

---

### UI OVERVIEW

### Tab 1: Manage Products

**Layout: Split Pane (Horizontal)**

* **Left Side: Add/Edit Product Form**

    * Title: “Add Product” or “Edit Product (ID: X)”
    * Fields:

        * Product Name (TextField)
        * Category (ComboBox: Electronics, Grocery, Clothing)
        * Description (TextArea)
        * Unit Price (TextField, decimal)
        * Save / Update Button

* **Right Side: Table of Products**

    * Columns:

        * ID
        * Name
        * Category
        * Unit Price
        * Actions: \[Edit] \[Delete] (Buttons per row)

---

### Tab 2: Stock In/Out

**Layout: Split Pane (Horizontal)**

* **Left Side: Stock Action Form**

    * Title: “Stock In / Out”
    * Fields:

        * Product (ComboBox)
        * Quantity (TextField - int only)
        * Action Type (ComboBox: IN, OUT)
        * Submit Button

* **Right Side: Product Stocks Table**

    * Columns:

        * ID
        * Name
        * Category
        * Current Stock

---

### Tab 3: Stock Report

**Layout: VBox (Vertical Box)**

* **Top Filter Section**

    * From Date (DatePicker)
    * To Date (DatePicker)
    * Filter Button

* **Below: Stock Log Table**

    * Columns:

        * ID
        * Product Name
        * Action Type
        * Quantity
        * Date

---

### Product Categories (for dropdowns)

* Electronics (e.g., Mobile, Laptop, Charger)
* Grocery (e.g., Rice, Oil, Sugar)
* Clothing (e.g., T-shirt, Pants, Jacket)


