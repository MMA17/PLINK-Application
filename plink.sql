-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 04, 2022 lúc 02:46 AM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `plink`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `class`
--
CREATE DATABASE PLink;

USE PLink;

CREATE TABLE `class` (
  `ID` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `note` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `classmember`
--

CREATE TABLE `classmember` (
  `memberID` int(11) DEFAULT NULL,
  `classID` int(11) DEFAULT NULL,
  `isOwner` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comment`
--

CREATE TABLE `comment` (
  `ID` int(11) NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `postID` int(11) DEFAULT NULL,
  `memberID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `commentfile`
--

CREATE TABLE `commentfile` (
  `commentID` int(11) DEFAULT NULL,
  `fileID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `excersie`
--

CREATE TABLE `excersie` (
  `ID` int(11) NOT NULL,
  `title` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `ClassID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `excersiefile`
--

CREATE TABLE `excersiefile` (
  `excersieID` int(11) DEFAULT NULL,
  `fileID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `file`
--

CREATE TABLE `file` (
  `ID` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `path` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `size` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `member`
--

CREATE TABLE `member` (
  `ID` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `phone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `membersubmited`
--

CREATE TABLE `membersubmited` (
  `memberID` int(11) DEFAULT NULL,
  `excersieID` int(11) DEFAULT NULL,
  `fileID` int(11) DEFAULT NULL,
  `timeSubmited` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `post`
--

CREATE TABLE `post` (
  `ID` int(11) NOT NULL,
  `title` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `author` int(11) DEFAULT NULL,
  `classid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `postfile`
--

CREATE TABLE `postfile` (
  `postID` int(11) DEFAULT NULL,
  `fileID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `classmember`
--
ALTER TABLE `classmember`
  ADD KEY `memberID` (`memberID`),
  ADD KEY `classID` (`classID`);

--
-- Chỉ mục cho bảng `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `postID` (`postID`),
  ADD KEY `memberID` (`memberID`);

--
-- Chỉ mục cho bảng `commentfile`
--
ALTER TABLE `commentfile`
  ADD KEY `commentID` (`commentID`),
  ADD KEY `fileID` (`fileID`);

--
-- Chỉ mục cho bảng `excersie`
--
ALTER TABLE `excersie`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ClassID` (`ClassID`);

--
-- Chỉ mục cho bảng `excersiefile`
--
ALTER TABLE `excersiefile`
  ADD KEY `excersieID` (`excersieID`),
  ADD KEY `fileID` (`fileID`);

--
-- Chỉ mục cho bảng `file`
--
ALTER TABLE `file`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `membersubmited`
--
ALTER TABLE `membersubmited`
  ADD KEY `memberID` (`memberID`),
  ADD KEY `excersieID` (`excersieID`),
  ADD KEY `fileID` (`fileID`);

--
-- Chỉ mục cho bảng `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `author` (`author`),
  ADD KEY `classid` (`classid`);

--
-- Chỉ mục cho bảng `postfile`
--
ALTER TABLE `postfile`
  ADD KEY `postID` (`postID`),
  ADD KEY `fileID` (`fileID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `class`
--
ALTER TABLE `class`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000;

--
-- AUTO_INCREMENT cho bảng `comment`
--
ALTER TABLE `comment`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `excersie`
--
ALTER TABLE `excersie`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `file`
--
ALTER TABLE `file`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10000;

--
-- AUTO_INCREMENT cho bảng `member`
--
ALTER TABLE `member`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT cho bảng `post`
--
ALTER TABLE `post`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `classmember`
--
ALTER TABLE `classmember`
  ADD CONSTRAINT `classmember_ibfk_1` FOREIGN KEY (`memberID`) REFERENCES `member` (`ID`),
  ADD CONSTRAINT `classmember_ibfk_2` FOREIGN KEY (`classID`) REFERENCES `class` (`ID`);

--
-- Các ràng buộc cho bảng `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`postID`) REFERENCES `post` (`ID`),
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`memberID`) REFERENCES `member` (`ID`);

--
-- Các ràng buộc cho bảng `commentfile`
--
ALTER TABLE `commentfile`
  ADD CONSTRAINT `commentfile_ibfk_1` FOREIGN KEY (`commentID`) REFERENCES `comment` (`ID`),
  ADD CONSTRAINT `commentfile_ibfk_2` FOREIGN KEY (`fileID`) REFERENCES `file` (`ID`);

--
-- Các ràng buộc cho bảng `excersie`
--
ALTER TABLE `excersie`
  ADD CONSTRAINT `excersie_ibfk_1` FOREIGN KEY (`ClassID`) REFERENCES `class` (`ID`);

--
-- Các ràng buộc cho bảng `excersiefile`
--
ALTER TABLE `excersiefile`
  ADD CONSTRAINT `excersiefile_ibfk_1` FOREIGN KEY (`excersieID`) REFERENCES `excersie` (`ID`),
  ADD CONSTRAINT `excersiefile_ibfk_2` FOREIGN KEY (`fileID`) REFERENCES `file` (`ID`);

--
-- Các ràng buộc cho bảng `membersubmited`
--
ALTER TABLE `membersubmited`
  ADD CONSTRAINT `membersubmited_ibfk_1` FOREIGN KEY (`memberID`) REFERENCES `member` (`ID`),
  ADD CONSTRAINT `membersubmited_ibfk_2` FOREIGN KEY (`excersieID`) REFERENCES `excersie` (`ID`),
  ADD CONSTRAINT `membersubmited_ibfk_3` FOREIGN KEY (`fileID`) REFERENCES `file` (`ID`);

--
-- Các ràng buộc cho bảng `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`author`) REFERENCES `member` (`ID`),
  ADD CONSTRAINT `post_ibfk_2` FOREIGN KEY (`classid`) REFERENCES `class` (`ID`);

--
-- Các ràng buộc cho bảng `postfile`
--
ALTER TABLE `postfile`
  ADD CONSTRAINT `postfile_ibfk_1` FOREIGN KEY (`postID`) REFERENCES `post` (`ID`),
  ADD CONSTRAINT `postfile_ibfk_2` FOREIGN KEY (`fileID`) REFERENCES `file` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
