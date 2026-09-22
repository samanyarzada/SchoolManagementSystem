# School Management System

A desktop-based **School Management System** developed with Java and JavaFX.

The application provides a graphical interface for managing students, teachers, courses, classrooms, attendance, enrollment, schedules, and reports.

## Features

- Student management
- Teacher management
- Full-time and part-time teacher management
- Course management
- Classroom management
- Student enrollment
- Attendance management
- Timetable and scheduling
- Reports
- Dashboard
- Login screen
- JavaFX graphical user interface

## Technologies

- **Java 26**
- **JavaFX 26.0.1**
- **FXML**
- **VS Code**
- **Git & GitHub**

## Project Structure

```text
SchoolManagementSystem/
│
├── src/
│   ├── MainApp.java
│   ├── Main.java
│   ├── Student.java
│   ├── Teacher.java
│   ├── Course.java
│   ├── Classroom.java
│   ├── Attendance.java
│   ├── Enrollment.java
│   └── ...
│
├── lib/
│   ├── javafx.base.jar
│   ├── javafx.controls.jar
│   ├── javafx.fxml.jar
│   ├── javafx.graphics.jar
│   └── ...
│
├── .vscode/
│   ├── launch.json
│   └── settings.json
│
├── .gitignore
└── README.md
```

## Requirements

To run the project, you need:

- JDK 26
- JavaFX 26.0.1
- Windows
- Visual Studio Code with the Extension Pack for Java

## Running the Project

The project uses JavaFX 26.0.1.

Make sure the JavaFX SDK is available on your computer.

Example JavaFX SDK location:

```text
D:\Application\Computer Application\javafx
```

The SDK should contain:

```text
javafx/
├── bin/
└── lib/
```

The `bin` directory contains the required Windows native JavaFX libraries, while the `lib` directory contains the JavaFX JAR files.

### Run from Command Prompt

From the project directory:

```cmd
set PATH=D:\Application\Computer Application\javafx\bin;%PATH%

"C:\Program Files\Java\jdk-26\bin\java.exe" --enable-native-access=javafx.graphics --module-path "D:\Application\Computer Application\javafx\lib" --add-modules javafx.controls,javafx.fxml -cp ".\bin" MainApp
```

### Run from VS Code

Open the project in Visual Studio Code and use:

```text
Run and Debug → Run MainApp
```

The project contains VS Code configuration files in:

```text
.vscode/
```

## Notes

The `bin/` directory contains compiled `.class` files and is excluded from Git using `.gitignore`.

The JavaFX JAR files required by the project are included in the `lib/` directory.

## Author

**Saman Yarzada**

## License

This project is for educational and development purposes.