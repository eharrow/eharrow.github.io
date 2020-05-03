---
layout: post
title: Fix Yarn Upgrade Brew Issue
tags: yarn mac
comments: true
---

I recently needed to upgrade Yarn and Brew told me to run `brew yarn upgrade` however that failed in the past so I moved on.  Today though I decided to fix the issue.  A `brew update` on my newly upgrade High Sierra mac left me with

``` terminal
  $ brew update
  xcrun: error: invalid active developer path (/Library/Developer/CommandLineTools), missing xcrun at: /Library/Developer/CommandLineTools/usr/bin/xcrun
  Updated 1 tap (homebrew/core).
  ==> New Formulae
  ...
  ...
  ...
```

I don't use XCode though I appreciate the command line tools and dependencies and happily enough [someone else went through this pain](https://apple.stackexchange.com/questions/209624/how-to-fix-homebrew-error-invalid-active-developer-path-after-upgrade-to-os-x).

The trick

``` terminal
  $ sudo xcode-select --install
  $ sudo xcode-select -switch /
```

Then do a `brew update` which probably won't be required followed by the yarn upgrade.

``` terminal
  $ brew upgrade yarn
  ==> Upgrading 1 outdated package, with result:
  yarn 1.2.1
  ==> Upgrading yarn
  ==> Installing dependencies for yarn: icu4c, node
  ==> Installing yarn dependency: icu4c
  ==> Downloading https://homebrew.bintray.com/bottles/icu4c-59.1_1.high_sierra.bottle.tar.gz
  ==> Downloading from https://akamai.bintray.com/f0/f03168a285d1b2ee092e527655c0d78ffe9aa10f800d4a4d11a9bfdc0deb6315?__gda__=exp=1509357213~hmac=9b53fcbd26350b3289efe7e9da04d11ca1bd4b4dd8f11eafd54b709bcaffb
  ######################################################################## 100.0%
  ==> Pouring icu4c-59.1_1.high_sierra.bottle.tar.gz
  ==> Caveats
  This formula is keg-only, which means it was not symlinked into /usr/local,
  because macOS provides libicucore.dylib (but nothing else).

  If you need to have this software first in your PATH run:
    echo 'export PATH="/usr/local/opt/icu4c/bin:$PATH"' >> ~/.bash_profile
    echo 'export PATH="/usr/local/opt/icu4c/sbin:$PATH"' >> ~/.bash_profile

  For compilers to find this software you may need to set:
      LDFLAGS:  -L/usr/local/opt/icu4c/lib
      CPPFLAGS: -I/usr/local/opt/icu4c/include
  For pkg-config to find this software you may need to set:
      PKG_CONFIG_PATH: /usr/local/opt/icu4c/lib/pkgconfig

  ==> Summary
  🍺  /usr/local/Cellar/icu4c/59.1_1: 246 files, 65.3MB
  ==> Installing yarn dependency: node
  ==> Downloading https://homebrew.bintray.com/bottles/node-8.8.1.high_sierra.bottle.tar.gz
  ==> Downloading from https://akamai.bintray.com/5c/5c6b461412ed7935dc4969cf22bef33e6661caa02ccc2ae88c832dc1d6f05186?__gda__=exp=1509357226~hmac=4909b3e56554d69879647fc8d3e7b1032dd9c7b51ec4e8854d7e83d19199f
  ######################################################################## 100.0%
  ==> Pouring node-8.8.1.high_sierra.bottle.tar.gz
  ==> Caveats
  Bash completion has been installed to:
    /usr/local/etc/bash_completion.d
  ==> Summary
  🍺  /usr/local/Cellar/node/8.8.1: 3,830 files, 45.5MB
  ==> Installing yarn
  ==> Downloading https://yarnpkg.com/downloads/1.2.1/yarn-v1.2.1.tar.gz
  ==> Downloading from https://github.com/yarnpkg/yarn/releases/download/v1.2.1/yarn-v1.2.1.tar.gz
  ######################################################################## 100.0%
  🍺  /usr/local/Cellar/yarn/1.2.1: 14 files, 3.9MB, built in 4 seconds
```

Check that it was upgraded with `yarn --version`
