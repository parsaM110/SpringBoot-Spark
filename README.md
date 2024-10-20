## running in intellij
go to edit configurations>add VM options> paste below code there:
```
 --add-opens=java.base/java.lang=ALL-UNNAMED                         --add-opens=java.base/java.nio=ALL-UNNAMED                         --add-opens=java.base/java.util=ALL-UNNAMED                         --add-opens=java.base/sun.nio.ch=ALL-UNNAMED
```

there is an error in current codeBase at first but ignore it:
```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/Users/vincent/.m2/repository/ch/qos/logback/logback-classic/1.2.12/logback-classic-1.2.12.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/Users/vincent/.m2/repository/org/apache/logging/log4j/log4j-slf4j-impl/2.17.2/log4j-slf4j-impl-2.17.2.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
```