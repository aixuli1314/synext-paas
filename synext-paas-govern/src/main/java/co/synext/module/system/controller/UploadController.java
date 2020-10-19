package co.synext.module.system.controller;

import co.synext.common.base.resp.ReturnDatas;
import co.synext.common.constant.Constant;
import co.synext.config.aliyun.tools.AliyunOssTools;
import co.synext.module.system.dto.AttachmentInputDTO;
import co.synext.module.system.service.IAttachmentService;
import co.synext.module.system.service.IUploadFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "2-文件上传接口")
@RestController
@RequestMapping("/open")
public class UploadController {
    @Autowired
    private IUploadFile uploadFile;
    @Resource
    private AliyunOssTools aliyunOssTools;
    @Resource
    private IAttachmentService attachmentService;

    @ApiOperation(value = "文件上传oss")
    @PostMapping("/oss/upload")
    public ReturnDatas<AliyunOssTools.UploadBean> upload(@ApiParam(name = "file", value = "文件", required = true)
                        @RequestParam("file") MultipartFile multipartFile) {
        AliyunOssTools.UploadBean result = aliyunOssTools.uploadFile(multipartFile);
        return ReturnDatas.ok(result);
    }
    @ApiOperation(value = "文件上传本地")
    @PostMapping("/local/upload")
    public ReturnDatas uploadLocal(@ApiParam(name = "file", value = "文件", required = true)
    		@RequestParam("file") MultipartFile multipartFile) throws IOException {
            return uploadFile.uploadFile(multipartFile);
    }
}
