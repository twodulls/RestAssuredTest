
### How to Run

- QA : mvn test -P qa
- BETA : mvn test -P beta
- PROD : mvn test -P prod
- How to suite run
    - V1 : mvn test -P qa -DsuiteFile=src/test/resources/v1suite.xml
    - v2 : mvn test -P qa -DsuiteFile=src/test/resources/v2suite.xml