# 🚀 QuizKit – Full Stack Quiz Platform

![GitHub repo size](https://img.shields.io/github/repo-size/swayamsankar/quizkit_Web)
![GitHub stars](https://img.shields.io/github/stars/swayamsankar/quizkit_Web?style=social)
![GitHub forks](https://img.shields.io/github/forks/swayamsankar/quizkit_Web?style=social)
![License](https://img.shields.io/badge/license-MIT-green)

---

## 🌐 Overview

**QuizKit** is a full-stack web application built using **Spring Boot + React** that enables users to take quizzes, track performance, and manage profiles. Admin users can manage quiz questions dynamically.

---

## ✨ Features

### 👤 User Features

* 🔐 JWT Authentication (Login/Register)
* 📝 Attempt quizzes
* 📊 View results & history
* 👤 Profile management (update info, password)

### 🛠️ Admin Features

* ➕ Add questions
* ❌ Delete questions
* ⚙️ Manage quiz content

---

## 🧱 Tech Stack

### 🔹 Backend

* Java
* Spring Boot
* Spring Security
* JWT Authentication
* Spring Data JPA (Hibernate)
* MySQL

### 🔹 Frontend

* React.js
* Axios
* JavaScript (ES6)
* CSS

---

## 📁 Project Structure

```
quizkit_Web/
├── quizapp (Spring Boot Backend)
└── quiz-frontend (React Frontend)
```

---

## ⚙️ Installation & Setup

### 🔹 Backend

```bash
cd quizapp
mvn spring-boot:run
```

Create your own `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/quizdb
spring.datasource.username=root
spring.datasource.password=your_password
```

---

### 🔹 Frontend

```bash
cd quiz-frontend
npm install
npm start
```

---

## 🔐 Authentication Flow

```
User Login → JWT Token Generated → Stored in LocalStorage
→ Sent in Headers → Backend Validates → Access Granted
```

---

## 🔄 API Endpoints

### 🔐 Auth

* POST /auth/register
* POST /auth/login
* POST /auth/forgot

### 📝 Quiz

* GET /questions
* GET /questions/random
* POST /questions/submit

### ⚙️ Admin

* POST /questions
* DELETE /questions/{id}

### 👤 Profile

* GET /profile
* PUT /profile

---

## 🖼️ UI Preview


<img width="1865" height="905" alt="image" src="https://github.com/user-attachments/assets/c76ece11-dd0a-48dd-a8e4-a4846bbb934f" />


### 🔐 Login Page

<img width="1830" height="902" alt="image" src="https://github.com/user-attachments/assets/be877f66-5b59-4da8-bd04-f28864f24f95" />


---

## 🧠 Concepts Used

* REST API Design
* JWT Authentication & Authorization
* Role-Based Access Control (RBAC)
* Axios Interceptors
* CORS Handling
* Secure Password Hashing (BCrypt)

---

## 🔒 Security Practices

* 🚫 Sensitive files ignored (`application.properties`)
* 🔐 Passwords hashed using BCrypt
* 🛡️ JWT-based secure API communication

---

## 🚀 Future Enhancements

* 📧 Email-based password reset
* 🏆 Leaderboard system
* 📸 Profile image upload
* 🌍 Deployment (AWS / Render / Vercel)

---

## 📌 Author

👨‍💻 Developed by **Swayam Sankar Nayak**

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
