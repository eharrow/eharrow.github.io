---
layout: post
title: Generating a Jekyll post with Yeoman
comments: true
tags: jekyll
---
It is really easy to create a new Jekyll blog post - create a new markdown file with a kebab file name consisting of the date and post title plus the metadata at the top e.g. __2020-05-02-generating-a-jekyll-post-with-yeoman.md__
```
---
layout: post
title: Generating a Jekyll post with Yeoman
comments: false
tags: jekyll
---
```

The other way is to use my new [blog post generator](https://github.com/eharrow/generator-jekyll-post) that makes use of [Yeoman](https://yeoman.io).

```
$ yo jekyll-post

     _-----_     ╭──────────────────────────╮
    |       |    │      Welcome to the      │
    |--(o)--|    │        spectacular       │
   `---------´   │   generator-jekyll-post  │
    ( _´U`_ )    │        generator!        │
    /___A___\   /╰──────────────────────────╯
     |  ~  |     
   __'.___.'__   
 ´   `  |° ´ Y ` 

? Your post title? Generating a Jekyll post with Yeoman
? Your post tags separated by commas? jekyll
? Would you like to enable comments? No
   create 2020-05-02-generating-a-jekyll-post-with-yeoman.md

I'm all done. Running npm install && bower install for you to install the required dependencies. If this fails, try running the command yourself.
```

The [repo](https://github.com/eharrow/generator-jekyll-post) contains the yeoman project with instructions but basically yeoman prompts with a series of questions and then creates a new blog post markdown file from a template.



