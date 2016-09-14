---
layout: sv_wiki
title: Silver Eclipse Plugin
menu_weight: 10.0
---

The Silver Plugin for Eclipse will color and build Silver projects from within
Eclipse.

## Prerequisites

Above and beyond the standard Silver requirements, you need Eclipse 3.3+ and Maven 3.0+.

## Configuring Eclipse

Silver code is usually a bit memory hungry, and stack hungry too. Open `eclipse.ini` where Eclipse is unpacked, and edit it `-Xmx384M` (or whatever `-Xmx` line appears in yours) to `-Xmx2000M` and also add as another line `-Xss8M`.

## Building and installing the Silver plugin for Eclipse

Run the following commands:

```
silver$ cd eclipse-plugin
silver/eclipse-plugin$ ./build
```

Start Eclipse and select Help -> Install New Software -> Add... -> Local.. . Select the directory `silver/eclipse-plugin/updatesite/target/repository/` beneath your local silver installation. The plugin should show up. Install it, follow prompts, and restart Eclipse.

Now you should be able to create a new Silver project by going to File -> New -> Project... and selecting Silver -> New Silver Project.

The easiest way to make things work is to then symlink the files you're working on into a new project. That is, inside the newly created silver project in your workspace:

```
$ ln -s ~/repos/silver/tutorials/simple/src/simple simple
```

Right click your project in Eclipse and refresh. (Or press F5 with your project selected.)

Note: there is (at the time of writing) no way to adjust the grammar path. It's the project root. So you can't, e.g., symlink a `src` or `grammars` directory in. Yet. Maybe YOU can fix this!! :D

You need to tell the project which grammar to build. You can do this either by editing the properties file or by right clicking on your project and selecting Properties -> Silver.

(We should probably introduce a new build mode for Silver that just builds ALL grammars it can find under a root directory. This would remove this step for using Eclipse.)

## Using the Silver plugin to develop Silver

This is not YET practical due to slow performance.

First, builds use a ton of memory, and this does not seem to be adequately released between builds. Partly this might be due to a lack of garbage collection (some explicit calls might help), but there may be some possibility of a memory leak as well. Use of the Silver plugin on the Silver source *seems* manageable if you're willing to devote 2GB of ram to Eclipse, but that's assuming there is no memory leak, so continued use is not managable. This needs investigating.

Mostly, the problem is that the 'build' step passes no information to the 'postbuild' step, so every save causes two builds to occur: one to mark errors in the IDE, one to generate Java files.

There may be other performance bugs as well. In general, the silver compiler itself isn't very memory efficient. We could fix that and it would help both normal compiles and the Eclipse plugin.

