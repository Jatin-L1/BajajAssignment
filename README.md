# BFHL REST API - Spring Boot Application

A comprehensive REST API built with Spring Boot that processes arrays of mixed data types and returns categorized results.

## 🚀 Features

- **POST /bfhl**: Process arrays containing numbers, alphabets, and special characters
- **GET /bfhl**: Health check endpoint
- Comprehensive data categorization (odd/even numbers, alphabets, special characters)
- Custom string concatenation with alternating caps in reverse order
- Full test coverage with JUnit
- Docker support for easy deployment
- Render-ready configuration

## 📋 Requirements

- Java 21
- Maven 3.9+
- Docker (for containerized deployment)

## 🔧 Configuration

Before running, update your personal details in `BfhlServiceImpl.java`:

```java
private static final String FULL_NAME = "john_doe"; // Change to your name (lowercase, underscore-separated)
private static final String DOB = "17091999";       // Change to your DOB (ddmmyyyy)
private static final String EMAIL = "john@xyz.com"; // Change to your email
private static final String ROLL_NUMBER = "ABCD123"; // Change to your roll number
```

## 🏃 Running Locally

### Option 1: Using Maven

```bash
# Build the project
mvn clean package

# Run the application
java -jar target/JavaApi-0.0.1-SNAPSHOT.jar
```

The API will be available at `http://localhost:8080`

### Option 2: Using Maven Spring Boot Plugin

```bash
mvn spring-boot:run
```

### Option 3: Using Docker

```bash
# Build Docker image
docker build -t java-api-bfhl .

# Run container
docker run -p 8080:8080 java-api-bfhl
```

## 🧪 Testing

Run all tests:

```bash
mvn test
```

Run specific test class:

```bash
mvn test -Dtest=BfhlServiceImplTest
mvn test -Dtest=BfhlControllerTest
```

## 📡 API Endpoints

### POST /bfhl

**Request Body:**
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```

**Response (200 OK):**
```json
{
  "is_success": true,
  "user_id": "john_doe_17091999",
  "email": "john@xyz.com",
  "roll_number": "ABCD123",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

### GET /bfhl

Health check endpoint that returns "API is running"

## 📝 Test Cases

The project includes comprehensive test coverage:

- ✅ Example A: Mixed data with special characters
- ✅ Example B: Multiple special characters and numbers
- ✅ Example C: Only alphabetic strings
- ✅ Empty data array handling
- ✅ Null data handling
- ✅ Large numbers processing
- ✅ Only special characters
- ✅ Alternating caps concatenation logic
- ✅ Mixed case alphabets
- ✅ Controller endpoint tests

Use the `api-test.json` file for manual testing with tools like Postman or curl.

## 🚢 Deploying to Render

### Method 1: Using Render Dashboard

1. Create a new Web Service on Render
2. Connect your GitHub repository
3. Select "Docker" as the environment
4. Render will automatically detect the Dockerfile
5. Set environment variables if needed
6. Deploy!

### Method 2: Using render.yaml (Blueprint)

1. Push your code to GitHub
2. Go to Render Dashboard
3. Click "New" → "Blueprint"
4. Connect your repository
5. Render will detect `render.yaml` and configure everything automatically

### Environment Variables on Render

Render automatically provides the `PORT` environment variable, which the application uses via:
```properties
server.port=${PORT:8080}
```

## 🧪 Testing the Deployed API

### Using curl:

```bash
curl -X POST https://your-app.onrender.com/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["a", "1", "334", "4", "R", "$"]}'
```

### Using Postman:

1. Import the `api-test.json` file
2. Update the base URL to your Render URL
3. Run the test collection

## 📦 Project Structure

```
JavaApi/
├── src/
│   ├── main/
│   │   ├── java/com/example/JavaApi/
│   │   │   ├── controller/
│   │   │   │   └── BfhlController.java
│   │   │   ├── dto/
│   │   │   │   ├── BfhlRequest.java
│   │   │   │   └── BfhlResponse.java
│   │   │   ├── service/
│   │   │   │   ├── BfhlService.java (Interface)
│   │   │   │   └── BfhlServiceImpl.java
│   │   │   └── JavaApiApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/JavaApi/
│           ├── controller/
│           │   └── BfhlControllerTest.java
│           └── service/
│               └── BfhlServiceImplTest.java
├── Dockerfile
├── render.yaml
├── api-test.json
├── pom.xml
└── README.md
```

## 🎯 Logic Explanation

### Concatenation String Logic

The API concatenates all alphabetical characters in reverse order with alternating caps:

**Example:** Input `["a", "b", "c"]`
1. Collect all alpha chars: `['a', 'b', 'c']`
2. Reverse order: `['c', 'b', 'a']`
3. Alternate caps: `'C', 'b', 'A'`
4. Result: `"CbA"`

### Number Categorization

- Strings that can be parsed as numbers are categorized as odd/even
- All numbers are summed up
- Numbers are returned as strings in the response

### Alphabet Handling

- Single letters and full alphabetic strings are converted to uppercase
- All individual characters are collected for concatenation logic

### Special Characters

- Any character that is not alphanumeric is treated as a special character

## 🛠️ Technologies Used

- **Spring Boot 4.1.0** - Application framework
- **Spring Web** - REST API support
- **Jackson** - JSON serialization/deserialization
- **JUnit 5** - Testing framework
- **Maven** - Build tool
- **Docker** - Containerization

## 🐛 Error Handling

The API handles errors gracefully:
- Returns `is_success: false` for any processing errors
- Null data arrays are handled safely
- Empty arrays return empty result arrays
- All exceptions are caught and return valid response structure

## 👨‍💻 Author

Update with your details after modifying the constants in `BfhlServiceImpl.java`

