/*
 * Built at Mon Dec 11 06:56:49 UTC 2017
 * by Copper version 0.7.2,
 *      build 20170816-1437
 */
package silver.composed.Default;


import java.util.ArrayList;
import java.util.List;


public class Parser_silver_composed_Default_sviParse extends edu.umn.cs.melt.copper.runtime.engines.single.SingleDFAEngine<silver.definition.env.env_parser.NIRoot,edu.umn.cs.melt.copper.runtime.logging.CopperParserException>
{
    protected String formatError(String error)
    {
    	   String location = "";
        location += "line " + virtualLocation.getLine() + ", column " + virtualLocation.getColumn();
        if(currentState.pos.getFileName().length() > 40) location += "\n         ";
        location += " in file " + virtualLocation.getFileName();
        location += "\n         (parser state: " + currentState.statenum + "; real character index: " + currentState.pos.getPos() + ")";
        return "Error at " + location + ":\n  " + error;
    }
    protected void reportError(String message)
    throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
        throw new edu.umn.cs.melt.copper.runtime.logging.CopperParserException(message);
    }
    protected void reportSyntaxError()
    throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
    java.util.ArrayList<String> expectedTerminalsReal = bitVecToRealStringList(getShiftableSets()[currentState.statenum]);
    java.util.ArrayList<String> expectedTerminalsDisplay = bitVecToDisplayStringList(getShiftableSets()[currentState.statenum]);
    java.util.ArrayList<String> matchedTerminalsReal = bitVecToRealStringList(disjointMatch.terms);
    java.util.ArrayList<String> matchedTerminalsDisplay = bitVecToDisplayStringList(disjointMatch.terms);
    throw new edu.umn.cs.melt.copper.runtime.logging.CopperSyntaxError(virtualLocation,currentState.pos,currentState.statenum,expectedTerminalsReal,expectedTerminalsDisplay,matchedTerminalsReal,matchedTerminalsDisplay);
    }
    public static enum Terminals implements edu.umn.cs.melt.copper.runtime.engines.CopperTerminalEnum
    {
        silver_definition_concrete_syntax_ast_env_parser_AcodeTerm(1),
        silver_definition_concrete_syntax_ast_env_parser_AssocTerm(2),
        silver_definition_concrete_syntax_ast_env_parser_ClassesTerm(3),
        silver_definition_concrete_syntax_ast_env_parser_DisTerm(4),
        silver_definition_concrete_syntax_ast_env_parser_DomTerm(5),
        silver_definition_concrete_syntax_ast_env_parser_IgnoreTerm(6),
        silver_definition_concrete_syntax_ast_env_parser_LClassTerm(7),
        silver_definition_concrete_syntax_ast_env_parser_LayoutTerm(8),
        silver_definition_concrete_syntax_ast_env_parser_MarkingTerm(9),
        silver_definition_concrete_syntax_ast_env_parser_OperTerm(10),
        silver_definition_concrete_syntax_ast_env_parser_ParAttr(11),
        silver_definition_concrete_syntax_ast_env_parser_ParserTerm(12),
        silver_definition_concrete_syntax_ast_env_parser_ParsersTerm(13),
        silver_definition_concrete_syntax_ast_env_parser_PrecTerm(14),
        silver_definition_concrete_syntax_ast_env_parser_SubTerm(15),
        silver_definition_concrete_syntax_ast_env_parser_SyntaxTerm(16),
        silver_definition_env_env_parser_AllDepsTerm(17),
        silver_definition_env_env_parser_AnnoAt_t(18),
        silver_definition_env_env_parser_Anno_t(19),
        silver_definition_env_env_parser_BooleanTerm(20),
        silver_definition_env_env_parser_Comma_t(21),
        silver_definition_env_env_parser_CondBuildTerm(22),
        silver_definition_env_env_parser_DeclaredNameTerm(23),
        silver_definition_env_env_parser_DecoratedTerm(24),
        silver_definition_env_env_parser_DefaultTerm(25),
        silver_definition_env_env_parser_DefsTerm(26),
        silver_definition_env_env_parser_Eq(27),
        silver_definition_env_env_parser_EscapedStringTerm(28),
        silver_definition_env_env_parser_ExportedGrammarsTerm(29),
        silver_definition_env_env_parser_F_t(30),
        silver_definition_env_env_parser_FloatTerm(31),
        silver_definition_env_env_parser_ForwardTerm(32),
        silver_definition_env_env_parser_FunctionTerm(33),
        silver_definition_env_env_parser_GlobTerm(34),
        silver_definition_env_env_parser_GrammarSourceTerm(35),
        silver_definition_env_env_parser_GrammarTimeTerm(36),
        silver_definition_env_env_parser_IOTerm(37),
        silver_definition_env_env_parser_ITyVar(38),
        silver_definition_env_env_parser_Id_t(39),
        silver_definition_env_env_parser_InheritedTerm(40),
        silver_definition_env_env_parser_IntegerTerm(41),
        silver_definition_env_env_parser_LB_t(42),
        silver_definition_env_env_parser_LParent_t(43),
        silver_definition_env_env_parser_LocalTerm(44),
        silver_definition_env_env_parser_ModuleNamesTerm(45),
        silver_definition_env_env_parser_NonterminalTerm(46),
        silver_definition_env_env_parser_Num_t(47),
        silver_definition_env_env_parser_OccursTerm(48),
        silver_definition_env_env_parser_OptionalGrammarsTerm(49),
        silver_definition_env_env_parser_ProdAttrTerm(50),
        silver_definition_env_env_parser_ProductionTerm(51),
        silver_definition_env_env_parser_RB_t(52),
        silver_definition_env_env_parser_RParent_t(53),
        silver_definition_env_env_parser_RegExprDelim(54),
        silver_definition_env_env_parser_Semi(55),
        silver_definition_env_env_parser_SignatureElementTerm(56),
        silver_definition_env_env_parser_SignatureTerm(57),
        silver_definition_env_env_parser_Sigturnstile(58),
        silver_definition_env_env_parser_StringTerm(59),
        silver_definition_env_env_parser_SynthesizedTerm(60),
        silver_definition_env_env_parser_T_t(61),
        silver_definition_env_env_parser_TerminalTerm(62),
        silver_definition_env_env_parser_WS(63),
        silver_definition_flow_env_parser_AnonEqVTerm(64),
        silver_definition_flow_env_parser_AnonInhTerm(65),
        silver_definition_flow_env_parser_AnonTerm(66),
        silver_definition_flow_env_parser_AnonVTerm(67),
        silver_definition_flow_env_parser_DefTerm(68),
        silver_definition_flow_env_parser_ExtraTerm(69),
        silver_definition_flow_env_parser_FlowTerm(70),
        silver_definition_flow_env_parser_FwdInhTerm(71),
        silver_definition_flow_env_parser_ImplicitFwdAffectsTerm(72),
        silver_definition_flow_env_parser_LhsFlow(73),
        silver_definition_flow_env_parser_LhsInhVTerm(74),
        silver_definition_flow_env_parser_LhsSynVTerm(75),
        silver_definition_flow_env_parser_LocalEqVTerm(76),
        silver_definition_flow_env_parser_LocalInhTerm(77),
        silver_definition_flow_env_parser_LocalTerm(78),
        silver_definition_flow_env_parser_LocalVTerm(79),
        silver_definition_flow_env_parser_NonHostSynTerm(80),
        silver_definition_flow_env_parser_NtRefDefTerm(81),
        silver_definition_flow_env_parser_PatRulEqTerm(82),
        silver_definition_flow_env_parser_RhsFlow(83),
        silver_definition_flow_env_parser_RhsVTerm(84),
        silver_definition_flow_env_parser_SpecFlow(85),
        silver_definition_regex_Choice_t(86),
        silver_definition_regex_EscapedChar_t(87),
        silver_definition_regex_Kleene_t(88),
        silver_definition_regex_Optional_t(89),
        silver_definition_regex_Plus_t(90),
        silver_definition_regex_Range_t(91),
        silver_definition_regex_RegexChar_t(92),
        silver_definition_regex_RegexLBrack_t(93),
        silver_definition_regex_RegexLParen_t(94),
        silver_definition_regex_RegexNot_t(95),
        silver_definition_regex_RegexRBrack_t(96),
        silver_definition_regex_RegexRParen_t(97),
        silver_definition_regex_RegexWildcard_t(98),
        silver_modification_autocopyattr_env_parser_AutoCopyTerm(99),
        silver_modification_collection_env_parser_BOrTerm(100),
        silver_modification_collection_env_parser_InhColTerm(101),
        silver_modification_collection_env_parser_LocColTerm(102),
        silver_modification_collection_env_parser_PAndTerm(103),
        silver_modification_collection_env_parser_PlusPlusLst(104),
        silver_modification_collection_env_parser_PlusPlusStr(105),
        silver_modification_collection_env_parser_SynColTerm(106),
        silver_modification_copper_env_parser_LexerClassTerm(107),
        silver_modification_copper_env_parser_ParseAttrTerm(108),
        silver_modification_copper_env_parser_PrefixSeperatorTerm(109),
        silver_modification_ffi_env_parser_FTTerm(110),
        silver_modification_impide_env_parser_FontTerm(111),
        silver_modification_typedecl_env_parser_TypeTerm(112);

        private final int num;
        Terminals(int num) { this.num = num; }
        public int num() { return num; }
    }

    public void pushToken(Terminals t,String lexeme)
    {
        java.util.BitSet ts = new java.util.BitSet();
        ts.set(t.num());
        tokenBuffer.offer(new edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData(ts,currentState.pos,currentState.pos,lexeme,new java.util.LinkedList<edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData>()));
    }
    public void setupEngine()
    {
    }
    public int transition(int state,char ch)
    {
         return delta[state][cmap[ch]];
    }
    public class Semantics extends edu.umn.cs.melt.copper.runtime.engines.single.semantics.SingleDFASemanticActionContainer<edu.umn.cs.melt.copper.runtime.logging.CopperParserException>
    {
        public common.DecoratedNode context;

        public Semantics()
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            runInit();
        }

        public void error(edu.umn.cs.melt.copper.runtime.io.InputPosition pos,java.lang.String message)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            reportError("Error at " + pos.toString() + ":\n  " + message);
        }

        public void runDefaultTermAction()
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
        }
        public void runDefaultProdAction()
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
        }
        public void runInit()
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            
        reset();
                  context = common.TopNode.singleton;
        }
        public java.lang.Object runSemanticAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,java.lang.Object[] _children,int _prod)
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            this._pos = _pos;
            this._children = _children;
            this._prod = _prod;
            this._specialAttributes = new edu.umn.cs.melt.copper.runtime.engines.semantics.SpecialParserAttributes(virtualLocation);
            java.lang.Object RESULT = null;
            switch(_prod)
            {
            case 169:
                RESULT = runSemanticAction_169();
                break;
            case 170:
                RESULT = runSemanticAction_170();
                break;
            case 171:
                RESULT = runSemanticAction_171();
                break;
            case 172:
                RESULT = runSemanticAction_172();
                break;
            case 173:
                RESULT = runSemanticAction_173();
                break;
            case 174:
                RESULT = runSemanticAction_174();
                break;
            case 175:
                RESULT = runSemanticAction_175();
                break;
            case 176:
                RESULT = runSemanticAction_176();
                break;
            case 177:
                RESULT = runSemanticAction_177();
                break;
            case 178:
                RESULT = runSemanticAction_178();
                break;
            case 179:
                RESULT = runSemanticAction_179();
                break;
            case 180:
                RESULT = runSemanticAction_180();
                break;
            case 181:
                RESULT = runSemanticAction_181();
                break;
            case 182:
                RESULT = runSemanticAction_182();
                break;
            case 183:
                RESULT = runSemanticAction_183();
                break;
            case 184:
                RESULT = runSemanticAction_184();
                break;
            case 185:
                RESULT = runSemanticAction_185();
                break;
            case 186:
                RESULT = runSemanticAction_186();
                break;
            case 187:
                RESULT = runSemanticAction_187();
                break;
            case 188:
                RESULT = runSemanticAction_188();
                break;
            case 189:
                RESULT = runSemanticAction_189();
                break;
            case 190:
                RESULT = runSemanticAction_190();
                break;
            case 191:
                RESULT = runSemanticAction_191();
                break;
            case 192:
                RESULT = runSemanticAction_192();
                break;
            case 193:
                RESULT = runSemanticAction_193();
                break;
            case 194:
                RESULT = runSemanticAction_194();
                break;
            case 195:
                RESULT = runSemanticAction_195();
                break;
            case 196:
                RESULT = runSemanticAction_196();
                break;
            case 197:
                RESULT = runSemanticAction_197();
                break;
            case 198:
                RESULT = runSemanticAction_198();
                break;
            case 199:
                RESULT = runSemanticAction_199();
                break;
            case 200:
                RESULT = runSemanticAction_200();
                break;
            case 201:
                RESULT = runSemanticAction_201();
                break;
            case 202:
                RESULT = runSemanticAction_202();
                break;
            case 203:
                RESULT = runSemanticAction_203();
                break;
            case 204:
                RESULT = runSemanticAction_204();
                break;
            case 205:
                RESULT = runSemanticAction_205();
                break;
            case 206:
                RESULT = runSemanticAction_206();
                break;
            case 207:
                RESULT = runSemanticAction_207();
                break;
            case 208:
                RESULT = runSemanticAction_208();
                break;
            case 209:
                RESULT = runSemanticAction_209();
                break;
            case 210:
                RESULT = runSemanticAction_210();
                break;
            case 211:
                RESULT = runSemanticAction_211();
                break;
            case 212:
                RESULT = runSemanticAction_212();
                break;
            case 213:
                RESULT = runSemanticAction_213();
                break;
            case 214:
                RESULT = runSemanticAction_214();
                break;
            case 215:
                RESULT = runSemanticAction_215();
                break;
            case 216:
                RESULT = runSemanticAction_216();
                break;
            case 217:
                RESULT = runSemanticAction_217();
                break;
            case 218:
                RESULT = runSemanticAction_218();
                break;
            case 219:
                RESULT = runSemanticAction_219();
                break;
            case 220:
                RESULT = runSemanticAction_220();
                break;
            case 221:
                RESULT = runSemanticAction_221();
                break;
            case 222:
                RESULT = runSemanticAction_222();
                break;
            case 223:
                RESULT = runSemanticAction_223();
                break;
            case 224:
                RESULT = runSemanticAction_224();
                break;
            case 225:
                RESULT = runSemanticAction_225();
                break;
            case 226:
                RESULT = runSemanticAction_226();
                break;
            case 227:
                RESULT = runSemanticAction_227();
                break;
            case 228:
                RESULT = runSemanticAction_228();
                break;
            case 229:
                RESULT = runSemanticAction_229();
                break;
            case 230:
                RESULT = runSemanticAction_230();
                break;
            case 231:
                RESULT = runSemanticAction_231();
                break;
            case 232:
                RESULT = runSemanticAction_232();
                break;
            case 233:
                RESULT = runSemanticAction_233();
                break;
            case 234:
                RESULT = runSemanticAction_234();
                break;
            case 235:
                RESULT = runSemanticAction_235();
                break;
            case 236:
                RESULT = runSemanticAction_236();
                break;
            case 237:
                RESULT = runSemanticAction_237();
                break;
            case 238:
                RESULT = runSemanticAction_238();
                break;
            case 239:
                RESULT = runSemanticAction_239();
                break;
            case 240:
                RESULT = runSemanticAction_240();
                break;
            case 241:
                RESULT = runSemanticAction_241();
                break;
            case 242:
                RESULT = runSemanticAction_242();
                break;
            case 243:
                RESULT = runSemanticAction_243();
                break;
            case 244:
                RESULT = runSemanticAction_244();
                break;
            case 245:
                RESULT = runSemanticAction_245();
                break;
            case 246:
                RESULT = runSemanticAction_246();
                break;
            case 247:
                RESULT = runSemanticAction_247();
                break;
            case 248:
                RESULT = runSemanticAction_248();
                break;
            case 249:
                RESULT = runSemanticAction_249();
                break;
            case 250:
                RESULT = runSemanticAction_250();
                break;
            case 251:
                RESULT = runSemanticAction_251();
                break;
            case 252:
                RESULT = runSemanticAction_252();
                break;
            case 253:
                RESULT = runSemanticAction_253();
                break;
            case 254:
                RESULT = runSemanticAction_254();
                break;
            case 255:
                RESULT = runSemanticAction_255();
                break;
            case 256:
                RESULT = runSemanticAction_256();
                break;
            case 257:
                RESULT = runSemanticAction_257();
                break;
            case 258:
                RESULT = runSemanticAction_258();
                break;
            case 259:
                RESULT = runSemanticAction_259();
                break;
            case 260:
                RESULT = runSemanticAction_260();
                break;
            case 261:
                RESULT = runSemanticAction_261();
                break;
            case 262:
                RESULT = runSemanticAction_262();
                break;
            case 263:
                RESULT = runSemanticAction_263();
                break;
            case 264:
                RESULT = runSemanticAction_264();
                break;
            case 265:
                RESULT = runSemanticAction_265();
                break;
            case 266:
                RESULT = runSemanticAction_266();
                break;
            case 267:
                RESULT = runSemanticAction_267();
                break;
            case 268:
                RESULT = runSemanticAction_268();
                break;
            case 269:
                RESULT = runSemanticAction_269();
                break;
            case 270:
                RESULT = runSemanticAction_270();
                break;
            case 271:
                RESULT = runSemanticAction_271();
                break;
            case 272:
                RESULT = runSemanticAction_272();
                break;
            case 273:
                RESULT = runSemanticAction_273();
                break;
            case 274:
                RESULT = runSemanticAction_274();
                break;
            case 275:
                RESULT = runSemanticAction_275();
                break;
            case 276:
                RESULT = runSemanticAction_276();
                break;
            case 277:
                RESULT = runSemanticAction_277();
                break;
            case 278:
                RESULT = runSemanticAction_278();
                break;
            case 279:
                RESULT = runSemanticAction_279();
                break;
            case 280:
                RESULT = runSemanticAction_280();
                break;
            case 281:
                RESULT = runSemanticAction_281();
                break;
            case 282:
                RESULT = runSemanticAction_282();
                break;
            case 283:
                RESULT = runSemanticAction_283();
                break;
            case 284:
                RESULT = runSemanticAction_284();
                break;
            case 285:
                RESULT = runSemanticAction_285();
                break;
            case 286:
                RESULT = runSemanticAction_286();
                break;
            case 287:
                RESULT = runSemanticAction_287();
                break;
            case 288:
                RESULT = runSemanticAction_288();
                break;
            case 289:
                RESULT = runSemanticAction_289();
                break;
            case 290:
                RESULT = runSemanticAction_290();
                break;
            case 291:
                RESULT = runSemanticAction_291();
                break;
            case 292:
                RESULT = runSemanticAction_292();
                break;
            case 293:
                RESULT = runSemanticAction_293();
                break;
            case 294:
                RESULT = runSemanticAction_294();
                break;
            case 295:
                RESULT = runSemanticAction_295();
                break;
            case 296:
                RESULT = runSemanticAction_296();
                break;
            case 297:
                RESULT = runSemanticAction_297();
                break;
            case 298:
                RESULT = runSemanticAction_298();
                break;
            case 299:
                RESULT = runSemanticAction_299();
                break;
            case 300:
                RESULT = runSemanticAction_300();
                break;
            case 301:
                RESULT = runSemanticAction_301();
                break;
            case 302:
                RESULT = runSemanticAction_302();
                break;
            case 303:
                RESULT = runSemanticAction_303();
                break;
            case 304:
                RESULT = runSemanticAction_304();
                break;
            case 305:
                RESULT = runSemanticAction_305();
                break;
            case 306:
                RESULT = runSemanticAction_306();
                break;
            case 307:
                RESULT = runSemanticAction_307();
                break;
            case 308:
                RESULT = runSemanticAction_308();
                break;
            case 309:
                RESULT = runSemanticAction_309();
                break;
            case 310:
                RESULT = runSemanticAction_310();
                break;
            case 311:
                RESULT = runSemanticAction_311();
                break;
            case 312:
                RESULT = runSemanticAction_312();
                break;
            case 313:
                RESULT = runSemanticAction_313();
                break;
            case 314:
                RESULT = runSemanticAction_314();
                break;
            case 315:
                RESULT = runSemanticAction_315();
                break;
            case 316:
                RESULT = runSemanticAction_316();
                break;
            case 317:
                RESULT = runSemanticAction_317();
                break;
            case 318:
                RESULT = runSemanticAction_318();
                break;
            case 319:
                RESULT = runSemanticAction_319();
                break;
            case 320:
                RESULT = runSemanticAction_320();
                break;
            case 321:
                RESULT = runSemanticAction_321();
                break;
            case 322:
                RESULT = runSemanticAction_322();
                break;
            case 323:
                RESULT = runSemanticAction_323();
                break;
            case 324:
                RESULT = runSemanticAction_324();
                break;
            case 325:
                RESULT = runSemanticAction_325();
                break;
            case 326:
                RESULT = runSemanticAction_326();
                break;
            case 327:
                RESULT = runSemanticAction_327();
                break;
            case 328:
                RESULT = runSemanticAction_328();
                break;
            case 329:
                RESULT = runSemanticAction_329();
                break;
            case 330:
                RESULT = runSemanticAction_330();
                break;
            case 331:
                RESULT = runSemanticAction_331();
                break;
            case 332:
                RESULT = runSemanticAction_332();
                break;
            case 333:
                RESULT = runSemanticAction_333();
                break;
            case 334:
                RESULT = runSemanticAction_334();
                break;
            case 335:
                RESULT = runSemanticAction_335();
                break;
            case 336:
                RESULT = runSemanticAction_336();
                break;
            case 337:
                RESULT = runSemanticAction_337();
                break;
            case 338:
                RESULT = runSemanticAction_338();
                break;
            case 339:
                RESULT = runSemanticAction_339();
                break;
            case 340:
                RESULT = runSemanticAction_340();
                break;
            case 341:
                RESULT = runSemanticAction_341();
                break;
            case 342:
                RESULT = runSemanticAction_342();
                break;
            case 343:
                RESULT = runSemanticAction_343();
                break;
            case 344:
                RESULT = runSemanticAction_344();
                break;
            case 345:
                RESULT = runSemanticAction_345();
                break;
            case 346:
                RESULT = runSemanticAction_346();
                break;
            case 347:
                RESULT = runSemanticAction_347();
                break;
            case 348:
                RESULT = runSemanticAction_348();
                break;
            case 349:
                RESULT = runSemanticAction_349();
                break;
            case 350:
                RESULT = runSemanticAction_350();
                break;
            case 351:
                RESULT = runSemanticAction_351();
                break;
            case 352:
                RESULT = runSemanticAction_352();
                break;
            case 353:
                RESULT = runSemanticAction_353();
                break;
            case 354:
                RESULT = runSemanticAction_354();
                break;
            default:
        runDefaultProdAction();
                 break;
            }
            return RESULT;
        }
        public java.lang.Object runSemanticAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData _terminal)
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            this._pos = _pos;
            this._terminal = _terminal;
            this._specialAttributes = new edu.umn.cs.melt.copper.runtime.engines.semantics.SpecialParserAttributes(virtualLocation);
            String lexeme = _terminal.lexeme;
            java.lang.Object RESULT = null;
            switch(_terminal.firstTerm)
            {
            case 1:
                RESULT = runSemanticAction_1(lexeme);
                break;
            case 2:
                RESULT = runSemanticAction_2(lexeme);
                break;
            case 3:
                RESULT = runSemanticAction_3(lexeme);
                break;
            case 4:
                RESULT = runSemanticAction_4(lexeme);
                break;
            case 5:
                RESULT = runSemanticAction_5(lexeme);
                break;
            case 6:
                RESULT = runSemanticAction_6(lexeme);
                break;
            case 7:
                RESULT = runSemanticAction_7(lexeme);
                break;
            case 8:
                RESULT = runSemanticAction_8(lexeme);
                break;
            case 9:
                RESULT = runSemanticAction_9(lexeme);
                break;
            case 10:
                RESULT = runSemanticAction_10(lexeme);
                break;
            case 11:
                RESULT = runSemanticAction_11(lexeme);
                break;
            case 12:
                RESULT = runSemanticAction_12(lexeme);
                break;
            case 13:
                RESULT = runSemanticAction_13(lexeme);
                break;
            case 14:
                RESULT = runSemanticAction_14(lexeme);
                break;
            case 15:
                RESULT = runSemanticAction_15(lexeme);
                break;
            case 16:
                RESULT = runSemanticAction_16(lexeme);
                break;
            case 17:
                RESULT = runSemanticAction_17(lexeme);
                break;
            case 18:
                RESULT = runSemanticAction_18(lexeme);
                break;
            case 19:
                RESULT = runSemanticAction_19(lexeme);
                break;
            case 20:
                RESULT = runSemanticAction_20(lexeme);
                break;
            case 21:
                RESULT = runSemanticAction_21(lexeme);
                break;
            case 22:
                RESULT = runSemanticAction_22(lexeme);
                break;
            case 23:
                RESULT = runSemanticAction_23(lexeme);
                break;
            case 24:
                RESULT = runSemanticAction_24(lexeme);
                break;
            case 25:
                RESULT = runSemanticAction_25(lexeme);
                break;
            case 26:
                RESULT = runSemanticAction_26(lexeme);
                break;
            case 27:
                RESULT = runSemanticAction_27(lexeme);
                break;
            case 28:
                RESULT = runSemanticAction_28(lexeme);
                break;
            case 29:
                RESULT = runSemanticAction_29(lexeme);
                break;
            case 30:
                RESULT = runSemanticAction_30(lexeme);
                break;
            case 31:
                RESULT = runSemanticAction_31(lexeme);
                break;
            case 32:
                RESULT = runSemanticAction_32(lexeme);
                break;
            case 33:
                RESULT = runSemanticAction_33(lexeme);
                break;
            case 34:
                RESULT = runSemanticAction_34(lexeme);
                break;
            case 35:
                RESULT = runSemanticAction_35(lexeme);
                break;
            case 36:
                RESULT = runSemanticAction_36(lexeme);
                break;
            case 37:
                RESULT = runSemanticAction_37(lexeme);
                break;
            case 38:
                RESULT = runSemanticAction_38(lexeme);
                break;
            case 39:
                RESULT = runSemanticAction_39(lexeme);
                break;
            case 40:
                RESULT = runSemanticAction_40(lexeme);
                break;
            case 41:
                RESULT = runSemanticAction_41(lexeme);
                break;
            case 42:
                RESULT = runSemanticAction_42(lexeme);
                break;
            case 43:
                RESULT = runSemanticAction_43(lexeme);
                break;
            case 44:
                RESULT = runSemanticAction_44(lexeme);
                break;
            case 45:
                RESULT = runSemanticAction_45(lexeme);
                break;
            case 46:
                RESULT = runSemanticAction_46(lexeme);
                break;
            case 47:
                RESULT = runSemanticAction_47(lexeme);
                break;
            case 48:
                RESULT = runSemanticAction_48(lexeme);
                break;
            case 49:
                RESULT = runSemanticAction_49(lexeme);
                break;
            case 50:
                RESULT = runSemanticAction_50(lexeme);
                break;
            case 51:
                RESULT = runSemanticAction_51(lexeme);
                break;
            case 52:
                RESULT = runSemanticAction_52(lexeme);
                break;
            case 53:
                RESULT = runSemanticAction_53(lexeme);
                break;
            case 54:
                RESULT = runSemanticAction_54(lexeme);
                break;
            case 55:
                RESULT = runSemanticAction_55(lexeme);
                break;
            case 56:
                RESULT = runSemanticAction_56(lexeme);
                break;
            case 57:
                RESULT = runSemanticAction_57(lexeme);
                break;
            case 58:
                RESULT = runSemanticAction_58(lexeme);
                break;
            case 59:
                RESULT = runSemanticAction_59(lexeme);
                break;
            case 60:
                RESULT = runSemanticAction_60(lexeme);
                break;
            case 61:
                RESULT = runSemanticAction_61(lexeme);
                break;
            case 62:
                RESULT = runSemanticAction_62(lexeme);
                break;
            case 63:
                RESULT = runSemanticAction_63(lexeme);
                break;
            case 64:
                RESULT = runSemanticAction_64(lexeme);
                break;
            case 65:
                RESULT = runSemanticAction_65(lexeme);
                break;
            case 66:
                RESULT = runSemanticAction_66(lexeme);
                break;
            case 67:
                RESULT = runSemanticAction_67(lexeme);
                break;
            case 68:
                RESULT = runSemanticAction_68(lexeme);
                break;
            case 69:
                RESULT = runSemanticAction_69(lexeme);
                break;
            case 70:
                RESULT = runSemanticAction_70(lexeme);
                break;
            case 71:
                RESULT = runSemanticAction_71(lexeme);
                break;
            case 72:
                RESULT = runSemanticAction_72(lexeme);
                break;
            case 73:
                RESULT = runSemanticAction_73(lexeme);
                break;
            case 74:
                RESULT = runSemanticAction_74(lexeme);
                break;
            case 75:
                RESULT = runSemanticAction_75(lexeme);
                break;
            case 76:
                RESULT = runSemanticAction_76(lexeme);
                break;
            case 77:
                RESULT = runSemanticAction_77(lexeme);
                break;
            case 78:
                RESULT = runSemanticAction_78(lexeme);
                break;
            case 79:
                RESULT = runSemanticAction_79(lexeme);
                break;
            case 80:
                RESULT = runSemanticAction_80(lexeme);
                break;
            case 81:
                RESULT = runSemanticAction_81(lexeme);
                break;
            case 82:
                RESULT = runSemanticAction_82(lexeme);
                break;
            case 83:
                RESULT = runSemanticAction_83(lexeme);
                break;
            case 84:
                RESULT = runSemanticAction_84(lexeme);
                break;
            case 85:
                RESULT = runSemanticAction_85(lexeme);
                break;
            case 86:
                RESULT = runSemanticAction_86(lexeme);
                break;
            case 87:
                RESULT = runSemanticAction_87(lexeme);
                break;
            case 88:
                RESULT = runSemanticAction_88(lexeme);
                break;
            case 89:
                RESULT = runSemanticAction_89(lexeme);
                break;
            case 90:
                RESULT = runSemanticAction_90(lexeme);
                break;
            case 91:
                RESULT = runSemanticAction_91(lexeme);
                break;
            case 92:
                RESULT = runSemanticAction_92(lexeme);
                break;
            case 93:
                RESULT = runSemanticAction_93(lexeme);
                break;
            case 94:
                RESULT = runSemanticAction_94(lexeme);
                break;
            case 95:
                RESULT = runSemanticAction_95(lexeme);
                break;
            case 96:
                RESULT = runSemanticAction_96(lexeme);
                break;
            case 97:
                RESULT = runSemanticAction_97(lexeme);
                break;
            case 98:
                RESULT = runSemanticAction_98(lexeme);
                break;
            case 99:
                RESULT = runSemanticAction_99(lexeme);
                break;
            case 100:
                RESULT = runSemanticAction_100(lexeme);
                break;
            case 101:
                RESULT = runSemanticAction_101(lexeme);
                break;
            case 102:
                RESULT = runSemanticAction_102(lexeme);
                break;
            case 103:
                RESULT = runSemanticAction_103(lexeme);
                break;
            case 104:
                RESULT = runSemanticAction_104(lexeme);
                break;
            case 105:
                RESULT = runSemanticAction_105(lexeme);
                break;
            case 106:
                RESULT = runSemanticAction_106(lexeme);
                break;
            case 107:
                RESULT = runSemanticAction_107(lexeme);
                break;
            case 108:
                RESULT = runSemanticAction_108(lexeme);
                break;
            case 109:
                RESULT = runSemanticAction_109(lexeme);
                break;
            case 110:
                RESULT = runSemanticAction_110(lexeme);
                break;
            case 111:
                RESULT = runSemanticAction_111(lexeme);
                break;
            case 112:
                RESULT = runSemanticAction_112(lexeme);
                break;
            default:
        runDefaultTermAction();
                 break;
            }
            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier runSemanticAction_169()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifierDominates(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier runSemanticAction_170()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifierSubmits(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier runSemanticAction_171()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifier RESULT = null;
            
RESULT = new silver.modification.impide.env_parser.PaLexerClassModifierFont(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers runSemanticAction_172()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifiersNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers runSemanticAction_173()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifiersOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner runSemanticAction_174()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifierInnersCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner runSemanticAction_175()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NILexerClassModifiersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaLexerClassModifiersInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIParser runSemanticAction_176()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIParser RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaParser(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11], _children[12], _children[13], _children[14], _children[15], _children[16], _children[17]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIParsers runSemanticAction_177()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIParsers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaParsersNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIParsers runSemanticAction_178()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIParsers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaParsersSome(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIParsersInner runSemanticAction_179()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIParsersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaParsersCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIParsersInner runSemanticAction_180()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIParsersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaParsersOne(_children[0]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier runSemanticAction_181()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierAcode(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier runSemanticAction_182()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierLayout(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier runSemanticAction_183()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierOperator(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier runSemanticAction_184()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierPrecedence(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers runSemanticAction_185()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifiersNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers runSemanticAction_186()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifiersOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiersInner runSemanticAction_187()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifierInnersCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiersInner runSemanticAction_188()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NIProductionModifiersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaProductionModifiersInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntax runSemanticAction_189()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntax RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntax runSemanticAction_190()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntax RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxSome(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl runSemanticAction_191()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxDisambig(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl runSemanticAction_192()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxLclass(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl runSemanticAction_193()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxNt(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl runSemanticAction_194()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxPattr(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl runSemanticAction_195()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxProd(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl runSemanticAction_196()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxDcl RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxTerm(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner runSemanticAction_197()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner runSemanticAction_198()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NISyntaxInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaSyntaxOne(_children[0]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_199()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierAcode(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_200()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierAssoc(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_201()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierClasses(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_202()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierDominates(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_203()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierIgnore(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_204()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierMarking(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_205()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierPrec(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_206()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierSubmits(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier runSemanticAction_207()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifier RESULT = null;
            
RESULT = new silver.modification.impide.env_parser.PaTerminalModifierFont(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers runSemanticAction_208()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers runSemanticAction_209()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiers RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner runSemanticAction_210()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifierInnersCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner runSemanticAction_211()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.NITerminalModifiersInner RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaTerminalModifiersInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIBool runSemanticAction_212()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIBool RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaFalse(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIBool runSemanticAction_213()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIBool RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTrue(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_214()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoAnno(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_215()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoAnnoInstance(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11], _children[12], _children[13]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_216()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoForward(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_217()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoFunction(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_218()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoGlobalValue(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_219()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoInherited(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_220()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoLocal(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_221()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoNonterminal(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_222()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoOccurs(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11], _children[12], _children[13]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_223()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoProdAttr(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_224()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoProduction(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_225()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoSynthesized(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_226()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDclInfoTerminal(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_227()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.modification.autocopyattr.env_parser.PaDclInfoAutoCopy(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_228()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.PaDclInfoInheritedCol(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_229()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.PaDclInfoLocalCol(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_230()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.PaDclInfoSynthesizedCol(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_231()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.modification.copper.env_parser.PaDclInfoLexerClass(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_232()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.modification.copper.env_parser.PaDclInfoParseAttr(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_233()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.modification.copper.env_parser.PaDclInfoPrefixSeparator(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_234()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.modification.impide.env_parser.PaDclInfoFont(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDclInfo runSemanticAction_235()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDclInfo RESULT = null;
            
RESULT = new silver.modification.typedecl.env_parser.PaDclInfoType(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDefs runSemanticAction_236()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDefs RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDefsNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDefs runSemanticAction_237()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDefs RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDefsOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDefsInner runSemanticAction_238()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDefsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDefsInnerCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIDefsInner runSemanticAction_239()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIDefsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaDefsInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NILocation runSemanticAction_240()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NILocation RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaLocationInfo(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11], _children[12]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIName runSemanticAction_241()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIName RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaName(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedArgTypes runSemanticAction_242()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedArgTypes RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedArgTypeCons(_children[0], _children[1], _children[2], _children[3], _children[4]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedArgTypes runSemanticAction_243()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedArgTypes RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedArgTypeNil();

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedSignature runSemanticAction_244()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedSignature RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedSignatureDcl(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedSignatureElement runSemanticAction_245()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedSignatureElement RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedSignatureElementDcl(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedSignatureElements runSemanticAction_246()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedSignatureElements RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedSignatureElementsNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedSignatureElements runSemanticAction_247()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedSignatureElements RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedSignatureElementsOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedSignatureElementsInner runSemanticAction_248()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedSignatureElementsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedSignatureElementsInnerCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamedSignatureElementsInner runSemanticAction_249()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamedSignatureElementsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamedSignatureElementsInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINames runSemanticAction_250()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINames RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamesNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINames runSemanticAction_251()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINames RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamesOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamesInner runSemanticAction_252()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamesInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamesInnerCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NINamesInner runSemanticAction_253()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NINamesInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaNamesInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRoot runSemanticAction_254()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRoot RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRoot1(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRoot runSemanticAction_255()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRoot RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRoot2(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_256()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaRootParsers(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_257()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.PaRootSyntax(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_258()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootAllDeps(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_259()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootCondBuilds(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_260()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootDeclaredName(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_261()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootDefs(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_262()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootExportedGrammars(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_263()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootGrammarSource(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_264()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootGrammarTime(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_265()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootModuleNames(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_266()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaRootOptionalGrammars(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIRootPart runSemanticAction_267()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIRootPart RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaRootFlow(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NIString runSemanticAction_268()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NIString RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaString(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITyVarDcls runSemanticAction_269()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITyVarDcls RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTyVarDclsNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITyVarDcls runSemanticAction_270()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITyVarDcls RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTyVarDclsOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITyVarDclsInner runSemanticAction_271()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITyVarDclsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTyVarDclsInnerCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITyVarDclsInner runSemanticAction_272()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITyVarDclsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTyVarDclsInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_273()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepBoolean(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_274()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepDecorated(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_275()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepFloat(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_276()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepFunction(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_277()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepInteger(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_278()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepNonterminal(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_279()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepString(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_280()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepTerminal(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_281()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepVar(_children[0]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_282()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.extension.list.env_parser.PaTypeRepList(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRep runSemanticAction_283()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRep RESULT = null;
            
RESULT = new silver.modification.ffi.env_parser.PaTypeRepForeign(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeReps runSemanticAction_284()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeReps RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepsNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeReps runSemanticAction_285()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeReps RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepsOne(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRepsInner runSemanticAction_286()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRepsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepsInnerCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.env.env_parser.NITypeRepsInner runSemanticAction_287()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.NITypeRepsInner RESULT = null;
            
RESULT = new silver.definition.env.env_parser.PaTypeRepsInnerOne(_children[0]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_288()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowAnonEq(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9], _children[10], _children[11]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_289()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowAnonInhEq(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_290()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowDef(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_291()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowExtra(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_292()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowFwd(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_293()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowFwdInh(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_294()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowImplicitFwdAffects(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_295()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowInh(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_296()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowLocalEq(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_297()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowLocalInhEq(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_298()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowNonHostSyn(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_299()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowProd(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_300()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowRefFlowDef(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_301()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowSyn(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_302()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaPatternRuleEq(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7], _children[8], _children[9]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlow runSemanticAction_303()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaSpecFlowDef(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6], _children[7]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowVertex runSemanticAction_304()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowVertex RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowInh_vertex(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowVertex runSemanticAction_305()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowVertex RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowRhs_vertex(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowVertex runSemanticAction_306()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowVertex RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowSyn_vertex(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowVertex runSemanticAction_307()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowVertex RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowanonEqV_vertex(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowVertex runSemanticAction_308()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowVertex RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowanonV_vertex(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowVertex runSemanticAction_309()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowVertex RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowlocalEqV_vertex(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowVertex runSemanticAction_310()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowVertex RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowlocalV_vertex(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowVertexes runSemanticAction_311()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowVertexes RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowsNone_vertex(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowVertexes runSemanticAction_312()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowVertexes RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowsSome_vertex(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowVertexesInner runSemanticAction_313()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowVertexesInner RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowsCons_vertex(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowVertexesInner runSemanticAction_314()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowVertexesInner RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowsOne_vertex(_children[0]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlows runSemanticAction_315()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlows RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowsNone(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlows runSemanticAction_316()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlows RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowsSome(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowsInner runSemanticAction_317()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowsInner RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowsCons(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIFlowsInner runSemanticAction_318()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIFlowsInner RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowsOne(_children[0]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIPatternVarProjection runSemanticAction_319()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIPatternVarProjection RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaPatternVarProjection(_children[0], _children[1], _children[2], _children[3], _children[4], _children[5], _children[6]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIPatternVarProjections runSemanticAction_320()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIPatternVarProjections RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowsNone_proj(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIPatternVarProjections runSemanticAction_321()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIPatternVarProjections RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowsSome_proj(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIPatternVarProjectionsInner runSemanticAction_322()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIPatternVarProjectionsInner RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowsCons_proj(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIPatternVarProjectionsInner runSemanticAction_323()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIPatternVarProjectionsInner RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaFlowsOne_proj(_children[0]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIVertexType runSemanticAction_324()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIVertexType RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaanonVertexType(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIVertexType runSemanticAction_325()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIVertexType RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PaforwardVertexType(_children[0]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIVertexType runSemanticAction_326()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIVertexType RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PalhsVertexType(_children[0]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIVertexType runSemanticAction_327()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIVertexType RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.PalocalVertexType(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.flow.env_parser.NIVertexType runSemanticAction_328()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.NIVertexType RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.ParhsVertexType(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.regex.NRegex runSemanticAction_329()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegex RESULT = null;
            
RESULT = new silver.definition.regex.PregexChoice(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.regex.NRegex runSemanticAction_330()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegex RESULT = null;
            
RESULT = new silver.definition.regex.PregexEpsilon();

            return RESULT;
        }
        public silver.definition.regex.NRegex runSemanticAction_331()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegex RESULT = null;
            
RESULT = new silver.definition.regex.PregexSeq(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexChar runSemanticAction_332()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexChar RESULT = null;
            
RESULT = new silver.definition.regex.PregexChar(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexChar runSemanticAction_333()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexChar RESULT = null;
            
RESULT = new silver.definition.regex.PregexEscapedChar(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexCharSet runSemanticAction_334()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexCharSet RESULT = null;
            
RESULT = new silver.definition.regex.PregexCharSetOne(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexCharSet runSemanticAction_335()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexCharSet RESULT = null;
            
RESULT = new silver.definition.regex.PregexCharSetSnoc(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.regex.NRegexCharSetItem runSemanticAction_336()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexCharSetItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexSetChar(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexCharSetItem runSemanticAction_337()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexCharSetItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexSetRange(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.regex.NRegexItem runSemanticAction_338()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexCharItem(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexItem runSemanticAction_339()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexGroup(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.regex.NRegexItem runSemanticAction_340()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexSet(_children[0], _children[1], _children[2]);

            return RESULT;
        }
        public silver.definition.regex.NRegexItem runSemanticAction_341()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexSetInverted(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.definition.regex.NRegexItem runSemanticAction_342()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexItem RESULT = null;
            
RESULT = new silver.definition.regex.PregexWildcard(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexRepetition runSemanticAction_343()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexRepetition RESULT = null;
            
RESULT = new silver.definition.regex.PregexKleene(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.regex.NRegexRepetition runSemanticAction_344()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexRepetition RESULT = null;
            
RESULT = new silver.definition.regex.PregexOnce(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexRepetition runSemanticAction_345()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexRepetition RESULT = null;
            
RESULT = new silver.definition.regex.PregexOptional(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.regex.NRegexRepetition runSemanticAction_346()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexRepetition RESULT = null;
            
RESULT = new silver.definition.regex.PregexPlus(_children[0], _children[1]);

            return RESULT;
        }
        public silver.definition.regex.NRegexSeq runSemanticAction_347()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexSeq RESULT = null;
            
RESULT = new silver.definition.regex.PregexSeqOne(_children[0]);

            return RESULT;
        }
        public silver.definition.regex.NRegexSeq runSemanticAction_348()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.NRegexSeq RESULT = null;
            
RESULT = new silver.definition.regex.PregexSeqSnoc(_children[0], _children[1]);

            return RESULT;
        }
        public silver.modification.collection.env_parser.NIOperation runSemanticAction_349()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.NIOperation RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.PoperationBAnd(_children[0]);

            return RESULT;
        }
        public silver.modification.collection.env_parser.NIOperation runSemanticAction_350()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.NIOperation RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.PoperationBOr(_children[0]);

            return RESULT;
        }
        public silver.modification.collection.env_parser.NIOperation runSemanticAction_351()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.NIOperation RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.PoperationNameFun(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.modification.collection.env_parser.NIOperation runSemanticAction_352()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.NIOperation RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.PoperationNameProd(_children[0], _children[1], _children[2], _children[3]);

            return RESULT;
        }
        public silver.modification.collection.env_parser.NIOperation runSemanticAction_353()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.NIOperation RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.PoperationPlusPlusLst(_children[0]);

            return RESULT;
        }
        public silver.modification.collection.env_parser.NIOperation runSemanticAction_354()
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.NIOperation RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.PoperationPlusPlusStr(_children[0]);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TAcodeTerm runSemanticAction_1(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TAcodeTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TAcodeTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TAssocTerm runSemanticAction_2(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TAssocTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TAssocTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TClassesTerm runSemanticAction_3(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TClassesTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TClassesTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TDisTerm runSemanticAction_4(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TDisTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TDisTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TDomTerm runSemanticAction_5(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TDomTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TDomTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TIgnoreTerm runSemanticAction_6(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TIgnoreTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TIgnoreTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TLClassTerm runSemanticAction_7(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TLClassTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TLClassTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TLayoutTerm runSemanticAction_8(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TLayoutTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TLayoutTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TMarkingTerm runSemanticAction_9(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TMarkingTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TMarkingTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TOperTerm runSemanticAction_10(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TOperTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TOperTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TParAttr runSemanticAction_11(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TParAttr RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TParAttr(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TParserTerm runSemanticAction_12(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TParserTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TParserTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TParsersTerm runSemanticAction_13(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TParsersTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TParsersTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TPrecTerm runSemanticAction_14(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TPrecTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TPrecTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TSubTerm runSemanticAction_15(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TSubTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TSubTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.concrete_syntax.ast.env_parser.TSyntaxTerm runSemanticAction_16(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.concrete_syntax.ast.env_parser.TSyntaxTerm RESULT = null;
            
RESULT = new silver.definition.concrete_syntax.ast.env_parser.TSyntaxTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TAllDepsTerm runSemanticAction_17(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TAllDepsTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TAllDepsTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TAnnoAt_t runSemanticAction_18(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TAnnoAt_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TAnnoAt_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TAnno_t runSemanticAction_19(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TAnno_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TAnno_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TBooleanTerm runSemanticAction_20(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TBooleanTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TBooleanTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TComma_t runSemanticAction_21(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TComma_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TComma_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TCondBuildTerm runSemanticAction_22(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TCondBuildTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TCondBuildTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TDeclaredNameTerm runSemanticAction_23(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TDeclaredNameTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TDeclaredNameTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TDecoratedTerm runSemanticAction_24(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TDecoratedTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TDecoratedTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TDefaultTerm runSemanticAction_25(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TDefaultTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TDefaultTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TDefsTerm runSemanticAction_26(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TDefsTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TDefsTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TEq runSemanticAction_27(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TEq RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TEq(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TEscapedStringTerm runSemanticAction_28(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TEscapedStringTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TEscapedStringTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TExportedGrammarsTerm runSemanticAction_29(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TExportedGrammarsTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TExportedGrammarsTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TF_t runSemanticAction_30(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TF_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TF_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TFloatTerm runSemanticAction_31(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TFloatTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TFloatTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TForwardTerm runSemanticAction_32(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TForwardTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TForwardTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TFunctionTerm runSemanticAction_33(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TFunctionTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TFunctionTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TGlobTerm runSemanticAction_34(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TGlobTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TGlobTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TGrammarSourceTerm runSemanticAction_35(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TGrammarSourceTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TGrammarSourceTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TGrammarTimeTerm runSemanticAction_36(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TGrammarTimeTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TGrammarTimeTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TIOTerm runSemanticAction_37(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TIOTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TIOTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TITyVar runSemanticAction_38(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TITyVar RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TITyVar(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TId_t runSemanticAction_39(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TId_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TId_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TInheritedTerm runSemanticAction_40(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TInheritedTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TInheritedTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TIntegerTerm runSemanticAction_41(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TIntegerTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TIntegerTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TLB_t runSemanticAction_42(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TLB_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TLB_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TLParent_t runSemanticAction_43(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TLParent_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TLParent_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TLocalTerm runSemanticAction_44(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TLocalTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TLocalTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TModuleNamesTerm runSemanticAction_45(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TModuleNamesTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TModuleNamesTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TNonterminalTerm runSemanticAction_46(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TNonterminalTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TNonterminalTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TNum_t runSemanticAction_47(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TNum_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TNum_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TOccursTerm runSemanticAction_48(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TOccursTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TOccursTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TOptionalGrammarsTerm runSemanticAction_49(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TOptionalGrammarsTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TOptionalGrammarsTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TProdAttrTerm runSemanticAction_50(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TProdAttrTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TProdAttrTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TProductionTerm runSemanticAction_51(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TProductionTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TProductionTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TRB_t runSemanticAction_52(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TRB_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TRB_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TRParent_t runSemanticAction_53(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TRParent_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TRParent_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TRegExprDelim runSemanticAction_54(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TRegExprDelim RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TRegExprDelim(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TSemi runSemanticAction_55(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TSemi RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TSemi(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TSignatureElementTerm runSemanticAction_56(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TSignatureElementTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TSignatureElementTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TSignatureTerm runSemanticAction_57(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TSignatureTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TSignatureTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TSigturnstile runSemanticAction_58(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TSigturnstile RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TSigturnstile(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TStringTerm runSemanticAction_59(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TStringTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TStringTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TSynthesizedTerm runSemanticAction_60(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TSynthesizedTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TSynthesizedTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TT_t runSemanticAction_61(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TT_t RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TT_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TTerminalTerm runSemanticAction_62(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TTerminalTerm RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TTerminalTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.env.env_parser.TWS runSemanticAction_63(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.env.env_parser.TWS RESULT = null;
            
RESULT = new silver.definition.env.env_parser.TWS(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TAnonEqVTerm runSemanticAction_64(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TAnonEqVTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TAnonEqVTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TAnonInhTerm runSemanticAction_65(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TAnonInhTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TAnonInhTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TAnonTerm runSemanticAction_66(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TAnonTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TAnonTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TAnonVTerm runSemanticAction_67(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TAnonVTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TAnonVTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TDefTerm runSemanticAction_68(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TDefTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TDefTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TExtraTerm runSemanticAction_69(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TExtraTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TExtraTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TFlowTerm runSemanticAction_70(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TFlowTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TFlowTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TFwdInhTerm runSemanticAction_71(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TFwdInhTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TFwdInhTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TImplicitFwdAffectsTerm runSemanticAction_72(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TImplicitFwdAffectsTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TImplicitFwdAffectsTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TLhsFlow runSemanticAction_73(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TLhsFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TLhsFlow(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TLhsInhVTerm runSemanticAction_74(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TLhsInhVTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TLhsInhVTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TLhsSynVTerm runSemanticAction_75(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TLhsSynVTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TLhsSynVTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TLocalEqVTerm runSemanticAction_76(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TLocalEqVTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TLocalEqVTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TLocalInhTerm runSemanticAction_77(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TLocalInhTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TLocalInhTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TLocalTerm runSemanticAction_78(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TLocalTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TLocalTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TLocalVTerm runSemanticAction_79(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TLocalVTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TLocalVTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TNonHostSynTerm runSemanticAction_80(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TNonHostSynTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TNonHostSynTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TNtRefDefTerm runSemanticAction_81(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TNtRefDefTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TNtRefDefTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TPatRulEqTerm runSemanticAction_82(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TPatRulEqTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TPatRulEqTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TRhsFlow runSemanticAction_83(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TRhsFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TRhsFlow(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TRhsVTerm runSemanticAction_84(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TRhsVTerm RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TRhsVTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.flow.env_parser.TSpecFlow runSemanticAction_85(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.flow.env_parser.TSpecFlow RESULT = null;
            
RESULT = new silver.definition.flow.env_parser.TSpecFlow(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TChoice_t runSemanticAction_86(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TChoice_t RESULT = null;
            
RESULT = new silver.definition.regex.TChoice_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TEscapedChar_t runSemanticAction_87(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TEscapedChar_t RESULT = null;
            
RESULT = new silver.definition.regex.TEscapedChar_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TKleene_t runSemanticAction_88(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TKleene_t RESULT = null;
            
RESULT = new silver.definition.regex.TKleene_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TOptional_t runSemanticAction_89(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TOptional_t RESULT = null;
            
RESULT = new silver.definition.regex.TOptional_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TPlus_t runSemanticAction_90(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TPlus_t RESULT = null;
            
RESULT = new silver.definition.regex.TPlus_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRange_t runSemanticAction_91(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRange_t RESULT = null;
            
RESULT = new silver.definition.regex.TRange_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexChar_t runSemanticAction_92(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexChar_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexChar_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexLBrack_t runSemanticAction_93(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexLBrack_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexLBrack_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexLParen_t runSemanticAction_94(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexLParen_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexLParen_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexNot_t runSemanticAction_95(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexNot_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexNot_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexRBrack_t runSemanticAction_96(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexRBrack_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexRBrack_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexRParen_t runSemanticAction_97(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexRParen_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexRParen_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.definition.regex.TRegexWildcard_t runSemanticAction_98(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.definition.regex.TRegexWildcard_t RESULT = null;
            
RESULT = new silver.definition.regex.TRegexWildcard_t(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.autocopyattr.env_parser.TAutoCopyTerm runSemanticAction_99(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.autocopyattr.env_parser.TAutoCopyTerm RESULT = null;
            
RESULT = new silver.modification.autocopyattr.env_parser.TAutoCopyTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.collection.env_parser.TBOrTerm runSemanticAction_100(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.TBOrTerm RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.TBOrTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.collection.env_parser.TInhColTerm runSemanticAction_101(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.TInhColTerm RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.TInhColTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.collection.env_parser.TLocColTerm runSemanticAction_102(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.TLocColTerm RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.TLocColTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.collection.env_parser.TPAndTerm runSemanticAction_103(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.TPAndTerm RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.TPAndTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.collection.env_parser.TPlusPlusLst runSemanticAction_104(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.TPlusPlusLst RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.TPlusPlusLst(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.collection.env_parser.TPlusPlusStr runSemanticAction_105(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.TPlusPlusStr RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.TPlusPlusStr(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.collection.env_parser.TSynColTerm runSemanticAction_106(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.collection.env_parser.TSynColTerm RESULT = null;
            
RESULT = new silver.modification.collection.env_parser.TSynColTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.copper.env_parser.TLexerClassTerm runSemanticAction_107(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.copper.env_parser.TLexerClassTerm RESULT = null;
            
RESULT = new silver.modification.copper.env_parser.TLexerClassTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.copper.env_parser.TParseAttrTerm runSemanticAction_108(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.copper.env_parser.TParseAttrTerm RESULT = null;
            
RESULT = new silver.modification.copper.env_parser.TParseAttrTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.copper.env_parser.TPrefixSeperatorTerm runSemanticAction_109(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.copper.env_parser.TPrefixSeperatorTerm RESULT = null;
            
RESULT = new silver.modification.copper.env_parser.TPrefixSeperatorTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.ffi.env_parser.TFTTerm runSemanticAction_110(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.ffi.env_parser.TFTTerm RESULT = null;
            
RESULT = new silver.modification.ffi.env_parser.TFTTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.impide.env_parser.TFontTerm runSemanticAction_111(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.impide.env_parser.TFontTerm RESULT = null;
            
RESULT = new silver.modification.impide.env_parser.TFontTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public silver.modification.typedecl.env_parser.TTypeTerm runSemanticAction_112(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            silver.modification.typedecl.env_parser.TTypeTerm RESULT = null;
            
RESULT = new silver.modification.typedecl.env_parser.TTypeTerm(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());
  tokenList.add(RESULT);

            return RESULT;
        }
        public int runDisambiguationAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData match)
        throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            String lexeme = match.lexeme;
            if(match.terms.equals(disambiguationGroups[0])) return disambiguate_0(lexeme);
            else if(match.terms.equals(disambiguationGroups[1])) return disambiguate_1(lexeme);
            else return -1;
        }
        public int disambiguate_0(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unused") final int silver_definition_env_env_parser_RegExprDelim = 54;
            @SuppressWarnings("unused") final int silver_definition_regex_RegexChar_t = 92;
            
return (Integer)silver_definition_env_env_parser_RegExprDelim;

        }
        public int disambiguate_1(final String lexeme)
        throws edu.umn.cs.melt.copper.runtime.logging.CopperParserException
        {
            @SuppressWarnings("unused") final int silver_definition_env_env_parser_WS = 63;
            @SuppressWarnings("unused") final int silver_definition_regex_RegexChar_t = 92;
            
return (Integer)silver_definition_env_env_parser_WS;

        }
    }
    public Semantics semantics;
    public java.lang.Object runSemanticAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,java.lang.Object[] _children,int _prod)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
        return semantics.runSemanticAction(_pos,_children,_prod);
    }
    public java.lang.Object runSemanticAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData _terminal)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
        return semantics.runSemanticAction(_pos,_terminal);
    }
    public int runDisambiguationAction(edu.umn.cs.melt.copper.runtime.io.InputPosition _pos,edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData matches)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
        return semantics.runDisambiguationAction(_pos,matches);
    }
    public edu.umn.cs.melt.copper.runtime.engines.semantics.SpecialParserAttributes getSpecialAttributes()
    {
        return semantics.getSpecialAttributes();
    }
    public void startEngine(edu.umn.cs.melt.copper.runtime.io.InputPosition initialPos)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
         super.startEngine(initialPos);
         semantics = new Semantics();
    }

public static final byte[] symbolNamesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\255\133\333\222\343\266" +
"\021\145\222\162\176\040\317\316\075\166\256\033\173\343\134\174" +
"\111\312\032\151\146\254\130\073\232\222\124\362\103\036\124\130" +
"\022\322\320\246\010\055\010\356\150\222\137\112\176\046\077\221" +
"\312\103\376\041\100\003\240\110\011\024\032\030\075\170\134\245" +
"\355\323\004\372\206\356\106\343\237\377\113\336\251\171\362\275" +
"\277\115\276\046\157\311\213\202\224\233\027\163\301\363\162\363" +
"\351\277\376\275\374\317\177\337\375\307\355\267\223\144\277\113" +
"\222\157\245\042\371\316\365\364\106\044\237\124\171\361\226\362" +
"\125\106\327\171\231\213\234\225\253\224\225\051\247\202\256\252" +
"\247\122\220\375\212\124\142\105\313\267\253\035\341\225\244\034" +
"\244\054\243\013\312\267\121\340\252\142\251\006\177\026\014\036" +
"\026\244\252\150\245\341\177\012\206\217\362\170\050\333\152\350" +
"\247\301\320\361\246\144\234\306\242\047\260\347\150\064\171\142" +
"\265\210\025\367\053\302\277\221\266\243\341\037\007\303\247\073" +
"\312\143\345\175\117\370\100\010\036\263\347\173\370\137\354\236" +
"\065\272\212\335\363\075\247\151\354\236\347\365\353\130\075\317" +
"\341\127\215\376\365\051\132\121\266\175\260\050\106\164\147\366" +
"\370\013\077\171\131\262\201\130\011\221\274\217\242\125\224\376" +
"\105\134\061\126\120\122\352\105\374\334\113\076\144\333\055\121" +
"\234\137\040\110\313\354\252\316\213\114\363\376\320\013\030\321" +
"\264\040\234\146\167\144\153\374\324\377\021\211\141\234\010\232" +
"\141\205\076\242\153\122\027\002\053\164\111\156\024\364\023\057" +
"\355\365\033\221\274\364\123\125\051\331\321\114\237\007\232\365" +
"\357\375\240\375\216\161\271\313\133\116\244\374\255\137\374\324" +
"\213\273\121\252\372\245\237\254\140\104\140\045\170\303\370\043" +
"\341\106\340\277\361\223\327\145\252\176\307\112\374\266\140\306" +
"\377\374\262\064\342\230\263\232\247\306\144\076\300\202\026\271" +
"\265\062\277\073\215\247\150\312\305\323\222\310\170\371\063\077" +
"\145\206\163\243\161\371\100\171\036\140\341\343\122\320\215\215" +
"\274\376\165\114\256\160\066\062\221\041\231\226\002\111\314\122" +
"\122\140\025\362\212\145\165\101\225\327\127\130\310\035\223\233" +
"\344\333\274\264\137\171\317\017\251\267\152\355\277\362\022\116" +
"\323\264\266\036\346\367\314\351\116\375\112\212\256\147\372\335" +
"\342\236\263\114\035\255\232\376\267\050\372\272\345\110\176\305" +
"\316\220\212\235\035\024\353\137\366\214\156\144\054\342\043\132" +
"\344\250\105\314\351\066\307\210\161\236\157\112\042\152\116\257" +
"\013\272\225\253\301\306\377\006\207\225\273\004\110\362\262\022" +
"\171\101\061\326\320\216\323\176\273\124\011\300\003\255\362\277" +
"\133\167\365\207\350\005\116\360\213\216\271\373\017\243\257\346" +
"\116\256\353\202\075\166\023\005\126\136\277\131\366\112\317\105" +
"\057\043\222\246\167\330\226\213\136\023\073\104\355\042\136\366" +
"\236\023\307\324\362\150\106\163\276\336\013\116\320\213\226\307" +
"\341\143\157\264\075\041\176\314\032\201\070\222\315\143\362\361" +
"\166\127\344\151\056\044\154\260\136\323\124\364\347\177\307\320" +
"\311\103\245\126\206\322\223\244\225\253\302\353\125\322\113\333" +
"\135\366\272\335\011\275\012\360\215\341\140\001\215\240\020\052" +
"\153\035\041\010\055\000\365\262\327\115\217\311\345\371\361\005" +
"\253\204\334\062\172\003\167\142\106\327\215\315\041\000\367\104" +
"\314\152\051\043\264\176\147\126\277\010\023\225\264\113\264\075" +
"\317\167\064\325\234\177\170\112\314\145\262\260\137\015\037\130" +
"\236\122\025\212\034\061\135\223\230\304\165\370\100\270\242\353" +
"\145\365\145\101\151\011\254\176\334\107\142\017\115\105\364\375" +
"\076\242\373\242\256\024\301\017\372\010\146\244\334\300\167\034" +
"\321\320\120\250\277\166\301\275\033\003\252\311\025\047\351\067" +
"\010\072\070\055\317\356\016\350\356\230\360\063\233\041\077\072" +
"\153\076\352\310\100\133\164\137\311\152\053\225\271\271\242\264" +
"\261\150\313\262\174\235\247\004\150\111\055\130\312\166\117\104" +
"\146\036\235\310\053\177\037\312\337\273\245\132\007\232\262\242" +
"\240\151\163\324\330\362\161\152\062\230\217\360\030\031\005\206" +
"\254\010\206\111\027\157\140\057\361\260\373\101\231\165\223\071" +
"\014\110\332\236\372\157\122\211\050\334\134\365\114\002\366\046" +
"\003\221\117\044\273\035\355\250\154\102\367\224\267\272\122\277" +
"\303\301\240\261\162\110\074\077\106\242\270\264\267\375\234\312" +
"\237\211\140\274\033\322\072\330\365\072\357\234\216\213\156\274" +
"\354\320\346\333\135\236\321\156\155\151\123\277\017\134\000\361" +
"\044\003\020\115\213\116\142\044\177\323\220\167\346\213\301\154" +
"\041\222\121\170\157\360\040\314\127\360\075\052\325\167\175\011" +
"\066\225\110\376\172\021\076\343\262\124\213\012\157\147\215\165" +
"\053\055\246\213\146\240\162\017\177\211\306\232\165\107\350\344" +
"\120\361\074\113\047\247\154\342\164\342\340\023\257\023\335\050" +
"\214\152\143\153\350\050\055\104\362\347\130\264\131\370\125\070" +
"\336\226\041\007\225\014\237\317\104\052\344\213\013\160\061\273" +
"\362\267\002\306\252\351\211\351\105\215\245\230\307\345\232\241" +
"\230\252\126\041\246\242\004\102\263\126\177\165\076\126\231\255" +
"\372\007\324\032\124\023\005\323\115\000\302\154\300\067\052\172" +
"\126\230\312\126\043\232\162\133\044\177\014\205\230\312\336\351" +
"\057\050\144\345\264\170\034\324\310\033\321\300\203\076\024\252" +
"\317\006\224\170\243\233\061\206\352\307\000\241\214\236\002\323" +
"\217\037\353\376\004\312\356\240\065\051\055\032\247\357\206\332" +
"\354\020\341\055\312\230\146\164\207\332\244\241\255\120\326\152" +
"\211\373\265\170\122\146\243\053\145\240\134\112\266\164\357\134" +
"\313\031\172\145\047\037\005\042\314\026\034\232\165\302\052\374" +
"\036\054\153\207\143\236\320\313\332\124\120\136\112\015\313\143" +
"\355\153\235\217\342\332\027\056\244\333\061\161\120\263\150\314" +
"\046\265\010\225\055\210\344\335\263\165\220\110\176\344\055\011" +
"\235\275\271\043\232\071\165\073\341\051\331\130\320\255\357\253" +
"\232\346\174\005\047\255\234\212\134\253\243\267\302\006\312\071" +
"\175\023\126\165\115\041\171\007\316\337\205\114\371\136\044\257" +
"\202\217\135\162\232\233\216\230\072\207\205\362\206\057\057\301" +
"\157\136\277\336\346\355\150\357\051\032\034\054\124\035\041\222" +
"\361\045\126\123\335\261\222\306\044\050\056\136\123\305\352\356" +
"\022\254\300\157\252\041\070\337\105\264\250\075\021\026\030\236" +
"\320\022\133\144\204\247\244\006\152\304\034\217\237\263\355\263" +
"\360\132\224\341\223\012\026\017\222\213\260\270\323\272\002\006" +
"\173\142\152\024\007\057\075\171\042\222\311\045\230\115\115\371" +
"\037\145\302\247\354\324\220\206\054\347\313\364\122\202\213\167" +
"\125\007\257\130\127\075\145\365\114\127\355\053\074\141\201\021" +
"\366\252\113\100\055\252\150\270\366\266\317\143\341\243\274\042" +
"\333\327\371\046\246\263\140\130\114\122\025\275\142\146\337\254" +
"\004\104\124\270\320\140\225\313\360\147\310\117\051\365\031\360" +
"\330\261\052\003\327\266\030\336\174\060\160\260\274\333\160\364" +
"\161\305\156\002\335\045\070\251\161\306\250\050\162\314\311\314" +
"\066\106\245\062\307\274\132\211\121\104\114\072\346\246\007\030" +
"\057\262\111\063\121\050\222\233\347\363\122\121\374\042\213\152" +
"\322\076\153\227\276\264\357\230\201\116\372\056\140\115\346\034" +
"\271\200\160\364\051\022\161\370\236\050\277\165\206\134\300\062" +
"\133\047\210\277\045\102\156\110\121\121\114\217\203\054\170\115" +
"\061\323\014\304\064\326\324\234\042\146\074\244\115\077\226\062" +
"\040\220\064\370\273\030\026\147\146\326\060\123\210\015\304\314" +
"\255\071\113\374\036\214\232\135\043\305\222\024\112\012\376\361" +
"\065\013\153\106\274\060\023\057\026\004\067\336\041\213\153\315" +
"\114\141\232\056\026\246\307\241\102\044\147\107\233\016\167\122" +
"\070\114\035\054\357\326\270\115\310\002\027\215\030\076\163\205" +
"\232\276\113\322\306\010\315\145\351\041\171\101\224\337\047\272" +
"\036\252\026\364\047\341\014\100\357\000\376\074\034\334\022\030" +
"\260\160\136\022\237\136\377\065\337\156\252\306\103\233\011\011" +
"\155\356\035\173\312\372\063\110\173\367\110\114\361\341\274\374" +
"\165\234\017\215\357\253\143\301\331\051\161\335\047\066\106\002" +
"\175\046\177\047\223\250\166\276\076\060\374\055\122\040\206\260" +
"\213\161\100\173\117\240\303\076\046\064\130\004\174\002\001\260" +
"\227\013\330\113\016\242\057\030\020\241\255\175\301\240\327\217" +
"\360\317\066\350\056\057\120\001\244\333\357\207\173\061\107\111" +
"\160\036\145\156\011\000\354\110\252\121\140\143\003\216\244\032" +
"\007\007\225\015\142\321\055\073\161\024\145\001\074\140\031\376" +
"\313\004\140\141\266\214\360\021\240\006\326\210\063\373\160\257" +
"\242\067\204\160\225\003\004\233\323\250\113\226\017\321\224\057" +
"\243\112\105\163\221\123\301\015\147\104\261\246\360\366\246\030" +
"\221\123\051\162\363\246\004\045\147\105\337\074\322\250\120\136" +
"\255\040\355\147\032\050\345\153\220\272\034\375\003\216\370\370" +
"\265\003\052\016\050\140\347\075\000\052\342\264\120\352\101\000" +
"\032\323\232\131\107\157\353\170\124\034\065\006\011\110\175\231" +
"\345\277\015\044\366\066\020\341\062\315\375\236\166\142\304\141" +
"\321\040\300\307\020\131\132\367\012\121\073\063\102\223\135\030" +
"\066\152\230\073\102\363\236\011\145\315\006\322\274\041\102\112" +
"\001\100\360\144\006\145\056\026\320\324\023\370\315\230\247\034" +
"\110\141\003\044\064\313\067\060\153\070\370\375\034\222\150\377" +
"\125\271\305\300\323\030\033\311\350\136\320\262\122\324\105\176" +
"\134\256\152\362\111\256\306\357\234\351\336\321\230\131\043\144" +
"\306\251\074\326\102\024\151\354\037\021\137\055\000\054\022\145" +
"\307\255\133\153\155\375\170\243\154\031\077\342\156\224\250\010" +
"\241\147\371\121\363\317\015\275\054\107\024\004\061\104\014\020" +
"\031\306\121\343\330\100\014\103\367\170\336\067\217\031\176\257" +
"\172\352\336\071\324\346\244\077\035\273\307\057\014\076\204\030" +
"\370\006\142\063\030\357\364\244\176\200\121\003\026\163\030\137" +
"\307\235\041\012\243\073\316\330\057\314\350\272\321\067\126\116" +
"\260\034\214\361\231\101\200\231\074\101\325\256\061\242\265\043" +
"\354\260\040\354\046\244\124\127\157\315\144\007\172\343\017\125" +
"\060\106\156\274\301\070\172\131\116\014\321\017\157\032\234\043" +
"\236\364\342\016\050\107\346\341\104\025\346\271\106\003\104\214" +
"\255\034\200\341\253\204\240\032\216\122\267\112\341\050\025\132" +
"\033\224\043\302\272\121\323\326\002\261\021\315\234\025\150\162" +
"\175\113\206\046\327\147\004\326\241\365\071\204\230\366\041\356" +
"\151\037\254\165\153\135\356\044\062\000\003\232\014\304\200\036" +
"\065\006\173\204\201\026\361\237\001\347\151\115\021\141\274\140" +
"\255\133\307\155\030\146\165\305\103\325\206\140\314\122\373\132" +
"\340\167\170\367\073\275\117\141\270\036\126\122\257\174\316\114" +
"\076\301\337\353\235\374\347\263\003\110\274\031\100\352\235\170" +
"\342\207\071\253\336\001\052\375\265\303\263\242\063\303\121\274" +
"\065\153\005\166\177\236\251\241\234\227\352\126\360\374\156\045" +
"\231\376\166\357\023\034\113\006\357\215\174\164\212\227\036\364" +
"\352\175\037\004\177\157\071\253\167\176\021\237\233\077\263\044" +
"\343\022\342\130\346\133\232\175\035\344\263\022\375\200\313\247" +
"\333\051\134\301\234\377\242\255\170\175\274\324\223\031\337\252" +
"\244\271\201\342\175\332\174\243\225\336\323\236\166\065\307\231" +
"\035\214\273\032\224\331\341\114\015\102\116\171\120\063\277\001" +
"\252\016\202\254\014\173\056\075\021\140\235\326\005\134\104\064" +
"\350\316\033\247\347\060\220\265\343\377\001\167\166\012\351\362" +
"\110\000\000"
});

public static final byte[] symbolDisplayNamesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\255\133\353\222\333\266" +
"\025\246\063\323\047\350\357\064\367\346\132\327\331\272\215\263" +
"\166\062\331\225\166\067\152\344\325\216\264\243\374\350\114\064" +
"\060\011\151\231\120\004\015\202\136\155\373\112\355\313\344\045" +
"\062\375\321\167\050\156\204\110\021\024\016\040\375\260\075\043" +
"\177\337\041\160\156\300\001\016\376\375\277\350\167\025\215\176" +
"\377\217\361\317\350\015\172\234\241\174\365\170\306\150\232\257" +
"\236\377\347\327\371\157\377\175\373\137\127\157\105\321\246\210" +
"\242\107\061\213\036\175\310\242\323\062\315\336\140\172\232\340" +
"\145\232\247\054\045\371\151\114\362\230\142\206\027\345\103\316" +
"\320\346\024\225\354\024\347\157\026\005\242\045\107\236\305\044" +
"\301\267\230\256\203\310\145\111\142\105\176\341\115\036\144\250" +
"\054\161\251\350\317\274\351\303\064\234\112\326\212\372\334\233" +
"\072\132\345\204\342\120\366\130\316\071\230\215\036\110\305\102" +
"\325\375\022\321\137\270\347\050\372\327\336\364\111\201\151\250" +
"\276\157\020\075\143\214\206\314\371\106\376\023\072\147\305\056" +
"\103\347\174\103\161\034\072\347\131\365\052\324\316\063\371\253" +
"\142\177\321\145\163\144\053\006\263\154\210\013\075\307\117\335" +
"\360\074\047\147\154\301\130\364\061\010\053\220\356\101\234\023" +
"\222\141\224\253\101\174\342\204\017\310\172\215\204\344\307\000" +
"\150\236\234\127\151\226\050\331\137\072\011\103\034\147\210\342" +
"\344\032\255\165\234\272\077\302\071\204\042\206\023\250\322\207" +
"\170\211\252\214\101\225\316\341\332\100\037\070\261\027\257\131" +
"\164\342\106\225\061\052\160\242\126\003\045\372\257\156\322\246" +
"\040\224\317\362\212\042\256\377\072\056\076\164\362\056\205\251" +
"\076\163\303\062\202\030\124\203\227\204\336\043\252\025\376\047" +
"\067\274\312\143\361\073\124\343\127\031\321\361\347\326\245\126" +
"\307\214\124\064\326\056\363\004\112\272\115\153\057\163\207\323" +
"\150\002\106\336\076\314\021\317\227\037\271\221\011\054\214\106" +
"\371\035\246\251\207\207\217\162\206\127\165\346\165\217\143\174" +
"\016\363\221\061\117\311\070\147\100\060\211\121\006\065\310\113" +
"\222\124\031\026\121\137\102\051\327\204\117\222\256\323\274\376" +
"\312\037\335\224\152\055\306\376\271\023\070\211\343\252\216\060" +
"\167\144\116\012\361\053\312\332\221\351\016\213\033\112\022\261" +
"\264\052\374\237\101\370\252\021\110\156\303\116\201\206\235\156" +
"\015\353\036\366\024\257\170\056\242\103\234\245\240\101\314\360" +
"\072\205\250\161\226\256\162\304\052\212\057\062\274\346\243\201" +
"\346\177\303\203\352\235\023\070\074\057\131\232\141\210\067\064" +
"\363\264\333\057\305\006\340\016\227\351\077\353\160\165\247\350" +
"\133\230\342\157\133\356\356\136\214\176\234\131\245\056\063\162" +
"\337\336\050\220\374\342\365\274\127\173\066\074\317\110\012\157" +
"\361\055\033\136\201\055\252\266\201\347\275\353\304\056\232\057" +
"\315\140\311\027\033\106\021\170\320\174\071\274\357\315\266\035" +
"\360\175\142\024\142\331\154\356\302\107\353\042\113\343\224\161" +
"\332\331\162\211\143\326\277\377\333\245\216\357\112\061\062\220" +
"\235\070\226\217\012\156\127\216\347\276\073\357\015\273\016\136" +
"\044\170\343\070\120\202\121\024\300\144\215\045\004\140\005\211" +
"\236\367\206\351\056\234\257\037\337\223\222\361\051\203\047\160" +
"\315\246\170\151\174\016\100\270\101\154\132\161\035\201\355\073" +
"\255\355\013\160\121\216\235\203\375\171\126\340\130\111\176\267" +
"\013\246\174\263\260\071\035\334\221\064\306\042\025\131\162\272" +
"\202\350\215\353\340\016\121\201\353\025\365\103\206\161\056\105" +
"\275\337\007\251\027\115\001\372\103\037\350\046\253\112\001\170" +
"\247\017\060\105\371\112\176\307\222\015\065\102\374\135\017\270" +
"\167\142\022\065\076\247\050\376\005\200\223\253\345\336\331\111" +
"\334\065\141\156\141\123\340\107\247\346\243\226\035\150\003\367" +
"\043\257\266\142\276\067\027\310\072\027\255\111\222\056\323\030" +
"\111\054\252\030\211\111\361\200\370\316\243\225\171\371\357\003" +
"\376\173\273\124\153\121\143\222\145\070\256\227\032\123\076\116" +
"\364\016\346\051\234\303\263\300\200\144\336\064\036\342\206\166" +
"\002\247\335\234\345\111\173\063\007\041\161\337\023\177\306\045" +
"\013\342\315\304\231\211\307\334\170\042\162\251\244\050\160\313" +
"\144\143\274\301\264\161\052\365\027\030\115\036\254\154\067\236" +
"\137\003\131\224\373\333\146\206\371\317\210\021\332\116\151\055" +
"\356\162\231\266\126\307\333\166\276\154\141\323\165\221\046\270" +
"\135\133\326\133\277\047\066\002\173\340\011\010\307\131\153\143" +
"\304\177\123\224\107\077\261\150\350\177\056\270\125\344\113\371" +
"\055\314\115\167\161\014\061\045\213\376\176\024\071\243\074\027" +
"\203\362\077\312\032\251\143\264\220\023\064\115\345\163\370\066" +
"\230\253\307\035\140\223\155\265\163\220\115\272\142\302\154\142" +
"\221\023\156\023\165\110\030\164\204\255\250\303\070\143\321\067" +
"\241\154\075\360\163\177\176\135\202\154\115\062\070\134\010\067" +
"\310\367\107\220\242\147\345\076\006\030\211\003\117\310\071\324" +
"\210\253\171\224\057\011\110\250\070\046\204\124\223\022\250\307" +
"\352\256\314\107\142\127\053\376\003\064\006\161\200\002\071\111" +
"\220\300\344\214\256\104\346\054\041\125\255\142\230\122\233\105" +
"\137\371\122\164\125\157\215\027\020\263\264\172\074\214\252\365" +
"\015\070\274\223\147\120\240\063\066\211\204\073\335\224\020\320" +
"\131\214\004\362\354\311\040\147\361\043\165\066\001\362\073\171" +
"\054\311\075\032\146\157\203\326\063\004\104\213\160\246\051\056" +
"\100\223\324\330\022\344\255\065\270\337\212\235\022\033\134\045" +
"\113\344\234\213\305\033\353\130\366\340\205\237\074\365\144\350" +
"\051\130\054\153\245\225\360\071\324\242\055\201\331\301\363\272" +
"\224\141\232\163\013\363\145\355\147\265\027\205\035\135\330\230" +
"\366\300\204\121\365\240\041\223\124\052\024\276\300\242\267\367" +
"\326\100\054\172\317\131\016\132\317\345\166\060\063\154\017\302" +
"\056\154\304\360\332\365\125\205\331\137\275\161\057\307\054\125" +
"\346\350\255\256\045\162\206\137\373\125\134\023\271\161\227\222" +
"\337\372\211\157\223\137\052\356\142\053\176\261\263\344\056\370" +
"\222\273\330\212\130\240\356\276\164\110\304\032\314\104\044\374" +
"\160\014\171\263\352\325\072\155\144\372\105\163\146\013\125\054" +
"\070\104\210\372\201\105\243\143\214\246\274\046\071\066\233\223" +
"\003\145\115\204\250\353\143\210\222\061\123\016\144\340\035\305" +
"\212\052\012\345\000\237\371\313\253\013\214\157\102\251\132\315" +
"\341\374\031\131\037\304\127\252\174\021\314\227\232\013\360\270" +
"\156\115\041\033\172\114\175\162\230\054\325\161\302\242\361\061" +
"\204\115\164\331\037\344\302\135\161\242\071\203\227\361\171\174" +
"\054\305\205\207\252\105\126\150\250\166\105\035\030\252\175\105" +
"\247\034\140\200\277\252\362\117\251\052\230\256\242\355\273\120" +
"\372\060\055\321\372\125\272\062\247\012\376\042\306\261\310\136" +
"\246\347\055\100\003\054\050\135\050\262\330\307\320\003\364\047" +
"\214\172\000\275\325\116\345\117\127\276\370\074\224\056\075\357" +
"\312\237\275\133\255\353\104\167\014\111\242\215\061\050\213\354" +
"\112\322\075\215\101\133\231\135\131\215\215\121\100\116\332\225" +
"\246\032\027\217\062\111\335\111\310\242\313\303\145\211\054\176" +
"\224\101\231\155\137\355\227\256\155\337\256\000\265\351\073\202" +
"\067\351\165\344\010\312\121\253\110\300\342\333\061\176\143\015" +
"\071\202\147\066\126\220\217\273\322\004\263\311\276\104\131\211" +
"\315\371\306\076\344\055\255\260\051\344\366\001\365\241\232\350" +
"\117\064\027\053\100\374\210\353\000\311\115\303\023\060\117\367" +
"\252\231\053\055\010\105\367\253\231\002\013\300\021\075\153\050" +
"\233\243\114\150\341\004\114\063\255\135\346\176\004\100\222\067" +
"\335\076\203\153\364\112\231\103\016\000\115\265\101\371\150\256" +
"\156\151\062\167\121\100\116\345\255\357\106\233\215\317\000\157" +
"\215\032\136\330\122\115\363\162\324\352\204\372\222\164\273\171" +
"\151\321\267\245\367\136\133\017\304\361\363\251\277\000\151\167" +
"\111\376\316\237\334\120\230\024\361\314\056\102\134\373\131\277" +
"\155\252\106\163\304\004\245\232\373\306\236\262\176\017\263\276" +
"\163\104\272\370\070\001\256\017\046\366\305\262\360\324\106\252" +
"\357\021\255\116\042\317\230\076\003\070\025\136\352\005\343\123" +
"\030\130\246\135\110\000\326\167\004\052\355\103\122\103\315\220" +
"\237\000\020\352\213\205\326\005\307\076\202\272\134\000\244\266" +
"\346\345\202\032\077\040\076\233\244\353\064\003\045\220\366\131" +
"\277\274\023\263\224\004\373\131\372\206\100\222\055\233\152\020" +
"\131\373\200\145\123\015\243\113\223\235\205\262\033\176\142\051" +
"\312\074\144\310\141\174\016\023\241\247\014\210\021\211\226\242" +
"\001\153\366\366\116\105\115\010\020\052\133\012\164\117\043\056" +
"\130\276\004\043\117\202\112\105\175\211\123\312\333\315\200\142" +
"\115\360\353\133\142\300\236\112\300\365\133\022\220\236\005\336" +
"\074\316\050\101\121\055\050\315\347\031\040\343\053\222\270\030" +
"\375\033\014\274\373\312\001\224\007\004\261\365\016\000\224\161" +
"\032\054\361\020\000\314\151\364\252\203\247\265\333\042\156\215" +
"\063\161\343\321\141\252\213\254\117\334\237\251\157\002\001\041" +
"\143\356\366\124\020\003\026\013\303\220\061\006\330\245\265\257" +
"\017\125\060\003\054\331\246\101\263\206\276\037\324\357\230\100" +
"\336\254\051\346\355\020\120\013\222\044\237\312\200\334\245\046" +
"\230\172\002\076\031\375\204\003\250\154\111\361\335\345\153\132" +
"\355\070\360\371\154\067\321\137\200\071\362\111\114\235\311\360" +
"\206\341\274\024\350\054\335\055\127\025\174\234\212\266\073\353" +
"\166\157\271\114\255\112\046\024\363\145\315\307\220\332\377\001" +
"\371\265\046\110\217\004\371\161\343\306\132\171\077\334\051\033" +
"\316\157\031\132\047\113\210\014\241\172\370\255\376\325\213\347" +
"\345\210\240\130\262\270\225\302\323\270\325\336\126\260\154\266" +
"\207\313\276\274\117\340\163\125\335\366\246\241\315\211\357\266" +
"\333\303\007\046\077\144\161\050\053\130\067\304\133\043\251\237" +
"\240\315\000\345\154\333\326\141\153\210\340\250\023\147\350\027" +
"\246\170\151\354\015\325\223\034\016\304\371\164\023\300\224\257" +
"\240\142\326\020\325\326\255\353\162\100\320\111\160\255\056\336" +
"\350\256\016\360\304\357\112\157\016\237\270\341\130\316\262\254" +
"\034\244\036\334\030\236\045\237\364\362\266\054\313\316\303\312" +
"\312\364\063\015\103\264\054\050\375\104\377\121\312\244\352\317" +
"\022\267\112\376\054\221\132\015\313\222\141\355\254\111\143\200" +
"\320\214\246\327\012\060\134\335\222\201\341\152\215\200\006\264" +
"\132\207\276\202\307\333\116\247\017\324\273\225\055\013\316\364" +
"\340\110\113\172\162\244\035\025\007\272\204\111\053\302\077\043" +
"\203\247\321\101\004\211\202\245\072\072\156\322\040\243\313\356" +
"\312\046\005\342\226\052\326\074\277\103\333\337\371\240\113\221" +
"\375\102\352\157\365\272\307\164\075\365\240\056\012\376\337\215" +
"\346\243\036\230\154\076\172\317\361\075\104\255\165\113\363\153" +
"\333\347\104\326\162\174\107\334\014\063\351\367\373\205\152\344" +
"\054\027\267\202\373\147\313\141\352\333\037\271\140\362\235\221" +
"\013\047\144\251\046\257\367\367\342\256\050\251\012\267\212\231" +
"\153\246\242\357\054\227\171\054\161\015\255\176\025\344\362\022" +
"\365\160\313\145\333\211\274\202\331\377\305\272\342\165\311\022" +
"\117\145\134\243\342\356\046\015\357\262\346\153\145\364\236\343" +
"\151\333\341\070\251\233\342\316\317\362\144\273\246\172\061\047" +
"\324\353\060\337\020\305\011\002\257\014\173\056\075\001\144\265" +
"\255\363\270\210\060\354\326\333\246\103\004\360\332\361\377\172" +
"\067\015\151\350\110\000\000"
});

public static final byte[] symbolNumbersHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\223\031\106\301\220\007\012\303\030\073\060" +
"\060\060\001\061\013\022\006\361\231\241\230\021\210\205\260\210" +
"\261\020\320\003\343\163\000\061\033\024\163\100\061\027\036\163" +
"\140\146\340\063\033\206\101\346\360\141\061\033\104\363\100\345" +
"\270\220\304\141\230\007\211\206\351\205\271\221\013\213\175\274" +
"\120\232\325\001\022\136\134\120\265\270\374\214\354\116\046\062" +
"\060\056\263\130\240\064\073\224\146\103\023\147\306\341\056\230" +
"\177\221\303\007\346\147\344\160\141\163\100\015\007\230\132\026" +
"\250\030\013\032\233\230\060\140\307\042\006\163\057\162\374\063" +
"\103\303\226\021\015\303\302\203\031\211\146\106\062\003\071\274" +
"\230\320\304\220\315\007\363\001\323\074\311\274\247\005\000\000" +
""
});

public static final byte[] productionLHSsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\265\303\267\011\002\141" +
"\030\200\341\217\003\367\260\162\027\013\047\260\161\004\343\171" +
"\346\234\163\316\071\065\126\226\272\221\340\016\376\305\133\210" +
"\234\242\210\017\074\307\233\130\002\136\321\234\166\307\331\145" +
"\323\257\247\235\046\142\270\105\344\142\025\361\250\336\207\076" +
"\372\031\120\165\006\151\074\015\061\314\010\243\057\306\030\377" +
"\140\202\111\246\230\376\323\014\263\314\251\171\265\300\242\132" +
"\122\313\254\260\312\032\353\154\174\271\251\266\330\146\347\213" +
"\135\366\330\377\361\340\315\041\107\034\163\302\251\072\343\234" +
"\013\223\313\207\053\256\271\341\326\344\356\351\236\007\263\167" +
"\277\037\236\115\007\003\000\000"
});

public static final byte[] parseTableHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\335\007\270\053\125" +
"\325\006\340\165\316\345\122\125\054\050\242\250\133\105\172\221" +
"\216\200\032\172\257\052\275\131\300\256\200\242\330\173\301\336" +
"\001\025\053\352\257\142\303\216\275\053\066\354\275\213\035\121" +
"\121\261\373\257\271\346\074\314\031\046\223\231\144\175\263\366" +
"\236\375\275\317\235\047\223\111\146\355\165\163\276\354\111\162" +
"\162\222\213\256\220\225\147\235\051\053\216\077\376\200\365\257" +
"\376\371\141\033\074\367\234\335\027\105\316\076\135\144\361\152" +
"\335\276\170\374\001\207\134\162\312\306\017\373\315\305\257\033" +
"\157\226\377\023\242\210\235\165\206\074\126\064\255\263\045\065" +
"\310\252\175\227\326\127\350\262\132\315\165\126\352\262\172\351" +
"\374\032\245\365\065\047\324\135\113\227\265\133\214\277\116\345" +
"\374\165\246\355\323\225\326\274\256\165\315\041\011\042\327\323" +
"\145\135\364\070\363\045\325\103\020\271\276\167\017\051\011\042" +
"\067\360\356\301\102\222\111\275\241\167\017\251\013\042\067\362" +
"\356\241\253\044\223\272\236\167\017\264\352\347\160\343\076\307" +
"\143\122\151\066\101\344\046\175\216\227\106\122\203\310\372\336" +
"\075\344\052\210\334\324\273\207\102\032\111\055\013\042\033\170" +
"\367\060\164\101\344\146\336\075\124\045\231\124\036\375\043\020" +
"\104\156\336\347\170\176\111\015\042\033\366\075\046\341\004\221" +
"\133\040\353\247\067\247\326\011\042\267\364\356\201\260\322\113" +
"\152\340\321\077\012\101\344\126\175\216\307\244\322\154\302\252" +
"\177\375\111\062\251\267\366\356\201\146\027\104\156\063\313\176" +
"\327\044\365\024\323\176\210\154\135\223\324\221\310\177\272\356" +
"\035\370\136\252\354\005\221\333\206\250\337\113\025\104\066\262" +
"\357\307\116\020\271\235\167\017\336\202\310\306\343\323\115\174" +
"\073\231\137\171\116\135\350\274\167\171\237\142\275\256\106\165" +
"\373\244\365\246\175\332\214\337\124\157\036\210\232\324\335\344" +
"\071\065\210\154\132\132\337\254\162\331\346\350\316\332\012\042" +
"\133\324\154\333\122\227\255\132\356\277\265\155\107\151\010\042" +
"\333\350\162\173\357\076\332\132\066\247\166\236\074\312\373\024" +
"\353\165\065\252\333\047\255\067\355\323\146\374\246\172\363\100" +
"\324\244\356\322\170\225\052\314\360\136\052\335\147\133\373\116" +
"\362\023\104\266\153\270\154\373\276\372\130\066\247\056\116\275" +
"\172\105\171\237\142\275\256\106\165\373\244\365\246\175\332\214" +
"\337\124\157\036\210\232\324\335\262\244\256\350\272\167\171\237" +
"\142\275\256\106\165\373\244\365\246\175\332\214\337\124\157\036" +
"\210\232\324\335\262\327\123\377\330\165\357\362\076\305\172\265" +
"\306\322\266\111\327\233\064\146\135\255\151\343\267\351\147\026" +
"\026\065\150\176\313\346\324\153\275\152\077\115\171\237\142\275" +
"\256\106\165\373\244\365\246\175\332\214\337\124\157\036\210\232" +
"\324\235\375\063\252\040\262\203\056\073\316\260\337\116\272\354" +
"\254\313\056\023\056\277\103\345\374\256\343\323\335\306\247\273" +
"\217\117\357\250\313\235\164\271\363\224\361\106\343\323\075\272" +
"\366\212\026\104\366\034\237\356\245\313\336\225\313\366\321\145" +
"\137\135\366\323\145\377\361\266\003\164\071\320\150\354\203\306" +
"\247\007\133\324\263\262\154\116\135\331\165\357\362\076\305\172" +
"\135\215\352\366\111\353\115\373\264\031\277\251\336\074\020\065" +
"\251\273\145\111\135\175\352\325\053\312\373\024\353\165\065\252" +
"\333\047\255\067\355\323\146\374\246\172\363\100\324\244\356\226" +
"\045\265\363\273\073\312\373\024\353\165\065\252\333\047\255\067" +
"\355\323\146\374\066\347\147\141\121\203\346\267\054\251\153\114" +
"\275\172\105\171\237\142\275\256\106\165\373\244\365\246\175\332" +
"\214\337\124\157\036\210\232\324\335\262\244\326\276\003\257\111" +
"\171\237\142\275\256\106\165\373\244\365\246\175\332\214\337\124" +
"\157\036\210\232\324\335\262\244\256\325\165\357\362\076\305\172" +
"\135\215\352\366\111\353\115\373\264\031\277\251\336\074\020\065" +
"\251\273\145\111\235\372\316\345\252\362\076\305\172\135\215\352" +
"\366\111\353\115\373\264\031\277\251\336\074\020\065\251\273\170" +
"\336\241\022\104\016\231\162\371\241\035\152\035\246\313\341\123" +
"\256\163\104\315\266\043\165\271\313\170\375\256\272\334\155\274" +
"\176\224\056\107\327\134\377\030\135\216\035\257\037\247\313\361" +
"\272\234\120\272\374\304\266\075\017\125\020\071\251\146\333\311" +
"\135\353\054\233\123\327\231\172\365\212\362\076\305\172\135\215" +
"\352\366\111\353\115\373\264\031\277\251\336\074\020\065\251\273" +
"\145\277\367\377\257\167\067\104\223\304\163\364\357\042\210\234" +
"\342\335\003\365\153\331\234\372\216\256\173\227\367\051\326\353" +
"\152\124\267\117\132\157\332\247\315\370\115\365\346\201\250\111" +
"\335\141\347\324\221\310\273\020\165\143\037\233\354\141\223\032" +
"\104\356\216\250\333\162\354\173\170\215\115\366\222\175\234\172" +
"\117\357\036\250\137\311\046\365\136\336\075\120\277\222\115\352" +
"\251\336\075\120\277\222\115\352\151\336\075\120\277\222\115\352" +
"\275\275\173\240\176\055\173\075\365\103\135\367\056\357\123\254" +
"\327\325\250\156\237\264\336\264\117\233\361\333\234\237\205\105" +
"\015\232\137\262\163\352\175\274\173\240\176\301\137\371\377\064" +
"\242\156\354\143\223\075\370\053\377\367\105\324\155\071\366\375" +
"\274\306\046\173\313\036\247\376\243\353\336\345\175\212\365\152" +
"\215\245\155\325\353\325\255\327\355\327\145\374\066\347\147\141" +
"\121\203\346\007\237\123\357\217\250\333\306\110\344\337\136\143" +
"\223\275\064\237\121\265\021\104\036\340\335\003\331\111\063\251" +
"\101\344\201\336\075\120\277\222\115\352\203\274\173\240\176\045" +
"\233\324\007\173\367\100\375\112\066\251\247\173\367\100\375\112" +
"\066\251\147\170\367\100\375\112\066\251\147\172\367\100\375\112" +
"\066\251\017\361\356\201\372\225\154\122\037\352\335\003\365\053" +
"\331\244\236\345\335\003\365\053\331\244\076\314\273\007\352\127" +
"\262\111\175\270\167\017\324\257\145\357\245\372\135\327\275\313" +
"\373\024\353\165\065\252\333\047\255\067\355\323\146\374\066\347" +
"\147\141\121\203\346\227\354\234\172\266\167\017\324\257\144\223" +
"\372\010\357\036\250\137\311\046\365\221\336\075\120\277\222\115" +
"\352\243\274\173\240\176\045\233\324\107\173\367\100\375\112\066" +
"\251\217\361\356\201\372\225\154\122\037\353\335\003\365\053\331" +
"\244\076\316\273\007\352\127\262\111\175\274\167\017\324\257\144" +
"\223\372\004\357\036\250\137\311\046\365\211\336\075\120\277\340" +
"\237\114\361\044\104\335\066\106\042\127\172\215\115\366\322\234" +
"\123\333\010\042\117\366\356\201\354\244\231\324\040\362\024\357" +
"\036\250\137\311\046\365\251\336\075\120\277\222\115\352\323\274" +
"\173\240\176\055\373\056\352\335\272\356\135\336\247\130\257\253" +
"\121\335\076\151\275\151\237\066\343\067\325\233\007\242\046\165" +
"\227\354\234\372\164\357\036\250\137\311\046\365\034\357\036\250" +
"\137\311\046\365\031\336\075\120\277\222\115\352\063\275\173\240" +
"\176\045\233\324\147\171\367\100\375\112\066\251\317\366\356\201" +
"\372\225\154\122\237\343\335\003\365\053\331\244\076\327\273\007" +
"\352\127\262\111\175\236\167\017\324\257\144\223\372\174\357\036" +
"\250\137\311\046\365\005\336\075\120\277\222\115\352\013\275\173" +
"\240\176\045\233\324\027\171\367\100\375\102\177\153\372\302\235" +
"\020\165\143\037\233\354\301\377\216\352\305\210\272\055\307\176" +
"\211\327\330\144\057\215\243\177\020\131\337\273\207\034\005\221" +
"\163\165\071\317\273\217\302\354\111\015\042\033\331\367\103\226" +
"\202\310\371\336\075\130\131\366\351\351\027\167\335\273\274\117" +
"\261\136\127\243\272\175\322\172\323\076\155\306\157\252\067\017" +
"\104\115\352\216\107\177\152\026\104\136\352\335\103\201\111\245" +
"\146\101\344\145\336\075\024\230\124\152\026\104\136\356\335\103" +
"\041\215\244\226\005\221\013\274\173\240\172\101\344\025\250\332" +
"\114\052\331\011\042\257\104\325\116\043\251\201\107\177\067\101" +
"\344\125\336\075\024\046\047\065\210\154\132\132\337\254\162\331" +
"\346\350\316\332\012\042\133\324\154\333\262\303\376\133\033\266" +
"\223\214\040\362\152\357\036\272\130\366\172\352\207\273\356\135" +
"\336\247\130\257\253\121\335\076\151\275\151\237\066\343\267\071" +
"\077\013\213\032\064\077\036\375\251\131\020\331\256\341\262\327" +
"\364\325\307\262\071\365\237\135\367\056\357\123\254\127\153\054" +
"\155\253\136\257\156\275\156\277\056\343\267\071\077\013\213\032" +
"\064\077\316\251\064\131\020\171\155\110\376\035\052\175\012\114" +
"\252\213\040\162\141\140\122\073\010\114\252\213\040\362\272\300" +
"\244\166\020\230\124\027\101\344\365\201\111\355\040\060\251\056" +
"\202\310\033\002\223\332\101\140\122\135\004\315\105\140\122\073" +
"\010\114\252\213\040\362\306\300\244\166\020\230\124\027\101\344" +
"\115\201\111\355\040\060\251\056\202\310\233\003\223\332\101\140" +
"\122\135\004\221\213\002\223\332\101\140\122\135\004\221\267\004" +
"\046\265\203\300\244\272\010\042\157\015\114\152\007\201\111\165" +
"\021\104\336\026\230\324\016\002\223\352\042\210\274\075\060\251" +
"\035\004\046\325\105\020\171\107\140\122\073\010\114\252\213\040" +
"\162\161\140\122\073\010\114\252\213\040\362\316\300\244\166\020" +
"\230\124\027\101\344\135\201\111\355\040\060\251\056\202\310\273" +
"\003\223\332\101\140\122\135\004\221\367\004\046\265\203\300\244" +
"\272\010\042\357\015\114\152\007\201\111\165\021\104\336\027\006" +
"\233\324\040\262\203\056\073\316\260\337\116\272\354\254\313\056" +
"\023\056\277\103\345\374\256\343\323\335\306\247\273\217\117\357" +
"\250\313\324\157\243\010\253\376\100\172\325\351\036\135\173\105" +
"\013\042\173\216\117\367\322\145\357\312\145\373\350\262\257\056" +
"\373\351\262\377\170\333\001\272\034\150\064\366\101\343\323\367" +
"\133\324\263\262\354\357\375\257\350\272\167\171\237\142\275\256" +
"\106\165\373\244\365\246\175\332\214\337\346\374\054\054\152\320" +
"\374\170\364\247\146\101\344\022\357\036\012\114\052\065\013\042" +
"\037\360\356\241\300\244\122\263\040\362\101\357\036\012\114\052" +
"\065\013\042\037\362\356\241\300\244\122\263\020\311\247\162\062" +
"\251\324\054\210\174\304\273\207\002\223\112\315\202\310\107\275" +
"\173\050\060\251\324\054\210\174\314\273\207\002\223\112\315\202" +
"\310\307\275\173\050\060\251\324\054\210\174\302\273\207\002\223" +
"\112\315\202\310\047\275\173\050\060\251\324\054\210\174\312\273" +
"\207\002\223\112\315\202\310\247\275\173\050\060\251\324\054\210" +
"\174\306\273\207\002\223\112\315\202\310\147\275\173\050\060\251" +
"\324\054\210\174\316\273\207\102\074\111\015\042\207\114\271\374" +
"\320\016\265\016\153\161\235\043\152\266\035\251\313\135\306\353" +
"\167\325\345\156\343\365\243\164\071\272\346\372\307\350\162\354" +
"\170\375\070\135\216\327\345\204\322\345\047\266\355\171\250\202" +
"\310\245\026\165\312\357\371\137\330\275\353\336\345\175\212\365" +
"\272\032\325\355\223\326\233\366\151\063\176\123\275\171\040\152" +
"\122\167\330\071\065\210\174\036\121\227\362\003\117\352\027\020" +
"\165\051\077\330\244\216\104\336\211\250\033\373\330\144\017\076" +
"\247\176\021\121\227\362\003\117\352\227\020\165\051\077\360\244" +
"\176\031\121\227\362\023\317\353\251\223\004\221\313\146\334\357" +
"\053\266\235\120\033\101\344\253\210\272\360\071\365\153\210\272" +
"\224\037\170\122\277\216\250\113\371\201\047\365\033\210\272\224" +
"\037\370\353\251\156\357\302\365\034\233\354\305\377\214\152\126" +
"\043\221\177\171\367\100\166\340\107\377\157\042\352\122\176\340" +
"\111\375\026\242\056\345\007\236\324\157\043\352\122\176\340\111" +
"\375\016\242\056\345\007\236\324\357\042\352\122\176\340\111\375" +
"\036\242\056\345\007\236\324\357\043\352\122\176\340\111\375\001" +
"\242\056\345\007\236\324\037\042\352\122\176\340\111\375\021\242" +
"\056\345\007\236\324\037\043\352\122\176\340\111\375\011\242\056" +
"\345\007\236\324\237\042\352\122\176\340\111\375\031\242\056\345" +
"\007\236\324\237\043\352\122\176\340\111\275\034\121\227\362\003" +
"\117\352\057\020\165\051\077\360\244\376\022\121\227\362\003\117" +
"\352\257\020\165\051\077\360\244\376\032\121\227\362\003\117\352" +
"\157\020\165\051\077\360\244\376\026\121\227\362\063\350\277\242" +
"\376\275\167\017\144\007\076\247\376\016\121\227\362\003\117\352" +
"\025\210\272\224\037\170\122\171\004\046\023\360\244\136\211\250" +
"\113\371\201\047\365\017\210\272\224\037\170\122\377\210\250\113" +
"\371\201\047\365\117\210\272\224\037\170\122\257\102\324\245\374" +
"\300\223\372\147\104\135\312\017\074\251\177\101\324\245\374\300" +
"\223\372\127\104\135\312\017\074\251\127\043\352\122\176\340\111" +
"\375\033\242\056\345\007\236\324\277\043\352\122\176\340\111\375" +
"\007\242\056\345\007\236\324\177\042\352\122\176\320\337\234\266" +
"\160\107\104\335\330\307\046\173\151\274\347\077\210\254\357\335" +
"\103\256\102\044\337\100\227\106\122\247\011\042\377\366\356\201" +
"\260\322\113\152\020\131\317\273\007\132\365\163\370\117\237\343" +
"\045\231\324\377\172\367\020\273\040\013\336\055\230\303\045\125" +
"\157\255\205\312\371\305\206\353\256\030\237\256\126\163\331\312" +
"\322\372\352\272\254\121\271\174\315\031\172\133\253\264\276\166" +
"\327\375\143\242\375\257\343\335\103\225\366\164\035\353\232\350" +
"\127\251\026\256\213\250\333\206\076\367\277\276\327\330\144\017" +
"\376\052\225\133\122\151\130\322\173\234\332\226\316\347\327\363" +
"\356\201\354\360\161\052\037\247\332\323\236\326\265\256\071\334" +
"\071\265\116\340\143\327\231\351\155\167\003\317\361\207\237\124" +
"\275\205\157\350\335\003\315\057\215\244\006\376\066\325\215\336" +
"\323\157\344\335\103\201\111\245\146\232\324\050\176\047\310\147" +
"\124\174\106\145\117\173\272\261\165\315\064\346\324\262\040\162" +
"\201\167\017\124\117\023\172\023\124\355\064\222\032\170\364\167" +
"\243\351\213\342\266\147\122\251\231\046\365\246\336\075\024\230" +
"\124\152\246\111\335\300\273\207\002\223\112\315\064\251\067\363" +
"\356\241\300\244\122\063\115\352\315\275\173\050\244\221\324\262" +
"\300\347\376\321\322\124\157\210\252\315\244\222\035\115\352\055" +
"\120\265\323\110\152\340\321\337\215\246\357\226\336\075\024\230" +
"\124\152\246\111\275\225\167\017\005\046\225\232\151\122\203\163" +
"\013\253\060\251\324\114\223\172\153\357\036\012\114\052\065\323" +
"\244\336\306\273\207\002\223\112\315\064\251\267\365\356\241\300" +
"\244\122\063\115\352\106\336\075\024\230\124\152\246\111\275\235" +
"\167\017\005\277\244\006\021\330\357\063\250\177\232\350\215\221" +
"\365\071\247\122\063\115\340\046\336\075\024\230\124\152\246\111" +
"\335\324\273\207\102\032\111\055\323\133\156\063\357\036\250\073" +
"\375\271\155\076\317\376\151\044\065\160\116\165\243\011\333\302" +
"\273\207\002\223\112\315\064\251\133\172\367\120\140\122\251\231" +
"\046\165\053\357\036\012\114\052\065\323\244\156\355\335\103\201" +
"\111\245\146\232\324\155\274\173\050\060\251\324\114\223\172\173" +
"\357\036\012\151\044\265\017\372\023\331\266\264\276\135\303\365" +
"\266\327\145\007\135\166\054\155\333\251\346\172\073\333\167\231" +
"\046\275\055\166\231\267\106\032\111\015\234\123\335\150\312\356" +
"\340\335\103\041\215\244\226\005\176\037\125\024\064\301\273\366" +
"\071\136\032\111\015\234\123\335\150\042\167\363\356\241\300\244" +
"\122\063\115\352\356\336\075\024\230\124\152\026\042\371\116\357" +
"\064\222\132\026\370\070\065\012\232\340\073\365\071\136\032\111" +
"\015\234\123\335\150\042\357\354\335\103\201\111\245\146\232\324" +
"\221\167\017\005\370\367\246\356\201\250\113\371\201\047\165\117" +
"\104\135\312\017\074\251\173\041\352\122\176\232\223\252\111\333" +
"\273\115\025\275\336\076\246\155\031\320\236\366\365\356\241\057" +
"\372\177\335\257\264\276\377\370\364\000\277\216\354\245\361\214" +
"\152\126\372\323\072\320\273\007\262\201\115\352\310\361\133\141" +
"\213\261\227\306\367\354\203\154\244\071\247\352\134\171\220\167" +
"\017\324\057\370\234\352\366\355\260\305\330\113\343\173\366\101" +
"\066\222\235\123\017\366\356\201\372\005\237\123\335\276\165\253" +
"\030\173\151\174\317\076\310\006\074\251\346\337\111\334\145\354" +
"\245\361\075\373\040\033\374\326\164\176\153\272\075\355\351\020" +
"\353\232\311\076\116\075\324\273\007\352\027\374\350\357\366\036" +
"\250\142\354\245\361\075\373\040\033\311\316\251\207\171\367\100" +
"\375\112\066\251\207\173\367\100\375\202\277\227\352\010\104\135" +
"\312\117\374\163\152\020\271\314\273\007\152\117\147\247\043\021" +
"\165\341\317\250\256\207\250\113\371\211\177\116\235\207\336\277" +
"\357\342\335\003\331\110\063\251\232\300\273\172\367\100\375\202" +
"\077\243\272\033\242\056\345\047\315\071\265\213\121\004\357\373" +
"\327\173\354\121\065\333\216\326\345\030\135\216\255\154\077\256" +
"\277\316\342\241\377\357\343\165\071\241\164\376\104\135\116\322" +
"\345\344\342\074\174\116\075\005\121\227\362\003\117\352\335\021" +
"\165\051\077\303\076\372\353\075\345\036\336\075\220\015\370\234" +
"\172\117\104\135\312\017\074\251\367\102\324\245\374\300\223\172" +
"\052\242\056\345\007\236\324\323\020\165\051\077\360\244\336\033" +
"\121\227\362\003\117\352\175\020\165\051\077\360\244\336\027\121" +
"\227\362\003\117\352\375\020\165\051\077\360\244\336\037\121\227" +
"\362\003\117\352\003\020\165\051\077\360\244\076\020\121\227\362" +
"\003\117\352\203\020\165\051\077\360\244\076\030\121\227\362\003" +
"\117\352\351\210\272\224\237\301\277\353\357\014\357\036\310\006" +
"\174\116\075\023\121\227\362\063\370\071\365\041\336\075\220\215" +
"\301\047\365\241\336\075\220\015\370\321\377\054\104\135\312\317" +
"\160\347\124\275\227\074\254\343\365\267\055\255\157\327\160\275" +
"\355\165\331\101\227\035\113\333\166\252\271\336\316\135\306\037" +
"\062\275\055\036\076\076\075\173\326\032\360\071\365\021\210\272" +
"\224\037\170\122\037\211\250\113\371\031\356\321\277\240\367\224" +
"\107\171\367\100\066\340\163\352\243\021\165\051\077\360\244\076" +
"\006\121\227\362\003\117\352\143\021\165\051\077\360\244\076\016" +
"\121\227\362\223\346\063\052\275\007\074\336\273\007\352\127\262" +
"\111\175\202\167\017\324\257\144\223\372\104\357\036\250\137\311" +
"\046\365\111\336\075\120\277\222\115\352\223\275\173\240\176\045" +
"\233\324\247\170\367\100\375\112\066\251\117\365\356\201\372\005" +
"\177\075\365\151\210\272\224\037\170\122\237\216\250\113\371\111" +
"\363\350\337\226\336\123\316\361\356\201\154\300\347\324\147\040" +
"\352\122\176\340\111\175\046\242\056\345\147\360\107\377\147\171" +
"\367\100\066\006\237\324\147\173\367\100\066\340\107\377\347\040" +
"\352\122\176\340\111\175\056\242\056\345\047\215\243\177\020\131" +
"\337\273\207\134\351\154\363\074\357\036\012\151\044\165\032\275" +
"\065\237\357\335\003\141\371\045\065\210\154\330\367\230\204\243" +
"\263\305\013\220\365\323\234\123\365\126\171\241\167\017\324\257" +
"\144\223\372\042\357\036\250\137\151\046\265\255\221\310\105\336" +
"\075\220\215\064\223\252\163\352\213\275\173\240\176\301\137\117" +
"\175\011\242\156\033\043\221\267\171\215\115\366\322\234\123\333" +
"\320\173\311\271\336\075\220\035\154\122\107\042\037\105\324\215" +
"\175\154\262\207\113\252\316\151\013\225\363\213\015\327\135\061" +
"\076\135\255\346\262\225\245\365\325\165\131\243\162\371\232\063" +
"\364\266\126\151\175\355\256\373\307\104\373\137\307\273\207\052" +
"\355\351\074\353\232\351\035\375\365\126\070\337\273\007\232\116" +
"\177\116\057\265\254\227\136\122\333\322\133\352\145\336\075\220" +
"\235\064\222\032\370\016\025\067\172\217\177\271\167\017\005\046" +
"\225\232\151\122\057\360\356\241\300\244\122\063\115\352\053\274" +
"\173\050\360\275\124\144\103\023\375\112\144\375\064\346\324\131" +
"\214\144\141\135\357\036\310\016\374\225\377\217\041\352\306\076" +
"\066\331\113\143\116\015\174\234\352\106\217\352\257\362\356\241" +
"\220\106\122\313\364\226\173\265\167\017\251\322\333\356\065\336" +
"\075\314\052\275\244\166\245\217\127\017\361\036\177\332\122\275" +
"\236\147\277\261\312\042\251\007\173\217\077\155\251\136\317\263" +
"\337\130\015\077\251\061\320\243\356\121\065\333\216\056\255\277" +
"\266\337\216\342\244\267\303\205\272\274\116\227\327\127\057\143" +
"\122\373\060\051\251\272\034\243\313\261\113\333\164\076\075\120" +
"\317\037\327\157\167\161\320\377\367\033\164\071\241\164\376\104" +
"\135\116\322\345\344\342\374\360\223\072\252\111\111\337\343\127" +
"\227\352\366\352\165\375\272\215\327\360\223\252\367\311\301\376" +
"\337\162\062\374\244\352\034\165\244\367\370\325\245\272\275\172" +
"\135\277\156\343\225\105\122\217\361\036\177\364\277\307\243\157" +
"\324\345\115\272\274\271\274\275\334\137\365\074\135\043\213\244" +
"\036\357\075\176\135\017\113\333\313\227\115\272\056\145\222\324" +
"\203\074\307\327\071\364\242\016\317\375\017\312\365\271\177\241" +
"\346\271\377\133\226\316\247\221\324\300\337\373\273\321\264\274" +
"\325\273\207\102\032\111\055\013\042\121\274\007\235\256\115\123" +
"\015\373\064\020\370\273\376\276\215\250\033\373\330\144\057\275" +
"\071\165\036\172\237\277\276\167\017\251\322\333\356\355\236\343" +
"\363\223\051\370\311\024\366\264\247\167\130\327\114\157\116\015" +
"\174\234\032\055\115\350\305\250\332\234\123\071\247\332\323\236" +
"\336\151\135\223\163\052\331\321\204\276\013\125\073\215\244\006" +
"\276\236\352\106\323\367\156\357\036\012\151\044\325\012\237\373" +
"\317\116\157\273\367\170\216\317\244\122\073\172\333\275\327\163" +
"\374\364\222\032\370\070\065\132\232\346\367\241\152\247\227\324" +
"\256\364\326\173\277\167\017\064\277\364\222\032\070\247\106\113" +
"\147\205\113\120\265\231\124\262\243\111\375\000\252\066\137\371" +
"\347\053\377\366\264\247\017\132\327\344\234\112\166\064\241\037" +
"\102\325\206\277\353\357\227\210\272\261\217\115\366\170\364\347" +
"\321\337\236\366\364\141\353\232\360\071\365\327\210\272\261\217" +
"\115\366\340\111\375\015\242\156\354\143\223\075\076\243\042\073" +
"\172\324\377\010\252\066\172\116\135\330\031\121\067\225\361\311" +
"\016\074\251\273\042\352\306\076\066\331\303\046\125\217\006\037" +
"\105\324\155\071\066\277\221\142\100\374\037\247\152\242\076\356" +
"\070\366\047\274\306\216\221\336\036\237\364\356\141\022\377\244" +
"\266\021\370\236\177\067\232\336\117\171\367\120\200\077\116\335" +
"\002\121\067\366\261\311\136\032\163\152\231\336\307\067\363\356" +
"\201\272\323\237\333\247\347\331\077\215\244\006\036\375\335\150" +
"\302\076\343\335\103\201\111\245\146\232\324\317\172\367\120\110" +
"\043\251\145\074\372\247\111\177\156\237\233\147\377\064\222\032" +
"\070\247\272\321\204\135\352\335\103\201\111\245\146\232\324\317" +
"\173\367\120\140\122\251\231\046\365\013\336\075\024\230\124\152" +
"\246\111\375\242\167\017\005\046\225\232\151\122\277\344\335\103" +
"\201\111\245\146\232\324\057\173\367\120\140\122\251\231\046\365" +
"\062\357\036\012\151\044\265\214\257\247\246\111\177\156\137\231" +
"\147\177\046\225\372\241\077\267\257\316\263\077\374\275\124\033" +
"\043\352\306\076\066\331\113\143\116\015\174\234\352\106\347\302" +
"\257\171\367\120\140\122\251\231\046\365\353\336\075\024\340\107" +
"\377\315\021\165\143\037\233\354\301\223\272\045\242\156\354\143" +
"\223\275\170\216\376\172\224\371\206\167\017\005\355\343\233\015" +
"\227\175\253\146\333\265\276\237\125\267\175\307\272\257\041\323" +
"\333\353\273\323\256\023\117\122\333\012\042\353\171\367\100\253" +
"\322\365\275\076\307\203\377\275\377\367\021\165\051\077\360\244" +
"\376\000\121\227\362\223\336\321\277\013\275\247\374\320\273\007" +
"\262\221\136\122\003\037\247\106\101\147\201\037\365\071\036\223" +
"\112\263\321\244\376\270\317\361\322\110\152\340\357\250\334\150" +
"\042\177\342\335\103\241\071\251\332\345\336\155\252\350\365\366" +
"\061\155\213\072\321\333\177\277\322\372\376\343\323\237\372\165" +
"\144\057\215\071\165\126\043\221\267\170\367\100\066\206\235\124" +
"\235\127\176\346\335\003\331\030\356\267\374\350\366\237\067\214" +
"\307\157\371\001\322\236\056\037\237\376\302\252\046\374\225\177" +
"\176\173\031\231\200\277\227\352\146\210\272\155\307\136\032\337" +
"\263\017\262\001\237\123\177\205\250\113\371\031\374\063\052\176" +
"\317\337\100\300\347\124\176\317\036\231\030\374\234\372\133\357" +
"\036\310\006\174\116\375\035\242\056\345\147\171\122\065\131\127" +
"\114\272\246\136\366\373\361\351\225\225\355\177\200\266\070\043" +
"\355\353\217\336\075\304\104\157\217\077\215\117\257\362\356\145" +
"\026\203\077\372\377\331\273\007\262\061\354\244\306\102\357\061" +
"\107\325\154\073\332\243\227\230\351\155\162\241\056\177\321\345" +
"\365\325\313\230\324\076\214\144\341\360\362\171\375\111\374\265" +
"\272\255\172\236\226\143\122\373\320\146\116\325\363\127\367\327" +
"\121\234\212\071\165\174\372\267\352\145\114\152\037\164\276\074" +
"\164\332\266\272\353\320\065\230\324\230\350\134\362\167\357\036" +
"\142\005\177\075\365\037\210\272\224\237\341\317\251\043\347\347" +
"\330\305\370\165\075\054\155\057\137\066\351\272\224\111\122\217" +
"\365\036\277\256\207\245\355\345\313\046\135\227\062\111\352\161" +
"\336\343\327\365\260\264\275\174\331\244\353\122\046\111\075\320" +
"\273\207\111\257\122\351\162\114\130\076\247\036\030\062\115\252" +
"\376\277\377\251\313\011\245\363\047\352\162\222\056\047\027\347" +
"\263\110\352\011\323\257\205\035\277\256\207\245\355\345\313\046" +
"\135\227\172\170\356\377\057\104\135\312\017\074\251\377\106\324" +
"\245\374\014\373\350\257\367\224\377\170\367\100\066\006\237\324" +
"\377\172\367\100\066\320\107\377\211\037\106\101\324\311\320\347" +
"\324\305\205\351\327\242\024\300\347\124\116\252\144\002\236\324" +
"\025\210\272\224\037\170\122\257\365\211\150\104\263\030\374\343" +
"\324\225\323\257\105\051\200\317\251\253\043\352\122\176\206\075" +
"\247\026\122\172\207\312\170\173\246\357\120\131\134\303\357\035" +
"\052\072\172\355\247\360\022\165\005\117\352\132\323\257\105\064" +
"\035\074\251\111\177\062\071\305\003\236\324\350\076\203\236\322" +
"\064\354\147\124\172\117\271\216\167\017\144\003\076\247\136\027" +
"\121\227\362\063\354\071\265\213\040\013\333\226\326\267\153\270" +
"\336\366\272\354\240\313\216\245\155\073\325\134\157\147\373\056" +
"\323\244\063\326\365\346\255\001\377\356\224\135\020\165\123\031" +
"\237\354\014\173\116\035\211\174\335\273\007\262\061\370\244\176" +
"\303\273\007\262\061\354\244\352\343\243\165\275\173\040\033\360" +
"\347\376\327\107\324\245\374\300\223\172\003\104\135\312\017\074" +
"\251\067\104\324\245\374\300\223\172\043\104\135\312\317\340\237" +
"\121\255\347\335\003\331\030\174\122\157\354\335\003\331\200\037" +
"\375\157\202\250\113\371\031\374\234\272\276\167\017\144\143\360" +
"\111\275\251\167\017\144\143\360\111\335\300\273\007\262\001\177" +
"\234\172\063\104\135\312\017\074\251\067\107\324\245\374\300\223" +
"\272\041\242\056\345\147\360\217\123\157\341\335\003\331\200\317" +
"\251\267\104\324\245\374\300\223\172\053\104\135\312\017\374\357" +
"\250\366\101\324\245\374\244\371\070\125\347\352\340\335\003\365" +
"\013\076\247\356\213\250\113\371\111\166\116\275\265\167\017\324" +
"\257\144\223\172\033\357\036\250\137\360\347\376\267\105\324\245" +
"\374\244\071\247\266\245\367\224\215\274\173\040\033\151\044\065" +
"\210\360\175\246\116\364\336\176\073\357\036\012\151\044\165\032" +
"\275\065\067\366\356\201\260\320\257\122\311\107\020\165\143\037" +
"\233\354\015\143\116\235\104\347\332\115\274\173\040\033\203\117" +
"\352\246\336\075\220\215\301\047\165\063\357\036\310\306\160\223" +
"\072\022\171\253\167\017\144\007\376\173\177\267\317\245\052\306" +
"\136\032\337\263\017\262\001\117\252\353\137\247\170\217\117\166" +
"\340\277\115\335\034\121\267\015\115\051\337\305\075\040\303\175" +
"\234\252\367\222\055\274\173\040\073\270\244\006\131\130\250\234" +
"\137\154\270\356\212\361\351\152\065\227\255\054\255\257\256\313" +
"\032\225\313\073\177\213\260\356\263\126\151\075\351\157\314\324" +
"\376\243\373\036\105\235\045\266\264\256\231\336\234\252\077\231" +
"\363\275\173\240\351\064\255\133\131\326\203\077\243\162\373\134" +
"\250\142\354\245\361\075\373\040\033\234\123\011\103\347\324\255" +
"\055\353\301\337\241\362\161\104\335\330\307\046\173\051\316\251" +
"\213\333\170\367\220\063\275\375\157\357\061\156\172\111\055\350" +
"\255\265\355\364\153\321\220\044\233\324\211\337\025\115\303\224" +
"\154\122\267\367\356\201\372\225\154\122\167\360\356\201\372\225" +
"\146\122\333\032\211\274\337\273\007\262\001\177\207\312\216\210" +
"\272\155\214\104\076\350\065\066\331\033\356\234\252\367\222\235" +
"\274\173\040\073\360\127\376\077\201\250\033\373\330\144\157\270" +
"\163\152\114\202\054\034\125\263\355\350\345\347\027\167\356\257" +
"\243\070\351\155\162\341\370\364\157\325\313\230\324\076\264\111" +
"\052\255\272\267\356\062\351\262\341\047\165\044\013\167\365\036" +
"\277\272\124\267\127\257\353\327\155\274\206\237\324\030\150\372" +
"\016\233\266\255\356\072\164\215\341\047\125\023\160\027\357\361" +
"\253\113\165\173\365\272\176\335\306\053\275\244\352\143\231\073" +
"\170\367\060\064\172\233\356\352\335\303\064\351\045\265\053\235" +
"\243\016\360\356\241\255\224\172\355\133\172\111\015\042\027\170" +
"\367\100\365\164\156\336\015\125\233\177\233\312\277\115\265\247" +
"\211\335\335\272\046\374\167\124\337\101\324\215\175\154\262\007" +
"\117\352\167\021\165\143\037\233\354\361\350\317\243\277\075\075" +
"\372\337\321\272\046\174\116\375\076\242\156\354\143\223\075\316" +
"\251\234\123\355\351\234\172\047\353\232\174\225\212\354\150\102" +
"\357\214\252\235\144\122\067\360\356\141\350\064\161\043\347\026" +
"\256\005\376\070\365\307\210\272\261\217\115\366\370\070\225\217" +
"\123\355\351\234\274\207\165\315\364\216\376\135\351\255\266\247" +
"\167\017\064\077\316\251\234\123\355\351\354\260\227\165\115\046" +
"\225\111\265\247\111\335\333\272\146\134\107\177\375\037\356\143" +
"\124\147\137\213\072\236\364\377\260\137\151\175\177\135\016\320" +
"\345\100\317\236\274\350\377\373\040\316\251\234\123\355\151\262" +
"\016\266\256\011\177\225\352\127\210\272\261\217\115\366\070\247" +
"\162\116\265\247\163\352\041\326\065\341\337\110\341\366\067\117" +
"\236\143\223\075\170\122\067\102\324\215\175\154\262\027\327\163" +
"\377\066\364\150\307\157\102\117\220\076\042\070\164\236\375\375" +
"\223\252\311\163\373\216\023\035\233\237\262\126\242\151\212\366" +
"\323\061\374\223\332\025\347\324\064\351\275\340\360\171\366\117" +
"\043\251\101\144\175\357\036\162\244\351\072\042\210\234\347\335" +
"\107\001\376\214\352\066\210\272\261\217\115\366\340\111\165\373" +
"\216\023\317\261\311\036\217\376\324\114\037\001\034\351\335\103" +
"\001\076\247\272\174\163\241\367\330\144\017\236\124\267\357\342" +
"\363\034\233\354\301\223\352\370\055\077\176\143\223\075\076\116" +
"\245\146\372\070\065\212\117\036\146\122\251\231\046\065\212\357" +
"\035\360\117\052\177\233\032\017\115\345\335\274\173\230\004\376" +
"\070\365\166\210\272\261\217\115\366\374\347\324\256\370\173\377" +
"\064\351\174\175\255\357\344\352\202\111\245\176\150\122\347\372" +
"\246\270\064\222\032\370\214\312\215\046\354\030\357\036\012\114" +
"\052\065\323\244\036\353\335\103\201\111\245\146\232\324\343\274" +
"\173\050\244\221\324\062\275\345\216\367\356\201\154\350\317\362" +
"\204\266\327\205\277\112\265\015\242\156\354\143\223\075\154\122" +
"\365\076\163\042\242\056\345\007\236\324\223\020\165\051\077\360" +
"\117\373\171\043\242\156\354\143\223\075\170\122\337\204\250\033" +
"\373\330\144\017\236\324\067\043\352\306\076\066\331\343\047\250" +
"\361\023\324\354\151\117\227\377\357\164\361\144\253\232\360\127" +
"\251\156\201\250\233\312\370\144\047\275\127\376\273\030\211\134" +
"\125\234\352\175\373\024\337\116\322\246\267\337\335\275\173\030" +
"\166\122\365\026\276\207\167\017\144\143\360\111\275\247\167\017" +
"\144\143\270\111\325\224\336\253\162\376\124\257\136\206\102\157" +
"\303\323\306\247\367\356\173\154\370\357\250\356\203\250\113\371" +
"\361\233\123\203\310\206\175\217\111\070\072\053\335\027\131\077" +
"\275\243\177\020\131\317\273\007\132\225\314\373\365\071\136\032" +
"\111\015\174\047\265\033\115\344\375\275\173\050\244\221\324\151" +
"\364\326\174\200\167\017\204\265\074\251\101\026\256\230\164\115" +
"\275\354\367\343\323\053\053\333\377\000\155\221\114\350\317\351" +
"\117\377\073\135\174\240\167\057\263\030\306\234\072\311\110\344" +
"\022\357\036\310\306\260\223\132\030\311\202\353\047\330\024\343" +
"\127\227\352\366\352\165\375\272\215\327\360\223\032\003\115\337" +
"\021\323\266\325\135\207\256\121\175\234\272\370\040\135\036\254" +
"\313\351\343\363\147\350\162\346\322\265\165\375\041\325\012\272" +
"\355\241\272\234\325\133\313\055\151\117\017\363\356\241\057\372" +
"\177\175\370\034\373\236\075\076\175\204\135\107\366\206\075\247" +
"\352\255\377\110\357\036\310\006\374\267\251\217\102\324\245\374" +
"\014\176\116\175\264\167\017\144\143\360\111\175\214\167\017\144" +
"\003\176\364\177\054\242\056\345\007\236\324\307\041\352\122\176" +
"\006\177\364\177\274\167\017\144\143\360\111\175\202\167\017\144" +
"\143\360\111\175\242\167\017\144\143\360\111\175\222\167\017\144" +
"\003\376\214\352\311\210\272\224\237\064\347\124\275\007\074\305" +
"\273\007\352\127\262\111\175\252\167\017\324\257\064\223\332\326" +
"\110\026\370\111\303\003\061\370\244\362\323\333\007\142\360\111" +
"\165\377\344\057\262\061\370\244\362\023\324\006\142\330\111\325" +
"\147\136\117\363\356\201\154\300\137\117\175\072\242\056\345\147" +
"\360\163\352\071\336\075\220\215\301\047\365\031\336\075\220\215" +
"\301\047\365\231\336\075\220\215\301\047\365\131\336\075\220\015" +
"\370\063\252\147\043\352\122\176\006\077\247\076\307\273\007\262" +
"\061\370\244\076\327\273\007\262\061\370\244\076\317\273\007\262" +
"\061\370\244\076\337\273\007\262\061\370\244\276\300\273\007\262" +
"\061\370\244\276\320\273\007\262\061\370\244\276\310\273\007\262" +
"\061\370\244\276\330\273\007\262\061\370\244\276\304\273\007\262" +
"\221\146\122\065\201\347\266\270\316\171\175\364\102\263\321\237" +
"\317\371\343\323\227\266\271\176\232\111\155\113\157\205\227\171" +
"\367\100\066\322\113\152\340\267\121\106\101\147\201\227\367\071" +
"\136\172\111\255\243\267\332\005\336\075\020\326\060\222\132\147" +
"\044\013\267\364\356\201\354\244\221\324\300\157\370\165\243\307" +
"\253\127\170\367\120\110\043\251\263\322\133\371\225\336\075\220" +
"\015\154\122\365\010\174\023\104\335\266\143\057\215\357\331\007" +
"\331\200\047\365\346\210\272\155\307\136\032\337\263\017\262\201" +
"\116\252\374\025\121\067\225\361\311\116\232\217\123\365\361\347" +
"\253\274\173\240\176\301\377\342\357\325\210\272\155\214\104\376" +
"\356\065\066\331\113\163\116\155\103\357\045\257\361\356\201\354" +
"\014\067\251\125\232\334\123\275\173\110\235\336\206\257\365\032" +
"\173\330\111\325\133\366\102\357\036\310\306\340\223\372\072\357" +
"\036\310\306\340\223\372\172\357\036\310\306\340\223\372\006\357" +
"\036\310\306\160\223\072\022\371\200\167\017\144\047\315\244\352" +
"\134\231\124\277\064\277\144\223\372\106\357\036\250\137\311\046" +
"\365\115\336\075\120\277\222\115\352\233\275\173\240\176\045\233" +
"\324\213\274\173\240\176\045\233\324\267\170\367\100\375\112\066" +
"\251\157\365\356\201\372\225\154\122\337\346\335\003\365\053\315" +
"\244\266\065\022\271\314\273\007\262\221\146\122\165\116\175\273" +
"\167\017\324\057\370\173\376\337\201\250\333\306\110\344\153\136" +
"\143\223\275\064\347\324\066\364\136\162\261\167\017\144\007\376" +
"\267\251\237\104\324\215\175\154\262\207\113\152\220\205\205\312" +
"\371\305\206\353\256\030\237\256\126\163\331\312\322\372\352\272" +
"\254\121\271\174\315\031\172\133\253\264\276\166\327\375\143\242" +
"\375\257\343\335\103\225\036\317\336\151\135\023\076\247\176\023" +
"\121\067\366\261\311\036\074\251\337\103\324\215\175\154\262\347" +
"\377\214\112\217\136\037\167\034\373\023\136\143\307\110\217\332" +
"\357\362\356\141\022\076\116\345\343\124\173\232\370\167\133\327" +
"\204\037\375\177\204\250\033\373\330\144\017\236\324\237\040\352" +
"\306\076\066\331\203\047\365\247\210\272\261\217\115\366\340\111" +
"\375\031\242\156\354\143\223\075\377\347\376\145\372\110\174\037" +
"\243\072\373\132\324\361\244\377\207\375\112\353\373\353\162\200" +
"\056\007\172\366\344\105\377\337\357\211\053\251\223\004\176\043" +
"\205\033\115\311\173\275\173\050\060\251\324\114\223\372\076\357" +
"\036\012\360\307\251\227\043\352\306\076\066\331\213\153\116\345" +
"\343\324\153\360\161\352\065\364\377\375\176\370\234\372\133\104" +
"\335\330\307\046\173\360\157\371\331\004\121\067\366\261\311\036" +
"\074\251\133\041\352\306\076\066\331\203\047\365\326\210\272\261" +
"\217\115\366\342\172\106\325\106\220\205\315\274\173\240\356\364" +
"\131\321\045\363\354\017\237\123\167\100\324\215\175\154\262\007" +
"\117\352\116\210\272\261\217\115\366\340\111\335\026\121\067\366" +
"\261\311\036\074\251\267\105\324\215\175\154\262\007\117\252\333" +
"\263\037\317\261\311\036\074\251\233\042\352\306\076\066\331\203" +
"\047\165\157\104\135\312\017\074\251\373\115\277\026\321\164\360" +
"\244\356\217\250\113\371\111\343\167\124\201\357\244\166\023\144" +
"\061\212\357\112\114\043\251\263\322\071\175\344\335\003\331\200" +
"\037\375\367\102\324\215\175\154\262\007\377\114\352\017\042\352" +
"\266\034\373\103\136\143\223\075\370\234\272\065\242\156\354\143" +
"\223\075\370\234\372\141\104\135\312\017\074\251\037\101\324\245" +
"\374\370\074\367\327\004\177\264\317\361\050\175\360\307\251\353" +
"\041\352\266\035\173\151\174\317\076\310\106\032\257\247\006\276" +
"\362\357\106\217\177\037\363\356\241\220\106\122\055\350\055\176" +
"\252\167\017\251\323\333\360\264\361\151\357\237\170\017\377\144" +
"\212\253\021\165\123\031\237\354\300\237\373\363\033\037\310\004" +
"\174\116\065\377\146\202\024\306\046\173\360\244\276\007\121\067" +
"\366\261\311\036\074\251\156\237\022\353\071\066\331\203\047\325" +
"\355\123\142\075\307\046\173\176\257\122\005\221\015\373\036\223" +
"\160\364\331\063\364\273\277\323\170\075\065\360\225\177\067\232" +
"\300\117\171\367\120\110\043\251\145\101\204\277\031\215\200\046" +
"\370\323\175\216\307\244\322\154\064\251\237\351\163\274\364\222" +
"\332\205\336\232\237\365\356\201\154\014\076\251\237\363\356\201" +
"\154\014\043\251\232\310\113\275\173\040\254\364\222\032\370\070" +
"\065\012\072\073\174\276\317\361\322\110\152\340\253\124\156\064" +
"\221\137\360\356\241\260\074\251\332\325\203\164\171\260\056\247" +
"\217\317\237\241\313\231\113\327\326\365\207\124\053\350\266\207" +
"\352\162\126\157\055\323\265\350\355\377\360\071\366\075\173\174" +
"\372\105\273\216\354\245\061\247\316\152\044\362\025\357\036\310" +
"\006\374\375\251\137\102\324\245\374\014\173\116\325\173\312\227" +
"\275\173\040\033\360\071\365\062\104\135\312\317\340\347\124\076" +
"\116\035\210\301\047\365\253\336\075\220\215\301\047\365\153\336" +
"\075\220\215\301\047\365\353\336\075\220\215\301\047\365\033\336" +
"\075\220\015\370\163\377\157\042\352\122\176\322\234\123\365\036" +
"\160\256\167\017\064\037\375\031\176\253\313\365\323\114\152\133" +
"\043\131\330\303\273\007\262\221\136\122\003\337\365\027\005\235" +
"\023\277\335\347\170\351\045\265\216\336\152\337\361\356\201\260" +
"\160\111\015\262\260\120\071\277\330\160\335\025\343\323\325\152" +
"\056\133\131\132\137\135\227\065\052\227\257\071\103\157\153\225" +
"\326\327\356\272\177\114\264\377\165\274\173\250\322\231\343\273" +
"\326\065\341\317\375\277\207\250\113\371\031\306\321\277\316\110" +
"\344\157\336\075\220\235\364\222\252\363\364\066\336\075\344\114" +
"\157\377\357\173\214\233\136\122\273\320\133\365\007\336\075\220" +
"\215\301\047\365\207\336\075\220\215\301\047\365\107\336\075\220" +
"\215\301\047\365\307\336\075\220\015\370\047\375\366\372\351\005" +
"\261\214\115\366\340\111\165\373\124\003\317\261\311\336\340\217" +
"\376\077\361\356\201\154\014\076\251\077\365\356\201\154\014\076" +
"\251\077\363\356\201\154\014\067\251\043\021\376\135\352\200\360" +
"\275\124\174\057\225\075\075\226\375\334\272\046\374\271\277\333" +
"\157\063\075\307\046\173\234\123\071\247\332\323\071\365\162\353" +
"\232\360\071\325\374\050\220\302\330\144\157\270\317\250\012\043" +
"\131\070\331\273\007\262\061\370\244\236\342\335\003\331\200\037" +
"\375\177\201\250\033\373\330\144\017\235\324\205\200\250\033\373" +
"\330\144\057\215\243\177\340\167\247\270\321\347\361\121\034\233" +
"\340\163\352\236\210\272\261\217\115\366\340\177\105\375\113\104" +
"\135\312\017\074\251\277\102\324\245\374\244\361\070\165\126\043" +
"\221\253\212\123\275\307\234\342\333\111\332\364\366\373\265\167" +
"\017\374\155\052\177\233\152\117\223\375\033\353\232\303\236\123" +
"\365\026\373\255\167\017\144\003\376\312\377\147\020\165\143\037" +
"\233\354\301\223\372\131\104\335\330\307\046\173\360\244\176\016" +
"\121\067\366\261\311\036\074\251\227\042\352\306\076\066\331\203" +
"\047\325\355\073\343\075\307\046\173\360\244\272\175\153\272\347" +
"\330\144\017\236\124\267\157\055\367\034\233\354\015\376\365\324" +
"\337\171\367\100\066\006\237\324\053\274\173\040\033\360\167\250" +
"\374\036\121\227\362\223\336\234\032\370\315\151\121\320\131\350" +
"\312\076\307\113\057\251\165\364\126\373\203\167\017\204\065\214" +
"\244\116\062\022\371\223\167\017\144\143\330\111\325\271\366\217" +
"\336\075\220\015\370\353\251\177\106\324\115\145\174\262\003\117" +
"\152\247\357\160\037\312\330\144\017\236\124\267\357\203\362\034" +
"\233\354\245\361\070\065\360\357\375\335\350\143\375\050\236\225" +
"\302\137\371\277\012\121\227\362\003\077\372\363\165\116\062\001" +
"\117\352\137\020\165\143\037\233\354\245\361\070\165\126\372\350" +
"\203\257\122\015\104\172\111\015\042\067\364\356\041\165\172\017" +
"\116\356\170\003\377\004\265\073\043\352\306\076\066\331\113\157" +
"\116\355\102\347\216\277\172\367\100\066\340\317\250\336\216\250" +
"\033\373\330\144\357\377\001\211\362\255\100\057\346\007\000"
});

public static final byte[] shiftableSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\134\277\157\324\060" +
"\024\176\111\257\064\110\040\105\260\034\003\122\100\110\060\240" +
"\016\210\005\261\104\025\060\040\026\304\330\251\110\110\024\101" +
"\165\264\167\320\251\244\114\114\350\044\126\206\356\060\300\000" +
"\023\103\005\113\067\372\077\060\063\063\041\161\271\336\345\102" +
"\211\375\045\176\266\343\224\144\340\244\276\057\357\227\035\373" +
"\275\057\016\357\177\322\374\140\235\116\057\337\171\264\362\154" +
"\145\161\320\137\175\274\270\264\332\277\367\240\177\375\311\231" +
"\113\235\357\257\157\364\174\242\315\036\221\377\153\143\235\302" +
"\303\250\265\337\257\266\256\135\175\167\156\216\274\145\352\334" +
"\137\355\157\364\311\137\276\275\331\033\051\115\177\243\316\247" +
"\123\037\007\157\046\072\150\364\373\367\265\271\361\224\266\310" +
"\037\244\377\316\247\200\304\217\272\027\276\105\123\171\374\057" +
"\200\072\100\003\000\170\123\100\025\001\045\372\124\121\130\354" +
"\166\242\337\355\142\201\077\261\344\241\121\230\002\012\106\041" +
"\215\141\034\210\252\206\335\156\074\372\011\206\312\032\274\221" +
"\017\205\243\122\302\173\010\270\022\165\057\377\340\150\330\077" +
"\173\167\201\116\244\156\176\171\150\312\311\363\154\015\346\001" +
"\303\256\227\115\063\272\270\362\131\301\304\201\100\062\131\051" +
"\240\374\245\070\233\043\231\006\010\200\076\270\000\200\023\006" +
"\152\340\047\052\365\101\372\144\225\062\121\167\046\133\200\256" +
"\031\325\002\164\001\032\361\340\340\325\336\201\114\266\000\113" +
"\333\201\260\262\236\354\372\214\002\123\126\264\127\027\350\352" +
"\030\374\335\040\046\171\331\013\226\124\111\312\300\326\252\067" +
"\043\255\340\150\012\374\375\223\250\163\121\235\241\255\240\236" +
"\021\035\006\132\172\260\050\323\041\130\256\245\000\334\101\231" +
"\006\244\204\101\134\223\155\055\325\131\355\031\154\001\055\300" +
"\304\163\101\054\015\055\340\277\002\060\132\212\204\362\127\351" +
"\152\036\277\163\330\243\133\113\333\343\231\034\253\171\255\341" +
"\101\101\076\170\057\205\252\143\271\152\205\222\003\072\243\362" +
"\256\305\206\100\343\253\241\246\011\012\337\113\351\115\242\203" +
"\221\117\337\057\034\134\341\333\343\256\005\252\313\206\275\335" +
"\074\211\046\056\024\000\162\167\027\003\076\100\037\020\051\145" +
"\101\003\002\340\375\300\102\024\030\040\067\221\316\244\130\351" +
"\316\022\303\314\165\056\277\151\165\172\211\231\331\214\243\140" +
"\233\150\001\356\000\162\102\123\032\370\046\362\145\140\301\156" +
"\345\106\036\154\000\034\170\375\141\003\300\116\124\116\130\233" +
"\011\343\076\310\252\236\104\152\133\130\371\352\030\075\034\267" +
"\371\227\272\110\203\162\253\006\155\153\056\337\005\243\304\115" +
"\200\154\352\240\243\206\161\046\133\170\261\253\004\310\251\037" +
"\046\162\300\261\342\062\147\146\342\330\163\340\003\214\002\152" +
"\300\200\236\012\240\172\377\203\207\125\110\167\250\117\170\003" +
"\254\206\310\113\261\100\234\021\060\320\062\357\164\007\314\137" +
"\166\264\034\056\020\071\027\061\125\143\333\073\223\300\052\254" +
"\066\252\341\050\255\246\115\242\150\224\342\120\135\066\052\073" +
"\241\345\364\350\354\170\053\005\113\235\012\341\020\126\315\053" +
"\336\252\347\130\266\241\246\027\213\036\102\200\275\020\062\057" +
"\050\041\230\171\141\246\324\212\206\161\016\156\372\142\100\051" +
"\023\223\053\114\344\000\257\213\000\120\003\002\370\212\171\210" +
"\063\231\132\151\122\021\120\030\005\324\000\243\200\200\234\120" +
"\021\300\067\301\217\302\202\017\161\046\063\106\033\272\020\146" +
"\376\366\102\232\050\142\373\300\007\360\023\305\007\130\210\202" +
"\157\202\357\203\205\104\131\360\101\003\135\146\236\221\003\211" +
"\222\225\107\011\260\015\013\111\140\133\007\000\362\055\260\176" +
"\212\020\200\115\172\061\372\126\340\075\253\263\020\026\361\016" +
"\020\315\021\001\200\171\046\062\231\311\140\071\050\050\111\343" +
"\114\056\140\252\240\011\254\241\074\051\130\130\263\212\347\201" +
"\171\112\104\203\011\016\007\146\216\053\232\345\134\121\003\066" +
"\021\114\277\136\237\033\326\224\132\203\224\230\214\257\252\334" +
"\345\033\374\052\210\117\265\150\347\177\204\251\143\061\172\365" +
"\235\054\262\162\124\337\370\070\226\170\230\042\064\072\073\042" +
"\333\271\273\224\000\336\166\250\230\271\164\070\105\347\364\252" +
"\263\161\011\010\143\374\147\026\207\024\147\062\325\375\170\234" +
"\251\257\153\014\312\020\244\324\215\366\213\357\203\215\050\314" +
"\127\231\056\000\140\036\132\047\217\016\300\113\302\100\365\116" +
"\161\103\227\000\247\124\117\116\350\070\100\313\357\317\215\237" +
"\132\361\266\245\316\111\155\133\000\270\360\037\051\064\002\000" +
"\063\331\200\363\117\006\017\003\330\350\370\065\000\100\347\133" +
"\275\367\340\333\164\041\061\234\016\252\021\000\062\015\340\154" +
"\163\115\211\077\277\300\125\336\213\305\217\226\174\203\344\171" +
"\135\377\334\113\343\256\374\071\132\145\332\061\347\200\042\100" +
"\365\213\122\311\304\037\343\153\356\167\371\032\134\000\200\050" +
"\274\151\361\141\263\360\057\361\141\230\334\353\062\255\203\271" +
"\371\316\007\360\227\016\113\116\312\000\116\120\375\172\302\224" +
"\232\060\310\323\023\023\040\146\147\313\021\277\106\023\323\020" +
"\000\314\003\004\220\334\004\143\241\324\364\230\033\315\040\330" +
"\305\245\252\041\200\077\172\360\105\065\337\304\141\015\177\000" +
"\110\120\204\361\117\134\000\000"
});

public static final byte[] layoutSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\314\241\116\303\140" +
"\030\005\320\217\322\171\002\012\307\334\324\024\206\340\026\024" +
"\301\115\126\155\311\104\027\040\145\375\273\124\055\110\024\206" +
"\307\030\006\261\347\330\223\240\121\044\254\311\314\366\012\234" +
"\053\356\065\047\367\363\073\172\315\042\056\212\207\371\144\071" +
"\031\066\251\174\034\216\312\064\236\245\333\247\313\101\276\175" +
"\277\253\262\210\266\212\310\176\352\105\234\035\253\347\337\267" +
"\325\315\365\272\177\032\047\105\344\323\062\325\051\262\342\276" +
"\255\166\247\335\136\345\233\363\257\346\143\377\021\273\075\114" +
"\133\277\304\052\262\246\353\136\007\136\001\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\376\005\370\003\000\041\023\303\067\142\000\000"
});

public static final byte[] prefixSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\314\241\116\102\161" +
"\034\005\340\237\327\113\147\230\154\330\110\044\212\263\061\222" +
"\263\031\157\302\315\160\031\272\013\367\177\331\115\144\023\205" +
"\307\320\142\340\071\174\022\263\311\115\330\054\360\012\174\047" +
"\234\123\276\235\217\357\350\064\313\270\052\036\146\323\325\164" +
"\330\244\162\076\034\227\351\361\071\335\275\134\017\362\257\315" +
"\244\312\042\332\052\042\373\251\227\321\075\125\257\277\157\353" +
"\333\321\373\315\145\134\024\221\077\225\251\116\221\025\367\155" +
"\265\077\075\154\077\337\365\076\233\355\377\107\354\367\070\155" +
"\275\210\165\144\315\241\073\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\347" +
"\003\376\000\013\013\001\103\067\142\000\000"
});

public static final byte[] prefixMapsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\315\241\016\001\001" +
"\034\300\341\377\156\074\001\345\336\100\272\240\010\232\211\232" +
"\170\351\202\160\306\166\147\167\046\171\004\117\242\170\022\233" +
"\054\153\124\225\040\252\244\357\227\177\333\167\274\107\267\335" +
"\104\077\317\147\313\142\133\144\155\123\256\262\111\331\314\027" +
"\315\070\275\074\116\243\333\165\230\104\354\252\210\344\371\036" +
"\173\137\276\165\072\350\234\017\323\352\363\105\135\375\273\266" +
"\216\175\044\150\064\032\215\106\243\321\150\064\032\215\106\243" +
"\321\150\064\032\215\106\243\321\150\064\032\215\106\243\321\150" +
"\064\032\215\106\243\321\150\064\032\215\106\243\321\150\064\032" +
"\215\106\243\321\150\064\032\215\106\243\321\150\064\032\215\106" +
"\243\321\150\064\032\215\106\243\321\150\064\032\215\106\243\321" +
"\150\064\032\215\106\243\321\150\064\032\215\106\243\321\150\064" +
"\032\215\106\243\321\150\064\032\215\106\243\321\150\064\032\215" +
"\106\243\321\150\064\032\215\106\243\321\150\064\032\215\106\243" +
"\321\150\064\032\215\106\243\321\150\064\032\215\106\243\321\150" +
"\064\032\215\106\243\321\150\064\032\215\106\243\321\150\064\032" +
"\215\106\243\321\150\064\032\215\106\243\321\150\064\032\215\106" +
"\243\321\150\064\032\215\106\243\321\150\064\032\215\106\243\321" +
"\150\064\032\215\106\243\321\150\064\032\215\106\243\321\150\064" +
"\032\215\106\243\321\150\064\032\215\106\243\321\150\064\032\215" +
"\106\243\321\150\064\032\215\106\243\321\150\064\032\215\106\243" +
"\321\150\064\032\215\106\243\321\150\064\032\215\106\243\321\150" +
"\064\032\215\106\243\321\150\064\032\215\106\243\321\150\064\032" +
"\215\106\243\321\150\064\032\215\106\243\321\150\064\032\215\106" +
"\243\321\150\064\032\215\106\243\321\150\064\032\215\106\243\321" +
"\150\064\032\215\106\243\321\150\064\032\215\106\243\321\350\337" +
"\365\002\177\066\161\260\370\154\001\000"
});

public static final byte[] terminalUsesHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\201\051\332\323\167\127\202\132\331\253\115\113\231\030\030" +
"\052\012\030\030\030\012\031\106\301\220\007\000\366\351\030\160" +
"\337\001\000\000"
});

public static final byte[] shiftableUnionHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\270" +
"\210\101\040\053\261\054\121\257\264\044\063\107\317\051\263\044" +
"\070\265\044\357\157\107\235\245\311\152\105\146\006\306\150\006" +
"\226\244\314\222\342\022\006\246\150\257\212\202\322\042\060\255" +
"\300\262\125\150\143\351\144\046\006\206\212\002\006\006\006\246" +
"\337\377\377\337\377\373\377\377\177\006\306\377\140\120\001\000" +
"\264\307\121\260\131\000\000\000"
});

public static final byte[] acceptSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\132\275\216\324\060" +
"\020\036\033\237\224\062\202\212\316\164\124\127\321\234\256\211" +
"\020\025\242\243\334\352\220\050\026\001\132\156\263\350\252\123" +
"\112\052\032\036\003\232\053\170\016\236\204\107\100\042\136\342" +
"\045\227\214\035\217\075\311\371\164\227\042\053\355\174\376\346" +
"\327\343\237\335\037\277\341\150\167\016\217\126\257\336\235\175" +
"\076\073\336\325\353\367\307\317\327\365\353\267\365\351\207\307" +
"\117\325\257\257\057\066\022\340\142\003\040\256\266\347\120\016" +
"\121\037\377\174\271\074\171\366\375\311\003\020\053\120\157\326" +
"\365\266\006\271\172\171\261\151\111\315\247\126\077\037\136\355" +
"\276\165\034\355\173\373\011\056\101\356\314\373\010\373\242\105" +
"\102\005\335\123\306\002\300\013\150\246\000\351\052\164\062\103" +
"\010\040\135\305\064\103\021\252\102\240\000\175\220\357\301\151" +
"\136\310\110\206\146\042\016\025\114\000\004\103\044\275\000\141" +
"\115\364\372\137\304\351\376\357\176\172\045\251\111\043\323\213" +
"\325\100\307\200\362\040\026\156\033\164\240\012\345\062\322\132" +
"\031\353\205\072\250\320\070\240\070\000\252\330\100\205\107\262" +
"\101\000\302\062\140\002\355\022\070\107\320\004\050\022\365\043" +
"\174\212\314\155\036\215\242\037\377\046\172\344\176\354\214\216" +
"\056\044\250\310\011\153\370\122\314\110\165\163\202\260\022\245" +
"\025\232\260\135\052\252\064\313\140\253\362\213\346\355\023\110" +
"\127\242\004\055\203\114\055\317\074\072\262\243\265\245\063\032" +
"\151\227\313\334\302\176\107\005\327\023\126\160\351\002\323\241" +
"\302\107\330\235\336\022\335\063\262\341\316\235\210\271\254\100" +
"\017\141\302\246\151\136\117\113\246\244\016\272\212\314\153\012" +
"\345\053\160\036\172\376\075\335\104\035\215\254\300\041\360\352" +
"\062\332\330\123\015\012\327\305\326\250\026\111\203\231\010\176" +
"\107\021\300\215\233\075\277\240\073\031\073\312\251\342\154\236" +
"\275\160\067\116\245\114\216\015\153\270\042\121\242\367\055\271" +
"\146\360\136\300\265\135\002\031\135\137\005\121\227\162\033\201" +
"\266\333\074\143\226\247\300\244\343\066\156\023\151\027\207\203" +
"\012\044\164\352\214\016\073\100\070\327\006\121\152\172\324\201" +
"\272\072\334\013\122\005\366\072\236\230\050\341\315\275\242\123" +
"\322\004\256\362\051\023\050\172\366\013\232\375\303\211\324\260" +
"\317\315\046\156\244\047\201\355\207\153\012\062\230\237\131\221" +
"\173\103\041\131\250\206\065\040\246\000\350\006\147\017\043\367" +
"\114\332\216\147\221\240\222\256\045\134\024\172\261\365\076\051" +
"\327\343\035\316\000\060\276\011\040\030\141\223\114\256\212\361" +
"\145\363\074\021\050\362\110\203\136\146\077\331\123\052\351\171" +
"\111\161\020\131\334\006\200\200\371\022\071\235\005\037\225\021" +
"\061\120\131\327\363\075\103\204\051\313\350\004\200\354\351\222" +
"\050\361\373\356\273\023\135\321\235\201\170\366\244\025\215\112" +
"\200\343\227\352\036\145\101\247\164\012\150\177\330\270\136\170" +
"\224\075\117\006\363\004\271\060\102\251\346\136\030\263\347\356" +
"\302\346\211\246\106\001\302\212\142\127\312\366\154\363\027\372" +
"\201\044\104\331\053\000\000"
});

public static final byte[] rejectSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\133\363\226\201\265\264" +
"\210\101\070\332\047\053\261\054\121\257\264\044\063\107\317\051" +
"\263\044\070\265\304\072\127\122\203\345\174\237\113\001\023\003" +
"\103\105\001\003\003\343\306\342\042\006\001\164\125\171\177\073" +
"\352\054\115\126\053\062\063\060\106\063\260\044\145\226\024\227" +
"\060\060\105\173\125\024\000\015\005\321\012\054\133\205\066\226" +
"\116\206\232\001\044\213\013\031\352\030\230\112\101\044\353\210" +
"\023\140\142\100\002\002\344\050\240\202\216\101\342\325\201\061" +
"\163\160\306\000\015\134\105\005\043\030\201\330\201\274\230\032" +
"\025\240\203\300\150\374\214\012\320\070\045\321\064\211\021\157" +
"\370\150\112\247\175\221\060\032\033\103\042\027\321\045\206\207" +
"\216\000\351\141\116\227\164\076\060\005\352\320\165\327\140\365" +
"\312\300\230\065\364\042\143\350\010\014\154\366\037\116\002\203" +
"\315\223\103\046\177\215\066\322\006\143\130\017\366\076\320\220" +
"\113\150\043\241\255\061\260\301\103\337\200\034\272\365\041\055" +
"\122\342\300\304\322\140\055\056\206\156\074\016\222\144\076\350" +
"\303\176\144\246\270\301\346\353\301\136\352\014\326\202\176\260" +
"\071\164\260\172\026\241\022\000\254\210\134\213\031\040\000\000" +
""
});

public static final byte[] possibleSetsHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\130\077\157\023\061" +
"\024\177\166\256\345\206\250\230\302\120\230\314\306\124\165\140" +
"\000\261\230\322\001\041\206\112\054\110\145\150\221\030\202\000" +
"\225\066\101\235\252\033\231\120\045\076\006\054\035\330\363\015" +
"\370\044\110\174\001\012\347\213\175\167\255\337\263\235\353\265" +
"\151\304\071\152\056\325\373\371\375\367\357\071\371\376\013\026" +
"\106\173\160\163\353\371\333\235\117\073\253\243\341\340\335\352" +
"\372\140\370\342\315\360\321\373\333\367\222\237\137\066\166\071" +
"\300\301\056\000\073\336\337\003\161\026\365\341\317\347\303\207" +
"\367\277\335\355\001\333\202\344\365\140\270\077\004\276\365\354" +
"\140\067\127\252\237\062\371\261\174\074\372\152\164\344\357\373" +
"\037\341\020\370\110\277\057\344\377\363\277\223\165\002\154\362" +
"\301\001\000\364\266\363\247\312\377\244\000\346\152\060\302\142" +
"\011\314\204\025\220\200\014\252\105\151\120\327\062\130\063\000" +
"\341\000\222\220\206\155\353\044\363\070\031\214\302\254\014\007" +
"\160\155\042\211\322\000\042\043\302\204\045\130\314\237\151\256" +
"\341\072\252\041\215\164\022\156\240\000\131\312\071\231\352\374" +
"\225\106\231\130\306\001\112\225\141\366\120\100\026\110\265\202" +
"\000\200\205\213\245\154\317\321\000\253\141\305\005\060\353\242" +
"\327\373\025\014\160\107\253\056\366\047\040\044\246\241\122\100" +
"\071\047\225\315\164\250\006\011\005\310\374\046\372\072\376\203" +
"\374\343\032\210\304\157\102\010\205\001\104\011\140\264\223\322" +
"\137\003\263\172\160\213\000\074\031\027\200\261\326\060\306\000" +
"\257\002\231\254\230\141\015\007\244\045\140\023\007\350\350\165" +
"\071\123\045\340\304\005\344\255\250\144\345\203\364\036\230\043" +
"\304\204\356\145\005\204\140\033\023\150\116\126\106\243\044\000" +
"\365\063\344\222\266\265\251\060\001\232\312\006\202\314\230\247" +
"\214\073\064\156\005\150\016\365\000\050\153\355\354\114\010\133" +
"\125\046\030\341\214\042\262\177\212\360\361\060\014\341\173\053" +
"\256\367\372\312\223\372\062\304\361\235\375\122\271\063\042\354" +
"\316\004\027\160\312\226\242\235\110\275\014\217\251\074\335\176" +
"\014\125\235\171\132\103\204\124\142\204\115\252\344\324\361\112" +
"\075\347\116\151\165\176\047\044\342\245\050\270\137\113\023\052" +
"\076\351\263\311\121\101\106\355\230\116\140\334\177\000\305\171" +
"\105\110\237\131\266\164\167\246\172\247\002\113\350\336\161\021" +
"\000\364\110\015\046\267\143\262\065\326\251\235\217\153\366\063" +
"\124\165\126\001\120\322\173\111\331\334\040\154\012\333\011\371" +
"\363\067\125\117\232\171\161\057\053\047\227\174\031\112\351\024" +
"\312\112\205\103\243\214\321\035\317\121\001\237\020\344\104\135" +
"\240\154\000\355\115\032\275\043\176\074\024\002\075\156\120\357" +
"\026\053\367\056\143\156\060\173\177\045\334\307\347\205\315\141" +
"\223\161\120\055\142\034\364\151\225\027\074\047\152\336\065\234" +
"\023\115\307\101\307\372\347\235\023\346\142\114\174\071\120\223" +
"\013\265\040\002\116\351\113\225\212\034\044\074\004\140\044\300" +
"\224\053\013\001\024\125\256\156\322\030\200\147\322\324\022\201" +
"\063\262\304\342\244\106\120\135\245\214\260\071\345\244\321\002" +
"\174\240\060\242\032\012\273\271\027\002\116\355\110\274\156\167" +
"\203\344\312\017\022\004\060\375\040\011\316\213\211\063\210\227" +
"\265\235\155\015\222\013\344\162\143\123\371\302\220\250\312\253" +
"\100\362\147\073\336\141\311\042\274\054\142\147\073\044\137\127" +
"\351\222\174\035\340\053\106\363\051\220\206\234\362\322\115\067" +
"\046\142\307\004\301\351\355\121\067\013\001\002\364\212\230\140" +
"\246\001\346\225\374\347\233\302\215\040\164\100\233\160\373\345" +
"\062\265\012\001\212\025\117\304\044\317\166\164\332\321\151\200" +
"\116\247\146\115\224\024\073\212\103\155\066\275\245\102\251\325" +
"\103\063\324\055\325\120\335\334\122\134\014\223\211\020\040\364" +
"\353\125\236\203\271\341\302\162\067\351\224\304\004\061\124\007" +
"\276\160\072\302\232\045\141\305\360\322\254\330\005\077\074\225" +
"\253\104\070\125\232\374\361\052\322\166\053\334\340\077\372\360" +
"\177\236\323\363\175\035\123\041\367\257\310\321\070\377\011\270" +
"\244\106\177\112\011\066\303\141\065\075\001\146\115\331\277\161" +
"\155\332\116\067\316\242\331\146\335\113\306\204\042\253\062\175" +
"\253\134\144\241\033\263\113\343\337\263\273\352\314\252\072\255" +
"\044\277\313\155\104\347\067\111\241\067\103\115\343\214\012\147" +
"\036\274\015\072\323\331\364\331\164\001\377\000\025\334\263\372" +
"\301\061\000\000"
});

public static final byte[] cMapHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\321\071\116\235\061" +
"\024\006\320\377\061\047\314\123\030\303\074\207\051\314\011\004" +
"\152\212\254\040\015\113\100\110\104\154\210\216\212\222\045\041" +
"\261\007\276\047\271\100\117\024\257\240\102\307\322\321\325\265" +
"\175\257\055\373\341\245\152\277\275\251\132\376\135\376\175\272" +
"\132\371\377\374\170\337\122\125\167\327\125\255\252\217\336\046" +
"\264\307\122\103\336\114\135\263\275\353\261\255\141\276\047\266" +
"\343\167\214\307\267\070\214\203\130\213\255\230\213\357\115\372" +
"\022\077\112\357\321\022\247\142\076\176\305\154\214\304\131\344" +
"\175\252\325\030\216\231\070\055\153\157\255\064\344\313\061\030" +
"\023\045\357\170\247\346\044\006\142\077\056\242\273\334\143\050" +
"\026\142\062\272\342\074\216\342\070\326\243\126\352\133\343\117" +
"\154\306\164\364\305\116\354\306\142\154\304\327\350\057\373\307" +
"\242\063\176\226\274\176\326\336\007\376\035\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000\000" +
"\000\000\000\000\000\000\000\000\237\324\053\301\060\102\067\033" +
"\000\004\000"
});

public static final byte[] deltaHash = edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.literalToByteArray
(new String[]{ "\037\213\010\000\000\000\000\000\000\000\355\234\005\264\034\105" +
"\026\206\373\046\004\167\017\356\272\270\103\200\340\004\010\356" +
"\101\202\073\044\100\040\270\113\260\340\356\020\064\350\072\353" +
"\356\356\156\254\261\313\372\056\353\273\335\234\251\234\241\373" +
"\376\267\172\172\252\273\353\166\325\075\247\062\157\156\127\375" +
"\367\353\377\335\252\231\314\313\313\314\327\223\121\123\316\114" +
"\106\116\230\060\156\211\067\136\335\153\364\364\151\143\106\044" +
"\311\324\111\111\102\057\245\371\021\023\306\215\177\145\342\152" +
"\347\274\366\362\214\136\072\031\233\004\036\123\046\047\027\047" +
"\251\033\263\234\310\276\036\331\033\263\245\143\124\337\363\331" +
"\323\061\107\072\346\114\307\134\351\230\273\367\070\117\357\372" +
"\274\275\307\371\372\326\314\237\216\005\172\137\057\330\173\134" +
"\050\035\013\247\143\221\164\054\332\067\167\261\164\054\236\216" +
"\045\322\261\144\072\106\367\135\313\306\122\275\307\245\323\261" +
"\114\072\226\115\307\162\351\130\076\035\053\364\315\133\061\035" +
"\053\245\143\345\164\254\222\216\125\323\261\132\072\126\357\135" +
"\137\243\157\356\232\351\130\053\035\153\027\234\170\133\337\110" +
"\100\256\377\132\026\353\344\256\041\215\044\227\137\227\311\345" +
"\237\163\265\315\327\353\011\214\111\356\332\372\200\143\326\032" +
"\047\116\040\162\233\023\134\116\322\103\353\021\067\322\143\071" +
"\013\116\154\320\067\066\314\075\267\215\215\006\234\357\325\050" +
"\070\221\217\215\101\276\211\150\264\166\301\211\115\222\301\167" +
"\307\246\211\320\165\271\065\076\356\216\315\262\204\265\047\362" +
"\261\171\311\171\352\242\163\047\346\026\305\173\264\366\304\226" +
"\131\142\340\236\350\154\014\345\304\126\216\141\132\215\316\354" +
"\216\061\214\016\322\143\071\013\116\154\355\321\150\224\307\131" +
"\117\154\203\274\316\255\311\036\267\355\175\235\257\231\257\207" +
"\152\157\307\160\124\175\025\335\076\101\075\021\154\104\047\114" +
"\104\047\114\070\071\047\166\110\370\175\335\306\073\253\101\317" +
"\211\035\115\056\366\204\211\112\075\261\123\116\144\347\204\377" +
"\036\152\350\011\374\176\242\351\330\245\245\272\205\150\335\011" +
"\157\042\072\141\302\311\153\307\256\211\260\377\162\153\364\234" +
"\023\125\234\100\344\165\070\061\016\160\324\177\142\206\373\071" +
"\146\323\261\133\113\165\013\121\160\142\367\304\276\073\366\310" +
"\211\144\371\361\314\334\046\317\211\075\173\317\367\052\336\243" +
"\222\367\023\336\204\223\023\163\357\334\265\175\200\106\222\310" +
"\337\237\174\075\124\273\235\023\063\230\050\070\261\357\020\143" +
"\277\041\327\127\035\373\273\320\251\345\375\304\001\100\203\233" +
"\233\317\035\330\373\372\040\241\166\334\035\145\343\340\052\213" +
"\072\351\104\245\210\116\230\350\274\023\207\224\235\350\344\304" +
"\074\064\035\023\230\271\375\363\016\313\135\117\022\376\024\344" +
"\346\324\171\142\036\156\162\336\376\135\364\010\241\266\113\047" +
"\216\064\271\316\357\216\322\021\235\060\341\144\167\114\314\135" +
"\073\012\150\230\307\243\231\134\331\163\342\030\300\061\314\073" +
"\253\143\263\104\151\047\216\003\004\022\371\060\347\104\123\047" +
"\346\361\046\027\167\207\011\157\137\073\232\352\211\131\327\242" +
"\023\046\127\311\211\023\054\225\352\164\342\104\146\075\342\106" +
"\172\054\147\147\172\342\044\106\007\351\261\234\255\072\161\062" +
"\223\223\364\320\135\041\156\244\307\162\306\177\301\156\106\174" +
"\025\065\121\160\342\024\217\106\243\074\361\247\201\046\124\276" +
"\166\234\312\160\204\375\052\172\232\300\050\361\261\234\255\070" +
"\161\072\240\262\351\241\273\102\334\046\316\140\326\330\235\160" +
"\035\223\152\322\165\036\252\167\107\377\232\311\214\016\322\143" +
"\071\275\165\342\114\241\366\240\273\243\232\023\301\106\245\236" +
"\070\053\047\342\303\356\030\246\047\316\316\022\261\047\114\124" +
"\352\211\051\071\021\355\075\341\356\304\074\107\252\000\010\120" +
"\216\233\243\307\211\163\245\012\200\040\213\251\114\216\043\327" +
"\343\104\323\273\343\074\206\043\114\047\134\366\304\371\046\327" +
"\252\023\027\060\071\111\017\335\025\342\106\172\054\147\055\116" +
"\134\010\064\154\124\110\117\257\023\056\167\307\105\100\077\317" +
"\241\333\211\213\231\234\244\207\356\012\161\043\075\226\263\365" +
"\367\230\376\376\366\123\225\236\270\044\021\274\316\255\211\347" +
"\204\113\047\056\145\070\342\356\160\026\052\173\242\312\356\270" +
"\014\324\364\177\167\134\056\324\016\347\234\270\002\350\241\365" +
"\210\033\361\261\234\136\072\121\307\356\220\234\270\062\113\264" +
"\352\304\125\114\116\322\103\167\205\270\221\036\313\071\220\023" +
"\127\063\004\022\271\226\236\360\173\167\134\043\324\156\306\211" +
"\370\333\117\306\211\062\161\055\310\137\077\200\106\026\067\010" +
"\327\246\015\240\163\335\200\165\331\150\375\075\246\067\121\351" +
"\234\270\061\047\122\307\071\161\023\250\335\334\071\021\154\070" +
"\171\355\230\236\010\136\347\326\370\370\052\172\163\226\210\075" +
"\141\102\245\023\267\070\324\272\325\174\321\352\073\253\333\230" +
"\234\171\176\273\120\273\231\023\263\111\047\356\140\162\222\036" +
"\272\053\304\215\364\130\316\201\234\270\223\041\220\310\265\234" +
"\230\356\172\342\056\251\002\040\100\071\156\216\036\047\362\327" +
"\356\006\032\066\052\244\247\307\211\173\244\012\200\000\345\270" +
"\071\172\234\100\344\310\211\173\001\225\115\017\335\025\342\106" +
"\172\054\247\267\237\117\124\165\342\276\244\030\141\072\061\150" +
"\117\334\157\162\052\235\170\200\341\150\156\167\074\310\250\331" +
"\310\065\364\304\160\273\343\041\113\245\066\234\170\230\321\101" +
"\172\054\247\312\335\341\272\047\036\311\022\321\011\223\153\305" +
"\211\107\001\225\115\017\335\025\342\106\172\054\147\360\075\361" +
"\230\311\071\161\342\161\100\256\301\011\334\023\361\167\210\215" +
"\023\056\166\307\014\240\301\315\315\347\270\071\361\234\100\265" +
"\375\165\342\011\251\002\040\100\071\156\116\235\116\074\151\162" +
"\265\364\304\123\100\303\106\205\364\362\317\237\146\070\334\367" +
"\104\260\021\317\211\164\074\223\045\124\073\361\254\300\050\361" +
"\261\234\005\047\146\012\144\276\071\121\357\071\341\113\117\074" +
"\047\324\366\327\211\347\245\012\200\000\345\270\071\376\071\361" +
"\002\103\040\221\167\327\011\327\273\343\105\046\047\351\241\273" +
"\102\334\110\217\345\164\342\304\113\122\005\100\200\162\334\034" +
"\075\116\274\054\125\000\004\050\307\315\321\343\104\325\335\321" +
"\266\023\157\357\277\066\220\023\357\140\010\044\162\337\235\320" +
"\337\023\357\144\070\302\164\302\165\117\274\053\113\170\341\304" +
"\273\101\075\124\073\356\016\264\036\161\043\275\376\334\173\114" +
"\256\224\023\357\025\010\044\162\015\116\370\321\023\257\060\071" +
"\111\017\335\025\342\106\172\054\147\301\211\367\011\144\261\047" +
"\242\023\250\312\260\116\274\037\120\331\364\320\135\041\156\244" +
"\307\162\126\162\342\003\226\112\066\047\076\010\250\154\172\350" +
"\256\020\067\322\143\071\235\365\304\207\120\005\100\200\162\334" +
"\234\166\235\370\260\120\145\330\335\241\303\211\370\273\201\321" +
"\211\350\104\164\042\072\121\326\211\062\257\242\037\111\336\032" +
"\335\174\355\250\372\176\242\111\047\076\312\160\064\353\304\307" +
"\030\002\211\074\366\204\315\211\217\003\052\233\036\272\053\304" +
"\155\342\023\314\032\273\023\145\342\223\003\314\125\023\005\047" +
"\076\325\042\114\253\121\151\167\174\072\047\022\317\011\033\271" +
"\026\047\076\223\045\242\023\046\027\254\023\237\315\257\251\305" +
"\211\317\061\032\237\027\250\142\117\014\353\304\027\004\106\211" +
"\217\345\364\322\211\057\002\075\264\036\161\043\076\226\263\026" +
"\047\276\004\064\314\343\227\001\025\322\323\343\304\127\244\012" +
"\200\000\345\270\071\172\234\310\137\373\052\320\260\121\041\075" +
"\235\116\174\115\320\260\121\041\362\072\234\370\172\176\215\227" +
"\047\146\323\075\361\215\054\341\255\023\337\024\152\373\273\073" +
"\276\045\125\000\004\050\307\315\321\343\104\133\273\343\333\002" +
"\243\304\307\162\072\161\342\073\122\005\100\200\162\334\234\166" +
"\172\342\273\002\231\157\075\021\306\356\370\236\120\073\054\047" +
"\312\364\304\367\005\106\211\217\345\364\326\211\037\010\265\365" +
"\364\304\017\201\206\215\012\351\351\162\342\107\250\002\040\100" +
"\071\156\216\056\047\134\357\216\350\204\244\207\326\043\156\244" +
"\307\162\106\047\114\316\211\023\077\226\052\000\002\224\343\346" +
"\350\161\042\366\204\215\034\071\361\023\100\145\323\103\167\205" +
"\270\221\036\313\031\173\042\035\077\315\022\254\023\077\313\051" +
"\276\052\020\110\344\076\073\361\363\374\032\357\172\342\027\202" +
"\036\132\217\270\021\037\313\331\212\023\277\004\124\066\075\164" +
"\127\210\033\351\261\234\336\365\204\244\207\326\043\156\244\227" +
"\237\377\253\054\341\215\023\277\056\241\207\326\043\156\244\307" +
"\162\172\343\304\040\075\361\032\303\021\246\023\203\366\304\157" +
"\204\232\156\235\370\255\124\001\020\240\034\067\047\356\016\124" +
"\073\072\201\326\043\156\244\307\162\326\342\304\353\100\303\106" +
"\205\364\364\072\121\266\047\176\307\344\044\075\164\127\210\033" +
"\351\261\234\152\166\307\357\205\365\210\033\351\261\234\265\070" +
"\361\007\240\141\243\102\172\172\172\342\217\122\005\100\200\162" +
"\334\034\075\116\064\261\073\352\162\342\117\046\127\160\342\317" +
"\002\131\027\235\360\277\047\376\042\324\166\355\304\137\263\204" +
"\267\116\064\331\023\177\313\022\321\011\223\213\116\230\134\301" +
"\211\067\004\262\260\234\360\045\376\336\164\101\157\235\220\342" +
"\037\165\210\306\163\302\344\012\116\374\123\040\013\313\211\046" +
"\173\342\137\114\116\322\103\167\205\270\221\036\313\131\311\211" +
"\177\133\052\305\236\140\053\000\002\224\223\364\320\172\304\215" +
"\364\130\116\325\116\374\107\140\224\370\130\116\325\116\304\236" +
"\160\351\304\177\115\316\211\023\377\003\344\032\234\160\336\023" +
"\304\315\015\322\211\046\173\202\250\357\032\142\224\370\130\116" +
"\215\116\110\075\101\043\222\142\170\357\004\215\144\326\113\172" +
"\350\256\020\067\322\143\071\175\355\011\232\115\250\255\306\011" +
"\032\005\064\154\124\210\134\255\023\116\172\142\166\241\166\120" +
"\116\064\331\023\064\107\226\010\335\011\232\323\344\332\160\202" +
"\346\342\251\254\172\350\256\020\067\322\143\071\103\357\211\067" +
"\277\063\163\147\011\037\235\240\171\200\036\132\217\270\021\037" +
"\313\131\313\253\350\274\100\303\106\205\310\325\072\341\142\167" +
"\320\174\102\355\240\234\150\252\047\150\176\223\153\365\357\035" +
"\013\060\353\045\075\164\127\210\033\351\261\234\056\234\240\005" +
"\245\012\200\000\345\270\071\152\234\320\274\073\272\351\004\055" +
"\304\260\331\152\166\322\011\275\075\101\013\063\353\045\075\164" +
"\127\210\033\351\261\234\052\377\325\100\055\021\167\207\311\165" +
"\305\011\132\204\321\101\172\054\247\263\237\167\054\212\052\000" +
"\002\224\343\346\304\236\100\265\035\073\101\213\145\011\055\116" +
"\320\342\302\172\304\215\364\130\116\055\116\364\077\247\045\030" +
"\216\126\234\240\045\055\225\224\355\016\275\075\121\305\011\032" +
"\015\152\006\347\004\252\111\113\231\134\131\047\150\151\100\040" +
"\221\053\160\042\366\304\254\236\130\306\344\102\167\342\115\067" +
"\226\315\022\255\376\135\164\071\146\275\244\207\356\012\161\043" +
"\075\226\063\366\204\311\371\352\004\055\057\324\366\312\011\132" +
"\101\250\344\302\211\025\161\355\166\235\240\225\000\001\127\251" +
"\023\273\203\126\146\224\202\210\052\237\336\321\052\165\321\264" +
"\031\276\236\230\256\167\007\255\012\152\016\175\142\346\053\065" +
"\345\004\255\006\070\032\073\061\175\161\242\271\023\323\205\023" +
"\264\072\320\260\121\041\362\326\234\240\065\336\252\110\153\012" +
"\004\022\271\307\116\320\132\371\065\241\357\016\132\333\344\052" +
"\175\172\327\041\047\164\367\004\255\303\160\250\166\202\326\145" +
"\326\113\172\350\256\020\067\322\143\071\265\365\004\255\007\070" +
"\132\163\202\326\057\101\136\323\356\360\313\011\261\122\060\116" +
"\320\006\226\112\301\070\341\252\047\150\103\146\275\244\207\356" +
"\012\161\043\075\226\063\356\016\223\053\174\122\263\221\100\026" +
"\224\023\261\047\146\365\304\306\002\131\120\116\224\351\011\332" +
"\304\122\051\030\047\272\326\023\264\151\226\250\324\023\233\131" +
"\052\051\163\242\276\236\240\315\201\206\215\012\221\253\165\242" +
"\215\236\240\055\030\035\244\307\162\226\161\202\266\304\004\042" +
"\171\354\211\032\235\240\255\000\107\160\116\144\217\064\206\341" +
"\160\356\004\155\055\220\171\342\104\354\011\363\234\266\141\070" +
"\202\164\302\145\117\320\266\046\127\351\235\325\330\344\255\241" +
"\330\011\367\075\101\333\241\012\200\000\345\270\071\252\234\210" +
"\075\221\005\155\057\125\000\004\050\307\315\121\343\104\354\011" +
"\053\171\164\302\261\023\264\203\145\075\342\106\174\054\247\006" +
"\047\134\364\004\355\010\152\172\341\004\355\304\254\227\364\270" +
"\153\210\121\342\143\071\353\370\035\142\332\331\235\126\163\241" +
"\161\167\320\056\014\107\153\347\004\355\132\202\334\243\163\242" +
"\235\335\121\066\150\134\363\065\161\304\377\153\300\104\253\257" +
"\035\273\061\353\045\075\356\032\142\224\370\130\116\215\047\146" +
"\130\116\320\356\102\355\240\234\250\322\023\264\007\303\146\253" +
"\331\111\047\364\366\004\215\147\326\113\172\350\256\020\067\322" +
"\143\071\143\117\230\134\164\302\344\242\023\046\127\352\047\304" +
"\173\012\004\022\171\367\234\330\113\040\220\310\165\073\321\100" +
"\320\336\315\325\052\037\361\234\110\277\063\373\144\211\112\077" +
"\015\334\327\122\111\231\023\345\317\211\202\023\373\131\052\005" +
"\343\204\306\335\101\373\203\232\301\071\041\365\004\035\220\045" +
"\006\161\202\016\144\010\044\162\045\116\304\236\210\116\364\347" +
"\350\040\223\353\232\023\164\160\122\214\330\023\110\217\345\214" +
"\116\230\134\164\302\344\102\167\202\016\061\271\320\235\160\333" +
"\023\164\250\124\001\020\240\034\067\107\215\023\375\327\150\202" +
"\240\141\243\102\344\052\235\160\325\023\164\230\120\133\265\023" +
"\164\170\011\052\233\036\132\217\270\221\036\313\131\313\357\201" +
"\035\001\064\154\124\210\134\255\023\056\166\107\164\042\377\234" +
"\216\004\372\171\016\367\116\224\010\232\130\176\256\236\360\275" +
"\047\132\334\035\012\202\216\252\103\125\243\023\125\202\216\266" +
"\315\250\364\051\377\061\071\221\156\356\216\041\317\011\072\126" +
"\320\310\256\037\047\120\041\162\157\234\240\343\005\002\211\074" +
"\350\236\250\333\011\072\001\160\004\347\104\354\211\026\234\010" +
"\054\350\104\363\325\100\077\015\074\251\357\171\122\274\136\130" +
"\257\273\047\342\356\030\053\050\172\340\004\235\054\254\107\334" +
"\110\217\345\324\342\204\137\075\101\247\060\004\022\171\147\235" +
"\150\253\047\350\124\313\172\304\215\370\130\316\272\234\240\323" +
"\030\015\033\225\240\247\327\211\156\357\016\072\235\041\220\310" +
"\073\353\304\060\075\101\147\224\240\262\351\241\365\210\033\351" +
"\261\234\135\333\035\064\051\051\106\220\116\124\351\011\232\234" +
"\045\242\023\046\247\331\011\072\123\140\224\370\130\116\147\377" +
"\213\323\131\250\002\040\100\071\156\116\073\075\121\042\350\354" +
"\362\163\365\104\350\237\324\144\101\123\262\077\243\023\046\064" +
"\237\230\334\032\072\207\271\311\306\116\114\072\127\252\000\010" +
"\120\216\233\323\312\211\111\123\005\062\005\075\341\360\265\103" +
"\371\356\210\116\324\340\204\203\240\363\334\151\065\027\265\175" +
"\146\165\076\243\301\315\315\347\200\136\167\166\007\135\120\202" +
"\312\246\207\326\043\156\244\307\162\306\163\302\344\152\371\227" +
"\251\027\002\015\033\025\042\127\353\204\213\236\240\213\204\332" +
"\101\071\021\173\102\324\103\353\021\067\322\353\075\322\305\375" +
"\327\132\375\277\213\056\141\326\113\172\350\256\020\067\322\143" +
"\071\103\356\211\350\104\176\076\135\232\045\102\167\202\056\063" +
"\071\137\077\263\242\313\233\256\350\253\023\203\006\135\061\254" +
"\102\127\166\007\135\311\350\040\075\226\323\331\317\073\256\102" +
"\025\000\001\312\161\163\152\076\061\335\072\321\106\117\320\325" +
"\002\243\304\307\162\152\074\047\350\232\072\124\175\355\011\272" +
"\126\250\335\372\356\240\151\014\001\127\051\236\023\154\005\100" +
"\200\162\222\136\356\032\135\047\060\112\174\054\147\055\237\324" +
"\134\017\064\154\124\210\074\366\004\252\355\330\011\272\041\113" +
"\104\047\114\116\243\023\164\043\303\021\244\023\316\167\307\115" +
"\131\102\343\073\253\172\042\072\221\366\304\364\354\317\270\073" +
"\122\047\156\316\022\276\072\101\267\010\265\035\073\021\117\314" +
"\131\075\161\153\226\320\170\116\320\155\165\250\326\362\156\373" +
"\166\240\301\314\055\344\270\071\161\167\240\332\103\070\101\167" +
"\040\316\320\234\140\317\211\073\263\204\146\047\350\056\201\121" +
"\342\143\071\065\073\121\266\047\350\156\241\146\120\116\124\357" +
"\011\272\307\122\045\014\047\074\011\272\267\351\212\161\167\230" +
"\134\033\116\320\175\200\312\246\207\356\012\161\043\075\226\063" +
"\366\204\311\371\172\116\064\037\232\173\202\356\027\030\045\076" +
"\226\123\263\023\145\167\007\075\040\324\014\312\211\356\367\004" +
"\075\050\060\112\174\054\247\146\047\142\117\040\047\350\041\206" +
"\315\126\023\073\341\040\350\141\167\132\315\105\227\172\242\352" +
"\356\240\107\262\204\146\047\350\121\201\121\342\143\071\065\073" +
"\121\266\047\350\061\241\146\255\116\320\343\100\003\335\111\313" +
"\273\043\234\236\250\350\104\211\240\031\345\347\352\211\330\023" +
"\046\127\313\071\361\004\320\260\121\041\162\265\116\150\353\011" +
"\172\062\113\370\352\004\075\045\324\156\246\047\052\004\075\135" +
"\175\255\077\341\155\117\074\043\324\356\314\071\101\317\002\052" +
"\233\036\272\053\304\215\364\130\116\137\173\102\162\202\146\062" +
"\034\136\234\023\165\004\075\327\164\105\225\075\361\074\303\321" +
"\312\356\240\027\054\225\152\166\042\236\023\160\075\342\106\172" +
"\054\247\257\347\104\363\021\173\302\344\064\072\101\057\062\034" +
"\101\072\121\117\117\374\037\132\141\174\200\023\302\001\000"
});

public static void initArrays()
throws java.io.IOException,java.lang.ClassNotFoundException
{
    symbolNames = (String[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(symbolNamesHash);
    symbolDisplayNames = (String[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(symbolDisplayNamesHash);
    symbolNumbers = (int[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(symbolNumbersHash);
    productionLHSs = (int[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(productionLHSsHash);
    parseTable = (int[][]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(parseTableHash);
    shiftableSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(shiftableSetsHash);
    layoutSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(layoutSetsHash);
    prefixSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(prefixSetsHash);
    prefixMaps = (java.util.BitSet[][]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(prefixMapsHash);
    terminalUses = (int[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(terminalUsesHash);
    shiftableUnion = (java.util.BitSet) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(shiftableUnionHash);
    acceptSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(acceptSetsHash);
    rejectSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(rejectSetsHash);
    possibleSets = (java.util.BitSet[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(possibleSetsHash);
    cmap = (int[]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(cMapHash);
    delta = (int[][]) edu.umn.cs.melt.copper.runtime.auxiliary.internal.ByteArrayEncoder.readHash(deltaHash);
    }
public Parser_silver_composed_Default_sviParse() {}

		private static int TERMINAL_COUNT;
		private static int GRAMMAR_SYMBOL_COUNT;
		private static int SYMBOL_COUNT;
		private static int PARSER_STATE_COUNT;
		private static int SCANNER_STATE_COUNT;
		private static int DISAMBIG_GROUP_COUNT;
		
		private static int SCANNER_START_STATENUM;
		private static int PARSER_START_STATENUM;
		private static int EOF_SYMNUM;
		private static int EPS_SYMNUM;
		
		private static String[] symbolNames;
		private static String[] symbolDisplayNames;
		private static int[] symbolNumbers;
		private static int[] productionLHSs;
		
		private static int[][] parseTable;
		private static java.util.BitSet[] shiftableSets;
		private static java.util.BitSet[] layoutSets;
		private static java.util.BitSet[] prefixSets;
		private static java.util.BitSet[][] prefixMaps;
		private static int[] terminalUses;
		
		private static java.util.BitSet[] disambiguationGroups;
		
		private static java.util.BitSet shiftableUnion;
		
		private static java.util.BitSet[] acceptSets,rejectSets,possibleSets;
		
		private static int[][] delta;
		private static int[] cmap;
		
		public int getTERMINAL_COUNT() {
			return TERMINAL_COUNT;
		}
		public int getGRAMMAR_SYMBOL_COUNT() {
			return GRAMMAR_SYMBOL_COUNT;
		}
		public int getSYMBOL_COUNT() {
			return SYMBOL_COUNT;
		}
		public int getPARSER_STATE_COUNT() {
			return PARSER_STATE_COUNT;
		}
		public int getSCANNER_STATE_COUNT() {
			return SCANNER_STATE_COUNT;
		}
		public int getDISAMBIG_GROUP_COUNT() {
			return DISAMBIG_GROUP_COUNT;
		}
		public int getSCANNER_START_STATENUM() {
			return SCANNER_START_STATENUM;
		}
		public int getPARSER_START_STATENUM() {
			return PARSER_START_STATENUM;
		}
		public int getEOF_SYMNUM() {
			return EOF_SYMNUM;
		}
		public int getEPS_SYMNUM() {
			return EPS_SYMNUM;
		}
		public String[] getSymbolNames() {
			return symbolNames;
		}
		public String[] getSymbolDisplayNames() {
			return symbolDisplayNames;
		}
		public int[] getSymbolNumbers() {
			return symbolNumbers;
		}
		public int[] getProductionLHSs() {
			return productionLHSs;
		}
		public int[][] getParseTable() {
			return parseTable;
		}
		public java.util.BitSet[] getShiftableSets() {
			return shiftableSets;
		}
		public java.util.BitSet[] getLayoutSets() {
			return layoutSets;
		}
		public java.util.BitSet[] getPrefixSets() {
			return prefixSets;
		}
		public java.util.BitSet[][] getLayoutMaps() {
			return null;
		}
		public java.util.BitSet[][] getPrefixMaps() {
			return prefixMaps;
		}
		public int[] getTerminalUses() {
			return terminalUses;
		}
		public java.util.BitSet[] getDisambiguationGroups() {
			return disambiguationGroups;
		}
		public java.util.BitSet getShiftableUnion() {
			return shiftableUnion;
		}
		public java.util.BitSet[] getAcceptSets() {
			return acceptSets;
		}
		public java.util.BitSet[] getRejectSets() {
			return rejectSets;
		}
		public java.util.BitSet[] getPossibleSets() {
			return possibleSets;
		}
		public int[][] getDelta() {
			return delta;
		}
		public int[] getCmap() {
			return cmap;
		}	
    public silver.definition.env.env_parser.NIRoot parse(java.io.Reader input,String inputName)
    throws java.io.IOException,edu.umn.cs.melt.copper.runtime.logging.CopperParserException
    {
    this.charBuffer = edu.umn.cs.melt.copper.runtime.io.ScannerBuffer.instantiate(input);
    setupEngine();
    startEngine(edu.umn.cs.melt.copper.runtime.io.InputPosition.initialPos(inputName));
    silver.definition.env.env_parser.NIRoot parseTree = (silver.definition.env.env_parser.NIRoot) runEngine();
    return parseTree;
    }


          protected List<common.Terminal> tokenList = null;

          public void reset() {
            tokenList = new ArrayList<common.Terminal>();
          }

          public List<common.Terminal> getTokens() {
            return tokenList; // The way we reset this iterator when parsing again is to create a new list, so this is defacto immutable
          }
        

    static
    {
        TERMINAL_COUNT = 113;
        GRAMMAR_SYMBOL_COUNT = 168;
        SYMBOL_COUNT = 355;
        PARSER_STATE_COUNT = 759;
        SCANNER_STATE_COUNT = 433;
        DISAMBIG_GROUP_COUNT = 2;
        SCANNER_START_STATENUM = 1;
        PARSER_START_STATENUM = 1;
        EOF_SYMNUM = 0;
        EPS_SYMNUM = -1;
        try { initArrays(); }
        catch(java.io.IOException ex) { ex.printStackTrace(); System.exit(1); }
        catch(java.lang.ClassNotFoundException ex) { ex.printStackTrace(); System.exit(1); }
        disambiguationGroups = new java.util.BitSet[2];
        disambiguationGroups[0] = newBitVec(113,54,92);
        disambiguationGroups[1] = newBitVec(113,63,92);
    }

}
