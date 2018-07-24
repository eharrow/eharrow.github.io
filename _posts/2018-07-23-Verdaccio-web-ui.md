---
layout: post
title: Verdaccio Publishing
categories: yarn
comments: true
---
This is a follow on post to [Setting Up a Private NPM Repository with Yarn and Verdaccio]({{ site.baseurl }}{% post_url 2018-05-24-setting-up-npm-private-repo-using-verdaccio-yarn %}) to illustrate publishing to Verdaccio.  Verdaccio seems to work pretty well as a NPM proxy and *private* registry and comes with a web UI to browse your published packages and their dependencies.

Lets get started.  Firstly I'll assume that you have verdaccio installed and running.  I prefer to use Docker and briefly go over booting it up in the previous post above.  The next task is that we will create a brand new clean node module.

### Create a Test Module
Create a new directory which I'll call my-test-mod and `yarn init`.  Tip make this a public module which might seem counter to what you would usually do but NPM and Yarn won't publish at all if it is set to private in package.json - don't worry you are only publishing to your verdaccio and not to npm.org.

``` shell
Tognini:dev ewan$ mkdir my-test-mod
Tognini:dev ewan$ cd my-test-mod/
Tognini:my-test-mod ewan$ yarn init
yarn init v1.7.0
question name (my-test-mod):
question version (1.0.0): 1.0.0-SNAPHOT
question description: A test module for publishing
question entry point (index.js):
question repository url:
question author: Ewan Harrow
question license (MIT):
question private:
success Saved package.json
✨  Done in 81.77s.
```

I'll setup eslint as well to stop my ide moaning:
``` shell
Tognini:my-test-mod ewan$ eslint --init
? How would you like to configure ESLint? Use a popular style guide
? Which style guide do you want to follow? Google
? What format do you want your config file to be in? JavaScript
Checking peerDependencies of eslint-config-google@latest
Installing eslint-config-google@latest
npm notice created a lockfile as package-lock.json. You should commit this file.
npm WARN eslint-config-google@0.9.1 requires a peer of eslint@>=4.1.1 but none is installed. You must install peer dependencies yourself.

+ eslint-config-google@0.9.1
added 1 package from 1 contributor and audited 1 package in 1.142s
found 0 vulnerabilities

Successfully created .eslintrc.js file in /Users/ewan/Documents/dev/my-test-mod
```

Finally I'll add the dependency to my project:
``` shell
Tognini:my-test-mod ewan$ yarn add lodash
yarn add v1.7.0
info No lockfile found.
[1/4] 🔍  Resolving packages...
⠁ (node:64308) [DEP0005] DeprecationWarning: Buffer() is deprecated due to security and usability issues. Please use the Buffer.alloc(), Buffer.allocUnsafe(), or Buffer.from() methods instead.
[2/4] 🚚  Fetching packages...
[3/4] 🔗  Linking dependencies...
warning " > eslint-config-google@0.9.1" has unmet peer dependency "eslint@>=4.1.1".
[4/4] 📃  Building fresh packages...
success Saved lockfile.
success Saved 2 new dependencies.
info Direct dependencies
├─ eslint-config-google@0.9.1
└─ lodash@4.17.10
info All dependencies
├─ eslint-config-google@0.9.1
└─ lodash@4.17.10
✨  Done in 1.09s.
Tognini:my-test-mod ewan$
```


I'd better add some source as well:

``` shell
Tognini:my-test-mod ewan$ echo "console.log('test');" > index.js
Tognini:my-test-mod ewan$ node index.js
test
```

### Publish to Verdaccio
Login to the package manager which will take care of adding your account to the registry.

``` shell
Tognini:my-test-mod ewan$ yarn login
yarn login v1.7.0
question npm username: erch
question npm email: ewan@ha****.org
✨  Done in 9.91s.
```

You won't be asked for credentials until you try to publish.

``` shell
Tognini:my-test-mod ewan$ yarn publish
yarn publish v1.7.0
[1/4] Bumping version...
info Current version: 1.0.0-SNAPHOT
question New version:
info Proceeding with current version: 1.0.0-SNAPHOT
[2/4] Logging in...
info npm username: erch
info npm email: ewan@ha****.org
question npm password:
(node:65052) [DEP0005] DeprecationWarning: Buffer() is deprecated due to security and usability issues. Please use the Buffer.alloc(), Buffer.allocUnsafe(), or Buffer.from() methods instead.
success Logged in.
[3/4] Publishing...
success Published.
[4/4] Revoking token...
success Revoked login token.
✨  Done in 4.98s.
```

If you now go to http://localhost:4873 you should see your module listed and drilling down will show the package's details derived from package.json.

<img src="/public/verdaccio-published-module.png" class="img-fluid">

### Testing the Registry
We might be able to see the new module in the verdaccio registry but it needs to function as a private NPM registry as well i.e. I can use this new module or package from another private module.

Therefore create another new module.

``` shell
Tognini:dev ewan$ mkdir my-test-app-using-test-module
Tognini:dev ewan$ cd my-test-app-using-test-module/
Tognini:my-test-app-using-test-module ewan$ yarn init
yarn init v1.7.0
question name (my-test-app-using-test-module):
question version (1.0.0): 1.0.0-SNAPSHOT
question description: an app that uses my-test-mod
question entry point (index.js):
question repository url:
question author: Ewan Harrow
question license (MIT):
question private:
success Saved package.json
✨  Done in 38.69s.
```

Now add the test modules as a dependency usng yarn:

``` shell
Tognini:my-test-app-using-test-module ewan$ yarn add my-test-mod
yarn add v1.7.0
info No lockfile found.
[1/4] 🔍  Resolving packages...
⠁ (node:65153) [DEP0005] DeprecationWarning: Buffer() is deprecated due to security and usability issues. Please use the Buffer.alloc(), Buffer.allocUnsafe(), or Buffer.from() methods instead.
[2/4] 🚚  Fetching packages...
[3/4] 🔗  Linking dependencies...
[4/4] 📃  Building fresh packages...
success Saved lockfile.
success Saved 3 new dependencies.
info Direct dependencies
└─ my-test-mod@1.0.0-SNAPHOT
info All dependencies
├─ lodash@4.17.10
└─ my-test-mod@1.0.0-SNAPHOT
✨  Done in 2.30s.
```

Check that the module `my-test-mod` has been pulled in from the registry:

``` shell
Tognini:my-test-app-using-test-module ewan$ ls node_modules/
lodash			my-test-mod
Tognini:my-test-app-using-test-module ewan$ ls node_modules/my-test-mod/
index.js		package-lock.json	package.json
```
