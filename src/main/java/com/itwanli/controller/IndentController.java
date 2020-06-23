package com.itwanli.controller;

import com.itwanli.entity.Indent;
import com.itwanli.result.ResultModel;
import com.itwanli.result.ResultModelTool;
import com.itwanli.service.IndentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin  //允许跨域访问
@RestController
@RequestMapping("/park")
public class IndentController {
    
    @Autowired
    private IndentService indentService;

    @GetMapping("/indent")
    public ResultModel indents(){
        List<Indent> indentList = indentService.listIndent();
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(indentList);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /*根据用户id查询所有订单*/
    @GetMapping("/indent/user/{uid}")
    public ResultModel listIndentUser(@PathVariable Long uid){
        List<Indent> indentList = indentService.listIndent(uid);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(indentList);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /*根据用户id查询所有订单*/
    @GetMapping("/indent/carowner/{cid}")
    public ResultModel listIndentCarOwner(@PathVariable Long cid){
        List<Indent> indentList = indentService.listIndentByCarOwner(cid);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(indentList);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /*根据订单编号查询订单*/
    @GetMapping("/indent/{numbers}")
    public ResultModel getOne(@PathVariable String numbers){
        Indent indent = indentService.getIndent(numbers);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(indent);
        return ResultModelTool.handleResultModel(resultModel);
    }

/*    *//*根据id查询*//*
    @GetMapping("/indent/{id}")
    public ResultModel getOne(@PathVariable Long id){
        Indent indent = indentService.getIndent(id);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(indent);
        return ResultModelTool.handleResultModel(resultModel);
    }*/

    /*通过post提交来下订单*/
    @PostMapping("/indent/{uid}/{cid}")
    public ResultModel addIndent(@PathVariable Long uid,@PathVariable Long cid,@RequestBody Indent indent){
        int errorCode = indentService.saveIndent(uid, cid, indent);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(indent);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /*评价订单*/
    @PostMapping("/indent/{id}")
    public ResultModel saveEvaluate(@PathVariable Long id,@RequestBody Indent indent){
        int errorCode = indentService.saveEvaluate(id, indent);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(indent);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /*通过put提交来修改*/
    @PutMapping("/indent/{id}")
    public ResultModel updateIndent(@PathVariable Long id, @RequestBody Indent indent) {
        int errorCode = indentService.updateIndent(id, indent);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(indent);
        return ResultModelTool.handleResultModel(resultModel);

    }

    @DeleteMapping("/indent/{id}")
    public ResultModel delete(@PathVariable Long id){
        int errorCode = indentService.deleteIndent(id);
        List<Indent> indentList = indentService.listIndent();
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(indentList);
        return ResultModelTool.handleResultModel(resultModel);
    }
}
