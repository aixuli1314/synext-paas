package co.synext.config.aliyun.tools;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import co.synext.common.exception.BizException;
import co.synext.config.aliyun.BaseAliyunTools;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Slf4j
@Component
public class AliyunOssTools extends BaseAliyunTools {

    public UploadBean uploadFile(MultipartFile multipartFile) {
        UploadBean result = new UploadBean();
        if (!multipartFile.isEmpty()) {
            String folderName = aliyunProperties.getOss().getFolderName();
            String fileName = IdUtil.fastUUID();
            OSS ossClient = new OSSClient(aliyunProperties.getOss().getEndpoint(), aliyunProperties.getAccessKeyId(), aliyunProperties.getAccessKeySecret());
            try {
                ossClient.putObject(aliyunProperties.getOss().getBucketName(), folderName + "/" + fileName, multipartFile.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
                throw new BizException("文件上传失败 - Error[" + e.getMessage() + "]");
            }
            result.setFileUrl(aliyunProperties.getOss().getCdnDomain() + "/" + folderName + "/" + fileName);
            return result;
        } else {
            throw new BizException("上传文件不能为空！");
        }
    }

    public static class UploadBean {

        @ApiModelProperty("附件地址")
        private String fileUrl;


        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

    }
}
