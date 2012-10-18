#!/usr/bin/python

import os
import sys
import glob
import string
import httplib
import base64

## BEGIN: Configure this before running!

# Silver is built here. Shared workspace because everything needs to use it.
silverSharedWorkspace = "/export/scratch/melt-jenkins/custom-silver"
# "Deployment test" location.  The make-dist script is used, and unpacked here. This is where tutorials are tested!!
silverDeployWorkspace = "/export/scratch/melt-jenkins/custom-deploy"
# Where to dump the latest tar and jars when all tests and builds succeed.
silverStableWorkspace = "/export/scratch/melt-jenkins/custom-stable-dump"
# URL for the jenkins server
jenkinsURL = "localhost:8080"
# User name and API token. Note this is not your password! Check user config page to see it.
jenkinsUsername = "tedinski"
jenkinsAPIToken = "33f325b9b8a8cacd5cf612e2017a91e1"

## END: config stuff

# We just do http basic auth manually because it's easier than the alternatives, apparently.
jenkinsAuth = "Basic " + base64.encodestring('%s:%s' % (jenkinsUsername, jenkinsAPIToken))[:-1]

#############

def silverInvoke(grammar, include="", generated="", output=""):
	cmdStr = "java -Xmx2000M -Xss4M -jar " + silverSharedWorkspace + "/jars/RunSilver.jar --clean"
	if include != "":
		cmdStr += " -I " + include
	if generated != "":
		cmdStr += " -G " + generated
	if output != "":
		cmdStr += " -o " + output
	return cmdStr + " " + grammar + "\nant"

#####
class JenkinsJobConfig:
	def __init__(self, jobname):
		self.jobname = jobname
		
	def configXml(self):
		assert self.description != "", "Must provide description"
		assert self.command != "", "Must provide command"
		return string.Template("""<?xml version='1.0' encoding='UTF-8'?>
<project>
  <actions/>
  <description>${description}</description>
  <keepDependencies>false</keepDependencies>
  <properties/>
  <scm class="hudson.scm.NullSCM"/>
  <canRoam>true</canRoam>
  <disabled>false</disabled>
  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
  <blockBuildWhenUpstreamBuilding>true</blockBuildWhenUpstreamBuilding>
  <triggers class="vector"/>
  <concurrentBuild>false</concurrentBuild>
  <builders>
    <hudson.tasks.Shell>
      <command>${command}</command>
    </hudson.tasks.Shell>
  </builders>
  <publishers/>
  <buildWrappers/>
</project>""").substitute(self.__dict__)

	def getJobName(self):
		return self.jobname

	def createJob(self):
		conn = httplib.HTTPConnection(jenkinsURL)
		conn.request("POST", "/createItem?name=" + self.getJobName(), self.configXml(), {"Content-type": "text/xml", "Authorization" : jenkinsAuth})
		r2 = conn.getresponse()
		if r2.status != 200:
			print(str(r2.status) + " " + r2.reason + "\nWhile posting new job: " + self.getJobName() + "\n" + r2.read())
			sys.exit(r2.status)

	def updateJob(self):
		conn = httplib.HTTPConnection(jenkinsURL)
		conn.request("POST", "/job/" + self.getJobName() + "/config.xml", self.configXml(), {"Content-type": "text/xml", "Authorization" : jenkinsAuth})
		r2 = conn.getresponse()
		if r2.status != 200:
			print(str(r2.status) + " " + r2.reason + "\nWhile updating job: " + self.getJobName() + "\n" + r2.read())
			sys.exit(r2.status)

	def createOrUpdateJob(self):
		conn = httplib.HTTPConnection(jenkinsURL)
		conn.request("GET", "/job/" + self.getJobName() + "/config.xml", headers = {"Authorization" : jenkinsAuth})
		r1 = conn.getresponse()
		if r1.status == 404:
			self.createJob()
		elif r1.status == 200:
			self.updateJob()
		else:
			print(str(r1.status) + " " + r1.reason + "\nWhile checking for existence of job: " + self.getJobName() + "\n" + r1.read())
			sys.exit(r1.status)


#####
class SilverTestJob(JenkinsJobConfig):
	def __init__(self, testname):
		self.jobname = "silver-test-" + testname
		self.description = testname + " test suite for the Silver compiler"
		self.command = "cd " + os.path.join(silverSharedWorkspace, "test", testname) + "\n" + \
		               "./silver-compile --clean -G $WORKSPACE\n" + \
		               "java -jar test.jar\n" + \
		               "rm -rf $WORKSPACE/src $WORKSPACE/bin\n"

#####
class SilverTutorialJob(JenkinsJobConfig):
	def __init__(self, tutorialpath):
		self.jobname = "silver-tutorial-" + tutorialpath.replace("/","-")
		self.description = tutorialpath + " tutorial included with the Silver compiler"
		self.command = "cd " + os.path.join(silverDeployWorkspace, "silver-latest", "tutorials", tutorialpath) + "\n" + \
		               "./silver-compile --clean -G $WORKSPACE\n" + \
		               "rm -rf $WORKSPACE/src $WORKSPACE/bin\n"

#####
class SilverJob(JenkinsJobConfig):
	def __init__(self, testtriggers, othertriggers):
		self.jobname = "silver"
		self.description = "The Silver compiler"
		self.command = "./fetch-jars\n" + \
		               "./deep-rebuild\n" + \
		               "./deep-clean -delete all\n" + \
		               "./make-dist latest\n" + \
		               "cd " + silverDeployWorkspace + "\n" + \
		               "rm -rf *\n" + \
		               "tar zxvf " + silverSharedWorkspace + "/silver-latest.tar.gz\n"
		self.testtriggers = ', '.join(testtriggers)
		self.testtriggers_orig = testtriggers
		self.othertriggers = ', '.join(othertriggers)
		self.othertriggers_orig = othertriggers
		self.customWorkspace = silverSharedWorkspace  # global

	def configXml(self):
		assert self.description != "", "Must provide description"
		assert self.command != "", "Must provide command"
		assert self.customWorkspace != "", "Must provide customWorkspace"
		assert self.testtriggers != "", "Must provide testtriggers"
		assert self.othertriggers != "", "Must provide othertriggers"
		return string.Template("""<?xml version='1.0' encoding='UTF-8'?>
<project>
  <actions/>
  <description>${description}</description>
  <logRotator>
    <daysToKeep>-1</daysToKeep>
    <numToKeep>-1</numToKeep>
    <artifactDaysToKeep>14</artifactDaysToKeep>
    <artifactNumToKeep>-1</artifactNumToKeep>
  </logRotator>
  <keepDependencies>false</keepDependencies>
  <properties/>
  <scm class="hudson.plugins.mercurial.MercurialSCM">
    <installation>Module system mercurial</installation>
    <source>http://code.google.com/p/silver</source>
    <modules></modules>
    <clean>true</clean>
    <browser class="hudson.plugins.mercurial.browser.GoogleCode">
      <url>http://code.google.com/p/silver/source/</url>
    </browser>
  </scm>
  <canRoam>true</canRoam>
  <disabled>false</disabled>
  <blockBuildWhenDownstreamBuilding>true</blockBuildWhenDownstreamBuilding>
  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
  <triggers class="vector">
    <hudson.triggers.SCMTrigger>
      <spec>@hourly</spec>
    </hudson.triggers.SCMTrigger>
  </triggers>
  <concurrentBuild>false</concurrentBuild>
  <customWorkspace>${customWorkspace}</customWorkspace>
  <builders>
    <hudson.tasks.Shell>
      <command>${command}</command>
    </hudson.tasks.Shell>
  </builders>
  <publishers>
    <hudson.tasks.ArtifactArchiver>
      <artifacts>jars/*.jar</artifacts>
      <latestOnly>false</latestOnly>
    </hudson.tasks.ArtifactArchiver>
    <hudson.tasks.BuildTrigger>
      <childProjects>${testtriggers}</childProjects>
      <threshold>
        <name>SUCCESS</name>
        <ordinal>0</ordinal>
        <color>BLUE</color>
      </threshold>
    </hudson.tasks.BuildTrigger>
    <join.JoinTrigger>
      <joinProjects>${othertriggers}</joinProjects>
      <joinPublishers/>
      <evenIfDownstreamUnstable>false</evenIfDownstreamUnstable>
    </join.JoinTrigger>
    <hudson.tasks.Fingerprinter>
      <targets></targets>
      <recordBuildArtifacts>true</recordBuildArtifacts>
    </hudson.tasks.Fingerprinter>
  </publishers>
  <buildWrappers/>
</project>""").substitute(self.__dict__)

#####
class SubversionJob(JenkinsJobConfig):
	def __init__(self, jobname, description, paths, command):
		self.jobname = jobname
		self.description = description
		self.command = command
		self.paths = ''.join([self.pairToConfig(p) for p in paths])

	def pairToConfig(self, (r,l)):
		return string.Template("""
      <hudson.scm.SubversionSCM_-ModuleLocation>
        <remote>${r}</remote>
        <local>${l}</local>
      </hudson.scm.SubversionSCM_-ModuleLocation>""").substitute(r=r, l=l)

	def configXml(self):
		assert self.description != "", "Must provide description"
		assert self.command != "", "Must provide command"
		assert self.paths != "", "Must provide paths"
		return string.Template("""<?xml version='1.0' encoding='UTF-8'?>
<project>
  <actions/>
  <description>${description}</description>
  <keepDependencies>false</keepDependencies>
  <properties/>
  <scm class="hudson.scm.SubversionSCM">
    <locations>${paths}
    </locations>
    <excludedRegions></excludedRegions>
    <includedRegions></includedRegions>
    <excludedUsers></excludedUsers>
    <excludedRevprop></excludedRevprop>
    <excludedCommitMessages></excludedCommitMessages>
    <workspaceUpdater class="hudson.scm.subversion.UpdateWithCleanUpdater"/>
  </scm>
  <canRoam>true</canRoam>
  <disabled>false</disabled>
  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
  <blockBuildWhenUpstreamBuilding>true</blockBuildWhenUpstreamBuilding>
  <triggers class="vector">
    <hudson.triggers.SCMTrigger>
      <spec>@midnight</spec>
    </hudson.triggers.SCMTrigger>
  </triggers>
  <concurrentBuild>false</concurrentBuild>
  <builders>
    <hudson.tasks.Shell>
      <command>${command}</command>
    </hudson.tasks.Shell>
  </builders>
  <publishers/>
  <buildWrappers/>
</project>""").substitute(self.__dict__)

#####
class MeltsvnGrammarJob(SubversionJob):
	def __init__(self, jobname, pregrammar, subgrammar, includepath="grammars", invoke="", deps=[]):
		self.jobname = "meltsvn-" + jobname
		self.grammar = pregrammar + ":" + subgrammar
		self.description = "Build grammar " + self.grammar + " located in meltsvn/" + includepath
		svnlocal = includepath + "/" + pregrammar.replace(":", "/")
		self.paths = ''.join([self.grammarToConfig(includepath, g) for g in [pregrammar] + deps])
		self.command = silverInvoke(grammar=self.grammar, generated=".", include=includepath)
		if invoke == True:
			self.command += "\n" + \
			               "cd " + svnlocal + "/" + subgrammar.replace(":", "/") + "\n" + \
			               "java -jar $WORKSPACE/" + self.grammar.replace(":",".") + ".jar"
		elif invoke != "":
			self.command += "\n" + invoke
	
	def grammarToConfig(self, includepath, g):
		local = includepath + "/" + g.replace(":", "/")
		remote = "https://www-users.cs.umn.edu/meltsvn/" + local
		return self.pairToConfig((remote, local))

#####
class ProxyJob(JenkinsJobConfig):
	def __init__(self, jobname, description, triggers=[], jointriggers=[]):
		assert len(triggers + jointriggers) != 0, "must provide some triggers!"
		self.jobname = jobname
		self.description = description
		self.triggers_orig = triggers
		self.jointriggers_orig = jointriggers
		self.publishers = ""
		if len(triggers) > 0:
			self.publishers += "<hudson.tasks.BuildTrigger><childProjects>" + ', '.join(triggers) + "</childProjects><threshold><name>SUCCESS</name><ordinal>0</ordinal><color>BLUE</color></threshold></hudson.tasks.BuildTrigger>"
		if len(jointriggers) > 0:
			self.publishers += "<join.JoinTrigger><joinProjects>" + ', '.join(jointriggers) + "</joinProjects><joinPublishers/><evenIfDownstreamUnstable>false</evenIfDownstreamUnstable></join.JoinTrigger>"

	def configXml(self):
		assert self.description != "", "Must provide description"
		assert self.publishers != "", "Must provide publishers"
		return string.Template("""<?xml version='1.0' encoding='UTF-8'?>
<project>
  <actions/>
  <description>${description}</description>
  <keepDependencies>false</keepDependencies>
  <properties/>
  <scm class="hudson.scm.NullSCM"/>
  <canRoam>true</canRoam>
  <disabled>false</disabled>
  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
  <triggers class="vector"/>
  <concurrentBuild>false</concurrentBuild>
  <builders/>
  <publishers>
    ${publishers}
  </publishers>
  <buildWrappers/>
</project>""").substitute(self.__dict__)

#####
class SilverStableJob(JenkinsJobConfig):
	def __init__(self):
		self.jobname = "silver-stable-deploy"
		self.description = "Copies the latest tar file and jars to a deployment staging area, whenever all tests pass and grammars build."
		self.command = "cd " + silverStableWorkspace + "\n" + \
		               "cp " + silverSharedWorkspace + "/silver-latest.tar.gz .\n" + \
		               "cp " + silverSharedWorkspace + "/jars/*.jar .\n"


################################################################################

def find_all_sv_tests(svroot):
	return [os.path.relpath(os.path.dirname(x), os.path.join(svroot, "test")) for x in glob.glob(os.path.join(svroot, "test", "*", "silver-compile"))]

def find_all_sv_tutorials(svroot):
	return [os.path.relpath(root, os.path.join(svroot, "tutorials")) for root, dirs, files in os.walk(os.path.join(svroot, "tutorials")) if "silver-compile" in files]

# Manually set:
meltsvngrammars = [
MeltsvnGrammarJob("Oberon0-A1",  "edu:umn:cs:melt:Oberon0", "artifacts:A1"),
MeltsvnGrammarJob("Oberon0-A2a", "edu:umn:cs:melt:Oberon0", "artifacts:A2a"),
MeltsvnGrammarJob("Oberon0-A2b", "edu:umn:cs:melt:Oberon0", "artifacts:A2b"),
MeltsvnGrammarJob("Oberon0-A3",  "edu:umn:cs:melt:Oberon0", "artifacts:A3"),
MeltsvnGrammarJob("Oberon0-A4",  "edu:umn:cs:melt:Oberon0", "artifacts:A4"),
MeltsvnGrammarJob("Oberon0-A5",  "edu:umn:cs:melt:Oberon0", "artifacts:A5"),

MeltsvnGrammarJob("Matlab-host", "edu:umn:cs:melt:MATLAB", "artifacts:MATLAB"),

# miniHaskell has apparently atrophied beyond easy redemption. Seriously, it looks like something exploded all over it...
#MeltsvnGrammarJob("miniHaskell-host", "edu:umn:cs:melt:miniHaskell", "host:bin"),
#MeltsvnGrammarJob("miniHaskell-host-tests", "edu:umn:cs:melt:miniHaskell", "host:tests", invoke=True),

MeltsvnGrammarJob("ableP-host",              "edu:umn:cs:melt:ableP", "artifacts:promela", deps=["edu:umn:cs:melt:ableC"]), 
MeltsvnGrammarJob("ableP-host-tests",        "edu:umn:cs:melt:ableP", "artifacts:promela:tests", deps=["edu:umn:cs:melt:ableC"], invoke=True), 
MeltsvnGrammarJob("ableP-promelaCore",       "edu:umn:cs:melt:ableP", "artifacts:promelaCore", deps=["edu:umn:cs:melt:ableC"]),
MeltsvnGrammarJob("ableP-promelaCore-tests", "edu:umn:cs:melt:ableP", "artifacts:promelaCore:tests", deps=["edu:umn:cs:melt:ableC"], invoke=True), 
MeltsvnGrammarJob("ableP-aviation",          "edu:umn:cs:melt:ableP", "artifacts:aviation", deps=["edu:umn:cs:melt:ableC"]), 
MeltsvnGrammarJob("ableP-aviation-tests",    "edu:umn:cs:melt:ableP", "artifacts:aviation:tests", deps=["edu:umn:cs:melt:ableC"], invoke=True), 

MeltsvnGrammarJob("ableC-host", "edu:umn:cs:melt:ableC", "host:bin"), 
MeltsvnGrammarJob("ableC-host-tests", "edu:umn:cs:melt:ableC", "host:tests", invoke=True), 

MeltsvnGrammarJob("ableJ-alone",      "edu:umn:cs:melt:ableJ14", "composed:java_alone:bin"), 
MeltsvnGrammarJob("ableJ-autoboxing", "edu:umn:cs:melt:ableJ14", "composed:java_autoboxing:bin"), 
MeltsvnGrammarJob("ableJ-sql",        "edu:umn:cs:melt:ableJ14", "composed:java_sql:bin"), 
MeltsvnGrammarJob("ableJ-complex",    "edu:umn:cs:melt:ableJ14", "composed:java_complex:bin"), 
MeltsvnGrammarJob("ableJ-foreach",    "edu:umn:cs:melt:ableJ14", "composed:java_foreach:bin"), 
MeltsvnGrammarJob("ableJ-tables",     "edu:umn:cs:melt:ableJ14", "composed:java_tables:bin"), 
MeltsvnGrammarJob("ableJ-pizza",      "edu:umn:cs:melt:ableJ14", "composed:java_pizza:bin"), 
MeltsvnGrammarJob("ableJ-rlp",        "edu:umn:cs:melt:ableJ14", "composed:java_rlp:bin"), 

MeltsvnGrammarJob("simple-host",   "edu:umn:cs:melt:simple", "host:artifact"),
MeltsvnGrammarJob("simple-matrix", "edu:umn:cs:melt:simple", "compositions:simple_matrix"),
MeltsvnGrammarJob("simple-all",    "edu:umn:cs:melt:simple", "compositions:simple_all"),

MeltsvnGrammarJob("ring-host", "react", "bin", includepath="users/srinivasr/trunk/grammars"),
MeltsvnGrammarJob("ring-host-tests", "react", "tests", includepath="users/srinivasr/trunk/grammars", invoke=True),
]

###########

def main():
	localSvRoot = "../.." # expected to be in support/jenkins
	assert os.path.isdir(os.path.join(localSvRoot, "test")), "test directory not found... configure!"
	assert os.path.isdir(os.path.join(localSvRoot, "tutorials")), "tutorials directory not found... configure!"
	assert jenkinsAPIToken != "", "Forgot API token"
	
	svtests = find_all_sv_tests(localSvRoot)
	svtuts = find_all_sv_tutorials(localSvRoot)
	
	# runs immediately after silver
	svtestjobs = [SilverTestJob(x) for x in svtests]
	# runs only after all previous succeed
	postsvjobs = [SilverTutorialJob(x) for x in svtuts] + meltsvngrammars
	
	for job in postsvjobs + svtestjobs:
		job.createOrUpdateJob()
	
	# Deploy stuff if it all succeeds, oh happy day
	svdeployjob = SilverStableJob()
	svdeployjob.createOrUpdateJob()
	
	# After silver tests pass, build all grammars.
	svtestproxy = ProxyJob("silver-tests-okay", "Proxy to trigger more builds once Silver's tests pass", \
	   triggers=[x.getJobName() for x in postsvjobs], \
	   jointriggers=[svdeployjob.getJobName()])
	svtestproxy.createOrUpdateJob()
	
	# After silver builds, and the tests pass, trigger the proxy.
	SilverJob([x.getJobName() for x in svtestjobs], [svtestproxy.getJobName()]).createOrUpdateJob()
	

if __name__ == "__main__":
	main()

