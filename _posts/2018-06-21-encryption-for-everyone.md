---
layout: post
title: Encryption for Everyone with Keybase
category: crypto
---

Finally some bright sparks have worked out how to do public key encryption that most people could manage - not my mother-in-law - but the barriers to entry have been lowered a lot.  [Keybase](https://keybase.io) uses or wraps PGP so they have not invented anything new but have "wrapped" it in a nice desktop (Electron I think) and device UI.

For the last twenty years or so I have been using PGP on and off but struggled to get anyone else to try it as frankly it is too hard for most people that have not seen a commandline in years if at all and the GUIs for mail apps are a non-starter for webmail which in my experience most people use.  [Canary](https://canarymail.io) for Mac and ios has solved email encryption for me.

One important difference and frankly the killer feature that keybase offers is the whole web of trust thing i.e. the public key that I have acquired for you really is yours.  [FSF's email self defence guide](https://emailselfdefense.fsf.org/en/) (nothing to do with Keybase) runs you through the basics of setting up public and private keys and then takes you through signing other peoples keys - the web of trust - but it does rely on key exchange and there really seems to be a lack browsable key discovery from  my albeit limited experience.  Keybase solves this issue by verifying your identity based on the internet services you use such as Twitter, Github and even your own domain.  Verification during the account creation usually involves publishing something that Keybase can check and presumably as you are the only one able to post a tweet to your account

<blockquote class="twitter-tweet" data-lang="en-gb"><p lang="en" dir="ltr">Verifying myself: I am ewanharrow on <a href="https://t.co/oJYb6weDDA">https://t.co/oJYb6weDDA</a>. EBitNXS7vNLxAEPlWjBbMmfrEmYCeMeIMS6i / <a href="https://t.co/GYqHWwo922">https://t.co/GYqHWwo922</a></p>&mdash; Ewan Harrow (@ewanharrow) <a href="https://twitter.com/ewanharrow/status/1004710267907866624?ref_src=twsrc%5Etfw">7 June 2018</a></blockquote> <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>


or create a [gist](https://gist.github.com/eharrow/6a12e5b2c160cc26d2a09cb898ca6a6e) or add a [TXT DNS record](https://dig.whois.com.au/dig/ewanharrow.com) to your own domain then *you* are who you say you are.

This means that someone communicating with me via the Keybase app can trust that I am who I purport to be.  Nice features are end-to-end encrypted chat and end-to-end encrypted file sharing (no cleartext or metadata such as file names stored in the clear).  They now support teams with chat and file sharing as well for free and again encrypted like Slack.  Recently git repos have been added so that even the repo name is effectively encrypted let alone the files and commit history.

When you create an account, set up or import your keys and verify yourself you then have a public facing profile page such as [keybase.io/ewanharrow](https://keybase.io/ewanharrow).  From here you could encrypt a message using my keybase public key or verify a message I might have sent you and thats just in the browser.  ![My helpful screenshot]({{ "/public/keybase-dot-io-encrypt.png" | absolute_url }})


Want to import my public key then just copy it or follow the supplied keybase instructions - too easy.
