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
        return Response.ok(data).build();
    }

    public static Response getCreated201() {
        return Response.status(Response.Status.CREATED).build();
    }

    public static Response getCreated201(Object data) {
        return Response.status(Response.Status.CREATED).entity(data).build();
    }

    public static Response getNoContent204() {
        return Response.status(Response.Status.NO_CONTENT).build();
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
     * Get 400 error with custom error code, error message and more details
     *
     * @param errorMessage error message
     * @return formatted Response
     */
    public static Response getBadRequest400(String errorMessage) {
        return buildErrorResponse(Response.Status.BAD_REQUEST, errorMessage);
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
     * Get blank 404 error
     *
     * @return formatted Response
     */
    public static Response getNotFound404() {
        return getNotFound404("Unable to find requested source!!!");
    }

    /**
     * Get 404 error with error message
     *
     * @param errorMessage error message
     * @return formatted Response
     */
    public static Response getNotFound404(String errorMessage) {
        return buildErrorResponse(Response.Status.NOT_FOUND, errorMessage);
    }


    /**
     * Get default 500 error message
     *
     * @return formatted Response
     */
    public static Response getInternalError500() {
        return getInternalError500("Ups, something went wrong!");
    }

    /**
     * Get default 500 error message
     *
     * @param reason the reason
     * @return formatted Response
     */
    private static Response getInternalError500(String reason) {
        return buildErrorResponse(
                Response.Status.INTERNAL_SERVER_ERROR,
                reason);
    }

    /**
     * Get default 501 error message
     *
     * @return formatted Response
     */
    public static Response getNotImplemented501() {
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }

    private static Response buildErrorResponse(Response.Status status, String errorMessage) {
        return Response
                .status(status)
                .entity(new ErrorMessage(
                        null != errorMessage ? errorMessage : ""
                ))
                .build();
    }


    private static class ErrorMessage {
        String errorMessage;

        ErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
