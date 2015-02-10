jtester
=======
jtester demo

usage
=======
- fork or clone this project directly
- modify jtester.properties and configure your own data source
- execute user2.sql script in sqlserver
- right click testng.xml and run as testng suite

notice
=======
if you run test on OSX, configure Default VM arguments for JRE as follows:  
-javaagent:$MAVEN_REPOSITORY/com/googlecode/jmockit/jmockit/0.999.11/jmockit-0.999.11.jar