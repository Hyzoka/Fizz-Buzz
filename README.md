# Fizz-Buzz


Ce projet permet de récupérer les valeurs demander pour afficher une list en fonctions des multiples des valeurs demander précédemment.

Je suis parti sur une Activity et plusieurs fragments pour afficher le “formulaire” qui va demander sous forme de flow les entiers, strings et la limite, le tout avec des viewModels qui vont récupérer les valeurs et les renvoyer là où elles sont nécessaires.
Ce choix permet grace au flow de le rendre évolutif (en ajouter/supprimer) et d’apporter pour le user une interface plus agréable qu’un simple écran avec plusieurs editText.
J’aurais pu ajouter quelque élément décoratif comme une progress bar en haut de l’écran.

Afin de respecter le time de 3-4h je ne me suis pas attardé sur l’ui, ce que j’aurais pu faire c’est de crée une nouvelle activity pour le result (l’affichage de la list) pour le coter scalable, et update l’ui avec des custom background pour l’editText, et apporter une font + une charte de couleurs 
