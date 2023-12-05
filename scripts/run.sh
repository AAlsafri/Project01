#!/usr/bin/env bash

#
# Convenience script for invoking 'sam local' functions
#

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
LOG_DIR="$SCRIPT_DIR/../logs"
LOG_FILE="$LOG_DIR/SustainableShipmentService.log"

LAMBDA='SustainableShipmentService'

event=''
debug=''

function die() {
  echo "ERROR: $1"
  exit 1
}

function die_if_empty() {
  if [[ -z "$1" ]]; then
    die "$2"
  fi
}

function parse_args() {
  while [[ $# -gt 0 ]]; do
    case $1 in
      -e|--event)
        die_if_empty "$2" "expected an event JSON file"
        event="--event $2"
        ;;
      -d|--debug-port)
        die_if_empty "$2" "expected port number"
        debug="--debug-port $2"
        ;;
      *)
        die "unexpected arg $1"
        ;;
    esac

    # pop the flag and it's argument
    shift
    shift
  done

  die_if_empty "$event" "You must specify an event"
}

function ensure_log_dir() {
  mkdir -p $LOG_DIR
}

function sam_local_invoke() {
 command="sam local invoke $LAMBDA $debug $event 2>>$LOG_FILE | jq '{ statusCode: .statusCode, body: .body | fromjson }'"

 echo "Running Command... '$command'"
 echo

 eval $command
}

parse_args $@
ensure_log_dir
sam_local_invoke
