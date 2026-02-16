grammar silver:compiler:extension:scopegraphs2;

-- put this in silver:langutil:scopegraphs:

nonterminal Scope<d>;

synthesized attribute datum<d>::d occurs on Scope<d>;

abstract production mkScope
top::Scope<d> ::= datum::d
{ top.datum = datum; }

-- put this in silver:langutil:scopegraphs:

data nonterminal Label<(i::InhSet) d>;

synthesized attribute label::String occurs on Label<(i::InhSet) d>;
synthesized attribute demand<(i::InhSet) d>::([Decorated Scope<d> with i] ::= Decorated Scope<d> with i) occurs on Label<(i::InhSet) d>;

production label
top::Label<(i::InhSet) d> ::=
{ top.demand = error("label.demand");
  top.label = error("label.name"); }

instance Eq Label<(i::InhSet) d> {
  eq = \left::Label<(i::InhSet) d> right::Label<(i::InhSet) d> -> left.label == right.label;
}

--

type Predicate<(i::InhSet) d> = (Boolean ::= Decorated Scope<d> with i);
type Ordering<(i::InhSet) d> = (Integer ::= Label<i d> Label<i d>);

-- Resolution

fun resolve
[Decorated Scope<d> with i] ::= p::Predicate<i d> r::Regex<i d> o::Maybe<Ordering<i d>> s::Decorated Scope<d> with i
=
  let cont::[Decorated Scope<d> with i] =
    -- labels that form a prefix of a word in L(r)
    let validLabels::[Label<i d>] = r.first in
      foldl (
        \acc::(Maybe<Label<i d>>, [Decorated Scope<d> with i]) nextLab::Label<i d> ->
          -- label followed to get the resolution in acc.2
          let prevLab::Maybe<Label<i d>> = acc.1
          in
          -- resolution found by following the label in acc.1
          let prevRes::[Decorated Scope<d> with i] = acc.2
          in
          -- make a new resolution by following edges with label nextLab
          let nextRes::[Decorated Scope<d> with i] =
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
    | _ -> if p(s) && r.nullable then s::cont else cont
    end
  end;

fun visible
[Decorated Scope<d> with i] ::= p::Predicate<i d> r::Regex<i d> o::Ordering<i d> s::Decorated Scope<d> with i
= resolve(p, r, just(o), s);

fun reachable
[Decorated Scope<d> with i] ::= p::Predicate<i d> r::Regex<i d> s::Decorated Scope<d> with i 
= resolve(p, r, nothing(), s);

-- Regex

nonterminal Regex<(i::InhSet) d>;

-- Transform a Regex to an equivalent fully simplified one
synthesized attribute simplify<(i::InhSet) d>::Regex<(i::InhSet) d> occurs on Regex<(i::InhSet) d>;
-- Theorem 3.1 of Brzozowski (1964). Derivative with respect to a single token
synthesized attribute deriv<(i::InhSet) d>::(Regex<i d> ::= Label<i d>) occurs on Regex<(i::InhSet) d>;
-- Definition 3.2 of Brzozowski (1964), return epsilon if Regex contains epsilon
synthesized attribute hasEps<(i::InhSet) d>::Regex<i d>;
attribute hasEps<i d> occurs on Regex<(i::InhSet) d>;
-- True if epsilon is a valid string in the language of the Regex
synthesized attribute nullable::Boolean occurs on Regex<(i::InhSet) d>;
-- Compute first set of a Regex
synthesized attribute first<(i::InhSet) d>::[Label<i d>] occurs on Regex<(i::InhSet) d>;

production regexLabel
top::Regex<(i::InhSet) d> ::= label::Label<(i::InhSet) d>
{
  top.hasEps = regexEmpty();
  top.deriv = \l::Label<i d> -> if l.label == label.label 
                                then regexEpsilon() else regexEmpty();
  top.simplify = ^top;
  top.nullable = false;
  top.first = [label];
}

production regexEpsilon
top::Regex<(i::InhSet) d> ::=
{
  top.hasEps = regexEpsilon();
  top.deriv = \_ -> regexEmpty();
  top.simplify = ^top;
  top.nullable = true;
  top.first = [];
}

production regexEmpty
top::Regex<(i::InhSet) d> ::=
{
  top.hasEps = regexEmpty();
  top.deriv = \_ -> regexEmpty();
  top.simplify = ^top;
  top.nullable = false;
  top.first = [];
}

production regexCat
top::Regex<(i::InhSet) d> ::= left::Regex<i d> right::Regex<i d>
{
  top.hasEps = regexAnd(left.hasEps, right.hasEps);
  top.deriv = \l -> regexOr(regexCat(left.deriv(l), ^right),
                            regexCat(left.hasEps, right.deriv(l)));
  top.simplify = 
    let simpR1::Regex<i d> = left.simplify in
    let simpR2::Regex<i d> = right.simplify in
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
  top.first = if !left.nullable then left.first else union(left.first, right.first); 
}

production regexOr
top::Regex<(i::InhSet) d> ::= left::Regex<i d> right::Regex<i d>
{
  top.hasEps = regexOr(left.hasEps, right.hasEps);
  top.deriv = \l -> regexOr(left.deriv(l), right.deriv(l));
  top.simplify =
    let simpR1::Regex<i d> = left.simplify in
    let simpR2::Regex<i d> = right.simplify in
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
top::Regex<(i::InhSet) d> ::= left::Regex<i d> right::Regex<i d>
{
  top.hasEps = regexAnd(left.hasEps, right.hasEps);
  top.deriv = \l -> regexAnd(left.deriv(l), right.deriv(l));
  top.simplify =
    let simpR1::Regex<i d> = left.simplify in
    let simpR2::Regex<i d> = right.simplify in
      case (simpR1, simpR2) of
      | (regexEmpty(), _) -> regexEmpty()
      | (_ , regexEmpty()) -> regexEmpty()
      | (regexEpsilon(), sub) -> if sub.nullable then regexEpsilon() else regexEmpty()
      | (sub, regexEpsilon()) -> if sub.nullable then regexEpsilon() else regexEmpty()
      | (_, _) -> regexAnd(simpR1, simpR2)
      end
    end end;
  top.nullable = left.nullable && right.nullable;
  top.first = intersect(left.first, right.first);
}

production regexStar
top::Regex<(i::InhSet) d> ::= sub::Regex<i d>
{
  top.hasEps = regexEpsilon();
  top.deriv = \l -> regexCat(sub.deriv(l), regexStar(^sub));
  top.simplify =
    let simpR::Regex<i d> = sub.simplify in 
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
top::Regex<(i::InhSet) d> ::= sub::Regex<i d>
{ forwards to regexCat(^sub, regexStar(^sub)); }

production regexMaybe
top::Regex<(i::InhSet) d> ::= sub::Regex<i d>
{ forwards to regexOr(regexEpsilon(), ^sub); }
