grammar core:monad;

nonterminal State<s a>;

inherited attribute stateIn::s occurs on State<s a>;
synthesized attribute stateOut::s occurs on State<s a>;
synthesized attribute stateVal::a occurs on State<s a>;

abstract production bindState
top::State<s b> ::= st::State<s a> fn::(State<s b> ::= a)
{
  st.stateIn = top.stateIn;
  forward.stateIn = st.stateOut;
  
  forwards to fn(st.stateVal);
}

abstract production returnState
top::State<s a> ::= x::a
{
  top.stateOut = top.stateIn;
  top.stateVal = x;
}

abstract production getState
top::State<s s> ::= 
{
  top.stateOut = top.stateIn;
  top.stateVal = top.stateIn;
}

abstract production setState
top::State<s a> ::= newState::s
{
  top.stateOut = s;
  top.stateVal = error("Unit value"); -- TODO?
}

function runState
Pair<s a> ::= st::State<s a> initialState::s
{
  st.stateIn = initialState;
  return pair(st.stateOut, st.stateVal);
}

function evalState
a ::= st::State<s a> initialState::s
{
  return runState(st, s).snd;
}