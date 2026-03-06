package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DreamfaceAccountMapper;
import com.ruoyi.system.domain.DreamfaceAccount;
import com.ruoyi.system.service.IDreamfaceAccountService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-13
 */
@Service
public class DreamfaceAccountServiceImpl implements IDreamfaceAccountService 
{
    @Autowired
    private DreamfaceAccountMapper dreamfaceAccountMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public DreamfaceAccount selectDreamfaceAccountById(String id)
    {
        return dreamfaceAccountMapper.selectDreamfaceAccountById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param dreamfaceAccount 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<DreamfaceAccount> selectDreamfaceAccountList(DreamfaceAccount dreamfaceAccount)
    {
        return dreamfaceAccountMapper.selectDreamfaceAccountList(dreamfaceAccount);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param dreamfaceAccount 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertDreamfaceAccount(DreamfaceAccount dreamfaceAccount)
    {
        return dreamfaceAccountMapper.insertDreamfaceAccount(dreamfaceAccount);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param dreamfaceAccount 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateDreamfaceAccount(DreamfaceAccount dreamfaceAccount)
    {
        return dreamfaceAccountMapper.updateDreamfaceAccount(dreamfaceAccount);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDreamfaceAccountByIds(String ids)
    {
        return dreamfaceAccountMapper.deleteDreamfaceAccountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteDreamfaceAccountById(String id)
    {
        return dreamfaceAccountMapper.deleteDreamfaceAccountById(id);
    }
}
