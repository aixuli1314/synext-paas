package co.synext.config.nim.service;

import co.synext.config.nim.entity.NimBaseRespEntity;
import co.synext.config.nim.entity.req.*;
import co.synext.config.nim.entity.req.MsgRecallOneWayRequestEntity;
import co.synext.config.nim.entity.resp.*;

/**
 * <p>
 *  消息功能接口
 * </p>
 *
 * @author xu.ran
 * @since 2020-04-09
 */
public interface INimMsgService {

    //发送普通消息
    String sendMsgUrl = "https://api.netease.im/nimserver/msg/sendMsg.action";
    //批量发送点对点普通消息
    String sendBatchMsgUrl = "https://api.netease.im/nimserver/msg/sendBatchMsg.action";
    //发送自定义系统通知
    String sendAttachMsgUrl = "https://api.netease.im/nimserver/msg/sendAttachMsg.action";
    //发送普通消息
    String sendBatchAttachMsgUrl = "https://api.netease.im/nimserver/msg/sendBatchAttachMsg.action";
    //发送普通消息
    String uploadUrl = "https://api.netease.im/nimserver/msg/upload.action";
    //发送普通消息
    String fileUploadUrl = "https://api.netease.im/nimserver/msg/fileUpload.action";
    //上传NOS文件清理任务
    String nosDelUrl = "https://api.netease.im/nimserver/job/nos/del.action";
    //消息撤回
    String recallUrl = "https://api.netease.im/nimserver/msg/recall.action";
    //发送广播消息
    String broadcastMsgUrl = "https://api.netease.im/nimserver/msg/broadcastMsg.action";
    //单向撤回消息
    String delMsgOneWayUrl = "https://api.netease.im/nimserver/msg/delMsgOneWay.action";
    //删除会话漫游
    String delRoamSessionUrl = "https://api.netease.im/nimserver/msg/delRoamSession.action";

    /**
     * 发送普通消息
     *
     * @param sendMessageRequestEntity
     * @return
     */
    MsgSendResponseEntity sendMsg(MsgSendRequestEntity sendMessageRequestEntity);

    /**
     * 批量发送点对点普通消息
     *
     * @param batchSendRequestEntity
     * @return
     */
    MsgBatchSendResponseEntity sendBatchMsg(MsgBatchSendRequestEntity batchSendRequestEntity);

    /**
     * 发送自定义系统通知
     *
     * @param msgAttachSendRequestEntity
     * @return
     */
    NimBaseRespEntity sendAttachMsg(MsgAttachSendRequestEntity msgAttachSendRequestEntity);

    /**
     * 批量发送点对点自定义系统通知
     *
     * @param msgBatchAttachSendRequestEntity
     * @return
     */
    MsgAttachBatchSendResponseEntity sendBatchAttachMsg(MsgBatchAttachSendRequestEntity msgBatchAttachSendRequestEntity);

    /**
     * 文件上传
     *
     * @param sendMessageRequestEntity
     * @return
     */
    MsgUploadResponseEntity upload(MsgUploadRequestEntity sendMessageRequestEntity);

    /**
     * 文件上传（multipart方式）
     *
     * @param msgFileUploadRequestEntity
     * @return
     */
    MsgFileUploadResponseEntity fileUpload(MsgFileUploadRequestEntity msgFileUploadRequestEntity);

    /**
     * 上传NOS文件清理任务
     *
     * @param jobNosDelRequestEntity
     * @return
     */
    JobNosDelResponseEntity fileDel(JobNosDelRequestEntity jobNosDelRequestEntity);

    /**
     * 消息撤回
     *
     * @param msgRecallRequestEntity
     * @return
     */
    NimBaseRespEntity recall(MsgRecallRequestEntity msgRecallRequestEntity);

    /**
     * 发送广播消息
     *
     * @param msgBroadCastResponseEntity
     * @return
     */
    MsgBroadCastResponseEntity broadcastMsg(MsgBroadCastRequestEntity msgBroadCastResponseEntity);

    /**
     * 单向撤回消息
     *
     * @param msgRecallOneWayRequestEntity
     * @return
     */
    NimBaseRespEntity delMsgOneWay(MsgRecallOneWayRequestEntity msgRecallOneWayRequestEntity);

    /**
     * 删除会话漫游
     *
     * @param msgRoamDelRequestEntity
     * @return
     */
    NimBaseRespEntity delRoamSession(MsgRoamDelRequestEntity msgRoamDelRequestEntity);


}
