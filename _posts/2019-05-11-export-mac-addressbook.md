---
layout: post
title: How to Export a Mac's Contacts or Address Book
tags: mac email automation
comments: true
---
As part of my [callerid](https://github.com/eharrow/callerid) project I use a simple CSV address book to lookup names from an inbound call.  Having to maintain this manually in a spreadsheet is far too painful if I can just export from a master source from time to time and as I keep my addresses synced in my mac and phone using the Contacts app I just need a away to export them to a file.  Natively the Contacts app can export as a [vCard](https://en.wikipedia.org/wiki/VCard) and I am sure there must be suitable parsers to read one but that would mean changing my super simple CSV address book code.  What is interesting is that like a number of Apple applications it uses Sqlite as its backing store.  Crack open the file, run some queries and you can retrieve contact data.

```terminal
$ sqlite3 ~/Library/Application\ Support/AddressBook/Sources/AB13F8E5-5DDB-43B5-A2FB-126E53099691/AddressBook-v22.abcddb
SQLite version 3.24.0 2018-06-04 14:10:15
Enter ".help" for usage hints.
sqlite> .output address.txt  
sqlite> select replace(ZFULLNUMBER,' ','') || '=' || coalesce(ZFIRSTNAME || ',' || ZLASTNAME || ',' || ltrim(rtrim(ZLABEL,'>!$_'),'_$!<'),ZORGANIZATION||',,Main','N/A'||',,Main') from ZABCDPHONENUMBER,ZABCDRECORD where ZABCDRECORD.Z_PK = ZABCDPHONENUMBER.ZOWNER;
sqlite> .q

$ head address.txt
015********=Bar Azita,,Main
01*********=Louise,Smith,Home
079********=Mag*****,Brown,Mobile
015********=De****,******ren,Home
077********=Bill,F*******,Mobile
```

The path will differ for every case but the `AddressBook-v22.abcddb` database file will be under there somewhere.  Note that the path changed when iCloud syncing was introduced.
