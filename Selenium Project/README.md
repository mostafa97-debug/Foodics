# Amazon Test Automation Framework

## Overview
A Selenium TestNG-based automation framework designed to automate Amazon shopping scenarios using the Page Object Model (POM) for maintainability and a properties file for data-driven testing.

## Tech Stack
- **Selenium WebDriver** - Browser automation
- **TestNG** - Test execution and assertions
- **Java** - Test script development
- **Page Object Model (POM)** - Structured automation architecture
- **Properties File** - Test data management

## Automated Scenario
1. Open Amazon Egypt and login
2. Open "All" menu → "Video Games" → "All Video Games"
3. Apply filters:
   - Free Shipping
   - Condition: New
4. Sort results by "Price: High to Low"
5. Add all products below 15,000 EGP to cart (paginate if none found)
6. Verify cart contents

## Project Structure
```bash
src/
  main/
    java/
      org/
        pages/       # Page classes (POM)
        utils/       # Utilities (DriverManager)
  test/
    java/
      org.foodics/   # Test classes
        AmazonTest.java
    resources/
      credentials.properties  # Test data
pom.xml              # Maven dependencies
```

## Setup & Execution

### Prerequisites
- JDK 8+
- Maven
- Google Chrome & ChromeDriver

### Configuration
1. Create `credentials.properties`:
```properties
amazon.username=your-email@example.com
amazon.password=yourpassword
```

### Run Tests
```bash
mvn clean test
```

## Reporting
- **TestNG Reports**: `test-output/` directory
- **Logs/Screenshots**: `/target` directory

## Future Enhancements
- [ ] Add Cucumber BDD support
- [ ] Integrate Allure reporting
- [ ] Implement parallel execution
- [ ] Enhance logging mechanism

*Author: Mostafa Mansour*  
*Date: 14-3-2025*