# hand-range-trainer

## Spring-boot/Angular2 application

Might be useful for online players to increase their skills in Holdem Poker

uses Maven and Swagger to build rest api for stack java/spring-boot and js/angular2 applications based on .yaml modell describtor:

<https://github.com/soulaway/hand-range-trainer/blob/master/swagger-api-builder/api-source/prod/api.yaml>

prerequired:

- jdk ~1.8

- maven ~3.

- npm ~4.4.

- nodejs ~0.10.

Build:

> cd ../hand-range-trainer

> mvn clean install -Pdev

Run backend on 8080:

> cd ../hand-range-trainer/ng2spring-boot-seed/backend

> mvn spring-boot:run

Run separate hot deployed frontend on 4200

> cd ../hand-range-trainer/ng2spring-boot-seed/frontend/src/main/frontend

> npm start
