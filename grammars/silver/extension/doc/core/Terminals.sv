grammar silver:extension:doc:core;

temp_imp_ide_font font_doc color(82, 100, 166) italic; -- TODO?
temp_imp_ide_font font_doc_kwd color(82, 100, 166) italic bold; -- TODO?
temp_imp_ide_font font_doc_id color(82, 100, 166); -- TODO?

lexer class DOC extends Silver, font = font_doc;
lexer class DOC_KWD extends Silver, font = font_doc_kwd;
lexer class DOC_ID extends Silver, font = font_doc_id;

-- For comments on AGDcls
terminal CommentOpen_t '{@comment' lexer classes {DOC_KWD};
terminal CommentLink_t '@link' lexer classes {DOC_KWD};
terminal CommentId_t /[a-zA-Z0-9]+[a-zA-Z0-9:]*/ lexer classes {DOC_ID};
terminal CommentOpenSquare_t '[' lexer classes {DOC};
terminal CommentCloseSquare_t ']' lexer classes {DOC};

-- For grammar level documentation configuration
terminal ConfigOpen_t '{@config' lexer classes {DOC_KWD};
terminal ConfigHeader_t 'header' lexer classes {DOC_KWD};
terminal ConfigSplitFiles_t 'split-files' lexer classes {DOC_KWD};
terminal ConfigNoDoc_t 'no-doc' lexer classes {DOC_KWD};
terminal ConfigValue_t /"[^"]*"/ lexer classes {DOC};

-- For tutorials and large bodies of text
--terminal TutOpen_t '{@tutor' lexer classes {DOC};

-- For comments on AGDcls
terminal NoDclComment_t /{@no\-doc(\ )*@}/ dominates {DOC_KWD};

-- Closes a documentation block
terminal Close_t '@}' lexer classes {DOC_KWD};

-- Captures text inside comments
terminal CommentText_t /[a-zA-Z0-9][^\@]*/ lexer classes {DOC};

