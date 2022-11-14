package com.example.sociallogin2.converters;

import com.example.sociallogin2.model.ProviderUser;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class DelegatingProviderUserConverter implements
    ProviderUserConverter<ProviderUserRequest, ProviderUser> {

    private List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> converters;

    public DelegatingProviderUserConverter() {
        List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> providerUserConverters =
            Arrays.asList(new GoogleProviderUserConverter(),
                          new KakaoProviderUserConverter());
        this.converters = Collections.unmodifiableList(new LinkedList<>(providerUserConverters));
    }

    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        Assert.notNull(providerUserRequest, "providerUserRequest cannot be null");

        for (ProviderUserConverter<ProviderUserRequest,ProviderUser> converter : this.converters) {
            ProviderUser providerUser = converter.converter(providerUserRequest);
            if (providerUser != null) {
                return providerUser;
            }
        }
        return null;
    }
}
