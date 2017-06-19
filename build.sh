#!/bin/bash

set -e

echo
echo "---------------- Build node modules ----------------"
time npm i
echo
echo "---------------- Build JS with npm ----------------"
time npm run build
echo
echo "---------------- Build Java with maven ----------------"
mvn clean install