
package silver.composed.montoide.imp.coloring;

import java.util.HashMap;

import edu.umn.cs.melt.ide.copper.coloring.ITokenClassifier;
import edu.umn.cs.melt.ide.copper.coloring.TextAttributeProvider;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.widgets.Display;

public class Parser_silver_composed_Default_svParse_TokenClassifier implements ITokenClassifier {
	private static final HashMap<String, Integer> map = new HashMap<String, Integer>();

	public final static class TokenType {
		public static final int DEFAULT = 0; 
		public static final int silver_definition_core_font_comments = 1;
		public static final int silver_definition_core_font_literal = 2;
		public static final int silver_definition_core_font_keyword = 3;
		public static final int silver_definition_core_font_modword = 4;
		public static final int silver_definition_core_font_type = 5;
		public static final int silver_extension_doc_core_font_doc = 6;
		public static final int silver_extension_doc_core_font_doc_kwd = 7;
		public static final int silver_extension_doc_core_font_doc_id = 8;

		public static final int TOTAL = 9; 
	}

	static {
		map.put("silver:definition:concrete_syntax:Association_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:concrete_syntax:Ignore_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:concrete_syntax:Left_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:concrete_syntax:Marking_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:concrete_syntax:Operator_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:concrete_syntax:Precedence_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:concrete_syntax:Right_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:concrete_syntax:Jason_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Abstract_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:And_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Annotation_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:As_kwd", TokenType.silver_definition_core_font_modword);
		map.put("silver:definition:core:Aspect_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Attribute_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:BlockComments", TokenType.silver_definition_core_font_comments);
		map.put("silver:definition:core:CCEQ_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Closed_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:ColonColon_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Colon_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Comma_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Comments", TokenType.silver_definition_core_font_comments);
		map.put("silver:definition:core:Concrete_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Decorate_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Divide_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Dot_t", TokenType.DEFAULT);
		map.put("silver:definition:core:EQEQ_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Else_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Equal_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Exports_kwd", TokenType.silver_definition_core_font_modword);
		map.put("silver:definition:core:False_kwd", TokenType.silver_definition_core_font_literal);
		map.put("silver:definition:core:Float_t", TokenType.silver_definition_core_font_literal);
		map.put("silver:definition:core:Forward_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Forwarding_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Forwards_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Function_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:GTEQ_t", TokenType.DEFAULT);
		map.put("silver:definition:core:GT_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Global_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Grammar_kwd", TokenType.silver_definition_core_font_modword);
		map.put("silver:definition:core:Hiding_kwd", TokenType.silver_definition_core_font_modword);
		map.put("silver:definition:core:IdLower_t", TokenType.DEFAULT);
		map.put("silver:definition:core:IdUpper_t", TokenType.DEFAULT);
		map.put("silver:definition:core:If_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Import_kwd", TokenType.silver_definition_core_font_modword);
		map.put("silver:definition:core:Imports_kwd", TokenType.silver_definition_core_font_modword);
		map.put("silver:definition:core:Inherited_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Int_t", TokenType.silver_definition_core_font_literal);
		map.put("silver:definition:core:LCurly_t", TokenType.DEFAULT);
		map.put("silver:definition:core:LParen_t", TokenType.DEFAULT);
		map.put("silver:definition:core:LTEQ_t", TokenType.DEFAULT);
		map.put("silver:definition:core:LT_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Length_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Local_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Minus_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Modulus_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Multiply_t", TokenType.DEFAULT);
		map.put("silver:definition:core:NEQ_t", TokenType.DEFAULT);
		map.put("silver:definition:core:New_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:NonTerminal_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Not_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Occurs_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:On_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Only_kwd", TokenType.silver_definition_core_font_modword);
		map.put("silver:definition:core:Optional_kwd", TokenType.silver_definition_core_font_modword);
		map.put("silver:definition:core:Or_t", TokenType.DEFAULT);
		map.put("silver:definition:core:PlusPlus_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Plus_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Production_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:RCurly_t", TokenType.DEFAULT);
		map.put("silver:definition:core:RParen_t", TokenType.DEFAULT);
		map.put("silver:definition:core:Return_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Semi_t", TokenType.DEFAULT);
		map.put("silver:definition:core:String_t", TokenType.silver_definition_core_font_literal);
		map.put("silver:definition:core:Synthesized_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Terminal_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:Then_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:ToFloat_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:ToInt_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:ToString_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:To_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:core:True_kwd", TokenType.silver_definition_core_font_literal);
		map.put("silver:definition:core:UnderScore_t", TokenType.DEFAULT);
		map.put("silver:definition:core:WhiteSpace", TokenType.DEFAULT);
		map.put("silver:definition:core:With_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:definition:regex:Choice_t", TokenType.DEFAULT);
		map.put("silver:definition:regex:EscapedChar_t", TokenType.DEFAULT);
		map.put("silver:definition:regex:Kleene_t", TokenType.DEFAULT);
		map.put("silver:definition:regex:Optional_t", TokenType.DEFAULT);
		map.put("silver:definition:regex:Plus_t", TokenType.DEFAULT);
		map.put("silver:definition:regex:Range_t", TokenType.DEFAULT);
		map.put("silver:definition:regex:RegexChar_t", TokenType.DEFAULT);
		map.put("silver:definition:regex:RegexLBrack_t", TokenType.DEFAULT);
		map.put("silver:definition:regex:RegexLParen_t", TokenType.DEFAULT);
		map.put("silver:definition:regex:RegexNot_t", TokenType.DEFAULT);
		map.put("silver:definition:regex:RegexRBrack_t", TokenType.DEFAULT);
		map.put("silver:definition:regex:RegexRParen_t", TokenType.DEFAULT);
		map.put("silver:definition:regex:RegexWildcard_t", TokenType.DEFAULT);
		map.put("silver:definition:type:syntax:Boolean_tkwd", TokenType.silver_definition_core_font_type);
		map.put("silver:definition:type:syntax:Decorated_tkwd", TokenType.silver_definition_core_font_type);
		map.put("silver:definition:type:syntax:Float_tkwd", TokenType.silver_definition_core_font_type);
		map.put("silver:definition:type:syntax:Integer_tkwd", TokenType.silver_definition_core_font_type);
		map.put("silver:definition:type:syntax:String_tkwd", TokenType.silver_definition_core_font_type);
		map.put("silver:extension:convenience:Children_kwd", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:convenience:ProdVBar", TokenType.DEFAULT);
		map.put("silver:extension:convenience:Productions_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:deprecation:Build_kwd", TokenType.silver_definition_core_font_modword);
		map.put("silver:extension:deprecation:Deprecated_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:deprecation:Function_tkwd", TokenType.silver_definition_core_font_type);
		map.put("silver:extension:deprecation:IdTickTick_t", TokenType.DEFAULT);
		map.put("silver:extension:deprecation:IdTick_t", TokenType.DEFAULT);
		map.put("silver:extension:deprecation:Production_tkwd", TokenType.silver_definition_core_font_type);
		map.put("silver:extension:doc:core:Close_t", TokenType.silver_extension_doc_core_font_doc_kwd);
		map.put("silver:extension:doc:core:CommentCloseSquare_t", TokenType.silver_extension_doc_core_font_doc);
		map.put("silver:extension:doc:core:CommentId_t", TokenType.silver_extension_doc_core_font_doc_id);
		map.put("silver:extension:doc:core:CommentLink_t", TokenType.silver_extension_doc_core_font_doc_kwd);
		map.put("silver:extension:doc:core:CommentOpenSquare_t", TokenType.silver_extension_doc_core_font_doc);
		map.put("silver:extension:doc:core:CommentOpen_t", TokenType.silver_extension_doc_core_font_doc_kwd);
		map.put("silver:extension:doc:core:CommentText_t", TokenType.silver_extension_doc_core_font_doc);
		map.put("silver:extension:doc:core:ConfigHeader_t", TokenType.silver_extension_doc_core_font_doc_kwd);
		map.put("silver:extension:doc:core:ConfigNoDoc_t", TokenType.silver_extension_doc_core_font_doc_kwd);
		map.put("silver:extension:doc:core:ConfigOpen_t", TokenType.silver_extension_doc_core_font_doc_kwd);
		map.put("silver:extension:doc:core:ConfigSplitFiles_t", TokenType.silver_extension_doc_core_font_doc_kwd);
		map.put("silver:extension:doc:core:ConfigValue_t", TokenType.silver_extension_doc_core_font_doc);
		map.put("silver:extension:doc:core:NoDclComment_t", TokenType.silver_extension_doc_core_font_doc_kwd);
		map.put("silver:extension:easyterminal:Terminal_t", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:functorattrib:Functor_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:functorattrib:Propagate_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:list:LSqr_t", TokenType.DEFAULT);
		map.put("silver:extension:list:RSqr_t", TokenType.DEFAULT);
		map.put("silver:extension:monad:Bind_kwd", TokenType.DEFAULT);
		map.put("silver:extension:monad:Do_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:monad:LArrow_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:monad:NoElse_t", TokenType.DEFAULT);
		map.put("silver:extension:monad:Sequence_t", TokenType.DEFAULT);
		map.put("silver:extension:patternmatching:Arrow_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:patternmatching:Case_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:patternmatching:Of_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:patternmatching:Opt_Vbar_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:patternmatching:Vbar_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:templating:PPTemplate_kwd", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:templating:SLPPTemplate_kwd", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:templating:SLTemplate_kwd", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:templating:Template_kwd", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:templating:syntax:DoubleDollar", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:templating:syntax:LiteralBackslash", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:templating:syntax:LiteralNewline", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:templating:syntax:LiteralQuote", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:templating:syntax:LiteralTab", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:templating:syntax:OpenEscape", TokenType.DEFAULT);
		map.put("silver:extension:templating:syntax:QuoteWater", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:templating:syntax:SingleLineQuoteWater", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:templating:syntax:TripleQuote", TokenType.silver_definition_core_font_literal);
		map.put("silver:extension:testing:EqualityTest_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:testing:MainTestSuite_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:testing:MakeTestSuite_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:testing:WrongCode_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:testing:WrongFlowCode_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:treegen:Arbitrary_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:treegen:Derive_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:treegen:Eq_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:extension:treegen:TestFor_T", TokenType.DEFAULT);
		map.put("silver:modification:autocopyattr:AutoCopy_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:collection:BaseContains_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:collection:Contains_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Action_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Class_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Classes_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Disambiguation_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Dominates_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Layout_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Lexer_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Over_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Parser_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Pluck_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Prefer_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Prefix_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Print_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:PushToken_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Separator_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper:Submits_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:copper_mda:CopperMDA", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:defaultattr:Default_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:ffi:FFI_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:Bold_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:Color_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:Font_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpFont_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_IdeResource", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_OptFunc_Builder", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_OptFunc_Exporter", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_OptFunc_Folder", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_OptFunc_PostBuilder", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_OptFunc_Property", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_ProdInfo_Name_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_ProdInfo_Version_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_PropOption_Default_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_PropOption_Display_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_PropOption_Required_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_PropType_integer_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_PropType_path_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_PropType_string_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_PropType_url_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_Wizard", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_Wizard_NewFile", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_Wizard_StubGen", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:ImpIde_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:impide:Italic_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:lambda_fn:Arrow_t", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:lambda_fn:Lambda_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:let_fix:End_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:let_fix:In_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:let_fix:Let_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:primitivepattern:Match_kwd", TokenType.silver_definition_core_font_keyword);
		map.put("silver:modification:typedecl:Type_t", TokenType.silver_definition_core_font_keyword);
	}

	public static int getKind(String symbolName) {
		if(symbolName == null || "".equals(symbolName)) {
			return TokenType.DEFAULT;
		}

		Integer kind = map.get(symbolName);

		if(kind == null) {
			return TokenType.DEFAULT;
		}

		return kind;
	}

	private static final TextAttribute[] attributes = new TextAttribute[TokenType.TOTAL];
	
	static {
		Display display = Display.getDefault();
		attributes[TokenType.silver_definition_core_font_comments] = TextAttributeProvider.getAttribute(display, 82, 141, 115, false, true);
		attributes[TokenType.silver_definition_core_font_literal] = TextAttributeProvider.getAttribute(display, 50, 50, 250, false, false);
		attributes[TokenType.silver_definition_core_font_keyword] = TextAttributeProvider.getAttribute(display, 123, 0, 82, true, false);
		attributes[TokenType.silver_definition_core_font_modword] = TextAttributeProvider.getAttribute(display, 41, 95, 148, true, false);
		attributes[TokenType.silver_definition_core_font_type] = TextAttributeProvider.getAttribute(display, 41, 95, 148, false, false);
		attributes[TokenType.silver_extension_doc_core_font_doc] = TextAttributeProvider.getAttribute(display, 82, 100, 166, false, true);
		attributes[TokenType.silver_extension_doc_core_font_doc_kwd] = TextAttributeProvider.getAttribute(display, 82, 100, 166, true, true);
		attributes[TokenType.silver_extension_doc_core_font_doc_id] = TextAttributeProvider.getAttribute(display, 82, 100, 166, false, false);
	}
	
	@Override
	public TextAttribute getColoring(common.Terminal token) {
    // TODO: check kind by getLexerClasses()
		return attributes[getKind(token.getName())];
	}
}
