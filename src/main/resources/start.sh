#!/bin/bash
set -e

export APP_BIN="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
export APP_HOME="$(dirname $APP_BIN)"; [ -d "$APP_HOME" ] || { echo "error"; exit 1;}
export APP_LOG=$APP_HOME/logs
export APP_RESOURCES=$APP_HOME/conf
export APP_LIB=$APP_HOME/lib
export APP_APPS=$APP_HOME/apps

export APP_MAIN_CLASS=`cat $APP_BIN/server.env | grep APP_MAIN_CLASS::: | awk -F ':::' {'print $2'}`
export APP_NAME=$(basename $APP_HOME)
export CLASSPATH=$APP_RESOURCES:$(echo $APP_APPS/*.jar | tr ' ' ':'):$(echo $APP_LIB/*.jar | tr ' ' ':')
export APP_START_JVM_OPTION=`cat $APP_BIN/server.env | grep APP_START_JVM_OPTION::: | awk -F ':::' {'print $2'}`
export JVM_OPTION=`eval echo $APP_START_JVM_OPTION`

cd $APP_BIN
java $JVM_OPTION -cp $CLASSPATH $APP_MAIN_CLASS >> $APP_LOG/${APP_NAME}.out 2>&1 &
echo "start success"