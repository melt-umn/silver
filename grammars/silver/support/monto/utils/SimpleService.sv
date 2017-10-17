grammar silver:support:monto:utils;

import silver:support:monto;
import silver:support:monto:negotiation;

function simpleService
Service ::= version::SoftwareVersion providers::[ServiceProvider]
{
  local sn :: ServiceNegotiation = serviceNegotiation(
    protocolVersion(3, 0, 0),
    version,
    [],
    map((.descriptor), providers));
  return service(sn, providers);
}
