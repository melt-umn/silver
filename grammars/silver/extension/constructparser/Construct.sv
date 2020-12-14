grammar silver:extension:constructparser;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:modification:copper;
import silver:extension:list;

terminal Construct_t 'construct' lexer classes {KEYWORD};
terminal Translator_t 'translator' lexer classes {KEYWORD};
terminal Using_t 'using' lexer classes {KEYWORD};

concrete production construct_c
top::Root ::= gdcl::GrammarDcl  mStmts::ModuleStmts  is::ImportStmts
  'construct' parserName::Name  'as' m::QName  'translator'  'using'  ms::ParserComponents
{
  local agDcls :: AGDcls =
    consAGDcls(setJarName, consAGDcls(prsr, consAGDcls(main,
      nilAGDcls(location=top.location), location=top.location),
      location=top.location), location=top.location);

  local setJarName :: AGDcl = jarNameDcl(parserName, location=top.location);

  local prsr :: AGDcl =
    parserDcl('parser', name("extendedParser", top.location), '::',
      nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "Root"), location=top.location),
        botlNone(location=top.location), location=top.location),
      '{',
      consParserComponent(
        parserComponent(moduleName(m, location=top.location),
          nilParserComponentModifier(location=top.location), ';', location=top.location),
        ms, location=top.location),
      '}', location=top.location);

  local main :: AGDcl =
    functionDcl('function', name("main", top.location),
      functionSignature(
        noConstraintList(location=top.location),
        functionLHS(
          nominalTypeExpr(
            qNameTypeId(terminal(IdUpper_t, "IOVal"), location=top.location),
            botlSome('<', typeListSingle(integerTypeExpr('Integer', location=top.location),
              location=top.location), '>', location=top.location),
            location=top.location
          ),
          location=top.location),
        '::=',
        productionRHSCons(
          productionRHSElem(name("args",  top.location), '::',
            listTypeExpr('[', stringTypeExpr('String', location=top.location), ']',
            location=top.location), location=top.location),
          productionRHSCons(
            productionRHSElem(name("ioIn",  top.location), '::',
              nominalTypeExpr(qNameTypeId(terminal(IdUpper_t, "IO"), location=top.location),
                botlNone(location=top.location), location=top.location),
              location=top.location),
            productionRHSNil(location=top.location),
            location=top.location
          ),
          location=top.location
        ), location=top.location),
      productionBody('{',
        productionStmtsSnoc(productionStmtsNil(location=top.location),
          returnDef('return',
            Silver_Expr { driver(args, ioIn, extendedParser) },
            ';', location=top.location),
          location=top.location),
        '}', location=top.location),
      location=top.location);

  local importStmts :: ImportStmts =
    consImportStmts(
      importStmt('import', moduleAll(m, location=top.location), ';', location=top.location),
      is, location=top.location);

  forwards to root(gdcl, mStmts, importStmts, agDcls, location=top.location);
}

