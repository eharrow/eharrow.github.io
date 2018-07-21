---
layout: post
title: Setting Up a Private NPM Repository with Yarn and Verdaccio
category: yarn
comments: true
---

Coming from a fifteen year Maven background (maven 1 yikes) I at some point knew that I was going to need a private repo for JS while working for any company with their own private codebase.  For a couple of years I used a combination of git repos and (shock horror) copy and paste to achieve code reuse but as the problem has been solved before and the whole point of NPM is to work in this way then it could be done.

An old stack  [question](https://stackoverflow.com/questions/7575627/can-you-host-a-private-repository-for-your-organization-to-use-with-npm) had a solution that involved mirroring using couchdb and other solutions finally lead me to [Verdaccio](http://www.verdaccio.org), a lightweight private npm proxy registry built in Node.js that works with Yarn.

Using Docker makes it easy.

``` shell
$ docker pull verdaccio/verdaccio
```

and to start it up

``` shell
$ docker run -it --rm --name verdaccio -p 4873:4873 verdaccio/verdaccio
```

To make Yarn or NPM use it then add the url as a registry setting:
``` shell
$ npm set registry http://localhost:4873
```

or for yarn
``` shell
$ yarn config set registry http://localhost:4873
```

followed by adding a dependency
``` shell
$ yarn add lodash
yarn add v1.7.0
info No lockfile found.
(node:42164) [DEP0005] DeprecationWarning: Buffer() is deprecated due to security and usability issues. Please use the Buffer.alloc(), Buffer.allocUnsafe(), or Buffer.from() methods instead.
[1/4] 🔍  Resolving packages...
error An unexpected error occurred: "http://localhost:4873/lodash: connect ECONNREFUSED 127.0.0.1:4873".
info If you think this is a bug, please open a bug report with the information provided in "/Users/ewan/Documents/dev/callerId/js-callerid/yarn-error.log".
info Visit https://yarnpkg.com/en/docs/cli/add for documentation about this command.
```

Ooops I forgot to actually start Verdaccio.  Try again and success.

``` shell
$ yarn add lodash
yarn add v1.7.0
info No lockfile found.
[1/4] 🔍  Resolving packages...
⠁ (node:42165) [DEP0005] DeprecationWarning: Buffer() is deprecated due to security and usability issues. Please use the Buffer.alloc(), Buffer.allocUnsafe(), or Buffer.from() methods instead.
[2/4] 🚚  Fetching packages...
[3/4] 🔗  Linking dependencies...
[4/4] 📃  Building fresh packages...
success Saved lockfile.
success Saved 1 new dependency.
info Direct dependencies
└─ lodash@4.17.10
info All dependencies
└─ lodash@4.17.10
✨  Done in 1.57s.
```

To test disconnect from the internet if you can.  As I host verdaccio on my local dev at the moment I can prove that blowing away node_modules and `yarn add lodash` again does pull from the upstream verdaccio registry.

The next step is to publish private modules to the registry and use the verdaccio web UI.
