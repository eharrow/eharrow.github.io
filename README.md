# README
This is a [Jekyll](https://jekyllrb.com) powered blog that works on Github. I use it to remind myself how to do things https://blog.ewanharrow.com.

To run locally the simplest thing is to use `docker-compose up`. and `open http://localhost:4000`.

First, install [Yeoman](http://yeoman.io) and [generator-jekyll-post](https://blog.ewanharrow.com/2020/05/02/generating-a-jekyll-post-with-yeoman/) using [npm](https://www.npmjs.com/) (we assume you have pre-installed [node.js](https://nodejs.org/)).

```bash
npm install -g yo
npm install -g generator-jekyll-post
```


Then generate your new jekyll post md file which will be created in the current directory so you'll need to move it to _posts afterwards:

```bash
yo jekyll-post
```

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
