import os
import re
import subprocess

branch = subprocess.Popen("git branch", stdout=subprocess.PIPE, shell=True)
for line in branch.stdout:
	if line.startswith('*'):
		branch_name = line.split()[1]
		print "<branch>: ", branch_name
		break

origin = subprocess.Popen("git remote", stdout=subprocess.PIPE, shell=True)
origin_name = origin.stdout.read().strip()
print "<origin>: ", origin_name

command = "git push " + origin_name + " HEAD:refs/for/" + branch_name
print command
result = subprocess.Popen(command, stdout=subprocess.PIPE, shell=True)
for line in result.stdout:
	print line