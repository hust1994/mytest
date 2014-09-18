import os
import re
import subprocess

config_path = os.getcwd() + '\.git\config'
remote_pattern = re.compile(r"^\[remote.*")
branch_pattern = re.compile(r"^\[branch.*")

config = open(config_path)
while True:
	line = config.readline()
	if not line: break
	if remote_pattern.match(line):
		origin = line.split('"')[1]
		print 'remote: ', origin
	elif branch_pattern.match(line):
		branch_name = line.split('"')[1]
		print 'branch_name: ', branch_name
config.close()

command = "git push " + origin + " HEAD:refs/for/" + branch_name
print command
result = subprocess.Popen(command, stdout=subprocess.PIPE, shell=True)
for line in result.stdout:
	print line





