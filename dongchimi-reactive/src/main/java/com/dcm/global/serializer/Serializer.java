package com.dcm.global.serializer;

import org.springframework.lang.Nullable;

public interface Serializer<T> {

    T deserialize(@Nullable byte[] bytes);

}
