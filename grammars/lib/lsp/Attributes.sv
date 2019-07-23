
synthesized attribute message :: String;
synthesized attribute range :: Range;
synthesized attribute optionalRange :: Maybe<Range>;
synthesized attribute position :: Position;
synthesized attribute optionalPosition :: Maybe<Position>;

synthesized attribute dynamicRegistration :: Maybe<Boolean>;
synthesized attribute resolveProvider :: Maybe<Boolean>;
synthesized attribute prepareProvider :: Maybe<Boolean>;
synthesized attribute linkSupport :: Maybe<Boolean>;
synthesized attribute optionalSupported :: Maybe<Boolean>;
synthesized attribute ignoreIfExists :: Maybe<Boolean>;
synthesized attribute shouldOverwrite :: Maybe<Boolean>;

synthesized attribute documentText :: String;
synthesized attribute documentId :: TextDocumentIdentifier;
synthesized attribute versionedDocumentId :: VersionedTextDocumentIdentifier;
synthesized attribute textEdits :: [TextEdit];
synthesized attribute newText :: String;
synthesized attribute documentVersionNumber :: Integer;
synthesized attribute formattingOptions :: FormattingOptions;
synthesized attribute fileOperationKind :: String;
synthesized attribute workspaceEdit :: WorkspaceEdit;
