#  Java Arena – Guide de lancement (macOS)

Ce projet est un jeu Java développé en **programmation orientée objet (POO)**.
Ce guide explique **comment compiler et lancer le jeu sur macOS** 

---

##  Prérequis

Avant de lancer le jeu, assure‑toi d’avoir :

* macOS
* **Java JDK 17 ou supérieur** installé

  ```bash
  java --version
  ```

  Si Java n’est pas installé :
  👉 [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)

**Première utilisation (obligatoire)**
*  Ouvrez le Terminal
*  Allez dans le dossier du projet
*  Rendre le fichier exécutable :
*  chmod +x run.command
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
├── run.command         # Lanceur macOS (double‑clic)
└── README_MAC.md
```

 **Ne pas modifier l’arborescence**, sinon le jeu ne se lancera pas.

---

## ▶️ Lancer le jeu (méthode recommandée)

### 1️⃣ Double‑clique sur :

```
run.command
```

➡️ Une fenêtre Terminal s’ouvre automatiquement
➡️ Le projet est compilé
➡️ Le jeu se lance

---

##  Autorisation macOS (première fois uniquement)

Si macOS bloque l’exécution :

1. Double‑clique sur `run.command`
2. macOS affiche un message de sécurité
3. Ouvre **Réglages Système → Sécurité et confidentialité**
4. Clique sur **Autoriser quand même**
5. Relance `run.command`

 Cette étape n’est nécessaire **qu’une seule fois**

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
