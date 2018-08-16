package org.paasta.caas.api.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

            Method methodSetResultCode = aClass.getMethod( "setResultCode", String.class );
            Method methodSetResultMessage = aClass.getMethod( "setResultMessage", String.class );
            methodSetResultCode.invoke( reqObject, resultStatusCode );
            methodSetResultMessage.invoke( reqObject, resultStatusMessage );

        } catch ( NoSuchMethodException e ) {
            LOGGER.error( "NoSuchMethodException :: {}", e );
        } catch ( IllegalAccessException e1 ) {
            LOGGER.error( "IllegalAccessException :: {}", e1 );
        } catch ( InvocationTargetException e2 ) {
            LOGGER.error( "InvocationTargetException :: {}", e2 );
        }

        return reqObject;
    }
}
