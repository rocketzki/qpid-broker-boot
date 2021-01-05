##Apache Qpid Broker running on Spring Boot

#####I. Basic Requirements
1. Gradle 6.x
2. Java 11

#####II. Config
The server defaults: 
AMQP port 5672,
HTTP port 8080
Management console availalble at localhost:8080 (login and pass available in config.json file)


#####III. Run
You can run the app by running Gradle task
`gradle bootRun`
ExampleSendingScript.kts  contains example invocations of sending methods.
Client config is for know possible via editing properties in Client.kt class


#####III. Issues
In case of any issues restart app and remove `work` folder.


######
by Michał Markieta