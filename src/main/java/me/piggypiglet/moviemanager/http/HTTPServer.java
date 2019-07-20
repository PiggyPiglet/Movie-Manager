package me.piggypiglet.moviemanager.http;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fi.iki.elonen.NanoHTTPD;
import me.piggypiglet.moviemanager.file.framework.FileConfiguration;
import me.piggypiglet.moviemanager.guice.annotations.Config;
import org.slf4j.LoggerFactory;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
@Singleton
public final class HTTPServer {
    @Inject @Config private FileConfiguration config;
    @Inject private ResponseHandler responseHandler;

    public void start() {
        String ip = config.getString("http.ip");
        int port = config.getInt("http.port");

        try {
            new NestedServer(ip, port).start();
            LoggerFactory.getLogger("HTTP").info("HTTP Server started at {}:{}", ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final class NestedServer extends NanoHTTPD {
        NestedServer(String ip, int port) {
            super(ip, port);
        }

        @Override
        public Response serve(IHTTPSession session) {
            return responseHandler.serve(session);
        }
    }
}
