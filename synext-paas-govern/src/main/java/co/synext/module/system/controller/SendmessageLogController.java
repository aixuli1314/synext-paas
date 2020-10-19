package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.mybatis.entity.TSendmessageLog;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import co.synext.module.system.dto.SendmessageLogInputDTO;
import co.synext.module.system.dto.SendmessageLogUpdateDTO;
import co.synext.module.system.dto.SendmessageLogQueryDTO;
import co.synext.module.system.vo.SendmessageLogVO;
import co.synext.module.system.service.ISendmessageLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


/**
 * <p>
 * 消息发送记录表 控制器
 * </p>
 *
 * @author xu.ran
 * @since 2020-09-10
 */
@Api(tags = "消息发送记录表接口")
@RestController
@RequestMapping("/system/api/kjj/sendmessagelog")
@AllArgsConstructor
public class SendmessageLogController {
    private final ISendmessageLogService sendmessageLogService;

    @ApiOperation(value = "消息发送记录表列表接口")
    @PostMapping("/list")
    public ReturnDatas<Page<TSendmessageLog>> getPage(@RequestBody SendmessageLogQueryDTO sendmessageLogQueryDTO) {
        return sendmessageLogService.page(sendmessageLogQueryDTO);
    }

    @ApiOperation(value = "消息发送记录表保存接口")
    @PostMapping("/save")
    public ReturnDatas<Boolean> save(@Valid @RequestBody SendmessageLogInputDTO sendmessageLogInputDTO) {
        System.out.println("****************");
        System.out.println(sendmessageLogInputDTO.toString());
        return sendmessageLogService.save(sendmessageLogInputDTO);
    }

/*    @ApiOperation(value = "消息发送记录表更新接口")
    @PostMapping("/update")
    public ReturnDatas<Boolean> updateById(@Valid @RequestBody SendmessageLogUpdateDTO sendmessageLogUpdateDTO) {
        return sendmessageLogService.update(sendmessageLogUpdateDTO);
    }*/

    @ApiOperation(value = "消息发送记录表详情接口")
    @PostMapping("/look/{id}")
    public ReturnDatas<TSendmessageLog> getById(@PathVariable String id) {
        return sendmessageLogService.findById(id);
    }

/*    @ApiOperation(value = "消息发送记录表删除接口")
    @PostMapping("/delete")
    public ReturnDatas<Boolean> remove(@RequestBody List<String> ids) {
        return sendmessageLogService.batchDelete(ids);
    }*/


}