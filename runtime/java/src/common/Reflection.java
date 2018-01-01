package common;

import java.io.*;
import java.lang.reflect.*;
import java.nio.channels.FileChannel;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import common.exceptions.*;
import common.javainterop.ConsCellCollection;

import core.NLocation;
import core.NParseError;
import core.NParseResult;
import core.NTerminalDescriptor;
import core.Ploc;
import core.PparseFailed;
import core.PsyntaxError;
import core.PterminalDescriptor;
import core.PunknownParseError;
import edu.umn.cs.melt.copper.runtime.engines.CopperParser;
import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;
import edu.umn.cs.melt.copper.runtime.logging.CopperSyntaxError;


/**
 * Implementation of the Silver reflection library
 * 
 * @author krame505
 */
public final class Reflection {
	public static core.reflect.NAST reflect(Object o) {
		if(o instanceof Node) {
			return null;// TODO
		} else if(o instanceof ConsCell) {
			return new core.reflect.PlistAST(reflectList((ConsCell)o));
		} else if(o instanceof StringCatter) {
			return new core.reflect.PstringAST((StringCatter)o);
		} else if(o instanceof Integer) {
			return new core.reflect.PintegerAST((Integer)o);
		} else if(o instanceof Float) {
			return new core.reflect.PfloatAST((Float)o);
		} else if(o instanceof Boolean) {
			return new core.reflect.PbooleanAST((Boolean)o);
		} else {
			return new core.reflect.PforeignAST(o);
		}
	}
	private static core.reflect.NASTs reflectList(ConsCell l) {
		if (!l.nil()) {
			return new core.reflect.PconsAST(l.head(), l.tail());
		} else {
			return new core.reflect.PnilAST();
		}
	}
}
