grammar silver:compiler:extension:scopegraphs2;


-- Scope graph identified by the edge labels within it
-- todo: check qns in graphSpec is a list of names for which each has an `edge ...` agdecl

production graphSpec
top::AGDcl ::= ident::String qns::LabelNames
{
  top.scopeGraphDefs := [scopeGraphDef(defaultEnvItem(graphDcl(
    top.grammarName ++ ":" ++ ident,
    qns.names
  )))];

  nondecorated local fsInhs::FlowSpecInhs = qns.fsInhs;

  nondecorated local alias::AGDcl = Silver_AGDcl {
    type $Name{name(ident)} = 
      $TypeExpr{inhSetTypeExpr(terminal(InhSetLCurly_t, "{"), fsInhs, '}')};
  };

  top.errors :=
    foldr(
      \n::String acc::[Message] ->
        let 
          lookup::[ScopeLabelDclInfo] =
            lookupLabelDcl(top.grammarName ++ ":" ++ n, top.sgEnv)
        in
          case lookup of
          | h::[] -> acc
          | _ -> errFromOrigin(top, toString(length(lookup)) ++ " edge declarations found with label '" ++ n ++ "'")::acc
          end
        end,
      [],
      qns.names
    )
  ;

  forwards to alias;
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
