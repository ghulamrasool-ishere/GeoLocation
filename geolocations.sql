-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 25, 2019 at 08:52 AM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `geolocations`
--

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE `locations` (
  `id` int(10) NOT NULL,
  `location` varchar(100) NOT NULL,
  `latlon` varchar(30) NOT NULL,
  `description` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `locations`
--

INSERT INTO `locations` (`id`, `location`, `latlon`, `description`) VALUES
(1, 'COMSATS University', '31.4014296,74.2099841', '1.5 KM Defence Road، Off Raiwand Rd, Lda Avenue Phase 1 Lda Avenue, Lahore, Punjab 54000, Pakistan'),
(3, 'Jupiter Hall', '31.3800864,74.2247535', 'Lahore Wildlife Park road,، Raiwind Rd, Kot Bagh, Lahore, Punjab, Pakistan'),
(4, 'UOL', '31.3904701,74.2398886', '1-Km Defence Road، Near Bhuptian Chowk، Lahore, Punjab, Pakistan'),
(5, 'Bhubtian Chowk', '31.3955804,74.2305438', 'Raiwind Rd, Lahore, Punjab 54000, Pakistan'),
(6, 'PU-Quaid-i-Azam Campus', '31.4924688,74.2703781', ''),
(7, 'Degree College Tonsa', '30.7031866,70.6585495', ''),
(8, 'Allama Iqbal Airport', '31.5205412,74.4105177', ''),
(9, 'COMSATS ISB', '33.6518263,73.1565933', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` char(30) NOT NULL,
  `user_name` char(30) NOT NULL,
  `password` char(30) NOT NULL,
  `age` tinyint(30) NOT NULL,
  `occupation` char(30) NOT NULL,
  `email` char(30) NOT NULL,
  `home` char(30) NOT NULL,
  `work` char(30) NOT NULL,
  `cafe` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `user_name`, `password`, `age`, `occupation`, `email`, `home`, `work`, `cafe`) VALUES
(21, 'Arslan', 'ars', '12345678', 28, 'student', 'arslan@gmail.com', '33.6518263,73.1565933', '', ''),
(18, 'Arslan Saleem', 'arslan', '12345678', 20, 'Student', 'arslan@gmail.com', '', '', ''),
(1, 'Ghulam Rasool', 'GAMA', '12345678', 18, 'Student', 'ghulam.rasool.uni@gmail.com', '31.3904701,74.2398886', '30.7031866,70.6585495', '31.3800864,74.2247535'),
(9, '', 'gamagama', 'Gama', 0, '', '', '', '', ''),
(11, 'gaman', 'gaman', '12345678', 12, 'worker', 'w@', '', '', ''),
(5, 'GAMA', 'GAMU', '1234', 20, 'Student', '@', '', '', ''),
(17, 'Mohsin', 'MOH', '12345678', 23, 'Student', 'mohsin@gmail.com', '', '31.5205412,74.4105177', ''),
(2, 'Mohsin Nazakat', 'mohsin', '12345678', 20, 'Stalker', 'mohsin.nazakat.uni@gmail.com', '', '', ''),
(10, '', 'nomin', '12345678', 0, '', '', '', '', ''),
(12, 'Pakistan', 'Pakistan', '12345678', 73, 'Country', 'pakistan@pakistan.com', '31.3904701,74.2398886', '', ''),
(13, 'Saira Aslam', 'SairaAslam', '12345678', 35, 'Teacher', 'sairaaslam@gmail.com', '31.3800864,74.2247535', '', ''),
(4, 'Usman Javed', 'usman', '12345678', 20, 'Freelance', 'uman.javed.uni@gmail.com', '', '', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `locations`
--
ALTER TABLE `locations`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `location` (`location`),
  ADD UNIQUE KEY `latlon` (`latlon`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_name`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `locations`
--
ALTER TABLE `locations`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
