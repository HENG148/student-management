# 📚 Student Management System

A terminal-based Student Management System built with **pure Java**, following **Object-Oriented Programming (OOP)** principles. Runs entirely in PowerShell, Command Prompt, or any terminal — no frontend UI required.

---

## 📸 Preview

```
  *************************************************************
  *        STUDENT MANAGEMENT SYSTEM  v2.0                   *
  *        Pure Java  |  OOP  |  File Persistence            *
  *************************************************************

  +---------------------------------------+
  |             MAIN MENU                 |
  +---------------------------------------+
  |   1.  Add Student                     |
  |   2.  Update Student                  |
  |   3.  Delete Student                  |
  |   4.  Search Student                  |
  |   5.  View All Students               |
  |   6.  Save Data to File               |
  |   7.  Load Data from File             |
  |   8.  Exit                            |
  +---------------------------------------+
```

---

## 📁 Project Structure

```
StudentManagementSystem/
├── src/
│   ├── Main.java                          ← Entry point & menu loop
│   ├── model/
│   │   └── Student.java                   ← Student data model
│   ├── repository/
│   │   └── StudentRepository.java         ← Shared student list (single source of truth)
│   ├── service/
│   │   ├── AddStudentService.java         ← Add student feature
│   │   ├── UpdateStudentService.java      ← Update student feature
│   │   ├── DeleteStudentService.java      ← Delete student feature
│   │   ├── SearchStudentService.java      ← Search student feature
│   │   └── ViewStudentService.java        ← View all students feature
│   └── utils/
│       ├── FileHandler.java               ← Save & load data to/from file
│       ├── InputHelper.java               ← Shared user input methods
│       └── PrintHelper.java               ← Shared console rendering methods
├── data/
│   └── students.txt                       ← Auto-created when you save data
└── out/                                   ← Compiled .class files (auto-created)
```

---

## ✨ Features

| # | Feature | Description |
|---|---------|-------------|
| 1 | **Add Student** | Add a new student with full validation |
| 2 | **Update Student** | Edit any field of an existing student |
| 3 | **Delete Student** | Remove a student after confirmation |
| 4 | **Search Student** | Search by ID, Name, Course, or Email |
| 5 | **View All Students** | Display all students in a formatted table |
| 6 | **Save Data** | Save all students to `data/students.txt` |
| 7 | **Load Data** | Load students from `data/students.txt` |
| 8 | **Exit** | Cleanly exit the application |

---

## 👤 Student Fields

Each student record contains:

| Field | Type | Validation |
|-------|------|------------|
| Student ID | String | Must be unique, cannot be empty |
| Full Name | String | At least 2 characters |
| Age | Integer | Between 5 and 100 |
| Gender | String | Male, Female, or Other only |
| Course | String | Cannot be empty |
| Email | String | Must match `user@domain.com` format |

---

## 🏗️ Architecture

```
Main.java
  ├── StudentRepository   ← one shared ArrayList for the whole app
  ├── InputHelper         ← one shared Scanner wrapper
  ├── PrintHelper         ← shared table & header printing
  └── Services (each owns one feature):
        AddStudentService
        UpdateStudentService
        DeleteStudentService
        SearchStudentService
        ViewStudentService
```

### Why this structure?

| Principle | How it's applied |
|-----------|-----------------|
| **Single Responsibility** | Each service class does exactly one thing |
| **DRY** (Don't Repeat Yourself) | `InputHelper` and `PrintHelper` shared across all services |
| **Encapsulation** | All `Student` fields are private, accessed via getters/setters |
| **Separation of Concerns** | Model / Repository / Service / Utils / Main are fully separated |

---

## 🗂️ Data File Format

Data is saved as a human-readable pipe-delimited text file at `data/students.txt`:

```
# Student Management System - Data File
# Format: studentId|fullName|age|gender|course|email
STU001|Alice Johnson|22|Female|Computer Science|alice@example.com
STU002|Bob Martinez|24|Male|Data Engineering|bob@example.com
```

- Lines starting with `#` are comments — ignored on load
- Pipe `|` delimiter avoids conflicts with commas in names or courses

---

## 🚀 Getting Started

### Prerequisites

- Java JDK 11 or later — download from [https://adoptium.net](https://adoptium.net)

Verify your installation:

```powershell
java -version
javac -version
```

---

### Installation

1. Clone or download this repository
2. Open **PowerShell** or **Command Prompt**
3. Navigate to the project folder:

```powershell
cd path\to\StudentManagementSystem
```

---

### Compile

```powershell
mkdir out

javac -d out -sourcepath src (Get-ChildItem -Recurse -Filter *.java src | % { $_.FullName })
```

Or manually list each file:

```powershell
javac -d out -sourcepath src `
  src\Main.java `
  src\model\Student.java `
  src\repository\StudentRepository.java `
  src\service\AddStudentService.java `
  src\service\UpdateStudentService.java `
  src\service\DeleteStudentService.java `
  src\service\SearchStudentService.java `
  src\service\ViewStudentService.java `
  src\utils\FileHandler.java `
  src\utils\InputHelper.java `
  src\utils\PrintHelper.java
```

---

### Run

```powershell
java -cp out Main
```

---

## 💡 VS Code Tips

If you're using **Visual Studio Code**, you may see these hints — they are **NOT errors**:

| Hint | Meaning | Action needed? |
|------|---------|----------------|
| `Field can be final` | Suggests adding `final` keyword | ❌ None |
| `Convert switch to rule switch` | Suggests arrow `->` syntax | ❌ None |
| `Can be converted to text block` | Suggests `"""` string syntax | ❌ None |

These are optional style suggestions. Your code compiles and runs perfectly without them.

---

## 📋 Example Session

```
Enter your choice (1-8): 1

  ============================================================
  ==  ADD NEW STUDENT                                       ==
  ============================================================

  Enter Student ID (e.g. STU001): STU001
  Enter Full Name: Alice Johnson
  Enter Age (5-100): 22
  Enter Gender (Male / Female / Other): Female
  Enter Course: Computer Science
  Enter Email: alice@example.com

  [OK] Student 'Alice Johnson' added successfully!

─────────────────────────────────────────────────────────────

Enter your choice (1-8): 5

  ============================================================
  ==  ALL STUDENTS                                          ==
  ============================================================

  Total: 2 student(s)

  +------------+------------------------+-----+----------+----------------------+------------------------------+
  | Student ID | Full Name              | Age | Gender   | Course               | Email                        |
  +------------+------------------------+-----+----------+----------------------+------------------------------+
  | STU001     | Alice Johnson          | 22  | Female   | Computer Science     | alice@example.com            |
  | STU002     | Bob Martinez           | 24  | Male     | Data Engineering     | bob@example.com              |
  +------------+------------------------+-----+----------+----------------------+------------------------------+
```

---

## 🔧 Common Errors & Fixes

| Error | Cause | Fix |
|-------|-------|-----|
| `javac is not recognized` | Java not installed or not in PATH | Reinstall JDK and check "Set JAVA_HOME" during setup |
| `package model does not exist` | Running `javac` from wrong folder | Run from the `StudentManagementSystem/` root, not inside `src/` |
| `could not find or load main class` | Missing `-cp out` flag | Use `java -cp out Main` not just `java Main` |
| `error: file not found` | Wrong file path | Double-check folder structure matches the layout above |

---

## 🚧 Future Improvements

- [ ] **MySQL / SQLite database** — swap `FileHandler` for a JDBC `DatabaseHandler`
- [ ] **Sort students** — sort by name, age, or course using `Comparator`
- [ ] **Pagination** — show 10 students per page for large datasets
- [ ] **Export to CSV / JSON** — additional export formats
- [ ] **JUnit 5 tests** — unit tests for all services and validation logic
- [ ] **Grade tracking** — add a `List<Grade>` field and GPA calculator
- [ ] **Login system** — Admin vs Viewer roles

---

## 🛠️ Built With

- **Java** — pure Java, no frameworks or external libraries
- **OOP principles** — Encapsulation, Separation of Concerns, DRY
- **File I/O** — `BufferedReader` / `BufferedWriter` for persistence
- **Regex** — email validation via `java.util.regex.Pattern`
- **ArrayList** — in-memory student storage via `java.util.ArrayList`

---

## 📄 License

Free to use and modify for educational purposes.
