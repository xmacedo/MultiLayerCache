package br.com.xmacedo.multlayercache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MultLayerCacheApplication {

    static void main(String[] args) {
        SpringApplication.run(MultLayerCacheApplication.class, args);
    }

}
