import silver:modification:copper;

terminal AtomNameSeparator_t '.';
-- Top Level Atom Names
terminal AtomNameComment_t 'comment';
terminal AtomNameConstant_t 'constant';
terminal AtomNameEntity_t 'entity';
terminal AtomNameInvalid_t 'invalid';
terminal AtomNameKeyword_t 'keyword';
terminal AtomNameMarkup_t 'markup';
terminal AtomNameMeta_t 'meta';
terminal AtomNameStorage_t 'storage';
terminal AtomNameString_t 'string';
terminal AtomNameSupport_t 'support';
terminal AtomNameVariable_t 'variable';

-- Second Level Atom Naming Convetions
-- second level for comments
terminal AtomNameLine_t 'line';
terminal AtomNameBlock_t 'block';
-- second level for constants
terminal AtomNameNumeric_t 'numeric';
terminal AtomNameCharacter_t 'character';
terminal AtomNameLanguage_Constant_t 'language';
terminal AtomNameOther_Constant_t 'other'; -- needed for name collision
-- second level for entities
terminal AtomNameName_t 'name';
terminal AtomNameOther_Entity_t 'other';
-- second level for invalid
terminal AtomNameIllegal_t 'illegal';
terminal AtomNameDeprecated_t 'deprecated';
-- second level for keyword
terminal AtomNameControl_t 'control';
terminal AtomNameOperator_t 'operator';
terminal AtomNameOther_Keyword_t 'other';
-- second level for markup
terminal AtomNameUnderline_t 'underline';
terminal AtomNameBold_t 'bold';
terminal AtomNameHeading_t 'heading';
terminal AtomNameItalic_t 'italic';
terminal AtomNameList_t 'list';
terminal AtomNameQuote_t 'quote';
terminal AtomNameRaw_t 'raw';
terminal AtomNameOther_Markup_t 'other';
-- second level for storage
terminal AtomNameType_Storage_t 'type';
terminal AtomNameModifier_t 'modifier';
-- second level for string
terminal AtomNameQuoted_t 'quoted';
terminal AtomNameUnquoted_t 'unquoted';
terminal AtomNameInterpolated_t 'interpolated';
terminal AtomNameRegexp_t 'regexp';
terminal AtomNameOther_String_t 'other';
-- second level for support 
terminal AtomNameFunction_Support_t 'function';
terminal AtomNameClass_t 'class';
terminal AtomNameType_Support_t 'type';
terminal AtomNameConstant_Support_t 'constant';
terminal AtomNameVariable_Support_t 'variable';
terminal AtomNameOther_Support_t 'other';
-- second level for variable
terminal AtomNameParameter_t 'parameter';
terminal AtomNameLanguage_Variable_t 'language';
terminal AtomNameOther_Variable_t 'other';

-- Third Level of Naming Conventions
-- third level for line comments
terminal AtomNameDoubleSlash_t 'double-slash';
terminal AtomNameDoubleDash_t 'double-dash';
terminal AtomNameNumberSign_t 'number-sign';
terminal AtomNamePercentage_t 'percentage';
-- third level for block comments
terminal AtomNameDocumentation_t 'documentation';
-- third level for character constants
terminal AtomNameEscape_t 'escape';
-- third level for name entities
terminal AtomNameFunction_EntityName_t 'function';
terminal AtomNameType_EntityName_t 'type';
terminal AtomNameTag_t 'tag';
terminal AtomNameSection_t 'section';
-- third level for other entities
terminal AtomNameInheritedClass_t 'inherited-class';
terminal AtomNameAttributeName_t 'attribute-name';
-- third level for underline markup
terminal AtomNameLink_t 'link';
-- third level for list markup
terminal AtomNameNumbered_t 'numbered';
terminal AtomNameUnnumbered_t 'unnumbered';
-- third level for quoted strings
terminal AtomNameSingle_t 'single';
terminal AtomNameDouble_t 'double';
terminal AtomNameTriple_t 'triple';
terminal AtomNameOther_StringQuoted_t 'other';

nonterminal AtomName_c with unparse, location;
nonterminal AtomNameComment_c with unparse, location;
nonterminal AtomNameConstant_c with unparse, location; 
nonterminal AtomNameEntity_c with unparse, location;
nonterminal AtomNameInvalid_c with unparse, location;
nonterminal AtomNameKeyword_c with unparse, location;
nonterminal AtomNameMarkup_c with unparse, location;
nonterminal AtomNameStorage_c with unparse, location;
nonterminal AtomNameString_c with unparse, location;
nonterminal AtomNameSupport_c with unparse, location;
nonterminal AtomNameVariable_c with unparse, location;

concrete productions
top::AtomName_c
| first::'comment' rest::AtomNameComment_c
  {
    top.unparse = first.lexeme ++ rest.unparse;
  }
| first::AtomNameConstant_t rest::AtomNameConstant_c
  {
    top.unparse = first.lexeme ++ rest.unparse;
  }
| first::'entity' rest::AtomNameEntity_c
  {
    top.unparse = first.lexeme ++ rest.unparse;
  }
| first::'invalid' rest::AtomNameInvalid_c
  {
    top.unparse = first.lexeme ++ rest.unparse;
  }
| first::'keyword' rest::AtomNameKeyword_c
  {
    top.unparse = first.lexeme ++ rest.unparse;
  }
| first::'markup' rest::AtomNameMarkup_c
  {
    top.unparse = first.lexeme ++ rest.unparse;
  }
| first::'storage' rest::AtomNameStorage_c
  {
    top.unparse = first.lexeme ++ rest.unparse;
  }
| first::'string' rest::AtomNameString_c
  {
    top.unparse = first.lexeme ++ rest.unparse;
  }
| first::'support' rest::AtomNameSupport_c
  {
    top.unparse = first.lexeme ++ rest.unparse;
  }
| first::AtomNameVariable_t rest::AtomNameVariable_c
  {
    top.unparse = first.lexeme ++ rest.unparse;
  }

nonterminal AtomNameCommentLine_c with unparse, location;
nonterminal AtomNameCommentBlock_c with unparse, location;
concrete productions 
top::AtomNameComment_c
| 
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'line' rest::AtomNameCommentLine_c
  {
    top.unparse = sep.lexeme ++ first.lexeme ++ rest.unparse;
  }
| sep::AtomNameSeparator_t first::'block' rest::AtomNameCommentBlock_c
  {
    top.unparse = sep.lexeme ++ first.lexeme ++ rest.unparse;
  }

concrete productions
top::AtomNameCommentLine_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'double-slash' 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'double-dash' 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'number-sign' 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'percentage' 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

concrete productions 
top::AtomNameCommentBlock_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'documentation' 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

nonterminal AtomNameConstantCharacter_c with unparse, location;
concrete productions
top::AtomNameConstant_c
| 
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'numeric' 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'character' rest::AtomNameConstantCharacter_c
  {
    top.unparse = sep.lexeme ++ first.lexeme ++ rest.unparse;
  }
| sep::AtomNameSeparator_t first::AtomNameLanguage_Constant_t
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameOther_Constant_t 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

concrete productions
top::AtomNameConstantCharacter_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'escape' 
  {
  }


nonterminal AtomNameEntityName_c with unparse, location;
nonterminal AtomNameEntityOther_c with unparse, location;
concrete productions
top::AtomNameEntity_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'name' rest::AtomNameEntityName_c
  {
    top.unparse = sep.lexeme ++ first.lexeme ++ rest.unparse;
  }
| sep::AtomNameSeparator_t first::AtomNameOther_Entity_t rest::AtomNameEntityOther_c
  {
    top.unparse = sep.lexeme ++ first.lexeme ++ rest.unparse;
  }

concrete productions
top::AtomNameEntityName_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::AtomNameFunction_EntityName_t
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameType_EntityName_t
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'tag'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'section'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

concrete productions
top::AtomNameEntityOther_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'inherited-class'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'attribute-name'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

concrete productions
top::AtomNameInvalid_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'illegal' 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'deprecated' 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

concrete productions
top::AtomNameKeyword_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'control' 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'operator' 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameOther_Keyword_t
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

nonterminal AtomNameMarkupUnderline_c with unparse, location;
nonterminal AtomNameMarkupList_c with unparse, location;
concrete productions
top::AtomNameMarkup_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'underline' rest::AtomNameMarkupUnderline_c
  {
    top.unparse = sep.lexeme ++ first.lexeme ++ rest.unparse;
  }
| sep::AtomNameSeparator_t first::'bold' 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'heading' 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'italic'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'list' rest::AtomNameMarkupList_c
  {
    top.unparse = sep.lexeme ++ first.lexeme ++ rest.unparse;
  }
| sep::AtomNameSeparator_t first::'quote'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'raw'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameOther_Markup_t
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

concrete productions
top::AtomNameMarkupUnderline_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'link'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

concrete productions
top::AtomNameMarkupList_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'numbered'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'unnumbered'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

concrete productions
top::AtomNameStorage_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::AtomNameType_Storage_t
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'modifier'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

nonterminal AtomNameStringQuoted_c with unparse, location;
concrete productions
top::AtomNameString_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'quoted' rest::AtomNameStringQuoted_c
  {
    top.unparse = sep.lexeme ++ first.lexeme ++ rest.unparse;
  }
| sep::AtomNameSeparator_t first::'unquoted'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'interpolated'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'regexp'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameOther_String_t
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

concrete productions
top::AtomNameStringQuoted_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'single'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'double'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::'triple'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameOther_StringQuoted_t
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
concrete productions
top::AtomNameSupport_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::AtomNameFunction_Support_t 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameClass_t
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameType_Support_t 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameConstant_Support_t 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameVariable_Support_t 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameOther_Support_t 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

concrete productions
top::AtomNameVariable_c
|
  {
    top.unparse = "";
  }
| sep::AtomNameSeparator_t first::'parameter'
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameLanguage_Variable_t
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }
| sep::AtomNameSeparator_t first::AtomNameOther_Variable_t 
  {
    top.unparse = sep.lexeme ++ first.lexeme;
  }

terminal AtomNameConvention_t 'atomNameConvention';
-- Allows atomName on lexer classes
concrete production lexerClassModifierAtomName
top::LexerClassModifier ::= 'atomNameConvention' '=' name::AtomName_c
{
  top.unparse = "atomNameConvention = " ++ name.unparse;

  top.lexerClassModifiers = [lexerClassAtomName(name.unparse)];
  top.errors := [];
}

concrete production terminalModifierAtomName
top::TerminalModifier ::= 'atomNameConvention' '=' name::AtomName_c
{
  top.unparse = "atomNameConvention = " ++ name.unparse;

  top.terminalModifiers = [terminalAtomName(name.unparse)];
  top.errors := [];
}
