---
layout: post
title: Adding a GPG Key to Sign Git Commits with Github and Keybase
tags: crypto git
comments: true
last_updated: 2019-03-25T13:46:54+00:00
---
If you wanted or needed to see a verified label against your git commit e.g.

![gpg-verified-commit screenshot](/public/images/gpg-verified-commit.png){:class="img-fluid rounded"}

then you need to add your gpg public key to your github account.  There are two parts to the process:
1. get and export your public key to Github.
2. set up git to sign your commits with your private key.

#### [Update 2019-03-25] SourceTree signing breaks after upgrading the version of gpg
When using homebrew to upgrade gpg remember that the symlink in `/usr/local/bin` to gpg and gpg2 (that SourceTree looks for) will change and that to make it work after an upgrade with SourceTree you will need to create a soft link to gpg2.  See [ the following link](https://kevingoedecke.me/2018/06/28/setup-sourcetree-with-homebrew-gpg-invalid-gpg-path/).

## Get, Export and Set Your Public Key
If you don't have a GPG key pair then consider [FSF's email self defence guide](https://emailselfdefense.fsf.org/en/) which is easy to follow and explains what and why pretty comprehensively.

With your public key you now need to add it to Github but first find your key and configure git to use it for signing commits.

Firstly you need to get your private or secret key id which is `B782D1B96DED7230` for me.

``` terminal
  $ gpg --list-secret-keys --keyid-format LONG
  /Users/ewan/.gnupg/pubring.kbx
  ------------------------------
  sec   rsa2048/B782D1B96DED7230 2018-03-27 [SC] [expires: 2020-03-26]
        02C478D0E5023223FFE1ABA7B782D1B96DED7230
```

Then we configure git to use it from now on whenever we commit.  You can also configure it on a per repo basis rather than for all.

``` terminal
  $ git config --global user.signingkey B782D1B96DED7230
  $ git config --global commit.gpgsign true
```

Now that is done we need to copy the associated public key to Github. Login to Github and open the keys page of settings and paste the exported public key in my case I used Keybase to do this.

``` terminal
  $ open https://github.com/settings/keys
  $ keybase pgp export -q B782D1B96DED7230 | pbcopy
```

## Signing Git Commits
During testing I needed to set the ENV GPG_TTY and a quick test from the command line showed the signing working.

``` terminal
  $ export GPG_TTY=$(tty)
  $ git commit -m "updated"
```

I usually use Sourcetree and making this sign commits was a bit more involved.  Sourcetree requires GPG v2 to sign so install it and update git to use it.  On a Mac with [homebrew](https://brew.sh) I did:

``` terminal
  $ brew install gnupg2
  $ git config --global gpg.program gpg2
```

SourceTree needs to use this version (system) as opposed to the bundled or embedded git that is used by default so under preferences select the system git.  That should work but I needed add the means to enter the secret key when signing that SourceTree lacks but [pinentry-mac](https://formulae.brew.sh/formula/pinentry-mac) can do that.

``` terminal
  $ brew install pinentry-mac
  $ echo "pinentry-program /usr/local/bin/pinentry-mac" >> ~/.gnupg/gpg-agent.conf
```

![pinentry screenshot](/public/images/pinentry.png){:class="img-fluid rounded"}

Test with a simple commit and fingers crossed git and gpg are ok.  If there are no complaints then check in Sourcetree that the committer (you) is verified and even better if the remote repo is on Github it should say verified as well.
