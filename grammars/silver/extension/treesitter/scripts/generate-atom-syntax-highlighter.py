import argparse
import os
from subprocess import call
TREESITTER_PARSERS_DIRECTORY = "~/silver/syntax-highlighter/treesitter"
ATOM_PACKAGES_DIRECTORY = "~/silver/syntax-highlighter/atom"
language_name = ""
should_publish = False
atom_only = False
treesitter_only = False
called_from_directory = ""
new_version_num
hfile = "default.json"
def generate_treesitter_parser()
{
  call(["echo", "Generating tree-sitter parser")
  if not os.isdir(TREESITTER_PARSERS_DIRECTORY):
    call(["mkdir", "-p", TREESITTER_PARSERS_DIRECTORY])
  call(["cd", TREESITTER_PARSERS_DIRECTORY])
  language_dir = f"tree-sitter-{language_name}"
  first_generation = not os.isdir(language_dir)
  if first_generation:
    call(["mkdir", language_dir])
    call(["cd", language_dir])
    # -y so that no questions are needed from the user
    # This could also be a flag eventually
    call(["npm", "init", "-y"])
    call(["npm", "install", "--save", "nan"])
    call(["npm", "install", "--save-dev", "tree-sitter-cli")
  else
    call(["cd", language_dir])

  treesitter_dir = os.getcwd()
  grammar_file = called_from_directory + "/grammar.js" 
  call(["cp", grammar_file, treesitter_dir])
  # Update version number: May become more complex with command line later
  if not first_generation:
    call(["npm", "version", "minor"])

  call(["tree-sitter", "generate"])
  if should_publish:
    call(["npm", "publish"])
  call(["echo", "Tree-sitter parser generated")
}

def generate_atom_package()
{
  call(["echo", "Generating atom package"])
  if not os.isdir(ATOM_PACKAGES_DIRECTORY):
    call(["mkdir", "-p", ATOM_PACKAGES_DIRECTORY])
  call(["cd", ATOM_PACKAGES_DIRECTORY])
  atom_language_dir = "language-" + language_name
  atom_package_dir = os.getcwd() + atom_language_dir
  first_generation = not os.isdir(atom_language_dir)
  if first_generation:
    call(["apm", "init", "--language", language_name])
  # Todo: add command line option for dev packages
  call(["apm", "link", atom_package_dir])
  call(["cd", atom_language_dir])
  # TODO: do conversion
}

def build_language_file()
{
  
}

arg_parser = argparse.ArgumentParser(description = "Generate a treesitter parse and Atom language package for syntax highlighting in Atom")
arg_parser.addArgument("-publish", action="store_true" dest="should_publish"
arg_parser.addArgument("-atom", action="store_true" dest="atom_only")
arg_parser.addArgument("-treesitter", action="store_true" dest="treesitter_only")
arg_parser.addArgument("-hfile", action
# Commands to Add
# - atom
# - treesitter
# - publish
# - force-publish
# - version-number 
# - npm-version-update-type
# -
