grammar silver:compiler:extension:datalog;

import silver:util:random;

function datalogFactsFromList
DatalogRepresentable a => [Fact] ::= name::String top::String lst::[a]
{
   return map(\target::a -> factTwo(name,top,datalogID(target)),lst);
}


abstract production randTempDirectory
top::IO<String> ::= inStr::String
{
  local randGen::IO<Integer> = runRandomGen(randomRange(1, 1000));
  randGen.stateIn = top.stateIn;
  top.stateOut = randGen.stateOut;
  top.stateVal = s"/tmp/${inStr}_${toString(randGen.stateVal)}";
}

function StringToFact
Fact ::= inStr::String factName::String
{
   local listElems::[String] = filter(\re::String -> re != "", explode("\t",inStr));
   return factN(factName,listElems);
}

function fromCSVString
[Fact] ::= inCSV::String factName::String
{
  local listLines::[String] = filter(\re::String -> re != "", explode("\n",inCSV) );
  return map(\elem::String -> StringToFact(elem,factName),listLines);
}


function foldFact
[String] ::= inp::Fact accum::[String]
{
  return implode("\t",inp.params)::accum;
}

function factImplode
String ::= inList::[Fact]
{
  return implode("\n", foldr(foldFact, [], inList));
}

function runDatalog
Either<String [(String,[Fact])]> ::= factsIn::[[Fact]] factsOut::[String] datalogInput::String
{
  local result :: IO<Either<String [(String, [Fact])]>> =
    do {
      tempDir::String <- randTempDirectory("datalog");
      mkdir(tempDir);
      souffle::String <- envVar("SOUFFLE");
      writeFile(s"${tempDir}/silverRun.dl", datalogInput);
      eprintln(s"wrote the following to ${tempDir}/silverRun.dl:\n${datalogInput}\n");
      traverse_(\item::[Fact] -> writeFile(s"${tempDir}/${head(item).factHead}.facts", factImplode(item)), factsIn);
      cmd::String <- pure(s" ${souffle} -F ${tempDir}/ -D${tempDir} ${tempDir}/silverRun.dl ");
      exitCode::Integer <- system(cmd);
      -- make better error handling here, check exit code at least
      outList::Either<String [(String, [Fact])]> <-
        ifM(pure(exitCode == 0),
          bind(
            (traverseA(\item::String ->
              (bind(
                (readFile(s"${tempDir}/${item}.csv")),
                \val::String -> pure((item,fromCSVString(val, item))))),
            factsOut)),
            (\val::[(String,[Fact])] -> pure(right(val)))),
          (pure(left(s"Command: ${cmd} failed with return code ${integerToString(exitCode)}"))));
      return outList;
  };
  return unsafeEvalIO(result);
}

function mapsLookup
[Fact] ::= elemLooking::String maps::[(String, [Fact])]
{
  return mapOrElse([], \elem::(String,[Fact]) -> elem.snd, find(\elem::(String, [Fact]) -> elem.fst == elemLooking, maps));
}
