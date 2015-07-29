README for rest-devtools
========================
Developer tools for Spring based Rest applications. 
Offering solutions for:<br/>
1, Logging, converting object to it's String representation<br/>
2, Logging, define logger for components by interface default method<br/>
3, <a href="http://www.talangsoft.org/2015/04/04/rest-devtools-interfaces/" target="_blank">Interfaces, Indentifiable, CreatedTrackable, etc.</a><br/>
4, Constants, profileConstants<br/>
5, Domain entity parent classes<br/>
6, Rest exception handling and resolving it to common ErrorDTO<br/>
7, Gateway (external Rest calls) exception handling and resolve it to a common ErrorDTO<br/>

### Version 1.0.0. Available in maven central
http://search.maven.org/#artifactdetails%7Corg.talangsoft%7Crest-devtools%7C1.0.0%7Cjar


```xml
<dependency>
    <groupId>org.talangsoft</groupId>
    <artifactId>rest-devtools</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Release Notes
### 1.0.1:
- Upgrade to Spring Boot 1.2.2
- RestUtils Param mapping simplified with java8 streams
- ErrorDTO is converted to Immutable class with RestCreator constructor
- Removed unused dependencies
- Neo4j dependency scope is now "provided"

### 1.0.2:
- Upgrade to Spring Boot 1.2.3
- ModifiedBy and CreatedBy is generic typed to identify the author

### 1.0.3:
- Simplify modelmapper example

### 1.0.4:
- Change log level from error to debug on exception handling
- Upgrade to Spring Boot 1.2.5