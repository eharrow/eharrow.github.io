---
layout: post
title: Crikey A New Year
---

How hopeless!  My last post was nearly a year ago - so much for a new year resolution to blog regularly.

I'll try to catch up by listing the things I have been up to.

1. Python/Flask web dashboard converted to Bootstrap and new stuff added.
1. Node app using [EIP routing patterns](http://www.enterpriseintegrationpatterns.com/patterns/messaging/index.html) to take raw sightings data when a user is
detected and enriched through a series of transformations and filters to display
pretty images of sighted users and their known demographic data.  Raw data was
extracted from MySQL and pushed as JSON to a STOMP queue (ActiveMQ broker) where it was consumed by a Node app that applied the filters and transformations.  Final piece to the jigsaw was a simple express webapp that served up a static page with javascript and websockets to display the users in realtime on the dashboard.  Architecturally this application was built on my previous experience of applying EIP patterns using Apache
Camel for a number of big Java sites with enough complex integration to make it a no brainer.
1. A services platform to manage devices in the field.  REST webservices built with Restify/Node, MongoDB and MQTT for the backend and an EmberJS frontend.  All deployed in a number of Docker containers.  I'll document this architecture, issues and work arounds another time.  Probably next year.
