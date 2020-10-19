package co.synext.config.nim.entity.req;

import co.synext.config.nim.entity.INimBaseMsg;
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
public class MsgSendRequestEntity extends NimBaseReqEntity {

    private String from;
    private Integer ope;
    private String to;
    private Integer type;
    private INimBaseMsg body;
    private String antispam;
    private AntispamCustom antispamCustom;
    private Option option;
    private String pushcontent;
    private String payload;
    private String ext;
    private String[] forcepushlist;
    private String forcepushcontent;
    private String forcepushall;
    private String bid;
    private Integer useYidun;
    private Integer markRead;
    private Boolean checkFriend;


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

    @Getter
    @Setter
    @Builder
    @ToString
    @Accessors(chain = true)
    private static class AntispamCustom implements Serializable {

        private String type;
        private String data;

    }

}
