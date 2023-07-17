# Iron EdDSA Signature Suite 2022

An implementation of the [EdDSA Cryptosuite 2022](https://www.w3.org/TR/vc-di-eddsa/#eddsa-2022) in Java.

[![Java 17 CI](https://github.com/filip26/iron-eddsa-cryptosuite-2022/actions/workflows/java17-build.yml/badge.svg)](https://github.com/filip26/iron-eddsa-cryptosuite-2022/actions/workflows/java17-build.yml)
[![Android (Java 8) CI](https://github.com/filip26/iron-eddsa-cryptosuite-2022/actions/workflows/java8-build.yml/badge.svg)](https://github.com/filip26/iron-eddsa-cryptosuite-2022/actions/workflows/java8-build.yml)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/806688cdb1d248e8b5cc2a67f6c2f0f8)](https://www.codacy.com/gh/filip26/iron-eddsa-cryptosuite-2022/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=filip26/iron-eddsa-cryptosuite-2022&amp;utm_campaign=Badge_Grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/806688cdb1d248e8b5cc2a67f6c2f0f8)](https://www.codacy.com/gh/filip26/iron-eddsa-cryptosuite-2022/dashboard?utm_source=github.com&utm_medium=referral&utm_content=filip26/iron-eddsa-cryptosuite-2022&utm_campaign=Badge_Coverage)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=filip26_iron-eddsa-cryptosuite-2022&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=filip26_iron-eddsa-cryptosuite-2022)
[![Maven Central](https://img.shields.io/maven-central/v/com.apicatalog/iron-eddsa-cryptosuite-2022.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.apicatalog%22%20AND%20a:%22iron-eddsa-cryptosuite-2022%22)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Features
* [EdDSA Signature 2022](https://www.w3.org/TR/vc-di-eddsa/#eddsa-2022)
  * Verifying VC/VP
  * Issuing VC/VP
* [VC HTTP API & Service](https://github.com/filip26/iron-vc-api)

## Installation

### Maven

```xml
<!-- Java 17 -->
<dependency>
    <groupId>com.apicatalog</groupId>
    <artifactId>iron-eddsa-cryptosuite-2022</artifactId>
    <version>0.9.0</version>
</dependency>

<dependency>
    <groupId>com.apicatalog</groupId>
    <artifactId>iron-verifiable-credentials</artifactId>
    <version>0.9.0</version>
</dependency>
```

or

```xml
<!-- Android (Java 8, Tink) -->
<dependency>
    <groupId>com.apicatalog</groupId>
    <artifactId>iron-eddsa-cryptosuite-2022-jre8</artifactId>
    <version>0.9.0</version>
</dependency>

<dependency>
    <groupId>com.apicatalog</groupId>
    <artifactId>iron-verifiable-credentials-jre8</artifactId>
    <version>0.9.0</version>
</dependency>
```

### Gradle

```gradle
compile group: 'com.apicatalog', name: 'iron-eddsa-cryptosuite-2022-jre8', version: '0.9.0'
```

## Documentation

[![javadoc](https://javadoc.io/badge2/com.apicatalog/iron-eddsa-cryptosuite-2022/javadoc.svg)](https://javadoc.io/doc/com.apicatalog/iron-eddsa-cryptosuite-2022)

## Usage

### Verifying 

```java
try {
  Vc.verify(credential|presentation, new EdDsaSignature2022())
      
    // optional
    .base(...)
    .loader(documentLoader) 
    .statusVerifier(...)
    .useBundledContexts(true|false)

    // custom | suite specific | parameters
    .param(DataIntegrity.DOMAIN.name(), ....)

    // assert document validity
    .isValid();
    
} catch (VerificationError | DataError e) {
  ...
}

```

### Issuing

```java
var suite = new EdDsaSignature2022();

var proofDraft = suite.createDraft(
    verificationMethod,
    purpose,
    created
    );

Vc.sign(credential|presentation, keys, proofDraft)

   // optional
   .base(...)
   .loader(documentLoader) 
   .statusVerifier(...)
   .useBundledContexts(true|false)

    // return signed document in a compacted form
   .getCompacted();

```

## Contributing

All PR's welcome!

### Building

Fork and clone the project repository.

#### Java 17
```bash
> cd iron-eddsa-cryptosuite-2022
> mvn clean package
```

#### Java 8
```bash
> cd iron-eddsa-cryptosuite-2022
> mvn -f pom_jre8.xml clean package
```

## Resources
* [EdDSA Cryptosuite 2022](https://www.w3.org/TR/vc-di-eddsa/#eddsa-2022)
* [Interoperability Report](https://w3c-ccg.github.io/di-ed25519signature2020-test-suite/)
* [Iron Verifiable Credentials](https://github.com/filip26/iron-verifiable-credentials)

## Sponsors

<a href="https://github.com/digitalbazaar">
  <img src="https://avatars.githubusercontent.com/u/167436?s=200&v=4" width="40" />
</a> 

## Commercial Support
Commercial support is available at filip26@gmail.com
.
