package com.xatkit.plugins.wordpress.platform.io;

import com.google.gson.JsonElement;
import com.xatkit.core.platform.io.EventInstanceBuilder;
import com.xatkit.core.platform.io.JsonEventMatcher;
import com.xatkit.core.platform.io.JsonWebhookEventProvider;
import com.xatkit.core.session.XatkitSession;
import com.xatkit.intent.EventInstance;
import com.xatkit.plugins.wordpress.platform.WordPressPlatform;
import org.apache.commons.configuration2.Configuration;
import org.apache.http.Header;

public class RestProvider extends JsonWebhookEventProvider<WordPressPlatform> {

    private final static String WP_EVENT_HEADER_KEY = "WordPress-Event";

    private JsonEventMatcher matcher;

    public RestProvider(WordPressPlatform runtimePlatform, Configuration configuration) {
        super(runtimePlatform, configuration);
        matcher = new JsonEventMatcher(EventInstanceBuilder.newBuilder(this.xatkitCore.getEventDefinitionRegistry()),
                configuration);
        JsonEventMatcher.HeaderValue commentHeader = JsonEventMatcher.HeaderValue.of(WP_EVENT_HEADER_KEY, "comment");
        matcher.addMatchableEvent(commentHeader, JsonEventMatcher.FieldValue.of("action", "created"), "NewComment");
    }

    @Override
    protected void handleParsedContent(JsonElement jsonElement, Header[] headers) {
        EventInstance eventInstance = matcher.match(headers, jsonElement);
        XatkitSession xatkitSession = xatkitCore.getOrCreateXatkitSession("wordpress");
        this.sendEventInstance(eventInstance, xatkitSession);
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                wait();
            } catch(InterruptedException e) {

            }
        }
    }
}
