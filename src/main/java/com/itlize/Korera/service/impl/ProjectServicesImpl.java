package com.itlize.Korera.service.impl;

import com.itlize.Korera.service.ProjectServices;
import com.itlize.Korera.entities.Project;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Weiduo
 * @date 2019/12/30 - 4:00 PM
 */
@Service("ProjectServices")
public class ProjectServicesImpl implements ProjectServices {
    @Override
    public List<Project> findAll() {
        System.out.println("business layer: find all projects services");
        return null;
    }

    @Override
    public int savePoject(Project project) {
        System.out.println("business layer: save a project into database");
        return 0;
    }
}
