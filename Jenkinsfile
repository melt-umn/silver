#!groovy

library "github.com/melt-umn/jenkins-lib"

melt.setProperties()

node {
try {
  def WS = pwd()

  stage("Build") {

    checkout scm

    // Bootstrap
    sh "./fetch-jars"
    // Build
    sh "./deep-rebuild"
    // Clean
    sh "./deep-clean -delete all"
    // Package
    sh "rm -rf silver-latest* || true" // Robustness to past failures
    sh "./make-dist latest"
  }

  stage("Test") {
    def tests = ["silver_features", "copper_features", "patt", "stdlib", "performance", "cstast", "csterrors"]
    def tuts = ["simple/with_all", "simple/with_do_while", "simple/with_repeat_until", "simple/with_implication", "simple/host", "dc", "lambda", "turing", "hello"]

    def tasks = [:]
    for (t in tests) { tasks[t] = task_test(t, WS) }
    for (t in tuts) { tasks[t] = task_tutorial(t, WS) }

    // Unpack tarball (into ./silver-latest/) (for tutorial testing)
    sh "tar zxf silver-latest.tar.gz"
    // Run tests
    parallel tasks
    // Clean
    sh "rm -rf silver-latest"
  }

  stage("Integration") {
    def public_github = ["ableC"]
    // TODO: anything to build here?
    //def private_github = []
    // TODO: move these, port them over to new locations, migrate them to pipeline
    def legacy_internal = ["x-metaII-artifacts", "meltsvn-Oberon0-A1", "meltsvn-Oberon0-A2a", "meltsvn-Oberon0-A2b", "meltsvn-Oberon0-A3", "meltsvn-Oberon0-A4", "meltsvn-Oberon0-A5", "meltsvn-Matlab-host", "meltsvn-ableP-host", "meltsvn-ableP-host-tests", "meltsvn-ableP-promelaCore", "meltsvn-ableP-promelaCore-tests", "meltsvn-ableP-aviation", "meltsvn-ableP-aviation-tests", "meltsvn-ableJ-alone", "meltsvn-ableJ-autoboxing", "meltsvn-ableJ-sql", "meltsvn-ableJ-complex", "meltsvn-ableJ-foreach", "meltsvn-ableJ-tables", "meltsvn-ableJ-pizza", "meltsvn-ableJ-rlp", "meltsvn-simple-core", "meltsvn-simple-host", "meltsvn-simple-matrix", "meltsvn-simple-all", "meltsvn-ring-host", "meltsvn-ring-host-tests"]
    // Notes: The above consists of: Oberon0, Matlab, ableP, ableJ, simple, and
    // two that are especially interesting: ring and the metaII code we wish to keep working.

    def tasks = [:]
    for (t in public_github) { tasks[t] = task_job("/melt-umn/" + t + "/develop", WS) }
    for (t in legacy_internal) { tasks[t] = task_job("/" + t, WS) }

    // Early deploy
    if (env.BRANCH_NAME == 'develop') {
      // Legacy builds don't know about SILVER_BASE,
      // So we copy ourselves early into the default workspace, now
      // instead of as a final deploy task later.
      
      // NOTE: This means some builds may fail in develop that don't fail in branch.
      // So, uh, let's try to get rid of those legacy builds, huh?
      
      sh "rsync -a --exclude .git --exclude generated --exclude silver-latest.tar.gz --delete --delete-excluded ./ ${melt.SILVER_WORKSPACE}/"
      // -a is archive mode: rlptgoD (recurse, links, perms, time, group, owner, devices)
      // --delete  Remove files from destination not in src
      // --delete-excluded  Remove files from dest that we're excluding
      
      // NOTE: we exclude generated, which means there's no generated dir in the custom
      // location, which means if you don't set it, things should blow up.
    }
    // Do downstream integration testing
    parallel tasks
  }

  if (env.BRANCH_NAME == 'develop') {
    stage("Deploy") {
      sh "cp silver-latest.tar.gz ${melt.ARTIFACTS}/"
      sh "cp jars/*.jar ${melt.ARTIFACTS}/"
    }
  }

} catch(e) {
  melt.handle(e)
} finally {
  melt.notify(job: "silver")
}
} // node


def task_test(String testname, String WS) {
  return {
    node {
      sh "touch ensure_workspace" // convince jenkins to create our workspace
      def GEN = pwd() // This node's workspace
      // Go back to our "parent" workspace, into the test
      dir(WS + '/test/' + testname) {
        sh "./silver-compile --clean -G ${GEN}"
        if (fileExists("test.jar")) {
          sh "java -jar test.jar"
          sh "rm test.jar"
        }
      }
      // Blow away these generated files in our private workspace
      deleteDir()
    }
  }
}
def task_tutorial(String tutorialpath, String WS) {
  return {
    node {
      sh "touch ensure_workspace" // convince jenkins to create our workspace
      def GEN = pwd() // This node's workspace
      // For tutorials we use what's distributed in the tarball:
      dir(WS + '/silver-latest/tutorials/' + tutorialpath) {
        sh "./silver-compile --clean -G ${GEN}"
        sh "rm *.jar"
      }
      // Blow away these generated files in our private workspace
      deleteDir()
    }
  }
}

def task_job(String jobname, String WS) {
  return {
    melt.buildJob(jobname, [SILVER_BASE: WS])
  }
}

