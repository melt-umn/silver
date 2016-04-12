grammar silver:extension:doc:core;


temp_imp_ide_font font_doc color(82, 100, 166) italic; -- TODO?
temp_imp_ide_font font_doc_kwd color(82, 100, 166) italic bold; -- TODO?
temp_imp_ide_font font_doc_id color(82, 100, 166); -- TODO?

lexer class DOC font = font_doc;

-- For comments on AGDcls
terminal CommentOpen_t '{@comment' lexer classes {DOC}, font = font_doc_kwd;
terminal CommentLink_t '@link' lexer classes {DOC}, font = font_doc_kwd;
terminal CommentId_t /[a-zA-Z0-9]+[a-zA-Z0-9:]*/ lexer classes {DOC}, font = font_doc_id;
terminal CommentOpenSquare_t '[' lexer classes {DOC};
terminal CommentCloseSquare_t ']' lexer classes {DOC};

-- For documentation configuration
terminal ConfigOpen_t '{@config' lexer classes {DOC}, font = font_doc_kwd;
terminal ConfigHeader_t 'header' lexer classes {DOC}, font = font_doc_kwd;
terminal ConfigSplitFiles_t 'split-files' lexer classes {DOC}, font = font_doc_kwd;
terminal ConfigNoDoc_t 'no-doc' lexer classes {DOC}, font = font_doc_kwd;
terminal ConfigValue_t /"[^"]*"/ lexer classes {DOC};

-- For tutorials and large bodies of text
--terminal TutOpen_t '{@tutor' lexer classes {DOC};

-- For comments on AGDcls
terminal NoDclComment_t /{@no\-doc(\ )*@}/ dominates {DOC}, font = font_doc_kwd;

-- Closes a documentation block
terminal Close_t '@}' lexer classes {DOC}, font = font_doc_kwd;

-- Captures text inside comments
terminal CommentText_t /[a-zA-Z0-9][^\@]*/ lexer classes {DOC};

