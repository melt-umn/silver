---
layout: sv_wiki
title: Getting started
menu_weight: 10.0
---

First see the [Silver Eclipse Plugin](/silver/eclipse/silver/) documentation.

## Doing development on the Silver-Eclipse runtime or plugin

I use this script:

```
#!/bin/bash

SILVER_SITE=/home/tedinski/repos/silver/generated/ide/silver.composed.idetest/updatesite/target/repository
SILVER_FEATURE_NAME=silver.composed.idetest.feature

set -e

rm -rf eclipse workspaces
mkdir workspaces
cp -r eclipse-template eclipse

SILVER_FEATURE_VERSIONDOTJAR=$(ls $SILVER_SITE/features | cut -d_ -f2)
SILVER_FEATURE_VERSION=${SILVER_FEATURE_VERSIONDOTJAR/.jar/}

eclipse/eclipse -application org.eclipse.update.core.standaloneUpdate -command install -from "file:$SILVER_SITE" -featureId $SILVER_FEATURE_NAME -version $SILVER_FEATURE_VERSION
eclipse/eclipse
```

I:

  1. Extract a fresh eclipse install. This creates a folder called `eclipse`.
  1. Start it, set the workspace to a `workspaces` directory right next to `eclipse` wherever we are, and set it to ALWAYS use this workspace.
  1. Close eclipse. `mv eclipse eclipse-template`

Then use this script. It nukes the workspace and any old eclipse install. Creates a new eclipse install by copying back `eclipse-template`, then it installs the plugin pointed to by `SILVER_SITE` and `SILVER_FEATURE_NAME`, then it starts the IDE.

You still have to new project, symlink and whatnot to actually test things out, but it automates some of the annoying steps.

This script also eliminates any worries about version numbers of the plugin, since it starts fresh each time. (I call the script `start-fresh`.)

## Original process for building the plugin

This section is out of date.

Starting point: everything should be in order enough to run, for example `./deep-rebuild`. Things should presumably be up to date, e.g. you've run `./update`.

Next, run `./make-ide`. This will do the following:

  1. Runs `./self-compile` once. From scratch this is unnecessary, but this step helps with doing development on the Silver plugin for Eclipse, by first rebuilding the Silver compiler, so that any changes in how it generates plugins are reflected in the resulting build.
  1. Runs `build.sh` in `runtime/imp`. This:
    1. Runs `silver` on `core` and `ide` and generates these java files inside the silver-eclipse runtime.
    1. Builds that jar with maven.
    1. Strips out `core` and `ide` class files from that jar. These should ultimately be provided by the real generated silver jar.
  1. Runs `./self-compile-ide` which just builds `silver:composed:idetest`
  1. Runs `mvn package` in `generated/ide/silver.composed.idetest`.
  1. Reminds you of what path to use, when we later install this plugin to eclipse.

