-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 27, 2024 at 12:13 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `finalproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `role_privileges`
--

CREATE TABLE `role_privileges` (
  `role_id` int NOT NULL,
  `privilege_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `role_privileges`
--

INSERT INTO `role_privileges` (`role_id`, `privilege_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8);

-- --------------------------------------------------------

--
-- Table structure for table `tb_category`
--

CREATE TABLE `tb_category` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `link_photo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_category`
--

INSERT INTO `tb_category` (`id`, `name`, `link_photo`) VALUES
(1, 'UI/UX Design', 'http://res.cloudinary.com/easyclass/image/upload/v1718671447/kxetyuog8zzmch4z3edm.jpg'),
(2, 'Product Management', 'http://res.cloudinary.com/easyclass/image/upload/v1718671532/jpfk0m0ifgjjl75j2mxd.jpg'),
(3, 'Web Development', 'http://res.cloudinary.com/easyclass/image/upload/v1718671570/zekhhuor7zm9lnssgwu1.jpg'),
(4, 'Android Development', 'http://res.cloudinary.com/easyclass/image/upload/v1718671587/tqgf9nxkljdsr2suhekh.jpg'),
(5, 'Data Science', 'http://res.cloudinary.com/easyclass/image/upload/v1718671603/vriir0tlvx4m693qvmd9.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tb_course`
--

CREATE TABLE `tb_course` (
  `id` int NOT NULL,
  `about` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_premium` bit(1) NOT NULL,
  `level` varchar(25) NOT NULL,
  `mentor` varchar(50) NOT NULL,
  `price` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `total_duration` int NOT NULL,
  `category_id` int NOT NULL,
  `user_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_course`
--

INSERT INTO `tb_course` (`id`, `about`, `is_premium`, `level`, `mentor`, `price`, `title`, `total_duration`, `category_id`, `user_id`) VALUES
(1, 'Kursus \"HTML Basic\" dirancang untuk membantu Anda memulai perjalanan dalam pengembangan web dengan mempelajari dasar-dasar HTML (HyperText Markup Language). Dalam kursus ini, Anda akan mempelajari struktur dasar dokumen HTML, elemen teks, hyperlink, gambar, daftar, tabel, dan formulir. Setiap konsep disertai dengan contoh praktis yang memudahkan pemahaman, sehingga Anda dapat langsung menerapkannya dalam proyek web sederhana.\r\n\r\nKursus ini sangat cocok untuk pemula yang ingin memahami dasar-dasar pengembangan web, pelajar yang membutuhkan pengetahuan HTML untuk tugas sekolah, serta profesional yang ingin menambah keterampilan pengembangan web mereka. Dengan menyelesaikan kursus ini, Anda akan memiliki fondasi yang kuat dalam HTML, yang merupakan langkah pertama untuk menjadi pengembang web yang sukses.', b'1', 'Beginner', 'Teofilus Desius Z.', 129000, 'HTML Basic', 150, 3, 1),
(2, 'Kursus \"Full-Stack Java Programming\" dirancang untuk memberi Anda keahlian dalam mengembangkan aplikasi web yang lengkap dan dinamis menggunakan Java. Dalam kursus ini, Anda akan mempelajari bagaimana mengembangkan frontend yang menarik menggunakan HTML, CSS, dan JavaScript, serta backend yang kuat dan scalable menggunakan Java dengan framework populer seperti Spring Boot. Anda akan belajar tentang RESTful services, manajemen basis data, dan integrasi dengan berbagai API.\r\n\r\nSelain itu, kursus ini akan mengajarkan Anda praktik terbaik dalam pengembangan perangkat lunak, termasuk penggunaan alat version control seperti Git, penerapan arsitektur MVC, dan pengujian otomatis. Dengan menyelesaikan kursus ini, Anda akan siap untuk menjadi seorang Full-Stack Developer yang kompeten, mampu menangani seluruh siklus pengembangan aplikasi web dari awal hingga akhir.', b'1', 'Intermediate', 'M. Bintang Alfarizki', 899000, 'Full-Stack Java Programming', 540, 3, 1),
(3, 'Kursus \"CSS Basic\" dirancang untuk memperkenalkan Anda pada dunia styling web menggunakan CSS (Cascading Style Sheets). Dalam kursus ini, Anda akan belajar cara mengontrol tata letak, warna, font, dan elemen visual lainnya dari halaman web. Anda akan memahami konsep dasar CSS seperti selektor, properti, nilai, dan bagaimana mereka bekerja sama untuk memberikan tampilan yang menarik dan konsisten pada website Anda.\r\n\r\nKursus ini sangat cocok bagi pemula yang telah memahami dasar-dasar HTML dan ingin melangkah lebih jauh dengan memperindah tampilan situs web mereka. Dengan menyelesaikan kursus ini, Anda akan memiliki kemampuan untuk membuat halaman web yang tidak hanya fungsional tetapi juga estetis, memberikan pengalaman pengguna yang lebih baik dan profesional.', b'1', 'Beginner', 'Rovines Derlan H.', 129000, 'CSS Basic', 120, 3, 1),
(4, 'Kursus \"Python for Data Science\" dirancang untuk membekali Anda dengan keterampilan yang dibutuhkan untuk menganalisis dan memvisualisasikan data menggunakan bahasa pemrograman Python. Dalam kursus ini, Anda akan mempelajari dasar-dasar Python, termasuk struktur data, kontrol alur, dan fungsi. Selanjutnya, Anda akan mendalami berbagai pustaka Python yang populer untuk analisis data seperti NumPy, Pandas, Matplotlib, dan Seaborn. Melalui latihan praktis, Anda akan belajar bagaimana membersihkan, memanipulasi, dan menampilkan data dalam berbagai format yang mudah dipahami.\r\n\r\nSelain itu, kursus ini juga akan memperkenalkan Anda pada konsep machine learning dasar menggunakan pustaka Scikit-Learn. Anda akan mempelajari teknik-teknik penting dalam pembelajaran mesin seperti regresi, klasifikasi, dan clustering. Dengan menyelesaikan kursus ini, Anda akan memperoleh keterampilan yang diperlukan untuk mengeksplorasi dan menganalisis dataset besar, serta membangun model prediktif yang dapat memberikan wawasan berharga dari data', b'0', 'Beginner', 'M. Bintang Alfarizki', 0, 'Python for Data Science', 90, 5, 1),
(10, 'Kursus \"Digital Product Management\" dirancang untuk membekali Anda dengan keterampilan dan pengetahuan yang diperlukan untuk mengelola produk digital dengan efektif. Dalam kursus ini, Anda akan mempelajari seluruh siklus hidup produk, mulai dari ideasi dan pengembangan hingga peluncuran dan peningkatan. Anda akan mempelajari cara mengidentifikasi kebutuhan pasar, membuat roadmap produk, serta mengelola tim lintas fungsi untuk memastikan produk Anda memenuhi kebutuhan pelanggan dan tujuan bisnis.\r\n\r\nSelain itu, kursus ini akan mengajarkan Anda tentang berbagai alat dan teknik yang digunakan dalam manajemen produk digital, termasuk Agile, Scrum, dan Lean Startup. Anda akan belajar bagaimana membuat keputusan berdasarkan data, mengelola backlog produk, dan menerapkan strategi peluncuran yang efektif. Dengan menyelesaikan kursus ini, Anda akan siap untuk menghadapi tantangan dalam mengelola produk digital di lingkungan bisnis yang dinamis dan cepat berubah.', b'1', 'Intermediate', 'Rizki Kurnianingsih', 699000, 'Digital Product Management', 135, 2, 1),
(11, 'Kursus UI/UX Research & Design adalah program yang dirancang untuk membekali peserta dengan keterampilan dalam merancang pengalaman pengguna yang optimal. Fokus utama kursus ini adalah pada tahap penelitian dan desain, di mana peserta akan mempelajari metode-metode untuk memahami kebutuhan pengguna, menganalisis data penelitian, dan mengembangkan konsep-konsep desain yang responsif terhadap pengguna. Selain itu, kursus ini juga mencakup praktik-praktik terbaik dalam pengujian prototipe dan iterasi desain untuk memastikan produk akhir dapat memberikan pengalaman pengguna yang intuitif dan memuaskan.\r\n\r\nPeserta dalam kursus UI/UX Research & Design dapat mengharapkan untuk memperoleh pemahaman mendalam tentang bagaimana melakukan penelitian pasar yang efektif, menerapkan teknik-teknik desain visual, dan memanfaatkan alat-alat perangkat lunak untuk memfasilitasi proses desain. Selain itu, mereka juga akan belajar tentang pentingnya kolaborasi lintas disiplin dalam tim pengembangan produk untuk mencapai tujuan-tujuan desain yang telah ditetapkan. Dengan demikian, kursus ini tidak hanya menawarkan keterampilan praktis dalam UI/UX, tetapi juga mempersiapkan peserta untuk berkontribusi secara signifikan dalam menghadapi tantangan-tantangan desain yang kompleks di berbagai industri.', b'1', 'Intermediate', 'Harun Al Hakim', 999000, 'UI/UX Research & Design', 160, 1, 1),
(12, 'Kursus Android Mobile Developer in Industry 4.0 for Manufacturing Industry dirancang khusus untuk membekali peserta dengan keterampilan teknis dan konseptual yang diperlukan untuk mengembangkan aplikasi mobile berbasis Android dalam konteks industri manufaktur yang sedang mengalami transformasi ke era Industri 4.0. Peserta akan mempelajari dasar-dasar pengembangan aplikasi Android, seperti pemrograman dengan Java atau Kotlin, integrasi API, dan pengelolaan basis data, serta fokus pada aplikasi teknologi terkini seperti Internet of Things (IoT), Big Data Analytics, dan Kecerdasan Buatan (AI) yang relevan untuk industri manufaktur.\r\n\r\nKursus ini tidak hanya menawarkan keterampilan teknis dalam pengembangan aplikasi Android, tetapi juga menekankan pentingnya pemahaman mendalam tentang kebutuhan industri manufaktur modern yang terus berubah. Peserta akan dilatih untuk mengembangkan solusi-solusi inovatif yang dapat meningkatkan efisiensi operasional, pemeliharaan prediktif, dan kualitas produk melalui aplikasi mobile berbasis Android. Dengan demikian, kursus ini membuka peluang bagi peserta untuk berkontribusi dalam mendorong transformasi digital di sektor manufaktur, mempersiapkan mereka untuk menjadi pengembang aplikasi yang kompeten dan adaptif di era Industri 4.0.', b'1', 'Intermediate', 'Fazar Hidayat', 1299000, 'Android Mobile Developer In Industry 4.0 For Manufacturing Industry', 380, 4, 1),
(13, 'Kursus Data Science for Business Development dirancang untuk memberikan peserta pemahaman mendalam tentang bagaimana ilmu data dapat diterapkan untuk mendorong pertumbuhan bisnis. Dalam kursus ini, peserta akan mempelajari teknik-teknik pengolahan dan analisis data, mulai dari pengumpulan data, pembersihan data, hingga visualisasi data untuk menghasilkan wawasan yang bermanfaat bagi pengambilan keputusan bisnis. Selain itu, peserta juga akan diperkenalkan dengan alat-alat analisis data populer seperti Python, R, dan perangkat lunak business intelligence.\r\n\r\nKursus ini juga menekankan pentingnya memahami konteks bisnis dalam analisis data, sehingga peserta tidak hanya mahir secara teknis tetapi juga mampu menerjemahkan data menjadi strategi bisnis yang efektif. Peserta akan belajar bagaimana mengidentifikasi peluang bisnis, mengukur kinerja, dan memprediksi tren pasar berdasarkan data yang ada. Dengan demikian, kursus ini membantu peserta untuk mengembangkan kemampuan analitis dan strategis yang diperlukan untuk memaksimalkan potensi bisnis melalui pendekatan berbasis data.', b'1', 'Beginner', 'Rizki Kurnianingsih', 399000, 'Data Science For Business Development', 280, 5, 1),
(14, 'Kursus Database MySQL dirancang untuk memberikan pemahaman komprehensif mengenai pengelolaan basis data menggunakan MySQL, salah satu sistem manajemen basis data relasional (RDBMS) yang paling populer. Dalam kursus ini, peserta akan mempelajari konsep dasar hingga lanjutan tentang bagaimana merancang, mengimplementasikan, dan mengoptimalkan basis data MySQL. Materi yang diajarkan mencakup penulisan dan pengelolaan query SQL, normalisasi data, serta teknik-teknik untuk menjaga integritas dan keamanan data.\r\n\r\nSelain aspek teknis, kursus ini juga menekankan pentingnya pemahaman praktis dalam konteks aplikasi nyata. Peserta akan diberikan berbagai studi kasus dan proyek yang memungkinkan mereka untuk menerapkan pengetahuan yang diperoleh dalam situasi dunia nyata. Dengan demikian, kursus ini tidak hanya membekali peserta dengan keterampilan teknis yang kuat dalam manajemen basis data, tetapi juga kesiapan untuk menghadapi tantangan-tantangan yang sering muncul dalam pengelolaan data di berbagai industri.', b'0', 'Beginner', 'Harun Al Hakim', 0, 'MySQL', 75, 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_module`
--

CREATE TABLE `tb_module` (
  `id` int NOT NULL,
  `content` varchar(255) NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `duration` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `course_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_module`
--

INSERT INTO `tb_module` (`id`, `content`, `description`, `duration`, `name`, `course_id`) VALUES
(1, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Full Stack Java Programming.', 180, 'Introduction to Programming', 2),
(2, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Full Stack Java Programming.', 180, 'Java Programming I', 2),
(3, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Full Stack Java Programming.', 180, 'Java Programming II', 2),
(4, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about CSS Basic.', 60, 'Introduction to CSS', 3),
(5, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about HTML Basic.', 50, 'Introduction to HTML', 1),
(6, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Python Basic', 30, 'Introduction to Python Programming for Data Science Part 1', 4),
(7, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Python Basic', 30, 'Introduction to Python Programming for Data Science Part 2', 4),
(11, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning basic-basic skills from CSS.', 30, 'CSS Part I', 3),
(12, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning basic-basic skills from CSS.', 30, 'CSS Part II', 3),
(19, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Python Basic', 30, 'Introduction to Python Programming for Data Science Part 3', 4),
(25, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about HTML Basic.', 50, 'HTML Part I', 1),
(1002, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about HTML Basic.', 50, 'HTML Part II', 1),
(1003, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Product management.', 65, 'PM Part I', 10),
(1004, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Product management.', 30, 'PM Part II', 10),
(1005, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Product management.', 15, 'PM Part III', 10),
(1006, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Product management.', 25, 'PM Part IV', 10),
(1007, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about UI/UX Research & Design.', 39, 'Introduction to UI/UX', 11),
(1008, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about UI/UX Research & Design.', 71, 'UI/UX Part I', 11),
(1009, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about UI/UX Research & Design.', 50, 'UI/UX Part II', 11),
(1010, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Android Developer.', 45, 'AND Part I', 12),
(1011, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Android Developer.', 90, 'AND Part II', 12),
(1012, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Android Developer.', 120, 'AND Part III', 12),
(1013, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Android Developer.', 80, 'AND Part IV', 12),
(1014, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Android Developer.', 45, 'AND Part V', 12),
(1015, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Data Science.', 100, 'Data Science Introduction', 13),
(1016, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about Data Science.', 180, 'Data Science For Business Development', 13),
(1017, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about MySQL.', 30, 'MySQL Part I', 14),
(1018, 'https://youtu.be/04YZOIFPxP0?si=GL5iYGxv1aP4PI3-', 'Learning about MySQL.', 45, 'MySQL Part II', 14);

-- --------------------------------------------------------

--
-- Table structure for table `tb_order`
--

CREATE TABLE `tb_order` (
  `id` varchar(255) NOT NULL,
  `is_paid` bit(1) NOT NULL,
  `time` datetime(6) NOT NULL,
  `course_id` int NOT NULL,
  `payment_id` int NOT NULL,
  `user_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_order`
--

INSERT INTO `tb_order` (`id`, `is_paid`, `time`, `course_id`, `payment_id`, `user_id`) VALUES
('227ec7fe-0937-40be-b4b0-0b87cb84c413', b'1', '2024-06-18 08:01:12.024000', 1, 5, 2),
('8bb6a547-6895-4fd3-b8f0-14d65aeea2ee', b'1', '2024-06-26 18:16:49.415000', 3, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tb_payment`
--

CREATE TABLE `tb_payment` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_payment`
--

INSERT INTO `tb_payment` (`id`, `name`) VALUES
(5, 'Bank Transfer'),
(6, 'Credit Card'),
(3, 'DANA'),
(1, 'GoPay'),
(4, 'OVO'),
(2, 'ShopeePay');

-- --------------------------------------------------------

--
-- Table structure for table `tb_privilege`
--

CREATE TABLE `tb_privilege` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_privilege`
--

INSERT INTO `tb_privilege` (`id`, `name`) VALUES
(1, 'CREATE_ADMIN'),
(5, 'CREATE_USER'),
(4, 'DELETE_ADMIN'),
(8, 'DELETE_USER'),
(2, 'READ_ADMIN'),
(6, 'READ_USER'),
(3, 'UPDATE_ADMIN'),
(7, 'UPDATE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `tb_role`
--

CREATE TABLE `tb_role` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_role`
--

INSERT INTO `tb_role` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int NOT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id`, `city`, `country`, `email`, `name`, `password`, `phone`, `username`) VALUES
(1, 'Central Jakarta', 'Indonesia', 'admin@gmail.com', 'Admin', '$2a$10$vm/jXvsQe3Xl3ZonmTEOuuXjHIazMTkos9kzjjDvbwsMeKFKDkpa.', '081234567890', 'admin'),
(2, 'Central Jakarta', 'Indonesia', 'alfarizky0912@gmail.com', 'Bintang', '$2a$10$kgXkPHbCD3aKBzjZR/CFPegg0Ukj74j4j5c.Fo2PbIykvxh1T9kcm', '085156169412', 'starsyndrome'),
(12, 'South Jakarta', 'Indonesia', 'test@gmail.com', 'Test', '$2a$10$BJf18DjKfPZKgYkiGYNeAeg6n3l7Yek6JdLN1pySpiH8epB6GfbEC', '081987654321', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(12, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `role_privileges`
--
ALTER TABLE `role_privileges`
  ADD KEY `FK5jikgip1c9jm8tw96cyyjyc4t` (`privilege_id`),
  ADD KEY `FKoi6kllfqx49bagv6x4u5jbqo` (`role_id`);

--
-- Indexes for table `tb_category`
--
ALTER TABLE `tb_category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_4gpl7afmaxiecnv7fv7unn2mp` (`name`);

--
-- Indexes for table `tb_course`
--
ALTER TABLE `tb_course`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6h94dyy8xu3ei6ulyvfw39ki7` (`title`),
  ADD KEY `fk_course2` (`category_id`),
  ADD KEY `fk_course1` (`user_id`);

--
-- Indexes for table `tb_module`
--
ALTER TABLE `tb_module`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_j5slefafl83xqcsmhfnxh5oaw` (`name`),
  ADD KEY `fk_module` (`course_id`);

--
-- Indexes for table `tb_order`
--
ALTER TABLE `tb_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_order3` (`course_id`),
  ADD KEY `fk_order1` (`payment_id`),
  ADD KEY `fk_order2` (`user_id`);

--
-- Indexes for table `tb_payment`
--
ALTER TABLE `tb_payment`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6n788n42vy5rgmqh7dd10iwts` (`name`);

--
-- Indexes for table `tb_privilege`
--
ALTER TABLE `tb_privilege`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_t4vvgri0dgkkyq3uu04gfa3hu` (`name`);

--
-- Indexes for table `tb_role`
--
ALTER TABLE `tb_role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_1ncmoedv5ta7r19y9d4oidn0y` (`name`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_4vih17mube9j7cqyjlfbcrk4m` (`email`),
  ADD UNIQUE KEY `UK_8yfdv7pbvgjexnabpkxnd2v2w` (`phone`),
  ADD UNIQUE KEY `UK_4wv83hfajry5tdoamn8wsqa6x` (`username`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD KEY `fk_user2` (`role_id`),
  ADD KEY `fk_user1` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_category`
--
ALTER TABLE `tb_category`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tb_course`
--
ALTER TABLE `tb_course`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tb_module`
--
ALTER TABLE `tb_module`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1019;

--
-- AUTO_INCREMENT for table `tb_payment`
--
ALTER TABLE `tb_payment`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tb_privilege`
--
ALTER TABLE `tb_privilege`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tb_role`
--
ALTER TABLE `tb_role`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `role_privileges`
--
ALTER TABLE `role_privileges`
  ADD CONSTRAINT `FK5jikgip1c9jm8tw96cyyjyc4t` FOREIGN KEY (`privilege_id`) REFERENCES `tb_privilege` (`id`),
  ADD CONSTRAINT `FKoi6kllfqx49bagv6x4u5jbqo` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);

--
-- Constraints for table `tb_course`
--
ALTER TABLE `tb_course`
  ADD CONSTRAINT `fk_course1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  ADD CONSTRAINT `fk_course2` FOREIGN KEY (`category_id`) REFERENCES `tb_category` (`id`);

--
-- Constraints for table `tb_module`
--
ALTER TABLE `tb_module`
  ADD CONSTRAINT `fk_module` FOREIGN KEY (`course_id`) REFERENCES `tb_course` (`id`);

--
-- Constraints for table `tb_order`
--
ALTER TABLE `tb_order`
  ADD CONSTRAINT `fk_order1` FOREIGN KEY (`payment_id`) REFERENCES `tb_payment` (`id`),
  ADD CONSTRAINT `fk_order2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  ADD CONSTRAINT `fk_order3` FOREIGN KEY (`course_id`) REFERENCES `tb_course` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `fk_user1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  ADD CONSTRAINT `fk_user2` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
