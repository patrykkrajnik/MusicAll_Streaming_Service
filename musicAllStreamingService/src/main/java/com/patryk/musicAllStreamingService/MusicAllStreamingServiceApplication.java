package com.patryk.musicAllStreamingService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class MusicAllStreamingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicAllStreamingServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner saveData(MusicRepository musicRepository) {
		String DIRECTORY_PATH = "your local path to directory with music files";
		File files = new File(DIRECTORY_PATH);
		return (args -> {
			for (File file : files.listFiles()) {
				if (file.isFile() & !file.getName().equals(".DS_Store")) {
					String baseName = file.getName().replaceAll("\\.[^.]*$", "");
					musicRepository.save(new MusicEntity(baseName, "http://localhost:8080/api/music/" + baseName + ".mp3"));
				}
			}
		});
	}
}
