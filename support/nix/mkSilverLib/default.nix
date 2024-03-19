{ default-silver, lib, mdbook, stdenv }:

{ grammarNames, buildInputs ? [ ], enableMdbook ? false
, grammarPath ? "grammars", mdbookPath ? ".", nativeBuildInputs ? [ ]
, outputs ? [ ], silver ? default-silver, silverFlags ? [ ], silverInputs ? [ ]
, silverName ? silver.meta.mainProgram, ... }@args:

stdenv.mkDerivation ({
  outputs =
    lib.unique (outputs ++ [ "out" ] ++ lib.optional enableMdbook "doc");
  buildInputs = buildInputs ++ builtins.map lib.getDev silverInputs;
  nativeBuildInputs = nativeBuildInputs ++ lib.optional enableMdbook mdbook
    ++ [ silver ];

  buildPhase = ''
    runHook preBuild

    mkdir -p $out/generated
    for grammarName in ${lib.escapeShellArgs grammarNames}; do
      ${silverName} \
        -G $out/generated \
        -I ${lib.escapeShellArg grammarPath} \
        -o "$(mktemp)" \
        ${lib.escapeShellArgs silverFlags} \
        "$grammarName"
    done
  '' + lib.optionalString enableMdbook ''
    mdbook build -d $doc ${lib.escapeShellArg mdbookPath}
  '' + ''
    runHook postBuild
  '';

  installPhase = ''
    runHook preInstall

    cp -r ${lib.escapeShellArg grammarPath} $out/grammars
    mkdir -p $out/nix-support
    substitute ${./setup-hook.sh} $out/nix-support/setup-hook \
      --subst-var out

    runHook postInstall
  '';
} // lib.optionalAttrs enableMdbook { MDBOOK_BUILD__CREATE_MISSING = "false"; }
  // lib.filterAttrs
  (name: _: !lib.elem name [ "buildInputs" "nativeBuildInputs" "outputs" ])
  args)
