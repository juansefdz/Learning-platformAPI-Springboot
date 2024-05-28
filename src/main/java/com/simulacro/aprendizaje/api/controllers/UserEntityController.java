package com.simulacro.aprendizaje.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simulacro.aprendizaje.api.dto.request.UserRequest;
import com.simulacro.aprendizaje.api.dto.response.UserResponse;
import com.simulacro.aprendizaje.infraestructure.abstract_services.IUserEntityService;
import com.simulacro.aprendizaje.utils.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping (path = "/user")
@AllArgsConstructor
@Controller
public class UserEntityController {

    @Autowired
    private final IUserEntityService objIUserEntityService;

    
    public ResponseEntity<Page<UserResponse>> getAll(
        @RequestParam(defaultValue ="1") int page,
        @RequestParam(defaultValue="10")int size){
            
            return ResponseEntity.ok(this.objIUserEntityService.getAll(page -1 , size, SortType.NONE));
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id){

        return ResponseEntity.ok(this.objIUserEntityService.getById(id));
    }
    
    @PostMapping(path="/create")
    public ResponseEntity<UserResponse>create(
        @Validated  @RequestBody UserRequest request){
            return ResponseEntity.ok(this.objIUserEntityService.create(request));
        }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.objIUserEntityService.delete(id);
            
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<UserResponse> update(
        @Validated  @RequestBody UserRequest request, @PathVariable Long id){
            return ResponseEntity.ok(this.objIUserEntityService.update(request,id));
        }
    


}
