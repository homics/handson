#!/usr/bin/env bash

set -e

help="""
Build JARs for HOMICS project \n\n
Usage: $0 [OPTIONS] [all | gateway | user-activity | monolith | stats | stock ]\n

Options :\n
	\t[ -h for help] \n

"""

usage() { echo -e $help 1>&2; exit 1; }

while getopts ":h" arg; do
  case $arg in
    h | *)
      usage
      exit 0
      ;;
  esac
done
shift $((OPTIND -1))

if [[ $# -lt 1 ]]; then
  echo "Error: You must specify 'all' or the name of the service to deploy."
  exit 1
fi

mvnInstallCommonMessaging() {
  echo "----"
  echo """Copying commons-messagig-1.0.0.jar to your local .m2"""
  mvn install:install-file -Dfile=docker/jars/commons-messaging-1.0.0.jar -DgroupId=commons-messaging -DartifactId=commons-messaging -Dversion=1.0.0 -Dpackaging=jar
}

buildJars() {
  local module="${1}"
  echo "----"
  echo "  - building ${module} jar"
  mvn clean install spring-boot:repackage -pl "${module}"
  cp "${module}"/target/*.jar docker/jars/
}

exec_all() {
  local func="${1}"
  shift
  for module in "${modules[@]}"; do
    eval "${func}" "${module}"
  done
}

mvnInstallCommonMessaging

if [[ $1 == "all" ]]; then
  modules=("gateway" "monolith" "stats" "stock" "user-activity")
  echo "----"
  echo "Building each projects..."
else
  modules=($1)
  echo "----"
  echo "Building only $1: "
fi

exec_all "buildJars"

echo "----"
echo "All jars have been copied in docker/jars"
echo "Done"
