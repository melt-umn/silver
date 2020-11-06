package edu.umn.cs.melt.ide.imp.builders;

import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

import common.ConsCell;
import common.DecoratedNode;
import common.IOToken;
import common.Lazy;
import common.TopNode;
import common.javainterop.ConsCellCollection;

import core.NIOVal;
import silver.langutil.NMessage;

import edu.umn.cs.melt.ide.silver.misc.Problem;
import edu.umn.cs.melt.ide.silver.property.ProjectProperties;

import edu.umn.cs.melt.ide.util.ReflectedCall;

/**
 * Standard eclipse builder implementation. Example configuration:
    <extension point="org.eclipse.core.resources.builders" id="builder" name="Silver builder">
      <builder hasNature="true">
        <run class="edu.umn.cs.melt.ide.imp.builders.Builder">
          <parameter name="markerName" value="silver.composed.idetest.builder.problem" />
          <parameter name="silver_build" value="silver:composed:idetest:analyze" />
          <parameter name="silver_postbuild" value="silver:composed:idetest:generate" />
        </run>
      </builder>
    </extension>
 *
 * Runs `build` and optionally `postbuild`.
 * These should have Silver type `IOVal<[Message]> ::= IdeProject [IdeProperty] IO`.
 */
public class Builder extends IncrementalProjectBuilder implements IExecutableExtension {

	private ReflectedCall<NIOVal> sv_build;
	private ReflectedCall<NIOVal> sv_postbuild;
	private String markerName;
	
	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		if(!(data instanceof java.util.Hashtable))
			return;
		
		java.util.Hashtable<String, String> d = (java.util.Hashtable<String, String>)data;
		
		markerName = d.get("markerName");
		String build = d.get("silver_build");
		if(build != null)
			sv_build = new ReflectedCall<NIOVal>(build, 3);
		String postbuild = d.get("silver_postbuild");
		if(postbuild != null)
			sv_postbuild = new ReflectedCall<NIOVal>(postbuild, 3);
	}

	public Builder() {}
	
	/**
	 * General process:
	 * 1. Constructed.
	 * 2. `setInitializationData` with data from plugin.xml
	 * 3. Base class stuff gets initialized, e.g. `setProject`
	 * 4. We get called.
	 */
	@Override
	synchronized protected IProject[] build(int kind, Map/*<String, String> - mvn build complains??*/ args,
			IProgressMonitor monitor) throws CoreException {
		
		final IProject project = getProject();
		
		// TODO: I'm not sure if this should be here, but it was the behavior of the old builder to create this
		IFolder gen_folder = project.getFolder("bin");
		if(!gen_folder.exists()) {
			gen_folder.create(IResource.FORCE|IResource.DERIVED|IResource.HIDDEN, true, null);
		}
		
		// TODO: inefficient because we re-parse the properties file every time. should cache
		// Recommendation: use project.set/getSessionProperty, probably within ProjectProperties itself.
		// After all, why are we doing all this getLocation toString stuff ourselves, when it could do it?
		final ProjectProperties properties =
				ProjectProperties.getPropertyPersister(project.getLocation().toString());
		
		// Run the build
		final NIOVal undecorated_build_result =
			sv_build.invoke(new Object[]{project, properties.serializeToSilverType(), IOToken.singleton});
		final DecoratedNode build_result = undecorated_build_result.decorate();
		// demand evaluation of io actions
		build_result.synthesized(core.Init.core_io__ON__core_IOVal);
		
		final ConsCell errors = (ConsCell)build_result.synthesized(core.Init.core_iovalue__ON__core_IOVal);
		
		// Clear old markers this builder had perhaps created.
		// TODO: we should preserve existing markers that haven't changed as much as possible. oh well.
		project.deleteMarkers(markerName, true, IResource.DEPTH_INFINITE);
		
		boolean stopBuild = renderMessages(errors, project, markerName); 
		
		if(stopBuild || sv_postbuild == null)
			return new IProject[0];
		
		// TODO: look up what to do with monitor. this is a point where we could throw if canceled.
		if(monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		
		// In order to update the markers immediately, we run the post build in a separate thread,
		// so we can return from this one, updating the workspace.
		
		// We'll have the thread run exclusively on this builder.
		final Builder lock = this;
		
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				// Now, invoke the post-builder.
				synchronized(lock) {
					final NIOVal undecorate_post_result = 
						sv_postbuild.invoke(new Object[]{project, properties.serializeToSilverType(), IOToken.singleton});
					final DecoratedNode post_result = undecorate_post_result.decorate();
					post_result.synthesized(core.Init.core_io__ON__core_IOVal);
					
					final ConsCell post_errors = (ConsCell)post_result.synthesized(core.Init.core_iovalue__ON__core_IOVal);
	
					// TODO: it's now possible that we do need to batch these marker creations?
					// because we're outside the (probable?) AVOID_UPDATE of the build function.
					// (in this separate thread)
					
					// otoh, this should be used very rarely: normal errors are already created
					// this is just special post build failures only.
					try {
						renderMessages(post_errors, project, markerName);
					} catch (CoreException e) {
						// TODO: who knows.
						e.printStackTrace();
					}
				}
			}
		};
		
		// just go in the background
		new Thread(thread).start();
		
		// done!
		return new IProject[0];
	}

	public static boolean renderMessages(ConsCell errors, IProject project, String markerName) throws CoreException {
		boolean stopBuild = false;
		for(NMessage msg : new ConsCellCollection<NMessage>(errors)) {
			// it seems we do not need to worry about batching changes, as a builder gets called
			// with AVOID_UPDATE. apparently. I'm guessing. from the fact that markers don't appear
			// until this function returns.
			Problem p = Problem.extractProblem(msg);
			p.createMarker(project, markerName);
			stopBuild = stopBuild || p.buildBlocker();
		}
		return stopBuild;
	}
}
