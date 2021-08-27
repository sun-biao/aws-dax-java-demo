mvn compile exec:java -X -Dexec.mainClass="org.example.basicapp.GetMoviefromDax"   -Dexec.cleanupDaemonThreads=false -Dexec.args="daxtest.s9apfk.dax-clusters.us-west-2.amazonaws.com 8111 us-west-2"
java -jar ./target/ddb-demo-jar-with-dependencies.jar  daxtest.s9apfk.dax-clusters.us-west-2.amazonaws.com 8111 us-west-2
mvn clean compile assembly:single
