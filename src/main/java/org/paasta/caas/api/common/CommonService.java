package org.paasta.caas.api.common;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
     * Sets list data.
     *
     * @param reqObject     the req object
     * @param itemKey       the item key
     * @param apiResultList the api result list
     * @return the list data
     */
// TODO :: REMOVE
    public List setListData(Object reqObject, String itemKey, List apiResultList) {
        List resultList = new ArrayList<>();

        try {
            for (Object itemsList : apiResultList) {
                Object resultObject;
                Class<?> aClass;

                if (reqObject instanceof Class) {
                    aClass = (Class<?>) reqObject;
                    resultObject = ((Class) reqObject).newInstance();
                } else {
                    aClass = reqObject.getClass();
                    resultObject = aClass.newInstance();
                }

                HashMap itemsMap = (HashMap) itemsList;
                HashMap metadataMap = (HashMap) itemsMap.get(itemKey);
                Field[] declaredFields = aClass.getDeclaredFields();
                String fieldName;

                for (Field field : declaredFields) {
                    fieldName = field.getName();
                    Method methodSetName = aClass.getMethod("set" + StringUtils.capitalize(fieldName), field.getType());
                    methodSetName.invoke(resultObject, (String) metadataMap.get(fieldName));
                }

                resultList.add(resultObject);
            }


        } catch (NoSuchMethodException e) {
            LOGGER.error("NoSuchMethodException :: {}", e);
        } catch (IllegalAccessException e1) {
            LOGGER.error("IllegalAccessException :: {}", e1);
        } catch (InvocationTargetException e2) {
            LOGGER.error("InvocationTargetException :: {}", e2);
        } catch (InstantiationException e3) {
            LOGGER.error("InstantiationException :: {}", e3);
        }

        return resultList;
    }

    /**
     * Sets result model.
     *
     * @param reqObject           the req object
     * @param resultStatusCode    the result status code
     * @param resultStatusMessage the result status message
     * @return the result model
     */
    public Object setResultModel(Object reqObject, String resultStatusCode, String resultStatusMessage) {
        try {
            Class<?> aClass = reqObject.getClass();

            Method methodSetResultCode = aClass.getMethod("setResultCode", String.class);
            Method methodSetResultMessage = aClass.getMethod("setResultMessage", String.class);
            methodSetResultCode.invoke(reqObject, resultStatusCode);
            methodSetResultMessage.invoke(reqObject, resultStatusMessage);

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
     * @param <T>              the type parameter
     * @param requestString the request string
     * @param requestClass     the request class
     * @return the t
     */
    public <T> T fromJson(String requestString, Class<T> requestClass) {
        return gson.fromJson(requestString, requestClass);
    }

}
