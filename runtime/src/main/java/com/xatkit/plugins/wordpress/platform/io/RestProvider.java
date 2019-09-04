package com.xatkit.plugins.wordpress.platform.io;

import com.xatkit.core.platform.io.EventInstanceBuilder;
import com.xatkit.core.platform.io.JsonEventMatcher;
import com.xatkit.core.platform.io.WebhookEventProvider;
import com.xatkit.core.server.JsonRestHandler;
import com.xatkit.core.server.RestHandlerFactory;
import com.xatkit.core.session.XatkitSession;
import com.xatkit.intent.EventInstance;
import com.xatkit.plugins.wordpress.platform.WordPressPlatform;
import org.apache.commons.configuration2.Configuration;

public class RestProvider extends WebhookEventProvider<WordPressPlatform, JsonRestHandler> {

    private final static String WP_EVENT_HEADER_KEY = "WordPress-Event";

    private final static String ENDPOINT_URI = "/wordpress";

    private JsonEventMatcher matcher;

    public RestProvider(WordPressPlatform runtimePlatform, Configuration configuration) {
        super(runtimePlatform, configuration);
        matcher = new JsonEventMatcher(EventInstanceBuilder.newBuilder(this.xatkitCore.getEventDefinitionRegistry()),
                configuration);
        JsonEventMatcher.HeaderValue commentHeader = JsonEventMatcher.HeaderValue.of(WP_EVENT_HEADER_KEY, "comment");
        matcher.addMatchableEvent(commentHeader, JsonEventMatcher.FieldValue.of("action", "created"), "NewComment");
    }

    @Override
    public String getEndpointURI() {
        return ENDPOINT_URI;
    }

    @Override
    protected JsonRestHandler createRestHandler() {
        return RestHandlerFactory.createJsonRestHandler((headers, params, content) -> {
            EventInstance eventInstance = matcher.match(headers, content);
            XatkitSession xatkitSession = xatkitCore.getOrCreateXatkitSession("wordpress");
            this.sendEventInstance(eventInstance, xatkitSession);
            return null;
        });
    }

}
