#!/usr/bin/env bash

@execline@/bin/importas -D "-Xmx4G -Xss16M" javaFlags SVJVM_FLAGS
exec @java@ $javaFlags -jar @out@/SilverLanguageServer.jar $@
