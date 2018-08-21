/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kefet.dao;

import com.kefet.model.Employer;

/**
 *
 * @author hardddisk
 */
public interface EmployerDAO {
    
     void save(Employer employer);
    
     void update(Employer employer);
    
     void delete(Employer employer);
    
     Employer getEmployer(Integer id);
    
}
