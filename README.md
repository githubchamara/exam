# Hello World API – Java Assignment

## Overview
This is a simple HTTP API implemented in Java using Spring Boot. It exposes an endpoint that validates a `name` query parameter and responds according to the first letter of the name.

---

## Endpoint

**GET** `/hello-world?name=<your-name>`

### Rules

1. If the first letter of the name is in the first half of the English alphabet (A–M or a–m):
   - HTTP Status: **200 OK**
   - Response:
   ```json
   { "message": "Hello Alice" }
   ```

2. If the first letter is in the second half (N–Z or n–z), or if the name is missing/empty:
   - HTTP Status: **400 Bad Request**
   - Response:
   ```json
   { "error": "Invalid Input" }
   ```

---

## How to Run the Application

### Prerequisites
- Java 11 or higher
- Maven 3+

### Steps
1. Open a terminal in the project root directory.
2. Run the application:
```bash
mvn spring-boot:run
```
3. The application will start at:
```
http://localhost:8080
```
4. Test the endpoint using:
```
http://localhost:8080/hello-world?name=alice
```

---

## How to Run Tests

Run all unit tests:
```bash
mvn test
```

Tests cover:
- Valid inputs (A–M)
- Invalid inputs (N–Z)
- Missing or empty name values
- Edge-case behavior

---

## Assumptions Made
- Input must be a non-empty string.
- Only English alphabet characters are considered.
- Logic is case-insensitive.
- If the first letter is not A–Z (e.g., symbols/numbers), it is treated as invalid.
- Responses always follow JSON format.

---



## Solution Rationale

- **Spring Boot** chosen for simplicity and minimal setup.
- **Controller** handles HTTP request/response formatting.
- **Service** contains business logic (`isFirstHalf`) for testability.
- **Unit tests** written using JUnit 5 + MockMvc to validate all scenarios.
- Project follows clean, maintainable structure suitable for small APIs.

---

## 5-Minute Walkthrough

1. **HelloWorldController**
   - Exposes GET `/hello-world`.
   - Reads `name` as a request parameter.
   - Validates it using the service layer.
   - Returns either `{ "message": "Hello <Name>" }` or `{ "error": "Invalid Input" }`.

2. **HelloWorldService**
   - Contains the method `isFirstHalf(char c)`.
   - Determines whether the first character belongs to A–M (case-insensitive).

3. **Models**
   - Simple response classes for clean JSON output.

4. **Tests**
   - `HelloWorldControllerTest` verifies valid/invalid/missing input scenarios.
   - Uses `@WebMvcTest` + `@MockBean` to isolate controller behavior.

---

## Example Requests

### Valid (200 OK)
```
GET http://localhost:8080/hello-world?name=alice
```
Response:
```json
{ "message": "Hello Alice" }
```

### Invalid (400 Bad Request)
```
GET http://localhost:8080/hello-world?name=zara
```
Response:
```json
{ "error": "Invalid Input" }
```

### Missing Parameter
```
GET http://localhost:8080/hello-world
```
Response:
```json
{ "error": "Invalid Input" }
```

---



