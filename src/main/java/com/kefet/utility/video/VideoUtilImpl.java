package com.kefet.utility.video;

import java.util.List;

import com.kefet.model.UploadedFile;

public class VideoUtilImpl implements VideoUtil {

	@Override
	public boolean isThereThumbnail(List<UploadedFile> uploadedFiles) {
		boolean tumbnail = false;
		if(uploadedFiles != null){
			for(int i=0; i<uploadedFiles.size(); i++ ){
				if(uploadedFiles.get(i).getType().equals("image/png")){
					tumbnail = true; 
					break;
				}
			}
		}
		return tumbnail;
	}

}
