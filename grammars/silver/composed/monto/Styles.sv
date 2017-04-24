grammar silver:composed:monto;

import lib:monto:helpers;

global styles :: [Pair<String Style>] =
  [ pair("silver:definition:core:COMMENT", style(["comment"], nothing()))
  , pair("silver:definition:core:IDENTIFIER", style(["identifier"], nothing()))
  , pair("silver:definition:core:KEYWORD", style(["keyword"], nothing()))
  , pair("silver:definition:core:MODSTMT", style(["keyword"], nothing()))
  , pair("silver:definition:core:LITERAL", style(["literal"], nothing()))
  , pair("silver:definition:core:RESERVED", style(["keyword"], nothing()))
  ];
