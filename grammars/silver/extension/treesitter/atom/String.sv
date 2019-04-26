function jsonify
String ::= str::String
{
  -- substitute backslashes with double backslashes then substitute double quotes
  -- with backslash quote
  return substitute("\"", "\\\"", substitute("\\", "\\\\", str));
}
