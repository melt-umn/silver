{ execline, java, javaPackages, default-silver, stdenvNoCC }:

silver:

let
  gson = javaPackages.fetchMaven {
    artifactId = "gson";
    groupId = "com.google.code.gson";
    version = "2.10";
    sha512 =
      "sha512-QGmBkK2VeY8s+K8hZDz05QRDEX3candRqMAhfrT3sMN0hxV03wIGDnoseCeIhlOs5TZdIvZclWBWyFsdaYHACw==";
  };
  lsp4j = javaPackages.fetchMaven {
    artifactId = "org.eclipse.lsp4j";
    groupId = "org.eclipse.lsp4j";
    version = "0.14.0";
    sha512 =
      "sha512-x/0Pa096IsFUsOfjMD8iu6ctmIOts5GVC5TfnhHdRJLP5/b4quXzF7f4UyLqX0XAjQhoRQk5f8b6S64xQhgtSQ==";
  };
  lsp4j-jsonrpc = javaPackages.fetchMaven {
    artifactId = "org.eclipse.lsp4j.jsonrpc";
    groupId = "org.eclipse.lsp4j";
    version = "0.14.0";
    sha512 =
      "sha512-h3VStYMCKFk1J0NVfZAHKxEz8EEDoP0LRKxhS8igJtlWIQieg7TXRO4pE3SOWbwSXuALbJlriAZDpc4YtsnOsw==";
  };

  classpath = [
    "${gson}/m2/com/google/code/gson/gson/2.10/gson-2.10.jar"
    "${lsp4j}/m2/org/eclipse/lsp4j/org.eclipse.lsp4j/0.14.0/org.eclipse.lsp4j-0.14.0.jar"
    "${lsp4j-jsonrpc}/m2/org/eclipse/lsp4j/org.eclipse.lsp4j.jsonrpc/0.14.0/org.eclipse.lsp4j.jsonrpc-0.14.0.jar"

    # This includes the Copper and Silver runtimes since we build it as
    # --onejar.
    "${silver}/jars/${silver.jarName}"
  ];

  silverJavaPackageName =
    builtins.replaceStrings [ ":" ] [ "." ] silver.grammarName;
  mangledParserName = builtins.replaceStrings [ ":" ] [ "_" ] silver.grammarName
    + "_" + silver.silverParserName;

in stdenvNoCC.mkDerivation {
  pname = "silver-language-server";
  inherit (default-silver) src version;

  nativeBuildInputs = [ java ];

  # Maven is a huge pain (no lockfiles in 2022?), so instead, we'll assemble
  # the sources by hand. Drastic, yes, but it works.
  buildPhase = ''
    cd ..
    cp -rT $sourceRoot/language-server/langserver/src/main/java src
    cp -rT $sourceRoot/language-server/launcher/src/main/java src
    cp -rT $sourceRoot/runtime/lsp4j/src/main/java src
    cd src

    # Patch references to the Silver main grammar and parser.
    sed -i \
      -e 's/silver\.compiler\.composed\.Default/${silverJavaPackageName}/' \
      -e 's/silver_compiler_composed_Default_svParse/${mangledParserName}/' \
      edu/umn/cs/melt/silver/langserver/SilverLanguageServer.java \
      edu/umn/cs/melt/silver/langserver/SilverLanguageService.java

    # Compile the sources.
    javac \
      -classpath ${builtins.concatStringsSep ":" classpath} \
      -d ../obj $(find -name '*.java')
    cd ..

    # Build a --onejar of this too.
    cd obj
    for file in ${builtins.concatStringsSep " " classpath}; do
      jar -x -f $file
    done
    cp -Lr ${silver.symlinkJoinedSrcs} grammars
    rm -r META-INF
    jar -c -e StdioLauncher -f ../SilverLanguageServer.jar .
    cd ..

    # Write the script that actually runs the language server.
    substitute ${./silver-language-server.sh} silver-language-server \
      --subst-var out \
      --subst-var-by execline ${execline} \
      --subst-var-by java ${java}/bin/java
  '';
  installPhase = ''
    install -Dt $out -m0644 SilverLanguageServer.jar
    install -Dt $out/bin silver-language-server
  '';

  meta = default-silver.meta // { mainProgram = "silver-language-server"; };
}
