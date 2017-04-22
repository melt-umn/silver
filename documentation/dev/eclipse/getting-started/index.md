---
layout: sv_wiki
title: Getting started
menu_weight: 10.0
---

## Prerequisites

Above and beyond the standard Silver requirements, you need Eclipse 3.3+ and Maven 3.0+.

## Configuring Eclipse

Silver code is usually a bit memory hungry, and stack hungry too. Open `eclipse.ini` where eclipse is unpacked, and edit it `-Xmx384M` (or whatever `-Xmx` line appears in yours) to `-Xmx2000M` and also add as another line `-Xss8M`.

## Building the Silver plugin for Eclipse from scratch

In other words, this will color and build Silver projects from within Eclipse.

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

Next, start eclipse and go to install software (in mine its Help->Install New Software...) and paste in the path it told you to use.

The plugin should show up. Install it, blah blah, restart eclipse, and now should should be able to File->New->Project... (Silver->Silver project).

The easiest way to make things work is to then symlink the files you're working on into a new project. That is, inside the newly created silver project in your workspace:

```
$ ln -s ~/repos/silver/tutorials/simple/src/simple simple
```

Then right click your project in Eclipse and refresh. (Or hit f5 with your project selected.)

Note: there is (at the time of writing) no way to adjust the grammar path. It's the project root. So you can't, e.g., symlink a `src` or `grammars` directory in. Yet. Maybe YOU can fix this!! :D

Then you need to tell it which grammar to build. You can do this by either editing the properties file, or right clicking on your project, going to Properties, Silver page, and giving it the grammar name to build there.

(We should probably introduce a new build mode for Silver that just builds ALL grammars it can find under a root directory. This would remove this step for using eclipse.)

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

## Using the Silver pluging to develop Silver

This is not YET practical due to slow performance.

First, builds use a ton of memory, and this does not seem to be adequately released between builds. Partly this might be a lack of garbage collection (some explicit calls might help), but there may be some possibility of a memory leak as well. Needs investigating. But this part seems managable if you're willing to devote 2G of ram to eclipse, but that's assuming there is no memory leak, so continued use is managable.

Mostly, the problem is that the 'build' step passes no information to the 'postbuild' step. So every save basically causes two builds to occur. One to mark errors in the IDE, one to generate java files.

There may be other performance bugs as well. In general, the silver compiler itself isn't very memory efficient. We could fix that and it would help both normal compiles and the eclipse plugin.
