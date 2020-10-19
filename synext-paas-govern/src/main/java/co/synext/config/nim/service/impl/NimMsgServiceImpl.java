package co.synext.config.nim.service.impl;

import co.synext.config.nim.AbstractNimHttpRequest;
import co.synext.config.nim.entity.NimBaseRespEntity;
import co.synext.config.nim.entity.req.*;
import co.synext.config.nim.entity.req.MsgRecallOneWayRequestEntity;
import co.synext.config.nim.entity.resp.*;
import co.synext.config.nim.service.INimMsgService;
import org.springframework.stereotype.Service;

@Service
public class NimMsgServiceImpl extends AbstractNimHttpRequest implements INimMsgService {

    @Override
    public MsgSendResponseEntity sendMsg(MsgSendRequestEntity sendMessageRequestEntity) {
        return execute(sendMsgUrl, sendMessageRequestEntity, MsgSendResponseEntity.class);
    }

    @Override
    public MsgBatchSendResponseEntity sendBatchMsg(MsgBatchSendRequestEntity batchSendRequestEntity) {
        return execute(sendBatchMsgUrl, batchSendRequestEntity, MsgBatchSendResponseEntity.class);
    }

    @Override
    public NimBaseRespEntity sendAttachMsg(MsgAttachSendRequestEntity msgAttachSendRequestEntity) {
        return execute(sendAttachMsgUrl, msgAttachSendRequestEntity, MsgBatchSendResponseEntity.class);
    }

    @Override
    public MsgAttachBatchSendResponseEntity sendBatchAttachMsg(MsgBatchAttachSendRequestEntity msgBatchAttachSendRequestEntity) {
        return execute(sendBatchAttachMsgUrl, msgBatchAttachSendRequestEntity, MsgAttachBatchSendResponseEntity.class);
    }


    @Override
    public MsgUploadResponseEntity upload(MsgUploadRequestEntity sendMessageRequestEntity) {
        return execute(uploadUrl, sendMessageRequestEntity, MsgUploadResponseEntity.class);
    }


    @Override
    public MsgFileUploadResponseEntity fileUpload(MsgFileUploadRequestEntity msgFileUploadRequestEntity) {
        return execute(fileUploadUrl, msgFileUploadRequestEntity, MsgFileUploadResponseEntity.class);
    }

    @Override
    public JobNosDelResponseEntity fileDel(JobNosDelRequestEntity jobNosDelRequestEntity) {
        return execute(nosDelUrl, jobNosDelRequestEntity, JobNosDelResponseEntity.class);
    }

    @Override
    public NimBaseRespEntity recall(MsgRecallRequestEntity msgRecallRequestEntity) {
        return execute(recallUrl, msgRecallRequestEntity, NimBaseRespEntity.class);
    }


    @Override
    public MsgBroadCastResponseEntity broadcastMsg(MsgBroadCastRequestEntity msgBroadCastResponseEntity) {
        return execute(broadcastMsgUrl, msgBroadCastResponseEntity, MsgBroadCastResponseEntity.class);
    }

    @Override
    public NimBaseRespEntity delMsgOneWay(MsgRecallOneWayRequestEntity msgRecallOneWayRequestEntity) {
        return execute(delMsgOneWayUrl, msgRecallOneWayRequestEntity, NimBaseRespEntity.class);
    }

    @Override
    public NimBaseRespEntity delRoamSession(MsgRoamDelRequestEntity msgRoamDelRequestEntity) {
        return execute(delRoamSessionUrl, msgRoamDelRequestEntity, NimBaseRespEntity.class);
    }
}
