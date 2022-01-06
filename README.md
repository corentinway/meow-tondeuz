
[![codecov](https://codecov.io/gh/corentinway/meow-tondeuz/branch/dev/graph/badge.svg?token=TWQMMK9KYT)](https://codecov.io/gh/corentinway/meow-tondeuz)

# Contexte

## Exercice de développement - La tondeuse

La société MowItNow a décidé de développer une tondeuse à gazon automatique, destinée aux
surfaces rectangulaires.

La tondeuse peut être programmée pour parcourir l'intégralité de la surface.
La position de la tondeuse est représentée par une combinaison de coordonnées (x,y) et d'une
lettre indiquant l'orientation selon la notation cardinale anglaise (N,E,W,S). La pelouse est
divisée en grille pour simplifier la navigation.

Par exemple, la position de la tondeuse peut être « 0, 0, N », ce qui signifie qu'elle se situe
dans le coin inférieur gauche de la pelouse, et orientée vers le Nord.

Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres. Les lettres possibles
sont « D », « G » et « A ». « D » et « G » font pivoter la tondeuse de 90° à droite ou à gauche
respectivement, sans la déplacer. « A » signifie que l'on avance la tondeuse d'une case dans la
direction à laquelle elle fait face, et sans modifier son orientation.

Si la position après mouvement est en dehors de la pelouse, la tondeuse ne bouge pas,
conserve son orientation et traite la commande suivante.

On présuppose que la case directement au Nord de la position (x, y) a pour coordonnées (x,
y+1).

Pour programmer la tondeuse, on lui fournit un fichier d'entrée construit comme suit :
* La première ligne correspond aux coordonnées du coin supérieur droit de la pelouse, celles
du coin inférieur gauche sont supposées être (0,0)
* La suite du fichier permet de piloter toutes les tondeuses qui ont été déployées. Chaque
tondeuse a deux lignes la concernant :
  * la première ligne donne la position initiale de la tondeuse, ainsi que son orientation. La
position et l'orientation sont fournies sous la forme de 2 chiffres et d’une lettre, séparés
par un espace
  * la seconde ligne est une série d'instructions ordonnant à la tondeuse d'explorer la
pelouse. Les instructions sont une suite de caractères sans espaces.

Chaque tondeuse se déplace de façon séquentielle, ce qui signifie que la seconde tondeuse ne
bouge que lorsque la première a exécuté intégralement sa série d'instructions.

Lorsqu'une tondeuse achève une série d'instruction, elle communique sa position et son
orientation.

## OBJECTIF
Concevoir et écrire un programme dans un des langages suivants : Java, Scala, Kotlin,
JavaScript, Typescript, Python, Go (Si vous souhaitez utiliser un autre langage, bien le valider
avant auprès de votre chargé de recrutement)
Ce programme devra implémenter la spécification ci-dessus et passer le test ci-après.

## TEST
Le fichier suivant est fourni en entrée :
```
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA
```
On attend le résultat suivant (position finale des tondeuses) :
```
1 3 N
5 1 E
```
NB: Les données en entrée sont injectées sous forme de fichier.

# Backlog

Voici les *user story* à réaliser dont l'ordre de priorité est :
* User Story 1
* User Story 2
* User Story 3
* User Story 4

## User Story 1 - créer une surface

```
Etant données un fichier dont la 1er ligne contient 2 entiers X et Y strictement positifs
Etant données que le séparateur entre ces 2 entiers est un espace
Quand cette première ligne du fichier d'instruction est lu
Alors une surface de largeur X et de hauteur Y est créée.
```

Add surface definition validation



## User Story 2 - positionner la tondeuse sur la surface

```
Etant données une surface de largeur X et de hauteur Y crée à partir de la 1er ligne du fichier d'instruction
Etant données la seconde ligne contenant 2 entiers x (inférieur ou égale à X) et y (inférieur ou égale à Y) strictement positif et une lettre (N, S, E, W au choix)
Quand la seconde ligne est lu
Alors la tondeuse est positionné dans la surface
Alors l'orientation de la tondeuse est la bonne (on pourra tester ici en faisant avancer la tondeuse d'un cran)
```

```
Etant données une surface de largeur X et de hauteur Y crée à partir de la 1er ligne du fichier d'instruction
Etant données la seconde ligne contenant 2 entiers x (supérieur à X) et y (inférieur ou égale à Y) strictement positif et une lettre (N, S, E, W au choix)
Quand la seconde ligne est lu
Alors une exception est lancé : `MowerCoordinateOutOfBoundException` 
Et le message d'erreur est "la coordonnée 'x' de la tondeuse est en dehors de la surface"
```

```
Etant données une surface de largeur X et de hauteur Y crée à partir de la 1er ligne du fichier d'instruction
Etant données la seconde ligne contenant 2 entiers x (inférieur ou égale à X) et y (supérieur à Y) strictement positif et une lettre (N, S, E, W au choix)
Quand la seconde ligne est lu
Alors une exception est lancé : `MowerCoordinateOutOfBoundException`
Et le message d'erreur est "la coordonnée 'y' de la tondeuse est en dehors de la surface"
```

## User Story 3 - faire avancer une seule tondeuse


```
Etant données une surface de largeur X et de hauteur Y crée à partir de la 1er ligne du fichier d'instruction
Etant données la seconde ligne contenant 2 entiers x (inférieur ou égale à X) et y (inférieur ou égale à Y) strictement positif et une lettre (N, S, E, W au choix)
Etant données la troisième ligne contenant une série de lettre 'G', 'D' ou 'A'
Quand la troisième ligne est lu
Alors la tondeuse se déplace selon les instructions données dans la surface
```


**Attention** : on ne va pas vérifier ici que si la tondeuse sort 
de la surface en avançant trop loin.
On pourrait lancer l'exception `MowerCoordinateOutOfBoundException` si
la tondeuse sort de la surface



## User Story 4 - faire avancer une plusieurs tondeuses

```
Etant données le fichier d'instruction en entrée :
  5 5
  1 2 N
  GAGAGAGAA
  3 3 E
  AADAADADDA
Quand le fichier est lu
Alors la tondeuse de la ligne 2 retourne sa poistion `1 3 N`
Et la tondeuse de la ligne 2 retourne sa poistion `5 1 E`

```
