# Job Portal Backend API

A secure and scalable backend API for a Job Portal application, built with Spring Boot.  
This project provides REST endpoints for user authentication and job management, with JWT-based security, PostgreSQL persistence, and Redis caching support.

---

## Features

- User registration and login
- JWT-based authentication and authorization
- Secure API access with Spring Security
- Create, read, update, and delete job posts
- Search jobs by keyword (profile, description, tech stack)
- Redis caching for job listing performance
- PostgreSQL database integration using Spring Data JPA

---

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Security**
- **JWT (jjwt)**
- **Spring Data JPA (Hibernate)**
- **PostgreSQL**
- **Redis**
- **Maven**

---

## Project Structure

```text
src/main/java/com/example/demo
├── config
│   ├── JwtFilter.java
│   └── Securityconfig.java
├── controller
│   ├── Restcontroller.java
│   └── UserController.java
├── dto
│   └── JobPostDto.java
├── model
│   ├── JobPost.java
│   ├── User.java
│   └── userPrincipal.java
├── repo
│   ├── JobRepo.java
│   └── userRepo.java
├── service
│   ├── JobService.java
│   ├── JwtService.java
│   ├── MyUserDetailsService.java
│   └── UserService.java
└── PostmanAppApplication.java
```

---

## Prerequisites

Before running the project, make sure you have:

- JDK 21
- Maven
- PostgreSQL running locally
- Redis running locally

---

## Configuration

Edit:

`/home/runner/work/Job_portal/Job_portal/src/main/resources/application.properties`

Current key settings:

- `server.port=8087`
- PostgreSQL URL, username, password
- Redis host and port


---

## How to Run

```bash
./mvnw clean install
./mvnw spring-boot:run
```

Application runs at:

`http://localhost:8087`

---

## Run Tests

```bash
./mvnw test
```

> Note: In the current repository state, tests/build may fail because Maven cannot resolve `com.example:JWT:0.0.1-SNAPSHOT` from `pom.xml`.

---

## Authentication Flow

1. Register a user: `POST /api/users/register`
2. Login: `POST /api/users/login`
3. Receive JWT token
4. Send token in request header for protected endpoints:

```http
Authorization: ******
```

---

## API Endpoints

### User Endpoints
- `POST /api/users/register` → Register new user
- `POST /api/users/login` → Login and get JWT token
- `GET /api/users` → Get all users (authenticated)

### Job Endpoints (Authenticated)
- `GET /api/jobs` → Get all jobs
- `GET /api/jobs/{postid}` → Get job by ID
- `POST /api/jobs` → Create job
- `PUT /api/jobs` → Update job
- `DELETE /api/jobs/{postid}` → Delete job
- `GET /api/jobs/keyword/{keyword}` → Search jobs by keyword
- `GET /api/pushdata` → Insert sample job records

---

## Sample Login Request

```http
POST /api/users/login
Content-Type: application/json

{
  "username": "your_username",
  "password": "your_password"
}
```

---

## Troubleshooting

- **Database errors**: Verify PostgreSQL is running and credentials are correct.
- **Redis issues**: Check Redis server and host/port config.
- **401 Unauthorized**: Ensure JWT token is valid and sent in correct format.
- **Port already in use**: Change `server.port` in `application.properties`.

---

## Future Improvements

- Move secrets/config to environment variables
- Add Swagger/OpenAPI documentation
- Add validation and global exception handling
- Improve automated test coverage
- Add Docker support and CI/CD pipeline

---

## Contributing

1. Fork this repository
2. Create a feature branch
3. Commit your changes
4. Open a pull request

---

## License

This repository currently has no license file.  

