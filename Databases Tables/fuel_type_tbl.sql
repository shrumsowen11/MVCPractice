-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 02, 2020 at 04:15 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `keubiko_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `fuel_type_tbl`
--

CREATE TABLE `fuel_type_tbl` (
  `FID` int(11) NOT NULL,
  `Gas` varchar(30) NOT NULL,
  `Gallons` int(11) NOT NULL,
  `CreateDate` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `fuel_type_tbl`
--

INSERT INTO `fuel_type_tbl` (`FID`, `Gas`, `Gallons`, `CreateDate`) VALUES
(1, 'Petrol', 4, '2020-05-28 03:19:17'),
(2, 'Kerosene', 15, '2020-05-28 03:19:17'),
(3, 'petrol', 2, '2020-05-28 22:16:15'),
(4, 'deisel', 4, '2020-05-28 22:17:12'),
(5, 'Wood Coal', 20, '2020-05-28 23:46:55'),
(6, 'coals only', 1, '2020-05-29 05:03:59');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fuel_type_tbl`
--
ALTER TABLE `fuel_type_tbl`
  ADD PRIMARY KEY (`FID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fuel_type_tbl`
--
ALTER TABLE `fuel_type_tbl`
  MODIFY `FID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
