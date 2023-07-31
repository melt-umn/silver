grammar silver:langutil;


production extensionGenerated
top::OriginNote ::= string::String
{
  
}

function originatesInExt
Maybe<String> ::= chain::[OriginInfo]
{
  return case chain of
         | [] -> nothing()
         | link::rest -> 
             orElse(findExtensionGeneratedNote(link.originNotes),
              originatesInExt(rest))
         end;
}

function findExtensionGeneratedNote
Maybe<String> ::= notes::[OriginNote]
{
  return case notes of
         | [] -> nothing()
         | extensionGenerated(l)::_ -> just(l)
         | x::r -> findExtensionGeneratedNote(r)
         end;
}
