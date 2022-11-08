<h1 align="center">Jyme</h1>

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/c6ac604d18a445ae99f34cda5c802ce1)](https://www.codacy.com/gh/goldentg/Jyme/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=goldentg/Jyme&amp;utm_campaign=Badge_Grade)
***
This bot is currently still in heavy development. Some commands may not work as intended and new features are being added often. If you experience any issues with the bot you can speed up the development process by posting an issue on the issue board
***

## **Table of Contents**
***
* [Commands](#commands)
* [Events](#events)
* [Installation](#installation)
* [Configuration](#configuration)
* [Support](#support)
***

## **Commands**
***

`Bot Owner`
------------
| Command Name | Command Purpose                                                                     |
|:------------:|-------------------------------------------------------------------------------------|
|     host     | Displays information about the bot host machine like memory and CPU information     |
|   servers    | Displays the amount of servers the bot is in as well as some additional information |
| stop | Stops the bot |
***

`Fun`
------------
| Command Name | Command Purpose                                                                     |
|:------------:|-------------------------------------------------------------------------------------|
| binary | Converts a message into binary | 
| coinflip | Flips a coin |
| hug | hug a friend | 
| 8ball | get some advice from a ball of wisdom | 
 | profile | enlarge a users avatar | 



`Admin`
------------
| Command Name | Command Purpose         |
|:------------:|-------------------------|
| kick | Kick a user             |
| ban | Ban a user              | 
| say | Make the bot talk       | 
| poll | Create a community poll |

`Function`
------------
| Command Name | Command Purpose                                         |
|:------------:|---------------------------------------------------------|
| help | Display a list of availible commands with a description | 
| ping | Ping the bot                                            | 
| botinfo | Displays some information on the bot  |
| serverinfo | Displays some info about the server |
| userinfo | Display some information about a mentioned user |  
| uptime | See how long the bot has been running |
| report | Makes it easy for members to report a bug for this bot | 


## **Events**
***
Information of the bot getting added or removed from a server as well as a server changing its name is logged.

## **Installation**
***
*** Temporary installation instructions ***

Use the `build.gradle` file to get required dependencies. Create the file `/Jyme/src/main/java/Jyme/secret/Token.java` and add the following within the created file:
```java
package Jyme.secret;

public interface Token {
    String token = "YOUR-TOKEN-HERE";
}
```
You can then run the bot from the `Main.java` file. 

## **Configuration**
***
Most of the configuration can be done from within the `Main.java` file, you can change the bot activity status and type as well as logging settings. I am actively working on making this bot as customizable as possible and many more options will be found here soon.  
***

## **Support**
***
This is a work in progress. If you are having issues with the functionality of the bot please [create an issue ticket](https://github.com/goldentg/Jyme/issues). You can reach out for support or suggestions/recommendations in the [discussion board](https://github.com/goldentg/Jyme/discussions)

If you are looking for a more advanced bot, [Check out my C# bot Nyme](https://github.com/goldentg/Nyme)

***
Made with
![IntelliJ IDEA logo](https://resources.jetbrains.com.cn/storage/products/company/brand/logos/IntelliJ_IDEA.png)
***