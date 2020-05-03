---
layout: post
title: Generating a Jekyll post with Yeoman
comments: true
tags: javascript automation
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

![jekyll post yeoman generator screenshot](/public/images/jekyll-post-yeoman-generator.png){:class="img-fluid rounded"}

The [repo](https://github.com/eharrow/generator-jekyll-post) contains the yeoman project with instructions but basically yeoman prompts with a series of questions and then creates a new blog post markdown file from a template.



