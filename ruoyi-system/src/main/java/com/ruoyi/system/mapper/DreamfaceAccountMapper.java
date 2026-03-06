package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DreamfaceAccount;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-13
 */
public interface DreamfaceAccountMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public DreamfaceAccount selectDreamfaceAccountById(String id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param dreamfaceAccount 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<DreamfaceAccount> selectDreamfaceAccountList(DreamfaceAccount dreamfaceAccount);

    /**
     * 新增【请填写功能名称】
     * 
     * @param dreamfaceAccount 【请填写功能名称】
     * @return 结果
     */
    public int insertDreamfaceAccount(DreamfaceAccount dreamfaceAccount);

    /**
     * 修改【请填写功能名称】
     * 
     * @param dreamfaceAccount 【请填写功能名称】
     * @return 结果
     */
    public int updateDreamfaceAccount(DreamfaceAccount dreamfaceAccount);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteDreamfaceAccountById(String id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDreamfaceAccountByIds(String[] ids);
}
