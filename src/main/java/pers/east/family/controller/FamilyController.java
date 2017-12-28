package pers.east.family.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.east.family.service.FamilyService;
import pers.east.family.util.XmlUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author : east.Fu
 * @Description :
 * @Date : Created in  2017/12/19 16:00
 */
@RestController
public class FamilyController {

    @Autowired
    private FamilyService familyServiceImpl;

    public static final String TOKEN = "happyfamily";

    @RequestMapping("/")
    public String service(HttpServletRequest request, HttpServletResponse response){
        System.out.println("signature:"+request.getParameter("signature"));
        System.out.println("timestamp:"+request.getParameter("timestamp"));
        System.out.println("nonce:"+request.getParameter("nonce"));
        String echostr=request.getParameter("echostr");
        System.out.println("echostr:"+echostr);
        if(StringUtils.isNotBlank(echostr)){
            return echostr;
        }else{
            String responseMessage;
            try {
                //解析微信发来的请求,将解析后的结果封装成Map返回
                Map<String,String> map = XmlUtil.parseXml(request);
                System.out.println("开始构造响应消息");
                responseMessage = familyServiceImpl.buildResponseMessage(map);
                System.out.println(responseMessage);
                if(responseMessage.equals("")){
                    responseMessage ="未正确响应";
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("发生异常："+ e.getMessage());
                responseMessage ="未正确响应";
            }
            //发送响应消息
            return responseMessage;
        }
    }
}
