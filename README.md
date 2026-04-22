# Java Core Hands-On Practice Repository

A comprehensive collection of Java core concepts, design patterns, and hands-on exercises. This repository contains multiple Maven-based projects demonstrating various Java features and best practices.

## 📋 Project Overview

### Core Projects

#### 1. **OrderProcessingSystem**
- Demonstrates modern Java features including:
  - **Sealed Classes**: Type-safe inheritance restrictions with `OrderStatus`
  - **Records**: Immutable data carriers (`Money`, `OrderLine`, `ProductId`)
  - **Pattern Matching**: Switch expressions with sealed classes for order status handling
- Maven-based project with compiled classes in target directory

#### 2. **EJBTesting**
- Enterprise JavaBean (EJB) examples
- Includes:
  - `StatelessEJB`: Stateless session bean implementations
  - `EJBClient` & `EJBClient2`: Client code for EJB communication
  - `Wildcards`: Java generics wildcard examples
- Unit tests included with `testingEJBStateless.java`

#### 3. **InventoryWatchmen** / **ProductCatalogueService**
- Observer pattern implementation for product inventory management
- Architecture:
  - **DTO Layer**: `ProductRegistered` event objects
  - **Entity Layer**: `Product` domain models
  - **Repository Pattern**: `ProductRepository` for data access
  - **Service Layer**: `ProductService` business logic
  - **Observer Pattern**: `CatalogueAuditListener` for event handling

#### 4. **Threads**
- Multithreading and concurrency concepts
- Daemon thread examples:
  - `DeamonThreads`: Basic daemon thread implementation
  - `CustomDeamonThread`, `DeamonThread2`, `DeamonThread3`, `DeamonThread4`: Various daemon thread patterns

#### 5. **ConcurrencyInJava**
- Advanced concurrency patterns and utilities

#### 6. **Lambdas_Func_interfaces**
- Functional programming with Java lambdas
- Functional interfaces and lambda expressions
- `Predicate` implementations for filtering logic

#### 7. **Eventfolder**
- Event-driven architecture example
- **EventBus**: Custom event bus implementation with:
  - Generic event type mapping
  - Consumer-based handler chains
  - Method chaining support
- `EventMain`: Main entry point for event demonstrations

#### 8. **KaratInterview**
- Interview preparation problems and solutions

#### 9. **Wildcards**
- Java generics and wildcard examples

### Root Level Files

- **SmartProcessor.java**: Pattern matching with sealed classes and records
  - Demonstrates switch expressions on complex types
- **Solution.java**: Stock trading data analysis challenges

## 🛠️ Technologies & Tools

- **Java**: Version 26 (as per `pom.xml`)
- **Build Tool**: Maven 3.x+
- **Testing**: JUnit 4.13.2
- **IDE Compatible**: IntelliJ IDEA

## 🚀 Getting Started

### Prerequisites

- JDK 26 or higher installed
- Maven 3.6.0 or higher

### Building the Project

```bash
# Navigate to the repository
cd java-core-handson

# Build all modules
mvn clean package

# Run tests
mvn test
```

### Building Individual Projects

```bash
# Example: Build OrderProcessingSystem
cd OrderProcessingSystem
mvn clean package

# Run tests
mvn test
```

## 📦 Project Structure

```
java-core-handson/
├── OrderProcessingSystem/       # Sealed classes, records, pattern matching
├── EJBTesting/                  # Enterprise JavaBeans
├── InventoryWatchmen/           # Observer pattern + domain-driven design
├── ProductCatalogueService/     # Observer pattern implementation
├── Threads/                     # Daemon threads and concurrency
├── ConcurrencyInJava/           # Advanced concurrency patterns
├── Lambdas_Func_interfaces/     # Functional programming
├── Eventfolder/                 # Event-driven architecture
├── KaratInterview/              # Interview problems
├── Wildcards/                   # Java generics
├── SmartProcessor.java          # Pattern matching examples
├── Solution.java                # Stock trading challenges
└── pom.xml                      # Parent POM configuration
```

## 🎯 Key Concepts Covered

### Java Language Features
- ✅ **Sealed Classes**: Type hierarchy constraints
- ✅ **Records**: Immutable data structures
- ✅ **Pattern Matching**: Switch expressions and type patterns
- ✅ **Generics & Wildcards**: Type parameterization
- ✅ **Lambda Expressions**: Functional programming
- ✅ **Functional Interfaces**: Predicate, Consumer, etc.

### Design Patterns
- ✅ **Observer Pattern**: Event-driven systems
- ✅ **Repository Pattern**: Data access abstraction
- ✅ **Service Layer Pattern**: Business logic separation
- ✅ **Event Bus Pattern**: Decoupled event handling

### Concurrency
- ✅ **Daemon Threads**: Background thread management
- ✅ **Thread Safety**: Concurrent programming practices

### Enterprise Development
- ✅ **EJB**: Enterprise JavaBeans implementation
- ✅ **DTO Pattern**: Data transfer objects
- ✅ **Domain-Driven Design**: Entity and service organization

## 📝 Usage Examples

### Running OrderProcessingSystem
```bash
cd OrderProcessingSystem
mvn clean package
java -cp target/classes org.example.Main
```

### Running Tests
```bash
cd EJBTesting
mvn test
```

## 🔧 Development Guidelines

- Follow Java naming conventions (camelCase for methods/variables, PascalCase for classes)
- Each project is self-contained with its own `pom.xml`
- Use Maven for dependency management
- Add comprehensive comments for complex logic
- Include unit tests for new features

## 📚 Learning Path

1. Start with **Lambdas_Func_interfaces** to understand functional programming
2. Progress to **Threads** for concurrency basics
3. Study **OrderProcessingSystem** for modern Java features
4. Explore **Eventfolder** for event-driven patterns
5. Learn **InventoryWatchmen** for domain-driven design
6. Review **EJBTesting** for enterprise concepts

## 📄 License

This project is a hands-on learning repository. Feel free to use for educational purposes.

## 👤 Author

Created as a hands-on practice and learning resource for Java core concepts.

---

**Last Updated**: April 2026

For questions or contributions, please feel free to explore the code and experiment with the examples.

