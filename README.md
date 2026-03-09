# TDSE-LAB06-MicroSpringBoot
Application Server Architectures Workshop, Object Meta Protocols, IoC Pattern, Reflection

MicroSpringBoot is a micro web framework built from scratch that replicates Spring Boot's behavior using Java reflection. The framework automatically scans the classpath looking for classes annotated with `@RestController`, registers their methods annotated with `@GetMapping`, and allows invoking them by route.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Java 17 or higher
- Maven

```
java --version
```

### Installing

1. Clone the repository

```
git clone https://github.com/sebasPuentes/TDSE-LAB06-MicroSpringBoot
cd TDSE-LAB06-MicroSpringBoot
```

2. Compile the project

```
mvn clean compile
```

3. Run the framework passing the route you want to invoke

```
java -cp target/classes edu.lab.tdse.microframeworkweb.MicroSpringBoot.MicroSpringBoot /greeting
```

The framework will automatically scan the package, discover all controllers annotated with `@RestController`, and execute the method corresponding to the given route.



### Available Routes

| Route | Controller | Method | Response |
|-------|-----------|--------|----------|
| `/` | HelloController | `index()` | `Greetings from Spring Boot!` |
| `/pi` | HelloController | `getPI()` | `PI: 3.141592653589793` |
| `/greeting` | GreetingController | `greeting()` | `Hello, welcome to the micro framework!` |


### Execution Examples

Invoke the root route:
```
java -cp target/classes edu.lab.tdse.microframeworkweb.MicroSpringBoot.MicroSpringBoot /
```

Invoke the `/pi` route:
```
java -cp target/classes edu.lab.tdse.microframeworkweb.MicroSpringBoot.MicroSpringBoot /pi
```

Invoke the `/greeting` route:
```
java -cp target/classes edu.lab.tdse.microframeworkweb.MicroSpringBoot.MicroSpringBoot /greeting
```

## Architecture

The project is organized:

### MicroSpringBoot

- **`MicroSpringBoot.java`** - Main class that scans the classpath looking for classes with `@RestController`, registers their `@GetMapping` methods in a route map, and executes them through reflection.
- **`RestController.java`** - Custom class-level annotation.
- **`GetMapping.java`** - Custom method-level annotation.
- **`RequestParam.java`** - Custom parameter-level annotation.
- **`HelloController.java`** - Sample controller with routes `/` and `/pi`.
- **`GreetingController.java`** - Sample controller with routes `/greeting`.

## Amazon Deploy

Pending

## Built With

* [Java 17](https://docs.oracle.com/en/java/javase/17/) - Programming language
* [Maven](https://maven.apache.org/) - Dependency management and build

## Authors

* **Julio Sebastian Puentes** - *TDSE LAB06*

