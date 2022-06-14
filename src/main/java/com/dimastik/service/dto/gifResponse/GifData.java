package com.dimastik.service.dto.gifResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class GifData {
    private String type;
    private String id;
    private String embed_url;
}
