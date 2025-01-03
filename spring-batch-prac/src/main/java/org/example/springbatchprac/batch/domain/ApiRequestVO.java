package org.example.springbatchprac.batch.domain;

public record ApiRequestVO(
    long id,
    ProductVO productVO,
    ApiResponseVO apiResponseVO
) {
    public static ApiRequestVO from(ProductVO productVO) {
        return new ApiRequestVO(productVO.id(), productVO, null);
    }

    public static ApiRequestVO from(Product product) {
        return from(ProductVO.from(product));
    }

    public ApiRequestVO withApiResponseVO(ApiResponseVO apiResponseVO) {
        return new ApiRequestVO(id, productVO, apiResponseVO);
    }

}
