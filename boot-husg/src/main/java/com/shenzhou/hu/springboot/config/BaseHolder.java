package com.shenzhou.hu.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BaseHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Value("${springLoadClass}")
    private String springLoadClass;


    /**
     * 服务器启动，Spring容器初始化时，当加载了当前类为bean组件后，
     * 将会调用下面方法注入ApplicationContext实例
     */
    @Override
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        log.info("BaseHolder  初始化了");
        log.info("springLoadClass -- " + springLoadClass);
        BaseHolder.applicationContext = arg0;
        log.info("applicationContext 的类型" + applicationContext.getClass()+"");
        String[] s = applicationContext.getBeanDefinitionNames();
        if(s != null && s.length > 0) {
            if(StringUtils.isNotBlank(springLoadClass)){
                String [] springArr = springLoadClass.split(",");
                if(springArr != null && springArr.length > 0) {
                    for (String name : s) {
                        if (isContain(name,springArr)) {
                            log.info("spring初始化的类-" + name);
                        }
                    }
                }
            }
        }
    }

    /** 
    * @Description: 判断是否包含
    * @Param: [name, springArr] 
    * @return: boolean 
    * @Author: 胡胜归
    * @Date: 2020/1/7 
    * @throws  
    */ 
    private boolean isContain(String name, String[] springArr){
        for (String str: springArr) {
            if(name.contains(str)){
                return true;
            }
        }
        return false;
    }



    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 外部调用这个getBean方法就可以手动获取到bean
     * 用bean组件的name来获取bean
     *
     * @param beanName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }


    /**
     * 通过class获取Bean.
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getBean(String name,Class<T> clazz){
        return applicationContext.getBean(name, clazz);
    }


    public static void main(String[] args) {

        int[] a=new int[]{1,6,9,122};
        int[] b=new int[]{2,3,4};
        int length = a.length + b.length;
        int[] temp=new int[length];

        int[]d= compareDG(a,0,b,0,temp,0);
        for (int i=0; i<length; i++){
            System.out.println(d[i]);
        }





    }

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }




    private static int[] compareDG(int[] a,int i, int[] b,int j,int[] temp,int k) {
        if (i + j == temp.length){
            return temp;
        }else if (a.length == i){
            temp[k++] = b[j++];
        }else if (b.length == j){
            temp[k++] = a[i++];
        }else {
            if (a[i] <b [j]){
                temp[k++] = a[i++];
            }else {
                temp[k++] = b[j++];
            }
        }
        return compareDG(a,i,b,j,temp,k);
    }


}