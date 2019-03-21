
To edit the Silver runtime from an Eclipse IDE,

1. Run a successful `./deep-rebuild` at project root.
   This is necessary to build generate translations for Silver standard library
   and other things referenced by the runtime.
2. Add `silver/runtime/java` as a project to your Eclipse workspace.
3. `Window -> Preferences` and `Java -> Build Path -> Classpath Variables`
   Then set `SILVER_HOME` to where you have checked out Silver.
   (e.g. `/home/tedinski/repos/silver`)

