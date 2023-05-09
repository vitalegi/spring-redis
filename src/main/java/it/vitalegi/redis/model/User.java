package it.vitalegi.redis.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("User")
@Data
public class User implements Serializable {
    int id;
    String name;
}
