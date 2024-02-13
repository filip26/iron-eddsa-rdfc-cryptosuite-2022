# Iron EdDSA RDFC 2022 Cryptographic Suite

An implementation of the [EdDSA RDFC 2022 Cryptosuite](https://www.w3.org/TR/vc-di-eddsa/#eddsa-rdfc-2022) in Java.

[![Java 17 CI](https://github.com/filip26/iron-eddsa-rdfc-cryptosuite-2022/actions/workflows/java17-build.yml/badge.svg)](https://github.com/filip26/iron-eddsa-rdfc-cryptosuite-2022/actions/workflows/java17-build.yml)
[![Android (Java 8) CI](https://github.com/filip26/iron-eddsa-rdfc-cryptosuite-2022/actions/workflows/java8-build.yml/badge.svg)](https://github.com/filip26/iron-eddsa-rdfc-cryptosuite-2022/actions/workflows/java8-build.yml)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/646b0b676a73465aa61ec80a17730bd6)](https://app.codacy.com/gh/filip26/iron-eddsa-rdfc-cryptosuite-2022/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/646b0b676a73465aa61ec80a17730bd6)](https://app.codacy.com/gh/filip26/iron-eddsa-rdfc-cryptosuite-2022/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_coverage)
[![Maven Central](https://img.shields.io/maven-central/v/com.apicatalog/iron-eddsa-rdfc-cryptosuite-2022.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:com.apicatalog%20AND%20a:iron-eddsa-rdfc-cryptosuite-2022)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Features
* [EdDSA RDFC 2022](https://www.w3.org/TR/vc-di-eddsa/#eddsa-rdfc-2022)
  * Verifying VC/VP
  * Issuing VC/VP
* [VC HTTP API & Service](https://github.com/filip26/iron-vc-api)

## Installation

### Maven
Java 17+

```xml
<dependency>
    <groupId>com.apicatalog</groupId>
    <artifactId>iron-eddsa-rdfc-cryptosuite-2022</artifactId>
    <version>0.10.0</version>
</dependency>

<dependency>
    <groupId>com.apicatalog</groupId>
    <artifactId>iron-verifiable-credentials</artifactId>
    <version>0.10.0</version>
</dependency>
```

### Gradle

Android 12+ (API Level >=31)

```gradle
compile group: 'com.apicatalog', name: 'iron-eddsa-rdfc-cryptosuite-2022-jre8', version: '0.10.0'
compile group: 'com.apicatalog', name: 'iron-verifiable-credentials-jre8', version: '0.10.0'
```

## Documentation

[![javadoc](https://javadoc.io/badge2/com.apicatalog/iron-eddsa-rdfc-cryptosuite-2022/javadoc.svg)](https://javadoc.io/doc/com.apicatalog/iron-eddsa-rdfc-cryptosuite-2022)

## Usage

### Verifying 

```java
try {
  Vc.verify(credential|presentation, new EdDSASignature2022())
      
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
var suite = new EdDSASignature2022();

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
> cd iron-eddsa-rdfc-cryptosuite-2022
> mvn clean package
```

#### Java 8
```bash
> cd iron-eddsa-rdfc-cryptosuite-2022
> mvn -f pom_jre8.xml clean package
```

## Resources
* [EdDSA RDFC 2022 Cryptosuite](https://www.w3.org/TR/vc-di-eddsa/#eddsa-rdfc-2022)
* [Iron Verifiable Credentials](https://github.com/filip26/iron-verifiable-credentials)

## Sponsors

<a href="https://github.com/digitalbazaar">
  <img src="https://avatars.githubusercontent.com/u/167436?s=200&v=4" width="40" />
</a> 

## Commercial Support
Commercial support is available at filip26@gmail.com
