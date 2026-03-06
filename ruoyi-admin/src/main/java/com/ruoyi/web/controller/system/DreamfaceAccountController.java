package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.DreamfaceAccount;
import com.ruoyi.system.service.IDreamfaceAccountService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2026-01-13
 */
@Controller
@RequestMapping("/system/account")
public class DreamfaceAccountController extends BaseController
{
    private String prefix = "system/account";

    @Autowired
    private IDreamfaceAccountService dreamfaceAccountService;

    @RequiresPermissions("system:account:view")
    @GetMapping()
    public String account()
    {
        return prefix + "/account";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:account:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DreamfaceAccount dreamfaceAccount)
    {
        startPage();
        List<DreamfaceAccount> list = dreamfaceAccountService.selectDreamfaceAccountList(dreamfaceAccount);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:account:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DreamfaceAccount dreamfaceAccount)
    {
        List<DreamfaceAccount> list = dreamfaceAccountService.selectDreamfaceAccountList(dreamfaceAccount);
        ExcelUtil<DreamfaceAccount> util = new ExcelUtil<DreamfaceAccount>(DreamfaceAccount.class);
        return util.exportExcel(list, "【请填写功能名称】数据");
    }

    /**
     * 新增【请填写功能名称】
     */
    @RequiresPermissions("system:account:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("system:account:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DreamfaceAccount dreamfaceAccount)
    {
        return toAjax(dreamfaceAccountService.insertDreamfaceAccount(dreamfaceAccount));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("system:account:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        DreamfaceAccount dreamfaceAccount = dreamfaceAccountService.selectDreamfaceAccountById(id);
        mmap.put("dreamfaceAccount", dreamfaceAccount);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:account:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DreamfaceAccount dreamfaceAccount)
    {
        return toAjax(dreamfaceAccountService.updateDreamfaceAccount(dreamfaceAccount));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:account:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dreamfaceAccountService.deleteDreamfaceAccountByIds(ids));
    }
}
