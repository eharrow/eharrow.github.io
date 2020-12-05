---
layout: post
title: Curl The Ultimate Swiss Army Knife?
comments: false

tags: cli

---
When one needs to test the connection from a kubernetes pod to an external service it can be difficult if the pod is based on an image with the bare minimal of networking utilities such as ping or traceroute.  Happily enough *Curl* seems to be quite often available and while testing http is easy enough what do you do for other protocols?

### SFTP?
Easy.  `curl  -k "sftp://remote.host:22/" --user "user:password"`.  File transfers and directory creation is supported as well.

There are quite a few [supported protocols](https://curl.se/docs/manpage.html) including LDAP and even MQTT.
