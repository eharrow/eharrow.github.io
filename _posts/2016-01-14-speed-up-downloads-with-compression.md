---
layout: post
title: Speed Up Downloads With Compression
---
It might sound pretty obvious to anyone working with web servers that a site's assets when delivered compressed has a major boost on  page performance and the benefits that brings as well as network load and the associated costs.  I came across the issue today where IOT style devices communicating over a mobile operator's wireless link would often fail or be patchy at best particularly during provisioing when fairly large amounts of XML where being transported.

The "client" effectively uses curl to contact the provisioning server over HTTP.  A quick ```curl -I -H 'Accept-Encoding: gzip,deflate' http://172.NNN.NNN.NNN``` showed that compression looked like it was disabled.  Routing around a bit it turned out that the provisioning server is a actually a Glassfish instance with no web server sitting in front of it to terminate SSL or any other filtering.

Browsing to ```http://172.NNN.NNN.NNN:4848``` and logging into the admin console and ```Network Config > Network Listener > HTTP tab``` showed that indeed compression was off.  We now have a plan to enable this and thoroughly test and if we are correct then we ought to be able to save up 80% through put.