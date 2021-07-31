-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 31, 2021 at 10:08 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.4.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_perpus`
--
CREATE DATABASE IF NOT EXISTS `db_perpus` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `db_perpus`;

-- --------------------------------------------------------

--
-- Table structure for table `buku_master`
--

CREATE TABLE `buku_master` (
  `id` int(11) NOT NULL,
  `judul` varchar(40) NOT NULL,
  `penulis` varchar(40) NOT NULL,
  `penerbit` varchar(40) NOT NULL,
  `tahun` varchar(10) NOT NULL,
  `kategori` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `buku_master`
--

INSERT INTO `buku_master` (`id`, `judul`, `penulis`, `penerbit`, `tahun`, `kategori`) VALUES
(1, 'konoha', 'konoha', 'konoha', 'konoha', 'konoha'),
(2, 'KOjo', 'aosk', 'asi', 'oi', 'oi'),
(3, 'jj', 'ajk', 'dak', 'kdajk', 'kdajk'),
(4, 'jj', 'ajk', 'dak', 'kdajk', 'kdajk'),
(5, 'ajsldj', 'dajl', 'dakj', 'daklj', 'daklj'),
(6, 'admin', 'admin', 'daojo', 'doao', 'doao'),
(7, 'admin', 'admin', 'daojo', 'doao', 'doao'),
(8, 'bukusaya', 'bundar', 'kalau', 'tidak', 'tidak'),
(9, 'bukusaya', 'bundar', 'kalau', 'tidak', 'tidak'),
(10, 'bukusaya', 'bundar', 'kalau', 'tidak', 'tidak'),
(11, 'oooj', 'idoai', 'oidaoi', 'doaio', 'doaio'),
(12, 'admin', 'admin', 'dakj', 'kdaj', 'kdaj');

-- --------------------------------------------------------

--
-- Table structure for table `t_login`
--

CREATE TABLE `t_login` (
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `no_telp` varchar(40) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_login`
--

INSERT INTO `t_login` (`username`, `password`, `nama`, `no_telp`, `alamat`) VALUES
('admin', 'admin', 'admin', '09', 'da'),
('arunnurhuda', 'Omayun123', 'Arun Nurhuda', '089601188294', 'Jalan Sepakat'),
('asep', 'ganteng', 'Asep Saepudin', '08121212', 'Karawang	');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku_master`
--
ALTER TABLE `buku_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_login`
--
ALTER TABLE `t_login`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buku_master`
--
ALTER TABLE `buku_master`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
