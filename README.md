Assignment for Senior Java developer at Lottoland
-------------------------------------------------

**Task description**</br>
Write a program that plays 100 iterations of Rock, Paper, Scissors (https://en.wikipedia.org/wiki/Rock-paper-scissors).
One player should always play randomly, the other should always choose rock. It should show at the end how many games each player has one and how many were a draw.

The code should have high test coverage.
Keep it simple - remember: we're not looking for the perfect "correct" solution, but a basis for discussion and development.

**Technical requirements**</br>
Language: Java, tests in a JVM language of your choice
Approach: ideally "test-driven"

Please use local git (initially a “git init” in the project directory), so that we see a little of your working methods.

**Solution**</br>
Project is implemented in pure Java 8 - no frameworks - with minimal set of libraries (mainly for testing).
By default it will run 100 iterations for 2 players. Number of iterations can be changed on application call.
There are two strategies for simulating user activity one of which returns fixed and the other one random value.

To run test call
```sh
$ mvn clean test
```
To build and run the application as maven project call
```sh
$ mvn clean package
$ mvn exec:java -Dexec.mainClass="com.rps.GamePlaySessionRunner"
```
Optionally one can pass number of iterations by adding ` -Dexec.args="99"`

To build and run jar call
```sh
$ mvn clean compile assembly:single
$ java -jar target/rock-paper-scissors-jar-with-dependencies.jar
```

**Example output**</br>
```sh
2017-07-23 08:34:28 [main] INFO  com.rps.GamePlaySessionRunner - Running session with 100 turns
2017-07-23 08:34:28 [main] INFO  com.rps.GamePlaySessionRunner - Players played 100 times
2017-07-23 08:34:28 [main] INFO  com.rps.GamePlaySessionRunner - Player1 won 30 times
2017-07-23 08:34:28 [main] INFO  com.rps.GamePlaySessionRunner - Player2 won 31 times
2017-07-23 08:34:28 [main] INFO  com.rps.GamePlaySessionRunner - There were 39 draws
```