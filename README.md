# Inventory-Monitoring-Reporting-System
Inventory Monitoring &amp; Reporting System A role-based Inventory Management System built with Spring Boot, Spring Security, Thymeleaf, and MySQL. The application allows users, managers, and admins to manage stock, track transactions, simulate inventory updates, and export logs as CSV reports with secure authentication and access control.
# 📦 Inventory Management System

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Template%20Engine-blue)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Bootstrap](https://img.shields.io/badge/Bootstrap-5-purple)
![Project](https://img.shields.io/badge/Project-Full%20Stack-success)
![Internship](https://img.shields.io/badge/Infosys-Springboard%20Internship-blue)
![License](https://img.shields.io/badge/License-MIT-green)

A **web-based Inventory Management System (IMS)** built using **Spring Boot, Thymeleaf, and MySQL**.  
This application helps manage products, suppliers, inventory levels, and stock tracking efficiently.

---

# 🚀 Features

## 🔐 Authentication & Security
- User registration and login
- Role-based access control
- Access denied page for unauthorized access
- Secure authentication system

## 📦 Product Management
- Add new products
- Update product details
- Delete products
- View all products
- Track product quantity

## 📉 Low Stock Monitoring
- Automatically detects low stock products
- Displays alerts on dashboard

## 📊 Inventory Dashboard
- Total products
- Low stock items
- Total inventory value
- System status indicators

## 🧾 Stock Logs
Tracks inventory actions:
- Item name
- Quantity
- Action performed
- Timestamp

## 📤 Export Data
- Export inventory data to CSV format

## 🏭 Supplier Management
- Add suppliers
- Manage supplier information

---

# 🏗 System Architecture

```
Frontend (Thymeleaf Templates)
        │
        ▼
Spring Boot Controllers
        │
        ▼
Service Layer
        │
        ▼
Spring Data JPA Repositories
        │
        ▼
MySQL Database
```

---

# 🛠 Tech Stack

| Technology | Usage |
|------------|------|
| Java | Backend |
| Spring Boot | Application Framework |
| Spring Security | Authentication |
| Spring Data JPA | Database ORM |
| Thymeleaf | Frontend Templates |
| MySQL | Database |
| Maven | Dependency Management |
| HTML/CSS | UI |

---

# 📂 Project Structure

```
src/
│── controller/
│ ├── AuthController.java
│ └── InventoryController.java
│
│── service/
│ ├── InventoryService.java
│ ├── EmailService.java
│ ├── ReportService.java
│ └── CustomUserDetailsService.java
│
│── repository/
│── model/
│── config/
│── templates/
│── static/
```

---

# ⚙ Installation & Setup

## 1️⃣ Clone Repository

```
git clone https://github.com/prajapativikram/Inventory-Monitoring-Reporting-System.git
```

## 2️⃣ Navigate to Project

```
cd inventory-management-system
```

## 3️⃣ Configure Database

Edit **application.properties**

```
spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
```

## 4️⃣ Run Application

```
mvn spring-boot:run
```

Or run `DemoApplication.java`.

## 5️⃣ Open Browser

```
http://localhost:8080
```

---

# 📸 Screenshots

```
Screenshots/
   dashboard.png
   login.png
   register.png
   analytics.png
```

Example:

```
Dashboard

<img width="1919" height="1031" alt="dashboard" src="https://github.com/user-attachments/assets/b2ec919d-88be-4d8a-8552-d4716790fa77" />

```

---

# 💡 Future Improvements

- Inventory analytics dashboard
- Real-time stock notifications
- Barcode scanning
- AI-based stock prediction
- REST API for mobile apps
- Multi-warehouse support

---

# 👨‍💻 Author

**Vikram Kumar**

Computer Science Engineer  
AI / ML Enthusiast | Software Developer

---

# ⭐ Contributing

```
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request
```

---

# 📜 License

This project is licensed under the **MIT License**.
