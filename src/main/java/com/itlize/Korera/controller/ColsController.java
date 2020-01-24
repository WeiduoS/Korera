package com.itlize.Korera.controller;

import com.itlize.Korera.entities.Cols;
import com.itlize.Korera.entities.Resource;
import com.itlize.Korera.service.ColsServices;
import com.itlize.Korera.service.ResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Weiduo
 * @date 2020/1/10 - 12:48 PM
 */
@RestController
@RequestMapping("/cols")
public class ColsController {

    @Autowired
    @Qualifier(value = "ColsServicesImpl")
    private ColsServices colsServices;


    @RequestMapping(value = "/findById/{col_id}",method = RequestMethod.GET)
    public Cols findByID(@PathVariable("col_id") Integer id){
        if (colsServices == null) return null;
        Cols col = colsServices.getColsById(id);
        return col;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Cols> addResource(RequestEntity<Cols> requestEntity){
        Cols col = requestEntity.getBody();

        HttpStatus status = null;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        int res = -1;
        res = colsServices.addCols(col);

        if(res > 0) status = HttpStatus.CREATED;
        else status = HttpStatus.BAD_REQUEST;

        ResponseEntity<Cols> responseEntity = new ResponseEntity<>(col,
                headers,
                status);
        return responseEntity;

    }

    @RequestMapping(value = "/update/{cols_id}", method = RequestMethod.PUT)
    public ResponseEntity<Cols> updateResource(@ModelAttribute("cols") Cols cols, RequestEntity<Cols> requestEntity){
        cols = updateColMapping(cols, requestEntity.getBody());

        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        int res = colsServices.saveOrUpdateCols(cols);

        if(res > 0) status = HttpStatus.OK;
        else status = HttpStatus.BAD_REQUEST;

        ResponseEntity<Cols> responseEntity = new ResponseEntity<>(cols,
                headers,
                status);
        return responseEntity;
    }

    @RequestMapping(value = "/delete/{cols_id}", method = RequestMethod.DELETE)
    public ResponseEntity<Cols> deleteResource(@ModelAttribute("cols") Cols cols){
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=3600"));
        headers.put("Content-Type", Arrays.asList("application/json;charset=UTF-8"));

        int res = colsServices.removeCols(cols);

        if(res > 0) status = HttpStatus.OK;
        else status = HttpStatus.BAD_REQUEST;

        ResponseEntity<Cols> responseEntity = new ResponseEntity<>(cols,
                headers,
                status);
        return responseEntity;
    }


    @ModelAttribute(value = "/*/{col_id}")
    public void preColRequest(@PathVariable(value = "col_id", required = false) Integer col_id, Model model) {
        if(col_id != null) {
            Cols cols = colsServices.getColsById(col_id);
            if(cols == null) model.addAttribute("cols", new Cols());
            else model.addAttribute("cols", cols);
        }
    }

    private Cols updateColMapping(Cols db_cols, Cols web_cols) {
        if(web_cols.getField() != null && !web_cols.getField().equals("")) db_cols.setField(web_cols.getField());
        if(web_cols.getFormula() != null && !web_cols.getFormula().equals("")) db_cols.setFormula(web_cols.getFormula());
        if(web_cols.getType() != null && !web_cols.getType().equals("")) db_cols.setType(web_cols.getType());
        if(web_cols.getValue() != null && !web_cols.getValue().equals("")) db_cols.setValue(web_cols.getValue());
        return db_cols;
    }

}
