-- ===========================================================================================
-- Dreamface 项目数据库脚本
-- ===========================================================================================
-- 生成日期: 2026-01-13
-- 说明: 该脚本包含Dreamface项目所需的所有数据库表结构
-- ===========================================================================================

-- ===========================================================================================
-- 1、Dreamface账户表 (dreamface_account)
-- ===========================================================================================
-- 说明: 用于存储梦幻脸部识别系统的账户信息
-- ===========================================================================================

DROP TABLE IF EXISTS dreamface_account;

CREATE TABLE dreamface_account (
  id                VARCHAR(64)      NOT NULL                        COMMENT '主键ID（唯一标识）',
  token             VARCHAR(500)     DEFAULT NULL                    COMMENT '用户令牌（API令牌/访问令牌）',
  useragent         VARCHAR(500)     DEFAULT NULL                    COMMENT '用户代理（浏览器/设备信息）',
  cookie            VARCHAR(2000)    DEFAULT NULL                    COMMENT '用户Cookie信息（会话信息）',
  is_used           CHAR(1)          DEFAULT '0'                     COMMENT '使用状态（1-使用 2-不使用，0-未知）',
  checktime         DATETIME         DEFAULT NULL                    COMMENT '检测时间（账户信息最后验证时间）',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Dreamface账户表';

-- ===========================================================================================
-- 2、初始化数据
-- ===========================================================================================
-- 暂无初始化数据，请根据需要通过应用程序插入真实数据

-- ===========================================================================================
-- 索引定义
-- ===========================================================================================
-- 为提高查询性能，可根据实际业务需求添加以下索引

-- 为token字段添加索引（用于快速查找账户）
-- ALTER TABLE dreamface_account ADD INDEX idx_token (token(100));

-- 为is_used字段添加索引（用于快速过滤可用账户）
-- ALTER TABLE dreamface_account ADD INDEX idx_is_used (is_used);

-- 为checktime字段添加索引（用于按时间查询）
-- ALTER TABLE dreamface_account ADD INDEX idx_checktime (checktime);

-- ===========================================================================================
-- 说明文档
-- ===========================================================================================
-- 
-- 表名: dreamface_account (Dreamface账户表)
-- 用途: 存储梦幻脸部识别系统（Dreamface）的用户账户信息
-- 
-- 字段说明:
-- ├─ id: 主键，唯一标识每条账户记录
-- │  ├─ 类型: VARCHAR(64)
-- │  ├─ 约束: NOT NULL
-- │  └─ 说明: 通常由应用生成的唯一ID（如UUID）
-- │
-- ├─ token: 用户令牌
-- │  ├─ 类型: VARCHAR(500)
-- │  └─ 说明: API或应用访问令牌，用于认证和授权
-- │
-- ├─ useragent: 用户代理信息
-- │  ├─ 类型: VARCHAR(500)
-- │  └─ 说明: HTTP User-Agent头信息，包含浏览器和设备信息
-- │
-- ├─ cookie: Cookie信息
-- │  ├─ 类型: VARCHAR(2000)
-- │  └─ 说明: 会话Cookie，用于记录用户会话状态
-- │
-- ├─ is_used: 使用状态
-- │  ├─ 类型: CHAR(1)
-- │  ├─ 值域: '1'(使用), '2'(不使用), '0'(未知/默认)
-- │  └─ 说明: 账户是否处于活跃使用状态
-- │
-- └─ checktime: 检测时间
--    ├─ 类型: DATETIME
--    └─ 说明: 账户信息最后验证/检测的时间戳
--
-- ===========================================================================================
-- 应用程序交互说明
-- ===========================================================================================
--
-- Java实体类: com.ruoyi.system.domain.DreamfaceAccount
-- Mapper类: com.ruoyi.system.mapper.DreamfaceAccountMapper
-- Service类: com.ruoyi.system.service.IDreamfaceAccountService
-- Controller类: com.ruoyi.web.controller.system.DreamfaceAccountController
--
-- 主要操作:
-- ├─ selectDreamfaceAccountById(id) - 按ID查询单个账户
-- ├─ selectDreamfaceAccountList(dreamfaceAccount) - 查询账户列表（支持条件过滤）
-- ├─ insertDreamfaceAccount(dreamfaceAccount) - 插入新账户
-- ├─ updateDreamfaceAccount(dreamfaceAccount) - 更新账户信息
-- ├─ deleteDreamfaceAccountById(id) - 删除单个账户
-- └─ deleteDreamfaceAccountByIds(ids) - 批量删除账户
--
-- ===========================================================================================
-- 查询示例
-- ===========================================================================================
--
-- 1. 查询所有账户
-- SELECT * FROM dreamface_account;
--
-- 2. 查询所有使用中的账户
-- SELECT * FROM dreamface_account WHERE is_used = '1';
--
-- 3. 查询特定token的账户
-- SELECT * FROM dreamface_account WHERE token = 'your_token_here';
--
-- 4. 查询最近检测的账户（近7天）
-- SELECT * FROM dreamface_account WHERE checktime >= DATE_SUB(NOW(), INTERVAL 7 DAY);
--
-- 5. 统计使用中的账户数
-- SELECT COUNT(*) as active_count FROM dreamface_account WHERE is_used = '1';
--
-- ===========================================================================================
-- 维护建议
-- ===========================================================================================
--
-- 1. 定期清理过期的账户记录（可通过checktime字段判断）
-- 2. 为高频查询字段添加索引，如token、is_used、checktime
-- 3. 考虑添加create_time和update_time字段用于记录数据变更时间
-- 4. 定期备份数据库，特别是生产环境
-- 5. 对cookie字段中的敏感信息进行加密存储
--
-- ===========================================================================================
