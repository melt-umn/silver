grammar silver:compiler:extension:abella_compilation;


imports silver:compiler:driver:util;


--Whether we should try to output anything
synthesized attribute shouldOutput::Boolean;
--The text we should output for the grammar
synthesized attribute output::String;


attribute
   shouldOutput, output,
   prods, nonterminals, attrs, attrOccurrences, localAttrs,
   inheritedAttrs, associatedAttrs
occurs on RootSpec;


aspect production interfaceRootSpec
top::RootSpec ::= _ _ _
{
  top.shouldOutput = false;
  top.output = "";
}

aspect production errorRootSpec
top::RootSpec ::= _ _ _ _ _
{
  top.shouldOutput = false;
  top.output = "";
}

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar grammarName::String grammarSource::String
                  grammarTime::Integer generateLocation::String
{
  g.attrTypeSchemas = g.attrTypeSchemas_up;
  top.shouldOutput = grammarName != "silver:core";
  local componentName::String = shortestName(grammarName);
  top.output =
      generateContents(g.nonterminals, g.attrs, g.attrOccurrences,
         g.inheritedAttrs, g.localAttrs, g.associatedAttrs, g.prods,
         shortestName(grammarName));
}

