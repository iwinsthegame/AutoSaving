 Auto-Savings & Investment Calculation System for Retirement (BlacRock Hackathon)

  **Overview**

  This project implements an auto-saving and investment system inspired by a retirement auto-investment challenge.


**Auto Savings system:**

   Converts daily expenses into micro-savings using round-up logic

   Applies configurable temporal financial rules (q → p → k)

   Groups savings over evaluation periods

   Calculates long-term investment returns using:

   Index Fund

   NPS (National Pension Scheme)

   Exposes clean REST APIs

   Is fully Dockerized and runs on the required port 5477



**Tech Stack**

  Language: Java 17

  Framework: Spring Boot

  Build Tool: Maven

  Architecture: Layered (Controller → Service → Model / Utils)

  Data Processing: In-memory (no database for MVP)

  Containerization: Docker

  Port: 5477



**High-Level Flow**

Expenses are parsed and rounded up to the next multiple of 100.

The rounding difference (“remanent”) is treated as savings.

Savings are processed using temporal rules:

q rules → fixed override

p rules → extra addition

k rules → grouping / evaluation

Grouped savings are invested using:

Index Fund (14.49% annual return)

NPS (7.11% annual return + tax benefit)

Returns are adjusted for inflation.



  **API Endpoints**

1. Parse Expenses

POST

/blackrock/challenge/v1/transactions/parse

Converts expenses into transactions with ceiling and remanent.


2. Apply Temporal Rules (q + p + k)

POST

/blackrock/challenge/v1/transactions/filter

Applies q → p → k rules and returns total savings per k period.


3. Index Fund Returns

POST

/blackrock/challenge/v1/transactions/returns/index

Calculates long-term Index Fund returns with inflation adjustment.


4. NPS Returns

POST

/blackrock/challenge/v1/transactions/returns/nps

Calculates NPS returns with:

Investment cap

Tax benefit

Inflation-adjusted real value


5. Transaction Validator

POST

/blackrock/challenge/v1/transactions/validate

Validates transactions and separates:

Valid transactions

Invalid transactions (with reason)

Validation rules (MVP):

Negative or zero amount

Duplicate transaction timestamps

**Run Locally (Without Docker)**.
   Prerequisites - Java 17 , Maven

Steps
mvn spring-boot:run

Application runs at:

http://localhost:5477


****Run with Docker (Recommended)****
Docker Image

Public image available on Docker Hub:

wavmoon/auto-savings:1.0

***Run Command**
docker pull wavmoon/auto-savings:1.0
docker run -p 5477:5477 wavmoon/auto-savings:1.0

Application will be available at:

http://localhost:5477




**Future Improvements**

Add persistence using a relational database (JPA).

Add performance metrics endpoint.

Improve validation and global error handling.

Add automated test coverage.

Support additional investment strategies.





**Submission Artifacts**

GitHub Repository: https://github.com/iwinsthegame/AutoSaving

Docker Image: wavmoon/auto-savings:1.0

Demo Video: ..

