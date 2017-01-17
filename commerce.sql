-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 17, 2017 at 02:00 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `commerce`
--

-- --------------------------------------------------------

--
-- Table structure for table `commandes`
--

CREATE TABLE IF NOT EXISTS `commandes` (
  `REF_COM` int(11) NOT NULL,
  `DT` date DEFAULT NULL,
  `PRIX_COM` double DEFAULT NULL,
  `QT_COM` int(11) DEFAULT NULL,
  `login` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`REF_COM`),
  KEY `login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `commandes`
--

INSERT INTO `commandes` (`REF_COM`, `DT`, `PRIX_COM`, `QT_COM`, `login`) VALUES
(14, '2016-01-10', 3919, 2, 'chafca'),
(16, '2016-01-05', 4084, 3, 'houmed');

-- --------------------------------------------------------

--
-- Table structure for table `produits`
--

CREATE TABLE IF NOT EXISTS `produits` (
  `REF_PROD` varchar(25) NOT NULL,
  `DESIGNATION` varchar(70) DEFAULT NULL,
  `PRIX` double DEFAULT NULL,
  `QUANTITE` int(11) DEFAULT NULL,
  PRIMARY KEY (`REF_PROD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produits`
--

INSERT INTO `produits` (`REF_PROD`, `DESIGNATION`, `PRIX`, `QUANTITE`) VALUES
('ALA16', 'laptop lenovo', 200, 36),
('PRO01', 'imprimante hp', 2019, 5),
('PRO13', 'imprimante canon', 1900, 9),
('PRO14', 'imprimante epson', 1092, 7),
('PRO20', 'Telephone Portable', 280, 30);

-- --------------------------------------------------------

--
-- Table structure for table `prod_com`
--

CREATE TABLE IF NOT EXISTS `prod_com` (
  `REF_COM` int(11) NOT NULL DEFAULT '0',
  `REF_PROD` varchar(25) DEFAULT NULL,
  `numero` varchar(50) NOT NULL DEFAULT '',
  `PRIX_PROD_COM` double DEFAULT NULL,
  PRIMARY KEY (`REF_COM`,`numero`),
  KEY `REF_PROD` (`REF_PROD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prod_com`
--

INSERT INTO `prod_com` (`REF_COM`, `REF_PROD`, `numero`, `PRIX_PROD_COM`) VALUES
(14, 'PRO01', 'PRO01006', 2019),
(14, 'PRO13', 'PRO13020', 1900),
(16, 'PRO13', 'PRO13021', 1900),
(16, 'PRO14', 'PRO14028', 1092),
(16, 'PRO14', 'PRO14029', 1092);

-- --------------------------------------------------------

--
-- Table structure for table `series`
--

CREATE TABLE IF NOT EXISTS `series` (
  `numero` varchar(50) NOT NULL,
  `REF_PROD` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`numero`),
  KEY `REF_PROD` (`REF_PROD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `series`
--

INSERT INTO `series` (`numero`, `REF_PROD`) VALUES
('PRO01001', 'PRO01'),
('PRO01002', 'PRO01'),
('PRO01003', 'PRO01'),
('PRO01004', 'PRO01'),
('PRO01005', 'PRO01'),
('PRO13011', 'PRO13'),
('PRO13012', 'PRO13'),
('PRO13013', 'PRO13'),
('PRO13014', 'PRO13'),
('PRO13015', 'PRO13'),
('PRO13016', 'PRO13'),
('PRO13017', 'PRO13'),
('PRO13018', 'PRO13'),
('PRO13019', 'PRO13'),
('PRO14021', 'PRO14'),
('PRO14022', 'PRO14'),
('PRO14023', 'PRO14'),
('PRO14024', 'PRO14'),
('PRO14025', 'PRO14'),
('PRO14026', 'PRO14'),
('PRO14027', 'PRO14');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `login` varchar(50) NOT NULL,
  `passwd` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `role` enum('client','responsable') DEFAULT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`login`, `passwd`, `nom`, `prenom`, `role`) VALUES
('admin', 'admin', 'admin', 'admin', 'responsable'),
('chafca', 'hamham', 'hamoud manlid', 'abdoulrahim', 'client'),
('houmed', 'houmed', 'hassan mohamed', 'houmed', 'client');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `commandes_ibfk_1` FOREIGN KEY (`login`) REFERENCES `users` (`login`);

--
-- Constraints for table `prod_com`
--
ALTER TABLE `prod_com`
  ADD CONSTRAINT `prod_com_ibfk_1` FOREIGN KEY (`REF_PROD`) REFERENCES `produits` (`REF_PROD`),
  ADD CONSTRAINT `prod_com_ibfk_2` FOREIGN KEY (`REF_COM`) REFERENCES `commandes` (`REF_COM`);

--
-- Constraints for table `series`
--
ALTER TABLE `series`
  ADD CONSTRAINT `series_ibfk_1` FOREIGN KEY (`REF_PROD`) REFERENCES `produits` (`REF_PROD`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
