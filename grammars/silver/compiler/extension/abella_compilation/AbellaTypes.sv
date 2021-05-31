grammar silver:compiler:extension:abella_compilation;


--The final type after all the arrows
synthesized attribute resultType::AbellaType;
--Get the functor building a type, or just its name
synthesized attribute headTypeName::Maybe<String>;
--The types before the final arrow
synthesized attribute argumentTypes::[AbellaType];
--The is relation for a given type
synthesized attribute isRelation::String;

--Replace a variable with a type
inherited attribute replaceVar::TyVar;
inherited attribute replaceTy::AbellaType;
synthesized attribute replacedTy::AbellaType;


nonterminal AbellaType with
   unparse, isAtomic,
   resultType, headTypeName, argumentTypes,
   isRelation,
   replaceVar, replaceTy, replacedTy;

abstract production arrowAbellaType
top::AbellaType ::= ty1::AbellaType ty2::AbellaType
{
  top.unparse =
      ( if ty1.isAtomic
        then ty1.unparse
        else "(" ++ ty1.unparse ++ ")" ) ++
      " -> " ++ ty2.unparse;
  top.isAtomic = false;

  top.resultType = ty2.resultType;
  top.headTypeName = ty2.headTypeName;
  top.argumentTypes = ty1::ty2.argumentTypes;

  top.isRelation = error("Cannot generate is relation for arrow type");

  ty1.replaceVar = top.replaceVar;
  ty2.replaceVar = top.replaceVar;
  ty1.replaceTy = top.replaceTy;
  ty2.replaceTy = top.replaceTy;
  top.replacedTy = arrowAbellaType(ty1.replacedTy, ty2.replacedTy);
}

abstract production nameAbellaType
top::AbellaType ::= name::String
{
  top.unparse = name;
  top.isAtomic = true;

  top.resultType = top;
  top.headTypeName = just(name);
  top.argumentTypes = [];

  top.isRelation =
      case name of
      | "list" -> "is_list"
      | "$pair" -> "is_pair"
      | "integer" -> "is_integer"
      | "bool" -> "is_bool"
      | _ -> "is_something_else(" ++ name ++ ")" --error("Cannot generate is relation for type " ++ name)
      end;

  top.replacedTy = top;
}

abstract production functorAbellaType
top::AbellaType ::= functorTy::AbellaType argTy::AbellaType
{
  top.unparse =
      functorTy.unparse ++ " " ++
      if argTy.isAtomic
      then argTy.unparse
      else "(" ++ argTy.unparse ++ ")";
  top.isAtomic = false;

  top.resultType = top;
  top.headTypeName = functorTy.headTypeName;
  top.argumentTypes = [];

  top.isRelation =
      case functorTy, argTy of
      | nameAbellaType("list"), nameAbellaType("$char") -> "is_string"
      | functorAbellaType(nameAbellaType("$pair"), nt), node
        when tyIsNonterminal(nt) ->
        "$split " ++ wpdTypeName(nt)
      | _, _ -> functorTy.isRelation ++ " (" ++ argTy.isRelation ++ ")"
      end;

  functorTy.replaceVar = top.replaceVar;
  argTy.replaceVar = top.replaceVar;
  functorTy.replaceTy = top.replaceTy;
  argTy.replaceTy = top.replaceTy;
  top.replacedTy = functorAbellaType(functorTy.replacedTy, argTy.replacedTy);
}

abstract production varAbellaType
top::AbellaType ::= var::TyVar
{
  top.unparse =
      case var of
      | tyVar(k, i) -> toString(i)
      end;
  top.isAtomic = true;

  top.resultType = top;
  top.headTypeName = just(top.unparse);
  top.argumentTypes = [];

  top.isRelation = error("Cannot generate is relation for var type");

  top.replacedTy =
      if var == top.replaceVar
      then top.replaceTy
      else top;
}



function replaceVars
AbellaType ::= vars::[TyVar] replaceTys::[AbellaType] original::AbellaType
{
  return
     case vars, replaceTys of
     | v::vrest, t::tysrest ->
       replaceVars(vrest, tysrest,
                   decorate original with
                      {replaceVar = v; replaceTy = t;}.replacedTy)
     | _, _ -> original
     end;
}



function tysEqual
Boolean ::= ty1::AbellaType ty2::AbellaType
{
  return
     case ty1, ty2 of
     | arrowAbellaType(t11, t12), arrowAbellaType(t21, t22) ->
       tysEqual(t11, t21) && tysEqual(t12, t22)
     | nameAbellaType(n1), nameAbellaType(n2) -> n1 == n2
     | functorAbellaType(f1, a1), functorAbellaType(f2, a2) ->
       tysEqual(f1, f2) && tysEqual(a1, a2)
     | _, _ -> false
     end;
}


function tyIsNonterminal
Boolean ::= ty::AbellaType
{
  return
     case ty of
     | nameAbellaType(name) when startsWith("nt_", name) -> true
     | _ -> false
     end;
}

