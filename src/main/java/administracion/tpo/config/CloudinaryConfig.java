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
	        config.put("cloud_name", "");
	        config.put("api_key", "");
	        config.put("api_secret", "");
	        return new Cloudinary(config);
	    }
	
}