
Each of the tutorials has a compile script that, when run from the directory
it is in, should build the grammar with no special configuration necessary.

These scripts are:
 hello/silver-compile
 dc/silver-compile
 turing/silver-compile
 simple/host/silver-compile
 simple/with_implication/silver-compile
 simple/with_repeat_until/silver-compile
 simple/with_all/silver-compile

The resulting compiler, translator, or evaluator can be run by
executing the generated .jar file.

In the case of 'hello', no input is required and can be run by typing
  java -jar hello.jar

In the case of 'dc', a simple arithmetic expression is entered as a
command line argument.  To prevent the multiplication operator "*"
from being interpreted as a wild-card by the shell put single quotes
around the expression, as in
  java -jar dc.jar '1 + 2 * 3'
(Note that this may depend on the shell you use, the above works for,
at least, the bash shell.)

In the case of the 'simple' translators, the name of a file is
required, as in
  java -jar <name of simple jar> 1.simple

See the tutorial documentation for more information.  It can be found
online at http://melt.cs.umn.edu/silver.
