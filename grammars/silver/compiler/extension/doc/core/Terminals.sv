grammar silver:compiler:extension:doc:core;
imports silver:compiler:extension:doc:core:doclang;

-- Once nested
terminal DocComment_t /@\{\-(\{\-([^\-]|\-+[^\}\-])*\-+\}|[^\-]|\-+[^\}\-])*\-+\}/;

-- No nesting
-- terminal DocComment_t /@\{\-+([^\-]|\-[^\}]|[^\-\}])*\-+\}/;

