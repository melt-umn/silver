
imports core with Location as CoreLocation;

function silverLocationToRange
Range ::= loc::CoreLocation
{
  return range(
    position(loc.line-1, loc.column),
    position(loc.endLine-1, loc.endColumn));
}

function silverLocationToLSPLocation
Location ::= loc::CoreLocation
{
  return location(fileToUri(loc.filename), silverLocationToRange(loc));
}

function doesRangeContainPosition
Boolean ::= range::Range pos::Position
{
  -- range.startPosition <= pos && pos <= range.endPosition
  return positionLte(range.startPosition, pos) && positionLte(pos, range.endPosition);
}

function doesLocationContainPosition
Boolean ::= loc::Location pos::Position
{
  return doesRangeContainPosition(loc.range, pos);
}

function doesLocationContainRange
Boolean ::= loc::Location range::Range
{
  return doesLocationContainPosition(loc, range.startPosition) &&
         doesLocationContainPosition(loc, range.endPosition);
}

function doesLocationContainLocation
Boolean ::= bigLoc::Location lilLoc::Location
{
  return bigLoc.uri == lilLoc.uri &&
         doesLocationContainRange(bigLoc, lilLoc.range);
}

function silverLocationContainsPosition
Boolean ::= loc::CoreLocation pos::Position
{
  return doesLocationContainPosition(silverLocationToLSPLocation(loc), pos);
}

function positionLte
Boolean ::= pos1::Position pos2::Position
{
  return pos1.lineNum < pos2.lineNum || 
         (pos1.lineNum == pos2.lineNum && pos1.characterPos <= pos2.characterPos);
}

function positionEq
Boolean ::= pos1::Position pos2::Position
{
  return pos1.lineNum == pos2.lineNum && pos1.characterPos == pos2.characterPos;
}

function rangeEq
Boolean ::= r1::Range r2::Range
{
  return positionEq(r1.startPosition, r2.startPosition) && 
         positionEq(r1.endPosition, r2.endPosition);
}

function locationEq
Boolean ::= loc1::Location loc2::Location
{
  return stringEq(loc1.uri, loc2.uri) && rangeEq(loc1.range, loc2.range);
}

function coreLocationEq
Boolean ::= loc1::CoreLocation loc2::CoreLocation
{
  return locationEq(silverLocationToLSPLocation(loc1), silverLocationToLSPLocation(loc2));
}

function locationToString
String ::= loc::CoreLocation
{
  local lspLoc :: Location = silverLocationToLSPLocation(loc);
  return s"""${lspLoc.uri} at range: ${rangeToString(lspLoc.range)}""";
}

function rangeToString
String ::= r::Range
{
  return s"""start: ${positionToString(r.startPosition)}, end: ${positionToString(r.endPosition)}""";
}

function positionToString
String ::= p::Position
{
  return s"""line ${toString(p.lineNum)} column ${toString(p.characterPos)}""";
}
