package org.talang.rest.devtools.fullapptest.bookinventory.gateway;

import org.springframework.stereotype.Component;
import org.talang.rest.devtools.gateway.GatewayCommon;
import org.talang.rest.devtools.fullapptest.bookinventory.api.BookDTO;

@Component
public class IsbnGateway extends GatewayCommon {

    public static final String ISBN_GATEWAY_ENDPOINT = "http://localhost:8080/api/register";

    public String registerBook(BookDTO book) {
        return genericPost(ISBN_GATEWAY_ENDPOINT, book, String.class);
    }

}
