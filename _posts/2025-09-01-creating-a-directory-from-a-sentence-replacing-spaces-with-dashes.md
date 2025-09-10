---
layout: post
title: "Creating a Directory from a Sentence Replacing Spaces with Dashes"
date: 2025-09-01
categories: bash
---
Sometimes we want to create a directory without having to think too hard.

Try the following `mkdir $(echo "Lorem ipsum dolor sit amet" |tr ' ' '-')`. It creates a directory
named `Lorem-ipsum-dolor-sit-amet`.

I prefer directories as all lowercase so let's do that as well with
`mkdir $(echo "Lorem ipsum dolor sit amet" | tr ' ' '-' | tr '[:upper:]' '[:lower:]')`

Wrapping it up as a parameterised shell script `mklongdir`
```shell
#!/usr/bin/env bash
set -o errexit
set -o pipefail

target_dir=$1
mkdir $(echo ${target_dir} | tr ' ' '-' | tr '[:upper:]' '[:lower:]')
```

and then
```shell
chmod +x mklongdir
```
Using it as `mklongdir "Creating a Directory from a Sentence Replacing Spaces with Dashes"` will
create the directory `creating-a-directory-from-a-sentence-replacing-spaces-with-dashes`.
