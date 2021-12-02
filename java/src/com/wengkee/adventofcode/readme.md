# Prerequisite
- Java 11

# How to add
- Add respective challenge input file into src/input
- Add respective implementation by extending Challenge abstract class

# How to run
- At ChallengeRun, 
  - initialise the challenge
    ```java
    cm.addChallenge(new RespectiveProcess(yyyy, day, partNumber, "input-file-name.txt"));
    ```
  - includes the execution
    ```java
    cm.runChallenge(yyyy, day, part);
    ```