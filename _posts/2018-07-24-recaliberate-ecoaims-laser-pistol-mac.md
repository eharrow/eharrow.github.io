---
layout: post
title: Recaliberate an EcoAims Laser Pistol with a Mac
categories: mac
comments: false
---
We have had the EcoAims PP500 laser pistol for modern pentathlon competitions for a few months now and used it out of the box without any adjustments.  Recently I found a new place at home which made a decent ten metre range however no of us could hit the target.  From five metres it was generally fine but pulled a bit to the left but we compensated for it.  At ten metres we were off by easily ten cms so I needed to adjust the rear sight.

The PP500 can be set to *fire* a pulsating LED to help adjust the sight.  To enable this mode you have to plug the pistol into a USB port and run an app that [EcoAims supply](http://www.ecoaims.com/page/11/download-software).  The issue for me is that it is a Win32 or windows app.

There are only two ways to run it on a mac:
1. Wine
2. Virtualisation

I didn't want to install any virtualisation software and certainly didn't have a windows license anyway so I tried the first option.

### Wine
[Wine](https://www.winehq.org) is a framework that *pretends* to be the windows operating system to a windows app when run on a different host such as a Mac.  Quite a lot of software does run but not in the case of the EcoAims laser control app.  I was unsure if it was the version of wine I was trying or missing this or that but I could not be bothered to explore that rat hole any more than I had.

### Virtualisation
I wanted to avoid this option simply because of licensing issues but it did work!  I'll explain how to do it.

Virtualisation meant running a complete windows 10 installation on my mac and the laser control app in that.  I used [VirtualBox](https://www.virtualbox.org) as it is free and works very well.  I also found that it is possible these days to install Windows in it without a license.  I don't know how long that has been an option as I have not had to install windows for at least ten years.

Lets Go.
1. [Download](https://www.virtualbox.org/wiki/Downloads) and install virtualbox for the Mac.  Choose *OS X hosts* for a Mac.
1. Follow [these instructions](https://betanews.com/2015/07/29/how-to-install-windows-10-on-oracle-virtualbox-no-windows-key-required/) to create a vm.
1. [Download](https://betanews.com/2015/07/29/how-to-download-windows-10-and-create-your-own-installation-usb-flash-drive-or-dvd/) a windows 10 image from Microsoft.
1. When starting up the vm choose the Microsoft windows 10 ISO you downloaded.
1. Start it up and login using the account you created on Microsoft's site.  Personally I used my old Skype login (owned by Microsoft these days).
1. Open the browser inside the vm and [download the EcoAims Laser Control 1.0 software](http://www.ecoaims.com/page/11/download-software).
1. Download the recommended drivers that it needs to connect to the pistol.  Install them.
1. Reboot the windows VM in virtualbox.
1. Plugin the pistol via the USB cable and turn the pistol on.  In the VirtualBox menubar will be an item for USB devices select it and choose <driver name>.
1. In the windows VM double clicke the downloaded laser control software which will open and hopefully have COM1, COM2 or COM3 in the field.  If so click the *Connect* button.
1. Point the pistol at the target and adjust the rear sights for horizontal and vertical differences.
1. Shoot to test.
1. Readjust as necessary.
