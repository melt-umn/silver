{ pkgs ? import <nixpkgs> { } }:

pkgs.mkShell {
  nativeBuildInputs = with pkgs; [ ant openjdk_headless wget ];
  shellHook = ''
    PATH="$(pwd)/support/bin:$PATH"
  '';
}
