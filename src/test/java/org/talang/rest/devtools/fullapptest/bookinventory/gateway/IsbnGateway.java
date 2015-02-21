package org.talang.rest.devtools.fullapptest.bookinventory.gateway;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.talang.rest.devtools.gateway.GatewayCommon;
import org.talang.rest.devtools.fullapptest.bookinventory.api.BookDTO;

@Service
public class IsbnGateway extends GatewayCommon {

    public static final String ISBN_GATEWAY_ENDPOINT = "http://some_3rd_party_org/api/register";

    public String registerBook(BookDTO book) {
        return genericPost(ISBN_GATEWAY_ENDPOINT, book, String.class);
    }

}
