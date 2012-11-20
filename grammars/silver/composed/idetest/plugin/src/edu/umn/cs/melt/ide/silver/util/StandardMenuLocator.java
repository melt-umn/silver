package edu.umn.cs.melt.ide.silver.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * A helper class used to get handle of standard menu items in main menu.
 * <p>
 * In most cases this is for workaround approach, if menu item cannot be
 * referred to declaratively.
 */
public class StandardMenuLocator {
	
	private static Map<String, Map<String, MenuItem>> MAP = 
		new HashMap<String, Map<String, MenuItem>>();
	
	public static class Category {
		/**
		 * Standard menu items available under category "&Project", 
		 * organized into four groups:
		 * <p>
		 * Op&en Project			<br>
		 * Clo&se Project			<br>
		 * --------------			<br>
		 * Build &All	Ctrl+B		<br>
		 * &Build Project			<br>
		 * Build &Working Set		<br>
		 * Clea&n...				<br>
		 * Build Auto&matically		<br>
		 * --------------			<br>
		 * &Generate Javadoc...		<br>
		 * --------------			<br>
		 * &Properties				<br>
		 */
		public final static String PROJECT = "&Project";
	}
	
	public static class Command {
		public final static String BUILD_ALL = "Build &All";
		public final static String BUILD_PROJECT = "&Build Project";
		public final static String BUILD_AUTOMATICALLY = "Build Auto&matically";
	}
	
	/**
	 * Get menu item with given category(group) and name
	 * <p>
	 * Should always use names defined in static classes 
	 * <code>Category</code> and <code>Command</code> nested in this class.
	 * 
	 * @param category
	 * @param name
	 * @return
	 */
	public static MenuItem getMenuItem(final String category, final String name){
		//Try to return from cache
		MenuItem mi = getFromMap(category, name);
		if(mi!=null){
			return mi;
		}
		
		//Try to locate in standard menu
		if(Thread.currentThread() != Display.getDefault().getThread()){
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					findMenuItem(category, name);
				}
			});			
		} else {
			findMenuItem(category, name);
		}

		//Try to return from cache again
		return getFromMap(category, name);
	}
	

	/**
	 * Get menu items with given names under same category(group). References 
	 * retrieved will be put into the given array <code>menus</code>, which the
	 * caller is responsible for providing
	 * <p>
	 * Should always use names defined in static classes 
	 * <code>Category</code> and <code>Command</code> nested in this class.
	 * 
	 * @param menus		a list used to accommodate menu items to find. The length 
	 * 					MUST be equivalent to <code>name</code>'s length.
	 * @param category
	 * @param names
	 * @return			nothing. The parameter <code>menus</code> will be filled 
	 * 					in with menu items found as result.
	 */
	public static void getMenuItems(MenuItem[] menus, final String category, final String[] names){
		//Check
		if(menus == null || menus.length != names.length){
			return;
		}
		
		//Try to return from cache
		boolean res = getFromMap(menus, category, names);
		if(res){
			return;
		}
		
		//Try to locate in standard menu
		if(Thread.currentThread() != Display.getDefault().getThread()){
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					findMenuItems(category, names);
				}
			});			
		} else {
			findMenuItems(category, names);
		}

		//Try to return from cache again
		getFromMap(menus, category, names);
	}
	
	private static MenuItem getFromMap(String category, String name){
		Map<String, MenuItem> subMap = MAP.get(category);
		if(subMap!=null){
			MenuItem mi = subMap.get(name);
			if(mi != null){// && !mi.isDisposed()
				return mi;
			}
		}
		
		return null;
	}
	
	private static boolean getFromMap(MenuItem[] menus, String category, String[] names){
		Map<String, MenuItem> subMap = MAP.get(category);
		if(subMap!=null){
			for(int i=0;i<menus.length;i++){
				MenuItem mi = subMap.get(names[i]);
				if(mi != null){// && !mi.isDisposed()
					menus[i] = mi;
				} else {
					//Report?
					return false;
				}
			}
		}
		
		return true;
	}
	
	private static void findMenuItem(String category, String name){
    	findMenuItems(category, new String[]{name});
	}
	
	private static void findMenuItems(String category, String[] names){
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    	if(window == null){
    		return;//Shouldn't reach here
    	}
		Menu menubar = window.getShell().getMenuBar();
    	if(menubar == null){
    		return;//Shouldn't reach here
    	}
    	MenuItem[] mItems = menubar.getItems();
    	if(mItems == null){
    		return;//Shouldn't reach here
    	}
    	
    	for(MenuItem mi:mItems){
    		if(category.equals(mi.getText())){
    			Map<String, MenuItem> subMap = MAP.get(category);
    			if(subMap==null){
    				subMap = new HashMap<String, MenuItem>();
    				MAP.put(category, subMap);
    			}
    			Menu menu = mi.getMenu();
    			for(MenuItem item : menu.getItems()){
    				for(String name : names){
    		    		if(item.getText().startsWith(name)){
    		    			subMap.put(name, item);
    		    			break;
    		    		}				
    				}
    			}
    			break;
	    	}
    	}
	}
	
}
