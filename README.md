#  Local Machine Password-Manager (Java Desktop App)

# Project Description
This project is a Java based password manager desktop application that is meant to run on a user's local machine (desktop, laptop). I did this project to apply key Cybersecurity topics such as user authentication , two-factor authentication, cryptography, etc... . 
It was a fun project to create since it was quite challenging at times to implement many of the features I included. There was a lot of trial and error involved, especially for the two-factor authentication implementation. I resorted to applying another Github user, Austin Delamar's, totp authentication library. His Github account is here if you would like to see the aforementioned code: https://github.com/amdelamar/jotp.

# Project Demonstration
[Watch the Demo](https://youtu.be/nFo8tihyBhY)



# **Key Features**


![PMFeatures](https://github.com/michl203/Password-Manager-/blob/main/PassManagerFeatures.drawio%20(2).png)


# Cybersecurity Principles 

**Cybersecurity Principles Implemented**

I followed the information security principles found in the CIA Triad which consists of these three principles:

![ciatriad drawio (1)](https://github.com/michl203/Password-Manager-/assets/110306237/7156444d-c3c2-433c-a392-53e0e1d4fea0)


| **Confidentiality** | **Integrity** | **Availability** |
|---------------------|---------------|------------------|
| Hashing and salting user login info for authentication purposes. This prevents unauthorized access to sensitive information. | Jar-signing the application's JAR file ensures that the application hasn't been tampered with, maintaining its integrity. | Using a local MySQL database server prevents issues related to remote connections, ensuring the system's availability is not affected by external factors. |
| Enforcing time-based one-time password (TOTP) 2FA using Google Authenticator adds an extra layer of security to the login process, ensuring that only authorized users gain access to sensitive data. | Using parameterized statements for JDBC SQL queries prevents the execution of malicious SQL statements, ensuring the integrity of database operations by preventing unauthorized access or modification of data. | Utilizing a local MySQL database ensures that database operations are conducted swiftly, enhancing the system's availability by reducing reliance on external servers and network connections. |
| Implementing AES-256 encryption for storing user passwords provides a high level of confidentiality by securing sensitive data. |  |  |


When designing this app I also referred to the OWASP Top 10 Desktop App Security Risks which can be found here: https://owasp.org/www-project-desktop-app-security-top-10/ 

Here is what I applied to in application based recommendations by OWASP:

![owasp drawio](https://github.com/michl203/Password-Manager-/assets/110306237/7b6e4cdb-cff8-4feb-a7f9-45dc494953ff)


# Skills Applied
![PMSkills](/PMSkills.drawio.png)


# Code Organization
![PMCodeOrg](/PMCodeOrg.drawio.png)

# Dependencies 
1. jotp 1.3.0.jar (Austin Delamar's Project https://github.com/amdelamar/jotp)
2. mysql-connector.jar (8.2.0) (MySQl connection and database operations)
3. rs2xml.jar (DbUtils)


# Future Features
**In order of importance**
- Password Strength Analyzer
- Change Password reminders
- JavaFX GUI (for modern look)
  
# Licensing Information
Attribution-NonCommercial 4.0 International (CC BY-NC 4.0)

This work is licensed under the Creative Commons Attribution-NonCommercial 4.0 International License. To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/4.0/ or send a letter to Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.

Feel free to: -Use my application if you'd like -Adapt it, and even fix parts

Copy and redistribute it
Under the following terms:

Attribution — You must give appropriate credit, provide a link to the license, and indicate if changes were made. You may do so in any reasonable manner, but not in any way that suggests the licensor endorses you or your use.
NonCommercial — You may not use the material for commercial purposes.




