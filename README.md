# 🌎 Language / Idioma: [Português](README.pt-BR.md) | [English](README.md)

# 📚 Library Management System

This project implements a **Library Management System in Java**, allowing the management of **users, books, and loans**.

The system supports **creating, querying, updating, and removing records**, as well as controlling **active loans and returns**.

---

## 🚀 Features

### 📖 Book Management
- ➕ Add new books with full details (ISBN, title, author, publisher, year, quantity, category)
- 🔍 Search books by **ISBN**
- 📑 List all registered books
- ✏️ Update book information
- 🗑️ Remove books from the catalog
- 📊 Inventory control with available quantity tracking

### 👤 User Management
- ➕ Register users
- 🔍 Search users by **ID** or **name**
- 📑 List all users
- ✏️ Update user information
- 🗑️ Remove users

### 📦 Loan Management
- ➕ Register new loans
- 🔍 Search loans by **ID**
- 📑 List all loans
- 📌 List **active** loans
- 👥 Search loans by user
- 🗑️ Remove loans
- ⚠️ Exception handling for **user or book not found**

---

## 🏗️ Architecture

The system follows a **layered architecture**, organized into packages:

- **model** → Core entities
  - `Livro`
  - `Usuario`
  - `Emprestimo`

- **implementacao** → Business logic implementations
  - `LivroIMPL`
  - `UsuarioIMPL`
  - `EmprestimoIMPL`

- **interfaces** → Business rule contracts
  - `LivroRegras`
  - `UsuarioRegras`
  - `EmprestimoRegras`

- **exceptions** → Custom error handling
  - `EmprestimoException`
  - `UsuarioNaoEncontradoException`
  - `LivroNaoEncontradoException`

- **controllers** → Application control layer
  - `ControladoraLivros`
  - `ControladoraUsuarios`
  - `ControladoraEmprestimos`

- **Main.java** → Application entry point

---

## 🔁 CI/CD – Jenkins

This project includes a **Jenkins Continuous Integration pipeline** to automate build, testing, and quality validation processes.

The pipeline performs the following steps:

- Single project build using Maven
- Parallel execution of unit and integration tests
- Code coverage report generation using Jacoco
- Publication of test reports (JUnit)
- Packaging the application as a JAR file
- Build artifact archiving
- Email notifications with pipeline status updates

The pipeline uses **reusable Groovy scripts** to improve organization and maintainability.

---

## 📂 Project Structure

```bash
.
├── Jenkinsfile              
├── utils.groovy             
├── pom.xml                  
├── src
│   ├── main
│   │   └── java
│   │       ├── exceptions
│   │       ├── implementacao
│   │       ├── interfaces
│   │       ├── model
│   │       └── controllers
│   └── test                 
└── README.md
```


---
## ⚙️ Technologies Used
- ☕ **Java 17+ (21.0.9)**
- 📦 **Java Collections (List, HashMap, etc.)**
- 📅 java.time for date handling (LocalDate)
- ⌨️ Scanner for console input
- 🏗️ Design Principles: Interface Segregation, Dependency Injection
- ⚡ **Custom exceptions for clarity and error control**
- 🔧 **Maven for dependency management and build**
- 🔁 **Jenkins for CI/CD (build, testing, coverage, packaging)**
- 📊 **Jacoco for code coverage analysis**
- 🧪 **JUnit for automated testing**

---

