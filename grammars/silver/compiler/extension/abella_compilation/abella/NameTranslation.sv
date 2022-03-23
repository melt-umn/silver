grammar silver:compiler:extension:abella_compilation:abella;


--To make a consistent separator for names, I'm going to set it here
global name_sep::String = "_$$$$$_";


global attrValTypeName::String = "$attrVal";
global attributeExistsName::String = "$attr_ex";
global attributeNotExistsName::String = "$attr_no";

global nodeTreeName::String = "$node_tree";
global nodeTreeType::AbellaType = nameAbellaType(nodeTreeName);

global natSuccName::String = "$succ";
global natZeroName::String = "$zero";

global integerAdditionName::String = "$plus_integer";
global integerSubtractionName::String = "$minus_integer";
global integerMultiplicationName::String = "$multiply_integer";
global integerDivisionName::String = "$divide_integer";
global integerModulusName::String = "$modulus_integer";
global integerNegateName::String = "$negate_integer";
global integerLessName::String = "$less_integer";
global integerLessEqName::String = "$lesseq_integer";
global integerGreaterName::String = "$greater_integer";
global integerGreaterEqName::String = "$greatereq_integer";

global stringAbellaType::AbellaType =
       functorAbellaType(nameAbellaType("list"),
                         nameAbellaType("$char"));

global appendName::String = "$fun" ++ name_sep ++ "silver$*$core$*$append";

global pairTypeName::String = "$pair";
global pairType::AbellaType = nameAbellaType(pairTypeName);
global pairConstructorName::String = "$pair_c";

global orName::String = "$or_bool";
global andName::String = "$and_bool";
global notName::String = "$not_bool";
global trueName::String = "$btrue";
global falseName::String = "$bfalse";

global patternMatchResultVar::String = "$pattern_match_var_result";
global pmvrTy::AbellaType = nameAbellaType(patternMatchResultVar);



--Nonterminals
function nameToNonterminal
String ::= name::String
{
  return "$nt_" ++ name;
}
function nameToNonterminalType
AbellaType ::= name::String
{
  return nameAbellaType(nameToNonterminal(name));
}
function nonterminalToName
String ::= nt::String
{
  return substring(4, length(nt), nt);
}
function nonterminalTypeToName
String ::= ty::AbellaType
{
  return
     case ty of
     | nameAbellaType(nt) -> nonterminalToName(nt)
     | _ -> "Something_else(" ++ ty.unparse ++ ")" --error("Must call this on a nonterminal type (" ++ ty.unparse ++ ")")
     end;
}

function nameToNodeType
String ::= name::String
{
  return "node_" ++ name;
}
function typeToNodeType
String ::= ty::AbellaType
{
  --drop "$nt_" from beginning
  return nameToNodeType(substring(4, length(ty.unparse), ty.unparse));
}


--Decorated Trees
function treeToNodeName
String ::= treeName::String
{
  return "$Node_" ++ treeName;
}

function treeToChildListName
String ::= treeName::String
{
  return "$ChildList_" ++ treeName;
}


function nodeTreeConstructorName
String ::= treeTy::AbellaType
{
  return "$ntr_" ++ treeTy.unparse;
}


--Attribute Access
function accessRelationName
String ::= treeTy::AbellaType attrName::String
{
  return "$access_$_" ++ attrName ++ "_$_" ++ treeTy.unparse;
}
function accessToAttrName
String ::= str::String
{
  local firstSplit::Integer = indexOf("_$_", str);
  local lastSplit::Integer = lastIndexOf("_$_", str);
  return substring(firstSplit + 3, lastSplit, str);
}


--Access Theorems
function accessUniqueThm
String ::= attr::String ty::String
{
  return "$access_$_" ++ attr ++ "_$_" ++ ty ++ name_sep ++ "unique";
}
function accessIsThm
String ::= attr::String ty::String
{
  return "$access_$_" ++ attr ++ "_$_" ++ ty ++ name_sep ++ "is";
}


--Local Attributes
function localAccessRelationName
String ::= treeTy::AbellaType attrName::String prodName::String
{
  --$local_access_$_<prod>_$_<name>_$_<type>
  return "$local_access_$_" ++ nameToProd(prodName) ++ "_$_" ++
         attrName ++ "_$_" ++ treeTy.unparse;
}
function wpdNt_to_LocalAttrEq
String ::= prod::String attr::String ty::AbellaType
{
  --$wpd to <prod>_local_<attr> <ty>
  return "$wpd" ++ name_sep ++ "to" ++ name_sep ++ prod ++
         "_local_" ++ attr ++ name_sep ++ ty.unparse;
}
function localAccessUniqueThm
String ::= prod::String attr::String ty::String
{
  --$local_access_$_<$prod_prod>_$_<attr>_$_<ty> unique
  return "$local_access_$_$prod_" ++ prod ++ "_$_" ++ attr ++ "_$_" ++
         ty ++ name_sep ++ "unique";
}


--WPD Nonterminal
function wpdTypeName
String ::= treeTy::AbellaType
{
  return "$wpd_" ++ treeTy.unparse;
}
function wpdPrimaryComponent
String ::= prod::String builtTy::AbellaType
{
  return "$wpd_" ++ builtTy.unparse ++ name_sep ++ prod;
}

function wpdNodeTreeForm
String ::= ty::AbellaType
{
  return "$wpd_" ++ ty.unparse ++ name_sep ++ "ntr_" ++ ty.unparse;
}


--WPD Node
function wpdNodeTypeName
String ::= treeTy::AbellaType
{
  return "$wpd_node_" ++ treeTy.unparse;
}


--Attribute Equations
function equationName
String ::= attr::String ty::AbellaType
{
  return "$" ++ attr ++ name_sep ++ ty.unparse;
}
function inhChildEquationName
String ::= attr::String ty::AbellaType prod::String index::String
{
  return equationName(attr, ty) ++ name_sep ++ prod ++ name_sep ++
         index;
}
function inhChildEquationName_localIndex
String ::= localAttr::String
{
  return "local" ++ name_sep ++ localAttr;
}
function localEquationName
String ::= attr::String prod::String
{
  return "$" ++ prod ++ "_local_" ++ attr;
}
function equationRelType
AbellaType ::= treeTy::AbellaType
{
  return arrowAbellaType(treeTy,
         arrowAbellaType(treeTy,
         arrowAbellaType(nodeTreeType,
                         nameAbellaType("prop"))));
}
function wpdNode_to_AttrEq
String ::= attr::String ty::AbellaType
{
  return "$wpd_node" ++ name_sep ++ "to" ++ name_sep ++ attr ++
         name_sep ++ ty.unparse;
}
function wpdNt_to_AttrEq
String ::= attr::String ty::AbellaType
{
  return "$wpd" ++ name_sep ++ "to" ++ name_sep ++ attr ++ name_sep ++
         ty.unparse;
}
function primaryComponent
String ::= attr::String ty::AbellaType prod::String
{
  return "$" ++ attr ++ name_sep ++ ty.unparse ++ name_sep ++ prod;
}


--Structure Equality
function typeToStructureEqName
String ::= ty::AbellaType
{
  return "$structure_eq" ++ name_sep ++ ty.unparse;
}
function structureEqWPD
String ::= ty::AbellaType
{
  return "$structure_eq" ++ name_sep ++ ty.unparse ++ name_sep ++ "wpd";
}
function structureEqEqualTheorem
String ::= ty::AbellaType
{
  return "$structure_eq" ++ name_sep ++ ty.unparse ++ name_sep ++ "equal";
}
function typeToStructureEq_Symm
String ::= ty::AbellaType
{
  return "$structure_eq" ++ name_sep ++ ty.unparse ++ name_sep ++ "symm";
}
function structureEqProdComponent
String ::= prod::String
{
  return "$structure_eq" ++ name_sep ++ prod;
}
function structureEqExpansionTheorem
String ::= ty::AbellaType component::String
{
  return "$structure_eq" ++ name_sep ++ ty.unparse ++ name_sep ++
         component ++ name_sep ++ "expand";
}


--Pattern Matching
function typeToMatchName
String ::= ty::AbellaType
{
  return
     case ty of
     | functorAbellaType(nameAbellaType("list"), argty) ->
       typeNameToMatchName("list") ++ " " ++
          "(" ++ typeToMatchName(argty) ++ ")"
     | functorAbellaType(
          functorAbellaType(nameAbellaType(pty), aty),
          bty) when pty == pairTypeName ->
       typeNameToMatchName(pairTypeName) ++ " " ++
          "(" ++ typeToMatchName(aty) ++ ") " ++
          "(" ++ typeToMatchName(bty) ++ ")"
     | _ -> typeNameToMatchName(ty.unparse)
     end;
}
function typeNameToMatchName
String ::= ty::String
{
  return "$match" ++ name_sep ++ ty;
}
function matchRelationType
AbellaType ::= ty::String
{
  return arrowAbellaType(nameAbellaType(ty),
         arrowAbellaType(nameAbellaType(ty),
         arrowAbellaType(nodeTreeType,
         arrowAbellaType(patternType(nameAbellaType(ty)),
         arrowAbellaType(functorAbellaType(nameAbellaType("list"),
                                           pmvrTy),
                         nameAbellaType("prop"))))));
}
function patternType
AbellaType ::= ty::AbellaType
{
  return
     case ty of
     | functorAbellaType(fty, aty) ->
       functorAbellaType(patternType(fty), patternType(aty))
     | _ -> nameAbellaType(ty.unparse ++ "_$Pattern")
     end;
}
function pmvrConstructorName
String ::= ty::String
{
  return "$pmvr" ++ name_sep ++ ty;
}
function patternConstructorName
String ::= constructor::String ty::String
{
  return constructor ++ name_sep ++ patternType(nameAbellaType(ty)).unparse;
}
function varPatternName
String ::= ty::String
{
  return patternConstructorName("$mvar", ty);
}


--Productions
function nameToProd
String ::= s::String
{
  return "$prod_" ++ colonsToEncoded(s);
}
function termIsProd
Boolean ::= tm::Term
{
  return
     case tm of
     | nameTerm(str) -> startsWith("$prod_", str)
     | _ -> false
     end;
}


--Functions
function nameToFun
String ::= s::String
{
  return "$fun" ++ name_sep ++ s;
}





--Turn an integer into an Abella term
function integerToIntegerTerm
Term ::= i::Integer
{
  return if i >= 0
         then buildApplication(nameTerm("$posInt"),
                               [integerToNatTerm(i)])
         else buildApplication(nameTerm("$negSuccInt"),
                               [integerToNatTerm((i * -1) - 1)]);
}

function integerToNatTerm
Term ::= i::Integer
{
  return if i == 0
         then nameTerm(natZeroName)
         else buildApplication(nameTerm(natSuccName),
                               [integerToNatTerm(i-1)]);
}



--Turn a number into the char constructor
function stringToAbellaTerm
Term ::= contents::String
{
  local charOrdinals::[Integer] = stringToChars(contents);
  local charConstants::[String] =
        map(ordinalToCharConstructor, charOrdinals);
  local charTerms::[Term] =
        map(nameTerm(_), charConstants);
  return foldr(consTerm, nilTerm(), charTerms);
}
function ordinalToCharConstructor
String ::= ord::Integer
{
  return "$c_" ++ toString(ord);
}



--find both normal accesses and locals
function nameIsAccess
Boolean ::= rel::String
{
  return
     startsWith("$access_", rel) || startsWith("$local_access_", rel);
}



--Turn a colon-qualified name into an Abella-qualified name
function colonsToEncoded
String ::= name::String
{
  return substitute(":", "$*$", name);
}
function encodedToColons
String ::= name::String
{
  return substitute("$*$", ":", name);
}

--Get just the grammar from a fully-qualified name
--Works with either colons or encoded, but must be fully-qualified
function nameToGrammar
String ::= name::String
{
  local lastEncodedSep::Integer = lastIndexOf("$*$", name);
  local lastColon::Integer = lastIndexOf(":", name);
  return if lastColon >= 0
         then substring(0, lastColon, name)
         else substring(0, lastEncodedSep, name);
}

--Get just the short name from a possibly-fully-qualified name
--Works with either colons or encoded, but must be fully-qualified
function nameToShortName
String ::= name::String
{
  local lastEncodedSep::Integer = lastIndexOf("$*$", name);
  local lastColon::Integer = lastIndexOf(":", name);
  return if lastColon >= 0
         then substring(lastColon + 1, length(name), name)
         else if lastEncodedSep >= 0
              then substring(lastEncodedSep + 3, length(name), name)
              else name;
}

