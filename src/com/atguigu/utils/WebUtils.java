package com.atguigu.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {

    /*
     * @Description : 把 Map中的值注入到对应的 JavaBean属性中，并将 bean返回
     * @param value
	 * @param bean
	 *  使用 Map 代码的扩展性更好，耦合度更高
	 *  使用泛型，使用该方法就不需要进行类型转换
    */
    public static <T>  T copyParamToBean(Map value, T bean){
        try {
            System.out.println("注入之前："+ bean);
            BeanUtils.populate(bean,value);
            System.out.println("注入之后："+ bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /*
     * @Description : 将字符串转换成为 Int类型的数据
     * @param strInt  要转换的字符串
	* @param defaultValue 默认值
    */
    // 报 NumberFormatException: 原因是查看第一页图书信息时，没有传入 pageNo ,而是使用的默认值
    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return defaultValue;
    }
}
