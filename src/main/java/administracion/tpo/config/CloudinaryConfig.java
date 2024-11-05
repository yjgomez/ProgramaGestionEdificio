package administracion.tpo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {

	    @Bean
	    public Cloudinary cloudinary() {
	    	Map<String, String> config = new HashMap<>();
	        config.put("cloud_name", "dr0mbyc6d");
	        config.put("api_key", "872472423517251");
	        config.put("api_secret", "QWNvPlLQK7AJhtRzx_OtqHi1IRw");
	        return new Cloudinary(config);
	    }
	
}