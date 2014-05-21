
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
        location=top.location),
      appendAGDcl(
        multipleAttributionDclsSingleMany(  -- attribute origin occurs on ...;
          'attribute',
          qName(
            top.location,"origin"),
          botlNone(location=top.location),
          'occurs',
          'on',
          constructQNames(qlist.qnames),
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
      location=top.location);
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
             productionBody( -- {}
               '{', productionStmtsNil(location=t), '}',
               location=t),
             location=t),
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
               '{', productionStmtsNil(location=t), '}',
               location=t),
             location=t),
           location=t)
              else appendAGDcl(
                     productionDcl(
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
                         '{', productionStmtsNil(location=t), '}',
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

synthesized attribute wrapper::Expr;
nonterminal Wrapper with wrapper, location;

concrete production wrapperParam
top::Wrapper ::= q::QName '(' t::QName ')'
{
  top.wrapper =
            applicationExpr(
              baseExpr(
                q,
                location=top.location),
              '(',
              oneAppExprs(
                presentAppExpr(
                  baseExpr(
                    t,
                    location=top.location),
                  location=top.location),
                location=top.location),
              ')',location=top.location);
}

concrete production wrapperEmpty
top::Wrapper ::= q::QName '(' ')'
{
  top.wrapper =
            applicationExpr(
              baseExpr(
                q,
                location=top.location),
              '(',
              emptyAppExprs(
                location=top.location),
              ')',location=top.location);
}

concrete production oApplication
top::Expr ::= e::Expr '@(' es::AppExprs ',' anns::AnnoAppExprs ',' 'origins' ':' w::Wrapper ')'
{
  forwards to
    application(
      e,'(',es,',',
      snocAnnoAppExprs(
        anns,
        ',',
        annoExpr(
          qNameId(
            nameIdLower(
              terminal(IdLower_t,"origin"),
              location=top.location),
            location=top.location),
          '=',
          presentAppExpr(
            w.wrapper,
            location=top.location),
          location=top.location),
        location=top.location),
      ')',
      location=top.location);
}

concrete production oApplicationAnno
top::Expr ::= e::Expr '@(' anns::AnnoAppExprs ',' 'origins' ':' w::Wrapper ')'
{
  forwards to
    oApplication(e, $2, emptyAppExprs(location=$2.location), $4, anns, $4,
                 $5, $6, w, $8, location=top.location);
}

concrete production oApplicationExpr
top::Expr ::= e::Expr '@(' es::AppExprs ',' 'origins' ':' w::Wrapper ')'
{
  forwards to
    oApplication(e, $2, es, $4, emptyAnnoAppExprs(location=$4.location),
                 $4, $5, $6, w, $8, location=top.location);
}

concrete production oApplicationEmpty
top::Expr ::= e::Expr '@(' 'origins' ':' w::Wrapper ')'
{
  forwards to
    oApplication(e, $2, emptyAppExprs(location=$2.location), ',',
                 emptyAnnoAppExprs(location=$2.location), ',', $3, $4, w,
                 $6, location=top.location);
}