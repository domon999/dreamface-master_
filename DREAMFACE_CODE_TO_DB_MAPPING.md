# Dreamface 项目 - 代码到数据库的完整映射

## 🗺️ 架构全景图

```
┌─────────────────────────────────────────────────────────────────┐
│                        API 端点 (HTTP)                           │
│                                                                   │
│  GET  /system/account/list          (查询列表)                    │
│  GET  /system/account/export        (导出)                       │
│  GET  /system/account/add           (编辑页面)                   │
│  POST /system/account/add           (新增保存)                   │
│  GET  /system/account/edit/{id}     (编辑页面)                   │
│  POST /system/account/edit          (更新保存)                   │
│  POST /system/account/remove/{ids}  (删除)                       │
└────────────────────────────┬────────────────────────────────────┘
                             │
                    (数据绑定/验证)
                             │
┌────────────────────────────▼────────────────────────────────────┐
│         DreamfaceAccountController (@RestController)             │
│                                                                   │
│  ├─ list(DreamfaceAccount dto)                                   │
│  ├─ export(DreamfaceAccount dto)                                 │
│  ├─ add()                                                        │
│  ├─ addSave(DreamfaceAccount dto)                                │
│  ├─ edit(String id)                                              │
│  ├─ editSave(DreamfaceAccount dto)                               │
│  └─ remove(String ids)                                           │
└────────────────────────────┬────────────────────────────────────┘
                             │
                  (@Autowired Service)
                             │
┌────────────────────────────▼────────────────────────────────────┐
│    DreamfaceAccountServiceImpl (implements IDreamfaceAccountService) │
│                                                                   │
│  ├─ selectDreamfaceAccountById(String id)                        │
│  ├─ selectDreamfaceAccountList(DreamfaceAccount dto)             │
│  ├─ insertDreamfaceAccount(DreamfaceAccount dto)                 │
│  ├─ updateDreamfaceAccount(DreamfaceAccount dto)                 │
│  ├─ deleteDreamfaceAccountById(String id)                        │
│  └─ deleteDreamfaceAccountByIds(String[] ids)                    │
└────────────────────────────┬────────────────────────────────────┘
                             │
                  (@Autowired Mapper)
                             │
┌────────────────────────────▼────────────────────────────────────┐
│   DreamfaceAccountMapper (@Mapper)                               │
│                                                                   │
│   执行 DreamfaceAccountMapper.xml 中的 SQL 语句                   │
└────────────────────────────┬────────────────────────────────────┘
                             │
                        (SQL执行)
                             │
┌────────────────────────────▼────────────────────────────────────┐
│                  数据库 (MySQL/MariaDB)                          │
│                                                                   │
│  DATABASE: [your_database]                                       │
│  TABLE: dreamface_account                                        │
└─────────────────────────────────────────────────────────────────┘
```

---

## 📋 代码文件详细对照表

### 1. 实体类 (Entity)

**文件:** `ruoyi-system/src/main/java/com/ruoyi/system/domain/DreamfaceAccount.java`

```java
// 完整代码映射
public class DreamfaceAccount extends BaseEntity {
    
    // ┌─────────────────────────────────────────────┐
    // │ 字段            │ 数据库列     │ 类型          │
    // ├─────────────────────────────────────────────┤
    private String id;        // → id               VARCHAR(64)
    private String token;     // → token            VARCHAR(500)
    private String useragent; // → useragent        VARCHAR(500)
    private String cookie;    // → cookie           VARCHAR(2000)
    private String isUsed;    // → is_used          CHAR(1)
    private Date checktime;   // → checktime        DATETIME
    // └─────────────────────────────────────────────┘
    
    // getter/setter 方法
}

// 注解说明:
@Excel(name = "用户令牌")                    // 支持导出Excel
@JsonFormat(pattern = "yyyy-MM-dd")          // JSON序列化格式
```

---

### 2. Mapper XML 映射文件

**文件:** `ruoyi-system/src/main/resources/mapper/system/DreamfaceAccountMapper.xml`

#### 2.1 结果映射 (ResultMap)

```xml
<resultMap type="DreamfaceAccount" id="DreamfaceAccountResult">
    <result property="id"       column="id" />        
    <result property="token"    column="token" />      
    <result property="useragent" column="useragent" /> 
    <result property="cookie"   column="cookie" />     
    <result property="isUsed"   column="is_used" />    
    <result property="checktime" column="checktime" />
</resultMap>

映射关系:
Java属性名        →    数据库列名
────────────────────────────────
id                →    id
token             →    token
useragent         →    useragent
cookie            →    cookie
isUsed            →    is_used          (★ 注意命名转换)
checktime         →    checktime
```

#### 2.2 查询语句映射

```xml
<!-- 定义基础查询SQL -->
<sql id="selectDreamfaceAccountVo">
    select id, token, useragent, cookie, is_used, checktime 
    from dreamface_account
</sql>

<!-- 查询列表 (支持动态条件) -->
<select id="selectDreamfaceAccountList" 
        parameterType="DreamfaceAccount" 
        resultMap="DreamfaceAccountResult">
    <include refid="selectDreamfaceAccountVo"/>
    <where>
        <!-- 条件1: token过滤 -->
        <if test="token != null and token != ''">
            and token = #{token}
        </if>
        <!-- 条件2: useragent过滤 -->
        <if test="useragent != null and useragent != ''">
            and useragent = #{useragent}
        </if>
        <!-- 条件3: cookie过滤 -->
        <if test="cookie != null and cookie != ''">
            and cookie = #{cookie}
        </if>
        <!-- 条件4: is_used过滤 -->
        <if test="isUsed != null and isUsed != ''">
            and is_used = #{isUsed}
        </if>
        <!-- 条件5: checktime过滤 -->
        <if test="checktime != null">
            and checktime = #{checktime}
        </if>
    </where>
</select>

SQL生成示例:
- 查所有:    SELECT ... FROM dreamface_account
- 按token:  SELECT ... FROM dreamface_account WHERE token = ?
- 多条件:   SELECT ... FROM dreamface_account 
            WHERE token = ? AND is_used = ? AND checktime = ?
```

#### 2.3 单条查询

```xml
<select id="selectDreamfaceAccountById" 
        parameterType="String" 
        resultMap="DreamfaceAccountResult">
    <include refid="selectDreamfaceAccountVo"/>
    where id = #{id}
</select>

SQL: SELECT id, token, useragent, cookie, is_used, checktime 
     FROM dreamface_account 
     WHERE id = ?
```

#### 2.4 插入操作

```xml
<insert id="insertDreamfaceAccount" 
        parameterType="DreamfaceAccount" 
        useGeneratedKeys="true" 
        keyProperty="id">
    insert into dreamface_account
    <trim prefix="(" suffix=")" suffixOverrides=",">
        <if test="token != null">token,</if>
        <if test="useragent != null">useragent,</if>
        <if test="cookie != null">cookie,</if>
        <if test="isUsed != null and isUsed != ''">is_used,</if>
        <if test="checktime != null">checktime,</if>
    </trim>
    <trim prefix="values (" suffix=")" suffixOverrides=",">
        <if test="token != null">#{token},</if>
        <if test="useragent != null">#{useragent},</if>
        <if test="cookie != null">#{cookie},</if>
        <if test="isUsed != null and isUsed != ''">#{isUsed},</if>
        <if test="checktime != null">#{checktime},</if>
    </trim>
</insert>

SQL生成示例:
INSERT INTO dreamface_account (token, useragent, cookie, is_used, checktime)
VALUES (?, ?, ?, ?, ?)

重要: useGeneratedKeys="true" 表示 id 由数据库生成
```

#### 2.5 更新操作

```xml
<update id="updateDreamfaceAccount" parameterType="DreamfaceAccount">
    update dreamface_account
    <trim prefix="SET" suffixOverrides=",">
        <if test="token != null">token = #{token},</if>
        <if test="useragent != null">useragent = #{useragent},</if>
        <if test="cookie != null">cookie = #{cookie},</if>
        <if test="isUsed != null and isUsed != ''">is_used = #{isUsed},</if>
        <if test="checktime != null">checktime = #{checktime},</if>
    </trim>
    where id = #{id}
</update>

SQL生成示例:
UPDATE dreamface_account 
SET token = ?, useragent = ?, is_used = ? 
WHERE id = ?
```

#### 2.6 删除操作

```xml
<!-- 单条删除 -->
<delete id="deleteDreamfaceAccountById" parameterType="String">
    delete from dreamface_account where id = #{id}
</delete>

SQL: DELETE FROM dreamface_account WHERE id = ?

<!-- 批量删除 -->
<delete id="deleteDreamfaceAccountByIds" parameterType="String">
    delete from dreamface_account where id in 
    <foreach item="id" collection="array" open="(" separator="," close=")">
        #{id}
    </foreach>
</delete>

SQL生成示例:
DELETE FROM dreamface_account WHERE id IN (?, ?, ?)
```

---

### 3. Mapper 接口

**文件:** `ruoyi-system/src/main/java/com/ruoyi/system/mapper/DreamfaceAccountMapper.java`

```java
@Mapper
public interface DreamfaceAccountMapper {
    
    /**
     * 查询单个账户
     * @param id 账户ID
     * @return 账户对象
     */
    public DreamfaceAccount selectDreamfaceAccountById(String id);
    
    /**
     * 查询账户列表
     * @param dreamfaceAccount 查询条件
     * @return 账户列表
     */
    public List<DreamfaceAccount> selectDreamfaceAccountList(DreamfaceAccount dreamfaceAccount);
    
    /**
     * 新增账户
     * @param dreamfaceAccount 账户对象
     * @return 影响行数
     */
    public int insertDreamfaceAccount(DreamfaceAccount dreamfaceAccount);
    
    /**
     * 修改账户
     * @param dreamfaceAccount 账户对象
     * @return 影响行数
     */
    public int updateDreamfaceAccount(DreamfaceAccount dreamfaceAccount);
    
    /**
     * 删除单个账户
     * @param id 账户ID
     * @return 影响行数
     */
    public int deleteDreamfaceAccountById(String id);
    
    /**
     * 批量删除账户
     * @param ids 账户ID数组
     * @return 影响行数
     */
    public int deleteDreamfaceAccountByIds(String[] ids);
}

// XML中会自动扫描这些方法的SQL实现
// 方法名 = XML中的 id 属性值
```

---

### 4. Service 接口

**文件:** `ruoyi-system/src/main/java/com/ruoyi/system/service/IDreamfaceAccountService.java`

```java
public interface IDreamfaceAccountService {
    
    public DreamfaceAccount selectDreamfaceAccountById(String id);
    
    public List<DreamfaceAccount> selectDreamfaceAccountList(DreamfaceAccount dreamfaceAccount);
    
    public int insertDreamfaceAccount(DreamfaceAccount dreamfaceAccount);
    
    public int updateDreamfaceAccount(DreamfaceAccount dreamfaceAccount);
    
    public int deleteDreamfaceAccountById(String id);
    
    public int deleteDreamfaceAccountByIds(String ids);
}
```

---

### 5. Service 实现

**文件:** `ruoyi-system/src/main/java/com/ruoyi/system/service/impl/DreamfaceAccountServiceImpl.java`

```java
@Service
public class DreamfaceAccountServiceImpl implements IDreamfaceAccountService {
    
    @Autowired
    private DreamfaceAccountMapper dreamfaceAccountMapper;
    
    @Override
    public DreamfaceAccount selectDreamfaceAccountById(String id) {
        return dreamfaceAccountMapper.selectDreamfaceAccountById(id);
    }
    
    @Override
    public List<DreamfaceAccount> selectDreamfaceAccountList(DreamfaceAccount dreamfaceAccount) {
        return dreamfaceAccountMapper.selectDreamfaceAccountList(dreamfaceAccount);
    }
    
    @Override
    public int insertDreamfaceAccount(DreamfaceAccount dreamfaceAccount) {
        return dreamfaceAccountMapper.insertDreamfaceAccount(dreamfaceAccount);
    }
    
    @Override
    public int updateDreamfaceAccount(DreamfaceAccount dreamfaceAccount) {
        return dreamfaceAccountMapper.updateDreamfaceAccount(dreamfaceAccount);
    }
    
    @Override
    public int deleteDreamfaceAccountById(String id) {
        return dreamfaceAccountMapper.deleteDreamfaceAccountById(id);
    }
    
    @Override
    public int deleteDreamfaceAccountByIds(String ids) {
        return dreamfaceAccountMapper.deleteDreamfaceAccountByIds(Convert.toStrArray(ids));
    }
}
```

---

### 6. Controller 控制器

**文件:** `ruoyi-admin/src/main/java/com/ruoyi/web/controller/system/DreamfaceAccountController.java`

```java
@RestController
@RequestMapping("/system/account")
public class DreamfaceAccountController extends BaseController {
    
    @Autowired
    private IDreamfaceAccountService dreamfaceAccountService;
    
    // 查询列表 - GET /system/account/list
    @PostMapping("/list")
    public TableDataInfo list(DreamfaceAccount dreamfaceAccount) {
        startPage();
        List<DreamfaceAccount> list = dreamfaceAccountService.selectDreamfaceAccountList(dreamfaceAccount);
        return getDataTable(list);
    }
    
    // 导出Excel
    @PostMapping("/export")
    public AjaxResult export(DreamfaceAccount dreamfaceAccount) {
        List<DreamfaceAccount> list = dreamfaceAccountService.selectDreamfaceAccountList(dreamfaceAccount);
        ExcelUtil<DreamfaceAccount> util = new ExcelUtil<DreamfaceAccount>(DreamfaceAccount.class);
        return util.exportExcel(list, "account");
    }
    
    // 新增页面 - GET /system/account/add
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        return "system/account/add";
    }
    
    // 新增保存 - POST /system/account/add
    @PostMapping("/add")
    public AjaxResult addSave(DreamfaceAccount dreamfaceAccount) {
        return toAjax(dreamfaceAccountService.insertDreamfaceAccount(dreamfaceAccount));
    }
    
    // 编辑页面 - GET /system/account/edit/{id}
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        DreamfaceAccount dreamfaceAccount = dreamfaceAccountService.selectDreamfaceAccountById(id);
        mmap.put("dreamfaceAccount", dreamfaceAccount);
        return "system/account/edit";
    }
    
    // 编辑保存 - POST /system/account/edit
    @PostMapping("/edit")
    public AjaxResult editSave(DreamfaceAccount dreamfaceAccount) {
        return toAjax(dreamfaceAccountService.updateDreamfaceAccount(dreamfaceAccount));
    }
    
    // 删除 - POST /system/account/remove/{ids}
    @PostMapping("/remove/{ids}")
    public AjaxResult remove(@PathVariable String ids) {
        return toAjax(dreamfaceAccountService.deleteDreamfaceAccountByIds(ids));
    }
}

请求/响应示例:
┌─ 查询列表 ─────────────────────┐
│ GET /system/account/list        │
│ 响应: {                         │
│   "total": 10,                  │
│   "rows": [{...}, {...}],       │
│   "code": 0                     │
│ }                               │
└────────────────────────────────┘

┌─ 新增账户 ─────────────────────┐
│ POST /system/account/add        │
│ 请求体:                         │
│ {                               │
│   "token": "xxx",               │
│   "useragent": "Mozilla...",    │
│   "cookie": "sess=...",         │
│   "isUsed": "1",                │
│   "checktime": "2024-01-13"     │
│ }                               │
│ 响应: {                         │
│   "code": 0,                    │
│   "msg": "新增成功"              │
│ }                               │
└────────────────────────────────┘
```

---

## 🔄 一个完整操作流程示例

### 场景: 新增一个Dreamface账户

```
1. 用户在前端填写表单
   ├─ Token: abc123xyz
   ├─ UserAgent: Mozilla/5.0 (Windows NT 10.0; Win64; x64)
   ├─ Cookie: PHPSESSID=xyz; Path=/
   ├─ IsUsed: 1
   └─ CheckTime: 2024-01-13

2. 前端发送HTTP请求
   POST /system/account/add
   Content-Type: application/json
   {
     "token": "abc123xyz",
     "useragent": "Mozilla/5.0...",
     "cookie": "PHPSESSID=xyz...",
     "isUsed": "1",
     "checktime": "2024-01-13"
   }

3. 后端处理流程
   ↓ (请求达到)
   DreamfaceAccountController.addSave()
   ├─ 接收 DreamfaceAccount 对象
   ├─ 参数校验
   └─ 调用 service.insertDreamfaceAccount()
   
   ↓ (传递到Service)
   DreamfaceAccountServiceImpl.insertDreamfaceAccount()
   ├─ 业务逻辑处理
   └─ 调用 mapper.insertDreamfaceAccount()
   
   ↓ (传递到Mapper)
   DreamfaceAccountMapper.insertDreamfaceAccount()
   ├─ 执行 DreamfaceAccountMapper.xml 中的 insert 语句
   └─ 生成动态SQL
   
   ↓ (执行SQL)
   INSERT INTO dreamface_account 
   (id, token, useragent, cookie, is_used, checktime)
   VALUES 
   ('550e8400-e29b-41d4-a716-446655440001', 
    'abc123xyz', 
    'Mozilla/5.0...', 
    'PHPSESSID=xyz...', 
    '1', 
    '2024-01-13')

4. 数据库操作
   ├─ 自增ID: 550e8400-e29b-41d4-a716-446655440001
   ├─ 插入1行
   └─ 返回影响行数: 1

5. 返回响应
   ↑ (返回行数)
   Mapper 返回 1 到 Service
   ↑
   Service 传递到 Controller
   ↑
   Controller 构建 AjaxResult
   {
     "code": 0,
     "msg": "新增成功",
     "data": 1
   }

6. 前端收到响应
   ├─ 显示成功提示
   ├─ 刷新列表
   └─ 清空表单
```

---

## 📊 数据流向总结表

```
操作类型    │ 调用链条                              │ 数据库操作
─────────────┼──────────────────────────────────────┼──────────────
查询列表    │ Controller → Service → Mapper → XML   │ SELECT ... WHERE ...
查询单条    │ Controller → Service → Mapper → XML   │ SELECT ... WHERE id = ?
新增        │ Controller → Service → Mapper → XML   │ INSERT INTO ...
编辑/更新   │ Controller → Service → Mapper → XML   │ UPDATE ... WHERE id = ?
删除单条    │ Controller → Service → Mapper → XML   │ DELETE WHERE id = ?
批量删除    │ Controller → Service → Mapper → XML   │ DELETE WHERE id IN (...)
导出Excel   │ Controller → Service + ExcelUtil      │ SELECT全表
```

---

**代码到数据库完整映射版本:** v1.0  
**最后更新:** 2026-01-13
