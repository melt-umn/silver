grammar silver:composed:monto;

import lib:monto:helpers;

global styles :: [Pair<String Style>] = []; -- TODO
{-
  [ pair("lib:monto:helpers:MONTO_COMMENT", style(["comment"], nothing()))
  , pair("lib:monto:helpers:MONTO_FUNCTION", style(["function", "name"], nothing()))
  , pair("lib:monto:helpers:MONTO_IDENTIFIER", style(["variable", "name"], nothing()))
  , pair("lib:monto:helpers:MONTO_KEYWORD", style(["keyword"], nothing()))
  , pair("lib:monto:helpers:MONTO_LITERAL", style(["constant"], nothing()))
  , pair("lib:monto:helpers:MONTO_OPERATOR", style(["operator"], nothing()))
  ];
-}
