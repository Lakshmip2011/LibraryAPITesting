# LibraryAPI Testing Project

## Introduction
Here's what this glorified “Library Management System” façade actually does: it's a fully automated API-testing framework built using Java, TestNG, REST-Assured, and Maven. It tackles the mundane: CRUD operations for Users, Books, and Borrowed Books. Also throws in TestNG-generated reports—because apparently, we need visuals to feel accomplished

## Project Type
Backend / API testing framework

## Directory Structure
```markdown
LibraryAPITests/
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  └─ resources/
│  └─ test/
│     ├─ java/
│     │  ├─ base/
│     │  │  └─ BaseTest.java         # Common setup & teardown
│     │  ├─ tests/
│     │  │  ├─ BookTests.java        # API tests for Books
│     │  │  ├─ BorrowTests.java      # API tests for Borrow records
│     │  │  └─ UserTests.java        # API tests for Users
│     │  └─ ExtentManager.java       # Reporting utility
│     └─ resources/
├─ db.json                           # Mock database for JSON-Server
├─ pom.xml                           # Maven dependencies
├─ testng.xml                        # Test suite configuration
├─ test-output/                      # Auto-generated reports
├─ LICENSE
└─ README.md
```

## Video Walkthrough of the project
Attach a very short video walkthough of all of the features [ 1 - 3 minutes ]

## Video Walkthrough of the codebase
Attach a very short video walkthough of codebase [ 1 - 5 minutes ]

## Features
- API test coverage for Users, Books, and Borrowed Books
- CRUD operations tested for all modules
- BaseTest for consistent setup/teardown
- Extent Reports integration for detailed test results
- Easy scalability with modular test structure

## Design decisions or assumptions
- JSON-Server (db.json) is used to simulate the backend API
- Test data lives in the mock database file, keeping the framework lightweight
- No authentication layer added (assumes open APIs)
- Maven + TestNG chosen for easy CI/CD integration

## Installation & Getting started
Clone the repo and install dependencies:

```bash
git clone <repo-url>
cd LibraryAPITesting
mvn clean install
```
Start the mock API server:
```bash
json-server --watch db.json --port 3000
```
Run the tests:
```bash
mvn test
```

## Usage
- All test results will be available inside the test-output/ folder.
- Extent Reports provide detailed pass/fail summaries.

##Screenshots
LibraryAPITesting Test Report (https://drive.google.com/file/d/1dlGobycQc_IZsflw88Keifl51qunnzaz/view?usp=sharing), 
LibraryAPITesting Console Output (https://drive.google.com/file/d/1czJCEk6GJxey9F1pK9Zhfo1hnb2UlQS3/view?usp=sharing), 
LibraryAPITesting Extent Report (https://drive.google.com/file/d/1SZyFDQen-tk0T9_y1KafcXR1VYcPpLG7/view?usp=sharing)

## Credentials
None required – the APIs are open for testing.

## APIs Used
Internal—CRUD endpoints for:
- Users
- Books
- Borrowed Books
  Essentially self-contained—no hats tipped to external APIs.

## API Endpoints
Not explicitly documented, but implied via test names:
- Users:
  - POST /users – create a user
  - GET /users – list users
  - GET /users/{id} – fetch a user
  - PUT /users/{id} – update user
  - DELETE /users/{id} – delete user
- Books:
  - Mirror the above for /books
- Borrowing:
  - POST /borrowedBooks, GET /borrowedBooks, PUT /borrowedBooks/{id}, DELETE /borrowedBooks/{id}

## Technology Stack
- Java
- REST-Assured
- TestNG
- Maven
- JSON-Server (for mock API)
- Dummy JSON file (db.json)
