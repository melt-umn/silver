nonterminal RegistrationParams with jsonValue, registrations;

synthesized attribute registrations :: [Registration];

abstract production registrationParams
top::RegistrationParams::=
  registrations::[Registration]
{
  top.registrations = registrations;
  top.jsonValue =
    addKeyValuePairToJSONObject("registrations", jsonArray(map((.jsonValue), registrations)), 
    jsonObject([]));
}

function parseRegistrationParams
Either<ResponseError RegistrationParams> ::= val::JSONValue
{
  local registrations :: Maybe<[Either<ResponseError Registration>]>
    = mapMaybe(mapJsonArray(parseRegistration, _), getValueWithKey("registrations", val));

  local err :: Maybe<ResponseError> = 
    parseRegistrationParamsError(registrations);

  return
  if err.isJust
  then left(err.fromJust)
  else right(registrationParams(allFromRight(registrations.fromJust)));
}

function parseRegistrationParamsError
Maybe<ResponseError> ::= 
  registrations::Maybe<[Either<ResponseError Registration>]>
{
  return
  if !registrations.isJust|| anyLeft(registrations.fromJust) then just(lspInvalidParams("registrations not set or invalid on RegistrationParams"))
  else nothing();
}

function registrationParamsToJson
JSONValue ::= val::RegistrationParams
{
  return val.jsonValue;
}
