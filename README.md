# Java Dax Maven 访问

使用如下脚本:
```shell
mvn compile exec:java -X -Dexec.mainClass="org.example.basicapp.GetMoviefromDax"   -Dexec.cleanupDaemonThreads=false -Dexec.args="daxtest.s9apfk.dax-clusters.us-west-2.amazonaws.com 8111"
```
