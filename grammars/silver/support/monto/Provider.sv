grammar silver:support:monto;

import lib:json;
import silver:support:monto:products;

synthesized attribute descriptor :: ProductDescriptor;
synthesized attribute processService :: (Pair<Either<[ServiceError] Product> [ServiceNotice]> ::= String [Product]);
nonterminal ServiceProvider with descriptor, processService;

abstract production serviceProvider
top::ServiceProvider ::= descriptor::ProductDescriptor func::(Pair<Either<[ServiceError] Product> [ServiceNotice]> ::= String [Product])
{
  top.descriptor = descriptor;
  top.processService = func;
}
