---
layout: post
title: Configure a Service to Send Email Via Gmail SMTP
# tags: mac
comments: true
---
It turns out I have lost the email notifications my Synology NAS had been sending
me - specifically the monthly disk checks.  The issue seems to be due to the way
Google changed Gmail SMTP authentication i.e. my NAS uses Gmail to send the email
and at some point this changed to support 2 factor authentication and stopped working.

A few sites seem to have old settings that never worked but the following worked for me once
I created an app specific password in my google account just for this particular
purpose that meant 2FA was not required.

* SMTP Server: smtp.googlemail.com
* SMTP Port: 587
* SMTP TLS/SSL required: yes
* SMTP username: Your full Gmail address
* SMTP password: App password created in you google account - https://myaccount.google.com/apppasswords

