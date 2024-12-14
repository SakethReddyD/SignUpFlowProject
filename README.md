# SignUp Flow Automation Project

This project automates the process of creating an account, signing in, and testing various scenarios on the website: [https://magento.softwaretestingboard.com/](https://magento.softwaretestingboard.com/). The automation covers positive and negative test cases to ensure the robustness of the account creation and sign-in functionalities.

## Project Description
The project includes the following automated test cases:

### Test Case List

1. **TC1: Create Account**
   - Navigate to the website.
   - Verify that the Home Page is displayed.
   - Create an account with valid details.
   - Verify that the account is successfully created by checking the account details.

2. **TC2: Sign In to Account**
   - Sign in using valid credentials from TC1.
   - Verify the welcome message with the full name.

3. **TC3: Enter Invalid Names**
   - Test invalid first and last names during account creation.
   - Verify error messages such as "First Name is not valid" and "Last Name is not valid."

4. **TC4: No Names Entered**
   - Attempt to create an account without entering first and last names.
   - Verify that the required fields error message is displayed.

5. **TC5: Enter Existing Mail ID**
   - Attempt to create an account using an already registered email address.
   - Verify that the appropriate error message is displayed.

6. **TC6: Enter Invalid Mail**
   - Enter an invalid email format during account creation.
   - Verify that the "Invalid mail" error message is displayed.

7. **TC7: No Mail Entered**
   - Leave the email field empty during account creation.
   - Verify that the required fields error message is displayed.

8. **TC8: Enter Invalid Password**
   - Test passwords that are too short or lack the required character combinations.
   - Verify error messages for password length and complexity.

9. **TC9: Enter Mismatched Password**
   - Enter different values in the password and confirm password fields.
   - Verify that the "Password is not matched" error is displayed.

10. **TC10: Password Not Entered**
    - Leave the password field empty during account creation.
    - Verify that the required fields error message is displayed.

11. **TC11: Create Account from Sign In Page**
    - Navigate to the Create Account page from the Sign In page.
    - Successfully create an account and verify the account details.

12. **TC12: Enter Non-Existing User**
    - Attempt to sign in with a non-existent email.
    - Verify that the "The account sign-in was incorrect" error is displayed.

13. **TC13: Enter Wrong User in Sign In Page**
    - Use an invalid email format on the Sign In page.
    - Verify that the "Invalid mail" error message is displayed.

14. **TC14: Enter Wrong Password in Sign In Page**
    - Use an incorrect password to sign in.
    - Verify that the "The account sign-in was incorrect" error is displayed.

---

## Installation and Setup

### Prerequisites
1. Install [Git](https://git-scm.com/).
2. Install a programming language or framework suitable for automation (e.g., Python with Selenium, Java with TestNG, etc.).
3. Ensure browser drivers are installed for automation (e.g., ChromeDriver, GeckoDriver).

### Steps to Clone the Repository
1. Open a terminal or Git Bash.
2. Navigate to the directory where you want to clone the repository:
   ```bash
   cd /path/to/your/directory
   ```
3. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
4. Navigate to the project folder:
   ```bash
   cd SignUpFlowProject
   ```

### Running the Tests
1. Configure the test data (e.g., usernames, emails) in the appropriate configuration files.
2. Run the automation suite using your preferred test runner (e.g., pytest, TestNG).

---

## Test Execution
- Each test case is automated to perform specific steps and validate the results as described above.
- Logs and screenshots (if implemented) will be generated for debugging and reporting purposes.

---

## Contribution
If you wish to contribute to this project:
1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. Commit your changes and push them:
   ```bash
   git push origin feature/your-feature-name
   ```
4. Create a pull request.

---

## Contact
For any questions or feedback, please contact saketh.desu04@gmail.com
