/*
 * Variables used:
 *   PKG_NAME
 *   LANG_NAME
 */
package silver.composed.idetest.imp.builders;

/* Silver Builder START */
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.builder.BuilderBase;
import org.eclipse.imp.builder.BuilderUtils;
import org.eclipse.imp.builder.MarkerCreator;
import org.eclipse.imp.builder.MarkerCreatorWithBatching;
import org.eclipse.imp.language.Language;
import org.eclipse.imp.language.LanguageRegistry;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.model.ModelFactory;
import org.eclipse.imp.model.ModelFactory.ModelException;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.runtime.PluginBase;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.eclipse.ui.services.ISourceProviderService;

import silver.composed.idetest.imp.SILVERPlugin;
import silver.composed.idetest.imp.controller.SILVERParseController;
import edu.umn.cs.melt.ide.silver.ConsoleLoggingStream;
import edu.umn.cs.melt.ide.silver.SilverAnalysisInvoker;
import edu.umn.cs.melt.ide.silver.SilverAnalysisInvoker.AnalysisHandler;
import edu.umn.cs.melt.ide.silver.commands.QuickBuildCommandState;
import edu.umn.cs.melt.ide.silver.problem.Problem;
import edu.umn.cs.melt.ide.silver.util.StandardMenuLocator;

/*
 * TO DO:
 * 1) make reusable PrintStream for redirecting System output [done]
 * 2) scan the project to get all the possible grammars [done]
 * 3) pop up a window before building to prompt user to select the grammar he wants to build [done]
 * 4) disable build and build-all button while building [done]
 * 5) shut down auto build - <Perspective> [done]
 * 6) abort ant build if Silver compilation is failed [done]
 * 7) show console view-part at start - <Perspective> [done]
 * 
 * FIX ME:
 * 1) ant build makes the window popped up [done]
 * 2) cancelling window clears change mark, won't build again until new changes are made [done]
 * 3) refresh project [done]
 * 4) like standard "build", disable "quick build" when no project is selected [done]
 * 5) check off auto build when switching to Silver Perspecive - <Perspective> [done]
 * 
 * TO IMPROVE:
 * 1) scan grammars on demand [TODO]
 * 2) quick build button to rebuild the grammar selected last time [done]
 * 3) make compilation cancellable [done]
 * 4) enhance progress report
 * 
 */
/**
 * A builder may be activated on a file containing SILVER code every time it
 * has changed (when "Build automatically" is on), or when the programmer
 * chooses to "Build" a project.
 * <p>
 * This is what the current Silver Builder does:
 * <p>
 * (1) walk through the project to get a grammar list.<br>
 * (2) prompt a window to let the user choose the grammar he wants to build.<br>
 * (3) (syntactically) parse all the files affected since last build. Abort the
 *     build if any error. Errors will be marked.<br>
 * (4) (semantically) analyze the ENTIRE project regardless of delta. Abort the
 *     build if any error. Errors will be marked.<br>
 * <p>
 * If a user chooses Quick Build in Project menu, or turns Build Automatically 
 * on, the process will start directly from step (3). 
 */
public class SILVERBuilder extends BuilderBase implements AnalysisHandler {
/* Silver Builder END */
	
    /**
     * Extension ID of the SILVER builder, which matches the ID in
     * the corresponding extension definition in plugin.xml.
     */
    public static final String BUILDER_ID = SILVERPlugin.kPluginID
            + ".SILVER.imp.builder";

    /**
     * A marker ID that identifies problems detected by the builder
     */
    public static final String PROBLEM_MARKER_ID = SILVERPlugin.kPluginID
            + ".SILVER.imp.builder.problem";

    public static final String LANGUAGE_NAME = "SILVER";

    public static final Language LANGUAGE = LanguageRegistry
            .findLanguage(LANGUAGE_NAME);

    protected PluginBase getPlugin() {
        return SILVERPlugin.getInstance();
    }

    public String getBuilderID() {
    	return BUILDER_ID;
    }
    
    protected String getErrorMarkerID() {
        return PROBLEM_MARKER_ID;
    }

    protected String getWarningMarkerID() {
        return PROBLEM_MARKER_ID;
    }

    protected String getInfoMarkerID() {
        return PROBLEM_MARKER_ID;
    }

    /**
     * Decide whether a file needs to be build using this builder. Note that
     * <code>isNonRootSourceFile()</code> and <code>isSourceFile()</code>
     * should never return true for the same file.
     * 
     * @return true iff an arbitrary file is a SILVER source file.
     */
    protected boolean isSourceFile(IFile file) {
        IPath path = file.getRawLocation();
        if (path == null)
            return false;

        String pathString = path.toString();
        if (pathString.indexOf("/bin/") != -1)
            return false;

        return LANGUAGE.hasExtension(path.getFileExtension());
    }

    /**
     * Decide whether or not to scan a file for dependencies. Note:
     * <code>isNonRootSourceFile()</code> and <code>isSourceFile()</code>
     * should never return true for the same file.
     * 
     * @return true iff the given file is a source file that this builder should
     *         scan for dependencies, but not compile as a top-level compilation
     *         unit.
     * 
     */
    protected boolean isNonRootSourceFile(IFile resource) {
        return false;
    }

    /**
     * Collects compilation-unit dependencies for the given file, and records
     * them via calls to <code>fDependency.addDependency()</code>.
     */
    protected void collectDependencies(IFile file) {
        String fromPath = file.getFullPath().toString();
        
        getPlugin().writeInfoMsg("Collecting dependencies from SILVER file: " + file.getName());
        
        // TODO: implement dependency collector
        // E.g. for each dependency:
        // fDependencyInfo.addDependency(fromPath, uponPath);
    }

    /**
     * @return true iff this resource identifies the output folder
     */
    protected boolean isOutputFolder(IResource resource) {
        return resource.getFullPath().lastSegment().equals("bin");
    }

    /* Silver Builder START */
    public static final String SILVER_BUILDER_CONSOLE = "Silver Builder";
    
    /** Key used in argument map for build(). The corresponding value should be either "true" or "false". */
    public static final String IS_QUICK_BUILD = "silver.ide.quickbuild";
    
    /**
     * The method is overriden because we need to explicitly show the console,
     * which the ANT builder may close.
     */
    @Override
    protected MessageConsoleStream getConsoleStream() {
    	MessageConsole console = findConsole(getConsoleName());
    	ConsolePlugin.getDefault().getConsoleManager().showConsoleView(console);
    	return console.newMessageStream();
    }
    
    @Override
    protected String getConsoleName(){
    	return SILVER_BUILDER_CONSOLE;
    }
    
    private ConsoleLoggingStream getConsoleLoggingStream(){    	
    	return ConsoleLoggingStream.getStream(getConsoleStream(), getConsoleStream());
    }
    
//    private PrintStream buildInfoStream = null;
//    
//    /**
//     * Get a stream used to redirect the output of Silver compiler
//     */
//    private PrintStream getBuildInfoStream(){
//    	if(buildInfoStream==null){
//    		buildInfoStream = new PrintStream(getConsoleStream());
//    	}
//    	
//    	return buildInfoStream;
//    }
    
    private boolean isBuilding = false;
    
    @SuppressWarnings("unchecked")
    protected IProject[] build(int kind, Map args, IProgressMonitor monitor) {
    	boolean isQuickBuild = false;
    	
    	if(args != null){
    		String value = (String)args.get(IS_QUICK_BUILD);
    		isQuickBuild = Boolean.parseBoolean(value);
    	}
    	
    	if(kind == AUTO_BUILD){
    		isQuickBuild = true;
    	}
    	
    	//Secure the lock. No two builds are allowed at the same time.
    	synchronized(SILVERBuilder.class){
    		if(isBuilding){
    			getConsoleLoggingStream().error("Build is underway. Please retry later.");
    			return new IProject[0];
    		} else {
    			isBuilding = true;
    		}
    	}
    	
    	final IProject project = getProject();
    	final String projectPath = project.getLocation().toString();
    	
		//Get all the grammars, which are corresponding to the file system structure
    	File f = new File(projectPath);
		List<String> grammars = new ArrayList<String>();
		scanGrammars(f, grammars, "");
		
		//Build certain grammar from the given grammar list
		boolean buildSuccessful = buildGrammar(project, projectPath, grammars, isQuickBuild, monitor);
		
		//Cancel the build if compilation ended up failed
		//(this can stop ant-build from proceeding)
		if(!buildSuccessful){
			monitor.setCanceled(true);
			getConsoleLoggingStream().error("Build failed.");
		}
		
		//Release the lock
    	synchronized(SILVERBuilder.class){
    		isBuilding = false;
    	}
		
        return new IProject[0];
    }

    /**
     * Scan the grammars under a specific folder
     * @param f the project folder
     * @param grammars
     * @param base
     */
	private void scanGrammars(File f, List<String> grammars, String base) {
		for(File file:f.listFiles()){
			if(file.isDirectory()){
				String gname = "".equals(base) ? file.getName() : base + ":" + file.getName();
				grammars.add(gname);
				scanGrammars(file, grammars, gname);
			}
		}
	}
	
	/** A thread executor exclusively used for invoking compilation */
	private final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor(); 
	
	/** The grammar built last time in this session */
	private String grammarLastSelected = "";
	
	/** Asynchronous task for compilation */
	private FutureTask<Boolean> future;
	
	/**
	 * Prompt a window to show the grammars as given by param <code>grammars</code>.
	 * <p>
	 * By clicking OK, the selected grammar is compiled, with the result output to
	 * the console. If build is successful, the project folder will be refreshed to 
	 * show the updated build.xml file.
	 * <p>
	 * During building, some commands in workbench menu bar will be disabled.
	 * 
	 * @param project
	 * @param projectPath
	 * @param grammars
	 * @return true if build was successful; false if failed or cancelled
	 */
	private boolean buildGrammar(
		final IProject project, 
		final String projectPath, 
		final List<String> grammars, 
		boolean isQuickBuild, 
		final IProgressMonitor monitor) {
		 
		future = null;
		
    	//Disable build buttons
    	Display.getDefault().syncExec(new Runnable() {
    		public void run() {
            	enableBuildCommands(false);
    		}
    	});
    	
		//Invoke the compilation task immediately for quick build
		if(isQuickBuild && !"".equals(grammarLastSelected)){
        	//Invoke the compliation task
        	future = createBuildTask(project, projectPath, monitor);
        	EXECUTOR.execute(future);  
		} else {
			final Display display = Display.getDefault();
			
			display.syncExec(new Runnable() {
	    		public void run() {
	    			//Create a shell with workbench as parent
				    Shell shell = createDialog(project, projectPath, grammars, monitor);
				    
				    shell.pack();
				    shell.open();		
				    
				    //Loop until the dialog is disposed (closed), this is necessary
				    //for we want to suspend the thread in which showBuildDialog()
				    //is called.
				    while (!shell.isDisposed()) {
			            if (!display.readAndDispatch()) {
			            	display.sleep();
			            }
			        }
	    		}
	    	});
		
		}
		
		boolean res = false;
		
		//Wait for future to return if it's ever instantiated.
		if(future != null){
	    	try {
	    		//Blocking until the task finishes
				res = future.get();
				
				//Refresh after compilation, which should have created/updated
				//a jar under project's parent folder.
				project.refreshLocal(IResource.DEPTH_ONE, null);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			}			
		}
		
    	Display.getDefault().syncExec(new Runnable() {
    		public void run() {
            	enableBuildCommands(true);
    		}
    	});  
    	
		return res;
	}
	
	/**
	 * Create a modal dialog for selecting grammars in the project 
	 * <p>
	 * Depending on the button user clicks, <br>
	 * 1. <b>OK</b>: build the grammar  <br>
	 * 2. <b>CANCEL</b>: abort build  <br>
	 * 3. <b>CLOSE</b>: abort build  <br>
	 *  
	 * @param projectPath
	 * @param grammars
	 * @param monitor
	 * @return
	 */
	private Shell createDialog(
			final IProject project, 
			final String projectPath, 
			final List<String> grammars, 
			final IProgressMonitor monitor){
		//Create a shell with workbench as parent
	    Shell parentShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	    final Shell shell = new Shell(parentShell, SWT.DIALOG_TRIM|SWT.PRIMARY_MODAL);
	    RowLayout outerLayout = new RowLayout();
	    outerLayout.justify = true;
	    shell.setLayout(outerLayout);
	    shell.setText(getProject().getName());
	    
	    Composite panel = new Composite(shell, SWT.NULL);
	    
	    //Layer 0
	    GridLayout layout0 = new GridLayout();
	    layout0.numColumns = 1;
	    panel.setLayout(layout0);
	    
	    Label label = new Label(panel, SWT.LEFT);
	    label.setText("Select the grammar to build:");
	    
	    Composite controlComp = new Composite(panel, SWT.NULL);
	    
	    //Layer 1
	    FillLayout layout1 = new FillLayout(SWT.VERTICAL);
	    controlComp.setLayout(layout1);
	    
	    //Convert data for combo
	    final Combo simple = new Combo(controlComp, SWT.DROP_DOWN);
	    Object[] objs = grammars.toArray();
	    String[] strs = new String[objs.length];
	    for(int i=0;i<objs.length;i++){
	    	strs[i] = (String)objs[i];
	    }
	    simple.setItems(strs);
	    simple.setText(grammarLastSelected);

	    Composite btnComp = new Composite(controlComp, SWT.NULL);
	    RowLayout layout2 = outerLayout;//Reuse layout
	    btnComp.setLayout(layout2);
	    
	    //Layer 2
	    //OK button
	    Button okBtn = new Button(btnComp, SWT.PUSH);
	    okBtn.setText("OK");
	    okBtn.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event event) {
	        	//Disable build buttons
	        	//enableBuildCommands(false);
	        	
	        	//Save the specified grammar 
	        	grammarLastSelected = simple.getText();
	        	
	        	//Invoke the compliation task
	        	future = createBuildTask(project, projectPath, monitor);
	        	EXECUTOR.execute(future);  
	        	
	        	//Close the dialog
	        	shell.dispose();
	        }
	    });
	    
	    //Cancel button
	    Button cancelBtn = new Button(btnComp, SWT.PUSH);
	    cancelBtn.setText("Cancel");
	    cancelBtn.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event event) {
	        	SILVERBuilder.this.forgetLastBuiltState();
	        	shell.dispose();
	        }
	    });
	    
	    //Close button
	    shell.addShellListener(new ShellAdapter(){
			@Override
			public void shellClosed(ShellEvent e) {
	        	SILVERBuilder.this.forgetLastBuiltState();
	        	shell.dispose();
			}
	    });
	    
	    return shell;
	}
	
	/**
	 * create and return a task for build (compiling Silver's grammar)
	 * 
	 * @param projectPath
	 * @param monitor
	 * @return
	 */
	private FutureTask<Boolean> createBuildTask(
		final IProject project, final String projectPath, final IProgressMonitor monitor) {
		return new FutureTask<Boolean>(
			new Callable<Boolean>() {
		        public Boolean call() {
			    	//PrintStream defaultSystemOutStream = System.out;
			    	//System.setOut(getBuildInfoStream());
		        	
		        	//Let's parse files first to see if there is any syntactic errors. 
		    		if(!parseAll(project, monitor)){
		    			return false;
		    		}
		    		
		    		//Now we can continue the process.
			    	boolean res = SilverAnalysisInvoker.build(//SilverCompilerInvoker
			    		new String[]{"-I", projectPath, grammarLastSelected},
			    		projectPath, 
			    		getConsoleLoggingStream(),
			    		monitor,
			    		SILVERBuilder.this);
			    	
			    	//System.setOut(defaultSystemOutStream);
			    	
					return res;
		        }

				private boolean parseAll(IProject project, final IProgressMonitor monitor) {
					PROJECT_PARSER.reset(monitor);
					
					IResourceDelta delta = SILVERBuilder.this.getDelta(project);
					
					try {
						if(delta!=null){
							delta.accept(PROJECT_PARSER);
						}
						return PROJECT_PARSER.isSuccessful();
					} catch (CoreException e) {
						getPlugin().logException(
				            "The builder encounters a fatal problem during build.", e);
						return false;
					}
				}
				
			}
		);
	}
	
	/** 
	 * A delta visitor used to walk through the project and parse source files
	 * affected by last edit. 
	 */
	private final ResourceDeltaVisitor PROJECT_PARSER 
		= new ResourceDeltaVisitor();

	private class ResourceDeltaVisitor implements IResourceDeltaVisitor {
		
		private boolean success;
		private IProgressMonitor monitor;
		
		@Override
		public boolean visit(IResourceDelta delta)
				throws CoreException {
			IResource resource = delta.getResource();
            if(resource instanceof IFile){
                IFile file = (IFile)resource;
                if(file.exists()){
                    if(isSourceFile(file)){
                    	clearMarkersOn(file);
                    	success &= parseSourceFile(file, monitor);
                    }
                }
            }
            
            //Continue walk-through if any children is affected
            IResourceDelta children[] = delta.getAffectedChildren();
            if(children!=null && children.length>0){
            	return true;
            }
            
			return false;
		}

		public void reset(IProgressMonitor monitor) {
			this.success = true;
			this.monitor = monitor;
		}

		public boolean isSuccessful() {
			return success;
		}
		
	}
	
	/**
	 * Parse source file.
	 * 
	 * @param file
	 * @param monitor
	 * @return true if parsing is successful (no syntax error)
	 */
    private boolean parseSourceFile(final IFile file, IProgressMonitor monitor) {
        try {
        	//Create parser controller
            IParseController parseController = new SILVERParseController();
            
            //Create marker creator
            MarkerCreator markerCreator = new MarkerCreator(file, parseController, PROBLEM_MARKER_ID);
            parseController.getAnnotationTypeInfo().addProblemMarkerType(getErrorMarkerID());

            //Initialize parser controller
            ISourceProject sourceProject = ModelFactory.open(file.getProject());
            parseController.initialize(file.getProjectRelativePath(), sourceProject, markerCreator);

            //Go!
            String contents = BuilderUtils.getFileContents(file);
            Object result = parseController.parse(contents, monitor);
            
            //Nothing returned from parsing. An error must have happened.
            if(result==null){
            	return false;
            }
        } catch (ModelException e) {
            getPlugin().logException(
            	"The builder returns without parsing due to a ModelException", e);
            return false;
        }
        
        return true;
    }
	
	/**
	 * Handle the message list returned by SilverAnalysisInvoker.
	 */
	@Override
	public boolean handle(List<String> list) {
		IProject project = getProject();
		
		//Clean markers
		try {
			project.deleteMarkers(getErrorMarkerID(), false, IResource.DEPTH_INFINITE);
		} catch (CoreException e) {
			e.printStackTrace();
		}

		boolean success = true;
		
		//Add markers
		for(String message:list){
			Problem p = Problem.createProblem(project, message);
			//System.out.println(p);
			//System.out.println(p.getFile().exists());
			
			if(p==null){
				//Parsing error. Shouldn't reach here.
				success = false;
				continue;
			}
			
			IFile file = p.getFile();
			try {
				IMarker marker = file.createMarker(getErrorMarkerID());
				marker.setAttribute(IMarker.MESSAGE, p.getMessage());
				marker.setAttribute(IMarker.LINE_NUMBER, p.getLine());
				//marker.setAttribute(IMarker.CHAR_START, 0);
				//marker.setAttribute(IMarker.CHAR_END, 0);
				marker.setAttribute(IMarker.SEVERITY, p.getLevel());
				if(p.getLevel()==Problem.ERROR){
					success = false;
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		
		return success;
	}
    
	/** Buttons to be disabled during building */
	private MenuItem[] buildMenuItems = null;
	
	/** Name of buttons to be disabled during building */
	private static final String[] BUILD_BTNS_NAME = 
		new String[]{
			StandardMenuLocator.Command.BUILD_ALL, 
			StandardMenuLocator.Command.BUILD_PROJECT
		};

	/**
	 * Enable/disable commands in workbench for build actions.
	 * <p>
	 * This method must be called in UI thread.
	 */
    private synchronized void enableBuildCommands(final boolean enabled) {
    	//Find the standard buttons if not yet done
    	if(buildMenuItems==null){
    		buildMenuItems = new MenuItem[BUILD_BTNS_NAME.length];
    		StandardMenuLocator.getMenuItems(
    			buildMenuItems, StandardMenuLocator.Category.PROJECT, BUILD_BTNS_NAME);
    	}
    	
    	//Enable/disable standard buttons
		for(int i=0; i<buildMenuItems.length; i++){
			MenuItem mi = buildMenuItems[i];
			if(mi!=null){
				mi.setEnabled(enabled);
			}
		}
    			
    	//Enable/disable Quick Build Button
	    ISourceProviderService sourceProviderService = 
	    	(ISourceProviderService) PlatformUI.getWorkbench()
	    		.getActiveWorkbenchWindow().getService(ISourceProviderService.class);
	    QuickBuildCommandState commandStateService = 
	    	(QuickBuildCommandState) sourceProviderService.getSourceProvider(QuickBuildCommandState.ENABLEMENT);
	    commandStateService.setEnabled(enabled);
	}
	/* Silver Builder END */
    
    /**
     * Compile one SILVER file.
     */
    protected void compile(final IFile file, IProgressMonitor monitor) {
        try {
        	//findConsole(getConsoleName()).
        	//getConsoleStream().println("Building SILVER file: " + file.getName());
            getPlugin().writeInfoMsg("Building SILVER file: " + file.getName());

            // START_HERE
            //SILVERCompiler compiler= new SILVERCompiler(PROBLEM_MARKER_ID);
            //compiler.compile(file, monitor);
            // Here we provide a substitute for the compile method that simply
            // runs the parser in place of the compiler but creates problem
            // markers for errors that will show up in the problems view
            runParserForCompiler(file, monitor);

            doRefresh(file.getParent()); // N.B.: Assumes all generated files go into parent folder
        } catch (Exception e) {
            // catch Exception, because any exception could break the
            // builder infra-structure.
            getPlugin().logException(e.getMessage(), e);
        }
    }

    /**
     * This is an example "compiler" implementation that simply uses the parse controller
     * to parse the given file, adding error markers to the file for any parse errors
     * that are reported.
     * 
     * Error markers are created by a special type of message handler (i.e., implementation
     * of IMessageHandler) known as a MarkerCreator.  The MarkerCreator is passed to the
     * parse controller.  The parser reports its error messages to the MarkerCreator, and
     * the MarkerCreator puts corresponding error markers on the file.
     * 
     * This example shows the use of two different types of marker creator:  the MarkerCreator
     * base type and an the MarkerCreatorWithBatching subtype.  In MarkerCreator the error
     * markers are added to the file one at a time, as error messages are received.  In
     * MarkerCreatorWithBatching, the information from each error message is cached; 
     * the corresponding error markers are not created until the flush(..) method is called,
     * at which point all markers are created together.  MarkerCreatorWithBatching is more
     * complicated internally and requires proper use of the flush(..) method, but it may
     * be more efficient at runtime for files that have many errors.  That is because a
     * Workspace operation is required to add the error markers to the file.  There is one
     * of these for each of the error markers added in MarkerCreator, but only one for all
     * of the markers in MarkerCreatorWithBatching.
     * 
     * In this example we have declared a marker creator of each type but commented out the
     * batching version.  The example should also execute correctly if you comment out the
     * base version and uncomment the batching version, so it should be easy to experiment
     * with them.
     * 
     * TODO remove or rename this method once an actual compiler is being called. 
     * 
     * param file    input source file
     * param monitor progress monitor
     */
    protected void runParserForCompiler(final IFile file, IProgressMonitor monitor) {
    	/* //Commented out due to the incompatibility with IMP 0.2 - FIXME*/
        try {
            IParseController parseController = new SILVERParseController();

            // TODO:  Pick a version of the marker creator (or just go with this one)
            MarkerCreator markerCreator = new MarkerCreator(file, parseController, PROBLEM_MARKER_ID);
//			MarkerCreatorWithBatching markerCreator = new MarkerCreatorWithBatching(file, parseController, this);
            
            parseController.getAnnotationTypeInfo().addProblemMarkerType(getErrorMarkerID());

            ISourceProject sourceProject = ModelFactory.open(file.getProject());
            parseController.initialize(file.getProjectRelativePath(), sourceProject, markerCreator);

            String contents = BuilderUtils.getFileContents(file);
            parseController.parse(contents, monitor);
            
            if (markerCreator instanceof MarkerCreatorWithBatching) {
            	((MarkerCreatorWithBatching)markerCreator).flush(monitor);
            }
        } catch (ModelException e) {
            getPlugin().logException("Example builder returns without parsing due to a ModelException", e);
        }
        //*/
    }

}
