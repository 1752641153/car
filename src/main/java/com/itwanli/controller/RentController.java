package com.itwanli.controller;

import com.itwanli.entity.Rent;
import com.itwanli.result.ResultModel;
import com.itwanli.result.ResultModelTool;
import com.itwanli.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin  //允许跨域访问
@RestController
@RequestMapping("/park")
public class RentController {

    @Autowired
    private RentService rentService;

    @GetMapping("/rent")
    public ResultModel rents(){
        List<Rent> rentList = rentService.listRent();
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(rentList);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /**
     * 分页查询车位出租信息
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/rent/{page}/{size}")
    public ResultModel pageRent(@PathVariable Integer page,@PathVariable Integer size){
        Pageable pageable = PageRequest.of(page-1,size);
        Page<Rent> rents = rentService.pageRent(pageable);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(rents);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /*查询车主租赁时间*/
    @GetMapping("/rent/carowner/{cid}")
    public ResultModel rents(@PathVariable Long cid){
        List<Rent> rentList = rentService.listRent(cid);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(rentList);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /*根据id查询*/
    @GetMapping("/rent/{id}")
    public ResultModel getOne(@PathVariable Long id){
        Rent rent = rentService.getRent(id);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(rent);
        return ResultModelTool.handleResultModel(resultModel);
    }

    /*通过post提交来保存新增*/
    @PostMapping("/rent/{cid}")
    public ResultModel addRent(@PathVariable Long cid,@RequestBody Rent rent){
        int errorCode = rentService.saveRent(cid, rent);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(rent);
        return ResultModelTool.handleResultModel(resultModel);
    }


    /*通过put提交来修改*/
    @PutMapping("/rent/{id}")
    public ResultModel updateRent(@PathVariable Long id, @RequestBody Rent rent) {
        int errorCode = rentService.updateRent(id, rent);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(rent);
        return ResultModelTool.handleResultModel(resultModel);

    }

    @DeleteMapping("/rent/{id}")
    public ResultModel delete(@PathVariable Long id){
        int errorCode = rentService.deleteRent(id);
        List<Rent> rentList = rentService.listRent();
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(rentList);
        return ResultModelTool.handleResultModel(resultModel);
    }

}
