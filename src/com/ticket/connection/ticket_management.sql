-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 28, 2019 at 03:29 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.1.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ticket_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `department_id` int(11) NOT NULL,
  `department_name` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `departments`
--

INSERT INTO `departments` (`department_id`, `department_name`) VALUES
(12, 'backend'),
(11, 'frontend'),
(10, 'testing');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(200) DEFAULT NULL,
  `default_assignee` varchar(200) DEFAULT NULL,
  `parent` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `product_name`, `default_assignee`, `parent`) VALUES
(1, 'PSUPTUI ', 'mike', 0),
(3, 'PSUPCORE ', 'pranav', 0),
(9, 'Textual UI', 'pranav', 1),
(10, 'Generic Tree', 'mike', 1),
(11, 'AdminTool ', 'pranav', 3),
(12, 'UIGT', 'pranav', 3),
(26, 'PDCR', 'fehad', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `ticket_id` int(11) NOT NULL,
  `ticket_key` varchar(200) DEFAULT NULL,
  `ticket_type_id` int(11) NOT NULL DEFAULT 0,
  `product_id` int(11) NOT NULL DEFAULT 0,
  `component` int(11) DEFAULT 0,
  `summary` varchar(200) DEFAULT NULL,
  `assignee` varchar(200) DEFAULT NULL,
  `reporter` varchar(200) DEFAULT NULL,
  `priority` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  `resolution` varchar(200) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tickets`
--

INSERT INTO `tickets` (`ticket_id`, `ticket_key`, `ticket_type_id`, `product_id`, `component`, `summary`, `assignee`, `reporter`, `priority`, `status`, `resolution`, `due_date`, `created`, `updated`) VALUES
(62, 'PSUPTUI -62', 8, 1, 10, 'dfghnj', 'fehad', 'fehad', 'Blocker', 'open', 'unresolved', '2019-08-28', '2019-08-26 19:49:01', '2019-08-26 19:49:01'),
(69, 'PSUPCORE -69', 8, 3, 9, 'ddess', 'fehad', 'fehad', 'Critical', 'open', 'unresolved', '2019-08-28', '2019-08-27 00:25:24', '2019-08-27 00:25:24'),
(70, 'PSUPTUI -70', 13, 1, 9, 'gffg', 'mike', 'mike', 'Blocker', 'open', 'unresolved', '2019-08-29', '2019-08-27 00:33:07', '2019-08-27 00:33:07'),
(71, 'PSUPTUI -71', 11, 1, 0, 'dfgh', 'pranav', 'mike', 'Blocker', 'open', 'unresolved', '2019-08-29', '2019-08-27 00:35:14', '2019-08-27 00:35:14');

-- --------------------------------------------------------

--
-- Table structure for table `ticket_types`
--

CREATE TABLE `ticket_types` (
  `ticket_type_id` int(11) NOT NULL,
  `ticket_name` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ticket_types`
--

INSERT INTO `ticket_types` (`ticket_type_id`, `ticket_name`) VALUES
(8, 'buggs'),
(13, 'client error'),
(7, 'feature'),
(11, 'PDCR'),
(14, 'server problem');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `role` varchar(200) DEFAULT NULL,
  `department_id` int(11) NOT NULL DEFAULT 0,
  `home_company` varchar(200) DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `password`, `name`, `username`, `email`, `role`, `department_id`, `home_company`, `created`) VALUES
(1, '123456', 'fehad', 'admin', 'admin@nc.com', 'admin', 1, 'russia', '2019-08-20 10:07:05'),
(2, '123456', 'mike', 'employee', 'employee@nc.com', 'employee', 1, 'bangalore', '2019-08-20 10:07:05'),
(5, '123456', 'pranav', 'pranav', 'pranav@nc.com', 'employee', 0, 'new delhi', '2019-08-24 07:54:19'),
(6, '123456', 'ayush', 'ayush_123', 'ayush@nc.com', 'employee', 0, 'bangalore', '2019-08-25 11:04:56');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`department_id`),
  ADD UNIQUE KEY `department_name` (`department_name`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`),
  ADD UNIQUE KEY `product_name` (`product_name`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`ticket_id`),
  ADD UNIQUE KEY `ticket_key` (`ticket_key`);

--
-- Indexes for table `ticket_types`
--
ALTER TABLE `ticket_types`
  ADD PRIMARY KEY (`ticket_type_id`),
  ADD UNIQUE KEY `ticket_name` (`ticket_name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
  MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `ticket_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `ticket_types`
--
ALTER TABLE `ticket_types`
  MODIFY `ticket_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
