package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.DreamfaceAccount;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2026-01-13
 */
public interface IDreamfaceAccountService 
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
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteDreamfaceAccountByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteDreamfaceAccountById(String id);
}
