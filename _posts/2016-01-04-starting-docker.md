---
layout: post
title: Docker Day Today
---

Today is the first day of a new project that involves capturing, persisting and reporting on a lot of data.  It needs to have very HA and be really quick so the weapons of choice are [MongoDB](https://www.mongodb.org) and [Node.js](https://nodejs.org) - basically the [MEAN](http://mean.io) stack.

<!--break-->
As it is a new project and new toys I plan to encourage the use of vagrant and docker.  Both are new to the client and to be frank Docker is newish to me but I can see the benefits and have played with it.  To that end I am ploughing through [Using Docker](http://www.amazon.co.uk/Using-Docker-Adrian-Mouat/dp/1491915765/ref=sr_1_1?ie=UTF8&qid=1451904523&sr=8-1&keywords=using+docker).

[update] Looks like my first piece of work will be to implement a REST call to an existing python/flask webapp.  This [post](http://blog.miguelgrinberg.com/post/designing-a-restful-api-with-python-and-flask) seems to cover it quite well.  Have decided to add this in a vagrant vm which has meant installing pip using <code>sudo apt-get install -y python-pip</code> and then following the instructions above.