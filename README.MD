##Embedded AMQP Queue

####I. Requirements
1. Gradle 6.x <=
2. Java 11

####II. Config
The server runs on AMQP port 5672, by default and on HTTP 8080 for management console (localhost:8080, user and password provided in config.json file). 
Configure your client to point to amqp://localhost:5672.

####II. Run
You can either run it by using IntelliJ, by launching Spring Boot Application or by running Gradle task (eg. from command line): `gradle bootRun`
Sender.kts script file contains sample invocations of sending method.
 
 
####III. Issues
In case of any issues reboot the app. If that doesn't help, use `gradle cleanAll` task to remove QPID Server working files ("work" folder).
