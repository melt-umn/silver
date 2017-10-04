grammar silver:support:monto:negotiation;

import lib:json;

synthesized attribute major :: Integer;
synthesized attribute minor :: Integer;
synthesized attribute patch :: Integer;

nonterminal ProtocolVersion with major, minor, patch, json;

abstract production protocolVersion
top::ProtocolVersion ::= major::Integer minor::Integer patch::Integer
{
  top.major = major;
  top.minor = minor;
  top.patch = patch;
  top.json = jsonObject(
    [ pair("major", jsonInteger(top.major))
    , pair("minor", jsonInteger(top.minor))
    , pair("patch", jsonInteger(top.patch))
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
