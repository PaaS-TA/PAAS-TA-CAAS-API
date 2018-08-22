package org.paasta.caas.api.common;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Common Service 클래스
 *
 * @author REX
 * @version 1.0
 * @since 2018.08.07
 */
@Service
public class CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonService.class);
    private final Gson gson;

    /**
     * Instantiates a new Common service.
     *
     * @param gson the gson
     */
    @Autowired
    public CommonService(Gson gson) {this.gson = gson;}



    /**
     * Sets result model.
     *
     * @param reqObject  the req object
     * @param resultCode the result code
     * @return the result model
     */
    public Object setResultModel(Object reqObject, String resultCode) {
        try {
            Class<?> aClass = reqObject.getClass();

            Method methodSetResultCode = aClass.getMethod("setResultCode", String.class);
            methodSetResultCode.invoke(reqObject, resultCode);

        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException :: {}", e);
        } catch (IllegalAccessException e1) {
            LOGGER.error("IllegalAccessException :: {}", e1);
        } catch (InvocationTargetException e2) {
            LOGGER.error("InvocationTargetException :: {}", e2);
        }

        return reqObject;
    }


    /**
     * To json string.
     *
     * @param requestObject the request object
     * @return the string
     */
    public String toJson(Object requestObject) {
        return gson.toJson(requestObject);
    }


    /**
     * From json t.
     *
     * @param <T>           the type parameter
     * @param requestString the request string
     * @param requestClass  the request class
     * @return the t
     */
    public <T> T fromJson(String requestString, Class<T> requestClass) {
        return gson.fromJson(requestString, requestClass);
    }
}
