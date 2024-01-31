[![N|Solid](https://www.riodb.org/images/Logo_Name_Small.jpg)](https://www.riodb.org/index.html)

RioDB (www.riodb.org) is a downloadable software for data stream processing.  
You can build powerful real-time analytics solutions using simple SQL-like statements.  

## riodbplugin:  

This git repository contains the RioDBPlugin class (java), which is required for creating custom plugins for RioDB.  
This class can be used to build INPUT plugins, OUTPUT plugins, and PARSER plugins.

For those who are developing a new custom plugin for RioDB, you can pull the RioDBPlugin artifact from Maven:

```sh
<dependency>
  <groupId>org.riodb</groupId>
  <artifactId>RioDBPlugin</artifactId>
  <version>0.0.4</version>
</dependency>
```
