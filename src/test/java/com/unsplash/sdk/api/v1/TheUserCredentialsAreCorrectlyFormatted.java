package com.unsplash.sdk.api.v1;

import com.unsplash.sdk.api.stubs.UserCredentialsStub;
import com.unsplash.sdk.exceptions.WrongJsonUserCredentials;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TheUserCredentialsAreCorrectlyFormatted {

    @Test
    public void the_uri_format_is_correct() {
        UserV1Credentials userCredentials = UserCredentialsStub.makeForV1();

        String uri = userCredentials.toUriFormat().toString();

        assertTrue(uri.contains("client_id=" + userCredentials.getClientId()));
        assertTrue(uri.contains("client_secret=" + userCredentials.getClientSecret()));
        assertTrue(uri.contains("redirect_uri=" + userCredentials.getRedirectUri()));
    }

    @Test
    public void the_json_format_is_correct() throws WrongJsonUserCredentials {
        UserV1Credentials userCredentials = UserCredentialsStub.makeForV1();

        String json = userCredentials.toJson().toJSONString();

        assertTrue(json.contains("\"client_id\":\"" + userCredentials.getClientId() + "\""));
        assertTrue(json.contains("\"client_secret\":\"" + userCredentials.getClientSecret() + "\""));
        assertTrue(json.contains("\"redirect_uri\":\"" + userCredentials.getRedirectUri() + "\""));
    }
}
