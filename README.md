# 💰 SmartExpensePro - Expense Tracking System

![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-green?logo=springboot)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue?logo=mysql)
![JWT](https://img.shields.io/badge/Security-JWT-yellow?logo=jsonwebtokens)
![Swagger](https://img.shields.io/badge/API-Docs-Swagger-brightgreen?logo=swagger)

---

## 🧾 Overview

**SmartExpensePro** is a powerful backend REST API that allows users to track their daily expenses securely.  
It features **JWT-based authentication**, **expense CRUD operations**, and **automated expense history tracking** — built with **Spring Boot** and **MySQL**.

---

## 🚀 Features

✅ **User Authentication (JWT)** – Secure login & registration  
✅ **Add / Update / Delete / View Expenses**  
✅ **Automatic Expense History Logging**  
✅ **Swagger API Documentation**  
✅ **MySQL Database Integration**  
✅ **Exception Handling & Validation**  

---

## 🧩 Project Structure

SmartExpensePro/
├── src/
│ ├── main/
│ │ ├── java/com/shubham/expense/
│ │ │ ├── controller/
│ │ │ │ ├── AuthController.java
│ │ │ │ ├── ExpenseController.java
│ │ │ │ └── ExpenseHistoryController.java
│ │ │ ├── config/
│ │ │ │ ├── JwtFilter.java
│ │ │ │ ├── SecurityConfig.java
│ │ │ │ └── SwaggerConfig.java
│ │ │ ├── model/
│ │ │ │ ├── User.java
│ │ │ │ ├── Expense.java
│ │ │ │ └── ExpenseHistory.java
│ │ │ ├── repository/
│ │ │ │ ├── UserRepository.java
│ │ │ │ ├── ExpenseRepository.java
│ │ │ │ └── ExpenseHistoryRepository.java
│ │ │ ├── service/
│ │ │ │ ├── AuthService.java
│ │ │ │ ├── ExpenseService.java
│ │ │ │ └── ExpenseHistoryService.java
│ │ │ └── SmartExpenseProApplication.java
│ │ └── resources/
│ │ ├── application.properties
│ │ └── static/
│ └── test/
│ └── java/...
├── pom.xml
├── README.md


---

## ⚙️ Tech Stack

| Category | Technology |
|-----------|-------------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.2.x |
| **Database** | MySQL |
| **Security** | Spring Security + JWT |
| **Documentation** | Swagger (Springdoc OpenAPI) |
| **Build Tool** | Maven |
| **IDE** | IntelliJ / VS Code |

---

## 📡 API Endpoints

### 🔐 Authentication
| Method | Endpoint | Description |
|--------|-----------|-------------|
| `POST` | `/api/auth/register` | Register a new user |
| `POST` | `/api/auth/login` | Login and get JWT token |

---

### 💵 Expense Management
| Method | Endpoint | Description |
|--------|-----------|-------------|
| `POST` | `/api/expenses` | Add a new expense |
| `GET` | `/api/expenses` | Get all user expenses |
| `GET` | `/api/expenses/{id}` | Get single expense by ID |
| `PUT` | `/api/expenses/{id}` | Update expense |
| `DELETE` | `/api/expenses/{id}` | Delete expense |

---

### 🕘 Expense History
| Method | Endpoint | Description |
|--------|-----------|-------------|
| `GET` | `/api/history` | View all expense history |

---

## 🧰 How to Run Locally

### 1️⃣ Clone this repository
```bash
git clone https://github.com/your-username/SmartExpensePro.git
cd SmartExpensePro

2️⃣ Configure Database

Edit your application.properties file:

spring.datasource.url=jdbc:mysql://localhost:3306/expense_pro
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3️⃣ Run Application
mvn spring-boot:run

4️⃣ Open Swagger UI

Go to:
👉 http://localhost:9696/swagger-ui/index.html

🧠 Example Request (Add Expense)

POST /api/expenses
Header:
Authorization: Bearer <your_jwt_token>
Body:

{
  "amount": 1200.5,
  "category": "Food",
  "note": "Lunch at cafe",
  "date": "2025-10-28"
}

💾 Database Tables
Table	Description
users	Stores user credentials
expenses	Stores expense details
expense_history	Logs every expense change
👨‍💻 Author

🧑 Shubham Das
💼 Java Developer | Spring Boot | REST APIs | MySQL
🔗 https://www.linkedin.com/in/shubhamdas3511

⭐ Support

If you found this project helpful, please give it a star 🌟 on GitHub —
it really helps me grow and reach more developers!
