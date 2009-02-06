======
Tortue
======

-------------------------------------------------------------------------------------
Projet PPO 2009 (L3 MIAGE Grenoble) de Jean-Christophe Saad-Dupuy et Geoffroy Carrier
-------------------------------------------------------------------------------------

Réponses
--------

Dans la solution 2, certains mouvements sont purements bloques alors que dans la solution 3, ils ne seront que partiels.
Par exemple, si on demande a la tortue d'avancer de 10 ecrans, puis de tourner de 180 degres, puis d'avancer de 10 ecrans, dans le premier cas :

- Dans le premier cas la tortue ne change pas de position et change seulement d'orientation ;
- Dans le deuxieme cas la tortue se retrouve au bord de l'ecran (elle a recule a fond).

Compilation
-----------

Utiliser ``mvn package``.
