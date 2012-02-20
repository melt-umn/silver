#!/usr/bin/python

import os
import sys
import glob
import string
import httplib

# Configure this!
silverSharedWorkspace = "/home/tedinski/.jenkins/MYCUSTOM/silver"
jenkinsURL = "localhost:8080"



#############

def silverInvoke(grammar, include="", generated="", output=""):
	cmdStr = "java -Xmx2000M -Xss4M -jar " + silverSharedWorkspace + "/jars/RunSilver.jar --clean"
	if include != "":
		cmdStr = cmdStr + " -I " + include
	if generated != "":
		cmdStr = cmdStr + " -G " + generated
	if output != "":
		cmdStr = cmdStr + " -o " + output
	return cmdStr + " " + grammar


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
		conn.request("POST", "/createItem?name=" + self.getJobName(), self.configXml(), {"Content-type": "text/xml"})
		r2 = conn.getresponse()
		if r2.status != 200:
			print(str(r2.status) + " " + r2.reason + "\nWhile posting new job: " + self.getJobName() + "\n" + r2.read())
			sys.exit(r2.status)

	def updateJob(self):
		conn = httplib.HTTPConnection(jenkinsURL)
		conn.request("POST", "/job/" + self.getJobName() + "/config.xml", self.configXml(), {"Content-type": "text/xml"})
		r2 = conn.getresponse()
		if r2.status != 200:
			print(str(r2.status) + " " + r2.reason + "\nWhile updating job: " + self.getJobName() + "\n" + r2.read())
			sys.exit(r2.status)

	def createOrUpdateJob(self):
		conn = httplib.HTTPConnection(jenkinsURL)
		conn.request("GET", "/job/" + self.getJobName() + "/config.xml")
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
		               "java -jar test.jar\n"

class SilverTutorialJob(JenkinsJobConfig):
	def __init__(self, tutorialpath):
		self.jobname = "silver-tutorial-" + tutorialpath.replace("/","-")
		self.description = tutorialpath + " tutorial included with the Silver compiler"
		self.command = "cd " + os.path.join(silverSharedWorkspace, "tutorials", tutorialpath) + "\n" + \
		               "./silver-compile --clean -G $WORKSPACE\n"

class SilverJob(JenkinsJobConfig):
	def __init__(self, triggers):
		self.jobname = "silver"
		self.description = "The Silver compiler"
		self.command = "./fetch-jars\n./deep-rebuild\n./deep-clean -delete all\n"
		self.triggers = ', '.join(triggers)
		self.triggers_orig = triggers
		self.customWorkspace = silverSharedWorkspace  # global

	def configXml(self):
		return string.Template("""<?xml version='1.0' encoding='UTF-8'?>
<project>
  <actions/>
  <description>${description}</description>
  <keepDependencies>false</keepDependencies>
  <properties/>
  <scm class="hudson.plugins.mercurial.MercurialSCM">
    <source>http://code.google.com/p/silver</source>
    <modules></modules>
    <clean>true</clean>
    <browser class="hudson.plugins.mercurial.browser.GoogleCode">
      <url>http://code.google.com/p/silver/source/</url>
    </browser>
  </scm>
  <canRoam>true</canRoam>
  <disabled>false</disabled>
  <blockBuildWhenDownstreamBuilding>false</blockBuildWhenDownstreamBuilding>
  <blockBuildWhenUpstreamBuilding>true</blockBuildWhenUpstreamBuilding>
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

class SubversionGrammarJob(JenkinsJobConfig):
	def __init__(self, jobname, description, remotepath, localpath, grammar):
		self.jobname = jobname
		self.description = description
		self.command = silverInvoke(grammar=grammar, generated=".")
		self.svnremote = remotepath
		self.svnlocal = localpath
		self.grammar = grammar
		

	def configXml(self):
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
  <blockBuildWhenUpstreamBuilding>false</blockBuildWhenUpstreamBuilding>
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

################################################################################

def find_all_sv_tests(svroot):
	return [os.path.relpath(os.path.dirname(x), os.path.join(svroot, "test")) for x in glob.glob(os.path.join(svroot, "test", "*", "silver-compile"))]

def find_all_sv_tutorials(svroot):
	return [os.path.relpath(root, os.path.join(svroot, "tutorials")) for root, dirs, files in os.walk(os.path.join(svroot, "tutorials")) if "silver-compile" in files]

# Manually set:
meltsvngrammars = [
SubversionGrammarJob("meltsvn-Oberon0-A1", "Artifact 1 of Oberon0", "https://www-users.cs.umn.edu/meltsvn/grammars/edu/umn/cs/melt/Oberon0", "edu/umn/cs/melt/Oberon0", "edu:umn:cs:melt:Oberon0:artifacts:A1"),
SubversionGrammarJob("meltsvn-Oberon0-A2a", "Artifact 2a of Oberon0", "https://www-users.cs.umn.edu/meltsvn/grammars/edu/umn/cs/melt/Oberon0", "edu/umn/cs/melt/Oberon0", "edu:umn:cs:melt:Oberon0:artifacts:A2a"),
SubversionGrammarJob("meltsvn-Oberon0-A2b", "Artifact 2b of Oberon0", "https://www-users.cs.umn.edu/meltsvn/grammars/edu/umn/cs/melt/Oberon0", "edu/umn/cs/melt/Oberon0", "edu:umn:cs:melt:Oberon0:artifacts:A2b"),
SubversionGrammarJob("meltsvn-Oberon0-A3", "Artifact 3 of Oberon0", "https://www-users.cs.umn.edu/meltsvn/grammars/edu/umn/cs/melt/Oberon0", "edu/umn/cs/melt/Oberon0", "edu:umn:cs:melt:Oberon0:artifacts:A3"),
SubversionGrammarJob("meltsvn-Oberon0-A4", "Artifact 4 of Oberon0", "https://www-users.cs.umn.edu/meltsvn/grammars/edu/umn/cs/melt/Oberon0", "edu/umn/cs/melt/Oberon0", "edu:umn:cs:melt:Oberon0:artifacts:A4"),
]

###########

def main():
	assert os.path.isdir(os.path.join(silverSharedWorkspace, "test")), "test directory not found... configure!"
	assert os.path.isdir(os.path.join(silverSharedWorkspace, "tutorials")), "tutorials directory not found... configure!"
	
	svtests = find_all_sv_tests(silverSharedWorkspace)
	svtuts = find_all_sv_tutorials(silverSharedWorkspace)
	
	postsvjobs = [SilverTestJob(x) for x in svtests] + [SilverTutorialJob(x) for x in svtuts] + meltsvngrammars
	
	
	
	for job in postsvjobs:
		job.createOrUpdateJob()
	
	SilverJob([x.getJobName() for x in postsvjobs]).createOrUpdateJob()
	

if __name__ == "__main__":
	main()

