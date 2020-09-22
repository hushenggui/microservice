package com.tuacy.microservice.framework.common.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * xml工具类
 *
 * @author AnthonyPark
 * @date 2018/5/25
 */
@Slf4j
public class XmlUtil {

  public static String readElement(Element e, Map<String, String> vars, Document document) throws Exception {
    //判断是否有复合内容
    if (e.hasMixedContent()) {
      Iterator it = e.elementIterator();
      while (it.hasNext()) {
        Element arrrName = (Element) it.next();
        //递归
        readElement(arrrName, vars, document);
      }
    } else {
      if (StringUtils.hasText(e.getTextTrim())) {
        String key = e.getText();
        key = key.replaceAll("#", "");

        String value = vars.get(key);
        if (!StringUtils.isEmpty(value)) {
          e.setText(value);
        } else {
          if (StringUtils.hasText(key) && !(key.startsWith("#") && key.endsWith("#"))) {
            //e.setText(key);
            e.setText("");
          } else {
            e.setText("");
          }
        }
      }
    }
    return document.asXML();
  }

  public static String findNode(String xml, String nodeName) {
    Document document = null;
    try {
      document = DocumentHelper.parseText(xml);
    } catch (DocumentException e) {
      log.error(e.getMessage(), e);
      return null;
    }
    Element element = document.getRootElement();
    Element ec = (Element) element.selectSingleNode("//" + nodeName);
    if(ObjectUtils.isEmpty(ec)) {
      return "";
    }
    return ec.getText();
  }

  /**
   * 格式化XML文档
   *
   * @param document xml文档
   * @param charset 字符串的编码
   * @param istrans 是否对属性和元素值进行转移
   * @return 格式化后XML字符串
   */
  public static String formatXml(Document document, String charset, boolean istrans) {
    OutputFormat format = OutputFormat.createPrettyPrint();
    format.setEncoding(charset);
    StringWriter sw = new StringWriter();
    XMLWriter xw = new XMLWriter(sw, format);
    xw.setEscapeText(istrans);
    try {
      xw.write(document);
      xw.flush();
      xw.close();
    } catch (IOException e) {
      System.out.println("格式化XML文档发生异常，请检查！");
      e.printStackTrace();
    }
    return sw.toString();
  }


  /**
   * 从指定节点开始,递归遍历所有子节点
   *
   * @return 返回所有节点值
   */
  public static String getNodes(String nodeName, String xmlStr) {
    Document document = null;
    try {
      document = DocumentHelper.parseText(xmlStr);
      Element element = document.getRootElement();
      Element ec = (Element) element.selectSingleNode(nodeName);
      return getNodes(ec, null);
    } catch (DocumentException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 从指定节点开始,递归遍历所有子节点
   *
   * @return 返回所有节点值
   */
  public static String getNodes(Element node, StringBuffer sb) {
    if (sb == null) {
      sb = new StringBuffer();
    }

    if (!node.getName().equals("Signature")) {
      if (node.isTextOnly()) {
        sb.append(node.getText());
      } else {
        sb.append(node.getTextTrim());
      }
    }

    //递归遍历当前节点所有的子节点
    //所有一级子节点的list
    List<Element> listElement = node.elements();
    //遍历所有一级子节点
    for (Element e : listElement) {
      //递归
      getNodes(e, sb);
    }
    return sb.toString();
  }

  public static void main(String[] agrs) throws Exception {
    String reqTemp = "<?xmlversion=\"1.0\" encoding=\"UTF-8\"?>\n"
        + "<queryInvoiceRequest>\n"
        + "          <requestHead>\n"
        + "                <requestUUID>20200521193457</requestUUID>\n"
        + "                <sign>773C965B10150DE7B6DD0268C7CF4B5E</sign>\n"
        + "          </requestHead>\n"
        + "          <requestBody>\n"
        + "                <policyNo>13402033900977114837</policyNo>\n"
        + "                <downLoadUrl>https://open.pingan.com.cn/Gateway/P_ZJZTB_GP/printInvoiceForDMZ?ciphertext=QTJ6M0FOODZvQVJzcVhoSkdrbmxZWlMyL0xqYVdMUTJoMit1NExsdHArYngvYnpxMDRSVFlwZ2QvcGR5aW9GeDVYZmsrWTNMRFJrS2FyMnZOQ005RVlSbi9KU0Z3R1hOVXVxR1ZiUmQyUWlrTHFaRDVlZ0hqendHM0wyYTk3RWNBdXNVN1VBZlNnTmZzTXQwR2hPSVRPcks5Q1ZadUpPekJsS3ZURXAwMTNXRy9uMlgyMUlPR3ZZSkhVd1lqeTd1ZE1QQ0JsZkdqc0VQR0MzWldaaVNIcmhKWVhzVm5kNjVFSXowT2theDYvNW1vaExZT0RMeE5RVGgrUVF2K0N2Z29iUnhIcU9tYmxsS1N0ZEZvdGplYUFRcHhVZ0gzcVhNMTRZUngzQloyY1JIQUo1QXc3OTFIV3pQNFl0MzB4UkwxbDNwUzVxYUdaNUlRem9MT2JjVnVSY0dkT1ppVEg0dGhycTMxN1h2Ni8yMjNlMGpVZjNMOHZzaGFhT1lwbnZ2NjZlK29vczVGN1ZscGxkNko1T0NUbHZDRXlsUThUSEx1UHRHNkcvTGNETy84K3FZcjdtNnRoOFAyMXZpbjdnRU9RMk5pekVtNXNkay9xVlNVRUJyQTZMMUxOTVpQdnhDK3hNVVorckVvc0ZMaWRYUHdCcHIwUGtOYzdndGJFT24%3D%26sign=Ns4y0V4qBEFqsA6DW1GqvICrcSL4ZRfnEtwEaSIwqkr+x/4cZC1EJZ/2MSVGTQdDpwbSmdXwDmwQkJCIU28OBV/Ps/mHe2WmxqHRjUxlWdZCCFDhDQsAs31Qga7YbJ14MfUpclEfsKtyR+QNtEXBipyPLVGcRstEEPpG7m3q2Es=</downLoadUrl>\n"
        + "                <expressNo/>\n"
        + "                <expressName/>\n"
        + "                <insuranceCode>00007</insuranceCode>\n"
        + "          </requestBody>\n"
        + "        </queryInvoiceRequest>";

    String ss = getNodes("insuranceCode", reqTemp);
    System.out.println(ss);

    //ss = SignUtil.sign(reqTemp,"sign","123");
    //System.out.println(ss);
  }

  public static String getXml(String template, Map<String, String> dataMap) {
    try {
      Document document = DocumentHelper.parseText(template);
      Element element = document.getRootElement();
      String xml = XmlUtil.readElement(element, dataMap, document);
      return xml;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return "";
    }
  }

}
