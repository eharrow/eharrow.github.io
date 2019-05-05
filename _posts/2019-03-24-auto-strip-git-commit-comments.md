---
layout: post
title: How to Auto Strip Git Comments
tags: git
comments: false
---
I recently acquired a new mac at work and soon realised that I was missing my familiar
git template with commit message good form hints.  In addition after a subsequent merge
failed the fix with its commit retained the git warning comments in the message when they
used to be removed.  For posterity sake the `.gitconfig` needs the following lines:

```terminal
[commit]
        template = /Users/ewan/.gitmessage.txt
        cleanup = strip
```

The template file may be named whatever you choose.  Mine has the following to
remind to prefix with the change type.

```terminal
# Must be one of the following followed as a summary by a newline and then detail if required:

# feat: A new feature
# fix: A bug fix
# docs: Documentation only changes
# style: Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)
# refactor: A code change that neither fixes a bug or adds a feature
# test: Adding missing tests
# chore: Changes to the build process or auxiliary tools and libraries such as documentation generation
```

By default a git cleanup will strip line comments that start with *#*.  This can be changed with the `commentChar` in the git config.
