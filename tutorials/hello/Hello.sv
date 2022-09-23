grammar hello;

function main 
IO<Integer> ::= largs::[String]
{
  return do {
    print("Hello, world!\n");
    return 0;
  };
}
