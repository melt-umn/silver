grammar core;

terminal test /abc/;

inherited attribute ioIn :: IO;
synthesized attribute ioOut :: IO;

nonterminal Main;
attribute ioIn occurs on Main;
attribute ioOut occurs on Main;