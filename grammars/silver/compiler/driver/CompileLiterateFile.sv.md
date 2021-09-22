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
    then implode("\n", extractSilverCodeBlocks(path, contents))
```

Since these are the only two extensions allowed by `isValidSilverFile` in `CompileGrammar.sv`, they're the only two we need to handle.

```silver
    else error("Unknown filetype for " ++ path);
}
```

## Literate Silver

The preprocessing for Literate Silver files is just a matter of extracting and concatenating blocks of Silver code.
The CommonMark spec notes that commonly only the first word is used as the language; all the ones we recognize start with `silver` as a result.

In the future, we may want to support info strings like `silver test`, `silver wrongCode`, etc.
See [rustdoc](https://doc.rust-lang.org/rustdoc/documentation-tests.html#attributes) for prior art.


```silver
function extractSilverCodeBlocks
[String] ::= path::String  markdown::String
{
  local allCodeBlocks::[(String, Location, String)] = extractCodeBlocks(path, markdown);
```

The simple case is blocks whose info string is *exactly* `silver`.
These are replicated verbatim, after adjusting the line number so locations match.

```silver
  local goodCode::[String] =
    map(\block::(String, Location, String) -> "#line " ++ toString(block.2.line + 1) ++ "\n" ++ block.3,
        filter(\block::(String, Location, String) -> block.1 == "silver", allCodeBlocks));
```

An info string of `silver imports` causes the code block to be hoisted to the top; this is useful because imports need to be at the top of the file, but we might want to only show an import later on in a document, to avoid scaring the reader off with a large block of imports at the top.

```silver
  local importsCode::[String] =
    map(\block::(String, Location, String) -> "#line " ++ toString(block.2.line + 1) ++ "\n" ++ block.3,
        filter(\block::(String, Location, String) -> block.1 == "silver imports", allCodeBlocks));
```

We also emit warnings for code blocks with no info string.

```silver
  local warnCode::[String] =
    map(\block::(String, Location, String) -> "#line " ++ toString(block.2.line + 1) ++ "\n"
                                           ++ "#warn Code block with no info string; this won't be compiled",
        filter(\block::(String, Location, String) -> block.1 == "", allCodeBlocks));
```

Since we use `#line` to set the line numbers on the parsed trees, we don't need to do any fancy interleaving or anything before returning all the blocks.

```silver
  return importsCode ++ goodCode ++ warnCode;
}
```

We do the actual extraction with the [commonmark-java](https://github.com/commonmark/commonmark-java) library.
The actual conversion is in `Markdown.java` in the runtime; see there for the fine details.

```silver
function extractCodeBlocks
[(String, Location, String)] ::= path::String  markdown::String
{
  return [];
} foreign {
  "java" : return "common.Markdown.extractCodeBlocks(%path%.toString(), %markdown%.toString())";
}
```
