package co.synext.config.nim.entity.req;

import co.synext.config.nim.entity.NimBaseReqEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
@Accessors(chain = true)
public class MsgAttachSendRequestEntity extends NimBaseReqEntity {

    /**
     * 请求示例
     *
     * curl -X POST -H "AppKey: go9dnk49bkd9jd9vmel1kglw0803mgq3" -H "Nonce: 4tgggergigwow323t23t" -H "CurTime: 1443592222" -H "CheckSum: 9e9db3b6c9abb2e1962cf3e6f7316fcc55583f86" -H "Content-Type: application/x-www-form-urlencoded" -d 'from=zhangsan&msgtype=0&to=lisi&attach={"myattach":"test"}' 'https://api.netease.im/nimserver/msg/sendAttachMsg.action'
     *
     */

    private String from;
    private Integer msgtype;
    private String to;
    private String attach;
    private String pushcontent;
    private String payload;
    private String sound;
    private Integer save;
    private Option option;


    @Getter
    @Setter
    @Builder
    @ToString
    @Accessors(chain = true)
    private static class Option implements Serializable {

        private Boolean push;
        private Boolean roam;
        private Boolean history;
        private Boolean sendersync;
        private Boolean route;
        private Boolean badge;
        private Boolean needPushNick;
        private Boolean persistent;

    }

}
