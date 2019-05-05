---
layout: post
title: How to Use Your Own Domain For Github Pages
tags: git
comments: false
---
A Github Pages site is great for hosting a git project's website but can also be used for your own personal site if you like.
It is pretty easy to [setup](https://pages.github.com) and is free.  The url of the site will be _username.github.io_ which seems
fine.  This site is hosted on Github Pages and I have been pretty pleased with it in particular as https is always forced.

Browsing over the doco the other day I noticed that you can use your own domain with subdomain which sounded interesting.  The details for setting this up are [here](https://help.github.com/en/articles/using-a-custom-domain-with-github-pages) - simple stuff if you can change your domain's DNS records.  The output `dig` output should look something like the following for my _blog_ :
```terminal
ewan$ dig blog.ewanharrow.com +nostats +nocomments +nocmd

; <<>> DiG 9.10.6 <<>> blog.ewanharrow.com +nostats +nocomments +nocmd
;; global options: +cmd
;blog.ewanharrow.com.		IN	A
blog.ewanharrow.com.	300	IN	CNAME	eharrow.github.io.
eharrow.github.io.	2731	IN	A	185.199.108.153
eharrow.github.io.	2731	IN	A	185.199.109.153
eharrow.github.io.	2731	IN	A	185.199.110.153
eharrow.github.io.	2731	IN	A	185.199.111.153
```
However [https://blog.ewanharrow.com](https://blog.ewanharrow.com) ended up with a github error page.  A little further reading said that I needed to [enable](https://help.github.com/en/articles/adding-or-removing-a-custom-domain-for-your-github-pages-site) the custom domain on the github pages and voila [https://blog.ewanharrow.com](https://blog.ewanharrow.com) now works once you add the custom domain name and check the https box.

![github-pages-config screenshot](/public/images/github-pages-config.png){:class="img-fluid"}
