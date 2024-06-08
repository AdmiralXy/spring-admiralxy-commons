<h1 align="center">spring-commons</h1>
<p align="center">
  Utility library for Spring Boot applications.
</p>

<br/>

<div align="center">
  <a href="https://hub.docker.com/r/admiralxy/clean" target="_blank"><img src="https://img.shields.io/docker/v/admiralxy/clean?style=for-the-badge"/></a>
  <a href="https://hub.docker.com/r/admiralxy/clean" target="_blank"><img src="https://img.shields.io/docker/image-size/admiralxy/clean?style=for-the-badge&label=docker%20image%20size"/></a>
  <a href="https://hub.docker.com/r/admiralxy/clean" target="_blank"><img src="https://img.shields.io/docker/pulls/admiralxy/clean?style=for-the-badge"/></a>
</div>

<br/>

## :package: Packages

### **commons-domain-core** - Base domain entities

Contains the base classes and interfaces for domain entities, providing a foundational structure for various entity types.

- **BaseEntity**: marker interface for domain entities
- **TimestampedEntity**: base entity with created and updated timestamps
- **GuidEntity**: timestamped entity with GUID identifier (ID)
- **SequencedEntity**: timestamped entity with auto-increment sequence number (ID)

### **commons-domain-persistence** - Base persistence entities 
#### Prerequisites: Hibernate

Contains base interfaces and classes for JPA persistence, integrating with Hibernate to facilitate database operations.

- **BaseJpaEntity**: marker interface for JPA entities
- **TimestampedJpaEntity**: base JPA entity with created and updated timestamps
- **GuidJpaEntity**: timestamped JPA entity with GUID identifier (ID)
- **SequencedJpaEntity**: timestamped JPA entity with auto-increment sequence number (ID)

### **commons-messages** - Message source wrapper for internationalization

Offers a wrapper around message sources, making it easier to manage internationalization and localization of messages.

- **MessageSourceWrapper**: wrapper

### **commons-validation** - Extended validation annotations
#### Prerequisites: Hibernate, PostgreSQL

Provides additional validation annotations to enhance application's validation logic out of the box.

- **Unique**: ensuring that fields are unique within the database, commonly used for fields like username and email.

## :zap: Quick start

**Step 1:** Make sure you have `JDK >= 21` and `Gradle` installed

**Step 2:** Start application with `gradle start`

**Commands:**

```Bash
# Run application
$> gradle start

# Run tests
$> gradle test --warning-mode=all

# Run checkstyle
$> gradle check --warning-mode=all
```

<br>
