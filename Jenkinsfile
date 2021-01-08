#!groovy

library "github.com/melt-umn/jenkins-lib"

melt.setProperties(overrideJars: true)

melt.trynode('silver') {
  def WS = pwd()
  def SILVER_GEN = "${WS}/generated"
  def newenv = silver.getSilverEnv(WS)

  stage("Build") {

    checkout scm

    // Bootstrap logic to obtain jars
    if (params.OVERRIDE_JARS != 'no') {
      def source = params.OVERRIDE_JARS
      if (source == 'develop') {
        source = "${silver.SILVER_WORKSPACE}/jars"
      }
      // Obtain jars from specified location
      sh "mkdir -p jars"
      sh "cp ${source}/* jars/"
      melt.annotate("Jars overridden.")
    } else {
      // We start by obtaining normal jars, but we potentially overwrite them:
      // (This is the least annoying way to go about this...)
      sh "./fetch-jars"
      if (env.BRANCH_NAME == 'develop') {
        // For 'develop', detect pull request merges, and grab jars from the merged branch
        String branch = getMergedBranch()
        if (branch) {
          echo "Trying to get jars from merged branch ${branch}"
          String branchJob = "/melt-umn/silver/${hudson.Util.rawEncode(branch)}"
          if(melt.doesJobExist(branchJob)) {
            try {
              copyArtifacts(projectName: branchJob, selector: lastSuccessful())
              melt.annotate("Jars from merged branch.")
            } catch (hudson.AbortException exc1) {
              // That's okay. We prefer this approach to using 'optional: true' because it
              // lets us know whether it happened or not. The annotation only gets set when it does.
            }
          }
        }
      } else if (env.BRANCH_NAME != 'master') {
        // For branches, try to obtain jars from previous builds.
        try {
          // If the last build has artifacts, use those.
          copyArtifacts(projectName: env.JOB_NAME, selector: lastCompleted())
          melt.annotate("Jars from branch (prev).")
        } catch (hudson.AbortException exc2) {
          try {
            // If there is a last successful build, use those.
            copyArtifacts(projectName: env.JOB_NAME, selector: lastSuccessful())
            melt.annotate("Jars from branch (successful).")
          } catch (hudson.AbortException exc3) {
            // That's okay. We tried. We'll stick with fetch-jars
          }
        }
      }
    }
    // If requested, go download the latest Copper jars and use them instead of the archived/provided ones
    if (params.FETCH_COPPER_JARS == 'yes') {
      echo "Fetching lastest Copper jars"
      melt.annotate("Fetched Copper jars.")
      sh "./fetch-jars --copper"
    }
    // Build
    sh "./deep-rebuild"
    // Clean (but leave generated files)
    sh "./deep-clean -delete"
    // Package
    sh "rm -rf silver-latest* || true" // Robustness to past failures
    sh "./make-dist latest"
    // Upon succeeding at initial build, archive for future builds
    archiveArtifacts(artifacts: "jars/*.jar", fingerprint: true)
  }

  stage("Test") {
    def tests = ["silver_features", "copper_features", "patt", "stdlib", "performance", "csterrors"]
    def tuts = ["simple/with_all", "simple/with_do_while", "simple/with_repeat_until", "simple/with_implication", "simple/host", "dc", "lambda", "turing", "hello"]

    def tasks = [:]
    tasks << tests.collectEntries { t -> [(t): task_test(t, WS)] }
    tasks << tuts.collectEntries { t -> [(t): task_tutorial(t, WS)] }

    // Build test driver
    withEnv (newenv) {
      dir ("${WS}/tests") {
        sh "silver silver:testing:bin"
      }
    }

    // Unpack tarball (into ./silver-latest/) (for tutorial testing)
    sh "tar zxf silver-latest.tar.gz"
    // Run tests
    parallel tasks
    // Clean
    sh "rm -rf silver-latest"
  }

  stage("Integration") {
    // Projects with 'develop' as main branch, we'll try to build specific branch names if they exist
    def github_projects = ["/melt-umn/ableC", "/melt-umn/Oberon0", "/melt-umn/ableJ14", "/melt-umn/meta-ocaml-lite",
                           "/melt-umn/lambda-calculus", "/melt-umn/rewriting-regex-matching", "/melt-umn/rewriting-optimization-demo",
                           "/internal/ring"]
    // Specific other jobs to build
    def specific_jobs = ["/internal/matlab/master", "/internal/metaII/master", "/internal/simple/master"]
    // AbleP is now downstream from Silver-AbleC, so we don't need to build it here: "/melt-umn/ableP/master"

    def tasks = [:]
    tasks << github_projects.collectEntries { t ->
      [(t): { melt.buildProject(t, [SILVER_BASE: WS, SILVER_GEN: SILVER_GEN]) }]
    }
    tasks << specific_jobs.collectEntries { t ->
      [(t): { melt.buildJob(t, [SILVER_BASE: WS, SILVER_GEN: SILVER_GEN]) }]
    }

    // Do downstream integration testing
    parallel tasks
  }

  if (env.BRANCH_NAME == 'develop') {
    stage("Deploy") {
      sh "rsync -a --exclude .git --exclude generated --exclude silver-latest.tar.gz --delete --delete-excluded ./ ${silver.SILVER_WORKSPACE}/"
      // -a is archive mode: rlptgoD (recurse, links, perms, time, group, owner, devices)
      // --delete  Remove files from destination not in src
      // --delete-excluded  Remove files from dest that we're excluding
      // NOTE: we exclude generated, which means there's no generated dir in the custom
      // location, which means if you don't set it, things should blow up.

      sh "cp silver-latest.tar.gz ${melt.ARTIFACTS}/"
      sh "cp jars/*.jar ${melt.ARTIFACTS}/"
    }
  }

}

// If the last commit was a pull request merge, get the name of the merged branch
def getMergedBranch() {
  String commit_msg = sh(script: "git log --format=%B -n 1 HEAD", returnStdout: true)
  // Matcher cannot be serialized, but we can't mark this function @NonCPS because above we use 'sh'
  // So, YOLO, we probably won't get paused before we return!
  java.util.regex.Matcher merge_branch = commit_msg =~ /^Merge pull.*from melt-umn\/(.*)/
  if (merge_branch.find()) {
    return merge_branch.group(1)
  }
}

// Test in local workspace
def task_test(String testname, String WS) {
  def newenv = silver.getSilverEnv(WS)
  return {
    node {
      sh "touch ensure_workspace" // convince jenkins to create our workspace
      def GEN = pwd() // This node's workspace
      // Go back to our "parent" workspace, into the test
      dir(WS + '/test/' + testname) {
        // HACK: edit the test specs to specify the generated directory
        sh "find . -name \\'*.test\\' -exec sed -i\\'\\' \\'s/\\(run: [^ ]*silver[^ ]*\\) /\\\\1 -G ${GEN.replaceAll("/", "\\/")} /g\\' {} \\;"
        // Run the tests
        withEnv (newenv) {
          sh "java -jar ../silver.testing.bin.jar"
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

