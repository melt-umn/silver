#!/bin/bash

# A collection of functions useful in installing Silver and Silver-related
# software.

# This file is linked from ~/bin/silver-bash-lib.sh


# This determines the name of the "readlink" command, depending on the
# OS.
function set_readlink {
    case `uname` in
        *Darwin*)
            READLINK=greadlink
            readlink=greadlink
            if [ ! -f `which greadlink` ]; then
                echo "Missing greadlink. Please install coreutils:"
                echo -e "\tbrew install coreutils"
                exit 4
            fi
            ;;
        *)
            READLINK=readlink
            readlink=readlink
            ;;
    esac
}

# This sets up a symbolic link, removing the file or link at
# ${link_name} if it already exists, something that happens frequently
# in MELT install scripts.
function setup_sym_link {
    target=$1
    link_name=$2

    if [ -e ${link_name} -o -h ${link_name} ]; then
        rm ${link_name}
        echo "Removed existing file or link ${link_name}."
    fi

    ln -s ${target} ${link_name}
}

# This is useful for creating space and emphasis in displaying the
# activity of a script.
function task {
    echo ""
    echo ""
    echo "----------------------------------------------------------------------"
    echo $1
    echo "----------------------------------------------------------------------"
    echo ""
}
