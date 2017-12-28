package pers.east.family.service.impl;

import org.springframework.stereotype.Service;
import pers.east.family.MessageType;
import pers.east.family.service.FamilyService;

import java.util.Map;

/**
 * @Author : east.Fu
 * @Description :
 * @Date : Created in  2017/12/19 15:42
 */
@Service
public class FamilyServiceImpl implements FamilyService {

    @Override
    public void weather() {

    }

    @Override
    public void baby() {

    }

    @Override
    public void cooking() {

    }

    @Override
    public String buildResponseMessage(Map map) {
        //响应消息
        String responseMessage = "";
        //得到消息类型
        String msgType = map.get("MsgType").toString();
        System.out.println("MsgType:" + msgType);
        //消息类型
        MessageType messageEnumType = MessageType.valueOf(MessageType.class, msgType.toUpperCase());
        responseMessage = buildTextMessage(map,"I love you");
        //返回响应消息
        return responseMessage;
    }


    /**
     * 构造文本消息
     * @param map 封装了解析结果的Map
     * @param content 文本消息内容
     * @return 文本消息XML字符串
     */
    private  String buildTextMessage(Map<String, String> map, String content) {
        //发送方帐号
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        /**
         * 文本消息XML数据格式
         * <xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>1348831860</CreateTime>
         <MsgType><![CDATA[text]]></MsgType>
         <Content><![CDATA[this is a test]]></Content>
         <MsgId>1234567890123456</MsgId>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[text]]></MsgType>" +
                        "<Content><![CDATA[%s]]></Content>" +
                        "</xml>",
                fromUserName, toUserName, getMessageCreateTime(), content);
    }


    /**
     * 构造图片消息
     * @param map 封装了解析结果的Map
     * @param mediaId 通过素材管理接口上传多媒体文件得到的id
     * @return 图片消息XML字符串
     */
    private static String buildImageMessage(Map<String, String> map, String mediaId) {
        //发送方帐号
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        /**
         * 图片消息XML数据格式
         *<xml>
         <ToUserName><![CDATA[toUser]]></ToUserName>
         <FromUserName><![CDATA[fromUser]]></FromUserName>
         <CreateTime>12345678</CreateTime>
         <MsgType><![CDATA[image]]></MsgType>
         <Image>
         <MediaId><![CDATA[media_id]]></MediaId>
         </Image>
         </xml>
         */
        return String.format(
                "<xml>" +
                        "<ToUserName><![CDATA[%s]]></ToUserName>" +
                        "<FromUserName><![CDATA[%s]]></FromUserName>" +
                        "<CreateTime>%s</CreateTime>" +
                        "<MsgType><![CDATA[image]]></MsgType>" +
                        "<Image>" +
                        "   <MediaId><![CDATA[%s]]></MediaId>" +
                        "</Image>" +
                        "</xml>",
                fromUserName, toUserName, getMessageCreateTime(), mediaId);
    }


    public static String getMessageCreateTime(){
        return System.currentTimeMillis()/1000+"";
    }
}
