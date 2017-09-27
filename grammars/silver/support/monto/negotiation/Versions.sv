grammar silver:support:monto:negotiation;

import lib:json;

nonterminal ProtocolVersion with json;

abstract production protocolVersion
top::ProtocolVersion ::= major::Integer minor::Integer patch::Integer
{
  top.json = jsonObject(
    [ pair("major", jsonInteger(major))
    , pair("minor", jsonInteger(minor))
    , pair("patch", jsonInteger(patch))
    ]);
}

nonterminal SoftwareVersion with json;

abstract production softwareVersion
top::SoftwareVersion ::= id::String name::Maybe<String> vendor::Maybe<String> major::Maybe<Integer> minor::Maybe<Integer> patch::Maybe<Integer>
{
  local opts :: [Pair<String Json>] = catMaybes(
    [ pairMaybe("name", jsonString, name)
    , pairMaybe("vendor", jsonString, vendor)
    , pairMaybe("major", jsonInteger, major)
    , pairMaybe("minor", jsonInteger, minor)
    , pairMaybe("patch", jsonInteger, patch)
    ]);
  top.json = jsonObject(pair("id", jsonString(id)) :: opts);
}

function pairMaybe
Maybe<Pair<a c>> ::= a::a f::(c ::= b) b::Maybe<b>
{
  return case b of
  | just(x) -> just(pair(a, f(x)))
  | nothing() -> nothing()
  end;
}
