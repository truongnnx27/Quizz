USE [master]
GO
/****** Object:  Database [quizztest]    Script Date: 9/12/2024 9:38:18 AM ******/
CREATE DATABASE [quizztest]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'quizztest', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER01\MSSQL\DATA\quizztest.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'quizztest_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER01\MSSQL\DATA\quizztest_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [quizztest] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [quizztest].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [quizztest] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [quizztest] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [quizztest] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [quizztest] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [quizztest] SET ARITHABORT OFF 
GO
ALTER DATABASE [quizztest] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [quizztest] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [quizztest] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [quizztest] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [quizztest] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [quizztest] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [quizztest] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [quizztest] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [quizztest] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [quizztest] SET  ENABLE_BROKER 
GO
ALTER DATABASE [quizztest] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [quizztest] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [quizztest] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [quizztest] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [quizztest] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [quizztest] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [quizztest] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [quizztest] SET RECOVERY FULL 
GO
ALTER DATABASE [quizztest] SET  MULTI_USER 
GO
ALTER DATABASE [quizztest] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [quizztest] SET DB_CHAINING OFF 
GO
ALTER DATABASE [quizztest] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [quizztest] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [quizztest] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [quizztest] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'quizztest', N'ON'
GO
ALTER DATABASE [quizztest] SET QUERY_STORE = ON
GO
ALTER DATABASE [quizztest] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [quizztest]
GO
/****** Object:  Table [dbo].[comments]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[comments](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[comment_text] [varchar](255) NULL,
	[created_at] [datetime2](6) NULL,
	[updated_at] [datetime2](6) NULL,
	[lesson_id] [bigint] NULL,
	[user_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[course_levels]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[course_levels](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[level_name] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[course_tags]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[course_tags](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[course_id] [bigint] NULL,
	[tag_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[courses]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[courses](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[category] [varchar](255) NULL,
	[created_at] [datetime2](6) NULL,
	[description] [varchar](255) NULL,
	[language] [varchar](255) NULL,
	[number_of_ratings] [int] NOT NULL,
	[number_of_students] [int] NOT NULL,
	[price] [numeric](38, 2) NULL,
	[rating] [numeric](38, 2) NULL,
	[title] [varchar](255) NOT NULL,
	[updated_at] [datetime2](6) NULL,
	[course_level_id] [bigint] NULL,
	[created_by] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[enrollments]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[enrollments](
	[course_id] [bigint] NOT NULL,
	[user_id] [bigint] NOT NULL,
	[enrolled_at] [datetime2](6) NULL,
PRIMARY KEY CLUSTERED 
(
	[course_id] ASC,
	[user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[lesson_types]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lesson_types](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[type_name] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[lessons]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[lessons](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[content] [varchar](255) NULL,
	[created_at] [datetime2](6) NULL,
	[duration] [int] NOT NULL,
	[number_of_attachments] [int] NOT NULL,
	[title] [varchar](255) NOT NULL,
	[updated_at] [datetime2](6) NULL,
	[lesson_type_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[options]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[options](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[is_correct] [bit] NOT NULL,
	[option_text] [varchar](255) NULL,
	[question_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[question_types]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[question_types](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[type_name] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[questions]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[questions](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](6) NULL,
	[question_text] [varchar](255) NOT NULL,
	[updated_at] [datetime2](6) NULL,
	[question_type_id] [bigint] NULL,
	[quiz_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[quizzes]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[quizzes](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](6) NULL,
	[title] [varchar](255) NOT NULL,
	[updated_at] [datetime2](6) NULL,
	[lesson_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[reviews]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[reviews](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[created_at] [datetime2](6) NULL,
	[rating] [numeric](38, 2) NULL,
	[review_text] [varchar](255) NULL,
	[course_id] [bigint] NULL,
	[user_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[roles]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[roles](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[role_name] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sessions]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sessions](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[name_sessions] [varchar](255) NULL,
	[course_id] [bigint] NULL,
	[lesson_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tags]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tags](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[tag_name] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[users]    Script Date: 9/12/2024 9:38:19 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[users](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[bio] [varchar](255) NULL,
	[created_at] [datetime2](6) NULL,
	[email] [varchar](255) NOT NULL,
	[email_verified] [bit] NULL,
	[full_name] [varchar](255) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[profile_picture] [varchar](255) NULL,
	[updated_at] [datetime2](6) NULL,
	[username] [varchar](255) NOT NULL,
	[website] [varchar](255) NULL,
	[role_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[course_levels] ON 

INSERT [dbo].[course_levels] ([id], [level_name]) VALUES (1, N'Level 1')
SET IDENTITY_INSERT [dbo].[course_levels] OFF
GO
SET IDENTITY_INSERT [dbo].[course_tags] ON 

INSERT [dbo].[course_tags] ([id], [course_id], [tag_id]) VALUES (2, 4, 1)
SET IDENTITY_INSERT [dbo].[course_tags] OFF
GO
SET IDENTITY_INSERT [dbo].[courses] ON 

INSERT [dbo].[courses] ([id], [category], [created_at], [description], [language], [number_of_ratings], [number_of_students], [price], [rating], [title], [updated_at], [course_level_id], [created_by]) VALUES (4, N'CNTT', CAST(N'2024-09-05T01:30:00.0000000' AS DateTime2), N'NO', N'English', 1, 100, CAST(250.00 AS Numeric(38, 2)), CAST(123.00 AS Numeric(38, 2)), N'no', CAST(N'2024-10-05T05:00:00.0000000' AS DateTime2), 1, 1)
SET IDENTITY_INSERT [dbo].[courses] OFF
GO
SET IDENTITY_INSERT [dbo].[lesson_types] ON 

INSERT [dbo].[lesson_types] ([id], [type_name]) VALUES (1, N'kkk')
SET IDENTITY_INSERT [dbo].[lesson_types] OFF
GO
SET IDENTITY_INSERT [dbo].[lessons] ON 

INSERT [dbo].[lessons] ([id], [content], [created_at], [duration], [number_of_attachments], [title], [updated_at], [lesson_type_id]) VALUES (1, N'no', CAST(N'2024-09-10T00:00:00.0000000' AS DateTime2), 10, 10, N'no', CAST(N'2024-09-11T00:00:00.0000000' AS DateTime2), 1)
SET IDENTITY_INSERT [dbo].[lessons] OFF
GO
SET IDENTITY_INSERT [dbo].[roles] ON 

INSERT [dbo].[roles] ([id], [role_name]) VALUES (1, N'Admin')
SET IDENTITY_INSERT [dbo].[roles] OFF
GO
SET IDENTITY_INSERT [dbo].[sessions] ON 

INSERT [dbo].[sessions] ([id], [name_sessions], [course_id], [lesson_id]) VALUES (3, NULL, NULL, NULL)
SET IDENTITY_INSERT [dbo].[sessions] OFF
GO
SET IDENTITY_INSERT [dbo].[tags] ON 

INSERT [dbo].[tags] ([id], [tag_name]) VALUES (1, N'Sinh vien')
SET IDENTITY_INSERT [dbo].[tags] OFF
GO
SET IDENTITY_INSERT [dbo].[users] ON 

INSERT [dbo].[users] ([id], [bio], [created_at], [email], [email_verified], [full_name], [password], [profile_picture], [updated_at], [username], [website], [role_id]) VALUES (1, N'abc', CAST(N'2024-09-05T01:30:00.0000000' AS DateTime2), N'truongnnx27@gmail.com', 1, N'XuanTruong', N'123', N'anh.jpg', CAST(N'2024-09-05T01:32:00.0000000' AS DateTime2), N'nguyen', N'.vn', 1)
SET IDENTITY_INSERT [dbo].[users] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UKc1btrbept7r9drbibyiq79ve4]    Script Date: 9/12/2024 9:38:19 AM ******/
ALTER TABLE [dbo].[course_levels] ADD  CONSTRAINT [UKc1btrbept7r9drbibyiq79ve4] UNIQUE NONCLUSTERED 
(
	[level_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK4h1xqaq3279lcaro5bjmf0s12]    Script Date: 9/12/2024 9:38:19 AM ******/
ALTER TABLE [dbo].[lesson_types] ADD  CONSTRAINT [UK4h1xqaq3279lcaro5bjmf0s12] UNIQUE NONCLUSTERED 
(
	[type_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UKjv0wsc3ktfjllpcre9qgpfa7v]    Script Date: 9/12/2024 9:38:19 AM ******/
ALTER TABLE [dbo].[question_types] ADD  CONSTRAINT [UKjv0wsc3ktfjllpcre9qgpfa7v] UNIQUE NONCLUSTERED 
(
	[type_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK716hgxp60ym1lifrdgp67xt5k]    Script Date: 9/12/2024 9:38:19 AM ******/
ALTER TABLE [dbo].[roles] ADD  CONSTRAINT [UK716hgxp60ym1lifrdgp67xt5k] UNIQUE NONCLUSTERED 
(
	[role_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK2c6s9hekidseaj5vbgb3pgy3k]    Script Date: 9/12/2024 9:38:19 AM ******/
ALTER TABLE [dbo].[tags] ADD  CONSTRAINT [UK2c6s9hekidseaj5vbgb3pgy3k] UNIQUE NONCLUSTERED 
(
	[tag_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK6dotkott2kjsp8vw4d0m25fb7]    Script Date: 9/12/2024 9:38:19 AM ******/
ALTER TABLE [dbo].[users] ADD  CONSTRAINT [UK6dotkott2kjsp8vw4d0m25fb7] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UKr43af9ap4edm43mmtq01oddj6]    Script Date: 9/12/2024 9:38:19 AM ******/
ALTER TABLE [dbo].[users] ADD  CONSTRAINT [UKr43af9ap4edm43mmtq01oddj6] UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[comments]  WITH CHECK ADD  CONSTRAINT [FK37jam8u2nwqw9enhv7nqn52e4] FOREIGN KEY([lesson_id])
REFERENCES [dbo].[lessons] ([id])
GO
ALTER TABLE [dbo].[comments] CHECK CONSTRAINT [FK37jam8u2nwqw9enhv7nqn52e4]
GO
ALTER TABLE [dbo].[comments]  WITH CHECK ADD  CONSTRAINT [FK8omq0tc18jd43bu5tjh6jvraq] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[comments] CHECK CONSTRAINT [FK8omq0tc18jd43bu5tjh6jvraq]
GO
ALTER TABLE [dbo].[course_tags]  WITH CHECK ADD  CONSTRAINT [FKjqwlxw962j7q9wdogwnrctc2p] FOREIGN KEY([course_id])
REFERENCES [dbo].[courses] ([id])
GO
ALTER TABLE [dbo].[course_tags] CHECK CONSTRAINT [FKjqwlxw962j7q9wdogwnrctc2p]
GO
ALTER TABLE [dbo].[course_tags]  WITH CHECK ADD  CONSTRAINT [FKle4e0o8293pd96wrrfl77ij42] FOREIGN KEY([tag_id])
REFERENCES [dbo].[tags] ([id])
GO
ALTER TABLE [dbo].[course_tags] CHECK CONSTRAINT [FKle4e0o8293pd96wrrfl77ij42]
GO
ALTER TABLE [dbo].[courses]  WITH CHECK ADD  CONSTRAINT [FK4u40nf46n1nqa5h38sn5g17ac] FOREIGN KEY([created_by])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[courses] CHECK CONSTRAINT [FK4u40nf46n1nqa5h38sn5g17ac]
GO
ALTER TABLE [dbo].[courses]  WITH CHECK ADD  CONSTRAINT [FKmx5gb6xsbkdmycpbi3mkj9l89] FOREIGN KEY([course_level_id])
REFERENCES [dbo].[course_levels] ([id])
GO
ALTER TABLE [dbo].[courses] CHECK CONSTRAINT [FKmx5gb6xsbkdmycpbi3mkj9l89]
GO
ALTER TABLE [dbo].[enrollments]  WITH CHECK ADD  CONSTRAINT [FK3hjx6rcnbmfw368sxigrpfpx0] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[enrollments] CHECK CONSTRAINT [FK3hjx6rcnbmfw368sxigrpfpx0]
GO
ALTER TABLE [dbo].[enrollments]  WITH CHECK ADD  CONSTRAINT [FKho8mcicp4196ebpltdn9wl6co] FOREIGN KEY([course_id])
REFERENCES [dbo].[courses] ([id])
GO
ALTER TABLE [dbo].[enrollments] CHECK CONSTRAINT [FKho8mcicp4196ebpltdn9wl6co]
GO
ALTER TABLE [dbo].[lessons]  WITH CHECK ADD  CONSTRAINT [FK5dpiprwrol596t7165nlu625n] FOREIGN KEY([lesson_type_id])
REFERENCES [dbo].[lesson_types] ([id])
GO
ALTER TABLE [dbo].[lessons] CHECK CONSTRAINT [FK5dpiprwrol596t7165nlu625n]
GO
ALTER TABLE [dbo].[options]  WITH CHECK ADD  CONSTRAINT [FK5bmv46so2y5igt9o9n9w4fh6y] FOREIGN KEY([question_id])
REFERENCES [dbo].[questions] ([id])
GO
ALTER TABLE [dbo].[options] CHECK CONSTRAINT [FK5bmv46so2y5igt9o9n9w4fh6y]
GO
ALTER TABLE [dbo].[questions]  WITH CHECK ADD  CONSTRAINT [FKdyyji0ey6u2yifjdt8ybu3tyw] FOREIGN KEY([question_type_id])
REFERENCES [dbo].[question_types] ([id])
GO
ALTER TABLE [dbo].[questions] CHECK CONSTRAINT [FKdyyji0ey6u2yifjdt8ybu3tyw]
GO
ALTER TABLE [dbo].[questions]  WITH CHECK ADD  CONSTRAINT [FKn3gvco4b0kewxc0bywf1igfms] FOREIGN KEY([quiz_id])
REFERENCES [dbo].[quizzes] ([id])
GO
ALTER TABLE [dbo].[questions] CHECK CONSTRAINT [FKn3gvco4b0kewxc0bywf1igfms]
GO
ALTER TABLE [dbo].[quizzes]  WITH CHECK ADD  CONSTRAINT [FKbdv8uggpsin6pnkx0d80ryqey] FOREIGN KEY([lesson_id])
REFERENCES [dbo].[lessons] ([id])
GO
ALTER TABLE [dbo].[quizzes] CHECK CONSTRAINT [FKbdv8uggpsin6pnkx0d80ryqey]
GO
ALTER TABLE [dbo].[reviews]  WITH CHECK ADD  CONSTRAINT [FKccbfc9u1qimejr5ll7yuxbtqs] FOREIGN KEY([course_id])
REFERENCES [dbo].[courses] ([id])
GO
ALTER TABLE [dbo].[reviews] CHECK CONSTRAINT [FKccbfc9u1qimejr5ll7yuxbtqs]
GO
ALTER TABLE [dbo].[reviews]  WITH CHECK ADD  CONSTRAINT [FKcgy7qjc1r99dp117y9en6lxye] FOREIGN KEY([user_id])
REFERENCES [dbo].[users] ([id])
GO
ALTER TABLE [dbo].[reviews] CHECK CONSTRAINT [FKcgy7qjc1r99dp117y9en6lxye]
GO
ALTER TABLE [dbo].[sessions]  WITH CHECK ADD  CONSTRAINT [FK88i8wngw96iw0fscc0t6uq422] FOREIGN KEY([course_id])
REFERENCES [dbo].[courses] ([id])
GO
ALTER TABLE [dbo].[sessions] CHECK CONSTRAINT [FK88i8wngw96iw0fscc0t6uq422]
GO
ALTER TABLE [dbo].[sessions]  WITH CHECK ADD  CONSTRAINT [FK9sxwc3ijhkvspln98fj4irycq] FOREIGN KEY([lesson_id])
REFERENCES [dbo].[lessons] ([id])
GO
ALTER TABLE [dbo].[sessions] CHECK CONSTRAINT [FK9sxwc3ijhkvspln98fj4irycq]
GO
ALTER TABLE [dbo].[users]  WITH CHECK ADD  CONSTRAINT [FKp56c1712k691lhsyewcssf40f] FOREIGN KEY([role_id])
REFERENCES [dbo].[roles] ([id])
GO
ALTER TABLE [dbo].[users] CHECK CONSTRAINT [FKp56c1712k691lhsyewcssf40f]
GO
USE [master]
GO
ALTER DATABASE [quizztest] SET  READ_WRITE 
GO
