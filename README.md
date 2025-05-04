# Web Automation Framework for Swag Labs

This README provides instructions for setting up and running the web automation tests for the Swag Labs application.
This framework is built using Selenium WebDriver with Java and TestNG, following the Page Object Model (POM) design pattern.

## Setup Instructions

1.  **Prerequisites:**
    * **Java Development Kit (JDK):** Ensure you have Java installed on your machine. 
    * **Google Chrome:** The tests are configured to run on Google Chrome. Please ensure it is installed on your system.
    * **Maven:** This project uses Maven for dependency management.

2.  **Getting the Code:**
    * Clone this repository to your local machine using Git:
        ```bash
        git clone <repository_link>
        ```
        (Replace `<repository_link>` with the actual link to your GitHub/GitLab repository)
    * Alternatively, if you received a zip file, extract its contents to a directory on your machine.

3.  **Building the Project:**
    * Navigate to the root directory of the project in your terminal or command prompt.
    * Run the following Maven command to download dependencies and build the project:
        ```bash
        mvn clean install
        ```

## How to Run Tests

    **Running Tests via TestNG:**
    * The tests are designed to be executed using TestNG. You can run them in a few ways:
        * **From the Command Line (Maven):** Navigate to the project root directory and execute the following Maven command:
            ```bash
            mvn test
            ```
        * **From an IDE (e.g., IntelliJ IDEA, Eclipse):**
            * Import the project as a Maven project.
            * Locate the `testng.xml` file (usually in the `src/test/resources` directory).
            * Right-click on the `testng.xml` file and select "Run" or "Run As TestNG Suite".

## Summary of Framework Design Decisions

This automation framework follows the **Page Object Model (POM)** design pattern. This approach creates a separate class for each web page of the application under test. These page classes contain the locators for the elements within that page and the methods to interact with those elements.

**Key Design Decisions:**

* **Page Object Model (POM):** Implemented for better code organization, maintainability, and reusability. Each page in the Swag Labs application (e.g., Login Page, Products Page, Cart Page, Checkout Pages) has its corresponding Page Object class.
* **Selenium WebDriver:** Used as the core library for interacting with the web browser and its elements.
* **TestNG:** Chosen as the test framework for its rich features like test annotations,and reporting capabilities.
* **Test Reporting:** The framework includes test reporting using ExtentReports. These reports provide detailed information about the test execution status.
* **Logging:** Basic logging is implemented using Log4j to track the execution flow and any errors encountered during the tests.

## Functional Test Coverage

The following functional test cases are automated:

1.  **Login with valid credentials:**
    * Verifies that a user can successfully log in with the username `standard_user` and password `secret_sauce` and is redirected to the products page.
2.  **Login with invalid credentials:**
    * Verifies that an error message appears when attempting to log in with the username `locked_out_user` and password `secret_sauce`.
3.  **Add a product to the cart:**
    * After successful login, adds a product to the shopping cart.
    * Validates that the cart displays the correct item count (should be 1).
4.  **Checkout flow:**
    * Completes the entire checkout process by providing mock customer data (e.g., first name, last name, postal code).
    * Validates that the final order confirmation screen is displayed.


## Submission Guidelines

* The code for this automation framework is available in this [GitHub/GitLab repository](<repository_link>) or provided as a zip file.
* All necessary setup and execution instructions are included in this README file.
* The framework is designed to run on a clean machine with only Java and Chrome installed.
