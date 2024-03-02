--OA数据库端执行，注意更改对应数据库名
USE [dsecology]
GO

/****** Object:  Table [dbo].[oa_erp]    Script Date: 08/09/2022 13:38:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[oa_erp](
	[id] [nvarchar](50) NOT NULL,
	[status] [nvarchar](10) NULL,
	[time] [datetime] NULL,
	[changetime] [datetime] NULL,
	[changelog] [nvarchar](max) NULL,
	[sourcetable] [nvarchar](50) NULL,
	[sourceid] [nvarchar](50) NULL,
	[sourcejson] [nvarchar](max) NULL,
	[targettable] [nvarchar](50) NULL,
	[targetid] [nvarchar](50) NULL,
	[targetjson] [nvarchar](max) NULL,
 CONSTRAINT [PK_oa-erp] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

ALTER TABLE [dbo].[oa_erp] ADD  CONSTRAINT [DF_oa-erp_id]  DEFAULT ((0)) FOR [id]
GO


