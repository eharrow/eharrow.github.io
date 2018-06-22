---
layout: post
title: Adding GPG Key to Sign Git Commits with Github and Keybase
category: crypto git
---

695  gpg --list-secret-keys --keyid-format LONG
696  git config --global user.signingkey B782D1B96DED7230
697  git config --global commit.gpgsign true
698  open https://github.com/settings/keys
699  keybase pgp export -q B782D1B96DED7230
700  keybase pgp export -q B782D1B96DED7230 | pbcopy
718  export GPG_TTY=$(tty)
719  git commit -m "updated"
730  brew install gnupg2
731  git config --global gpg.program gpg2
732  brew install pinentry-mac
733  echo "pinentry-program /usr/local/bin/pinentry-mac" >> ~/.gnupg/gpg-agent.conf
