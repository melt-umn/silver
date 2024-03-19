{ default-java, default-silver, execline, lib, mdbook, stdenv }:

{ grammarName
, binaryName ? args.pname or (lib.last (lib.splitString ":" grammarName))
, buildInputs ? [ ], enableMdbook ? false, grammarPath ? "grammars"
, jarName ? binaryName + ".jar", java ? default-java, javaFlags ? [ ]
, mdbookPath ? ".", nativeBuildInputs ? [ ], outputs ? [ ]
, silver ? default-silver, silverFlags ? [ ], silverInputs ? [ ]
, silverName ? silver.meta.mainProgram, ... }@args:

let
  binaryName' = lib.escapeShellArg binaryName;
  grammarPath' = lib.escapeShellArg grammarPath;
  jarName' = lib.escapeShellArg jarName;
  javaFlags'' = lib.escapeShellArg (lib.escapeShellArgs javaFlags);
in stdenv.mkDerivation ({
  outputs =
    lib.unique (outputs ++ [ "out" "dev" ] ++ lib.optional enableMdbook "doc");
  buildInputs = buildInputs ++ builtins.map lib.getDev silverInputs;
  nativeBuildInputs = nativeBuildInputs ++ lib.optional enableMdbook mdbook
    ++ [ java silver ];

  buildPhase = ''
    runHook preBuild
  '' + lib.optionalString enableMdbook ''
    # This needs to come at the beginning to avoid scooping up build artifacts.
    mdbook build -d $doc ${lib.escapeShellArg mdbookPath}
  '' + ''
    # Ensure we're not about to overwrite the JAR file or binary.
    echo Checking for already existing $TMPDIR/mkSilverBin/${binaryName'}
    test ! -e $TMPDIR/mkSilverBin/${binaryName'}
    echo Checking for already existing $TMPDIR/mkSilverBin/${jarName'}
    test ! -e $TMPDIR/mkSilverBin/${jarName'}

    # Build the JAR.
    mkdir -p $dev/generated
    ${silverName} \
      -G $dev/generated \
      -I ${grammarPath'} \
      -o $TMPDIR/mkSilverBin/${jarName'} \
      --one-jar \
      ${lib.escapeShellArgs silverFlags} \
      ${lib.escapeShellArg grammarName}

    # Create a script to run the binary.
    substitute ${./bin.sh} $TMPDIR/mkSilverBin/${binaryName'} \
      --subst-var out \
      --subst-var-by execline ${execline} \
      --subst-var-by jarName ${jarName'} \
      --subst-var-by java ${java}/bin/java \
      --subst-var-by javaFlags ${javaFlags''}

    runHook postBuild
  '';

  installPhase = ''
    runHook preInstall

    cp -r ${grammarPath'} $dev/grammars
    install -Dt $out -m0644 $TMPDIR/mkSilverBin/${jarName'}
    install -Dt $out/bin -m0755 $TMPDIR/mkSilverBin/${binaryName'}
    mkdir -p $dev/nix-support
    substitute ${./setup-hook.sh} $dev/nix-support/setup-hook \
      --subst-var dev

    runHook postInstall
  '';

  passthru = { inherit grammarName jarName; } // (args.passthru or { });
} // lib.optionalAttrs enableMdbook { MDBOOK_BUILD__CREATE_MISSING = "false"; }
  // lib.filterAttrs (name: _:
    !lib.elem name [ "buildInputs" "nativeBuildInputs" "outputs" "passthru" ])
  args)
