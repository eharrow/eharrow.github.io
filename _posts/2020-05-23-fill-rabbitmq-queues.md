---
layout: post
title: Fill Rabbitmq Queues
comments: true
tags: rabbit rabbbitmq cli javascript
---
In a [previous post]({% post_url 2020-04-15-drain-rabbitmq-queues %}) I showed how to drain a queue but during some work I needed to publish some test messages so I added a new mode to publish as well.  In rabbitmq you don't publish to a queue though but to an exchange which will route the message to the appropriate queue.  The new publish mode has been added to the drainer which is a bit odd however as well as accepting a cli option to switch mode a new cli script `qfiller` has been added to compliment `qdrainer` which is added to the path as part in `npm -g install`.

![qfiller screenshot](/public/images/qfiller.png){:class="img-fluid rounded"}