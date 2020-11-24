-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 23 Lis 2020, 23:07
-- Wersja serwera: 10.4.14-MariaDB
-- Wersja PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `iami_project`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `checking_form`
--

CREATE TABLE `checking_form` (
  `id` int(11) NOT NULL,
  `name` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `id_teacher` int(11) NOT NULL,
  `id_subject` int(11) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `course_form`
--

CREATE TABLE `course_form` (
  `id` int(11) NOT NULL,
  `id_course` int(11) NOT NULL,
  `id_checking_form` int(11) NOT NULL,
  `weight` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `course_student`
--

CREATE TABLE `course_student` (
  `id` int(11) NOT NULL,
  `id_course` int(11) NOT NULL,
  `id_student` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `grade`
--

CREATE TABLE `grade` (
  `id` int(11) NOT NULL,
  `id_course` int(11) NOT NULL,
  `id_course_student` int(11) NOT NULL,
  `grade` text NOT NULL,
  `id_checking_form` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `presence`
--

CREATE TABLE `presence` (
  `id` int(11) NOT NULL,
  `date` text NOT NULL,
  `presence` tinyint(1) NOT NULL,
  `id_coure` int(11) NOT NULL,
  `id_course_student` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `surname` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `subject`
--

CREATE TABLE `subject` (
  `id` int(11) NOT NULL,
  `name` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `teacher`
--

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `surname` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `checking_form`
--
ALTER TABLE `checking_form`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `course_form`
--
ALTER TABLE `course_form`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `course_student`
--
ALTER TABLE `course_student`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `presence`
--
ALTER TABLE `presence`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `checking_form`
--
ALTER TABLE `checking_form`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `course_form`
--
ALTER TABLE `course_form`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `course_student`
--
ALTER TABLE `course_student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `grade`
--
ALTER TABLE `grade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `presence`
--
ALTER TABLE `presence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `subject`
--
ALTER TABLE `subject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
