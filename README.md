# School Activity Club Management System (SACMS)

This repository contains the implementation of the School Activity Club Management System (SACMS) designed to facilitate the management of various extracurricular activity clubs within a school. The system includes functionalities for club creation, event scheduling, user registration, attendance tracking, and reporting. The project follows Unified Modeling Language (UML) and Object-Oriented Programming (OOP) concepts, with Java as the primary programming language.

## Project Overview

The SACMS aims to streamline the management of school clubs by providing tools for club advisors and students to efficiently handle club activities, events, and memberships.

## Features of the System

1. **Club Creation**:
    - Enable club advisors to create and manage club profiles.
2. **Event Scheduling**:
    - Allow club advisors to schedule events, meetings, and activities for their respective clubs.
3. **User Registration**:
    - Allow students to register with SACMS and select the clubs they wish to join.
4. **Attendance Tracking**:
    - Provide a mechanism for club advisors to track attendance at club events.
5. **Reporting**:
    - Generate reports on club membership, event attendance, and club activities.

## Project Phases

### Task 1: Requirements Analysis (Week 2-3)

1. **Define Scope**:
    - Identify stakeholders and their requirements.
2. **Use Case Diagram**:
    - Create a use case diagram with descriptions for essential functions.
3. **Essential Functions**:
    - Document essential functions including club creation, event scheduling, user registration, attendance tracking, and reporting.

### Task 2: System Design (Week 4-5)

1. **Core Classes**:
    - Develop the core classes for the SACMS system using OOP concepts.
2. **Database Layer**:
    - Implement a database layer using Java Database Connectivity (JDBC) to store and retrieve user data, club information, and attendance records.

### Task 3: Implementation (Week 6-8)

1. **User Interface**:
    - Create a suitable interface using Command Line Interface (CLI) for user inputs and outputs. Optionally, you may use Graphical User Interface (GUI) with Swing or JavaFX.
2. **Functionality Implementation**:
    - Implement the functionalities for club creation, event scheduling, user registration, attendance tracking, and reporting.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA 
- MySQL

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/nisal2002/Activity-Club-Management-System.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd Activity-Club-Management-System
   ```

3. **Open the project in IntelliJ IDEA or your preferred IDE.**

4. **Setup Database:**

   - Create a database for SACMS.
   - Configure the database connection settings in the project.

### Running the Application

1. **Compile and run the application:**

   ```bash
   javac MainApplication.java
   java MainApplication
   ```

2. **Interact with the system through the CLI or GUI (if implemented).**

## Project Structure

- **MainApplication.java**: The main Java file containing the entry point of the application.
- **models/**: Directory containing the core classes representing the entities in the system.
- **controllers/**: Directory containing the controllers for handling the business logic.
- **views/**: Directory containing the CLI or GUI components for user interaction.
- **database/**: Directory containing the database configuration and connection classes.

## UML Diagrams

- **Use Case Diagram**: Represents the interactions between the system and external actors.
- **Class Diagrams**: Illustrate the structure of the core classes and their relationships.

## Author

- [Author](nisal2002) - Initial work

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
