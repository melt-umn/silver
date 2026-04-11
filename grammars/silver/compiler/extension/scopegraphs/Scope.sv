grammar silver:compiler:extension:scopegraphs;

--

synthesized attribute datum::Datum;

--

nonterminal Scope with datum;

abstract production scope
top::Scope ::= d::Datum
{ top.datum = ^d; }

type DecScope<(i::InhSet)> = Decorated Scope with i;

--

closed nonterminal Datum;

production datumDefault
top::Datum ::=
{}

--

closed nonterminal Label<(i::InhSet)> with name, demand<i>;

synthesized attribute name::String;
synthesized attribute demand<(i::InhSet)>::([DecScope<i>] ::= DecScope<i>);

instance Eq Label<(i::InhSet)> {
  eq = \left::Label<(i::InhSet)> right::Label<(i::InhSet)> -> 
    left.name == right.name;
}


--------
-- Regex

nonterminal Regex<(i::InhSet)>;

-- Transform a Regex to an equivalent fully simplified one
synthesized attribute simplify<(i::InhSet)>::Regex<(i::InhSet)>
  occurs on Regex<(i::InhSet)>;
-- Theorem 3.1 of Brzozowski (1964). Derivative with respect to a single token
synthesized attribute deriv<(i::InhSet)>::(Regex<i> ::= Label<i>)
  occurs on Regex<(i::InhSet)>;
-- Definition 3.2 of Brzozowski (1964), return epsilon if Regex contains epsilon
synthesized attribute hasEps<(i::InhSet)>::Regex<i>
  occurs on Regex<(i::InhSet)>;
-- True if epsilon is a valid string in the language of the Regex
synthesized attribute nullable::Boolean
  occurs on Regex<(i::InhSet)>;
-- Compute first set of a Regex
synthesized attribute first<(i::InhSet)>::[Label<i>]
  occurs on Regex<(i::InhSet)>;

production regexLabel
top::Regex<(i::InhSet)> ::= label::Label<(i::InhSet)>
{
  top.hasEps = regexEmpty();
  top.deriv = \l::Label<i> -> if l.name == label.name 
                              then regexEpsilon() else regexEmpty();
  top.simplify = ^top;
  top.nullable = false;
  top.first = [^label];
}

production regexEpsilon
top::Regex<(i::InhSet)> ::=
{
  top.hasEps = regexEpsilon();
  top.deriv = \_ -> regexEmpty();
  top.simplify = ^top;
  top.nullable = true;
  top.first = [];
}

production regexEmpty
top::Regex<(i::InhSet)> ::=
{
  top.hasEps = regexEmpty();
  top.deriv = \_ -> regexEmpty();
  top.simplify = ^top;
  top.nullable = false;
  top.first = [];
}

production regexCat
top::Regex<(i::InhSet)> ::= left::Regex<i> right::Regex<i>
{
  top.hasEps = regexAnd(left.hasEps, right.hasEps);
  top.deriv = \l -> regexOr(regexCat(left.deriv(l), ^right),
                            regexCat(left.hasEps, right.deriv(l)));
  top.simplify = 
    let simpR1::Regex<i> = left.simplify in
    let simpR2::Regex<i> = right.simplify in
      case (simpR1, simpR2) of
      | (regexEmpty(), _) -> regexEmpty()
      | (_, regexEmpty()) -> regexEmpty()
      | (regexEpsilon(), regexEpsilon()) -> regexEpsilon()
      | (regexEpsilon(), _) -> simpR2
      | (_, regexEpsilon()) -> simpR1
      | (_, _) -> regexCat(simpR1, simpR2)
      end
    end end;
  top.nullable = left.nullable && right.nullable;
  top.first = if !left.nullable then left.first
                                else union(left.first, right.first); 
}

production regexOr
top::Regex<(i::InhSet)> ::= left::Regex<i> right::Regex<i>
{
  top.hasEps = regexOr(left.hasEps, right.hasEps);
  top.deriv = \l -> regexOr(left.deriv(l), right.deriv(l));
  top.simplify =
    let simpR1::Regex<i> = left.simplify in
    let simpR2::Regex<i> = right.simplify in
      case (simpR1, simpR2) of
      | (regexEmpty(), _) -> simpR2
      | (_, regexEmpty()) -> simpR1
      | (regexEpsilon(), regexEpsilon()) -> regexEpsilon()
      | (regexEpsilon(), _) -> regexOr(regexEpsilon(), simpR2)
      | (_, regexEpsilon()) -> regexOr(simpR1, regexEpsilon())
      | (_, _) -> regexOr(simpR1, simpR2)
      end
    end end;
  top.nullable = left.nullable || right.nullable;
  top.first = union(left.first, right.first);
}

production regexAnd
top::Regex<(i::InhSet)> ::= left::Regex<i> right::Regex<i>
{
  top.hasEps = regexAnd(left.hasEps, right.hasEps);
  top.deriv = \l -> regexAnd(left.deriv(l), right.deriv(l));
  top.simplify =
    let simpR1::Regex<i> = left.simplify in
    let simpR2::Regex<i> = right.simplify in
      case (simpR1, simpR2) of
      | (regexEmpty(), _) -> regexEmpty()
      | (_ , regexEmpty()) -> regexEmpty()
      | (regexEpsilon(), sub) -> if sub.nullable then regexEpsilon()
                                                 else regexEmpty()
      | (sub, regexEpsilon()) -> if sub.nullable then regexEpsilon()
                                                 else regexEmpty()
      | (_, _) -> regexAnd(simpR1, simpR2)
      end
    end end;
  top.nullable = left.nullable && right.nullable;
  top.first = intersect(left.first, right.first);
}

production regexStar
top::Regex<(i::InhSet)> ::= sub::Regex<i>
{
  top.hasEps = regexEpsilon();
  top.deriv = \l -> regexCat(sub.deriv(l), regexStar(^sub));
  top.simplify =
    let simpR::Regex<i> = sub.simplify in 
      case simpR of
      | regexEmpty() -> regexEmpty()
      | regexEpsilon() -> regexEpsilon()
      | _ -> regexStar(simpR)
      end
    end;
  top.nullable = true;
  top.first = sub.first;
}

production regexPlus
top::Regex<(i::InhSet)> ::= sub::Regex<i>
{ forwards to regexCat(^sub, regexStar(^sub)); }

production regexMaybe
top::Regex<(i::InhSet)> ::= sub::Regex<i>
{ forwards to regexOr(regexEpsilon(), ^sub); }

-------------
-- Resolution

type Predicate = (Boolean ::= Datum);
type Ordering<(i::InhSet)> = (Integer ::= Label<i> Label<i>);

fun resolve
[DecScope<i>] ::= p::Predicate r::Regex<i> o::Maybe<Ordering<i>> s::DecScope<i>
=
  let cont::[DecScope<i>] =
    -- labels that form a prefix of a word in L(r)
    let validLabels::[Label<i>] = r.first in
      foldl (
        \acc::(Maybe<Label<i>>, [DecScope<i>]) nextLab::Label<i> ->
          -- label followed to get the resolution in acc.2
          let prevLab::Maybe<Label<i>> = acc.1
          in
          -- resolution found by following the label in acc.1
          let prevRes::[DecScope<i>] = acc.2
          in
          -- make a new resolution by following edges with label nextLab
          let nextRes::[DecScope<i>] =
            concat(map(resolve(p, r.deriv(nextLab), o, _),
                       nextLab.demand(s)))
          in
          -- use function o to compare nextLab with the previous
          -- if -1, resolutions found by following edges of nextLab shadow previous resolutions
          -- if  0, combine results of following edges of label nextLab with previous results
          -- if  1, resolutions found by following edges of prevLab shadow those by following nextLab
          let compare::Integer = o.fromJust(nextLab, prevLab.fromJust)
          in
            -- if there is an ordering, a previous resolution, comparison is not 0, and new resolution is nonempty
            if o.isJust && prevLab.isJust && compare != 0 && !null(nextRes)
            then  -- visibility
              if compare < 0
              then (just(nextLab), nextRes) -- nextLab < prevLab
              else (prevLab, prevRes)       -- nextLab > nextLab
            else  -- reachability
              (just(nextLab), prevRes ++ nextRes)
          end end end end,
        (nothing(), []),
        validLabels
      ).2
    end
  in
    case r.simplify of
    | regexEmpty() -> []
    | _ -> if p(s.datum) && r.nullable then s::cont else cont
    end
  end;

fun visibleQuery
[DecScope<i>] ::= r::Regex<i> o::Ordering<i> p::Predicate s::DecScope<i>
= resolve(p, r, just(o), s);

fun reachableQuery
[DecScope<i>] ::= r::Regex<i> p::Predicate s::DecScope<i> 
= resolve(p, r, nothing(), s);
