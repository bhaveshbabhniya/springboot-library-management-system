# 📚 Library Management System

This is a role-based Library Management System built using **Spring Boot 3**, **JWT Authentication**, **Spring Security 6**, and **MySQL**. It supports two roles: `MEMBER` and `LIBRARIAN`, each with different access levels.

📁 Project Path: [`/library_management_system`](https://github.com/bhaveshbabhniya/springboot_library_management_system)

---

## 🚀 Features

- 🔐 Login/Register with JWT token generation
- 👥 Role-based access using Spring Security 6
- 📘 Members can view all books
- 📤 Librarians can upload new books
- 🔁 Promote/Demote users to members/librarians via admin API
- 📑 Swagger UI integrated for API documentation with JWT support

---

## 🛠️ Tech Stack

- Java 17
- **Spring Boot 3**
- **Spring Security 6**
- **JWT (JSON Web Token)**
- **MySQL**
- **Swagger UI (springdoc-openapi)**
- **Lombok**
- **Hibernate + JPA**

---

## 📂 Project Structure
```
springboot-library-management-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── springboot/
│   │   │           └── library_management_system/
│   │   │               ├── config/
|   |   |               |   └── SwaggerConfig.java
|   |   |               |
|   |   |               ├── controller/
│   │   │               │   ├── AdminController.java
|   |   |               |   ├── AuthController.java
|   |   |               |   └── BookController.java
│   │   │               │
│   │   │               ├── dto/
│   │   │               │   └── AuthRequest.java
│   │   │               │
│   │   │               ├── entity/
|   |   |               |   ├── Book.java
|   |   |               |   ├── Role.java
│   │   │               │   └── User.java
│   │   │               │
│   │   │               ├── repository/
|   |   |               |   ├── BookRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               │
│   │   │               ├── security/
│   │   │               │   ├── CustomUserDetailsService.java
|   |   |               |   ├── JwtAuthenticationFilter.java
|   |   |               |   ├── JwtService.java
│   │   │               │   └── SecurityConfig.java
│   │   │               │
│   │   │               └── LibraryManagementSystemApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
├── pom.xml
└── README.md
```
---

## ⚙️ Setup Instructions

- Java 17+
- Maven 3.6+
- MySQL Server
- Swagger (for API testing)
- Git (optional, for cloning)

### 1. Clone the Repository
```bash
git clone https://github.com/bhaveshbabhniya/springboot_library_management_system.git
cd springboot-library-management-system
```

### 2. Create database in MYSQL
```bash
CREATE DATABASE librarydb;
```
Update your application.properties file with your MySQL credentials:
```
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. ▶️ Run the Application
```bash
./mvnw spring-boot:run
```
Or in Eclipse IDE, run LibraryManagementSystemApplication.java as a Java application.

---

## 📫 API Endpoints
```
POST | /api/auth/register             #Register new user (Role : Public)
POST | /api/auth/login                #Login and get JWT (Role : Public)
GET  | /api/books                     #View all books (Role : MEMBER/LIBRARIAN)
POST | /api/books/upload              #Upload new books (Role : LIBRARIAN)
POST | /api/admin/promote/{username}  #Promte MEMBER to LBRARIAN role (Role : LIBRARIAN)
POST | /api/admin/demote/{username}   #Demote LIBRARIAN to MEMBER role (Role : LIBRARIAN)
```
### 🧪 Sample JSON for Testing
Register new user
```
POST /api/auth/register
Body: {
  "username": "john",
  "password": "1234"
}
```
Login to get JWT Token:
```
POST /api/auth/login
Body: {
  "username": "john",
  "password": "1234"
}
Response: {
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```
Use this token in Swagger or Postman:
```
Authorization: Bearer <your-token>
```

## 📘 Swagger UI

Access API documentation and test requests from browser:
```
http://localhost:8080/swagger-ui.html
```
1. Click **"Authorize"** button
2. Enter your token:
   ```
   Bearer <your-token>
   ```
3. Test secure endpoints
---

# 📌 Notes
- Lombok must be installed and enabled in your IDE.
- Make sure MySQL is running and accessible.
- Consider securing the endpoints for production use.

---

# 👤 Author
  Bhavesh Babhniya
🔗 [`GitHub`](https://github.com/bhaveshbabhniya)
