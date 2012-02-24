#!/usr/bin/python

import os
import sys
import glob
import string
import httplib
import base64

# Configure this!
silverSharedWorkspace = "/export/scratch/melt-jenkins/custom-silver"
jenkinsURL = "localhost:8080"
jenkinsUsername = "tedinski"
jenkinsAPIToken = "" # replace with API key!!

##
jenkinsAuth = "Basic " + base64.encodestring('%s:%s' % (jenkinsUsername, jenkinsAPIToken))[:-1]

#############

def silverInvoke(grammar, include="", generated="", output=""):
	cmdStr = "java -Xmx2000M -Xss4M -jar " + silverSharedWorkspace + "/jars/RunSilver.jar --clean"
	if include != "":
		cmdStr = cmdStr + " -I " + include
	if generated != "":
		cmdStr = cmdStr + " -G " + generated
	if output != "":
		cmdStr = cmdStr + " -o " + output
	return cmdStr + " " + grammar + "\nant"


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


class SilverTestJob(JenkinsJobConfig):
	def __init__(self, testname):
		self.jobname = "silver-test-" + testname
		self.description = testname + " test suite for the Silver compiler"
		self.command = "cd " + os.path.join(silverSharedWorkspace, "test", testname) + "\n" + \
		               "./silver-compile --clean -G $WORKSPACE\n" + \
		               "java -jar test.jar\n" + \
		               "rm -rf $WORKSPACE/src $WORKSPACE/bin\n"

class SilverTutorialJob(JenkinsJobConfig):
	def __init__(self, tutorialpath):
		self.jobname = "silver-tutorial-" + tutorialpath.replace("/","-")
		self.description = tutorialpath + " tutorial included with the Silver compiler"
		self.command = "cd " + os.path.join(silverSharedWorkspace, "tutorials", tutorialpath) + "\n" + \
		               "./silver-compile --clean -G $WORKSPACE\n" + \
		               "rm -rf $WORKSPACE/src $WORKSPACE/bin\n"

class SilverJob(JenkinsJobConfig):
	def __init__(self, triggers):
		self.jobname = "silver"
		self.description = "The Silver compiler"
		self.command = "./fetch-jars\n./deep-rebuild\n./deep-clean -delete all\n"
		self.triggers = ', '.join(triggers)
		self.triggers_orig = triggers
		self.customWorkspace = silverSharedWorkspace  # global

	def configXml(self):
		assert self.description != "", "Must provide description"
		assert self.command != "", "Must provide command"
		assert self.customWorkspace != "", "Must provide customWorkspace"
		assert self.triggers != "", "Must provide triggers"
		return string.Template("""<?xml version='1.0' encoding='UTF-8'?>
<project>
  <actions/>
  <description>${description}</description>
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
      <childProjects>${triggers}</childProjects>
      <threshold>
        <name>SUCCESS</name>
        <ordinal>0</ordinal>
        <color>BLUE</color>
      </threshold>
    </hudson.tasks.BuildTrigger>
    <hudson.tasks.Fingerprinter>
      <targets></targets>
      <recordBuildArtifacts>true</recordBuildArtifacts>
    </hudson.tasks.Fingerprinter>
  </publishers>
  <buildWrappers/>
</project>""").substitute(self.__dict__)

class SubversionJob(JenkinsJobConfig):
	def __init__(self, jobname, description, remotepath, localpath, command):
		self.jobname = jobname
		self.description = description
		self.command = command
		self.svnremote = remotepath
		self.svnlocal = localpath

	def configXml(self):
		assert self.description != "", "Must provide description"
		assert self.command != "", "Must provide command"
		assert self.svnremote != "", "Must provide svnremote"
		assert self.svnlocal != "", "Must provide svnlocal"
		return string.Template("""<?xml version='1.0' encoding='UTF-8'?>
<project>
  <actions/>
  <description>${description}</description>
  <keepDependencies>false</keepDependencies>
  <properties/>
  <scm class="hudson.scm.SubversionSCM">
    <locations>
      <hudson.scm.SubversionSCM_-ModuleLocation>
        <remote>${svnremote}</remote>
        <local>${svnlocal}</local>
      </hudson.scm.SubversionSCM_-ModuleLocation>
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

class MeltsvnGrammarJob(SubversionJob):
	def __init__(self, jobname, pregrammar, subgrammar, includepath="grammars", invoke=""):
		self.jobname = "meltsvn-" + jobname
		self.grammar = pregrammar + ":" + subgrammar
		self.description = "Build grammar " + self.grammar + " located in meltsvn/" + includepath
		self.svnlocal = includepath + "/" + pregrammar.replace(":", "/")
		self.svnremote = "https://www-users.cs.umn.edu/meltsvn/" + self.svnlocal
		self.command = silverInvoke(grammar=self.grammar, generated=".", include=includepath)
		if invoke == True:
			self.command = self.command + "\n" + \
			               "cd " + self.svnlocal + "/" + subgrammar.replace(":", "/") + "\n" + \
			               "java -jar $WORKSPACE/" + self.grammar.replace(":",".") + ".jar"
		elif invoke != "":
			self.command = self.command + "\n" + invoke

################################################################################

def find_all_sv_tests(svroot):
	return [os.path.relpath(os.path.dirname(x), os.path.join(svroot, "test")) for x in glob.glob(os.path.join(svroot, "test", "*", "silver-compile"))]

def find_all_sv_tutorials(svroot):
	return [os.path.relpath(root, os.path.join(svroot, "tutorials")) for root, dirs, files in os.walk(os.path.join(svroot, "tutorials")) if "silver-compile" in files]

# Manually set:
meltsvngrammars = [
MeltsvnGrammarJob("Oberon0-A1", "edu:umn:cs:melt:Oberon0", "artifacts:A1"),
MeltsvnGrammarJob("Oberon0-A2a", "edu:umn:cs:melt:Oberon0", "artifacts:A2a"),
MeltsvnGrammarJob("Oberon0-A2b", "edu:umn:cs:melt:Oberon0", "artifacts:A2b"),
MeltsvnGrammarJob("Oberon0-A3", "edu:umn:cs:melt:Oberon0", "artifacts:A3"),
MeltsvnGrammarJob("Oberon0-A4", "edu:umn:cs:melt:Oberon0", "artifacts:A4"),
MeltsvnGrammarJob("Oberon0-A5", "edu:umn:cs:melt:Oberon0", "artifacts:A5"),

MeltsvnGrammarJob("Matlab-host", "edu:umn:cs:melt:MATLAB", "artifacts:MATLAB"),

# miniHaskell has apparently atrophied beyond easy redemption. Seriously, it looks like something exploded all over it...
#MeltsvnGrammarJob("miniHaskell-host", "edu:umn:cs:melt:miniHaskell", "host:bin"),
#MeltsvnGrammarJob("miniHaskell-host-tests", "edu:umn:cs:melt:miniHaskell", "host:tests", invoke=True),

# TODO: Will be rebuilt way too often. Find better solution for getting ableC with it?
MeltsvnGrammarJob("ableP-host", "edu:umn:cs:melt", "ableP:artifacts:promela"), 
MeltsvnGrammarJob("ableP-host-tests", "edu:umn:cs:melt", "ableP:artifacts:promela:tests", invoke=True), 
MeltsvnGrammarJob("ableP-promelaCore", "edu:umn:cs:melt", "ableP:artifacts:promelaCore"), 
MeltsvnGrammarJob("ableP-promelaCore-tests", "edu:umn:cs:melt", "ableP:artifacts:promelaCore:tests", invoke=True), 
MeltsvnGrammarJob("ableP-aviation", "edu:umn:cs:melt", "ableP:artifacts:aviation"), 
MeltsvnGrammarJob("ableP-aviation-tests", "edu:umn:cs:melt", "ableP:artifacts:aviation:tests", invoke=True), 

MeltsvnGrammarJob("ableC-host", "edu:umn:cs:melt:ableC", "host:bin"), 
MeltsvnGrammarJob("ableC-host-tests", "edu:umn:cs:melt:ableC", "host:tests", invoke=True), 

# simple is not set up to build properly yet
#MeltsvnGrammarJob("simple-host", "edu:umn:cs:melt:simple", "host"), 

MeltsvnGrammarJob("ring-host", "react", "bin", includepath="users/srinivasr/trunk/grammars"),
MeltsvnGrammarJob("ring-host-tests", "react", "tests", includepath="users/srinivasr/trunk/grammars", invoke=True),
]

###########

def main():
	localSvRoot = "../.." # expected to be in support/jenkins
	assert os.path.isdir(os.path.join(localSvRoot, "test")), "test directory not found... configure!"
	assert os.path.isdir(os.path.join(localSvRoot, "tutorials")), "tutorials directory not found... configure!"
	
	svtests = find_all_sv_tests(localSvRoot)
	svtuts = find_all_sv_tutorials(localSvRoot)
	
	postsvjobs = [SilverTestJob(x) for x in svtests] + [SilverTutorialJob(x) for x in svtuts]  + meltsvngrammars
	
	
	
	for job in postsvjobs:
		job.createOrUpdateJob()
	
	SilverJob([x.getJobName() for x in postsvjobs]).createOrUpdateJob()
	

if __name__ == "__main__":
	main()

