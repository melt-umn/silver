package edu.umn.cs.melt.ide.copper.coloring;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class TextAttributeProvider {
	
	private static Map<Display, Map<Integer, Color>> map = new HashMap<Display, Map<Integer, Color>>();
	
	/**
	 * Get an attribute.
	 * <p>
	 * The implementation caches the color demanded.
	 * 
	 * @param display
	 * @param r			color's Red value
	 * @param g			color's Green value
	 * @param b			color's Blue value
	 * @param isBold	if font is bold
	 * @param isItalic  if font is italic
	 * @return
	 */
	public static TextAttribute getAttribute(
		Display display, int r, int g, int b, boolean isBold, boolean isItalic){
		if(display==null || r<0 || r>255 || g<0 || g>255 || b<0 || b>255){
			return null;
		}
		
		int style = SWT.NORMAL;
		if(isBold){
			style |= SWT.BOLD;
		}
		if(isItalic){
			style |= SWT.ITALIC;
		}
		
		Color c = getCachedColor(display, r, g, b);
		
		if(c!=null){
			return new TextAttribute(c, null, style);
		} else {
			addColorToCache(display, r, g, b);
			c = getCachedColor(display, r, g, b);
		}
		
		return new TextAttribute(c, null, style);
	}
	
	/**
	 * Reset the provider. All colors cached will be disposed.
	 */
	public void reset(){
		for(Entry<Display, Map<Integer, Color>> dispMap:map.entrySet()){
			 Map<Integer, Color> colorMap = dispMap.getValue();
		     for(Entry<Integer, Color> cMap:colorMap.entrySet()){
		    	 int key = cMap.getKey();
				 Color color = cMap.getValue();
				 if(!isBuiltInColor(key)){
					 color.dispose();
				 }
			 } 
		     colorMap.clear();
		}
		
		map.clear();
	}
	
	private static void addColorToCache(Display display, int r, int g, int b) {
		Map<Integer, Color> colorMap = map.get(display);
		if(colorMap==null){
			map.put(display, createNewColorMap(display));
			colorMap = map.get(display);
		}
		
		int key = generateColorKey(r,g,b);
		if(!colorMap.containsKey(key)){//Defensive
			colorMap.put(key, new Color(display, r, g, b));
		}
	}

	private static Color getCachedColor(Display display, int r, int g, int b) {
		Map<Integer, Color> colorMap = map.get(display);
		if(colorMap==null){
			map.put(display, createNewColorMap(display));
			colorMap = map.get(display);
		}
		return colorMap.get(generateColorKey(r,g,b));
	}

	private static Map<Integer, Color> createNewColorMap(Display display) {
		Map<Integer, Color> colorMap = new HashMap<Integer, Color>();
		initColorMap(colorMap, display);
		return colorMap;
	}

	private static void initColorMap(Map<Integer, Color> map, Display display){
		for(int[] args:BUILT_IN_COLORS){
			map.put(generateColorKey(args[1],args[2],args[3]), display.getSystemColor(args[0]));
		}
	}

	private static Integer generateColorKey(int r, int g, int b) {
		return (r<<16) + (g<<8) + b;
	}
	
	private final static int[][] BUILT_IN_COLORS = new int[][]{
		new int[]{SWT.COLOR_WHITE, 255,255,255},
		new int[]{SWT.COLOR_BLACK, 0,0,0},
		new int[]{SWT.COLOR_RED, 250,0,0},
		new int[]{SWT.COLOR_DARK_RED, 128,0,0},
		new int[]{SWT.COLOR_GREEN, 0,255,0},
		new int[]{SWT.COLOR_DARK_GREEN, 0,128,0},
		new int[]{SWT.COLOR_YELLOW, 255,255,0},
		new int[]{SWT.COLOR_DARK_YELLOW, 128,128,0},
		new int[]{SWT.COLOR_BLUE, 0,0,255},
		new int[]{SWT.COLOR_DARK_BLUE, 0,0,128},
		new int[]{SWT.COLOR_MAGENTA, 255,0,255},
		new int[]{SWT.COLOR_DARK_MAGENTA, 128,0,128},
		new int[]{SWT.COLOR_CYAN, 0,255,255},
		new int[]{SWT.COLOR_DARK_CYAN, 0,128,128},
		new int[]{SWT.COLOR_GRAY, 192,192,192},
		new int[]{SWT.COLOR_DARK_GRAY, 128,128,128}
	};

	private boolean isBuiltInColor(int key) {
		for(int[] bicolor:BUILT_IN_COLORS){
			if(generateColorKey(bicolor[1], bicolor[2], bicolor[3]) == key){
				return true;
			}
		}
		return false;
	}
	
}
