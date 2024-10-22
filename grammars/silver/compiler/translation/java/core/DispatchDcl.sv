grammar silver:compiler:translation:java:core;

aspect production dispatchSigDcl
top::AGDcl ::= 'dispatch' id::Name '=' sig::ProductionSignature ';'
{
  local className :: String = "P" ++ id.name;
  
  -- Currently, implementation prods directly extend the nonterminal class.
  -- This just exists as a place to store the child indices.
  top.genFiles := [(className ++ ".java", s"""
package ${makeName(top.grammarName)};

// ${sig.unparse}
public final class ${className} {

${makeIndexDcls(0, namedSig.inputElements)}

}
""")];
}
