-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 24, 2023 at 01:36 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `monsterbattle`
--

-- --------------------------------------------------------

--
-- Table structure for table `attack`
--

CREATE TABLE `attack` (
  `id` int(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `damage` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `attack`
--

INSERT INTO `attack` (`id`, `name`, `type`, `damage`) VALUES
(1, 'Bash', 'physical', 12),
(2, 'Slice', 'physical', 16),
(3, 'Cut', 'physical', 20),
(4, 'Punch', 'physical', 13),
(5, 'Fireball', 'magical', 18),
(6, 'Ice Spear', 'magical', 17),
(7, 'Rock Throw', 'physical', 11),
(8, 'Water Jet', 'magical', 16),
(9, 'Bubble', 'magical', 10),
(10, 'Inferno', 'magical', 25),
(11, 'Air Slice', 'magical', 17),
(12, 'Earthquake', 'physical', 17),
(13, 'Knock Out', 'physical', 30),
(14, 'Combo Punch', 'physical', 15),
(15, 'Tsunami', 'magical', 20),
(16, 'Karate Punch', 'physical', 16),
(17, 'Hyper Beam', 'magical', 26),
(18, 'Flying Kick', 'physical', 15),
(19, 'Push', 'physical', 10),
(20, 'Dark Pulse', 'magical', 17);

-- --------------------------------------------------------

--
-- Table structure for table `monster`
--

CREATE TABLE `monster` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `minDamage` int(11) NOT NULL,
  `maxDamage` int(11) NOT NULL,
  `health` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `monster`
--

INSERT INTO `monster` (`id`, `name`, `type`, `minDamage`, `maxDamage`, `health`) VALUES
(1, 'Ogre King', 'normal', 5, 20, 60),
(2, 'Orc Lord', 'normal', 10, 20, 70),
(3, 'Banshee', 'ghost', 15, 30, 40),
(4, 'Sadako', 'ghost', 10, 37, 50);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `region` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `password`, `gender`, `region`, `role`) VALUES
(1, 'Darrell', 'darrell@gmail.com', 'binus2025', 'Male', 'Alam Sutera', 'User'),
(2, 'Rudii', 'rudi@email.com', 'rudii', 'Male', 'Bandung', 'User'),
(3, 'ariWuryanto', 'awoor@mail.com', 'ariearie', 'Male', 'Bekasi', 'User'),
(4, 'admin', 'admin@mail.com', 'admin', 'male', 'Alam Sutera', 'admin'),
(5, 'Nasywa', 'nasywa@mail.com', 'untung', 'Female', 'Kemanggisan', 'User');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attack`
--
ALTER TABLE `attack`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `monster`
--
ALTER TABLE `monster`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attack`
--
ALTER TABLE `attack`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `monster`
--
ALTER TABLE `monster`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
