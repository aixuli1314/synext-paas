package co.synext.module.system.service;

import co.synext.common.base.resp.ReturnDatas;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IUploadFile {
    ReturnDatas uploadFile(MultipartFile multipartFile) throws IOException;
}
