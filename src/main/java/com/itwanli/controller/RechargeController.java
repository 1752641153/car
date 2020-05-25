package com.itwanli.controller;

import com.itwanli.entity.Recharge;
import com.itwanli.result.ResultModel;
import com.itwanli.result.ResultModelTool;
import com.itwanli.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin  //允许跨域访问
@RestController
@RequestMapping("/park")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @GetMapping("/recharge")
    public ResultModel recharges(){
        List<Recharge> rechargeList = rechargeService.listRecharge();
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(rechargeList);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /*根据用户id查询所有充值记录*/
    @GetMapping("/recharge/user/{uid}")
    public ResultModel getUser(@PathVariable Long uid){
        List<Recharge> rechargeList = rechargeService.listUserRecharge(uid);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(rechargeList);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /*根据充值id查询一条充值记录*/
    @GetMapping("/recharge/{id}")
    public ResultModel getOne(@PathVariable Long id){
        Recharge recharge = rechargeService.getRecharge(id);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(recharge);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /*通过post提交来保存新增*/
    @PostMapping("/recharge/{uid}")
    public ResultModel addRecharge(@PathVariable Long uid, @RequestBody Recharge recharge){
        int errorCode = rechargeService.saveRecharge(uid, recharge);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(recharge);
        return ResultModelTool.handleResultModel(resultModel);
    }


    /*通过put提交来修改*/
    @PutMapping("/recharge/{id}")
    public ResultModel updateRecharge(@PathVariable Long id,@RequestBody Recharge recharge) {
        int errorCode = rechargeService.updateRecharge(id, recharge);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(recharge);
        return ResultModelTool.handleResultModel(resultModel);

    }

    @DeleteMapping("/recharge/{id}")
    public ResultModel delete(@PathVariable Long id){
        int errorCode = rechargeService.deleteRecharge(id);
        List<Recharge> rechargeList = rechargeService.listRecharge();
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(rechargeList);
        return ResultModelTool.handleResultModel(resultModel);
    }

}
