package com.tuacy.microservice.framework.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.*;

/**
 * @author: huacailiang
 * @date: 2020/4/8
 * @description:
 **/
@Slf4j
public class RestTemplateUtil {

  public static ResponseEntity<String> postForEntity(String template, Map<String, String> dataMap, String url) throws Exception {

    Document document = DocumentHelper.parseText(template);
    Element element = document.getRootElement();
    String requestEntity = XmlUtil.readElement(element, dataMap, document);
    byte[] bytes = requestEntity.getBytes(StandardCharsets.UTF_8);
    String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);

    log.info("url {}, data {}", url, utf8EncodedString);
    HttpEntity<String> request = new HttpEntity<>(utf8EncodedString, getHttpHeaders());
    ResponseEntity<String> stringResponseEntity = getRestTemplate().postForEntity(url, request, String.class);
    return stringResponseEntity;
  }

  public static ResponseEntity<String> postForEntity(String playLoad, String url) throws Exception {

    byte[] bytes = playLoad.getBytes(StandardCharsets.UTF_8);
    String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
    log.info("request url {}, data {}", url, utf8EncodedString);

    HttpEntity<String> request = new HttpEntity<>(utf8EncodedString, getHttpHeaders());
    ResponseEntity<String> stringResponseEntity = getRestTemplate().postForEntity(url, request, String.class);
    log.info("response url {}, data {}, response {}", url, utf8EncodedString, stringResponseEntity.getBody());
    return stringResponseEntity;
  }

  public static ResponseEntity<String> postForEntity(String playLoad, String url, String insCode) throws Exception {

    byte[] bytes = playLoad.getBytes(StandardCharsets.UTF_8);
    String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
    log.info("request url {}, data {}", url, utf8EncodedString);

    HttpEntity<String> request = new HttpEntity<>(utf8EncodedString, getHttpHeaders(insCode));
    ResponseEntity<String> stringResponseEntity = getRestTemplate().postForEntity(url, request, String.class);
    log.info("response url {}, data {}, response {}", url, utf8EncodedString, stringResponseEntity.getBody());
    return stringResponseEntity;
  }

  public static ResponseEntity<String> postForEntity(String playLoad, String url, HttpHeaders headers) throws Exception {
    byte[] bytes = playLoad.getBytes(StandardCharsets.UTF_8);
    String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
    log.info("url {}, data {}", url, utf8EncodedString);

    headers.setContentType(MediaType.APPLICATION_XML);
    HttpEntity<String> request = new HttpEntity<>(utf8EncodedString, headers);
    ResponseEntity<String> stringResponseEntity = getRestTemplate().postForEntity(url, request, String.class);
    return stringResponseEntity;
  }

  public static ResponseEntity<String> getForEntity(String url) throws Exception {
    log.info("url {}", url);

    ResponseEntity<String> stringResponseEntity = getRestTemplate().getForEntity(url, String.class);
    return stringResponseEntity;
  }

  static HttpHeaders getHttpHeaders() {

    HttpHeaders headers = new HttpHeaders();
    Charset utf8 = Charset.forName("UTF-8");
    MediaType mediaTypeXmlUtf8 = new MediaType("application", "xml", utf8);
    MediaType mediaTypeXml = new MediaType("application", "xml");
    MediaType mediaTypeJson = new MediaType("application", "json");
    MediaType mediaTypeHtml = new MediaType("text", "html");
    MediaType mediaTypeXHtml = new MediaType("application", "xhtml+xml");

    headers.setContentType(mediaTypeXmlUtf8);
    headers.setAccept(Arrays.asList(mediaTypeXml, mediaTypeJson, mediaTypeHtml, mediaTypeXHtml));
    headers.setAcceptCharset(Arrays.asList(utf8));
    return headers;
  }

  static HttpHeaders getHttpHeaders(String insCode) {

    HttpHeaders headers = new HttpHeaders();
    Charset utf8 = Charset.forName("UTF-8");
    MediaType mediaTypeXml = new MediaType("application", "xml");
    MediaType mediaTypeJson = new MediaType("application", "json");
    MediaType mediaTypeHtml = new MediaType("text", "html");
    MediaType mediaTypeXHtml = new MediaType("application", "xhtml+xml");
    headers.setAccept(Arrays.asList(mediaTypeXml, mediaTypeJson, mediaTypeHtml, mediaTypeXHtml));
    MediaType mediaTypeXmlUtf8 = new MediaType("application", "xml", utf8);
    headers.setContentType(mediaTypeXmlUtf8);
    headers.setAcceptCharset(Arrays.asList(utf8));
    return headers;
  }

  public static RestTemplate getRestTemplate() {
    RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMinutes(3)).setReadTimeout(
            Duration.ofMinutes(3)).build();

    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

    StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
    stringHttpMessageConverter.setWriteAcceptCharset(true);

    List<MediaType> mediaTypeList = new ArrayList<>();
    mediaTypeList.add(MediaType.ALL);

    for (int i = 0; i < restTemplate.getMessageConverters().size(); i++) {
      HttpMessageConverter<?> httpMessageConverter = restTemplate.getMessageConverters().get(i);
      if (httpMessageConverter instanceof StringHttpMessageConverter) {
        restTemplate.getMessageConverters().remove(i);
        restTemplate.getMessageConverters().add(i, stringHttpMessageConverter);
      } else if(httpMessageConverter instanceof MappingJackson2HttpMessageConverter) {
        try{
          ((MappingJackson2HttpMessageConverter) httpMessageConverter).setSupportedMediaTypes(mediaTypeList);
        }catch(Exception e){
          e.printStackTrace();
        }
      }
    }
    ClientHttpRequestFactory requestFactory = null;
    TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
              public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
              }
              public void checkClientTrusted(
                      X509Certificate[] certs, String authType) {
              }
              public void checkServerTrusted(
                      X509Certificate[] certs, String authType) {
              }
            }
    };
    SSLContext sslContext = null;
    HttpComponentsClientHttpRequestFactory customRequestFactory = new HttpComponentsClientHttpRequestFactory();
    try {
      sslContext = SSLContext.getInstance("SSL");
      sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
      CloseableHttpClient httpClient = HttpClients.custom()
              .setSSLContext(sslContext)
              .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
              .build();
      customRequestFactory.setHttpClient(httpClient);
    } catch (Exception e) {
      e.printStackTrace();
    }
    requestFactory = (ClientHttpRequestFactory)customRequestFactory;
    restTemplate.setRequestFactory(requestFactory);
    return restTemplate;
  }

}
