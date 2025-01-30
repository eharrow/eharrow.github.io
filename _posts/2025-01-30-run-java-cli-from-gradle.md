---
layout: post
title: Run Java CLI from Gradle
comments: false

tags: cli

---
Sometimes you might need to execute a Java CLI from Gradle.  I was playing around with
[Playwright](https://playwright.dev/java/) and wanted to use the `codegen` tool to gen java from recording
a surfing session.  There are [instructions](https://playwright.dev/java/docs/codegen-intro) for making
this work with Maven but not Gradle.  The trick with Gradle though is to create a new task in groovy and
then exec that with the gradle wrapper.

Assuming you have added the playwright dependency to `build.gradle` then add the following new task
```groovy
// Usage: ./gradlew playwright --args="help"
task playwright(type: JavaExec) {
  classpath sourceSets.test.runtimeClasspath
  mainClass = 'com.microsoft.playwright.CLI'
}
```
and then from the shell `./gradlew playwright --args="codegen www.google.com"`.

To sum up add a new task of type JavaExec with a useful name and supply the FQN as the mainClass.