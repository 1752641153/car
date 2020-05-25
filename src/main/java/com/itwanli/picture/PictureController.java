package com.itwanli.picture;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin  //允许跨域访问
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Autowired
    private PictureRepository pictureRepository;

/*    @PostMapping("/save")
    public ResultModel addPicture(@RequestParam byte[] CompanyLogo){
        int errorCode = pictureService.savePicture(CompanyLogo);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(CompanyLogo);
        return ResultModelTool.handleResultModel(resultModel);
    }
    */
    /**
     *company1 获得页面上input中的name属性对应的值  input z中name属性的值跟实体中name属性是一致的不一致的话   company1不能得到输入的值
     照片是使用@RequestParam获得
     */

    @RequestMapping("/save")
    public void save(@RequestParam(name="fileField",required=false) MultipartFile fileField) throws IOException
    {
        Company company = new Company();
        company.setCompanyLogo(fileField.getBytes());
        /*company.setCompanyLogo(fileField.getContentType());*/
        log.info("sss:{},SSS:{}",fileField.getOriginalFilename(),fileField.getContentType());
//        company.setCompanyLogo(fileField.getBytes());
        pictureRepository.save(company);
    }

    @RequestMapping("/s")
    public List<Company> list(){
        return pictureRepository.findAll();
    }

    @PostMapping("/save1")
    public void save(@RequestBody Company company){
/*        Company company = new Company();
        company.setAaa(aaa);*/
        /*company.setCompanyLogo(fileField.getContentType());*/
//        company.setCompanyLogo(fileField.getBytes());
        pictureRepository.save(company);
    }
}
