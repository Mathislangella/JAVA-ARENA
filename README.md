#  Java Arena – Guide de lancement (macOS/Windows)

Ce projet est un jeu Java développé en **programmation orientée objet (POO)**.
Ce guide explique **comment compiler et lancer le jeu sur macOS et Windows** 

---

# Java Arena

## Pré-requis
- Java 17 ou plus installé sur votre machine
- Pour macOS : Terminal pour exécuter le script `.command`
- Pour Windows : Invite de commandes pour exécuter le script `.bat`

## Lancer le jeu

### Windows
1. Double-cliquez sur `run.bat`
2. Le jeu se compile et se lance automatiquement

### macOS
1. Ouvrez Terminal et placez-vous dans le dossier du projet, ou double-cliquez sur `run.command`
2. Si nécessaire, rendez le script exécutable :
   ```bash
   chmod +x run.command


  Si Java n’est pas installé :
  👉 [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)

---

##  Structure du projet

```
JAVA-ARENA/
│
├── src/                # Code source Java
│   └── core/Main.java # Point d'entrée du jeu
│
├── class/              # Fichiers compilés (.class) (créé automatiquement)
├── saves/              # Sauvegardes (.csv)
├── run.bat             # Lanceur windows (double‑clic)
├── run.command         # Lanceur macOS (double‑clic)
└── README.md
```

 **Ne pas modifier l’arborescence**, sinon le jeu ne se lancera pas.
---

##  Contenu du lanceur `run.bat`

```bash
@echo off

REM Crée le dossier class s'il n'existe pas
if not exist class mkdir class

REM Compile tous les fichiers Java
for /R src %%f in (*.java) do javac -d class "%%f"

REM Lance le jeu
java -cp class src.core.Main

pause

```

---
---

##  Contenu du lanceur `run.command`

```bash
#!/bin/bash
cd "$(dirname "$0")"

mkdir -p class
javac -d class $(find src -name "*.java")
java -cp class src.core.Main


read -p "Appuyez sur Entrée pour fermer..."

```

---

##  Sauvegardes

* Les sauvegardes sont stockées dans le dossier :

```
csv/
```

* Format : `.csv`
* Le jeu gère automatiquement les slots (`save1`, `save2`, etc.)

---
---

Projet réalisé dans le cadre de la formation **Ynov – B1 Informatique**.

---
