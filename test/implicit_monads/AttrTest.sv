grammar implicit_monads;


implicit synthesized attribute test1::Either<String Integer>;
implicit inherited attribute test2::Either<String Integer>;


wrongCode "must have a monadic type" {
  implicit synthesized attribute test3::Integer;
}
wrongCode "must have a monadic type" {
  implicit inherited attribute test4::Integer;
}

