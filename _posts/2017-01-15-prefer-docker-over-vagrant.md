---
layout: post
title: Prefer Docker over Vagrant
tags: docker
---

A year ago I was using Vagrant as my preferred  way to develop on my mac as it
meant that my development environment matched the target linux deployment and it
mostly just worked and kept my mac mostly clean from _alien_ toolchains.  My view
has changed over the past six months of using Docker - I can develop using a Docker
container.  Two examples are a Jekyll container to write up this post and a
nodeschool one for Node.js tutorials without installing Jekyll and its ruby runtime
and the whole node and npm shebang.

My early attempts at using Jekyll (with Github hosting) required going though the
ruby install which from memory was ok but it did mean that it took a little while
to get going and I am sure I hit a few issues which were solved but annoying.  This
time on a clean mac I installed [Docker for Mac](https://www.docker.com/products/docker#/mac) and [Kitematic](https://kitematic.com) aka _Docker For Dummies_ and then hunted around
Docker Hub for a Jekyll image.  
