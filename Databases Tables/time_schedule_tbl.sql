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
-- Table structure for table `time_schedule_tbl`
--

CREATE TABLE `time_schedule_tbl` (
  `TID` int(11) DEFAULT NULL,
  `StartTime` time NOT NULL,
  `EndTime` time NOT NULL,
  `Active` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `time_schedule_tbl`
--

INSERT INTO `time_schedule_tbl` (`TID`, `StartTime`, `EndTime`, `Active`) VALUES
(1, '00:00:00', '24:00:00', 'YES'),
(2, '11:00:00', '14:00:00', 'No'),
(3, '14:00:00', '16:00:00', 'No'),
(4, '12:00:00', '13:00:00', 'No');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `time_schedule_tbl`
--
ALTER TABLE `time_schedule_tbl`
  ADD UNIQUE KEY `TID` (`TID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
