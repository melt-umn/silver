package edu.umn.cs.melt.ide.silver.env;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;
import org.osgi.framework.Bundle;

/**
 * A class representing the Silver installation embedded in Eclipse.
 * <p>
 * Silver IDE has a minimal version of Silver embedded in Eclipse, which 
 * includes /grammars, /generated, /jars (not done yet), among others. This 
 * class is a programming interface by which one can query and configure 
 * this Silver installation.
 * <p>
 * The Silver IDE plug-in will call {@link #initialize(Bundle _bundle)} when
 * started. The initialization will then install minimal Silver into Eclipse,
 * if it was not done before. A /silver folder under root path of Eclipse 
 * installation is expected to be created after this method returns.
 */
public final class SilverEnv {

	/** The root of Silver: ../Eclipse/silver/ */
	private static File ROOT;
	
	private static final String GENERATED = "generated";
	
	private static final String GRAMMARS = "grammars";
	
	/** A flag to identify if the environment is ready to use. */
	private static boolean READY;
	
	/**
	 * Initialize the environment.
	 * <p>
	 * The Silver IDE plug-in will call this method when started. This method 
	 * installs minimal Silver into Eclipse, if it was not done before. A 
	 * /silver folder under root path of Eclipse installation is expected to be
	 * created after this method returns.
	 */
	public static void initialize(Bundle _bundle) {
		Location platform = Platform.getInstallLocation();//.getInstanceLocation();
		try {
			setBundle(_bundle);
			
			File parent = new File(FileLocator.resolve(platform.getURL()).toURI());
			
			ROOT = new File(parent, "silver");
			
			if(!ROOT.exists()){
				//Create /silver
				ROOT.mkdir();
				
				//Create /silver/grammars, extract grammar files there
				extract(getGrammarsResource());
				
				//Create /silver/generated
				new File(ROOT, GENERATED).mkdir();
				
				READY = true;
			} else {
				READY = sanityCheck();
//				if(!READY){
//					//TODO delete ROOT, and recall this method
//				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Check if the environment is ready to use.
	 * <p>
	 * If this returns false, then something must have gone wrong during the
	 * initialization. The embedded silver installation is determined to
	 * be compromised and not usable.
	 * @return
	 */
	public static boolean isReady(){
		return READY;
	}
	
	/**
	 * Get the generated folder of embedded Silver
	 * <p>
	 * By standard this is ../Eclipse/silver/generated
	 * 
	 * @return
	 */
	public static File getGeneratedFolder(){
		return new File(ROOT, GENERATED);
	}
	
	/**
	 * Get the grammars folder of embedded Silver
	 * <p>
	 * By standard this is ../Eclipse/silver/grammars
	 * 
	 * @return
	 */
	public static File getGrammarsFolder(){
		return new File(ROOT, GRAMMARS);
	}
	
	/**
	 * Get the home of embedded Silver
	 * <p>
	 * By standard this is ../Eclipse/silver/
	 * 
	 * @return
	 */
	public static File getSilverHome(){
		return ROOT;
	}

	private static void extract(InputStream f0) {
		ZipInputStream zinstream = new ZipInputStream(f0);
		try {
			byte[] buf = new byte[1024];
			
			String root = ROOT.getAbsolutePath() + "/";
			ZipEntry zentry = zinstream.getNextEntry();
			while (zentry != null) {
				String entryName = root + zentry.getName();

				if (zentry.isDirectory()) {
					File f = new File(entryName);
					f.mkdir();
				} else {
					FileOutputStream outstream = new FileOutputStream(entryName);
					int n;
					while ((n = zinstream.read(buf, 0, 1024)) > -1) {
						outstream.write(buf, 0, n);
					}
					outstream.close();
				}

				zinstream.closeEntry();
				zentry = zinstream.getNextEntry();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				zinstream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static Bundle bundle = null;

	private static void setBundle(Bundle _bundle) {
		if(bundle==null){
			//Can only be set once
			bundle = _bundle;
		}
	}

	private static InputStream getGrammarsResource(){
		//Bundle bundle = Platform.getBundle("silver.composed.idetest");//Name of silver plug-in bundle
		URL fileURL = bundle.getEntry("grammars.zip");
		InputStream is = null;
		try {
			return is = FileLocator.resolve(fileURL).openStream();
		} catch (IOException e1) {
		    e1.printStackTrace();
		}
		
		return is;
	}
	
	private static boolean sanityCheck() {
		return getGeneratedFolder().exists() && getGrammarsFolder().exists();
	}
}

