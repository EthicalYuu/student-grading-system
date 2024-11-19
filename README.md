# Student Grading System

> ⚠️ **Important:** This README provides a high-level overview of the project. For comprehensive details, including architecture, design decisions, and implementation, please refer to the [Project Documentation (PDF)](./Student%20Grading%20System%20Documentation).

The **Student Grading System** is a comprehensive project developed to evaluate and demonstrate proficiency in various Java frameworks and backend development technologies. This system evolves through three distinct stages: command-line implementation, traditional MVC web application, and Spring MVC/Spring REST integration.

## Table of Contents

- [Objective](#objective)
- [Features](#features)
- [System Architecture](#system-architecture)
  - [Command-Line & JDBC Backend](#command-line--jdbc-backend)
  - [Traditional MVC Servlets and JSPs](#traditional-mvc-servlets-and-jsps)
  - [Spring MVC and Spring REST](#spring-mvc-and-spring-rest)
- [Technologies Used](#technologies-used)
- [Usage](#usage)
- [Future Enhancements](#future-enhancements)

## Objective

The primary goal of this project is to build a robust **Student Grading System** that progresses across three stages of implementation:
1. **Command-Line & JDBC Backend Implementation**
2. **Traditional MVC Servlets and JSPs Implementation**
3. **Spring MVC and Spring REST Implementation**

Each stage emphasizes learning and applying relevant technologies to enhance development skills and project scalability.

## Features

- Secure user authentication with role-based access control (Admin, Teacher, Student).
- Real-time communication using socket programming.
- Database interaction using JDBC and JPA/Hibernate.
- Dynamic web pages using JSP and Spring MVC.
- Statistical analysis of grades (e.g., class averages, medians, and more).
- Scalable architecture adaptable to future enhancements.

## System Architecture

### Command-Line & JDBC Backend
- **Client-Server Architecture**: Handles user login, role verification, and permissions.
- **Database Operations**: Manage student data, courses, and grades using JDBC.
- **Real-Time Communication**: Socket communication between client and server.

### Traditional MVC Servlets and JSPs
- **Model**: Encapsulates business logic for courses, grades, and users.
- **View**: JSP pages for user interaction.
- **Controller**: Servlets manage user requests and responses.

### Spring MVC and Spring REST
- **Spring MVC**: Enhanced structure for handling user interactions and HTTP requests.
- **Spring REST**: Provides APIs for external integrations.

## Technologies Used

- **Programming Language**: Java
- **Frameworks**: Spring MVC, Spring REST, Hibernate
- **Database**: MySQL
- **Frontend**: HTML, JSP
- **Other Tools**: Tomcat Server, JPA, Socket Programming

## Usage

### Login
Use appropriate credentials for Admin, Teacher, or Student roles.

### Admin Features
- Create/Delete Users
- Manage Courses

### Teacher Features
- Modify Grades
- View Assigned Courses

### Student Features
- View Grades for Registered Courses
  
## Future Enhancements
- Add advanced grading configuration for teachers.
- Enable parent role for tracking student progress.
- Introduce statistical visualizations for grades.
- Expand administrative capabilities to manage large datasets.

## Documentation
For detailed documentation on the system's architecture, design, and implementation, please refer to the [Project Documentation (PDF)](./Student%20Grading%20System%20Documentation).
