Hi everyone,

I want to apologize for not being able to complete the exercise within the stipulated time. The last few weeks have been very hectic and I only managed to start on the weekend.
I'm really enjoying doing the exercise, it's been challenging and has made me think a lot.
As this week will probably be the same, I hope you don't mind giving me a little more time to finish.
So far, I only managed to implement the data loading, but I still need to calculate the results, sort the ranking and export it.

That said, when you run the application you will only see the list of loaded athletes printed on the console.


### To run the tests 
#### 'mvn test'

### To generate jar file
#### 'mvn package'

### To run the application 
#### Locate the file 'decathlon-competition-1.0.jar' in the 'target' folder generated in the previous step and 
#### run 'java -cp decathlon-competition-1.0.jar org.olympics.competition.application.Application csv {path-to-csv-file}'

#### For example: 'java -cp decathlon-competition-1.0.jar org.olympics.competition.application.Application csv ./results.csv'

#### The project uses JDK 1.8
#### The program does not use any external libraries in addition to the Java standard API
#### Tests use Junit, Mockito and Surfire plugin
#### Project uses Maven 3.8