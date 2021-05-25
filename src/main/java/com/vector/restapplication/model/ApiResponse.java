package com.vector.restapplication.model;

/**
 * This class represents an custom api response
 */
public class ApiResponse {

    /**
     * The response status code
     */
    private String code;

    /**
     * The response message
     */
    private String message;

    /**
     * The default ApiResponse constructor
     */
    public ApiResponse() {
        this.code = "";
        this.message = "";
    }

    /**
     * The ApiResponse constructor
     * @param code
     * @param message
     */
    public ApiResponse(Integer code, String message) {
        this.code = code.toString(); // I wouldve left this as an Integer. Looks like the only thing that uses it is the toString.
        this.message = message;
    }

    /**
     * @return the response status code
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @return the response message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param code - the status code to be set
     */
    public void setcode(String code) {
        this.code = code;
    }

    /**
     * @param message - the message to be set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return a String representing the ApiResponse
     */
    public String toString() {
        return "{" + "code:" + this.code + ", message'" + this.message + "}";
    }
    
}
