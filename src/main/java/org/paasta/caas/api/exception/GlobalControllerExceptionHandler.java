package org.paasta.caas.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Api ExceptionHandler 클래스
 *
 * @author CISS
 * @version 1.0
 * @since 2018.08.01
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
    
    //private static final Locale DEFAULT_LOCALE = Locale.US;
    //private static final Locale DEFAULT_LOCALE = Locale.KOREA;

    @Autowired
    public MessageSource messageSource;

    // rest API template 에러시 처리
    @ExceptionHandler({HttpClientErrorException.class})
    @ResponseBody
    public void httpClientErrorException(HttpClientErrorException ex, HttpServletResponse response) throws Exception {

        LOGGER.error("HttpClientErrorException : " + ex );

        //String[] message;
        String msg = ex.getMessage();
        try {
            // To-be getStatusCode 로 처리 혹은 getMessage 로 메시지 처리(메시지 처리시)
            //msg = messageSource.getMessage( String.valueOf(ex.getStatusCode()) , null, "Not Found Message." ,Locale.KOREA);
            //response.getLocale() 시 DEV 에서는 Not Found Message. 로 메시지 찾지 못함. To-Be DEV 로케일 확인 필요
            //if (ex.getMessage().contains("Organization") &&  ex.getMessage().contains("not found")) {
            //    msg = messageSource.getMessage("Organization_not_found", null, DEFAULT_LOCALE);
            //}
        }catch(Exception e){
            //msg = getStackTraceString( e );
        }

        String message = "{\"result\":\"fail\""+
        ",\"message\":\""+msg+"\"" +
        ",\"code\":\""+ex.getStatusCode()+"\"}";

        response.resetBuffer();
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(message);
        response.flushBuffer();
    }

    private String getStackTraceString(Throwable t) {
        StringWriter swriter = new StringWriter();
        PrintWriter pwriter = new PrintWriter( swriter );
        String result = "";
       try {
           t.printStackTrace( pwriter );
           pwriter.flush();
           result = swriter.toString();
           swriter.close();
       }catch(Exception e){
           LOGGER.error("fail to getStackTraceString", e);
       }finally {
           pwriter.close();
       }
       return result;
    }

    private boolean errorResponse(Throwable throwable, HttpStatus status, HttpServletResponse response) throws IOException {
        LOGGER.info(response.toString());
        //response.sendError(status.value(), messageSource.getMessage(status.toString(), null, DEFAULT_LOCALE));
        final StringBuffer buffer = new StringBuffer();
        buffer.append( "Response : " )
            .append( response.toString() ).append( '\n' )
            .append( "Occured an exception : " ).append( throwable.getMessage() ).append( '\n' )
            .append( "Caused by... ").append( '\n' )
            .append( getStackTraceString( throwable ) );


        response.sendError(status.value(), buffer.toString());
        LOGGER.error("Http status : {}", status.value());
        LOGGER.error("Error message : {}", buffer.toString());
        
        return false;
    }

}

