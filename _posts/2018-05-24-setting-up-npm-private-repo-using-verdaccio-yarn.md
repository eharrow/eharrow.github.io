---
layout: post
title: Setting Up a Private NPM Repository with Yarn and Verdaccio
category: yarn
comments: true
---

Coming from a fifteen year Maven background (maven 1 yikes) I at some point knew that I was going to need a private repo for JS while working for any company with their own private codebase.  For a couple of years I used a combination of git repos and (shock horror) copy and paste to achieve code reuse but as the problem has been solved before and the whole point of NPM is to work in this way then it could be done.

An old stack  [question](https://stackoverflow.com/questions/7575627/can-you-host-a-private-repository-for-your-organization-to-use-with-npm) had a solution that involved mirroring using couchdb and other solutions finally lead me to [Verdaccio](http://www.verdaccio.org), a lightweight private npm proxy registry built in Node.js that works with Yarn.
