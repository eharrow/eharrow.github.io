---
layout: post
title: Display Mongo Bunyan Logs In The Terminal
comments: true
tags: mongo cli

---
If you use Bunyan and decide to use a MongoDB as a logging appender (destination) then you will arguably know what a great use of MongoDB it can be.  Querying is easy but sometimes visibly parsing a raw Bunyan log entry JSON is a pain but luckily Bunyan CLI will parse and display these in a much easier format.  However just extracting the raw Mongo documents using Robomongo and piping to bunyan will not work as the document schema and extra added Robomongo cruft differs a little making it non-parseable by Bunyan therefore we need to transform the entries a little which we can do using standard Mac or linux utils.

The transformations we use remove robomongo comments, mongodb metadata fields, change a time field to string from the Mongo ISODate, remove unnecessary whitespace and newlines.

### Step-by-step guide
1. Extract the raw log entries from the database to a file such as bunyan-db-logs.json
2. Install Bunyan CLI using `npm install -g bunyan`
3. Check you have `sed` installed
4. Execute the following adjusting file name and path appropriately `sed 's/^\/\*.*$//g' < ~/Downloads/bunyan-db-logs.json | sed 's/__v : 0//g' | sed 's/_id.*$//g' | sed 's/time : ISODate(\(.*\)),/time: \1,/g' | tr -d '[:space:]' | sed 's/v:0,}/v:0}|/g' | tr '|' '\n' | bunyan`



