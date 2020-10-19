package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TOrgEnterprise;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.module.system.dto.OrgEnterpriseInputDTO;
import co.synext.module.system.dto.OrgEnterpriseUpdateDTO;
import co.synext.module.system.dto.OrgEnterpriseQueryDTO;
import co.synext.module.system.vo.OrgEnterpriseVO;
import co.synext.module.system.service.IOrgEnterpriseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 *  企业信息表,企业实际是t_org部门,orgType=1.给部门创建一个账号,作为主管,用于登录.附件关联附件表t_attachment 控制器
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Api(tags = " 企业信息表,企业实际是t_org部门,orgType=1.给部门创建一个账号,作为主管,用于登录.附件关联附件表t_attachment接口")
@RestController
@RequestMapping("/company/api/kjj/orgenterprise")
@AllArgsConstructor
public class OrgEnterpriseController {
    private final IOrgEnterpriseService orgEnterpriseService;

    @ApiOperation(value = "列表接口")
    @PostMapping("/list")
    public ReturnDatas<Page<TOrgEnterprise>> getPage(@RequestBody OrgEnterpriseQueryDTO orgEnterpriseQueryDTO) {
        return orgEnterpriseService.page(orgEnterpriseQueryDTO);
    }

    @ApiOperation(value = "保存接口")
    @PostMapping("/save")
    public ReturnDatas<Boolean> save(@Valid @RequestBody OrgEnterpriseInputDTO orgEnterpriseInputDTO) {
        return orgEnterpriseService.save(orgEnterpriseInputDTO);
    }

    @ApiOperation(value = "更新接口")
    @PostMapping("/update")
    public ReturnDatas<Boolean> updateById(@Valid @RequestBody OrgEnterpriseUpdateDTO orgEnterpriseUpdateDTO) {
        return orgEnterpriseService.update(orgEnterpriseUpdateDTO);
    }

    @ApiOperation(value = "详情接口")
    @PostMapping("/look/{id}")
    public ReturnDatas<TOrgEnterprise> getById(@PathVariable String id) {
        return orgEnterpriseService.findById(id);
    }

    @ApiOperation(value = "删除接口")
    @PostMapping("/delete")
    public ReturnDatas<Boolean> remove(@RequestBody List<String> ids) {
        return orgEnterpriseService.batchDelete(ids);
    }

}