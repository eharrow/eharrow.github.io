---
layout: post
title: Add Simple Dark Mode to a Jekyll Blog
tags: mac
comments: true
---
I have been wanting to add a dark theme to this blog ever since Apple introduced dark mode to MacOS and iOS.  The solution if you don't want to supply a UI switch is to use the css media query `prefers-color-scheme` that checks the user's system preference which has been available in Safari for a little while and works in Chrome on a Mac at least.

I am using SCSS for this blog so the following sets the colours to a light or dark theme and in the case of dark if dims any images unless hovering over with a mouse when they brighten up.  I also find the white background of the *pre terminal* style to be too bright in the dark theme so I opted to switch the colour to a solarised dark theme but that may change.

```scss
/* Light mode */
@media (prefers-color-scheme: light) {
  body {
      background-color: white;
      color: black;
  }
}

/* Dark mode */
@media (prefers-color-scheme: dark) {
  body {
      background-color: #000;
      color: white;
  }
  img {
    opacity: .75;
    transition: opacity .5s ease-in-out;

  }
  img:hover {
    opacity: 1;
  }
  .highlight {
    background-color: lightgrey;
  }
}
```

On a Mac running Catalina you can switch to Dark Mode

![mac catalina dark mode screenshot](/public/images/mac-catalina-dark-mode.png){:class="img-fluid rounded"}
