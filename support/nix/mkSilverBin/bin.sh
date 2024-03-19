#!/usr/bin/env bash

@execline@/bin/importas -D "@javaFlags@" javaFlags SVJVM_FLAGS
exec @java@ $javaFlags -jar @out@/@jarName@ $@
