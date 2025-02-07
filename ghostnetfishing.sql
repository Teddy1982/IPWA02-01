-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 07. Feb 2025 um 20:16
-- Server-Version: 10.4.24-MariaDB
-- PHP-Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `ghostnetfishing`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `geisternetz`
--

CREATE TABLE `geisternetz` (
  `id` int(11) NOT NULL,
  `standort` varchar(50) NOT NULL,
  `groesse` varchar(50) NOT NULL,
  `netzStatus` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `geisternetz`
--

INSERT INTO `geisternetz` (`id`, `standort`, `groesse`, `netzStatus`) VALUES
(1, '11°;11°', 'Eins, Zwo', 'GEMELDET'),
(2, '22°;22°', 'Zwei, Drei', 'BERGUNG'),
(3, '33°;33°', 'Drei, vier', 'GEBORGEN'),
(4, '44°;44°', '4, funf', 'VERSCHOLLEN');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person`
--

CREATE TABLE `person` (
  `name` varchar(255) NOT NULL,
  `telnr` varchar(50) DEFAULT NULL,
  `passwort` varchar(255) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `person`
--

INSERT INTO `person` (`name`, `telnr`, `passwort`, `status`) VALUES
('Person1', '', '111', 'MELDEND'),
('Person2', '222', '222', 'BERGEND'),
('Person3', '333', '333', 'BERGEND');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `person_geisternetz`
--

CREATE TABLE `person_geisternetz` (
  `person_name` varchar(255) NOT NULL,
  `geisternetze_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `person_geisternetz`
--

INSERT INTO `person_geisternetz` (`person_name`, `geisternetze_id`) VALUES
('Person1', 1),
('Person1', 3),
('Person2', 2),
('Person2', 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sequence`
--

CREATE TABLE `sequence` (
  `seq_name` varchar(255) NOT NULL,
  `seq_count` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `sequence`
--

INSERT INTO `sequence` (`seq_name`, `seq_count`) VALUES
('default', 1);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `geisternetz`
--
ALTER TABLE `geisternetz`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`name`);

--
-- Indizes für die Tabelle `person_geisternetz`
--
ALTER TABLE `person_geisternetz`
  ADD PRIMARY KEY (`person_name`,`geisternetze_id`);

--
-- Indizes für die Tabelle `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`seq_name`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `geisternetz`
--
ALTER TABLE `geisternetz`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
