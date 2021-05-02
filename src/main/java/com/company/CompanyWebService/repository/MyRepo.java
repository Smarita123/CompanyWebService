package com.company.CompanyWebService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.company.CompanyWebService.entity.CompanyEntity;
import com.company.CompanyWebService.model.Company;

@Repository
public interface MyRepo extends CrudRepository<CompanyEntity, String> {
	
	//overriding findAll() method of CrudRepository to return a List instead of Iterable
	@Override
	List<CompanyEntity> findAll();

	Optional<CompanyEntity> findByName(String name);

	List<Optional<CompanyEntity>> findByIdAndName(String string, String name);

	List<Optional<CompanyEntity>> findByNameAndAddress(String name, String address);
	
	@Query("select c from CompanyEntity c where c.name = ?1")
	Optional <CompanyEntity> getCompanyDetailsById(String name);
	
	@Query("select c from CompanyEntity c where c.stipend > ?1")
	List<Optional<CompanyEntity>> getCompanyDetailsWhenStipendMoreThan(int value);
	
	@Query("select c from CompanyEntity c where c.stipend < ?1")
	List<Optional<CompanyEntity>>  getCompanyDetailsWhenStipendLessThan(int value);

	@Query("select c from CompanyEntity c where c.stipend between ?1 and ?2")
	List<Optional<CompanyEntity>>  getCompanyDetailsWhenStipendBetween(int value1, int value2);
	
	 @Query("select c from CompanyEntity c where c.name like %?1%")
	 List<Optional<CompanyEntity>> getCompanyNameContainingString(String name);	
	 
	 @Query("select c from CompanyEntity c where c.stipend between :value1 and :value2")
		List<Optional<CompanyEntity>>  getCompanyDetailsWhenStipendBet(int value1, int value2);

}
