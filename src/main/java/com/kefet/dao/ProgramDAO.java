/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.dao;

import com.kefet.model.Program;
import java.util.List;

/**
 *
 * @author hardddisk
 */
public interface ProgramDAO {
    
    
     void save(Program program);
    
     void update(Program program);
    
     void delete(Program program);
    
     Program getProgram(java.lang.Integer id);
    
     Long getNumberOfProgram();
    
     List<Program> getPrograms(Integer startPage, Integer pageSize);
    
}
