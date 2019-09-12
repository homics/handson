#!/usr/bin/env bash

set -e

help="""
Build JARs for HOMICS project \n\n
Usage: $0 [OPTIONS] [all | gateway | user-activity | monolith | stats | stock ]\n

Options :\n
	\t[ -h for help] \n

"""

usage() { echo -e "$help" 1>&2; exit 1; }

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
  echo "Error: You must specify 'all' or the name of the service that you want to build its docker's image."
  exit 1
fi

buildImages() {
  local module="${1}"
  echo "----"
  echo "  - building ${module} Docker Image"
  docker build -t "handson/${module}:0.0.1-SNAPSHOT" -f "${module}/Dockerfile" .
}

exec_all() {
  local func="${1}"
  shift
  for module in "${modules[@]}"; do
    eval "${func}" "${module}"
  done
}

if [[ $1 == "all" ]]; then
  modules=("gateway" "monolith" "stats" "stock" "user-activity")
  echo "----"
  echo "Building each project docker image..."
else
  modules=("$1")
  echo "----"
  echo "Building only $1 docker image: "
fi

exec_all "buildImages"

echo "----"
echo "All docker images have been built."
echo "Done"
