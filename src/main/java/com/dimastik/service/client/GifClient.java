package com.dimastik.service.client;

import com.dimastik.service.dto.gifResponse.GifResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gif", url = "${gif.url}")
public interface GifClient {

    @RequestMapping(value = "/v1/gifs/search", method = RequestMethod.GET)
    ResponseEntity<GifResponse> getGif(@RequestParam("apiKey") String apiKey,
                                       @RequestParam("q") String tag,
                                       @RequestParam("limit") String limit);
}
