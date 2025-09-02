# README
This is a [Jekyll](https://jekyllrb.com) powered blog that works on Github. I use it to remind myself how to do things https://blog.ewanharrow.com.

To run locally the simplest thing is to use `docker-compose up`. and `open http://localhost:4000`.

## Start off with a _post_ file entry
Use the AddPost CLI which will prompt for title etc. (has defaults) and spits out a jekyll front-matter header to start you off:
```shell
$ java --enable-preview AddPost.java
Enter post title => test
Enter post date dd/mm/yyyy [02/09/2025] => 
Enter post categories [java programing] => 

Save the following to a new file named: 2025-09-02-test.md

---
layout: post
title: "test"
date: 2025-09-02
categories: java programing
---
## Here is a heading about
This is a paragraph
```

## Running locally in Docker
Spin up docker and use the `docker-compose.yml`.  This will watch for changes in the `_posts`
directory so add changes there - on a new branch of course.

A gotcha is that it uses the latest jekyll image but the `Gemfile.lock` might have incompatibilities so
it is best to delete and rerun docker compose and a upto date version will be created.

Also remove the contents of the `_site` directory as these are cached.

## Update the copyright date for a new year
Simply create a new branch (branch protection on master is activated), perform a trigger build to
push an empty update, open a PR and merge to master.  The jekyll GH build will cause the footer date expression
to re-execute.

```bash
git branch update-2025-footer
git co update-2025-footer
git commit -m "Trigger build" --allow-empty
git push --set-upstream origin update-2025-footer
```
