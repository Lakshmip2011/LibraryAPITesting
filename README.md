# Library API Testing Project

Automated testing framework for a **Library Management System** using:

- Java  
- REST Assured  
- TestNG  
- Maven  

---

## Features

- CRUD operations for **Users**, **Books**, and **Borrowed Books**  
- Page Object-like separation with `BaseTest` for setup/teardown  
- Test cases for:
  - Users: Create, Read, Update, Delete  
  - Books: Create, Read, Update, Delete  
  - Borrow: Borrow Book, Read Borrowed Books, Update Borrow Status, Delete Borrow Record  
- TestNG reports for execution results  

---

## Project Structure

- `src/test/java` → Test classes (BookTests, UserTests, BorrowTests)
- `src/test/java/base` → BaseTest.java (setup/teardown)
- `db.json` → Mock database for JSON Server
- `pom.xml` → Maven dependencies
- `testng.xml` → TestNG suite configuration
- `test-output/` → Auto-generated reports (ignored in GitHub)
- `.gitignore` → Ignore compiled files and reports
- `README.md` → Project documentation

---

## How to Run

1. **Clone the repository**  

2. **Start JSON Server**  
```bash
json-server --watch db.json --port 3000
