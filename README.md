# Citronnix

## Contexte du Projet
Le projet Citronix consiste à développer une application de gestion pour une ferme de citrons, permettant aux fermiers de suivre la production, la récolte, et la vente de leurs produits. L'application doit faciliter la gestion des fermes, champs, arbres, récoltes et ventes, tout en optimisant le suivi de la productivité des arbres en fonction de leur âge.

Le projet suit l'architecture Domain-Driven Design (DDD), avec des domaines spécifiques pour gérer les entités de la ferme,  les récoltes, les arbres et les ventes.

## Structure du Projet (Architecture DDD)

Le projet est divisé en plusieurs domaines principaux, chacun ayant ses propres **Entities**, **ValueObject**, **Repositories**, **Services**, et **DTOs** pour assurer la séparation des préoccupations.

### 1. **Domaine Farm**
- **Responsabilités** : Gestion des fermes, y compris les informations comme le nom, la localisation et la superficie.
- **Entités** : `Farm`, `Field`.
- **Services** : Gestion des fermes et des champs associés.
- **Repositories** : Interface pour l'accès aux données des fermes et des champs.


### 3. **Domaine Tree**
- **Responsabilités** : Gestion des arbres, suivi de leur âge, leur plantation et la productivité.
- **Entités** : `Tree`.
- **Services** : Logique de calcul de la productivité en fonction de l'âge des arbres.
- **Repositories** : Interface pour l'accès aux données des arbres.

### 4. **Domaine Harvest**
- **Responsabilités** : Gestion des récoltes par saison et par arbre.
- **Entités** : `Harvest`,`HarvestDetails`.
- **Services** : Logique de gestion des récoltes, enregistrement des dates et des quantités récoltées.
- **Repositories** : Interface pour l'accès aux données des récoltes.

### 5. **Domaine Sale**
- **Responsabilités** : Gestion des ventes de citrons.
- **Entités** : `Sale`.
- **Services** : Calcul du revenu généré par la vente de la récolte, gestion des informations de vente.
- **Repositories** : Interface pour l'accès aux données des ventes.

## Fonctionnalités Principales
### 1. Gestion des Fermes :
- Créer, modifier et consulter les informations d'une ferme (nom, localisation, superficie, date de création).
- Recherche multicritère (Criteria Builder).

### 2. Gestion des Champs :
- Associer des champs à une ferme avec une superficie définie.
- Assurer la cohérence des superficies : la somme des superficies des champs d'une ferme doit être strictement inférieure à celle de la ferme.

### 3. Gestion des Arbres :
- Suivre les arbres avec leur date de plantation, âge, et champ d'appartenance.
- Calculer l'âge des arbres.
- Déterminer la productivité annuelle en fonction de l'âge de l'arbre :
    - Arbre jeune (< 3 ans) : 2,5 kg / saison.
    - Arbre mature (3-10 ans) : 12 kg / saison.
    - Arbre vieux (> 10 ans) : 20 kg / saison.

### 4. Gestion des Récoltes :
- Suivre les récoltes par saison (hiver, printemps, été, automne).
- Une seule récolte par saison (tous les 3 mois).
- Enregistrer la date de récolte et la quantité totale récoltée.

### 5. Détail des Récoltes :
- Suivre la quantité récoltée par arbre pour une récolte donnée.
- Associer chaque détail de récolte à un arbre spécifique.

### 6. Gestion des Ventes :
- Enregistrer les ventes avec la date, le prix unitaire, le client, et la récolte associée.
- Calcul du revenu : Revenu = quantité * prixUnitaire.

## Contraintes
- **Superficie minimale des champs** : La superficie d'un champ doit être au minimum de 0.1 hectare (1 000 m²).
- **Superficie maximale des champs** : Aucun champ ne peut dépasser 50% de la superficie totale de la ferme.
- **Nombre maximal de champs** : Une ferme ne peut contenir plus de 10 champs.
- **Espacement entre les arbres** : Chaque champ doit contenir un nombre d'arbres tel que la densité maximale est de 100 arbres par hectare (10 arbres par 1 000 m²).
- **Durée de vie maximale des arbres** : Un arbre ne peut être productif au-delà de 20 ans ; après cet âge, il est considéré comme non productif.
- **Période de plantation** : Les arbres ne peuvent être plantés qu'entre les mois de mars et mai, période idéale pour le climat.
- **Limite saisonnière** : Chaque champ ne peut être associé qu'à une seule récolte par saison.
- **Arbres non récoltés deux fois** : Un arbre ne peut pas être inclus dans plus d’une récolte pour une même saison.

## Exigences Techniques
- **Spring Boot** : Utilisé pour créer l'API REST.
- **Architecture en couches** (Controller, Service, Repository, Entity).
- **Validation des données** avec annotations Spring.
- **Utilisation des interfaces et implémentation**.
- **Gestion centralisée des exceptions**.
- **Tests unitaires** avec JUnit et Mockito.
- **Lombok et Builder Pattern** pour simplifier la gestion des entités.
- **MapStruct** pour la conversion entre les entités, DTO et View Models.

## Instructions de Déploiement
1. Clonez le repository.
2. Importez le projet dans votre IDE (ex : IntelliJ IDEA).
3. Assurez-vous d'avoir une instance de base de données PostgreSQL.
4. Configurez les paramètres de connexion à la base de données dans le fichier `application.properties`.
5. Lancez l'application avec la commande `mvn spring-boot:run`.

## Presentation
[Présentation](https://maleknoura098.atlassian.net/jira/software/projects/CIT/boards/8/backlog?epics=visible)
## UML
[UML Diagram](https://app.diagrams.net/?title=Copie%20de%20de%20class%20diagram%20citronix.drawio&client=1#G1TGUzooDTcaFcu1x3m3WMey6bLHZCorcZ#%7B%22pageId%22%3A%22CsAEcvr7ysyxghYFOYxA%22%7D)

## Planification
[Planification](https://maleknoura098.atlassian.net/jira/software/projects/CIT/boards/8/backlog?epics=visible)



## Auteurs
- **Noura Malek** - Développeuse principale du projet.
