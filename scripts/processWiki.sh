#!/bin/sh

if [ $# -ne 2 ] ; then
   echo "USAGE: ${0} <wiki directory> <version string>";
   exit 1;
fi

wikiDir=$1
versionString=$2

DEFAULT_TARGET_FILE='documentation.html'
targetFile="psl-wiki-${versionString}.html"

# Generate documentation.
# https://github.com/yakivmospan/github-wikito-converter
node dist/cli/gwtc.js -o . -f html -t "PSL ${versionString} Wiki" "${wikiDir}"

mv "${DEFAULT_TARGET_FILE}" "${targetFile}"

# Make page names more readable.
sed -i 's/\(\w\+\)-\([a-z0-9_\-]\+\)\.md/\1 \2.md/gi' "${targetFile}"
sed -i 's/\(\w\+\)-\([a-z0-9_\-]\+\)\.md/\1 \2.md/gi' "${targetFile}"
sed -i 's/\(\w\+\)-\([a-z0-9_\-]\+\)\.md/\1 \2.md/gi' "${targetFile}"
sed -i 's/\(\w\+\)-\([a-z0-9_\-]\+\)\.md/\1 \2.md/gi' "${targetFile}"
sed -i 's/\(\w\+\)-\([a-z0-9_\-]\+\)\.md/\1 \2.md/gi' "${targetFile}"
sed -i 's/\(\w\+\)-\([a-z0-9_\-]\+\)\.md/\1 \2.md/gi' "${targetFile}"
sed -i 's/\(\w\+\)-\([a-z0-9_\-]\+\)\.md/\1 \2.md/gi' "${targetFile}"
sed -i 's/\(\w\+\)-\([a-z0-9_\-]\+\)\.md/\1 \2.md/gi' "${targetFile}"
sed -i 's/\(\w\+\)-\([a-z0-9_\-]\+\)\.md/\1 \2.md/gi' "${targetFile}"
sed -i 's/\(\w\+\)-\([a-z0-9_\-]\+\)\.md/\1 \2.md/gi' "${targetFile}"

sed -i 's/\.md//g' "${targetFile}"

# Make ToC Scrollable
sed -i 's/^\.nav {/.nav-inner {\r   height: 100%;\r   overflow-y: scroll;\r   width: 250px;\r}\r\r\0/' "${targetFile}"

# Fix Links
sed -i 's/<p class="page" id="\(\w\+\)-\([a-z0-9_\-]\+\)">/<p class="page" id="\1\2">/ig' "${targetFile}"
sed -i 's/<p class="page" id="\(\w\+\)-\([a-z0-9_\-]\+\)">/<p class="page" id="\1\2">/ig' "${targetFile}"
sed -i 's/<p class="page" id="\(\w\+\)-\([a-z0-9_\-]\+\)">/<p class="page" id="\1\2">/ig' "${targetFile}"
sed -i 's/<p class="page" id="\(\w\+\)-\([a-z0-9_\-]\+\)">/<p class="page" id="\1\2">/ig' "${targetFile}"
sed -i 's/<p class="page" id="\(\w\+\)-\([a-z0-9_\-]\+\)">/<p class="page" id="\1\2">/ig' "${targetFile}"
sed -i 's/<p class="page" id="\(\w\+\)-\([a-z0-9_\-]\+\)">/<p class="page" id="\1\2">/ig' "${targetFile}"
sed -i 's/<p class="page" id="\(\w\+\)-\([a-z0-9_\-]\+\)">/<p class="page" id="\1\2">/ig' "${targetFile}"
sed -i 's/<p class="page" id="\(\w\+\)-\([a-z0-9_\-]\+\)">/<p class="page" id="\1\2">/ig' "${targetFile}"
sed -i 's/<p class="page" id="\(\w\+\)-\([a-z0-9_\-]\+\)">/<p class="page" id="\1\2">/ig' "${targetFile}"
sed -i 's/<p class="page" id="\(\w\+\)-\([a-z0-9_\-]\+\)">/<p class="page" id="\1\2">/ig' "${targetFile}"

sed -i 's/<a href="#\(\w\+\)-\([a-z0-9_\-]\+\)">/<a href="#\1\2">/ig' "${targetFile}"
sed -i 's/<a href="#\(\w\+\)-\([a-z0-9_\-]\+\)">/<a href="#\1\2">/ig' "${targetFile}"
sed -i 's/<a href="#\(\w\+\)-\([a-z0-9_\-]\+\)">/<a href="#\1\2">/ig' "${targetFile}"
sed -i 's/<a href="#\(\w\+\)-\([a-z0-9_\-]\+\)">/<a href="#\1\2">/ig' "${targetFile}"
sed -i 's/<a href="#\(\w\+\)-\([a-z0-9_\-]\+\)">/<a href="#\1\2">/ig' "${targetFile}"
sed -i 's/<a href="#\(\w\+\)-\([a-z0-9_\-]\+\)">/<a href="#\1\2">/ig' "${targetFile}"
sed -i 's/<a href="#\(\w\+\)-\([a-z0-9_\-]\+\)">/<a href="#\1\2">/ig' "${targetFile}"
sed -i 's/<a href="#\(\w\+\)-\([a-z0-9_\-]\+\)">/<a href="#\1\2">/ig' "${targetFile}"
sed -i 's/<a href="#\(\w\+\)-\([a-z0-9_\-]\+\)">/<a href="#\1\2">/ig' "${targetFile}"
sed -i 's/<a href="#\(\w\+\)-\([a-z0-9_\-]\+\)">/<a href="#\1\2">/ig' "${targetFile}"

# Add Sections
sed -i 's/<p class="page/<hr \/><p class="page/' "${targetFile}"

sed -i 's/background: transparent url/background: #e7e7e7 url/' "${targetFile}"

# Remove a broken link.
sed -i 's/url("..\/..\/images\/modules\/pulls\/dirty-shade.png") //' "${targetFile}"
