grammar silver:compiler:definition:flow:env;

import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax only BracketedOptTypeExprs;
import silver:compiler:driver:util only isStrictlyExportedBy;

aspect production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  -- Normally the flow analysis consider options to be the same as exports.
  -- Here, to avoid creating a hard dependency on options, we ignore options when
  -- deciding the include things in the *inferred* ref set. (Thus, isStrictlyExportedBy.)
  local inferredInhs :: [String] =
    getInhAttrsOnForReferences(fName, top.env, isStrictlyExportedBy(_, [top.grammarName], top.compiledGrammars));
  
  local specInhs :: Maybe<[String]> =
    map(fst, lookup("decorate", getFlowTypeSpecFor(fName, top.flowEnv)));

  -- Notice the circularity: refDefs uses flowEnv. Works fine because only
  -- the (lazy) parameter of pair isn't computable until later.

  top.refDefs <- [(fName, fromMaybe(inferredInhs, specInhs))];
}

-- If it is inherited and exported by this grammar (according to authority)
-- Also includes inherited on translation attributes
function getInhAttrsOnForReferences
[String] ::= nt::String  e::Decorated Env  authority::(Boolean ::= String)
{
  local ntty::Type =
    case getTypeDcl(nt, e) of
    | ty :: _ -> ty.typeScheme.monoType
    | [] -> errorType()
    end;
  return flatMap(\ occ::OccursDclInfo ->
    case getAttrDcl(occ.attrOccurring, e) of
    | at :: _ when authority(occ.sourceGrammar) ->
        if at.isInherited
        then [occ.attrOccurring]
        else if at.isSynthesized
        then flatMap(\ occ2::OccursDclInfo ->
          case getAttrDcl(occ2.attrOccurring, e) of
          | at2 :: _ when authority(occ2.sourceGrammar) && at2.isInherited ->
            [s"${occ.attrOccurring}.${occ2.attrOccurring}"]
          | _ -> []
          end,
          getAttrOccursOn(determineAttributeType(occ, ntty).typeName, e))
        else []
    | _ -> []
    end,
    getAttrOccursOn(nt, e)); 
}
