// Copyright (C) 2011 Ted Kaminski
// Based on lang-hs.js Copyright (C) 2009 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.



/**
 * @fileoverview
 * Registers a language handler for Silver for the google-code-prettify tool.
 *
 * This file supports the following language extensions:
 *     lang-sv - Silver
 *
 * @author tedinski@cs.umn.edu
 */

PR['registerLangHandler'](
    PR['createSimpleLexer'](
        [
         // Whitespace
         [PR['PR_PLAIN'],       /^[\t\n\r \xA0]+/, null, '\t\n\r \xA0'],
         // String literals. Original regex:
         // [\"]([^\n\"\\]|[\\][\"]|[\\][\\]|[\\]n|[\\]r|[\\]t)*[\"]
         // Modifications: tack on \r\x0c. For extra forgivingness, end at the end of a line.
         [PR['PR_STRING'],      /^\"(?:[^\"\\\n\x0C\r]|\\.)*(?:\"|$)/, null, '"'],
         // Terminal literals
         [PR['PR_STRING'],      /^\'(?:[^\'\\\n\x0C\r]|\\.)*(?:\'|$)/, null, "'"],
         // Numerical literals
         [PR['PR_LITERAL'],     /^(?:[0-9]+|[0-9]+\.[0-9]+)/, null, '0123456789'],
         // Child literal numerals
         [PR['PR_LITERAL'],     /^\$[0-9]+/, null, '$']
        ],
        [
         // Haskell-style comments. Just use the regex from lang-hs.js:
         [PR['PR_COMMENT'],     /^(?:(?:--+(?:[^\r\n\x0C]*)?)|(?:\{-(?:[^-]|-+[^-\}])*-\}))/],
         // Silver keyword list, borrowed from the gedit highlighting file:
         [PR['PR_KEYWORD'],     /^(?:grammar|import|imports|exports|build|hiding|as|only|parser|parse|attribute|function|local|nonterminal|type|occurs|on|production|terminal|foreign|layout|disambiguate|action|global|productions|lexer|class|abstract|aspect|concrete|inherited|synthesized|autocopy|ignore|left|association|right|precedence|operator|dominates|submits|classes|forwarding|forwards|to|return|pluck|print|case|of|let|in|end|decorate|with|else|forward|if|new|then|toString|toInt|toFloat|length|prefix|false|true)\b/, null],
         // Ordinary identifiers:
         [PR['PR_PLAIN'],  /^[a-z][\w]*\b/],
         // Types are capitalized
         [PR['PR_TYPE'],        /^[A-Z][\w]*\b/],
         // I suppose this is the easy way to avoid specing all the operators?
         [PR['PR_PUNCTUATION'], /^[^\t\n\x0B\x0C\r a-zA-Z0-9\'\"]+/]
        ]),
    ['sv']);


