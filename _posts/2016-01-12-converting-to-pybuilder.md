---
layout: post
title: Converting to PyBuilder
tags: python maven flask
---

I have inherited a python/flask project to maintain and add a few new features to.  While I know python enough to get by I have struggled a little with the organisation of the source code.  Coming from a Java and [Apache Maven](https://maven.apache.org) background going back at least twelve years I believe teams really benefit from using a conventional project layout which is something I have yet to find in python.  That said flask seems to have some conventions but my colleagues are uncertain where the best place to keep unit tests and coming from [Junit](http://junit.org) we always keep them in a different but parallel src tree.  Builds are done using make.  Yuck.

<!--break-->
With great relief I have found [PyBuilder](http://pybuilder.github.io) which not only enforces a project layout but behaves in a very similar way to maven so I'm happy.

