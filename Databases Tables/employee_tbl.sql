-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2020 at 05:49 PM
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
-- Table structure for table `employee_tbl`
--

CREATE TABLE `employee_tbl` (
  `EID` int(11) NOT NULL,
  `UserID` varchar(50) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `DOB` date NOT NULL,
  `MOBILE` bigint(11) DEFAULT NULL,
  `SALARY` double DEFAULT NULL,
  `SSN` bigint(11) NOT NULL,
  `CREATEDATE` timestamp NULL DEFAULT NULL,
  `UPDATEDATE` timestamp NULL DEFAULT NULL,
  `Role` varchar(20) NOT NULL DEFAULT 'Customer'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee_tbl`
--

INSERT INTO `employee_tbl` (`EID`, `UserID`, `Password`, `NAME`, `EMAIL`, `DOB`, `MOBILE`, `SALARY`, `SSN`, `CREATEDATE`, `UPDATEDATE`, `Role`) VALUES
(1, 'shrums', 'shrums', 'shrums', 'shrums@gmail.com', '1994-05-12', 2435235, 2435235, 2435235, '2020-05-07 04:00:00', '2020-05-21 04:00:00', 'Customer'),
(0, 'bibhu', 'bibhu', 'bibhu', 'bibhu@gmail.com', '2020-09-09', 123, 1500, 123, NULL, NULL, 'Customer'),
(4, 'Bidur', 'test', 'Bidur', 'bidur@gmail.com', '1998-03-04', 213, 2000, 11, NULL, NULL, 'Customer'),
(5, 'bidur', 'a', 'a', 'a@gmail.com', '2020-03-02', 1212, 1500, 234, NULL, NULL, 'Customer'),
(6, 'pukar', 'test', 'Pukar', 'pukar@gmail.com', '1996-03-03', 123, 2000, 123, NULL, NULL, 'Customer');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
