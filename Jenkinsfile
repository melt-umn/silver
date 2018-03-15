#!groovy

library "github.com/melt-umn/jenkins-lib"

melt.setProperties(overrideJars: true)

node {
try {
  def WS = pwd()

  stage("Build") {

    checkout scm

    // Bootstrap
    if (params.OVERRIDE_JARS != 'no') {
      // Obtain jars from specified location
      sh "cp ${params.OVERRIDE_JARS}/* jars/"
    } else {
      // We start by obtaining normal jars, but we potentially overwrite them:
      // (This is the least annoying way to go about this...)
      sh "./fetch-jars"
      if (env.BRANCH_NAME == 'develop') {
        // For 'develop', detect pull request merges, and grab jars from the merged branch
        String branch = getMergedBranch()
        if (branch) {
          echo "Using jars from merged branch ${branch}"
          String branchJob = "/melt-umn/silver/${hudson.Util.rawEncode(branch)}"
          copyArtifacts(projectName: branchJob, selector: lastSuccessful(), optional: true)
        }
      } else if (env.BRANCH_NAME != 'master') {
        // For branches, try to obtain jars from previous builds.
        copyArtifacts(projectName: env.JOB_NAME, selector: lastSuccessful(), optional: true)
        copyArtifacts(projectName: env.JOB_NAME, selector: lastCompleted(), optional: true)
        // The above is our attempt to emulate "lastest build that has artifacts."
        // It's not perfect. Essentially we're selecting jars in this priority order:
        // 1. Last build, if it got as far as having artifacts
        // 2. Last successful build, if it exists for this branch
        // 3. Normal `fetch-jars`
      }
    }
    // Build
    sh "./deep-rebuild"
    // Clean
    sh "./deep-clean -delete all"
    // Package
    sh "rm -rf silver-latest* || true" // Robustness to past failures
    sh "./make-dist latest"
    // Upon succeeding at initial build, archive for future builds
    archiveArtifacts(artifacts: "jars/*.jar", fingerprint: true)
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
    // Projects with 'develop' as main branch, we'll try to build specific branch names if they exist
    def public_github_projects = ["ableC"]
    // Specific other jobs to build
    def specific_jobs = ["/melt-umn/Oberon0/master", "/melt-umn/ableJ14/master", "/melt-umn/ableP/master"]
    // TODO: anything to build here?
    //def private_github = []
    // TODO: move these, port them over to new locations, migrate them to pipeline
    def legacy_internal = ["x-metaII-artifacts", "meltsvn-Matlab-host", "meltsvn-simple-core", "meltsvn-simple-host", "meltsvn-simple-matrix", "meltsvn-simple-all", "meltsvn-ring-host", "meltsvn-ring-host-tests"]
    // Notes: The above consists of: Matlab, simple, and
    // two that are especially interesting: ring and the metaII code we wish to keep working.

    def tasks = [:]
    for (t in public_github_projects) { tasks[t] = task_project(t, WS) }
    for (t in specific_jobs) { tasks[t] = task_job(t, WS) }
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

// If the last commit was a pull request merge, get the name of the merged branch
//@NonCPS // 'java.util.regex.Matcher' cannot be serialized, I guess BUT DOING THAT HAS BIZARRE EFFECTS
def getMergedBranch() {
  String commit_msg = sh(script: "git log --format=%B -n 1 HEAD", returnStdout: true)
  java.util.regex.Matcher merge_branch = commit_msg =~ /^Merge pull.*from melt-umn\/(.*)/
  if (merge_branch.find()) {
    return merge_branch.group(1)
  }
}
// Test in local workspace
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
// Tutorial, in silver-latest
def task_tutorial(String tutorialpath, String WS) {
  return {
    node {
      sh "touch ensure_workspace" // convince jenkins to create our workspace
      def GEN = pwd() // This node's workspace
      echo "\nRemember that tutorials are built in 'silver-latest'.\nThis only includes files distributed with the tarball according to 'make-dist'.\n"
      dir(WS + '/silver-latest/tutorials/' + tutorialpath) {
        sh "./silver-compile --clean -G ${GEN}"
        sh "rm *.jar"
      }
      // Blow away these generated files in our private workspace
      deleteDir()
    }
  }
}
// Build project (from /melt-umn/${repo}/develop OR current branch, if it exists
def task_project(String reponame, String WS) {
  return {
    def jobname = "/melt-umn/${reponame}/${hudson.Util.rawEncode(env.BRANCH_NAME)}"
    if (env.BRANCH_NAME != 'develop' && !melt.doesJobExist(jobname)) {
      jobname = "/melt-umn/${reponame}/develop"
    }
    melt.buildJob(jobname, [SILVER_BASE: WS])
  }
}
// Build generic job
def task_job(String jobname, String WS) {
  return {
    melt.buildJob(jobname, [SILVER_BASE: WS])
  }
}

