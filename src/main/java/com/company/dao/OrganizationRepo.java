package com.company.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.beans.Organization;

@Repository
public interface OrganizationRepo extends CrudRepository<Organization, Integer>{

}
