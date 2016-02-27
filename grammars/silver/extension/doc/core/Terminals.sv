grammar silver:extension:doc:core;

lexer class DOC;

terminal DocCommentOpen_t '{@' lexer classes {DOC};
terminal DocCommentClose_t '@}' lexer classes {DOC};
terminal DocCommentBody_t /[a-zA-Z0-9][^\@\}]*/ lexer classes {DOC};

terminal ConfigOpen_t '{#' lexer classes {DOC};
terminal ConfigClose_t '#}' lexer classes {DOC};
terminal ConfigHeader_t 'header' lexer classes {DOC};
terminal ConfigSplitFiles_t 'split-files' lexer classes {DOC};
terminal ConfigValue_t /"[^"]*"/ lexer classes {DOC};

terminal NoDocComment_t /{@(\ )*nodoc(\ )*@}/ dominates {DOC};

-- TODO: Find a way to indent doc information instead of quoting in markdown
-- TODO: Add document comments for parser declarations.
-- TODO: Add document comments for type declarations.
-- TODO: Use special string for above variables



