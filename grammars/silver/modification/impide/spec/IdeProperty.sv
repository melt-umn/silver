grammar silver:modification:impide:spec;

nonterminal IdeProperty with controlJavaTranslation, generatorJavaTranslation;

synthesized attribute controlJavaTranslation :: String;
synthesized attribute generatorJavaTranslation :: String;

abstract production makeIdeProperty
top::IdeProperty ::= propName::String propType::PropType optional::Boolean defaultVal::String displayName::String
{
  top.controlJavaTranslation =
    s"""    controls.add(new ${propType.propControl}(panel, "${propName}", "${displayName}", "${defaultVal}", ${if optional then "false" else "true"}));
""";
  -- TODO: honestly, we ought to just build this whole string statically, and do escaping here, too.
  top.generatorJavaTranslation =
    s"""    sb.append("${propName}/${propType.strPropType} = ");sb.append(escape("${defaultVal}"));sb.append("\n");
""";
}


nonterminal PropType with strPropType, propControl;

synthesized attribute strPropType :: String;
synthesized attribute propControl :: String;

abstract production stringPropType
top::PropType ::=
{
  top.strPropType = "string";
  top.propControl = "TextPropertyControl";
}

abstract production pathPropType
top::PropType ::=
{
  top.strPropType = "path";
  top.propControl = "PathPropertyControl";
}

abstract production urlPropType
top::PropType ::=
{
  top.strPropType = "url";
  top.propControl = "URLPropertyControl";
}

abstract production integerPropType
top::PropType ::=
{
  top.strPropType = "integer";
  top.propControl = "IntegerPropertyControl";
}

