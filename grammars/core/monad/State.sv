grammar core:monad;

nonterminal State<s a> with stateIn<s>, stateOut<s>, stateVal<a>;

inherited attribute stateIn<s>::s;
synthesized attribute stateOut<s>::s;
synthesized attribute stateVal<a>::a;

abstract production bindState
top::State<s b> ::= st::State<s a> fn::(State<s b> ::= a)
{
  st.stateIn = top.stateIn;
  local newState::State<s b> = fn(st.stateVal);
  newState.stateIn = st.stateOut;
  top.stateOut = newState.stateOut;
  
  top.stateVal = newState.stateVal;
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
top::State<s Unit> ::= newState::s
{
  top.stateOut = newState;
  top.stateVal = unit();
}

abstract production modifyState
top::State<s Unit> ::= fun::(s ::= s)
{
  top.stateOut = fun(top.stateIn);
  top.stateVal = unit();
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
  return runState(st, initialState).snd;
}