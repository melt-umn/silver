grammar silver:compiler:extension:doc:core;
imports silver:compiler:extension:doc:core:doclang;
imports silver:langutil:lsp as lsp;

terminal AtSign_t '@' lexer classes {COMMENT, lsp:Documentation};

-- Once nested
terminal DocComment_t /@\{\-(\{\-([^\-]|\-+[^\}\-])*\-+\}|[^\-]|\-+[^\}\-])*\-+\}/ lexer classes {COMMENT, lsp:Documentation};

-- No nesting
-- terminal DocComment_t /@\{\-+([^\-]|\-[^\}]|[^\-\}])*\-+\}/;
