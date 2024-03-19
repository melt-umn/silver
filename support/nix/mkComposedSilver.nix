{ apacheAnt, coreutils, java, lib, makeWrapper, mkSilverBin, silver, symlinkJoin
, writeTextDir }:

extensions:

let
  extensionGrammarNames =
    lib.concatMapStringsSep "\n  " (grammarName: "${grammarName};")
    (builtins.concatMap (extension: extension.grammarNames) extensions);

  extensionBulletPoints =
    lib.concatMapStringsSep "\n" (extension: "- ${extension.name}") extensions;

in mkSilverBin {
  name = "silver-composed";
  pname = "silver-composed";
  inherit (silver) version;

  src = writeTextDir "grammars/artifact/Artifact.sv" ''
    grammar artifact;
    import silver:compiler:host;
    parser extendedParser :: Root {
      silver:compiler:host;
      ${extensionGrammarNames}
    }

    function main
    IO<Integer> ::= args::[String]
    { return cmdLineRun(args, extendedParser); }
  '';

  binaryName = "silver";
  grammarName = "artifact";
  nativeBuildInputs = [ makeWrapper ];
  silverInputs = extensions;

  installPhase = ''
    runHook preInstall

    install -Dt $out/jars -m0644 $TMPDIR/mkSilverBin/silver.jar

    ln -s ${silver}/grammars $out/grammars
    for name in commonmark-0.17.1.jar CopperCompiler.jar SilverRuntime.jar; do
      ln -s ${silver}/jars/$name $out/jars/$name
    done

    mkdir -p $out/bin $out/support/bin
    for name in silver silver-custom; do
      substitute ${silver}/support/bin/$name $out/support/bin/$name \
        --replace silver.compiler.composed.Default.jar silver.jar
      chmod +x $out/support/bin/$name
      ln -s ../support/bin/$name $out/bin/$name
      wrapProgram $out/bin/$name \
        --prefix PATH : ${apacheAnt}/bin \
        --prefix PATH : ${coreutils}/bin \
        --prefix PATH : ${java}/bin \
        --prefix SILVER_HOST_GEN : $out/generated
    done

    mkdir -p $dev/nix-support
    substitute ${./mkSilverBin/setup-hook.sh} $dev/nix-support/setup-hook \
      --subst-var dev

    runHook postInstall
  '';

  meta = silver.meta // {
    longDescription = ''
      Silver extended with:

      ${extensionBulletPoints}
    '';
  };

  passthru = {
    symlinkJoinedSrcs = symlinkJoin {
      name = "silver-composed-symlinkJoinedSrcs";
      paths = [ "${silver}/grammars" ]
        ++ builtins.map (extension: "${extension.src}/${extension.grammarPath}")
        extensions;
    };
    silverParserName = "extendedParser";
  };
}
