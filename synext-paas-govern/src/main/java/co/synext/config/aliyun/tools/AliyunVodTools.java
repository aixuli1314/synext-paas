package co.synext.config.aliyun.tools;

import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import co.synext.config.aliyun.BaseAliyunTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AliyunVodTools extends BaseAliyunTools {

    public GetPlayInfoResponse getPlayInfo(String id) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(id);
        GetPlayInfoResponse playInfoResponse = client.getAcsResponse(request);
        return playInfoResponse;
    }

    public GetVideoPlayAuthResponse getVideoPlayAuth(String id) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(id);
        GetVideoPlayAuthResponse playAuthResponse = client.getAcsResponse(request);
        return playAuthResponse;
    }
}
