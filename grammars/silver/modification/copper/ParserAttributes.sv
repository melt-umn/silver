grammar silver:modification:copper;

concrete production attributeDclParser
top::AGDcl ::= 'parser' 'attribute' a::Name '::' te::Type 'action' acode::ActionCode_c ';'
{
  top.location = loc(top.file, $1.line, $1.column);
  top.pp = "parser attribute " ++ a.name ++ " :: " ++ te.pp ++ " action " ++ acode.pp ++ " ;" ;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ a.name;

  top.defs = addParserAttrDcl(top.grammarName, a.location, fName, te.typerep, emptyDefs());

  top.errors <- if length(getValueDcl(fName, top.env)) > 1
                then [err(top.location, "Attribute '" ++ fName ++ "' is already bound.")]
                else [];

  top.errors := te.errors ++ acode.errors;
  top.warnings := acode.warnings;
  
  acode.signature = namedNamedSignature(top.grammarName ++ ":" ++ a.name);
  acode.blockContext = actionContext();
  acode.env = newScopeEnv(acode.defs, top.env);
  
  top.syntaxAst = [syntaxParserAttribute(fName, te.typerep, acode.actionCode)];

  forwards to agDclDefault();
}

