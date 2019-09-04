---
layout: sv_wiki
title: Eclipse internals
menu_weight: 30.0
---

Understanding the eclipse plugin generation part of Silver requires understanding quite a lot of layers of abstraction and tooling that are an essential part of how eclipse plugins work. This is a rough, short guide.

  1. You should get passingly familiar with Maven and `pom.xml`. Maven is a tool for building Java projects. In particular, it deals with obtaining artifacts that a project depends upon, and generating the resulting artifact for a project. (Generally, a jar file.) Maven has some conventions that are best not violated, because things get painful if you do. The good news is these conventions are actually mostly sensible. The bad news is that we do occasionally have reason to violate them. It's mostly our fault.
  1. Next up, a basic understanding of OSGi is helpful. OSGi is the packaging system used by Eclipse. It takes jar files and amends them with additional metadata in the `MANIFEST.MF` file. This metadata indicates what other OSGi packages this jar depends upon (and versions), as well as what is exported from that jar (at what versions.) OSGi can get pretty complicated, mostly because it tries to be stupidly flexible for sometimes silly reasons, but the bare-bones basics are just that.
  1. Next, the Tycho plugin for Maven. Tycho modifies a maven build by, instead of looking to the `pom.xml` for project dependencies and such, it instead looks to the OSGi `MANIFEST.MF` data. Additionally, Tycho knows how to fetch dependencies from Eclipse p2 repositories. This allows us to build Eclipse plugins from outside of eclipse: tycho/maven will download the dependent jars automatically from the p2 repository because our `MANIFEST.MF` depends upon them (necessary for our eclipse plugin to ever work at all), and then build our jar.
  1. Next up, understanding eclipse `plugin.xml` infrastructure. There are two basic entry points for an eclipse plugin: The first is `plugin.xml` which tells eclipse what features this plugin actually provides. This contains essentially all the "hooks" where eclipse will call into our plugin somehow, and usually involves saying "for this feature that is supposed to implement this interface, instantiate this class with this initialization data." The second basic entry point is the "bundle activator" which is run before any of the class files from this OSGi bundle are actually made use of. We make use of this to call the silver generated `Init` classes.
  1. Next, the extension points we contribute extensions to do not just include those from eclipse -- this is also how we make use of imp. Imp has its own extension points that it introduces. These are almost all related to the editor and parsing and such.
  1. Finally, there's the plugin we actually generate. This plugin has an appropriate OSGi manifest, and a `plugin.xml` for eclipse. For the most part otherwise, it simply embeds other jars: the silver and copper runtimes, the generated silver jar, and the silver-eclipse runtime. (Instead of embedding imp, we depend upon it as a separate osgi package.) Most all of the java code dealing with eclipse is in the silver-eclipse runtime. The generated code should be kept to a minimum, as it's hard to maintain otherwise. This means most of the eclipse extension points point directly at classes in the silver-eclipse runtime, and provide configuration data telling those classes how to work for this particular plugin. There are also a couple of generated java files, in particular an implementation of `SVInterface` that provides mostly function pointers to the runtime.

## Editing the Silver-Eclipse runtime

Step 1: run from a shell the `build.sh` in `runtime/imp`. The build for this package requires lying a bit about what's in it. This will put `core` and `ide` grammars generated java code into `runtime/imp/main/src/`, so it can find this stuff.

Step 2: You should be able to File->Import an existing Maven project. Point this to the `runtime/imp/main` and it should find the `pom.xml`.

Step 3: Not quite done yet! Although the `pom.xml` has instructions to include `SilverRuntime.jar` and `CopperRuntime.jar` for `mvn` builds from the command line, eclipse does not understand these. So we also have to right click the project, Properties, Java Build Path, Libraries, Add External JARs... and add these jars to the class path. This does not affect the maven build, it only affects eclipse's internal java compiler (by modifying the `.classpath` file in the project root. This is eclipse's, not maven's.)

Step 4: It should now build successfully. However, I haven't figured out how to get JavaDoc for `org.eclipse.*` stuff to work yet. If you figure that out, FIXME.

## Example 1: the build process

You save a file in eclipse. A build happens as a result. How does this work?

Step 1: let's look at what's contributed to `plugin.xml`: (this from `generated/ide/silver.composed.idetest/plugin`)

```
<extension point="org.eclipse.core.resources.builders" id="builder" name="Silver builder">
  <builder hasNature="true">
    <run class="edu.umn.cs.melt.ide.imp.builders.Builder">
    </run>
  </builder>
</extension>

<extension point="org.eclipse.core.resources.natures" id="nature" name="Silver Nature">
  <builder id="silver.composed.idetest.builder" />
  <runtime>
    <run class="edu.umn.cs.melt.ide.imp.builders.Nature">
      <parameter name="builder" value="silver.composed.idetest.builder" />
    </run>
  </runtime>
</extension>
```

We contribute a "nature" (a flag, essentially, that manages how building works) and a "builder" (something the nature inserts into the build order for this project.)

This code is generated from an `IdeSpec` in grammar `silver:modification:impide:spec`.

You can see the class implementing the Eclipse builder and nature (this does not make use of IMP at all) is from the Silver-Eclipse runtime (e.g. edu.umn.cs.melt.ide.imp.builders.Nature). Note that here we're passing data to this class (in particular, the name of the builder is given as a parameter, `silver.composed.idetest.builder`. You can see that information extracted and used in the `Nature` class referenced.)

If we take a look at the implementation of the referenced `Builder` class from the runtime, we can see how the builder actually gets called (with lots of code omitted):

```
final SVInterface sv = SVRegistry.get();

... = sv.build(project, properties, null);

... = sv.postbuild(project, properties, null);
```

This obtains an `SVInterface` object, and calls the build methods on it. Right now, SVRegistry just has a `get()` method, because we embed the silver-eclipse runtime into the generated plugin jar. As a result, each runtime copy has only one plugin corresponding. In the future, this might change to require us to pass the name of the plugin to an independent silver-eclipse runtime that would work with multiple plugins. In that case, we would now need to pass the name of the plugin (the string to pass to `get`) as a parameter to the builder and nature.

You can go glance at what's on `SVInterface`, but that is implemented by a class we generate, so from `SVIdeInterface.java` in `generated/ide/silver.composed.idetest/plugin/src/silver/composed/idetest` we see:

```
@Override
public NIOVal build(IProject project, ConsCell properties, common.IOToken iotoken) {
        return (NIOVal)silver.composed.idetest.Panalyze.invoke(project, properties, iotoken);
}

@Override
public NIOVal postbuild(IProject project, ConsCell properties, common.IOToken iotoken) {
        return (NIOVal)silver.composed.idetest.Pgenerate.invoke(project, properties, iotoken);
}
```

In other words, these are basically function pointers to the Silver functions that were provided in the IDE declaration block.

There's one last piece tying this together, which is how the `SVRegistry` knows about this implementation. That's right next door in the `Plugin.java` file:

```
public void start(BundleContext context) throws Exception {
    Init.initAllStatics();
    Init.init();
    Init.postInit();
    SVRegistry.register(new SVIdeInterface());
}
```

And this is called because of this line in `generated/ide/silver.composed.idetest/plugin/META-INF/MANIFEST.MF`

```
Bundle-Activator: silver.composed.idetest.Plugin
```

i.e., whenever Eclipse wants to start using our plugin, it calls `start`, that registers the interface with the function pointers, and a project with the appropriate nature will have the appropriate builder, which means a save will call those function pointers.

Whew.

## Example 2: Syntax coloring

Again, let's start with `plugin.xml`:

```
<extension point="org.eclipse.imp.runtime.languageDescription">
  <language extensions="sv" description="nothing here" natureID="silver.composed.idetest.nature" language="Silver">
  </language>
</extension>
<extension point="org.eclipse.imp.runtime.parser">
  <parserWrapper class="edu.umn.cs.melt.ide.imp.services.ParseController" language="Silver">
  </parserWrapper>
</extension>
<extension point="org.eclipse.imp.runtime.tokenColorer">
  <tokenColorer class="edu.umn.cs.melt.ide.imp.services.Colorer" language="Silver">
  </tokenColorer>
</extension>
```

I've included some IMP prerequisites to just the `tokenColorer`.

Before we even look at these classes in the runtime, if you're paying attention, you can probably guess there's something in our SVInterface, too. You'd be right:

```
@Override
public ITokenClassifier getTokenClassifier() {
	return new silver.composed.idetest.imp.coloring.Parser_silver_composed_idetest_svParse_TokenClassifier();
}
private silver.composed.idetest.copper.parser.Parser_silver_composed_idetest_svParse parser = new silver.composed.idetest.copper.parser.Parser_silver_composed_idetest_svParse();
@Override
public IdeParseResult<Node> parse(Reader input, String filename) throws CopperParserException, IOException {
	// In the long run, maybe we should have a getParser() rather than parse() so things could be concurrent... TODO
	synchronized(parser) {
		parser.reset();
		return new IdeParseResult<Node>((Node)parser.parse(input, filename), parser.getTokens());
	}
}
```

So right now we have:

  1. A ParseController implementing some IMP interface
  1. A Colorer implementing some IMP interface
  1. Something called a ITokenClassifier that we implement for our plugin
  1. A result type called IdeParseResult that our parser returns to the silver-eclipse runtime. (Or it throws a CopperParserException.)
  1. A custom-generated parser with a `getTokens` method on it (see grammar `silver:modification:impide:cstast` for the implementation of this.)

If we investigate the parse controller, we find the `getTokenIterator` method. It calls a corresponding method on IdeParseResult, which we discover just stores a list of tokens (as well as the parse tree).

By implication, we must assume the imp framework will get this list, and interate over it, and for each token, it consults the Colorer we gave IMP. This Colorer does nothing but use the `ITokenClassifier` we gave the `SVInterface` to figure out what color to use.

The implementation of this interface is generated in `silver:modification:impide:spec` from an `IdeSpec` and the `ParserSpec` it contains. It works by translating copper token numbers to string names, and looking up in a map what font/color that terminal name was given by font modifiers in the silver source.
