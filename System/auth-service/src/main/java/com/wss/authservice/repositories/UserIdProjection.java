package com.wss.authservice.repositories;

import org.springframework.beans.factory.annotation.Value;

public interface UserIdProjection {
    @Value("#{target.id}")
    long getId();
}
