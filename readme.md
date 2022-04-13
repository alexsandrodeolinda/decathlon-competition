Hi everyone,


### To run the tests 
#### 'mvn test'

### To generate jar file
#### 'mvn package'

### To run the application 
#### Locate the file 'decathlon-competition-1.0.jar' in the 'target' folder generated in the previous step and 
#### run 'java -cp decathlon-competition-1.0.jar org.olympics.competition.application.Application csv {path-to-csv-file} xml {path-to-xml-file}'

#### For example: 'java -cp decathlon-competition-1.0.jar org.olympics.competition.application.Application csv ./results.csv xml ./output.xml'

#### The project uses JDK 1.8
#### The program does not use any external libraries in addition to the Java standard API
#### Tests use Junit, Mockito and Surfire plugin
#### Project uses Maven 3.8
