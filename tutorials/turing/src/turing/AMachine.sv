grammar turing;

{- Data structures for storing machine definitions
 -
 -}

nonterminal AMachineFlow with stateName, machineName, options;
nonterminal AMachine with states, machineName, startState, isSimpleMachine, machineFlow;
nonterminal AState with stateName, instructions;
nonterminal AInstruction with tapeHead, nextState, theAction;
nonterminal AOption with tapeHead, nextState;

synthesized attribute isSimpleMachine :: Boolean;
synthesized attribute options :: [AOption];
synthesized attribute states :: [AState];
synthesized attribute stateName :: String;
synthesized attribute machineName :: String;
synthesized attribute instructions :: [AInstruction];
synthesized attribute nextState :: String;
synthesized attribute theAction :: AAction;


abstract production newAMachineFlow
top::AMachineFlow ::= sn::String mn::String o::[AOption]
{
  top.stateName = sn;
  top.machineName = mn;
  top.options = o;
}

abstract production newSimpleAMachine
top::AMachine ::= str::String ss::String s::[AState]
{
  top.machineName = str;
  top.startState = ss;
  top.states = s;
  top.machineFlow = error("SIMPLE MACHINE");
  top.isSimpleMachine = true;
}

abstract production newComplexAMachine
top::AMachine ::= str::String ss::String mf::[AMachineFlow]
{
  top.machineName = str;
  top.startState = ss;
  top.states = error("COMPLEX MACHINE");
  top.machineFlow = mf;
  top.isSimpleMachine = false;
}

abstract production newAState
top::AState ::= str::String i::[AInstruction]
{
  top.stateName = str;
  top.instructions = i;
}

abstract production newAInstruction
top::AInstruction ::= h::String n::String a::AAction
{
  top.tapeHead = h;
  top.nextState = n;
  top.theAction = a;
}

abstract production newAOption
top::AOption ::= h::String n::String
{
  top.tapeHead = h;
  top.nextState = n;
}

function runMachine
Decorated ATape ::= theMachine::AMachine ms::[AMachine] t::Decorated ATape
{
  return if theMachine.isSimpleMachine 
  	 then runSimpleMachine(theMachine, theMachine.startState, t)
	 else runComplexMachine(theMachine.startState, theMachine.machineFlow, ms, t);
}

function runComplexMachine
Decorated ATape ::= str::String mfs::[AMachineFlow] ms::[AMachine] t::Decorated ATape
{
  --get the part of the machine we are going to run
  local attribute theMachineFlow :: [AMachineFlow];
  theMachineFlow = findAMachineFlow(str, mfs);

  --get the machine that needs to be run for this state
  local attribute theMachine :: [AMachine];
  theMachine = if null(theMachineFlow)
	       then [] 
	       else findAMachine(head(theMachineFlow).machineName, ms);

  --if there was no machine to run then return the current tape
  local attribute theTape :: Decorated ATape;
  theTape = if null(theMachine)
	    then t
	    else runMachine(head(theMachine), ms, t); 

  --get the next state for our machine flow
  local attribute theOption :: [AOption];
  theOption = if null(theMachineFlow)
	      then []
	      else findAOption(theTape.tapeHead, head(theMachineFlow).options);

  -- if the next state option does not exists just return the current tape, otherwise run on the new state.
  return if null(theOption)
	 then theTape
	 else runComplexMachine(head(theOption).nextState, mfs, ms, theTape);
}

function runSimpleMachine
Decorated ATape ::= m::AMachine str::String t::Decorated ATape
{
  local attribute theState :: [AState];
  theState = findAState(str, m.states);
 
  local attribute theInstruction :: [AInstruction];
  theInstruction = if null(theState) 
		   then []
		   else findAInstruction(t.tapeHead, head(theState).instructions);

  local attribute theTheAction :: AAction;
  theTheAction = head(theInstruction).theAction;
  theTheAction.oldTape = t;

  return if null(theInstruction)
	 then t
	 else runSimpleMachine(m, head(theInstruction).nextState, theTheAction.newTape);
}



function findAInstruction
[AInstruction] ::= str::String s::[AInstruction]
{
  return if null(s) then []
	 else if head(s).tapeHead == str 
	      then [head(s)]
	      else findAInstruction(str, tail(s));
}

function findAMachineFlow
[AMachineFlow] ::= str::String s::[AMachineFlow]
{
  return if null(s) then []
	 else if head(s).stateName == str 
	      then [head(s)]
	      else findAMachineFlow(str, tail(s));
}

function findAMachine
[AMachine] ::= str::String s::[AMachine]
{
  return if null(s) then []
	 else if head(s).machineName == str 
	      then [head(s)] 
	      else findAMachine(str, tail(s));
}

function findAOption
[AOption] ::= str::String s::[AOption]
{
  return if null(s) then [] 
	 else if head(s).tapeHead == str 
	      then [head(s)]
	      else findAOption(str, tail(s));
}

function findAState
[AState] ::= str::String s::[AState]
{
  return if null(s) then []
	 else if head(s).stateName == str 
	      then [head(s)]
	      else findAState(str, tail(s));
}

