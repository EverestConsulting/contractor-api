package com.contractor.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

public class ResponseFactory {
    private static final Logger LOG = LoggerFactory.getLogger(ResponseFactory.class.getSimpleName());

    private ResponseFactory() {
        // singleton
    }

    /**
     * Get blank 200 success
     *
     * @return formatted Response
     */
    public static Response getSuccess200() {
        return getSuccess200(null);
    }

    /**
     * Get 200 success with response message
     *
     * @param data the response message
     * @return formatted Response
     */
    public static Response getSuccess200(Object data) {
        return Response.ok(new ResponseMessage(true, data, null)).build();
    }

    /**
     * Get blank 404 error
     *
     * @return formatted Response
     */
    public static Response getNotFound404() {
        return getNotFound404(null, null);
    }

    /**
     * Get 404 error with error message
     *
     * @param errorMessage error message
     * @param errorReason  error reason (details)
     * @return formatted Response
     */
    public static Response getNotFound404(String errorMessage, String errorReason) {
        return buildErrorResponse(Response.Status.NOT_FOUND, errorMessage, errorReason, null);
    }

    /**
     * Get 400 error with error message
     *
     * @param errorMessage error message
     * @param errorReason  error reason (details)
     * @return formatted Response
     */
    public static Response getBadRequest400(String errorMessage, String errorReason) {
        return getBadRequest400(errorMessage, errorReason, null);
    }

    /**
     * Get 400 error with custom error code, error message and more details
     *
     * @param errorMessage error message
     * @param errorReason  error reason (details)
     * @param errorDetails error details object (json)
     * @return formatted Response
     */
    public static Response getBadRequest400(String errorMessage, String errorReason, Object errorDetails) {
        return buildErrorResponse(Response.Status.BAD_REQUEST, errorMessage, errorReason, errorDetails);
    }

    /**
     * Instruct client to visit provided location
     *
     * @param redirectLocation the location to redirect to
     * @return formatted Response
     */
    public static Response getSeeOther303(String redirectLocation) {
        try {
            return Response.seeOther(new URI(redirectLocation)).build();
        } catch (URISyntaxException e) {
            LOG.error(String.format("Redirect Location not valid: %s", redirectLocation), e);
        }
        return getInternalError500();
    }

    /**
     * Get blank 401 error message
     *
     * @return formatted Response
     */
    public static Response getUnauthorized401() {
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    /**
     * Get default 500 error message
     *
     * @return formatted Response
     */
    public static Response getInternalError500() {
        return getInternalError500(null);
    }

    /**
     * Get default 500 error message
     *
     * @param reason the reason
     * @return formatted Response
     */
    public static Response getInternalError500(String reason) {
        return buildErrorResponse(
                Response.Status.INTERNAL_SERVER_ERROR,
                "Ups, something went wrong!",
                null != reason ? reason : "Nobody knows!",
                null);
    }

    /**
     * Get default 501 error message
     *
     * @return formatted Response
     */
    public static Response getNotImplemented501() {
        return buildErrorResponse(
                Response.Status.NOT_IMPLEMENTED,
                "This feature is not implemented!",
                null,
                null);
    }

    private static Response buildErrorResponse(Response.Status status, String errorMessage, String errorReason, Object errorDetails) {
        return Response
                .status(status)
                .entity(new ResponseMessage(
                        false,
                        null,
                        new ErrorMessage(
                                String.valueOf(status.getStatusCode()),
                                null != errorMessage ? errorMessage : "",
                                null != errorReason ? errorReason : "",
                                null != errorDetails ? errorDetails : "")
                ))
                .build();
    }

    private static class ResponseMessage {

        public Boolean requestSuccess;
        public Object data;
        public ErrorMessage errorMessage;

        public ResponseMessage(Boolean requestSuccess, Object data, ErrorMessage errorMessage) {
            this.requestSuccess = requestSuccess;
            this.data = data;
            this.errorMessage = errorMessage;
        }
    }

    private static class ErrorMessage {
        public String errorCode;
        public String errorMessage;
        public String errorReason;
        public Object errorDetails;

        public ErrorMessage(String errorCode, String errorMessage, String errorReason, Object errorDetails) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
            this.errorReason = errorReason;
            this.errorDetails = errorDetails;
        }
    }
}
