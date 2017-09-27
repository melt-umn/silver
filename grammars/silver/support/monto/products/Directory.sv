grammar silver:support:monto:products;

import lib:json;

abstract production directoryProduct
top::ProductValue ::= entries::[DirectoryEntry]
{
  top.json = jsonArray(map((.json), entries));
}

nonterminal DirectoryEntry with json;

abstract production directoryEntry
top::DirectoryEntry ::= name::String absolutePath::String entryType::DirectoryEntryType
{
  top.json = jsonObject(obj);
  local obj :: [Pair<String Json>] =
    [ pair("name", jsonString(name))
    , pair("absolute_path", jsonString(absolutePath))
    , pair("type", entryType.json)
    ];
}

nonterminal DirectoryEntryType with json;

abstract production directoryEntryFile
top::DirectoryEntryType ::=
{
  top.json = jsonString("file");
}

abstract production directoryEntryDirectory
top::DirectoryEntryType ::=
{
  top.json = jsonString("directory");
}

abstract production directoryEntrySymlink
top::DirectoryEntryType ::=
{
  top.json = jsonString("symlink");
}

abstract production directoryEntryOther
top::DirectoryEntryType ::=
{
  top.json = jsonString("other");
}
