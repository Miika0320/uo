# csi2132


## Run application from terminal

``` mvn spring-boot:run ```

Dependencies added at project initialization:
- Spring Web
- Spring Data JPA
- Thymeleaf
- MySQL Driver (not sure if useful)

Java 17+

[Sprint Initializr Config](https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.0.2&packaging=jar&jvmVersion=17&groupId=CSI2132&artifactId=ehotel&name=ehotel&description=eHotel%20System&packageName=CSI2132.ehotel&dependencies=web,thymeleaf,mysql)

## References

- [Spring MVC Tutorial with Spring Boot](https://www.youtube.com/watch?v=Ku3gsv7_bCc)
- [Spring MVC Blog](https://www.javaguides.net/2021/05/spring-boot-crud-tutorial.html)

## Note

Running MySQL 5.7 on an outdated Mac machine.

```terminal
brew services start mysql@5.7
brew services stop mysql
brew services restart mysql
```
