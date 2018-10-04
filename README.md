This is the simple program to avoid manual id changing 
in list of values which contains integer id in betwen '(' and ','.
Just copy paste region with id in new text file and execute Gradle Wrapper run command from project folder (Windows):

<code>gradlew run --args=\"Path\\To\\file.txt"</code> // add one to each Id found

<code>gradlew run --args=\"Path\\To\\file.txt -2"</code> // add provided number to Ids
 
 Program use:
- Java 8 
- Gradle 4.10.2
- JUnit 4 for testing 

*Note*: <code>gradlew test</code> will not show results directly, you should check build/test-results folder
