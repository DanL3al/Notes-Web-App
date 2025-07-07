# 📘 Remember It - Notes Application

Remember it is a web application built with Spring Boot for managing personal notes. It features secure authentication using JWT and cookies, user registration and login, note creation and editing, and full session handling using Spring Security

---

## 🧠 Features

- User registration
- Login with **JWT** generation and **Cookie** storage
- Route protection with Spring Security
- Create, list, and delete **personal notes**
- Persistent storage using **JPA/Hibernate**
- Responsive interface with modal note creation

---
## Technologies

- **Java 17**
- **Spring Boot 3.5.3**
- **Spring Security**
- **Spring Web**
- **Spring Data JPA**
- **MySQL**
- **JWT (io.jsonwebtoken)**
- **Thymeleaf**

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/DanL3al/Notes-Web-App.git
```

### 2. MySQL Setup

- Create a database named `notesapp`
- Example SQL:
```sql
CREATE DATABASE notesapp;
```

### 3. Configure `application.properties`

Edit the file `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/notesapp?useSSL=false&serverTimezone=UTC
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.sql.init.mode=always
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

server.port=8888
```

### 4. Run the Application

```bash
./mvnw spring-boot:run
```

Or open the project in IntelliJ and run the main class.

### 5. Access the App

Open in your browser:

```
http://localhost:8888/
```

## Using the App

1. Go to `/` and register a new user.
2. Log in to generate a JWT stored in a cookie.
3. You'll be redirected to the notes page.
4. Create, edit, or delete notes.

## Authentication Details

- JWTs are generated after login and stored in cookies.
- Protected endpoints (like `/notes`) require a valid token in the `Authorization` header.
- Cookies are set via `document.cookie` on the frontend.

### JWT Details

- The token includes the user's ID and username.
- Tokens expire after 30 minutes.

### Spring Security

- Stateless session management
- Custom authentication filter to extract JWT from header
- Exception handling for unauthorized requests

## Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.11.5</version>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

---

## 💻 Frontend

- Built with **HTML, CSS and JavaScript**
- Modal for creating notes
- Fetches and displays user's name after login
- JWT is stored in cookies
- Authenticated requests made with `fetch`

---

## 🧼 Logout

The "Exit" button clears the cookie and redirects to the login page:

```js
document.cookie = 'jwt=; path=/; expires=Thu, 01 Jan 1970 00:00:00 UTC';
window.location.href = '/';
```

---

## 👨‍💻 Author

DanL3al

---

## 📜 License

MIT
