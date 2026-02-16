grammar silver:compiler:extension:scopegraphs2;

--

production graphSpec
top::AGDcl ::= ident::String qns::LabelNames
{
  -- 'graph definition' consisting of an identifier and constituent labels
  nondecorated local sgDef::ScopeGraphDclInfo = graphDcl(ident, labsTypesMsgs.1);
  
  -- alias of the set of inherited attributes corresponding to graph label set
  nondecorated local alias::AGDcl = Silver_AGDcl {
    type $Name{name(ident)} =
      $TypeExpr{inhSetTypeExpr(terminal(InhSetLCurly_t, "{"), qns.fsInhs, '}')};
  };

  -- names/types of labels referred to in `qns`, error messages for label
  -- references mapping to non-singleton resolutions
  local labsTypesMsgs::([(String, Maybe<TypeExpr>)], [Message]) =
    foldr(
      \lab::String acc::([(String, Maybe<TypeExpr>)], [Message]) ->
        let lookup::[ScopeLabelDclInfo] = lookupLabelDcl(lab, top.sgEnv) in
          case lookup of
          | [h] -> ((h.fullName, h.labelDclTypeExpr)::acc.1, acc.2)
          | _   -> (acc.1, errFromOrigin(top, toString(length(lookup)) ++ 
                                         " edge declarations with label '" ++
                                         lab ++ "'")::acc.2)
          end
        end,
      ([], []),
      qns.names
    );

  forwards to appendAGDcl(
    alias, 
    mkLabelInhs(sgDef.combinedTe, qns.names, ident)
  );

  top.scopeGraphDefs := [scopeGraphDef(defaultEnvItem(sgDef))];
  top.errors := labsTypesMsgs.2;
}

--

synthesized attribute names::[String] occurs on LabelNames;
synthesized attribute fsInhs::FlowSpecInhs occurs on LabelNames;

production labelNamesOne
top::LabelNames ::= lab::String
{
  top.names = [lab];
  top.fsInhs = oneFlowSpecInhs(flowSpecInh(qNameAttrOccur(qName(lab))));
}

production labelNamesCons
top::LabelNames ::= lab::String ns::LabelNames
{
  top.names = lab::ns.names;
  top.fsInhs = consFlowSpecInhs(
    flowSpecInh(qNameAttrOccur(qName(lab))),
    ',',
    ns.fsInhs
  );
}

--

fun genDatumPattern Pattern ::= pos::Integer =
  if pos <= 1
  then Silver_Pattern{ left(d) }
  else Silver_Pattern{ right($Pattern{ genDatumPattern(pos-1) }) }
;