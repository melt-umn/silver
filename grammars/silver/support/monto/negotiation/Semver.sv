grammar silver:support:monto:negotiation;

function negotiationsCompatible
Boolean ::= us::ServiceNegotiation them::ServiceBrokerNegotiation
{
  return semverCompatible(us.montoVersion, them.montoVersion);
}

function semverCompatible
Boolean ::= us::ProtocolVersion them::ProtocolVersion
{
  -- TODO Do this more correctly...
  return us.major == them.major;
}
