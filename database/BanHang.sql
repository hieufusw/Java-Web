USE [master]
GO
/****** Object:  Database [BanHang]    Script Date: 30/10/2018 20:31:20 PM ******/
CREATE DATABASE [BanHang]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BanHang', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\BanHang.mdf' , SIZE = 3136KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'BanHang_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.MSSQLSERVER\MSSQL\DATA\BanHang_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [BanHang] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BanHang].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BanHang] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BanHang] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BanHang] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BanHang] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BanHang] SET ARITHABORT OFF 
GO
ALTER DATABASE [BanHang] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BanHang] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [BanHang] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BanHang] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BanHang] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BanHang] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BanHang] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BanHang] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BanHang] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BanHang] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BanHang] SET  ENABLE_BROKER 
GO
ALTER DATABASE [BanHang] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BanHang] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BanHang] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BanHang] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BanHang] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BanHang] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BanHang] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BanHang] SET RECOVERY FULL 
GO
ALTER DATABASE [BanHang] SET  MULTI_USER 
GO
ALTER DATABASE [BanHang] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BanHang] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BanHang] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BanHang] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
EXEC sys.sp_db_vardecimal_storage_format N'BanHang', N'ON'
GO
USE [BanHang]
GO
/****** Object:  Table [dbo].[Manufacturer]    Script Date: 30/10/2018 20:31:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Manufacturer](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](30) NULL,
	[website] [varchar](100) NULL,
	[status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 30/10/2018 20:31:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[orderid] [int] NOT NULL,
	[productid] [int] NOT NULL,
	[quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[orderid] ASC,
	[productid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Orders]    Script Date: 30/10/2018 20:31:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Orders](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[date] [varchar](100) NULL,
	[ordername] [nvarchar](250) NULL,
	[address] [nvarchar](250) NULL,
	[phone] [varchar](30) NULL,
	[total] [int] NULL,
	[status] [int] NULL,
	[customerid] [int] NULL,
 CONSTRAINT [PK__Orders__3213E83FF77F8D1E] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Product]    Script Date: 30/10/2018 20:31:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[quantity] [int] NULL,
	[price] [int] NULL,
	[picture] [varchar](200) NULL,
	[description] [nvarchar](max) NULL,
	[status] [int] NULL,
	[manufacturerid] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Role]    Script Date: 30/10/2018 20:31:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](30) NULL,
	[name] [varchar](32) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Users]    Script Date: 30/10/2018 20:31:20 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](30) NULL,
	[password] [varchar](32) NULL,
	[status] [int] NULL,
	[address] [nvarchar](250) NULL,
	[phone] [varchar](20) NULL,
	[fullname] [nvarchar](250) NULL,
	[roleid] [int] NULL,
	[isdelete] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Manufacturer] ON 

INSERT [dbo].[Manufacturer] ([id], [name], [website], [status]) VALUES (1, N'Apple', N'apple', 1)
INSERT [dbo].[Manufacturer] ([id], [name], [website], [status]) VALUES (2, N'Nokia', N'nokia.com', 1)
INSERT [dbo].[Manufacturer] ([id], [name], [website], [status]) VALUES (3, N'Samsung', N'samsung.com', 1)
INSERT [dbo].[Manufacturer] ([id], [name], [website], [status]) VALUES (4, N'Xiaomi', N'xiaomi.com', 1)
SET IDENTITY_INSERT [dbo].[Manufacturer] OFF
--INSERT [dbo].[OrderDetail] ([orderid], [productid], [quantity]) VALUES (1, 4, 3)
--INSERT [dbo].[OrderDetail] ([orderid], [productid], [quantity]) VALUES (2, 5, 3)
SET IDENTITY_INSERT [dbo].[Orders] ON 

--INSERT [dbo].[Orders] ([id], [date], [ordername], [address], [phone], [total], [status], [customerid]) VALUES (1, N'30/10/2018', N'nguyen vu hieu', N'hanoi', N'0983450322', 604, 1, 3)
SET IDENTITY_INSERT [dbo].[Orders] OFF
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([id], [name], [quantity], [price], [picture], [description], [status], [manufacturerid]) VALUES (1, N'IPhone 8 Plus', 0, 814, N'/product/sp5.jpg', N'New Product', 1, 1)
INSERT [dbo].[Product] ([id], [name], [quantity], [price], [picture], [description], [status], [manufacturerid]) VALUES (2, N'Nokia 7 Plus', 10, 300, N'/product/Nokia7P.png', N'New Product', 1, 2)
INSERT [dbo].[Product] ([id], [name], [quantity], [price], [picture], [description], [status], [manufacturerid]) VALUES (3, N'Samsung S8', 5, 304, N'/product/sp2.jpg', N'New Product', 1, 3)
INSERT [dbo].[Product] ([id], [name], [quantity], [price], [picture], [description], [status], [manufacturerid]) VALUES (4, N'Xiaomi 1', 10, 206, N'/product/Xiaomi.jpeg', N'New Product', 1, 4)
INSERT [dbo].[Product] ([id], [name], [quantity], [price], [picture], [description], [status], [manufacturerid]) VALUES (5, N'IPhone XS Max', 10, 1486, N'/product/sp1.jpg', N'New Product', 1, 1)
INSERT [dbo].[Product] ([id], [name], [quantity], [price], [picture], [description], [status], [manufacturerid]) VALUES (6, N'Samsung Galaxy Note 9', 10, 984, N'/product/note9.jpg', N'New Product', 1, 3)
SET IDENTITY_INSERT [dbo].[Product] OFF
SET IDENTITY_INSERT [dbo].[Role] ON 

INSERT [dbo].[Role] ([id], [code], [name]) VALUES (1, N'admin', N'Quan tri')
INSERT [dbo].[Role] ([id], [code], [name]) VALUES (2, N'user', N'Nguoi dung')
SET IDENTITY_INSERT [dbo].[Role] OFF
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([id], [username], [password], [status], [address], [phone], [fullname], [roleid], [isdelete]) VALUES (1, N'admin', N'admin', 1, NULL, NULL, N'admin', 1, 0)
INSERT [dbo].[Users] ([id], [username], [password], [status], [address], [phone], [fullname], [roleid], [isdelete]) VALUES (2, N'hieunv', N'123456', 1, N'hanoi', N'0983450322', N'nguyen vu hieu', 2, 0)

SET IDENTITY_INSERT [dbo].[Users] OFF
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK__OrderDeta__order__24927208] FOREIGN KEY([orderid])
REFERENCES [dbo].[Orders] ([id])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK__OrderDeta__order__24927208]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([productid])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK__Orders__customer__21B6055D] FOREIGN KEY([customerid])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK__Orders__customer__21B6055D]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([manufacturerid])
REFERENCES [dbo].[Manufacturer] ([id])
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD FOREIGN KEY([roleid])
REFERENCES [dbo].[Role] ([id])
GO
USE [master]
GO
ALTER DATABASE [BanHang] SET  READ_WRITE 
GO
