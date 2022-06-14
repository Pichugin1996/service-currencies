package com.dimastik.service.service;

import com.dimastik.service.client.GifClient;
import com.dimastik.service.dto.gifResponse.GifResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GifService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GifService.class);

    private final String GIF_API_KEY;

    private final GifClient gifClient;

    @Autowired
    public GifService(@Value("${gif.key}") String GIF_API_KEY, GifClient gifClient) {
        this.GIF_API_KEY = GIF_API_KEY;
        this.gifClient = gifClient;
    }

    public String getGif(String tag) {
        LOGGER.info("GIF: Сервис получение GIF - START");
        LOGGER.info("GIF: получен тег для поиска GIF: " + tag);
        GifResponse body = gifClient.getGif(GIF_API_KEY, tag, "1").getBody();
        LOGGER.info("GIF: От сервиса получен url gif: " +body.getData().get(0).getEmbed_url());
        return body.getData().get(0).getEmbed_url();
    }
}
