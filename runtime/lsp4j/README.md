# lsp4j utils
This library contains utilities for interoperation between lsp4j and Silver/Copper,
e.g. encoding semantic tokens, converting Silver Message values into lsp4j Diagnostics,
loading bundled standard library resource files, etc.
It is currently used by the [LSP implementation for Silver](../../language-server/) itself,
but is intended for reuse in LSP implementations in other Silver-specified languages.