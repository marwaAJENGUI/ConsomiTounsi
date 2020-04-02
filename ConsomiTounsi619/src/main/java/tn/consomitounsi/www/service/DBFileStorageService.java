package tn.consomitounsi.www.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tn.consomitounsi.www.entity.DBFile;
import tn.consomitounsi.www.repository.DBFileRepository;

@Service
public class DBFileStorageService {

	    @Autowired
	    private DBFileRepository dbFileRepository;

	    public String storeFile(MultipartFile file,Long fileName) throws IOException {
	        // Normalize file name
	        DBFile dbFile = new DBFile(fileName, file.getContentType(),  file.getBytes());
	         dbFileRepository.save(dbFile);
	         return "file saved";
	    }

	    public Optional<DBFile> getFile(Long  fileName) {
	        return dbFileRepository.findById(fileName);
	    }
}

