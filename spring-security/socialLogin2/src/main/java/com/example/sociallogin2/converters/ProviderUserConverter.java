package com.example.sociallogin2.converters;

public interface ProviderUserConverter<T,R> {

    R converter(T t);
}
