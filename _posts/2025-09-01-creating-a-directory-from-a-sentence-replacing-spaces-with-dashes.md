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


