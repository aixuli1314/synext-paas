package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TAttachment;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.module.system.dto.AttachmentInputDTO;
import co.synext.module.system.dto.AttachmentUpdateDTO;
import co.synext.module.system.dto.AttachmentQueryDTO;
import co.synext.module.system.vo.AttachmentVO;
import co.synext.module.system.service.IAttachmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/**
 * <p>
 * 专家专业 信息表 控制器
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-11
 */
@Api(tags = "专家专业 信息表接口")
@RestController
@RequestMapping("/system/api/kjj/attachment")
@AllArgsConstructor
public class AttachmentController {
    private final IAttachmentService attachmentService;

    @ApiOperation(value = "专家专业 信息表列表接口")
    @GetMapping("/list")
    public ReturnDatas<Page<TAttachment>> getPage(AttachmentQueryDTO attachmentQueryDTO) {
        return attachmentService.page(attachmentQueryDTO);
    }

    @ApiOperation(value = "专家专业 信息表保存接口")
    @PostMapping
    public ReturnDatas<Boolean> save(@Valid AttachmentInputDTO attachmentInputDTO) {
        return attachmentService.save(attachmentInputDTO);
    }

    @ApiOperation(value = "专家专业 信息表更新接口")
    @PostMapping("/update")
    public ReturnDatas<Boolean> updateById(@Valid AttachmentUpdateDTO attachmentUpdateDTO) {
        return attachmentService.update(attachmentUpdateDTO);
    }

    @ApiOperation(value = "专家专业 信息表详情接口")
    @GetMapping("/{id}")
    public ReturnDatas<TAttachment> getById(@PathVariable String id) {
        return attachmentService.findById(id);
    }

    @ApiOperation(value = "专家专业 信息表删除接口")
    @DeleteMapping("/{id}")
    public ReturnDatas<Boolean> removeById(@PathVariable String id) {
        return attachmentService.deleteById(id);
    }


}