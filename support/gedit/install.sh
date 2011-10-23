#!/bin/bash

# determine what version of gtksourceview we're working with

case `gedit --version` in
*Version\ 2*)
  GTKSV=2.0
  ;;
*Version\ 3*)
  GTKSV=3.0
  ;;
*)
  echo "Unknown gedit verison?"
  gedit --version
  exit 1
  ;;
esac

# Create these directories, if they do not exist already

mkdir -p ~/.local/share/gtksourceview-$GTKSV/language-specs/
mkdir -p ~/.local/share/mime/packages/

# Copy our files over to them.

cp silver.lang ~/.local/share/gtksourceview-$GTKSV/language-specs/
cp silver.xml ~/.local/share/mime/packages/

# Run the mime database compiler on the user's mime directory,
# now that we've added to it.

(cd ~/.local/share && update-mime-database mime)



