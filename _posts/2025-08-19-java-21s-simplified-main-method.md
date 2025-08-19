---
layout: post
title: "Java 21's Simplified Main Method"
date: 2025-08-19
categories: java programming
---
## Introducing Unnamed Classes and Simplified Main Methods in Java

Java has long been known for its verbosity, especially when it comes to writing simple scripts or
small programs. With <b>Java 21's introduction of simplified main methods</b>, developers can now
write more concise and readable entry points for their applications.  The feature is primarily to
teach Java by just making it easier to start.

### The Traditional Main Method

Traditionally, Java required a verbose main method declaration in an enclosing class:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### The New Simplified Approach

Now, Java allows a more streamlined syntax that reduces boilerplate code:
```java
void main() {
    System.out.println("Hello, World!");
}
```
No surrounding `public class`, no `public static` and no `String[] args` - much easier to parse for a new
starter to the language.

This can be stripped back further from Java 24 using the new static methods from the IO class:
```java
void main() {
    IO.println("Hello, World!");
}
```
### Scripting Superpowers

The new main method syntax comes with some exciting features that make Java more script-friendly:
1. Direct Execution
You can now run Java files directly without explicit compilation as `java Helloworld.java`
2. Shell Script Compatibility
An ingenious trick allows you to make Java files executable - paste this comment into the top of the
java source file `///usr/bin/env java "$0" "$@" ; exit $?`.
   When added to the top of your Java file, this line:
   * Makes the file directly executable
   * Remains valid Java code
   * Works across most shell environments

    ```java
    ///usr/bin/env java "$0" "$@" ; exit $?
    
    void main() {
        IO.println("Hello, World!");
    }
    ```
    and then:
    ```shell
   $ chmod +x Helloworld.java
   $ ./Helloworld.java
   ```
   
## Practical Benefits
* Easier for Beginners
    * Reduced boilerplate
    * More approachable syntax
    * Lower entry barrier for new developers
    * Scripting Workflow Improvements

* Direct file execution
    * No need for separate compilation step
    * Seamless integration with shell scripts

## Third Party Libraries
How do you include these? Just standard Java classpath.  Rather than create a fairly heavyweight
maven or gradle project for a simple script lets just add the dependencies into the current
directory and using the 3rd party [jpm](https://github.com/codejive/java-jpm/releases) package
manager.  Install it and then install jfiglet to make a banner.
```java
///usr/bin/env java "$0" "$@" ; exit $?

import com.github.lalyos.jfiglet.FigletFont;

void main(String[] args) {
  try {
    if (args.length == 0) {
      print(FigletFont.convertOneLine("Hello world!"));
    } else {
      print(FigletFont.convertOneLine(args[0]));
    }
  } catch (java.lang.Exception e) {
    System.err.println(e);
  }
}
```
```shell
$ java --enable-preview -cp deps/* Helloworld.java 
  _   _      _ _                            _     _ _ 
 | | | | ___| | | ___   __      _____  _ __| | __| | |
 | |_| |/ _ \ | |/ _ \  \ \ /\ / / _ \| '__| |/ _` | |
 |  _  |  __/ | | (_) |  \ V  V / (_) | |  | | (_| |_|
 |_| |_|\___|_|_|\___/    \_/\_/ \___/|_|  |_|\__,_(_)
                                                      
```
You'll notice in this version how the flexible `main` signature can accept cli arguments as well so we can use it
to specify the text to output.
```shell
 $ java --enable-preview -cp deps/* Helloworld.java "It is ten o'clock"
  ___ _     _       _                     _      _            _    
 |_ _| |_  (_)___  | |_ ___ _ __     ___ ( ) ___| | ___   ___| | __
  | || __| | / __| | __/ _ \ '_ \   / _ \|/ / __| |/ _ \ / __| |/ /
  | || |_  | \__ \ | ||  __/ | | | | (_) | | (__| | (_) | (__|   < 
 |___|\__| |_|___/  \__\___|_| |_|  \___/   \___|_|\___/ \___|_|\_\
 
```
We need the `enable-preview` switch until it is officially released in Java 25.

## Performance
It is not super quick as there is still a hidden compile step going on under the covers but as a way
to write scripts it works very well.

```shell
$ time java --enable-preview -cp deps/* Helloworld.java 
  _   _      _ _                            _     _ _ 
 | | | | ___| | | ___   __      _____  _ __| | __| | |
 | |_| |/ _ \ | |/ _ \  \ \ /\ / / _ \| '__| |/ _` | |
 |  _  |  __/ | | (_) |  \ V  V / (_) | |  | | (_| |_|
 |_| |_|\___|_|_|\___/    \_/\_/ \___/|_|  |_|\__,_(_)
                                                      

real    0m0.385s
user    0m0.811s
sys     0m0.084s
```
Comparing the two methods to make this run we can see that normal pre-compiling with `javac` and then
executing with `java` is faster.
```shell
$ javac --enable-preview --release 24 -cp deps/* Helloworld.java 
$ time java --enable-preview -cp deps/*:. Helloworld
  _   _      _ _                            _     _ _ 
 | | | | ___| | | ___   __      _____  _ __| | __| | |
 | |_| |/ _ \ | |/ _ \  \ \ /\ / / _ \| '__| |/ _` | |
 |  _  |  __/ | | (_) |  \ V  V / (_) | |  | | (_| |_|
 |_| |_|\___|_|_|\___/    \_/\_/ \___/|_|  |_|\__,_(_)
                                                      

real    0m0.083s
user    0m0.105s
sys     0m0.038s
```

### Graal Native Image
As an aside how about seeing how fast we can make it run as a native image.
Do the following:
1. Compile the class as before `javac --enable-preview --release 24 -cp deps/* Helloworld.java `
2. Run the Graal native image tool `native-image --enable-preview -H:IncludeResources=".*/*.flf" -cp deps/*:. Helloworld`
In addition to passing the dependencies as classpath arguments plus the classfile we also need to tell
the native compiler that we want to include resources in this case files in the dependency jar needed
at runtime.  Miss these and the input stream used to read them fails.

```shell
$ time ./helloworld 
  _   _      _ _                            _     _ _ 
 | | | | ___| | | ___   __      _____  _ __| | __| | |
 | |_| |/ _ \ | |/ _ \  \ \ /\ / / _ \| '__| |/ _` | |
 |  _  |  __/ | | (_) |  \ V  V / (_) | |  | | (_| |_|
 |_| |_|\___|_|_|\___/    \_/\_/ \___/|_|  |_|\__,_(_)
                                                      

real    0m0.017s
user    0m0.006s
sys     0m0.011s
```
Very quick but we obviously trade the scripting benefits.