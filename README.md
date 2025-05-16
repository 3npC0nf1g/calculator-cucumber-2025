[![Continuous Reporting](https://github.com/3npC0nf1g/calculator-cucumber-2025/actions/workflows/Continuous-reporting.yml/badge.svg)](https://github.com/3npC0nf1g/calculator-cucumber-2025/actions/workflows/Continuous-reporting.yml)

Build and Test: [![Build and Test](https://github.com/3npC0nf1g/calculator-cucumber-2025/actions/workflows/maven.yml/badge.svg)](https://github.com/3npC0nf1g/calculator-cucumber-2025/actions/workflows/maven.yml)

Test coverage: ![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

[![SonarQube Cloud](https://sonarcloud.io/images/project_badges/sonarcloud-light.svg)](https://sonarcloud.io/summary/new_code?id=3npC0nf1g_calculator-cucumber-2025)

Code Quality: [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=3npC0nf1g_calculator-cucumber-2025&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=3npC0nf1g_calculator-cucumber-2025)

Code Smells: [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=3npC0nf1g_calculator-cucumber-2025&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=3npC0nf1g_calculator-cucumber-2025)

Bugs: [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=3npC0nf1g_calculator-cucumber-2025&metric=bugs)](https://sonarcloud.io/summary/new_code?id=3npC0nf1g_calculator-cucumber-2025)

Duplicated lines: [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=3npC0nf1g_calculator-cucumber-2025&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=3npC0nf1g_calculator-cucumber-2025)

# Calculating arithmetic expressions

## About

This repository contains Java code for computing arithmetic expressions. It is deliberately incomplete as it serves to be the basis of all kinds of extensions, such as a more sophisticated Calculator application. The code was written to be used for educational purposes at the University of Mons, Belgium in the context of the software evolution course.


### Unit testing and BDD

*  All tests can be found in the src\test directory. They serve as executable documentation of the source code.
*  The source code is accompanied by a set of JUnit 5 unit tests. These tests can be written and run in the usual way. If you are not familiar with unit testing or JUnit 5, please refer to https://junit.org/junit5/.
*  The source code is accompanied by a set of Cucumber BDD scenarios, also running in Junit. If you are not familiar with Cucumber and BDD, please refer to https://cucumber.io/docs/cucumber/.
The BDD scenarios are specified as .feature files in the src\test\resources directory. Some classes defined in src\test take care of converting these scenarios to executable JUnit tests.

### Prerequisites

*  You will need to have a running version of Java 23 on your machine in order to be able to compile and execute this code, although it is also backward compatible with earlier versions of Java.
*  You will need to have a running version of Maven, since this project is accompanied by a pom.xml file so that it can be installed, compiled, tested and run using Maven.

### Installation and testing instructions

*  Upon first use of the code in this repository, you will need to run "mvn clean install" to ensure that all required project dependencies (e.g. for Java, JUnit, Cucumber, and Maven) will be downloaded and installed locally.
*  Assuming you have a sufficiently recent version of Maven installed (the required versions are specified as properties in the POM file), you can compile the source code using "mvn compile"
*  Once the code is compiled, you can execute the main class of the Java code using "mvn exec:java" 
*  The tests and BDD scenarios are executable with Maven using "mvn test"
*  Note that the tests are also executed when you do a "mvn install". It is possible to skip those tests by providing an extra parameter. For details of more advanced uses of Maven, please refer to its official documentation https://maven.apache.org/guides/.

### Test coverage and JavaDoc reporting

*  In addition to testing the code, "mvn test" will also generate a test coverage report (in HTML format) using JaCoCo. This test coverage is generated in target/site/jacoco.
*  When packaging the code using "mvn package" the JavaDoc code documentation will be generated and stored in target/site/apidocs.

## Built With

*  [Maven](https://maven.apache.org/) - an open source build automation and dependency management tool
*  [JUnit5](https://junit.org/junit5/) - a unit testing framework for Java
*  [Cucumber](https://cucumber.io/docs/cucumber/) - a tool for Behaviour-Driven Development
*  [JaCoCo](https://www.jacoco.org) - a code coverage library for Java
*  [JavaDoc](https://docs.oracle.com/en/java/javase/21/javadoc/javadoc.html) - a code documentation tool for Java

## Versions

We use [SemVer](http://semver.org/) for semantic versioning. For the versions available, see the [tags on this repository](https://github.com/University-of-Mons/calculator-cucumber-2025/tags). 

## Contributors

* Tom Mens
* Gauvain Devillez @GauvainD

## Licence


[This code is available under the GNU General Public License v3.0](https://choosealicense.com/licenses/gpl-3.0/) (GPLv3)

## Acknowledgments

* Software Engineering Lab, Faculty of Sciences, University of Mons, Belgium.

# 🧮 Cucumber Calculator

This is a full-stack calculator application consisting of:

- A **Java Spring Boot backend API** for evaluating mathematical expressions
- A **React Native GUI** (with web support) for user interaction, built using **Expo**

---

## 📂 Project Structure

```
calculator-cucumber-2025/
├── src/
│   └── main/java/calculator/
│       ├── api/
│       ├── config/
│       ├── service/
│       ├── util/
│       ├── values/
│       └── Application.java
└── GUI/
    └── Calculator/
        ├── app/
        ├── components/
        ├── constants/
        ├── context/
        ├── hooks/
        └── ...
```

---

## 🚀 How to Launch the Application

### 1️⃣ Backend API (Java)

#### 📍 Location
`src/main/java/calculator/Application.java`

#### ▶️ Launch Instructions

##### ✅ Using Maven:
```bash
mvn clean install
mvn spring-boot:run
```

##### ✅ Using IntelliJ / Eclipse:
Open the project, then run `Application.java` directly.

#### 📡 API Endpoint

The API will start on:  
```
http://localhost:8080
```

Example request:
```
GET /api/evaluate?expression=2+*+(3+4)
```

Example curl:
```bash
curl "http://localhost:8080/api/evaluate?expression=2%20*%20(3%20+%204)"
```

Expected response:
```
14
```

---

### 2️⃣ Frontend GUI (React Native via Expo)

#### 📍 Location
`GUI/Calculator/`

#### ▶️ Launch Instructions

Navigate to the frontend folder:

```bash
cd GUI/Calculator
```

Install dependencies:

```bash
npm install
```

Start the Expo server in **web mode**:

```bash
npx expo start -w
```

Once running, open your browser and go to:

```
http://localhost:19006
```

You can interact with the calculator via the web interface.

---

## 🧪 Running Backend Tests

To execute unit and integration tests for the Java backend:

```bash
mvn clean test
```

Test coverage is managed via **JaCoCo**, and results can be found in:

```
target/site/jacoco/
```

---

## ⚙️ Requirements

### Backend:
- Java 21 or 23
- Maven 3.6+

### Frontend:
- Node.js ≥ 18
- Expo CLI *(optional for global install)*:
  ```bash
  npm install -g expo-cli
  ```


## 👥 Contributors

- [Hugugus](https://github.com/Hugugus)
- [Le-Mael](https://github.com/Le-Mael)
- [3npC0nf1g](https://github.com/3npC0nf1g)
- [T-ramissU](https://github.com/T-ramissU)

