package pers.east.family.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : east.Fu
 * @Description :
 * @Date : Created in  2017/12/19 17:26
 */
public class XmlUtil {

    /**
     * 解析微信发来的请求（XML）
     *
     * @param request 封装了请求信息的HttpServletRequest对象
     * @return map 解析结果
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        InputStream inputStream =null;
        Map<String, String> map=null;
        try {
            map = new HashMap<String, String>();
            inputStream = request.getInputStream();
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            List<Element> elementList = root.elements();

            for (Element e : elementList) {
                System.out.println(e.getName() + "|" + e.getText());
                map.put(e.getName(), e.getText());
            }
        }catch (Exception e){
            System.out.println();
        }finally {
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return map;
    }
}
