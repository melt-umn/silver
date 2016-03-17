grammar silver:extension:doc:core;

lexer class DOC;

-- For regular documentation comments
terminal CommentOpen_t '{@comment' lexer classes {DOC};
terminal CommentBody_t /[a-zA-Z0-9][^\@\}]*/ lexer classes {DOC};

-- For configuration comments
terminal ConfigOpen_t '{@config' lexer classes {DOC};
terminal ConfigHeader_t 'header' lexer classes {DOC};
terminal ConfigSplitFiles_t 'split-files' lexer classes {DOC};
terminal ConfigNoDoc_t 'no-doc' lexer classes {DOC};
terminal ConfigValue_t /"[^"]*"/ lexer classes {DOC};

-- For marking declarations as having no documentation
terminal NoDocComment_t /{@no\-doc(\ )*@}/ dominates {DOC};

-- Closes a documentation block
terminal Close_t '@}' lexer classes {DOC};



