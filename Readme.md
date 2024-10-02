# Projet de Gestion de Tâches Collaboratif

## Contexte du Projet

Ce projet vise à développer une application web collaborative de gestion de projets et de tâches, basée sur une application console existante. L'objectif est de créer une solution plus complète, intégrant des fonctionnalités de gestion de projets, de tâches, et d'équipes, tout en utilisant Java et une base de données relationnelle.

## Objectifs

- Créer une application web Java avec Servlets, JSP, et JSTL.
- Implémenter les opérations CRUD (Créer, Lire, Mettre à jour, Supprimer) pour les projets, tâches, membres et équipes.
- Adapter la base de données existante pour l'intégration dans une application web.
- Développer une interface utilisateur intuitive et responsive avec Bootstrap.
- Appliquer des principes de gestion de projet agile tout au long du développement.
- Utiliser des concepts Java avancés et les bonnes pratiques de développement.

## Fonctionnalités Principales

### Page de Gestion des Projets
- Liste des projets avec pagination.
- Création, modification, et suppression de projets.
- Recherche de projets.
- Affichage des statistiques de base sur les projets (nombre de tâches, membres).

### Page de Gestion des Tâches
- Liste des tâches par projet avec pagination.
- Ajout, modification, et suppression de tâches.
- Assignation de tâches aux membres du projet.
- Suivi de l'avancement des tâches avec mise à jour du statut ("À faire", "En cours", "Terminée").

### Page de Gestion des Équipes
- Liste des membres de l'équipe avec pagination.
- Ajout, modification, et suppression d'équipes.
- Ajout, modification, et suppression de membres.
- Visualisation des tâches assignées à chaque membre.

## Structure des Classes Principales

### Projet
- `nom`
- `description`
- `dateDebut`
- `dateFin`
- `statut` (Enum : en préparation, en cours, en pause, terminé, annulé)

### Tâche
- `titre`
- `description`
- `priorite` (Enum : Basse, Moyenne, Haute)
- `statut` (Enum : À faire, En cours, Terminée)
- `dateCreation`
- `dateEcheance`

### Membre
- `nom`
- `prenom`
- `email`
- `role` (Enum : Chef de projet, Développeur, Designer)

### Équipe
- `nom`

## Exigences Techniques

- **Langage** : Java 8
- **IDE** : Eclipse
- **Technologies** :
  - Servlet, JSP, JSTL
  - Maven pour la gestion des dépendances
  - Tomcat comme serveur d'application
- **Outils de Conception** :
  - Bootstrap pour le design
  - FIGMA pour le maquettage
- **Architecture MVC en Couches** :
  - Présentation (View)
  - Contrôleur (Controller)
  - Service
  - DAO (Data Access Object)
  - Repository
  - Utilitaires
- **Design Patterns** :
  - Repository
  - Singleton
- **Base de Données** : MySQL
  - Utilisation du pilote JDBC pour l'accès aux données
  - Script SQL : utilisation de `INDEX`, `GRANT`, `UNIQUE`, `NOT NULL`
  - Adapter la base de données existante pour une utilisation web
- **Tests Unitaires** : JUnit
- **Déploiement** :
  - Créer un fichier WAR et le déployer sur Tomcat sans utiliser Eclipse
- **Configuration** :
  - Utiliser un fichier `web.xml` pour la configuration (aucune annotation comme `@WebServlet` n'est permise)
- **Logging** : Implémenter un système de logging (LOGGER)
- **Gestion de Projet** : JIRA avec méthode Scrum, synchronisation avec Git
- **Pagination** : Implémenter la pagination pour les listes de projets, tâches et membres.

## Concepts Java Avancés

- Utilisation de l'API Java Time et des Collections.
- Utilisation des `HashMap`.
- Expressions lambda et Java Stream API.
- Gestion avancée des erreurs et validations.

## Installation et Lancement du Projet

### Prérequis
- **JDK 8+** : Assurez-vous que Java 8 est installé sur votre machine.
- **Maven** : Maven doit être configuré pour gérer les dépendances du projet.
- **Tomcat** : Téléchargez et configurez Apache Tomcat (version 9 ou supérieure).
- **MySQL** : Base de données MySQL pour stocker les projets, tâches, membres, et équipes.

#### 1. Configurer la Base de Données
- Créez une base de données MySQL nommée `taskManager`.
- Importez le fichier SQL situé dans le dossier `scripts/` pour créer les tables et insérer les données initiales.
