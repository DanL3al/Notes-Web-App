# 📘 Remember It - Notes Application

A simple **notes app** with **user authentication** built using **Spring Boot + JWT + Cookie** and a frontend using **HTML/CSS/JavaScript**.

---

## 🧠 Features

- User registration
- Login with **JWT** generation and **Cookie** storage
- Route protection with Spring Security
- Create, list, and delete **personal notes**
- Persistent storage using **JPA/Hibernate**
- Responsive interface with modal note creation

---

## 🔐 JWT + Cookie Authentication

- The JWT token is generated on login and saved as a cookie in the frontend:

```js
document.cookie = `jwt=${data.token}; path=/; max-age=1800; SameSite=Lax`;
```

- The token is then sent in the headers for protected requests:

```js
fetch("/notes", {
  headers: {
    'Authorization': `Bearer ${getTokenFromCookie()}`
  }
});
```

- On the backend, the token is manually extracted and validated.

---

## 🚀 Technologies and Dependencies

### Backend

- **Java 17+**
- **Spring Boot 3.5.3**
- **Spring Security**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database (or MySQL optionally)**
- **JWT (io.jsonwebtoken)**

**Maven `pom.xml` dependencies:**

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
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
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

## 🧾 API Endpoints

| Method | Endpoint              | Auth Required | Description                  |
|--------|------------------------|----------------|------------------------------|
| POST   | `/auth/register`      | ❌             | Register a new user          |
| POST   | `/auth/login`         | ❌             | Login and receive a JWT      |
| GET    | `/notes`              | ✅             | List user's personal notes   |
| POST   | `/notes`              | ✅             | Create a new note            |
| DELETE | `/notes/{id}`         | ✅             | Delete a note by ID          |
| GET    | `/users/info`         | ✅             | Get user information         |

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

## ✅ Future Improvements

- ✅ Manual JWT validation
- ☐ Form validation (frontend)
- ☐ Animations and transitions
- ☐ PostgreSQL support
- ☐ Cloud deployment

---

## 👨‍💻 Author

DanL3al

---

## 📜 License

MIT
