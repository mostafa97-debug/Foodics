# Appium2_Project
<div align="center">


 #  *Appium 2 Mobile Test Automation*
##  *📱 End-To-End Order Scenario on Android App "Sauce Labs"📱*

<img width="201" alt="Screenshot 2024-10-04 211552" src="https://github.com/user-attachments/assets/14c14fe7-3427-4ff3-b4d4-ba5577c9e842">


### 📝 The main Frameworks included in the project:

Appium 2.0: For automating mobile application testing.

TestNG: As the testing framework to structure and run tests.

Allure Report: To generate and visualize test reports for better tracking and analysis.


### 🏗️ Project Structure:

Page Object Model (POM): Ensures test code is organized by separating page elements and actions from test cases.

Fluent Design: Encourages smooth and readable code flow, especially useful in tests involving multiple steps.

Data-Driven Framework: Allows tests to run with various inputs, enhancing coverage and flexibility.


 ### 🗃️ Documentation:
* **[Appium Docs](http://appium.io/docs/en/2.0/quickstart/)**.


 ### 🚧 Requirements:
Java JDK-17: Ensures compatibility with Appium 2.0.
Appium 2.0, Maven, and Android Studio Emulator: Required to set up and run tests in the Android environment.

 ### 🚀 Test Execution Steps:
 
1-Start the Appium server.
2-Launch the Android emulator.
3-Execute tests by running the testng.xml file.
4-Generate reports using Allure.


-  ### 📄 Allure Report: 
![Capture33](https://github.com/user-attachments/assets/acf7c4fe-bc3d-4ef4-88c2-9a689554f6ed)


- ### 🔍️ Covered Test case in this project :
### Test Scenario: Purchase a Product in Sauce Labs Mobile Application.
#### ✅Steps:

1-Launch the Sauce Labs mobile application.

2-Navigate to "Menu" and select "Login".

3-Log in using valid credentials.

4-Choose a product to purchase.

5-Add the selected product to the cart by clicking "Counter Plus" and then "Add to Cart".

6-Access the shopping cart through the "Cart Badge" icon.

7-Proceed to checkout, filling out the required checkout and payment information.

8-Complete the purchase by clicking "Place Order".

9-Confirm return to the home screen by clicking "Continue Shopping".

This approach ensures comprehensive coverage for a key user flow, from login to purchase completion, with checkpoints to validate each critical step.
