Advanced Part A example: packaged layered application.
Compile from the Practical_14 folder:
  javac -d out advanced/src/app/model/Task.java advanced/src/app/service/TaskService.java advanced/src/app/Main.java
Run:
  java -cp out app.Main
Create a runnable JAR after compiling:
  jar --create --file layered-app.jar --main-class app.Main -C out .
The JUnit example is in LayeredAppTest.java at the practical root and requires JUnit 5 in the IDE/project test dependencies.
