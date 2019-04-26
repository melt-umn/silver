# CONSTANTS
reset_color=`tput sgr0`
use_red_color=`tput setaf 1`
treesitter_parsers_directory="silver-syntax-highlighter/treesitter"
atom_packages_directory="silver-syntax-highlighter/atom"

generate_treesitter_parser()
{
  echo Generating tree-sitter parser
  cd ~
  if [ ! -d $treesitter_parsers_directory ]; then
    mkdir -p $treesitter_parsers_directory
  fi
  
  cd $treesitter_parsers_directory
  
  # TODO add a unique id to avoid conflicts on npm registry
  if [ -d "tree-sitter-$lang_name" ]; then  
    cd tree-sitter-$lang_name
    if [ "$version_num" = "" -a "$version_update_type" = "" ]; then
      npm version minor  # update version number
    fi
  else
    mkdir tree-sitter-$lang_name
    cd tree-sitter-$lang_name
    npm init -y
    npm install --save nan
    npm install --save-dev tree-sitter-cli@0.13.15
  fi
  
  treesitter_dir=$(pwd)
  cp $original_dir/grammar.js $treesitter_dir/
  # Update version number in package.json if specified
  if [ "$version_num" != "" ]; then
    old_version_num=`node -pe "require('./package.json').version"`
    npm version $version_num --allow-same-version
    echo Version Number Updated from $old_version_num to $version_num
  fi
  if [ "$version_update_type" != "" ]; then
    npm version $version_update_type
  fi
  tree-sitter generate
  npm install
  if [ $publish_treesitter = true ]; then
    npm publish
  fi
  echo Tree-sitter parser generated
}

validate_version_number()
{
  echo $version_num | grep -e "[[:digit:]].[[:digit:]].[[:digit:]]" > /dev/null
  if [ $? -ne 0 ]; then
    echo "${use_red_color}Invalid version number specified: $version_num.${reset_color}"
    exit 1
  fi
  echo Version number $version_num is valid
}

check_if_parser_already_exists()
{
  old_dir=$(pwd)
  cd ~
  if [ ! -d $treesitter_parsers_directory/tree-sitter-$lang_name ]; then
    curl --output /dev/null --silent --head --fail https://www.npmjs.com/package/tree-sitter-$lang_name
    if [ $? -eq 0 -a $force_option = false -a $publish_treesitter = true ]; then
      echo "${use_red_color}A parser for this language name already exists, but you dont have a directory already set up at $treesitter_parsers_directory/tree-sitter-$lang_name. Please either use this parser or use a new language name."
      echo "If this is what you intended redo the command with the -force option with the appropriate version specified with -version versionum.${reset_color}"
      exit 1
    fi
  fi
  cd $old_dir # restore directory from before check
}
generate_atom_package()
{
  cd ~
  echo Generating atom language package
  if [ ! -d $atom_packages_directory ]; then
    mkdir -p $atom_packages_directory
  fi

  cd $atom_packages_directory
  atom_package_dir=$(pwd)/language-$lang_name
  if [ ! -d language-$lang_name ]; then
    apm init --language $lang_name
  fi
  if [ $atom_development_package = true ]; then
    apm unlink $atom_package_dir
    apm link --dev $atom_package_dir
  else
    apm unlink --dev $atom_package_dir
    apm link $atom_package_dir
  fi

  cd language-$lang_name
  cp $original_dir/$lang_name.cson $atom_package_dir/grammars/
  if [ ! -d node_modules/tree-sitter-$lang_name ]; then 
    npm install tree-sitter-$lang_name
  else
    old_version=`npm view tree-sitter-$lang_name version`
    npm install tree-sitter-$lang_name
    new_version=`npm view tree-sitter-$lang_name version`
    if [ "$old_version" != "$new_version" ]; then
      echo Package tree-sitter-$lang_name updated from version $old_version to $new_version
    fi
  fi
  echo Atom language package generated
}

original_dir=$(pwd)

# Parse Language Name from treesitter command
at_lang_name=false
lang_name=""
for word in $1; do
  if [ $at_lang_name = true ]; then
    lang_name=$word
    break
  fi
  if [ $word = "--treesitter-spec" ]; then
    at_lang_name=true
  fi
done
echo Language Name is $lang_name

# the silver command to run
silver_command=$1
run_atom=true
run_treesitter=true
force_option=false
publish_treesitter=true
version_num=""
version_update_type=""
atom_development_package=false
while [ "$2" != "" ]; do
  if [ "$2" = "-atom" ]; then
    run_treesitter=false
  elif [ "$2" = "-treesitter" ]; then
    run_atom=false
  elif [ "$2" = "-force" ]; then
    force_option=true
  elif [ "$2" = "-nopublish" ]; then
    publish_treesitter=false
  elif [ "$2" = "-atom-dev" ]; then
    atom_development_package=true
  elif [ "$2" = "-version" ]; then
    shift
    if [ "$2" = "major" -o "$2" = "minor" -o "$2" = "patch" ]; then
      version_update_type="$2"
      version_num=""
    else
      version_num=$2
      version_update_type=""
      validate_version_number
    fi
  fi
  shift  # shift the parameters down by one
done

if [ $run_treesitter = true ]; then
  check_if_parser_already_exists
fi
$silver_command
if [ $run_treesitter = true ]; then
  generate_treesitter_parser
fi
if [ $run_atom = true ]; then
  generate_atom_package
fi
