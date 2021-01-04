
To edit the Silver runtime from an Eclipse IDE,

1. Run a successful `./deep-rebuild` at Silver project root.
   This is necessary to generate translations for the Silver standard library
   and other things referenced by the runtime.
   (Failure to do this, or later having run clean, will result in errors related
   to not finding classes from `silver:core` and other grammars.)

2. In Eclipse, `File -> Import`, `General -> Existing Projects into Workspace`.
   Give it the path to `silver/runtime/java`.

3. Go to `Window -> Preferences` (NOTE: **WINDOW** preferences, other
   ways to get to preferences may not show this option! Confusingly.)
   and navigate to `Java -> Build Path -> Classpath Variables`.
   Then create a new `SILVER_HOME` variable, set to where you have checked out
   Silver. (e.g. `/home/tedinski/repos/silver`)

   (The purpose of this is to ensure the eclipse project configuration that's
   checked into version control does not need to change from person to person.)

