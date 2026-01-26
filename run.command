#!/bin/bash

cd "$(dirname "$0")"

mkdir -p class
javac -d class $(find src -name "*.java")
java -cp class src.core.Main


read -p "Appuyez sur Entrée pour fermer..."