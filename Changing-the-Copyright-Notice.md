Before [[releasing a new stable version]], it is good to make sure that PSL's copyright notices are up to date. Scripts for doing that are below:

### OSX Script for Changing Copyright Notices

```
#!/bin/bash

# THIS VERSION ONLY WORKS FOR THE MAC OSX VERSION OF SED

die () {
    echo >&2 "$@"
    exit 1
}

[ "$#" -eq 2 ] || die "Two arguments, old and new end years, required"

export LANG=C

find * -not -path '*/\.*' -type f -exec sed -i "" "s_ \* Copyright 2013-$1 The Regents of the University of California_ \* Copyright 2013-$2 The Regents of the University of California_g" {} \;

find . -not -path '*/\.*' -type f -exec sed -i "" "s_  - Copyright 2013-$1 The Regents of the University of California_  - Copyright 2013-$2 The Regents of the University of California_g" {} \;

sed -i '' "s_Copyright 2013-$1 The Regents of the University of California_Copyright 2013-$2 The Regents of the University of California_g" NOTICE

echo "Remember to check the results of this script before committing!"
```