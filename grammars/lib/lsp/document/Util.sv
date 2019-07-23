
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
