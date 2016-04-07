grammar silver:extension:doc:core;

lexer class DOC;

-- For comments on AGDcls
terminal CommentOpen_t '{@comment' lexer classes {DOC};
terminal CommentLink_t '@link' lexer classes {DOC};
terminal CommentId_t /[a-zA-Z0-9]+[a-zA-Z0-9:]*/ lexer classes {DOC};
terminal CommentOpenSquare_t '[' lexer classes {DOC};
terminal CommentCloseSquare_t ']' lexer classes {DOC};

-- For documentation configuration
terminal ConfigOpen_t '{@config' lexer classes {DOC};
terminal ConfigHeader_t 'header' lexer classes {DOC};
terminal ConfigSplitFiles_t 'split-files' lexer classes {DOC};
terminal ConfigNoDoc_t 'no-doc' lexer classes {DOC};
terminal ConfigValue_t /"[^"]*"/ lexer classes {DOC};

-- For tutorials and large bodies of text
--terminal TutOpen_t '{@tutor' lexer classes {DOC};

-- For comments on AGDcls
terminal NoDclComment_t /{@no\-doc(\ )*@}/ dominates {DOC};

-- Closes a documentation block
terminal Close_t '@}' lexer classes {DOC};

-- Captures text inside comments
terminal CommentText_t /[a-zA-Z0-9][^\@\}]*/ lexer classes {DOC};

