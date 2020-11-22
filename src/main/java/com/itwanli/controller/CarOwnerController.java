package com.itwanli.controller;

import com.itwanli.entity.CarOwner;
import com.itwanli.result.ResultModel;
import com.itwanli.result.ResultModelTool;
import com.itwanli.service.CarOwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin  //允许跨域访问
@RestController
@RequestMapping("/park")
public class CarOwnerController {

    @Autowired
    CarOwnerService carOwnerService;

    @RequestMapping(value = "/carowner",method = RequestMethod.GET)
    public ResultModel getAllCarOwner(){
        List<CarOwner> carOwnerList = carOwnerService.listCarOwner();
        Map<String,List<CarOwner>> CarOwnerMap = new HashMap<>();
        if (carOwnerList!=null){
            CarOwnerMap.put("carowner",carOwnerList);
        }
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(CarOwnerMap);
        return ResultModelTool.handleResultModel(resultModel);
    }

    @GetMapping("/carowner/{current}/{size}")
    public ResultModel pageAllCarOwner(@PathVariable Integer current,@PathVariable Integer size){
        Pageable pageable = PageRequest.of(current-1,size);
        Page<CarOwner> carOwners = carOwnerService.listCarOwner(pageable);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(carOwners);
        return ResultModelTool.handleResultModel(resultModel);
    }

    @GetMapping("/carowner/{id}")
    public ResultModel getCarOwner(@PathVariable Long id){
        CarOwner carOwner = carOwnerService.getCarOwner(id);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(0);
        resultModel.setData(carOwner);
        return ResultModelTool.handleResultModel(resultModel);
    }

    @PostMapping("/carowner")
    public ResultModel addCarOwner(@RequestBody CarOwner carOwner){
        int errorCode = carOwnerService.saveCarOwner(carOwner);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(carOwner);
        return ResultModelTool.handleResultModel(resultModel);
    }


    @PutMapping("/carowner/{id}")
    public ResultModel updateCarOwner(@PathVariable Long id, @RequestBody CarOwner carOwner){
        int errorCode = carOwnerService.updateCarOwner(id,carOwner);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        resultModel.setData(carOwner);
        return ResultModelTool.handleResultModel(resultModel);
    }

    @DeleteMapping("/carowner/{id}")
    public ResultModel deleteCarOwner(@PathVariable long id){
        int errorCode = carOwnerService.deleteCarOwner(id);
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(errorCode);
        List<CarOwner> carOwnerList = carOwnerService.listCarOwner();
        Map<String,List<CarOwner>> CarOwnerMap = new HashMap<>();
        if (carOwnerList!=null){
            CarOwnerMap.put("carowner",carOwnerList);
        }
        resultModel.setData(CarOwnerMap);
        return ResultModelTool.handleResultModel(resultModel);
    }
}
