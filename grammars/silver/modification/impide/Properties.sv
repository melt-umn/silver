grammar silver:modification:impide;

terminal ImpIde_OptFunc_Property 'property' lexer classes {KEYWORD};

terminal ImpIde_PropType_string_t 'string' lexer classes {KEYWORD};
terminal ImpIde_PropType_integer_t 'integer' lexer classes {KEYWORD};
terminal ImpIde_PropType_path_t 'path' lexer classes {KEYWORD};
terminal ImpIde_PropType_url_t 'url' lexer classes {KEYWORD};

terminal ImpIde_PropOption_Required_t 'required' lexer classes {KEYWORD};
terminal ImpIde_PropOption_Display_t 'display' lexer classes {KEYWORD};
terminal ImpIde_PropOption_Default_t 'default' lexer classes {KEYWORD};


nonterminal PropertyList with propDcls, errors;
nonterminal Property with location, propDcls, errors;

concrete production nilPropertyList
top::PropertyList ::= 
{
  top.propDcls = [];
  top.errors := [];
}

concrete production consPropertyList
top::PropertyList ::= p::Property pList::PropertyList
{
  top.propDcls = p.propDcls ++ pList.propDcls;
  top.errors := p.errors ++ pList.errors;
}

-- TODO: syntax? a bit inconsistent with rest of silver that we don't have at least commans between options
concrete production makeProperty
top::Property ::= 'property' pname::IdLower_t ptype::TypeName options::IdePropertyOptions ';'
{
  local optional::Boolean = null(options.propRequired);
  local defaultVal::String = if null(options.propDefault) then "" else head(options.propDefault);
  local displayName::String = if null(options.propDisplay) then pname.lexeme else head(options.propDisplay);

  top.propDcls = [makeIdeProperty(pname.lexeme, ptype.propType, optional, defaultVal, displayName)];

  top.errors := []; -- TODO checking of default values corresponds to type? maybe...
  top.errors <- if length(options.propRequired) > 1 then [err(top.location, "Multiple 'required' declarations")] else [];
  top.errors <- if length(options.propDefault) > 1 then [err(top.location, "Multiple 'default' declarations")] else [];
  top.errors <- if length(options.propDisplay) > 1 then [err(top.location, "Multiple 'display' declarations")] else [];
}


nonterminal TypeName with propType;

synthesized attribute propType :: PropType;

concrete production propType_String
top::TypeName ::= 'string'
{
  top.propType = stringPropType();
}

concrete production propType_Integer
top::TypeName ::= 'integer'
{
  top.propType = integerPropType();
}

concrete production propType_Path
top::TypeName ::= 'path'
{
  top.propType = pathPropType();
}

concrete production propType_URL
top::TypeName ::= 'url'
{
  top.propType = urlPropType();
}


nonterminal IdePropertyOption with propRequired, propDefault, propDisplay;
nonterminal IdePropertyOptions with propRequired, propDefault, propDisplay;

synthesized attribute propRequired :: [Boolean];
synthesized attribute propDefault :: [String];
synthesized attribute propDisplay :: [String];

concrete production nilPropertyOptions
top::IdePropertyOptions ::= 
{
  top.propRequired = [];
  top.propDefault = [];
  top.propDisplay = [];
}

concrete production consPropertyOptions
top::IdePropertyOptions ::= h::IdePropertyOption t::IdePropertyOptions
{
  top.propRequired = h.propRequired ++ t.propRequired;
  top.propDefault = h.propDefault ++ t.propDefault;
  top.propDisplay = h.propDisplay ++ t.propDisplay;
}

aspect default production 
top::IdePropertyOption ::=
{
  top.propRequired = [];
  top.propDefault = [];
  top.propDisplay = [];
}

concrete production idePropertyOption_optional
top::IdePropertyOption ::= 'required'
{
  top.propRequired = [true];
}

concrete production idePropertyOption_defaultVal
top::IdePropertyOption ::= 'default' '=' str::String_t
{
  top.propDefault = [substring(1, length(str.lexeme) - 1, str.lexeme)];
}

concrete production idePropertyOption_displayName
top::IdePropertyOption ::= 'display' '=' str::String_t
{
  top.propDisplay = [substring(1, length(str.lexeme) - 1, str.lexeme)];
}


