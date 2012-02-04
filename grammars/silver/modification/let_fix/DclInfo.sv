grammar silver:modification:let_fix;

abstract production lexicalLocalDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;
  top.unparse = error("lexical values should never appear in interface files.");
  
  top.typerep = ty;

  top.refDispatcher = lexicalLocalReference;
  top.defDispatcher = errorValueDef; -- TODO for better error messages, mention it's a lexical local
  top.defLHSDispatcher = errorDefLHS;

  forwards to defaultDcl();
}

function addLexicalLocalDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(decorate lexicalLocalDcl(sg,sl,fn,ty) with {}), defs);
}

