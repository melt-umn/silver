grammar lib:lsp:json;

nonterminal JSONText with jsonValue;
nonterminal JSONValueList with jsonValues;
nonterminal JSONKeyValuePairList with jsonKeyValuePairs;
nonterminal JSONKeyValuePair with jsonKeyValuePair;
terminal JSONInt_t     /[\-]?[0-9]+/;
terminal JSONFloat_t   /[\-]?[0-9]+[\.][0-9]+/;
terminal JSONString_t  /[\"]([^\r\n\"\\]|[\\][\"]|[\\][\\]|[\\]b|[\\]n|[\\]r|[\\]f|[\\]t)*[\"]/;
terminal JSONNull_t    'null';
terminal JSONTrue_t    'true';
terminal JSONFalse_t   'false';
terminal JSONKeyValueSeparator_t ':';
terminal JSONValueSeparator_t ',';
terminal JSONBeginObject_t '{';
terminal JSONEndObject_t '}';
terminal JSONBeginArray_t '[';
terminal JSONEndArray_t ']';
ignore terminal WhiteSpace_t /[\r\n\t\ ]+/;

concrete production jsonNullText
top::JSONText ::= 'null'
{
  top.jsonValue = jsonNull();
}

concrete production jsonTrueText
top::JSONText ::= 'true'
{
  top.jsonValue = jsonBoolean(true);
}

concrete production jsonFalseText
top::JSONText ::= 'false'
{
  top.jsonValue = jsonBoolean(false);
}
concrete production jsonStringText
top::JSONText ::= str::JSONString_t
{
  top.jsonValue = jsonString(substring(1, length(str.lexeme)-1, str.lexeme));
}

concrete production jsonIntText
top::JSONText ::= int::JSONInt_t
{
  top.jsonValue = jsonInteger(toInt(int.lexeme));
}

concrete production jsonFloatText
top::JSONText ::= float::JSONFloat_t
{
  top.jsonValue = jsonNumber(toFloat(float.lexeme));
}

concrete production jsonObjectText
top::JSONText ::= '{' keyVals::JSONKeyValuePairList '}'
{
  top.jsonValue = jsonObject(keyVals.jsonKeyValuePairs);
}

concrete production jsonArrayText
top::JSONText ::= '[' vals::JSONValueList ']'
{
  top.jsonValue = jsonArray(vals.jsonValues);
}

concrete production nilJsonValueList
top::JSONValueList ::=
{
  top.jsonValues = [];
}

concrete production singleJsonValueList
top::JSONValueList ::= hd::JSONText
{
  top.jsonValues = [hd.jsonValue];
}

concrete production consJsonValueList
top::JSONValueList ::= hd::JSONText ',' rest::JSONValueList
{
  top.jsonValues = hd.jsonValue :: rest.jsonValues;
}

concrete production nilJsonKeyValuePairs
top::JSONKeyValuePairList ::=
{
  top.jsonKeyValuePairs = [];
}

concrete production singleJsonKeyValuePair
top::JSONKeyValuePairList ::= hd::JSONKeyValuePair
{
  top.jsonKeyValuePairs = [hd.jsonKeyValuePair];
}

concrete production consJsonKeyValuePairs
top::JSONKeyValuePairList ::= hd::JSONKeyValuePair ',' rest::JSONKeyValuePairList
{
  top.jsonKeyValuePairs = hd.jsonKeyValuePair :: rest.jsonKeyValuePairs;
}

concrete production jsonKeyValuePair
top::JSONKeyValuePair ::= key::JSONString_t ':' value::JSONText
{
  -- strip off the quotation marks in the lexeme
  top.jsonKeyValuePair = pair(substring(1, length(key.lexeme)-1, key.lexeme), value.jsonValue);
}
