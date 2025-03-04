-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2020 at 03:50 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `custform`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `name` varchar(100) NOT NULL,
  `dos` date NOT NULL,
  `doe` date NOT NULL,
  `amount` float NOT NULL,
  `cqtotal` float NOT NULL,
  `bqtotal` float NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`name`, `dos`, `doe`, `amount`, `cqtotal`, `bqtotal`, `status`) VALUES
('harshita', '2020-03-27', '2020-05-19', 0, 106, 159, 1),
('ishaan', '2020-04-22', '2020-05-26', 10200, 136, 68, 0);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `name` varchar(50) NOT NULL,
  `mobile` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `cquan` float NOT NULL,
  `cprice` float NOT NULL,
  `bquan` float NOT NULL,
  `bprice` float NOT NULL,
  `dos` date NOT NULL,
  `image` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`name`, `mobile`, `address`, `cquan`, `cprice`, `bquan`, `bprice`, `dos`, `image`) VALUES
('diksha', '9884234232', '22 acre, barnala', 4, 0, 1, 0, '2020-03-17', 'F:\\SARHTAK.jpg'),
('kausha', '9876353432', 'Ajit road, bathinda', 3, 0, 3, 0, '2020-04-07', 'F:\\UMANG.png'),
('harshita', '8591510029', '22 acre, barnala', 2, 0, 3, 0, '2020-05-20', 'F:\\ARJUN.png'),
('tina', '8591510029', 'Chnadiggarh, Punjab', 3, 0, 4, 0, '2020-04-20', 'F:\\DEVESH.png'),
('tina', '8591510029', 'Chnadiggarh, Punjab', 3, 0, 4, 0, '2020-04-20', 'F:\\DEVESH.png'),
('ishaan', '9988221021', 'Patiala ', 4, 50, 2, 50, '2020-05-27', 'C:\\Users\\God\\Desktop\\image.png'),
('ishaan', '9988221021', 'Patiala ', 4, 50, 2, 50, '2020-05-27', 'C:\\Users\\God\\Desktop\\image.png');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userid` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `password`) VALUES
('abcd', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `variations`
--

CREATE TABLE `variations` (
  `cname` varchar(100) NOT NULL,
  `cowvar` float NOT NULL,
  `buffvar` float NOT NULL,
  `currdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `variations`
--

INSERT INTO `variations` (`cname`, `cowvar`, `buffvar`, `currdate`) VALUES
('diksha', -2, 2, '2020-05-04'),
('tina', -2, -2, '2020-04-27');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
