{-- ATOM HIGHLIGHTING LEXER CLASSES --}
lexer class atom_comment;
lexer class atom_comment_line;
lexer class atom_comment_line_doubleSlash;
lexer class atom_comment_line_doubleDash;
lexer class atom_comment_line_numberSign;
lexer class atom_comment_line_percentage;
lexer class atom_comment_block;
lexer class atom_comment_block_documentation;

lexer class atom_constant;
lexer class atom_constant_numeric;
lexer class atom_constant_character;
lexer class atom_constant_character_escape;
lexer class atom_constant_language;
lexer class atom_constant_other;

lexer class atom_entity;
lexer class atom_entity_name;
lexer class atom_entity_name_function;
lexer class atom_entity_name_type;
lexer class atom_entity_name_tag;
lexer class atom_entity_name_section;
lexer class atom_entity_other;
lexer class atom_entity_other_inheritedClass;
lexer class atom_entity_other_attributeName;

lexer class atom_invalid;
lexer class atom_invalid_illegal;
lexer class atom_invalid_deprecated;

lexer class atom_keyword;
lexer class atom_keyword_control;
lexer class atom_keyword_operator;
lexer class atom_keyword_other;

lexer class atom_markup;
lexer class atom_markup_underline;

lexer class atom_markup_heading;
lexer class atom_markup_italic;
lexer class atom_markup_list;
lexer class atom_markup_list_numbered;
lexer class atom_markup_list_unnumbered;
lexer class atom_markup_quote;
lexer class atom_markup_raw;
lexer class atom_markup_other;

lexer class atom_meta;

lexer class atom_storage;
lexer class atom_storage_type;
lexer class atom_storage_modifier;

lexer class atom_string;
lexer class atom_string_quoted;
lexer class atom_string_quoted_single;
lexer class atom_string_quoted_double;
lexer class atom_string_quoted_triple;
lexer class atom_string_quoted_other;
lexer class atom_string_unquoted;
lexer class atom_string_interpolated;
lexer class atom_string_regexp;
lexer class atom_string_other;

lexer class atom_support;
lexer class atom_support_function;
lexer class atom_support_class;
lexer class atom_support_type;
lexer class atom_support_constant;
lexer class atom_support_variable;
lexer class atom_support_other;

lexer class atom_variable;
lexer class atom_variable_parameter;
lexer class atom_variable_language;
lexer class atom_variable_other;
