package edu.uoc.som.jarvis.wordpress.platform.io;

import com.google.gson.JsonElement;
import edu.uoc.som.jarvis.core.platform.io.EventInstanceBuilder;
import edu.uoc.som.jarvis.core.platform.io.JsonEventMatcher;
import edu.uoc.som.jarvis.core.platform.io.JsonWebhookEventProvider;
import edu.uoc.som.jarvis.core.session.JarvisSession;
import edu.uoc.som.jarvis.intent.EventInstance;
import edu.uoc.som.jarvis.wordpress.platform.WordPressPlatform;
import org.apache.commons.configuration2.Configuration;
import org.apache.http.Header;

public class RestProvider extends JsonWebhookEventProvider<WordPressPlatform> {

    private final static String WP_EVENT_HEADER_KEY = "WordPress-Event";

    private JsonEventMatcher matcher;

    public RestProvider(WordPressPlatform runtimePlatform, Configuration configuration) {
        super(runtimePlatform, configuration);
        matcher = new JsonEventMatcher(EventInstanceBuilder.newBuilder(this.jarvisCore.getEventDefinitionRegistry()),
                configuration);
        JsonEventMatcher.HeaderValue commentHeader = JsonEventMatcher.HeaderValue.of(WP_EVENT_HEADER_KEY, "comment");
        matcher.addMatchableEvent(commentHeader, JsonEventMatcher.FieldValue.of("action", "created"), "NewComment");
    }

    @Override
    protected void handleParsedContent(JsonElement jsonElement, Header[] headers) {
        EventInstance eventInstance = matcher.match(headers, jsonElement);
        JarvisSession jarvisSession = jarvisCore.getOrCreateJarvisSession("wordpress");
        this.sendEventInstance(eventInstance, jarvisSession);
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
