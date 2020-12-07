package com.patryk.musicAllStreamingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MusicEntityController {

    private static final String FILES_PATH = "your local path to directory with music files";

    @Autowired
    MusicRepository musicRepository;

    //listing all available songs
    @RequestMapping(value = "/music", method = RequestMethod.GET)
    public ResponseEntity<List<MusicEntity>> getAllSongs(@RequestParam(required = false) String songName) {
        try {
            List<MusicEntity> musicEntities = new ArrayList<MusicEntity>();

            if (songName == null) {
                musicRepository.findAll().forEach(musicEntities::add);
            } else {
                musicRepository.findBySongName(songName).forEach(musicEntities::add);
            }

            if (musicEntities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(musicEntities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //streaming a certain chosen song
    @RequestMapping(value = "/music/{title}", method = RequestMethod.GET)
    public void getSongAsByteArray(@PathVariable("title") final String title, HttpServletResponse response) throws IOException {
        File file = new File(FILES_PATH + title);
        InputStream in = new FileInputStream(file);
        response.setContentType("audio/mp3");
        response.setContentLength((int) file.length());

        FileCopyUtils.copy(in, response.getOutputStream());
    }
}
