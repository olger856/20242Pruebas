-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-10-2024 a las 18:57:21
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `carrito`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detail`
--

CREATE TABLE `detail` (
  `id` varchar(255) NOT NULL,
  `amount` int(11) NOT NULL,
  `product_id` varchar(255) NOT NULL,
  `sale_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
  `id` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`id`, `category`, `description`, `image`, `name`, `price`) VALUES
('1', 'zapatilla', 'zapatilla', '', 'nike', 150);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_name` enum('ROLE_ADMIN','ROLE_USER') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id`, `role_name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sale`
--

CREATE TABLE `sale` (
  `id` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `total` double NOT NULL,
  `client_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `shopping_cart`
--

CREATE TABLE `shopping_cart` (
  `id` varchar(255) NOT NULL,
  `amount` int(11) NOT NULL,
  `client_id` varchar(255) NOT NULL,
  `product_id` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `user_name`) VALUES
('', 'usuario@gmail.com', '12345678', 'usuario'),
('9b06203a-ab23-45b4-aa19-509ca7d92797', 'user@user.com', '$2a$10$1p6R/ri68jyorXv/CZMcn.YaD1VqGZ.7dwHmmNbpI2/pXZYlUliAu', 'user'),
('b7072df5-a4c9-4afb-9238-8274038b0db7', 'user1@user.com', '$2a$10$kS4OMpHv.7ouc7gR3/cE4.6J09MoQjwEEt1hM8RgaKJ0XMI7mMHRi', 'user1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_role`
--

CREATE TABLE `user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
('9b06203a-ab23-45b4-aa19-509ca7d92797', 1),
('b7072df5-a4c9-4afb-9238-8274038b0db7', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `detail`
--
ALTER TABLE `detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnogkshb9tv6mtc9em7efogdni` (`product_id`),
  ADD KEY `FK11itjaoxevf67aa0370vyoj95` (`sale_id`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sale`
--
ALTER TABLE `sale`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKot5vkfc3sih2kdlyhah2yrgi5` (`client_id`);

--
-- Indices de la tabla `shopping_cart`
--
ALTER TABLE `shopping_cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKu4ldicofo0jsb7chiurq8sta` (`client_id`),
  ADD KEY `FKerqg5bwr2tdlwabwdn7r4n2xo` (`product_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKk8d0f2n7n88w1a16yhua64onx` (`user_name`),
  ADD UNIQUE KEY `UKq4gvg4dl2a3fpetfwspodde8e` (`user_name`);

--
-- Indices de la tabla `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detail`
--
ALTER TABLE `detail`
  ADD CONSTRAINT `FK11itjaoxevf67aa0370vyoj95` FOREIGN KEY (`sale_id`) REFERENCES `sale` (`id`),
  ADD CONSTRAINT `FKnogkshb9tv6mtc9em7efogdni` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Filtros para la tabla `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `FKot5vkfc3sih2kdlyhah2yrgi5` FOREIGN KEY (`client_id`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `shopping_cart`
--
ALTER TABLE `shopping_cart`
  ADD CONSTRAINT `FKerqg5bwr2tdlwabwdn7r4n2xo` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKu4ldicofo0jsb7chiurq8sta` FOREIGN KEY (`client_id`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
