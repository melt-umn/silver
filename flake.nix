{
  outputs = { self, flake-utils, nixpkgs }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        pkgs = nixpkgs.legacyPackages.${system};
        bootstrap-rev = "e7d612b72bf168daeb3d712eadf174b874ed7842";

        # The Java to use.
        java = pkgs.jdk21_headless;

        # Metadata shared by bootstrap and non-bootstrap.
        meta = {
          description =
            "An attribute grammar-based programming language for composable language extensions.";
          longDescription = ''
            Silver is an extensible attribute grammar system that support many modern extensions to Knuth's original design.
            These include higher-order attributes, reference attributes, forwarding, aspects, and collections attributes.
            Its type system support parametric polymorphism.
            Silver is distributed with Copper, a parser and context-aware scanner generator.

            It is designed for the modular development of composable language extensions, with language features and analyses to support this. 
          '';
          homepage = "http://melt.cs.umn.edu/silver/";
          license = pkgs.lib.licenses.lgpl3Plus;
          mainProgram = "silver";
          platforms = pkgs.lib.platforms.all;
        };

      in rec {
        devShells.default = pkgs.mkShell {
          inputsFrom = builtins.attrValues packages;
          nativeBuildInputs = [ pkgs.wget ];
          shellHook = ''
            PATH="$(pwd)/support/bin:$PATH"
          '';
        };

        legacyPackages = {
          mkComposedSilver =
            pkgs.callPackage ./support/nix/mkComposedSilver.nix {
              inherit java;
              inherit (legacyPackages) mkSilverBin;
              inherit (packages) silver;
            };
          mkSilverBin = pkgs.callPackage ./support/nix/mkSilverBin {
            default-java = java;
            default-silver = packages.silver;
          };
          mkSilverLanguageServer =
            pkgs.callPackage ./support/nix/mkSilverLanguageServer {
              inherit java;
              default-silver = packages.silver;
            };
          mkSilverLib = pkgs.callPackage ./support/nix/mkSilverLib {
            default-silver = packages.silver;
          };
        };

        packages = {
          default = packages.silver;
          silver = pkgs.stdenvNoCC.mkDerivation {
            pname = "silver";
            version =
              if (self ? rev) then self.shortRev else self.dirtyShortRev;
            src = ./.;

            nativeBuildInputs = [ pkgs.ant pkgs.makeWrapper java ];

            buildPhase = ''
              # First, build the grammars the runtime depends on. We avoid using the
              # actual silver script so that the old SILVER_HOME doesn't leak in.
              SILVER_HOME=$(pwd) java $SVJVM_FLAGS \
                -jar ${packages.bootstrap}/jars/silver.compiler.composed.Default.jar \
                -G generated \
                -o /dev/null \
                silver:core silver:xml:ast

              # Next, build the runtime itself.
              mkdir jars
              cp ${packages.bootstrap}/jars/commonmark-0.17.1.jar \
                 ${packages.bootstrap}/jars/CopperCompiler.jar \
                 jars/
              cd runtime/java
              ant
              mv SilverRuntime.jar ../../jars
              cd ../..

              # Build the rest of Silver.
              mkdir -p $out/generated
              SILVER_HOME=$(pwd) java $SVJVM_FLAGS \
                -jar ${packages.bootstrap}/jars/silver.compiler.composed.Default.jar \
                -G $out/generated \
                -o jars/silver.compiler.composed.Default.jar \
                --one-jar \
                silver:compiler:composed:Default
              ant

              # Install the jars.
              mkdir -p $out
              mv jars $out
            '';

            installPhase = ''
              mkdir -p $out/bin
              cp -r grammars $out/grammars
              for name in silver silver-custom; do
                install -Dt $out/support/bin support/bin/$name
                ln -s ../support/bin/$name $out/bin/$name
                wrapProgram $out/bin/$name \
                  --prefix PATH : ${pkgs.ant}/bin \
                  --prefix PATH : ${pkgs.coreutils}/bin \
                  --prefix PATH : ${java}/bin \
                  --prefix SILVER_HOST_GEN : $out/generated
              done
            '';

            ANT_OPTS = "-Xss10M";
            SVJVM_FLAGS = "-Xmx6G -Xss20M";

            inherit meta;
          };

          # Bootstrap compiler
          bootstrap = pkgs.stdenvNoCC.mkDerivation {
            pname = "silver-bootstrap";
            version = "g${bootstrap-rev}";
            src = pkgs.fetchFromGitHub {
              owner = "melt-umn";
              repo = "silver";
              rev = bootstrap-rev;
              hash = "sha256-J1+/gU5iRUvrd67FBMVrbizwWFMKwEQShInZV7VLZwo=";
            };

            nativeBuildInputs = [ pkgs.makeWrapper ];
            installPhase = ''
              mkdir -p $out/bin $out/jars $out/support
              cp -r support/bin $out/support/bin

              # These need to be copied, not linked, otherwise the Java classloader won't
              # pick them up.
              cp ${packages.bootstrap-commonmark} $out/jars/commonmark-0.17.1.jar
              cp ${packages.bootstrap-copper}     $out/jars/CopperCompiler.jar
              cp ${packages.bootstrap-compiler}   $out/jars/silver.compiler.composed.Default.jar
              cp ${packages.bootstrap-runtime}    $out/jars/SilverRuntime.jar

              # Silver needs to be a symlink, so we can't just link the whole bin
              # directory.
              ln -s ../support/bin/silver $out/bin/silver
              wrapProgram $out/bin/silver \
                --prefix PATH : ${pkgs.ant}/bin \
                --prefix PATH : ${pkgs.coreutils}/bin \
                --prefix PATH : ${java}/bin
            '';

            inherit meta;
          };

          # Bootstrap JARs
          bootstrap-commonmark = pkgs.fetchurl {
            url =
              "https://foundry.remexre.xyz/commit-artifacts/${bootstrap-rev}/commonmark-0.17.1.jar";
            hash = "sha256-5jrQgyZPWHdSJ0c8NduGbs3DhDxHvSvy8kdan65e938=";
          };
          bootstrap-compiler = pkgs.fetchurl {
            url =
              "https://foundry.remexre.xyz/commit-artifacts/${bootstrap-rev}/silver.compiler.composed.Default.jar";
            hash = "sha256-pUYGoq2Fk8Lh6gVcIatP9Drz3hcA38lwZuScyHoLeFY=";
          };
          bootstrap-copper = pkgs.fetchurl {
            url =
              "https://foundry.remexre.xyz/commit-artifacts/${bootstrap-rev}/CopperCompiler.jar";
            hash = "sha256-fu7PHitcR8lK3cWRy8YEa2cXQufmcUGVr3eMp/ctKMY=";
          };
          bootstrap-runtime = pkgs.fetchurl {
            url =
              "https://foundry.remexre.xyz/commit-artifacts/${bootstrap-rev}/SilverRuntime.jar";
            hash = "sha256-7zEhDlh2bmo4qNyQP+GOF4FAXYwiPDO6P0TDiWgu9kI=";
          };
        };
      });
}
