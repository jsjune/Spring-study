package org.example.springbatchprac.batch.domain;

import java.util.List;

public record ApiInfo(
    String url,
    List<? extends ApiRequestVO> apiRequestList
) {

}
