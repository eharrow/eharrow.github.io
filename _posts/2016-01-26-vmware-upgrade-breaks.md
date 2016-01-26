---
layout: post
title: VirtualBox Upgrade Breakage
---

VirtualBox prompted that there was an update today.  After dutifully downloading and installing ```vagrant up``` failed with:

	==> default: Machine booted and ready!
	==> default: Checking for guest additions in VM...
	    default: The guest additions on this VM do not match the installed version of
	    default: VirtualBox! In most cases this is fine, but in rare cases it can
	    default: prevent things such as shared folders from working properly. If you see
	    default: shared folder errors, please make sure the guest additions within the
	    default: virtual machine match the version of VirtualBox you have installed on
	    default: your host and reload your VM.
	    default: 
	    default: Guest Additions Version: 4.3.22
	    default: VirtualBox Version: 5.0
	==> default: Mounting shared folders...
	    default: /vagrant => /Users/ewan/dev/projectx
	Failed to mount folders in Linux guest. This is usually because
	the "vboxsf" file system is not available. Please verify that
	the guest additions are properly installed in the guest and
	can work properly. The command attempted was:

	mount -t vboxsf -o uid=`id -u vagrant`,gid=`getent group vagrant | cut -d: -f3` vagrant /vagrant
	mount -t vboxsf -o uid=`id -u vagrant`,gid=`id -g vagrant` vagrant /vagrant

	The error output from the last command was:

		/sbin/mount.vboxsf: mounting failed with the error: No such device


The guest additions, now updated with the new version of VirtualBox,  need to be installed into the guest vm.  Vagrant can do this for you using the command ```vagrant plugin install vagrant-vbguest``` which can take a minute or so to run.  Run vagrant up and be prepared to wait while the vm installs the latest guest addition from your host.

	$ vagrant up;vagrant ssh
	Bringing machine 'default' up with 'virtualbox' provider...
	==> default: Checking if box 'puppetlabs/centos-6.6-64-puppet' is up to date...
	==> default: Clearing any previously set forwarded ports...
	==> default: Clearing any previously set network interfaces...
	==> default: Preparing network interfaces based on configuration...
	    default: Adapter 1: nat
	==> default: Forwarding ports...
	    default: 8484 (guest) => 8484 (host) (adapter 1)
	    default: 3306 (guest) => 3306 (host) (adapter 1)
	    default: 8181 (guest) => 8181 (host) (adapter 1)
	    default: 8282 (guest) => 8282 (host) (adapter 1)
	    default: 22 (guest) => 2222 (host) (adapter 1)
	==> default: Booting VM...
	==> default: Waiting for machine to boot. This may take a few minutes...
	    default: SSH address: 127.0.0.1:2222
	    default: SSH username: vagrant
	    default: SSH auth method: private key
	==> default: Machine booted and ready!
	GuestAdditions versions on your host (5.0.14) and guest (4.3.22) do not match.
	Loaded plugins: fastestmirror, security
	Setting up Install Process
	Loading mirror speeds from cached hostfile
	 * base: mirror.vorboss.net
	 * epel: mirror.kinamo.be
	 * extras: www.mirrorservice.org
	 * updates: centos.mirroring.pulsant.co.uk
	Package kernel-devel-2.6.32-573.12.1.el6.x86_64 already installed and latest version
	Package gcc-4.4.7-16.el6.x86_64 already installed and latest version
	Package 1:make-3.81-20.el6.x86_64 already installed and latest version
	Package 4:perl-5.10.1-141.el6_7.1.x86_64 already installed and latest version
	Package bzip2-1.0.5-7.el6_0.x86_64 already installed and latest version
	Nothing to do
	Copy iso file /Applications/VirtualBox.app/Contents/MacOS/VBoxGuestAdditions.iso into the box /tmp/VBoxGuestAdditions.iso
	Installing Virtualbox Guest Additions 5.0.14 - guest version is 4.3.22
	Verifying archive integrity... All good.
	Uncompressing VirtualBox 5.0.14 Guest Additions for Linux............
	VirtualBox Guest Additions installer
	Removing installed version 4.3.22 of VirtualBox Guest Additions...
	Copying additional installer modules ...
	Installing additional modules ...
	Removing existing VirtualBox non-DKMS kernel modules[  OK  ]
	Building the VirtualBox Guest Additions kernel modules
	Building the main Guest Additions module[  OK  ]
	Building the shared folder support module[  OK  ]
	Building the OpenGL support module[FAILED]
	(Look at /var/log/vboxadd-install.log to find out what went wrong. The module is not built but the others are.)
	Doing non-kernel setup of the Guest Additions[  OK  ]
	Starting the VirtualBox Guest Additions [  OK  ]
	Installing the Window System drivers
	Could not find the X.Org or XFree86 Window System, skipping.
	An error occurred during installation of VirtualBox Guest Additions 5.0.14. Some functionality may not work as intended.
	In most cases it is OK that the "Window System drivers" installation failed.
	==> default: Checking for guest additions in VM...
	==> default: Mounting shared folders...
	    default: /vagrant => /Users/ewan/dev/projectx
	==> default: Machine already provisioned. Run `vagrant provision` or use the `--provision`
	==> default: flag to force provisioning. Provisioners marked to run always will still run.
	Last login: Tue Jan 26 09:06:27 2016 from 10.0.2.2

