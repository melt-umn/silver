grammar monto:artifacts;

imports monto:concretesyntax as cnc;

import monto:driver;

import lib:monto;

type MontoConnection foreign;

{- Used to create the concrete syntax. Do not modify! -}
parser parse::cnc:Root_c {
  monto:concretesyntax;
} 

{- Main uses the run and create functions from lib:monto 
	create will create a new MontoServer object
	run will run a given server
-}

function main
IOVal<Integer> ::= args::[String] ioIn::IO
{
 return run(create(evaluate, "tcp://127.0.0.1:5001", "tcp://127.0.0.1:5002"), ioIn);
}

{- Evalutae creates a MontoProduct from a MontoMessage using the dirverEval function defined in the monto:driver.
	createProduct creates a MontoProduct object from a source, a product, a language, and the product's contents.
	getSource and getContents are invoked with a given MontoMessage to extract the source and content fields from the MontoMessage.
-}
function evaluate
[MontoProduct] ::= input::MontoMessage
{
  return createProduct(getSource(input), "DesktopCalculator", "simple-eval", driverEval(getContents(input), parse)) :: [];
}


