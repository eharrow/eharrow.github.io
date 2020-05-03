---
layout: post
title: Drain RabbitMQ Queues
tags: javascript
comments: true
---
One thing that I find that we always need to do when developing with message queues is to occassionally remove, purge or drain all the messages from a queue.  More often than not this is after some testing and the messages are not not being acked and removed in the usual way but it might be that we just want to test publishing to a queue and need to verify that the messages are sent.

For Rabbit AMQP queues I have a small [CLI](https://github.com/eharrow/amq-queue-drainer) written in typescript for running with node that does the job.

### Install
Git clone then `npm install && npm run build`

### Use
```
$ node .
   ___      ____                   _                       
  / _ \    |  _ \   _ __    __ _  (_)  _ __     ___   _ __ 
 | | | |   | | | | | '__|  / _` | | | | '_ \   / _ \ | '__|
 | |_| |   | |_| | | |    | (_| | | | | | | | |  __/ | |   
  \__\_\   |____/  |_|     \__,_| |_| |_| |_|  \___| |_|   
                                                           
one or more missing arguments
Usage: queue-drainer [options]

Options:
  -V, --version              output the version number
  -h, --host [host]          rabbit host
  -p, --port [port]          rabbit port [5672] (default: 5672)
  -v, --vhost [vhost]        virtual host [/] (default: "")
  -u, --user [user]          rabbit user
  -c, --password [password]  rabbit password
  -q, --queue [queue]        queue name e.g. queue1
  -l, --log-message          log the dequeued message
  -n, --num-to-consume       number of messages to consume [default all]
  -h, --help                 output usage information
```
To connect to a broker with a queue named test_q consume and log all messages, in this case a local one, run with the following:
```
$ node . -h localhost -p 5672 -q test_q -l
 _____     _____  _____  _____  ___  _____  _____  _____ 
/  _  \   |  _  \/  _  \/  _  \/___\/  _  \/   __\/  _  \
|  |  |   |  |  ||  _  <|  _  ||   ||  |  ||   __||  _  <
\___\ \   |_____/\__|\_/\__|__/\___/\__|__/\_____/\__|\_/
      /                                                  
connecting to... amqp://localhost:5672
 [*] Waiting for messages in test_q. To exit press CTRL+C
did
message: 1 ✅
waiting.. \ ^C#
```

<kbd>CTRL-C</kbd> to exit.

Additional options to support virtual hosts, don't log messages and authentication are supported.

