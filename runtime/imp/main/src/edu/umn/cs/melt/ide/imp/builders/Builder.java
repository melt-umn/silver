package edu.umn.cs.melt.ide.imp.builders;

import ide.NIdeEnv;
import ide.NIdeMessage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;

import common.ConsCell;
import common.DecoratedNode;
import common.Lazy;
import common.TopNode;
import common.javainterop.ConsCellCollection;

import core.NIOVal;

import edu.umn.cs.melt.ide.impl.SVInterface;
import edu.umn.cs.melt.ide.impl.SVRegistry;
import edu.umn.cs.melt.ide.silver.misc.Problem;
import edu.umn.cs.melt.ide.silver.property.ProjectProperties;

/**
 * Standard eclipse builder implementation.
 */
public class Builder extends IncrementalProjectBuilder {

	public Builder() {}
	
	@Override
	synchronized protected IProject[] build(int kind, Map/*<String, String> - mvn build complains??*/ args,
			IProgressMonitor monitor) throws CoreException {
		
		final IProject project = getProject();
		final SVInterface sv = SVRegistry.get();
		
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
		
		// TODO: likewise, this could be cached.
		final NIdeEnv env = computeIdeEnv(project);
		
		// Run the build
		final NIOVal undecorated_build_result = sv.build(properties.serializeToSilverType(), env, null);
		final DecoratedNode build_result = undecorated_build_result.decorate(TopNode.singleton, (Lazy[])null);
		// demand evaluation of io actions
		build_result.synthesized(core.Init.core_io__ON__core_IOVal);
		
		final ConsCell errors = (ConsCell)build_result.synthesized(core.Init.core_iovalue__ON__core_IOVal);
		
		// Clear old markers this builder had perhaps created.
		// TODO: we should preserve existing markers that haven't changed as much as possible. oh well.
		project.deleteMarkers(sv.markerErrorName(), true, IResource.DEPTH_INFINITE);
		
		boolean stopBuild = renderMessages(errors, project, sv); 
		
		if(stopBuild)
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
					final NIOVal undecorate_post_result = sv.postbuild(properties.serializeToSilverType(), env, null);
					final DecoratedNode post_result = undecorate_post_result.decorate(TopNode.singleton, (Lazy[])null);
					post_result.synthesized(core.Init.core_io__ON__core_IOVal);
					
					final ConsCell post_errors = (ConsCell)post_result.synthesized(core.Init.core_iovalue__ON__core_IOVal);
	
					// TODO: it's now possible that we do need to batch these marker creations?
					// because we're outside the (probable?) AVOID_UPDATE of the build function.
					// (in this separate thread)
					
					// otoh, this should be used very rarely: normal errors are already created
					// this is just special post build failures only.
					try {
						renderMessages(post_errors, project, sv);
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

	public static boolean renderMessages(ConsCell errors, IProject project, SVInterface sv) throws CoreException {
		boolean stopBuild = false;
		for(NIdeMessage msg : new ConsCellCollection<NIdeMessage>(errors)) {
			// it seems we do not need to worry about batching changes, as a builder gets called
			// with AVOID_UPDATE. apparently. I'm guessing. from the fact that markers don't appear
			// until this function returns.
			Problem p = Problem.extractProblem(project, msg);
			p.createMarker(project, sv.markerErrorName());
			stopBuild = stopBuild || p.buildBlocker();
		}
		return stopBuild;
	}
	
	public static NIdeEnv computeIdeEnv(IProject fProject) {
		IPath path = fProject.getLocation();
		
		String projectPath = path.toOSString();
		String generatedPath = path.toOSString() + "/" + "bin";
		String platformPath = null;
		
		try {
			Location platform = Platform.getInstallLocation();
			File parent = new File(FileLocator.resolve(platform.getURL()).toURI());
			platformPath = parent.getAbsolutePath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		NIdeEnv env = new ide.PmakeIdeEnv(
            new common.StringCatter(fProject.getName()), 
            new common.StringCatter(projectPath), 
            new common.StringCatter(generatedPath), 
            new common.StringCatter(platformPath),
            fProject
        );
		
		return env;
	}
}
