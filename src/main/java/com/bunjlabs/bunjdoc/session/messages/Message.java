package com.bunjlabs.bunjdoc.session.messages;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ErrorMessage.class, name = "error"),
        @JsonSubTypes.Type(value = AuthMessage.class, name = "auth"),
        @JsonSubTypes.Type(value = AuthTokenMessage.class, name = "token"),
        @JsonSubTypes.Type(value = EchoMessage.class, name = "echo")
})
public abstract class Message {

}
