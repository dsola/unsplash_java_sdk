package com.unsplash.sdk.api.v1.resources;

import lombok.Data;

@Data
public class UserProfile {
    private String $id;
    private String $userName;
    private String $firstName;
    private String $lastName;
    private String $bio;
    private String $location;

}
