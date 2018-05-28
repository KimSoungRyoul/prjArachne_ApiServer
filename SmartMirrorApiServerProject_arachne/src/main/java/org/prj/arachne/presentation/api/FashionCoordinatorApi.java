package org.prj.arachne.presentation.api;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.prj.arachne.model.fileinfo.valueObj.FileType;
import org.prj.arachne.presentation.api.urlmapper.Version1ApiMapping;
import org.prj.arachne.util.file.MediaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


@RestController
public class FashionCoordinatorApi implements Version1ApiMapping {

    @Autowired
    @Qualifier("uploadFilePath")
    private String uploadPath;


    @ApiOperation(value="의상을 추천합니다",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name="memberId",dataType="String",
                    value="회원 아이디",
                    paramType="path")
            ,@ApiImplicitParam(name="x-auth-token",value = "인증토큰",required = true, paramType = "header" ,dataType = "string")
    })
    @PostMapping("/fashion/{memberId}")
    public ResponseEntity<InputStreamResource> analysisFashion(@PathVariable("memberId")Long id,
                                                               @RequestBody @RequestParam("file") MultipartFile file){
        File file2 = new File(uploadPath+File.separator+"model_man.png");

        MediaType mType = MediaUtils.getMediaType(FileType.PNG.toString().toUpperCase());


        HttpHeaders headers = new HttpHeaders();

        if(mType!=null){
            headers.setContentType(mType);
        }else{

            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        }

        headers.add("Content-dispositon", "attachment; filename=\"" + "model_man"+"."+"png"+"\";");

        try {
            return ResponseEntity.ok().headers(headers).contentLength(file2.length())

                    .body(new InputStreamResource(new FileInputStream(file2)));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
