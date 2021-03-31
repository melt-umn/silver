This file implements the helpers for literate programming in Silver, and serves as a test-case for it as well.

## Pre-processing

The transformFile function here implements whatever preprocessing needs to be done on a certain file.

```silver
function transformFile
String ::= path::String  contents::String
{
  return
```

If the file is a normal Silver file, we return the contents unchanged.

```silver
    if endsWith(".sv", path)
    then contents
```

If it's a Literate Silver file instead, we extract the relevant code blocks, then join them with newlines.

```silver
    else if endsWith(".sv.md", path)
    then implode("\n", extractSilverCodeBlocks(contents))
```

Since these are the only two extensions allowed by `isValidSilverFile` in `CompileGrammar.sv`, they're the only two we need to handle.

```silver
    else error("Unknown filetype for " ++ path);
}
```

## Literate Silver

To get the Silver code blocks, we check that the info string is *exactly* `silver`.
The CommonMark spec notes that commonly only the first word is used as the language; in the future, we may want to support info strings like `silver test`, `silver wrongCode`, etc.
See [rustdoc](https://doc.rust-lang.org/rustdoc/documentation-tests.html#attributes) for prior art.

```silver
function extractSilverCodeBlocks
[String] ::= markdown::String
{
  return map(\block::(String, Integer, String) -> "#line " ++ toString(block.2 + 1) ++ "\n" ++ block.3,
             filter(\block::(String, Integer, String) -> block.1 == "silver",
                    extractCodeBlocks(markdown)));
}
```

We do the actual extraction with the [commonmark-java](https://github.com/commonmark/commonmark-java) library.
The actual conversion is in `Markdown.java` in the runtime; see there for the fine details.

```silver
function extractCodeBlocks
[(String, Integer, String)] ::= markdown::String
{
  return [];
} foreign {
  "java" : return "common.Markdown.extractCodeBlocks(%markdown%.toString())";
}
```
