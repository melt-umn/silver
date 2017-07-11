
imports silver:definition:core;
imports silver:extension:convenience;
imports silver:definition:type:syntax;

terminal Origins_kwd   'origins' lexer classes {KEYWORD,RESERVED};
terminal At_kwd        '@'       association = left, lexer classes {KEYWORD,RESERVED};
terminal AtDot_kwd     '@.'      association = left, lexer classes {KEYWORD,RESERVED};
terminal AtLParen_kwd  '@('      association = left, lexer classes {KEYWORD,RESERVED};

concrete production originAGDcls
top::AGDcl ::= t::'origins' 'on' qlist::QNames ';'
{

  forwards to
    appendAGDcl(
      appendAGDcl(
        nonterminalDcl(  -- nonterminal NTWrapper;
          openNt(location=top.location),
          'nonterminal',
          nameIdUpper(
            terminal(IdUpper_t,"NTWrapper"),
            location=top.location),
          botlNone(location=top.location),
          ';',
          location=top.location),

      appendAGDcl(
        annotationDcl(  -- annotation origin::NTWrapper;
          'annotation',
          qName( 
            top.location,"origin"),
          botlNone(location=top.location),
          '::',
          nominalType(
            qNameTypeId(terminal(IdUpper_t,"NTWrapper"),
              location=top.location),
            botlNone(location=top.location),
            location=top.location),
            ';',
          location=top.location),
        annotationDcl( -- annotation new::NTWrapper;
          'annotation',
          qName(
            top.location,"isNew"),
          botlNone(location=top.location),
          '::',
          booleanType(
            'Boolean',
            location=top.location),
          ';',
          location=top.location),
        location=top.location),
      location=top.location),
      
      appendAGDcl(
        constructAnnotationDcls(
          qlist, top.location),

      appendAGDcl(
        attributeDclSyn( -- synthesized attribute origin_next:: NTWrapper;
          'synthesized',
          'attribute',
          nameIdLower(
            terminal(IdLower_t,"origin_next"),
            location=top.location),
          botlNone(
            location=top.location),
          '::',
          nominalType(
            qNameTypeId(
              terminal(IdUpper_t,"NTWrapper"),
              location=top.location),
            botlNone(
              location=top.location),
            location=top.location),
          ';',
          location=top.location),

        appendAGDcl(
          attributionDcl( -- attribute origin_next occurs on NTWrapper;
            'attribute',
            qNameId(
              nameIdLower(
                terminal(IdLower_t,"origin_next"),
                location=top.location),
              location=top.location),
            botlNone(
              location=top.location),
            'occurs', 'on',
            qNameId(
              nameIdUpper(
                terminal(IdUpper_t,"NTWrapper"),
                location=top.location),
              location=top.location),
            botlNone(
              location=top.location),
            ';',
            location=top.location),

          appendAGDcl(
            constructProductions(qlist.qnames, top.location), -- abstract production ntw...

            productionDcl( -- abstract production ntwBottom
              'abstract',
              'production',
              nameIdLower(
                terminal(IdLower_t, "ntwBottom"),
                location=top.location),
              productionSignature( -- top::NTWrapper ::=
                productionLHS( -- top::NTWrapper
                  nameIdLower(
                    terminal(IdLower_t,"top"),
                    location=top.location),
                  '::',
                  nominalType(
                    qNameTypeId(
                      terminal(IdUpper_t,"NTWrapper"),
                      location=top.location),
                    botlNone(location=top.location),
                    location=top.location),
                  location=top.location),
                '::=',
                productionRHSNil(location=top.location),
                location=top.location),
              productionBody( -- {}
                '{', productionStmtsNil(location=top.location), '}',
                location=top.location),
              location=top.location),
            location=top.location),
          location=top.location),
        location=top.location),
      location=top.location), location=top.location);
}

function constructAnnotationDcls
AGDcl ::= qlist::QNames l::Location
{
  return
    case qlist of
      qNamesSingle(qNameWithTL(q,_)) ->
        appendAGDcl(
          annotateDcl(
            'annotation',
            qName(
              l,"origin"),
            botlNone(location=l),
            'occurs', 'on',
            q,
            botlNone(location=l),
            ';',
            location=l),
          annotateDcl(
            'annotation',
            qName(
              l,"isNew"),
            botlNone(location=l),
            'occurs', 'on',
            q,
            botlNone(location=l),
            ';',
            location=l),
          location=l)

    | qNamesCons(qNameWithTL(q,_),_,qnames) ->
        appendAGDcl(
          appendAGDcl(
            annotateDcl(
              'annotation',
              qName(
                l,"origin"),
              botlNone(location=l),
              'occurs', 'on',
              q,
              botlNone(location=l),
              ';',
              location=l),
            annotateDcl(
              'annotation',
              qName(
                l,"isNew"),
              botlNone(location=l),
              'occurs', 'on',
              q,
              botlNone(location=l),
              ';',
              location=l),
            location=l),
          constructAnnotationDcls(qnames, l),
          location=l)
    end;
}

function constructQNames
QNames2 ::= input::[QNameWithTL]
{
  return 
    if null(input) then error("Need nonterminals for origin!")
    else if null(tail(input)) then error("Need nonterminals for origin!")
         else if null(tail(tail(input))) then qNames2Two(head(input), ',', head(tail(input)))
              else qNames2Cons(head(input), ',', constructQNames(tail(input)));
}

function constructProductions
AGDcl ::= input::[QNameWithTL] t::Location
{
  return
    if null(input) then error("Need nonterminals for origin!")
    else if null(tail(input)) then error("Need nonterminals for origin!")
         else if null(tail(tail(input))) then
         appendAGDcl(
           productionDcl( -- abstract production ntw... top::NTWrapper ::= nt::... {}
             'abstract',
             'production',
             nameIdLower(
               terminal(IdLower_t, "ntw" ++ head(input).qnwtQN.name),
               location=t),
             productionSignature( -- top::NTWrapper ::= nt::...
               productionLHS( -- top::NTWrapper
                 nameIdLower(
                   terminal(IdLower_t,"top"),
                   location=t),
                 '::',
                 nominalType(
                   qNameTypeId(
                     terminal(IdUpper_t,"NTWrapper"),
                     location=t),
                   botlNone(location=t),
                   location=t),
                 location=t),
               '::=',
               productionRHSCons( -- nt::...
                 productionRHSElem(
                   nameIdLower(
                     terminal(IdLower_t,"nt"),
                     location=t),
                   '::',
                   nominalType(
                     qNameTypeId(
                       terminal(IdUpper_t,head(input).qnwtQN.name), --TODO
                       location=t),
                     botlNone(location=t),
                     location=t),
                   location=t),
                 productionRHSNil(location=t),
                 location=t),
               location=t),
             productionBody( -- { top.origin_next = nt.origin; }
               '{', 
               productionStmtsSnoc(
                 productionStmtsNil(location=t),
                 attributeDef( -- top.origin_next = nt.origin;
                   concreteDefLHS(
                     qNameId(
                       nameIdLower(
                         terminal(IdLower_t,"top"),
                         location=t),
                       location=t),
                     location=t),
                   '.',
                   qNameAttrOccur(
                     qNameId(
                       nameIdLower(
                         terminal(IdLower_t,"origin_next"),
                         location=t),
                       location=t),
                     location=t),
                   '=',
                   access(
                     baseExpr(
                       qNameId(
                         nameIdLower(
                           terminal(IdLower_t,"nt"),
                           location=t),
                         location=t),
                       location=t),
                     '.',
                     qNameAttrOccur(
                       qNameId(
                         nameIdLower(
                           terminal(IdLower_t,"origin"),
                           location=t),
                         location=t),
                       location=t),
                     location=t),
                   ';',
                   location=t),
                 location=t),
               '}',
               location=t),
             location=t),

{-
abstract production ntw___
top::NTWrapper ::= nt::___
{
  top.origin_next = nt.origin;
}
-}
           productionDcl(
             'abstract',
             'production',
             nameIdLower(
               terminal(IdLower_t,"ntw" ++ head(tail(input)).qnwtQN.name), --TODO
               location=t),
             productionSignature( -- top::NTWrapper ::= nt::...
               productionLHS( -- top::NTWrapper
                 nameIdLower(
                   terminal(IdLower_t,"top"),
                   location=t),
                 '::',
                 nominalType(
                   qNameTypeId(
                     terminal(IdUpper_t,"NTWrapper"),
                     location=t),
                   botlNone(location=t),
                   location=t),
                 location=t),
               '::=',
               productionRHSCons( -- nt::...
                 productionRHSElem(
                   nameIdLower(
                     terminal(IdLower_t,"nt"),
                     location=t),
                   '::',
                   nominalType(
                     qNameTypeId(
                       terminal(IdUpper_t,head(tail(input)).qnwtQN.name),
                       location=t),
                     botlNone(location=t),
                     location=t),
                   location=t),
                 productionRHSNil(location=t),
                 location=t),
               location=t),
             productionBody(
               '{', 
               productionStmtsSnoc(
                 productionStmtsNil(location=t),
                 attributeDef( -- top.origin_next = nt.origin;
                   concreteDefLHS(
                     qNameId(
                       nameIdLower(
                         terminal(IdLower_t,"top"),
                         location=t),
                       location=t),
                     location=t),
                   '.',
                   qNameAttrOccur(
                     qNameId(
                       nameIdLower(
                         terminal(IdLower_t,"origin_next"),
                         location=t),
                       location=t),
                     location=t),
                   '=',
                   access(
                     baseExpr(
                       qNameId(
                         nameIdLower(
                           terminal(IdLower_t,"nt"),
                           location=t),
                         location=t),
                       location=t),
                     '.',
                     qNameAttrOccur(
                       qNameId(
                         nameIdLower(
                           terminal(IdLower_t,"origin"),
                           location=t),
                         location=t),
                       location=t),
                     location=t),
                   ';',
                   location=t),
                 location=t),
               '}',
               location=t),
             location=t),
           location=t)
              else appendAGDcl(
{-
abstract production ntw___
top::NTWrapper ::= nt::___
{
  top.origin_next = ntw.origin;
}
-}
                     productionDcl( -- abstract production ntw___
                       'abstract',
                       'production',
                       nameIdLower(
                         terminal(IdLower_t,"ntw" ++ head(input).qnwtQN.name),
                         location=t),
                       productionSignature( -- top::NTWrapper ::= nt::...
                         productionLHS( -- top::NTWrapper
                           nameIdLower(
                             terminal(IdLower_t,"top"),
                             location=t),
                           '::',
                           nominalType(
                             qNameTypeId(
                               terminal(IdUpper_t,"NTWrapper"),
                               location=t),
                             botlNone(location=t),
                             location=t),
                           location=t),
                         '::=',
                         productionRHSCons( -- nt::...
                           productionRHSElem(
                             nameIdLower(
                               terminal(IdLower_t,"nt"),
                               location=t),
                             '::',
                             nominalType(
                               qNameTypeId(
                                 terminal(IdUpper_t,head(tail(input)).qnwtQN.name),
                                 location=t),
                               botlNone(location=t),
                               location=t),
                             location=t),
                           productionRHSNil(location=t),
                           location=t),
                         location=t),
                       productionBody(
                         '{', 
                         productionStmtsSnoc(
                           productionStmtsNil(location=t),
                           attributeDef( -- top.origin_next = ntw.origin;
                             concreteDefLHS(
                               qNameId(
                                 nameIdLower(
                                   terminal(IdLower_t,"top"),
                                   location=t),
                                 location=t),
                               location=t),
                             '.',
                             qNameAttrOccur(
                               qNameId(
                                 nameIdLower(
                                   terminal(IdLower_t,"origin_next"),
                                   location=t),
                                 location=t),
                               location=t),
                             '=',
                           access(
                             baseExpr(
                               qNameId(
                                 nameIdLower(
                                   terminal(IdLower_t,"nt"),
                                   location=t),
                                 location=t),
                               location=t),
                             '.',
                             qNameAttrOccur(
                               qNameId(
                                 nameIdLower(
                                   terminal(IdLower_t,"origin"),
                                   location=t),
                                 location=t),
                               location=t),
                             location=t),
                           ';',
                           location=t),
                         location=t),
                         '}',
                         location=t),
                       location=t),
                     constructProductions(tail(input),t),
                     location=t);
}


{-
 - The following productions require that the origin is explicitly handed to the
 - annotation by the user. This will be replaced by an inherited attribute which
 - passes down the tree which the attribute is evaluated on.
 -}

autocopy attribute ori::Expr occurs on AGDcls,Expr,ProductionBody,ProductionStmts,ProductionStmt,Exprs,ForwardInhs,ForwardInh,ExprInh,AppExprs,AnnoExpr,AnnoAppExprs,AppExpr;

synthesized attribute lhs::Expr occurs on ProductionSignature, ProductionLHS;

aspect production productionDcl
top::AGDcl ::= 'abstract' 'production' id::Name ns::ProductionSignature body::ProductionBody
{
  body.ori = ns.lhs;
}

aspect production productionSignature
top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS
{
  top.lhs = lhs.lhs;
}

aspect production productionLHS
top::ProductionLHS ::= id::Name '::' t::Type
{
  top.lhs = applicationExpr(
              baseExpr(
                qNameId(
                  case t of
                  | nominalType(qNameTypeId(id),_) ->
                      nameIdLower(
                        terminal(IdLower_t,"ntw"++id.lexeme),
                        location=top.location)
                  | _ -> error("Origin does not support this type yet")
                  end,
                  location=top.location),
                location=top.location),
              '(',
              oneAppExprs(
                presentAppExpr(
                  baseExpr(
                    qNameId(
                       id,
                       location=top.location),
                    location=top.location),
                  location=top.location),
                location=top.location),
              ')',location=top.location);
}

aspect production functionDcl
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody
{
  body.ori = 
    applicationExpr(
      baseExpr(
        qNameId(
          nameIdLower(
            terminal(IdLower_t,"ntwBottom"),
            location=top.location),
          location=top.location),
        location=top.location),
      '(',
      emptyAppExprs(
        location=top.location),
      ')',
      location=top.location);
}

concrete production oChild
top::Expr ::= '@' q::QName
{
  forwards to baseExpr(q, location=top.location);
}

concrete production oAttribute
top::Expr ::= e::QName '@.' q::QNameAttrOccur
{
  forwards to access(
                baseExpr(
                  e,
                  location=top.location),
                '.',
                q,
                location=top.location);
}

concrete production oApplication
top::Expr ::= e::Expr '@(' es::AppExprs ',' anns::AnnoAppExprs ')'
{
  forwards to
    application(
      e,'(',es,',',
      snocAnnoAppExprs(
        snocAnnoAppExprs(
          anns,
          ',',
          annoExpr(
            qNameId(
              nameIdLower(
                terminal(IdLower_t,"isNew"),
                location=top.location),
              location=top.location),
            '=',
            presentAppExpr(
              trueConst(
                'true',
                location=top.location),
              location=top.location),
            location=top.location),
          location=top.location),
        ',',
        annoExpr(
          qNameId(
            nameIdLower(
              terminal(IdLower_t,"origin"),
              location=top.location),
            location=top.location),
          '=',
          presentAppExpr(
            top.ori,
            location=top.location),
          location=top.location),
        location=top.location),
      ')',
      location=top.location);
}

concrete production oApplicationAnno
top::Expr ::= e::Expr '@(' anns::AnnoAppExprs ')'
{
  forwards to
    oApplication(e, $2, emptyAppExprs(location=$2.location), ',', anns,
                 $4, location=top.location);
}

concrete production oApplicationExpr
top::Expr ::= e::Expr '@(' es::AppExprs ')'
{
  forwards to
    oApplication(e, $2, es, ',', emptyAnnoAppExprs(location=$4.location),
                 $4, location=top.location);
}

concrete production oApplicationEmpty
top::Expr ::= e::Expr '@(' ')'
{
  forwards to
    oApplication(e, $2, emptyAppExprs(location=$2.location), ',',
                 emptyAnnoAppExprs(location=$2.location),
                 $3, location=top.location);
}
