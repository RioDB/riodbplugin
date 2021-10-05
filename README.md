[![N|Solid](https://www.riodb.org/images/Logo_Name_Small.jpg)](https://www.riodb.org/index.html)

RioDB (www.riodb.org) is a downloadable software for data stream processing.  
You can build powerful data screening bots through simple SQL-like statements.

## In a nuthshell:

- You define streams that will receive data using plugins (udp, tcp, http, etc.)
- You define windows of data (example: messages received in the last 2 hours that contain the word 'free'.)
- You write queries against these windows, similarly to how you query tables in a database.
- Your queries also indicate what action to take when conditions are met.
- RioDB takes actions by using plugins (udp, tcp, http, etc.)

RioDB is ultra lightweight and fast so that you can crunch through a lot of data using very little compute resources.
Plugins are also open source and anyone can make their own custom plugin if they want to. 

---

This git repository contains the RioDBPlugin class (java), which define objects that RioDB and plugins exchange back and forth.  
If you are looking for the RioDB engine code, the repository is [RioDB/riodb](https://github.com/RioDB/riodb)    

For those who are developing a new custom plugin for RioDB, you can pull the RioDBPlugin artifact from Maven:

```sh
<dependency>
  <groupId>org.riodb</groupId>
  <artifactId>RioDBPlugin</artifactId>
  <version>0.0.3</version>
</dependency>
```
