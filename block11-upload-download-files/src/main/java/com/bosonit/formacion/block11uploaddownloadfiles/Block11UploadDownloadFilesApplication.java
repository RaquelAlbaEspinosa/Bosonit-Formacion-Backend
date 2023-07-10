package com.bosonit.formacion.block11uploaddownloadfiles;

import com.bosonit.formacion.block11uploaddownloadfiles.storage.service.StorageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.bosonit.formacion.block11uploaddownloadfiles.storage.StorageProperties;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Block11UploadDownloadFilesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block11UploadDownloadFilesApplication.class, args);
	}
	@Bean
	org.springframework.boot.CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			if(args.length == 1) {
				storageService.changeLocation(Path.of(args[0]));
			}
			//storageService.deleteAll();
			storageService.init();
		};
	}

}
