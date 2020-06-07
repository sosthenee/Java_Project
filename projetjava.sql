-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 07 juin 2020 à 16:42
-- Version du serveur :  5.7.24
-- Version de PHP :  7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projetjava`
--

-- --------------------------------------------------------

--
-- Structure de la table `cours`
--

DROP TABLE IF EXISTS `cours`;
CREATE TABLE IF NOT EXISTS `cours` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cours`
--

INSERT INTO `cours` (`id`, `nom`) VALUES
(1, 'Maths'),
(2, 'Physique'),
(3, 'Electronique'),
(4, 'Reseau'),
(5, 'Informatique');

-- --------------------------------------------------------

--
-- Structure de la table `enseignant_cours`
--

DROP TABLE IF EXISTS `enseignant_cours`;
CREATE TABLE IF NOT EXISTS `enseignant_cours` (
  `id_enseignant` bigint(20) NOT NULL,
  `id_cours` bigint(20) NOT NULL,
  PRIMARY KEY (`id_enseignant`,`id_cours`),
  KEY `FK7lqmn0cxwc6nkhf8rakq0om88` (`id_cours`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `enseignant_cours`
--

INSERT INTO `enseignant_cours` (`id_enseignant`, `id_cours`) VALUES
(8, 1),
(2, 2),
(8, 3),
(11, 4),
(2, 5),
(11, 5);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE IF NOT EXISTS `groupe` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `id_promotion` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt4yacmiqiullaa40apuiu61r6` (`id_promotion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`id`, `nom`, `id_promotion`) VALUES
(1, 'ING3-TD09', 3),
(2, 'ING3-TD08', 3),
(3, 'ING4-TD01', 2),
(4, 'ING4-TD02', 2),
(5, 'ING5-TD04', 1),
(6, 'ING5-TD05', 1);

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(18),
(18),
(18),
(18),
(18),
(18),
(18),
(18);

-- --------------------------------------------------------

--
-- Structure de la table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
CREATE TABLE IF NOT EXISTS `promotion` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `promotion`
--

INSERT INTO `promotion` (`id`, `nom`) VALUES
(1, '2020'),
(2, '2021'),
(3, '2022');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `id` bigint(20) NOT NULL,
  `capacite` int(11) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `id_site` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpbb1wyod2e7talc9m3h6udqpe` (`id_site`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`id`, `capacite`, `nom`, `id_site`) VALUES
(1, 35, 'P304', 2),
(2, 35, 'P305', 2),
(3, 150, 'Em10', 1),
(4, 150, 'Em09', 1),
(5, 30, 'Sc10', 3),
(6, 30, 'Sc11', 3);

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

DROP TABLE IF EXISTS `seance`;
CREATE TABLE IF NOT EXISTS `seance` (
  `id` bigint(20) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `etat` int(11) DEFAULT NULL,
  `heure_fin` int(11) DEFAULT NULL,
  `minute_fin` int(11) DEFAULT NULL,
  `semaine` int(11) DEFAULT NULL,
  `id_cours` bigint(20) DEFAULT NULL,
  `id_type` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2m8aw7gjwv3l098ji1bmvg8bo` (`id_cours`),
  KEY `FKqk4141q8snk0yjroejpre3ok4` (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance`
--

INSERT INTO `seance` (`id`, `date`, `etat`, `heure_fin`, `minute_fin`, `semaine`, `id_cours`, `id_type`) VALUES
(11, '2019-12-30 08:00:54.577000', 1, 10, 0, 1, 1, 3),
(12, '2019-12-30 10:30:03.462000', 1, 12, 0, 1, 5, 1),
(13, '2019-12-30 13:30:05.029000', 2, 16, 0, 1, 3, 2),
(14, '2019-12-31 09:00:39.113000', 0, 12, 30, 1, 4, 3),
(15, '2020-01-01 10:00:31.216000', 1, 12, 30, 1, 1, 1),
(16, '2020-01-02 08:00:48.648000', 1, 9, 30, 1, 2, 1),
(17, '2020-01-07 08:30:39.896000', 1, 11, 0, 2, 5, 3);

-- --------------------------------------------------------

--
-- Structure de la table `seance_enseignants`
--

DROP TABLE IF EXISTS `seance_enseignants`;
CREATE TABLE IF NOT EXISTS `seance_enseignants` (
  `seance_id` bigint(20) NOT NULL,
  `enseignant_id` bigint(20) NOT NULL,
  KEY `FKbjp6aelp3r47a9mss2nsqlcie` (`enseignant_id`),
  KEY `FKans9jtsie69dqqy9q418t4nqs` (`seance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance_enseignants`
--

INSERT INTO `seance_enseignants` (`seance_id`, `enseignant_id`) VALUES
(11, 8),
(12, 11),
(13, 8),
(14, 11),
(15, 8),
(16, 2),
(17, 2),
(17, 11);

-- --------------------------------------------------------

--
-- Structure de la table `seance_groupes`
--

DROP TABLE IF EXISTS `seance_groupes`;
CREATE TABLE IF NOT EXISTS `seance_groupes` (
  `seance_id` bigint(20) NOT NULL,
  `groupe_id` bigint(20) NOT NULL,
  KEY `FK4fserbldc7g7y2r3dr50giofd` (`groupe_id`),
  KEY `FK2da8ruroe959qx4x4cs7q35wk` (`seance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance_groupes`
--

INSERT INTO `seance_groupes` (`seance_id`, `groupe_id`) VALUES
(11, 2),
(12, 2),
(13, 2),
(14, 1),
(14, 2),
(15, 2),
(16, 2),
(17, 1),
(17, 2);

-- --------------------------------------------------------

--
-- Structure de la table `seance_salles`
--

DROP TABLE IF EXISTS `seance_salles`;
CREATE TABLE IF NOT EXISTS `seance_salles` (
  `seance_id` bigint(20) NOT NULL,
  `salle_id` bigint(20) NOT NULL,
  KEY `FK7v21um13qfcd44t5jfv3esk7x` (`salle_id`),
  KEY `FKq97ijk03j9t83g1y6a7jba21e` (`seance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `seance_salles`
--

INSERT INTO `seance_salles` (`seance_id`, `salle_id`) VALUES
(11, 3),
(12, 1),
(13, 2),
(14, 4),
(15, 5),
(16, 2),
(17, 3);

-- --------------------------------------------------------

--
-- Structure de la table `site`
--

DROP TABLE IF EXISTS `site`;
CREATE TABLE IF NOT EXISTS `site` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `site`
--

INSERT INTO `site` (`id`, `nom`) VALUES
(1, 'E1'),
(2, 'E2'),
(3, 'E3');

-- --------------------------------------------------------

--
-- Structure de la table `type_cours`
--

DROP TABLE IF EXISTS `type_cours`;
CREATE TABLE IF NOT EXISTS `type_cours` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type_cours`
--

INSERT INTO `type_cours` (`id`, `nom`) VALUES
(1, 'TD'),
(2, 'TP'),
(3, 'CI');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `dtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `droit` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `numero` bigint(20) DEFAULT NULL,
  `id_groupe` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmr47w5kd5awg7hia7i3ylu2tf` (`id_groupe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`dtype`, `id`, `droit`, `email`, `nom`, `password`, `prenom`, `numero`, `id_groupe`) VALUES
('Etudiant', 1, 4, 'etudiant@ece.fr', 'Marze', 'test', 'Oscar', 123465798, 1),
('Enseignant', 2, 3, 'enseignant@ece.fr', 'Segado', 'test', 'J-P', NULL, NULL),
('Admin', 3, 1, 'admin@ece.fr', 'admin', 'test', 'admin', NULL, NULL),
('Admin', 4, 2, 'referent@ece.fr', 'referent', 'test', 'referent', NULL, NULL),
('Etudiant', 5, 4, 'etudiant2@ece.fr', 'Lamotte', 'test', 'Sosthene', 123465791, 1),
('Etudiant', 6, 4, 'etudiant3@ece.fr', 'Delahegue', 'test', 'Emilien', 1234657914, 3),
('Etudiant', 7, 4, 'etudiant4@ece.fr', 'Teixeira', 'test', 'Tiago', 1234657918, 3),
('Enseignant', 8, 3, 'enseignant2@ece.fr', 'Coudray', 'enseignant2', 'Fabienne', NULL, NULL),
('Etudiant', 9, 4, 'etudiant5@ece.fr', 'Plante', 'test', 'Gautier', 12346579163, 2),
('Etudiant', 10, 4, 'etudiant6@ece.fr', 'Guisnel', 'test', 'Thibault', 123465791, 2),
('Enseignant', 11, 3, 'enseignant4@ece.fr', 'Hina', 'test', 'Manolo', NULL, NULL);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `enseignant_cours`
--
ALTER TABLE `enseignant_cours`
  ADD CONSTRAINT `FK7lqmn0cxwc6nkhf8rakq0om88` FOREIGN KEY (`id_cours`) REFERENCES `cours` (`id`),
  ADD CONSTRAINT `FKsqueckoyy7cwom60b2e14s7yg` FOREIGN KEY (`id_enseignant`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD CONSTRAINT `FKt4yacmiqiullaa40apuiu61r6` FOREIGN KEY (`id_promotion`) REFERENCES `promotion` (`id`);

--
-- Contraintes pour la table `salle`
--
ALTER TABLE `salle`
  ADD CONSTRAINT `FKpbb1wyod2e7talc9m3h6udqpe` FOREIGN KEY (`id_site`) REFERENCES `site` (`id`);

--
-- Contraintes pour la table `seance`
--
ALTER TABLE `seance`
  ADD CONSTRAINT `FK2m8aw7gjwv3l098ji1bmvg8bo` FOREIGN KEY (`id_cours`) REFERENCES `cours` (`id`),
  ADD CONSTRAINT `FKqk4141q8snk0yjroejpre3ok4` FOREIGN KEY (`id_type`) REFERENCES `type_cours` (`id`);

--
-- Contraintes pour la table `seance_enseignants`
--
ALTER TABLE `seance_enseignants`
  ADD CONSTRAINT `FKans9jtsie69dqqy9q418t4nqs` FOREIGN KEY (`seance_id`) REFERENCES `seance` (`id`),
  ADD CONSTRAINT `FKbjp6aelp3r47a9mss2nsqlcie` FOREIGN KEY (`enseignant_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `seance_groupes`
--
ALTER TABLE `seance_groupes`
  ADD CONSTRAINT `FK2da8ruroe959qx4x4cs7q35wk` FOREIGN KEY (`seance_id`) REFERENCES `seance` (`id`),
  ADD CONSTRAINT `FK4fserbldc7g7y2r3dr50giofd` FOREIGN KEY (`groupe_id`) REFERENCES `groupe` (`id`);

--
-- Contraintes pour la table `seance_salles`
--
ALTER TABLE `seance_salles`
  ADD CONSTRAINT `FK7v21um13qfcd44t5jfv3esk7x` FOREIGN KEY (`salle_id`) REFERENCES `salle` (`id`),
  ADD CONSTRAINT `FKq97ijk03j9t83g1y6a7jba21e` FOREIGN KEY (`seance_id`) REFERENCES `seance` (`id`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FKmr47w5kd5awg7hia7i3ylu2tf` FOREIGN KEY (`id_groupe`) REFERENCES `groupe` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
