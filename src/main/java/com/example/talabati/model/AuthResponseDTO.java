package com.example.talabati.model;



public class AuthResponseDTO {

    private String token;      // The JWT token generated for the authenticated user
    private String tokenType;  // The type of token, typically "Bearer" for JWT

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String token, String tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

}

