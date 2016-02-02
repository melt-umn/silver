grammar monto:driver;

imports monto:abstractsyntax as abs;
imports monto:concretesyntax as cnc;

imports silver:langutil;
imports silver:langutil:pp;


function driverEval
String ::= input::String parse::(ParseResult<cnc:Root_c>::=String String)
{
 local parseResult::ParseResult<cnc:Root_c> = parse(input, "input");
 local ast::abs:Root = parseResult.parseTree.cnc:ast;
 local result::Integer = ast.abs:value;
 return if !parseResult.parseSuccess 
 	   then "Parse errors: " ++ parseResult.parseErrors
	else if !null(ast.abs:errors)
	   then "Errors occured: " ++ implode("\n", ast.abs:errors)
	else toString(result);
}
