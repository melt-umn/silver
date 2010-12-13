grammar turing;

{-
 - The concrete syntax of Turing machine definition files.
 -}

nonterminal Dcls with pp, machines, tape, startMachine, theImports;
nonterminal Dcl with pp, machines, tape, isTapeDcl, startMachine, theImports;

nonterminal FlowDcls with pp, startState, machineFlow;
nonterminal FlowDcl with pp, startState, machineFlow;

nonterminal States with pp, states, startState;
nonterminal State with pp, states, startState;

nonterminal Instructions with pp, instructions;
nonterminal Instruction with pp, instructions;

nonterminal TheAction with pp, theAction;
nonterminal Transition with pp, tapeHead, nextState;

nonterminal TapeDcl with pp, tape;

nonterminal StateOrStart with pp, isStart;
nonterminal IdOrStart with pp, isStart;

nonterminal LeftTapeList with pp, tapeList;
nonterminal RightTapeList with pp, tapeList;

nonterminal Option with pp, options;
nonterminal Options with pp, options;

synthesized attribute tape :: Decorated ATape;
synthesized attribute tapeList :: [String];
synthesized attribute isStart :: Boolean;
synthesized attribute theImports :: [String];
synthesized attribute isTapeDcl :: Boolean;

synthesized attribute pp :: String;
synthesized attribute machines :: [AMachine];
synthesized attribute machineFlow :: [AMachineFlow];
synthesized attribute startState :: String;
synthesized attribute startMachine :: String;

lexer class KEYWORD;
lexer class IDENTIFIER submits to KEYWORD;

terminal RunTerminal     /run/     lexer classes {KEYWORD};
terminal MachineTerminal /machine/ lexer classes {KEYWORD};
terminal MoveTerminal    /move/    lexer classes {KEYWORD};
terminal EndTerminal     /end/     lexer classes {KEYWORD};
terminal LeftTerminal    /left/    lexer classes {KEYWORD};
terminal RightTerminal   /right/   lexer classes {KEYWORD};
terminal PrintTerminal   /print/   lexer classes {KEYWORD};
terminal NothingTerminal /nothing/ lexer classes {KEYWORD};
terminal TapeTerminal    /tape/    lexer classes {KEYWORD};
terminal StateTerminal   /state/   lexer classes {KEYWORD};
terminal StartTerminal   /start/   lexer classes {KEYWORD};
terminal LoadTerminal    /load/    lexer classes {KEYWORD};

terminal LeftCurl    /[\{]/;
terminal RightCurl   /[\}]/;
terminal Arrow       /[\-][\ \t\n]*[\>]/;
terminal Colon       /[\:]/;
terminal SemiColon   /[\;]/;
terminal LeftSquare  /[\[]/;
terminal RightSquare /[\]]/;
terminal Comma       /[\,]/;

terminal IdTerminal /[0-9A-Za-z]+/ lexer classes{IDENTIFIER};
terminal StringTerminal /[\"]([^\"]|([\\][\"]))*[\"]/;

ignore terminal WhiteSpace /[\n\t\ ]+/;
ignore terminal LineComment /\/\/[^\r\n]*/;


concrete production dcl
top::Dcls ::= c1::Dcl
{
  top.pp = c1.pp;
  top.machines = c1.machines;
  top.theImports = c1.theImports;
  top.tape = c1.tape;
  top.startMachine = c1.startMachine;
}

concrete production dcls
top::Dcls ::= c1::Dcl c2::Dcls
{
  top.pp = c1.pp ++ "\n" ++ c2.pp;
  top.machines = c1.machines ++ c2.machines;
  top.theImports = c1.theImports ++ c2.theImports;
  top.tape = if c1.isTapeDcl then c1.tape else c2.tape;
  top.startMachine = if c1.startMachine != "" then c1.startMachine else c2.startMachine;
}

concrete production simpleMachineDcl
top::Dcl ::= c1::MachineTerminal c2::IdTerminal c3::LeftCurl c4::States c5::RightCurl
{
  top.pp = c1.lexeme ++ " " ++ c2.lexeme ++ "{\n" ++ c4.pp ++ "\n}\n";
  top.machines = [newSimpleAMachine(c2.lexeme, c4.startState, c4.states)];
  top.theImports = [];
  top.tape = decorate emptyATape() with {};
  top.startMachine = "";

  top.isTapeDcl = false;
}

concrete production state
top::States ::= c1::State
{
  top.startState = c1.startState;
  top.pp = c1.pp;
  top.states = c1.states;
}

concrete production stateList
top::States ::= c1::State c2::States
{
  top.startState = if c1.startState != "" then c1.startState else c2.startState;
  top.pp = c1.pp ++ "\n" ++ c2.pp;
  top.states = c1.states ++ c2.states;
}

concrete production stateSome
top::State ::= c1::StateOrStart c2::IdTerminal c3::LeftCurl c4::Instructions c5::RightCurl
{
  top.pp = c1.pp ++ " " ++ c2.lexeme ++ " {\n" ++ c4.pp ++ "\n}\n";
  top.startState = if c1.isStart then c2.lexeme else "";

  top.states = [newAState(c2.lexeme, c4.instructions)];
}

concrete production stateNone
top::State ::= c1::StateOrStart c2::IdTerminal c3::LeftCurl c4::RightCurl
{
  top.pp = c1.pp ++ " " ++ c2.lexeme ++ " { }\n";
  top.startState = if c1.isStart then c2.lexeme else "";

  top.states = [newAState(c2.lexeme, [])];
}

concrete production stateStart
top::StateOrStart ::= c1::StartTerminal
{
  top.pp = "start";
  top.isStart = true;
}

concrete production stateState
top::StateOrStart ::= c1::StateTerminal
{
  top.pp = "state";
  top.isStart = false;
}

concrete production noState
top::StateOrStart ::= 
{
  top.pp = "";
  top.isStart = false;
}

concrete production instruction
top::Instructions ::= c1::Instruction
{
  top.pp = c1.pp;
  top.instructions = c1.instructions;
}

concrete production instructionList
top::Instructions ::= c1::Instruction c2::Comma c3::Instructions
{
  top.pp = c1.pp ++ ", " ++ c3.pp;
  top.instructions = c1.instructions ++ c3.instructions;
}

concrete production instructionDef
top::Instruction ::= c1::Transition c2::Colon c3::TheAction
{
  top.pp = c1.pp ++ " : " ++ c3.pp;
  top.instructions = [newAInstruction(c1.tapeHead, c1.nextState, c3.theAction)];

}
concrete production transition
top::Transition ::= c1::IdTerminal c2::Arrow c3::IdTerminal
{
  top.pp = c1.lexeme ++ " -> " ++ c3.lexeme;
  top.tapeHead = c1.lexeme;
  top.nextState = c3.lexeme;
}

concrete production transitionEnd
top::Transition ::= c1::IdTerminal c2::Arrow c3::EndTerminal
{
  top.pp = c1.lexeme ++ " -> end";
  top.tapeHead = c1.lexeme;
  top.nextState = "_";
}

concrete production printTheAction
top::TheAction ::= c1::PrintTerminal c2::IdTerminal
{
  top.pp = "print " ++ c2.lexeme;
  top.theAction = printToTape(c2.lexeme);
}

concrete production moveLeftTheAction
top::TheAction ::= c1::MoveTerminal c2::LeftTerminal
{
  top.pp = "move left";
  top.theAction = moveLeft();
}

concrete production moveRightTheAction
top::TheAction ::= c1::MoveTerminal c2::RightTerminal
{
  top.pp = "move right";
  top.theAction = moveRight();
}

concrete production nothingTheAction
top::TheAction ::= c1::NothingTerminal
{
  top.pp = "nothing";
  top.theAction = doNothing();
}

concrete production tapeDcl
top::Dcl ::= c1::TapeDcl
{
  top.pp = c1.pp;
  top.machines = [];
  top.theImports = [];
  top.tape = c1.tape;
  top.startMachine = "";

  top.isTapeDcl = true;
}

concrete production tapeDcl1
top::TapeDcl ::= c1::TapeTerminal c2::LeftTapeList c3::Comma c4::LeftSquare c5::IdTerminal c6::RightSquare c7::Comma c8::RightTapeList c9::SemiColon
{
  top.pp = "tape " ++ c2.pp ++ "[" ++ c5.lexeme ++ "]" ++ c8.pp ++ ";";
  top.tape = decorate newATapeFull(c2.tapeList, c5.lexeme, c8.tapeList) with {};
}

concrete production tapeDcl2
top::TapeDcl ::= c1::TapeTerminal c3::LeftSquare c4::IdTerminal c5::RightSquare c6::Comma c7::RightTapeList c8::SemiColon
{
  top.pp = "tape [" ++ c4.lexeme ++ "]" ++ c7.pp ++ ";";
  top.tape = decorate newATapeFull([], c4.lexeme, c7.tapeList) with {};
}

concrete production tapeDcl3
top::TapeDcl ::= c1::TapeTerminal c2::LeftTapeList c3::Comma c4::LeftSquare c5::IdTerminal c6::RightSquare c7::SemiColon
{
  top.pp = "tape " ++ c2.pp ++ "[" ++ c5.lexeme ++ "];";
  top.tape = decorate newATapeFull(c2.tapeList, c5.lexeme, []) with {};
}

concrete production tapeDcl4
top::TapeDcl ::= c1::TapeTerminal c3::LeftSquare c4::IdTerminal c5::RightSquare c7::SemiColon
{
  top.pp = "tape [" ++ c4.lexeme ++ "];";
  top.tape = decorate newATape(c4.lexeme) with {};
}

concrete production leftTapeNone
top::LeftTapeList ::= c1::IdTerminal
{
  top.pp = c1.lexeme;
  top.tapeList = [c1.lexeme];
}

concrete production leftTapeSome
top::LeftTapeList ::= c1::LeftTapeList c2::Comma c3::IdTerminal
{
  top.pp = c1.pp ++ ", " ++ c3.lexeme;
  top.tapeList = cons(c3.lexeme, c1.tapeList);
}

concrete production rightTapeNone
top::RightTapeList ::= c1::IdTerminal
{
  top.pp = c1.lexeme;
  top.tapeList = [c1.lexeme];
}

concrete production rightTapeSome
top::RightTapeList ::= c1::IdTerminal c2::Comma c3::RightTapeList
{
  top.pp = c1.lexeme ++ ", " ++ c3.pp;
  top.tapeList = cons(c1.lexeme, c3.tapeList);
}

concrete production runDcl
top::Dcl ::= c1::RunTerminal c2::MachineTerminal c3::IdTerminal c4::SemiColon
{
  top.pp = "run machine " ++ c3.lexeme ++ "\n";
  top.machines = [];
  top.theImports = [];
  top.tape = decorate emptyATape() with {};
  top.startMachine = c3.lexeme;

  top.isTapeDcl = false;
}

concrete production complexMachineDcl
top::Dcl ::= c1::MachineTerminal c2::IdTerminal c3::LeftCurl c4::FlowDcls c5::RightCurl
{
  top.pp = c1.lexeme ++ " " ++ c2.lexeme ++ "{\n" ++ c4.pp ++ "\n}\n";
  top.machines = [newComplexAMachine(c2.lexeme, c4.startState, c4.machineFlow)];
  top.theImports = [];
  top.tape = decorate emptyATape() with {};
  top.startMachine = "";

  top.isTapeDcl = false;
}

concrete production flowDcl
top::FlowDcls ::= c1::FlowDcl
{
  top.pp = c1.pp;
  top.machineFlow = c1.machineFlow;
  top.startState = c1.startState;
}

concrete production flowDcls
top::FlowDcls ::= c1::FlowDcl c2::FlowDcls
{
  top.pp = c1.pp ++ "\n" ++ c2.pp;
  top.machineFlow = c1.machineFlow ++ c2.machineFlow;
  top.startState = if c1.startState != "" then c1.startState else c2.startState;
}


concrete production flowDclSome
top::FlowDcl ::= c1::StateOrStart c2::IdTerminal c3::Colon c4::IdTerminal c5::LeftCurl c6::Options c7::RightCurl
{
  top.pp = c1.pp ++ " " ++ c2.lexeme ++ " : " ++ c4.lexeme ++ " {\n" ++ c6.pp ++ "}\n";
  top.machineFlow = [newAMachineFlow(c2.lexeme, c4.lexeme, c6.options)];
  top.startState = if c1.isStart then c2.lexeme else "";
}

concrete production flowDclNone
top::FlowDcl ::= c1::StateOrStart c2::IdTerminal c3::Colon c4::IdTerminal c5::LeftCurl c6::RightCurl
{
  top.pp = c1.pp ++ " " ++ c2.lexeme ++ " : " ++ c4.lexeme ++ " { }\n";
  top.machineFlow = [newAMachineFlow(c2.lexeme, c4.lexeme, [])];
  top.startState = if c1.isStart then c2.lexeme else "";
}

--imports
concrete production option
top::Options ::= c1::Option
{
  top.pp = c1.pp;
  top.options = c1.options;
}

concrete production option2
top::Options ::= c1::Option c2::Comma c3::Options
{
  top.pp = c1.pp ++ ", " ++ c3.pp;
  top.options = c1.options ++ c3.options;
}

concrete production optionTransition
top::Option ::= c1::Transition
{
  top.pp = c1.pp ++ ";\n";
  top.options = [newAOption(c1.tapeHead, c1.nextState)];
}

concrete production machineLoad 
top::Dcl ::= c1::LoadTerminal c2::StringTerminal c3::SemiColon
{
  top.pp = "load " ++ c2.lexeme ++ " ;";
  top.machines = [];


  top.theImports = [substring(1, length(c2.lexeme)-1, c2.lexeme)];
--  top.tape = decorate emptyATape() with {};
  top.startMachine = "";

  top.isTapeDcl = false;
}


