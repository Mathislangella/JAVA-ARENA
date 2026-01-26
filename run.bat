@echo off
REM Crée le dossier class s'il n'existe pas
if not exist class mkdir class

REM Compile tous les fichiers .java
for /R src %%f in (*.java) do javac -d class "%%f"

REM Lancer le jeu
java -cp class src.core.Main

pause
