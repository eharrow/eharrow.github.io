---
layout: post
title: Centos Port Forwarding
---
Before I forget I had an issue with port forwarding a webapp from the base centos vm to my host through vagrant - the webapp was unresponsive and requests would eventually timeout.  In the past the issue has always been starting the webapp listening on a non-“0.0.0.0” adapter but the webapp's config is quite explicit that it is set to listen on the right one.  A quick nestat test proved this:

`$ netstat -o -n -a | grep 84`

```vagrant@localhost vagrant]$ netstat -o -n -a | grep 84
tcp        0      0 0.0.0.0:58494               0.0.0.0:*                   LISTEN      off (0.00/0/0)
tcp        0      0 0.0.0.0:8484                0.0.0.0:*                   LISTEN      off (0.00/0/0)
unix  2      [ ACC ]     STREAM     LISTENING     10584  private/scache
```
<!--break-->

That is not the problem then.  Further investigation using `sudo tcpdump -i any not port 22` showed requests coming in and yet no response in the browser.

The issue turns out to be that the base centos image has iptables set up to stop it.

`sudo iptable -F` resolved it and now outbound responses are routed back.

Note that this does not affect a base Ubuntu image.