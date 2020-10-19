package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TSendmessageTemplate;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.module.system.dto.SendmessageTemplateInputDTO;
import co.synext.module.system.dto.SendmessageTemplateUpdateDTO;
import co.synext.module.system.dto.SendmessageTemplateQueryDTO;
import co.synext.module.system.vo.SendmessageTemplateVO;
import co.synext.module.system.service.ISendmessageTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/**
 * <p>
 * 消息模板表 控制器
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-11
 */
@Api(tags = "消息模板表接口")
@RestController
@RequestMapping("/system/api/kjj/sendmessageTemplate")
@AllArgsConstructor
public class SendmessageTemplateController {
    private final ISendmessageTemplateService sendmessageTemplateService;

    @ApiOperation(value = "消息模板表列表接口")
    @GetMapping("/list")
    public ReturnDatas<Page<TSendmessageTemplate>> getPage(SendmessageTemplateQueryDTO sendmessageTemplateQueryDTO) {
        return sendmessageTemplateService.page(sendmessageTemplateQueryDTO);
    }

    @ApiOperation(value = "消息模板表保存接口")
    @PostMapping
    public ReturnDatas<Boolean> save(@Valid SendmessageTemplateInputDTO sendmessageTemplateInputDTO) {
        return sendmessageTemplateService.save(sendmessageTemplateInputDTO);
    }

    @ApiOperation(value = "消息模板表更新接口")
    @PostMapping("/update")
    public ReturnDatas<Boolean> updateById(@Valid SendmessageTemplateUpdateDTO sendmessageTemplateUpdateDTO) {
        return sendmessageTemplateService.update(sendmessageTemplateUpdateDTO);
    }

    @ApiOperation(value = "消息模板表详情接口")
    @GetMapping("/{id}")
    public ReturnDatas<TSendmessageTemplate> getById(@PathVariable String id) {
        return sendmessageTemplateService.findById(id);
    }

    @ApiOperation(value = "消息模板表删除接口")
    @DeleteMapping("/{id}")
    public ReturnDatas<Boolean> removeById(@PathVariable String id) {
        return sendmessageTemplateService.deleteById(id);
    }


}