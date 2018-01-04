// An excellent resource is Maven's own Jenkinsfile: https://github.com/apache/maven/blob/master/Jenkinsfile
// This is also helpful: https://github.com/jenkinsci/pipeline-plugin/blob/master/TUTORIAL.md

properties([
  /* If we don't set this, everything is preserved forever.
     We don't bother discarding build logs (because they're small),
     but if this job keeps artifacts, we ask them to only stick around
     for awhile. */
  [ $class: 'BuildDiscarderProperty',
    strategy:
      [ $class: 'LogRotator',
        artifactDaysToKeepStr: '120',
        artifactNumToKeepStr: '20'
      ]
  ]
])

// Location where we dump stable artifacts: jars, tarballs
MELT_ARTIFACTS = '/export/scratch/melt-jenkins/custom-stable-dump'
// Location of a Silver checkout (w/ jars)
MELT_SILVER_WORKSPACE = '/export/scratch/melt-jenkins/custom-silver'
// N.B. these declarations must not have 'def' or a type because then they're
// invisible to functions because ??? Groovy never heard of lexical scope?

node {

def WS = pwd()
if (env.BRANCH_NAME == 'develop') {
  WS = MELT_SILVER_WORKSPACE
}

ws(WS) {
// If we use 'ws' we re-allocate our own workspace in branches and thus end up in @2
// instead of the original workspace.
// But if we use 'dir' then we potentially stomp on a concurrent build in the special
// workspace for 'develop'.
// Possibly we should just stop having a special *workspace* for develop and instead
// copy our workspace there when it builds ok, SOMEDAY. TODO
// For now, re-discover where we ended up:
WS = pwd()


try {

  stage("Build") {

    // Checks out this repo and branch
    checkout scm

    sh "./fetch-jars"
    sh "./deep-rebuild"
    sh "./deep-clean -delete all"
    sh "rm -rf silver-latest* || true" // Robustness to past failures
    sh "./make-dist latest"

    // An "installation" of what gets distributed in the tarball under ./silver-latest/
    sh "tar zxf silver-latest.tar.gz"
  }

  stage("Test") {
    def tests = ["silver_features", "copper_features", "patt", "stdlib", "performance", "cstast", "csterrors"]
    def tuts = ["simple/with_all", "simple/with_do_while", "simple/with_repeat_until", "simple/with_implication", "simple/host", "dc", "lambda", "turing", "hello"]

    def tasks = [:]
    for (t in tests) { tasks[t] = task_test(t, WS) }
    for (t in tuts) { tasks[t] = task_tutorial(t, WS) }

    parallel tasks

    // Done with tarbal contents
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

    parallel tasks
  }

  if (env.BRANCH_NAME == 'develop') {
    stage("Deploy") {
      sh "cp ${WS}/silver-latest.tar.gz ${MELT_ARTIFACTS}/"
      sh "cp ${WS}/jars/*.jar ${MELT_ARTIFACTS}/"
    }
  }

} catch(e) {

  // JENKINS-28822. Not sure if this works exactly as intended or not
  if(currentBuild.result == null) {
    echo "Setting failure flag"
    currentBuild.result = 'FAILURE'
  }

  throw e

} finally {

  // No notifications yet

}

} // end ws

} // end node


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
    build job: jobname, parameters:
      [[$class: 'StringParameterValue', name: 'SILVER_BASE', value: WS]]
  }
}

