package me.piggypiglet.moviemanager.http;

import fi.iki.elonen.NanoHTTPD;

// ------------------------------
// Copyright (c) PiggyPiglet 2019
// https://www.piggypiglet.me
// ------------------------------
final class ResponseHandler {
    NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession session) {
        return NanoHTTPD.newFixedLengthResponse("<html><body><h1>test</h1></body></html>");
    }
}