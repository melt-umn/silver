grammar silver:extension:doc:core;

nonterminal DocItem;
abstract production commentDocItem
top::DocItem ::= c::CommentItem
{}

abstract production configDocItem
top::DocItem ::= c::ConfigItem
{}
