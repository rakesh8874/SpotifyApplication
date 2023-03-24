package com.niit.musicapplication;

import com.niit.musicapplication.filter.JWTTockenFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@SpringBootApplication
@EnableFeignClients
public class MusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicApplication.class, args);
	}



	@Bean
	public FilterRegistrationBean filterJWTocken(){
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new JWTTockenFilter());
		frb.addUrlPatterns("/usersplaylist/createPlaylist",
				"/usersplaylist/allplaylist",
				"/users/addUserPlaylist",
				"/users/getAllPlaylist",
				"/playlistDetails/addsong/*",
				"/playlistDetails/getsong/*",
				"/users/addSongIntoPlaylist/*",
				"/users/deletePlaylist/*",
				"/users/deleteSongs/*",
				"/users/getAllMusicOfPlaylist/*");
		return frb;
	}

	@Bean
	FilterRegistrationBean configuration(){
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200/");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		final UrlBasedCorsConfigurationSource source =  new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}

}
