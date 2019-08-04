# About This Application
This application demonstrates the following:
- Triggering task(s) via Apache Camel endpoints.
- Controlling of tasks triggered with command line arguments.
- A task framework where:
   - a new task can be created simply by creating a sub-class of the base task class and assigning values to properties in application.yml, and
   - tasks accept arguments from and return results to the task runner.

## Running this application
### As JAR
Example with arguments:
```
java -jar build\libs\camel-route-demo-0.0.1-SNAPSHOT.jar task1 task7 task3
```

### With Gradle bootRun
Example with arguments:
```
gradlew bootRun --args="task1 task2 task4 task3"
```