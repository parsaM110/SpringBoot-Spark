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
based on https://www.youtube.com/watch?v=UVoP1wK2ODE

## Spark CLI
get interactive shell:
```
docker exec -it f6482cc64722 bash
```
run spark shell:
```
I have no name!@f6482cc64722:/opt/bitnami/spark$ spark-shell
```
actually if you want to see in SparkUI:
```agsl
spark-shell --master spark://spark-master:7077

```
now read a file and create RDD(Resilient Distributed Dataset) from it:
```bash
scala> val x = sc.textFile("README.md")
x: org.apache.spark.rdd.RDD[String] = README.md MapPartitionsRDD[1] at textFile at <console>:23
```
then:
```bash
scala> val y = x.map(_.toUpperCase)
y: org.apache.spark.rdd.RDD[String] = MapPartitionsRDD[2] at map at <console>:23
```
now if we print it, we see:
```agsl
scala> y.take(10).foreach(println)
# APACHE SPARK                                                                  

SPARK IS A UNIFIED ANALYTICS ENGINE FOR LARGE-SCALE DATA PROCESSING. IT PROVIDES
HIGH-LEVEL APIS IN SCALA, JAVA, PYTHON, AND R, AND AN OPTIMIZED ENGINE THAT
SUPPORTS GENERAL COMPUTATION GRAPHS FOR DATA ANALYSIS. IT ALSO SUPPORTS A
RICH SET OF HIGHER-LEVEL TOOLS INCLUDING SPARK SQL FOR SQL AND DATAFRAMES,
MLLIB FOR MACHINE LEARNING, GRAPHX FOR GRAPH PROCESSING,
AND STRUCTURED STREAMING FOR STREAM PROCESSING.

<HTTPS://SPARK.APACHE.ORG/>
```
you can count it even:
```agsl
scala> y.count()
res1: Long = 109
```
press ctrl + d to exit the shell
and now enter this in terminal to have python shell:
```
pyspark
```
now exact same operation is like:
```
>>> x = sc.textFile("README.md")
>>> x
README.md MapPartitionsRDD[1] at textFile at NativeMethodAccessorImpl.java:0
```
now we can do :
```
>>> y = x.map(lambda line: line.upper())
>>> y
PythonRDD[2] at RDD at PythonRDD.scala:53
>>> y.collect()
['# APACHE SPARK', '', 'SPARK IS A UNIFIED ANALYTICS ENGINE FOR LARGE-SCALE DATA PROCESSING. IT PROVIDES', 'HIGH-LEVEL APIS IN SCALA, JAVA, PYTHON, AND R, AND AN OPTIMIZED ENGINE THAT', 'SUPPORTS GENERAL COMPUTATION GRAPHS FOR DATA ANALYSIS. IT ALSO SUPPORTS A', 'RICH SET OF HIGHER-LEVEL TOOLS INCLUDING SPARK SQL FOR SQL AND DATAFRAMES,', 'MLLIB FOR MACHINE LEARNING, GRAPHX FOR GRAPH PROCESSING,', 'AND STRUCTURED STREAMING FOR STREAM PROCESSING.', '', '<HTTPS://SPARK.APACHE.ORG/>', '', '[![GITHUB ACTION BUILD](HTTPS://GITHUB.COM/APACHE/SPARK/ACTIONS/WORKFLOWS/BUILD_AND_TEST.YML/BADGE.SVG?BRANCH=MASTER)](HTTPS://GITHUB.COM/APACHE/SPARK/ACTIONS/WORKFLOWS/BUILD_AND_TEST.YML?QUERY=BRANCH%3AMASTER)', '[![JENKINS BUILD](HTTPS://AMPLAB.CS.BERKELEY.EDU/JENKINS/JOB/SPARK-MASTER-TEST-SBT-HADOOP-3.2/BADGE/ICON)](HTTPS://AMPLAB.CS.BERKELEY.EDU/JENKINS/JOB/SPARK-MASTER-TEST-SBT-HADOOP-3.2)', '[![APPVEYOR BUILD](HTTPS://IMG.SHIELDS.IO/APPVEYOR/CI/APACHESOFTWAREFOUNDATION/SPARK/MASTER.SVG?STYLE=PLASTIC&LOGO=APPVEYOR)](HTTPS://CI.APPVEYOR.COM/PROJECT/APACHESOFTWAREFOUNDATION/SPARK)', '[![PYSPARK COVERAGE](HTTPS://CODECOV.IO/GH/APACHE/SPARK/BRANCH/MASTER/GRAPH/BADGE.SVG)](HTTPS://CODECOV.IO/GH/APACHE/SPARK)', '', '', '## ONLINE DOCUMENTATION', '', 'YOU CAN FIND THE LATEST SPARK DOCUMENTATION, INCLUDING A PROGRAMMING', 'GUIDE, ON THE [PROJECT WEB PAGE](HTTPS://SPARK.APACHE.ORG/DOCUMENTATION.HTML).', 'THIS README FILE ONLY CONTAINS BASIC SETUP INSTRUCTIONS.', '', '## BUILDING SPARK', '', 'SPARK IS BUILT USING [APACHE MAVEN](HTTPS://MAVEN.APACHE.ORG/).', 'TO BUILD SPARK AND ITS EXAMPLE PROGRAMS, RUN:', '', '    ./BUILD/MVN -DSKIPTESTS CLEAN PACKAGE', '', '(YOU DO NOT NEED TO DO THIS IF YOU DOWNLOADED A PRE-BUILT PACKAGE.)', '', 'MORE DETAILED DOCUMENTATION IS AVAILABLE FROM THE PROJECT SITE, AT', '["BUILDING SPARK"](HTTPS://SPARK.APACHE.ORG/DOCS/LATEST/BUILDING-SPARK.HTML).', '', 'FOR GENERAL DEVELOPMENT TIPS, INCLUDING INFO ON DEVELOPING SPARK USING AN IDE, SEE ["USEFUL DEVELOPER TOOLS"](HTTPS://SPARK.APACHE.ORG/DEVELOPER-TOOLS.HTML).', '', '## INTERACTIVE SCALA SHELL', '', 'THE EASIEST WAY TO START USING SPARK IS THROUGH THE SCALA SHELL:', '', '    ./BIN/SPARK-SHELL', '', 'TRY THE FOLLOWING COMMAND, WHICH SHOULD RETURN 1,000,000,000:', '', '    SCALA> SPARK.RANGE(1000 * 1000 * 1000).COUNT()', '', '## INTERACTIVE PYTHON SHELL', '', 'ALTERNATIVELY, IF YOU PREFER PYTHON, YOU CAN USE THE PYTHON SHELL:', '', '    ./BIN/PYSPARK', '', 'AND RUN THE FOLLOWING COMMAND, WHICH SHOULD ALSO RETURN 1,000,000,000:', '', '    >>> SPARK.RANGE(1000 * 1000 * 1000).COUNT()', '', '## EXAMPLE PROGRAMS', '', 'SPARK ALSO COMES WITH SEVERAL SAMPLE PROGRAMS IN THE `EXAMPLES` DIRECTORY.', 'TO RUN ONE OF THEM, USE `./BIN/RUN-EXAMPLE <CLASS> [PARAMS]`. FOR EXAMPLE:', '', '    ./BIN/RUN-EXAMPLE SPARKPI', '', 'WILL RUN THE PI EXAMPLE LOCALLY.', '', 'YOU CAN SET THE MASTER ENVIRONMENT VARIABLE WHEN RUNNING EXAMPLES TO SUBMIT', 'EXAMPLES TO A CLUSTER. THIS CAN BE A MESOS:// OR SPARK:// URL,', '"YARN" TO RUN ON YARN, AND "LOCAL" TO RUN', 'LOCALLY WITH ONE THREAD, OR "LOCAL[N]" TO RUN LOCALLY WITH N THREADS. YOU', 'CAN ALSO USE AN ABBREVIATED CLASS NAME IF THE CLASS IS IN THE `EXAMPLES`', 'PACKAGE. FOR INSTANCE:', '', '    MASTER=SPARK://HOST:7077 ./BIN/RUN-EXAMPLE SPARKPI', '', 'MANY OF THE EXAMPLE PROGRAMS PRINT USAGE HELP IF NO PARAMS ARE GIVEN.', '', '## RUNNING TESTS', '', 'TESTING FIRST REQUIRES [BUILDING SPARK](#BUILDING-SPARK). ONCE SPARK IS BUILT, TESTS', 'CAN BE RUN USING:', '', '    ./DEV/RUN-TESTS', '', 'PLEASE SEE THE GUIDANCE ON HOW TO', '[RUN TESTS FOR A MODULE, OR INDIVIDUAL TESTS](HTTPS://SPARK.APACHE.ORG/DEVELOPER-TOOLS.HTML#INDIVIDUAL-TESTS).', '', 'THERE IS ALSO A KUBERNETES INTEGRATION TEST, SEE RESOURCE-MANAGERS/KUBERNETES/INTEGRATION-TESTS/README.MD', '', '## A NOTE ABOUT HADOOP VERSIONS', '', 'SPARK USES THE HADOOP CORE LIBRARY TO TALK TO HDFS AND OTHER HADOOP-SUPPORTED', 'STORAGE SYSTEMS. BECAUSE THE PROTOCOLS HAVE CHANGED IN DIFFERENT VERSIONS OF', 'HADOOP, YOU MUST BUILD SPARK AGAINST THE SAME VERSION THAT YOUR CLUSTER RUNS.', '', 'PLEASE REFER TO THE BUILD DOCUMENTATION AT', '["SPECIFYING THE HADOOP VERSION AND ENABLING YARN"](HTTPS://SPARK.APACHE.ORG/DOCS/LATEST/BUILDING-SPARK.HTML#SPECIFYING-THE-HADOOP-VERSION-AND-ENABLING-YARN)', 'FOR DETAILED GUIDANCE ON BUILDING FOR A PARTICULAR DISTRIBUTION OF HADOOP, INCLUDING', 'BUILDING FOR PARTICULAR HIVE AND HIVE THRIFTSERVER DISTRIBUTIONS.', '', '## CONFIGURATION', '', 'PLEASE REFER TO THE [CONFIGURATION GUIDE](HTTPS://SPARK.APACHE.ORG/DOCS/LATEST/CONFIGURATION.HTML)', 'IN THE ONLINE DOCUMENTATION FOR AN OVERVIEW ON HOW TO CONFIGURE SPARK.', '', '## CONTRIBUTING', '', 'PLEASE REVIEW THE [CONTRIBUTION TO SPARK GUIDE](HTTPS://SPARK.APACHE.ORG/CONTRIBUTING.HTML)', 'FOR INFORMATION ON HOW TO GET STARTED CONTRIBUTING TO THE PROJECT.']
```
for exit write:
```
quit()
```
so for using pyspark ml kit, we need numpy,
but it is not installed in docker image, so we need to install it:
first we need root access
```
docker exec -it -u root 790f2ec16bc3 bash
```
then install numpy:
```
pip install numpy
```
now we can use pyspark ml kit:
```
pyspark
```
if you want to see it in SparkUI:
```agsl
pyspark --master spark://spark-master:7077
```
and now we can use it:
seems like in root mode pyspark doesn't work well so go back to `I have no name!` user.
```
>>> from pyspark.ml.regression import LinearRegression
dataset = spark.read.csv("file:///home/EcommerceCustormers.csv",inferSchema=True,header=True)
```
just before that you need to add EcommerceCustormers in home directory:
```csv
Email,Address,Avatar,Avg Session Length,Time on App,Time on Website,Length of Membership,Yearly Amount Spent
mstephenson@fernandez.com,"835 Frank Tunnel, Wrightmouth, MI 82180-9605",Violet,34.49726773,12.65565115,39.57766802,4.08262063,587.951054
hduke@hotmail.com,"4547 Archer Common, Diazchester, CA 06566-8576",DarkGreen,31.92627203,11.10946073,37.26895887,2.66403418,487.547504
pallen@yahoo.com,"24645 Valerie Unions Suite 582, Cobboborough, DC 99414-7564",Bisque,33.00091476,11.33027806,37.11059744,4.10454353,581.852344
riverarebecca@gmail.com,"1414 David Throughway, Port Jason, OH 22070-1220",SaddleBrown,34.30555663,13.71751367,36.72128268,3.12017878,599.406092
mstephens@davidson-herman.com,"14023 Rodriguez Passage, Port Jacobville, PR 37242-1057",MediumAquaMarine,33.33067252,12.79518189,34.47687789,5.18408525,614.406914
alvareznancy@lucas.biz,"645 Martha Park Apt. 611, Jeffreychester, MN 67218-7250",FloralWhite,33.87103788,12.02692534,34.47687789,5.49350721,571.5721748
```
just before that exec to docker container and run to install vim:
```bash
apt-get update
apt-get install vim
```
now you can run :
```agsl
>>> dataset.show(10)                                                            
+--------------------+--------------------+----------------+------------------+-----------+---------------+--------------------+-------------------+
|               Email|             Address|          Avatar|Avg Session Length|Time on App|Time on Website|Length of Membership|Yearly Amount Spent|
+--------------------+--------------------+----------------+------------------+-----------+---------------+--------------------+-------------------+
|mstephenson@ferna...|835 Frank Tunnel,...|          Violet|       34.49726773|12.65565115|    39.57766802|          4.08262063|         587.951054|
|   hduke@hotmail.com|4547 Archer Commo...|       DarkGreen|       31.92627203|11.10946073|    37.26895887|          2.66403418|         487.547504|
|    pallen@yahoo.com|24645 Valerie Uni...|          Bisque|       33.00091476|11.33027806|    37.11059744|          4.10454353|         581.852344|
|riverarebecca@gma...|1414 David Throug...|     SaddleBrown|       34.30555663|13.71751367|    36.72128268|          3.12017878|         599.406092|
|mstephens@davidso...|14023 Rodriguez P...|MediumAquaMarine|       33.33067252|12.79518189|    34.47687789|          5.18408525|         614.406914|
|alvareznancy@luca...|645 Martha Park A...|     FloralWhite|       33.87103788|12.02692534|    34.47687789|          5.49350721|        571.5721748|
+--------------------+--------------------+----------------+------------------+-----------+---------------+--------------------+-------------------+

```
now we add two more libraries:
```agsl
>>> from pyspark.ml.linalg import Vectors
>>> from pyspark.ml.feature import VectorAssembler
>>> featureassembler=VectorAssembler(inputCols=["Avg Session Length","Time on App","Time on Website","Length of Membership"],outputCol="Independent Features")
>>> output = featureassembler.transform(dataset)
>>> output.show()
>>> finalized_data = output.select("Independent Features","Yearly Amount Spent")
>>> finalized_data.show()
+--------------------+-------------------+
|Independent Features|Yearly Amount Spent|
+--------------------+-------------------+
|[34.49726773,12.6...|         587.951054|
|[31.92627203,11.1...|         487.547504|
|[33.00091476,11.3...|         581.852344|
|[34.30555663,13.7...|         599.406092|
|[33.33067252,12.7...|         614.406914|
|[33.87103788,12.0...|        571.5721748|
+--------------------+-------------------+

```
now we go for linear regression:
```agsl
>>>train_data,test_data = finalized_data.randomSplit([0.75,0.25])
>>>regressor = LinearRegression(featuresCol="Independent Features",labelCol="Yearly Amount Spent")
>>>regressor = regressor.fit(train_data)
>>> regressor.coefficients
DenseVector([95.1646, -49.738, -28.5369, -1.0407])
>>>regressor.intercept
-931.8273759558115
>>>pred_results = regressor.evaluate(test_data)
>>>pred_results.predictions.show()
```
based on https://www.youtube.com/watch?v=S2MUhGA3lEw

### running spark example
execute this in master container:
```agsl
spark-submit \
  --class org.apache.spark.examples.SparkPi \
  --master spark://spark-master:7077 \
  /opt/bitnami/spark/examples/jars/spark-examples_2.12-3.2.1.jar \
  100
```
working with SparkSQL:
```agsl
scala> val df = spark.read.json("examples/src/main/resources/people.json");
df: org.apache.spark.sql.DataFrame = [age: bigint, name: string]                

scala> df.show()
+----+-------+
| age|   name|
+----+-------+
|null|Michael|
|  30|   Andy|
|  19| Justin|
+----+-------+

scala> df.printSchema()
root
 |-- age: long (nullable = true)
 |-- name: string (nullable = true)
```


