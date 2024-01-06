#  Local Machine Password-Manager

# Project Description
This project is a Java based password manager desktop application that can run on a user's local machine. 


# Cybersecurity Principles 

Cybersecurity Principles Implemented

I followed the information security principles found in the CIA Triad which consists of these three principles:


| **Confidentiality** | **Integrity** | **Availability** |
|---------------------|---------------|------------------|
| Hashing and salting user login info for authentication purposes. This prevents unauthorized access to sensitive information. | Jar-signing the application's JAR file ensures that the application hasn't been tampered with, maintaining its integrity. | Using a local MySQL database server prevents issues related to remote connections, ensuring the system's availability is not affected by external factors. |
| Enforcing time-based one-time password (TOTP) 2FA using Google Authenticator adds an extra layer of security to the login process, ensuring that only authorized users gain access to sensitive data. | Using parameterized statements for JDBC SQL queries prevents the execution of malicious SQL statements, ensuring the integrity of database operations by preventing unauthorized access or modification of data. | Utilizing a local MySQL database ensures that database operations are conducted swiftly, enhancing the system's availability by reducing reliance on external servers and network connections. |
| Implementing AES-256 encryption for storing user passwords provides a high level of confidentiality by securing sensitive data. | - | - |


When designing this app I also referred to the OWASP Top 10 Desktop App Security Risks which can be found here: https://owasp.org/www-project-desktop-app-security-top-10/ 

Here is what I applied to my application from the recommendations:

![OWASP Diagram]("C:\Users\apamb\Downloads\owasp.drawio.png")


# Features


# Technology used


# Usage 


