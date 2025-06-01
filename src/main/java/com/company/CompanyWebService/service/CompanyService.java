package com.company.CompanyWebService.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.company.CompanyWebService.entity.CompanyEntity;
import com.company.CompanyWebService.model.APIResponse;
import com.company.CompanyWebService.model.Company;
import com.company.CompanyWebService.model.GenericResponse;
import com.company.CompanyWebService.repository.MyRepo;

@Service
public class CompanyService  {
	ArrayList<String> suceessMessageList = new ArrayList<>();
	ArrayList<String> errorMessageList = new ArrayList<>();
	@Autowired
	MyRepo myRepo;


	public GenericResponse createCompanyService(Company company) {
		System.out.println("abc...");
		System.out.println("inside createCompanyService()");
		CompanyEntity companyEntity =new CompanyEntity();
		GenericResponse genericResponse= null;
		companyEntity.setAddress(company.getAddress());
		companyEntity.setName(company.getName());
		CompanyEntity response =myRepo.save(companyEntity);
		System.out.println(response.toString());
		String successmessage = "New company created with name: "+response.getName();
		System.out.println(successmessage);
		suceessMessageList.clear();;
		suceessMessageList.add(successmessage);
		genericResponse = new GenericResponse("SUCCESS", suceessMessageList);
		return genericResponse;
	}


	public GenericResponse viewCompanyService(){

		List <CompanyEntity> companyList = myRepo.findAll();
		System.out.println ("*companyList:"+companyList);
		System.out.println ("*GenercResponseServiceClass:"+new GenericResponse (companyList, "Fetched all records"));
		return new GenericResponse (companyList, "Fetched all records");

		/*
		HashMap<Integer, Company> map = new HashMap<>();
		Iterable<CompanyEntity> al= new ArrayList<> () ;
		al=myRepo.findAll();
		System.out.println (al);
		int i=0;
		for (CompanyEntity companyEntity : al) {
			i++;
			Company company = new Company();
			company.setAddress(companyEntity.getAddress()); 
			company.setName(companyEntity.getName());	
			map.put(i, company);
		}	
		return map;
		 */

	}


	public ResponseEntity<APIResponse> deleteCompanyById(String id) {

		myRepo.deleteById(id);	
		APIResponse apiResponse = new APIResponse();
		apiResponse.setStatusCode("OK");
		apiResponse.setSucessMessage("Company with id= "+id+" has been deleted");
		return new ResponseEntity<APIResponse>(apiResponse,HttpStatus.OK);

		//APIResponse apiResponse=myRepoSQL.deleteCompanyUsingid(id).getBody();		
		//return myRepoSQL.deleteCompanyUsingid(id);
	}


	public Optional<CompanyEntity> findById(String id) {

		Optional<CompanyEntity> companyentity=myRepo.findById(id)	;
		return companyentity;

	}
	public Optional<CompanyEntity> findByName(String name) {
		//Optional<CompanyEntity> companyentity=myRepo.findName(name)	;
		Optional<CompanyEntity> companyentity = myRepo.getCompanyDetailsById(name);
		return companyentity;
	}
	public List<Optional<CompanyEntity>> findNameContaining(String name) {
		List <Optional<CompanyEntity>> companyentitylist=myRepo.getCompanyNameContainingString(name)	;
		return companyentitylist;
	}
	public List<Optional<CompanyEntity>> findByIdAndName(String string, String name) {
		List <Optional<CompanyEntity>> companyentitylist=myRepo.findByIdAndName(string,name)	;
		return companyentitylist;
	}
	public List<Optional<CompanyEntity>> findByNameAndAddress(String name, String address) {
		List <Optional<CompanyEntity>> companyentitylist=myRepo.findByNameAndAddress(name, address)	;
		return companyentitylist;
	}

	public CompanyEntity updateCompany(String id, String companyAddress) {

		Optional<CompanyEntity> record = findById(id)	;
		CompanyEntity companyentity = record.get();
		companyentity.setAddress(companyAddress);
		return myRepo.save(companyentity);

	}


	public CompanyEntity updateCompany(String id, Company company) {
		Optional<CompanyEntity> record = findById(id)	;
		CompanyEntity companyentity = record.get();
		companyentity.setAddress(company.getAddress());
		companyentity.setName(company.getName());
		return myRepo.save(companyentity);
	}
	
	public List<Optional<CompanyEntity>> stipendMoreThan(int value) {
		List <Optional<CompanyEntity>> companyentitylist= myRepo.getCompanyDetailsWhenStipendMoreThan(value);
		return companyentitylist;
	}

	public List<Optional<CompanyEntity>> stipendLessThan(int value) {
		List <Optional<CompanyEntity>> companyentitylist= myRepo.getCompanyDetailsWhenStipendLessThan(value);
		return companyentitylist;
	}
	
	public List<Optional<CompanyEntity>> stipendBetween(int value1, int value2) {
		//List <Optional<CompanyEntity>> companyentitylist= myRepo.getCompanyDetailsWhenStipendBetween(value1, value2);
		List <Optional<CompanyEntity>> companyentitylist= myRepo.getCompanyDetailsWhenStipendBet(value1, value2);
		return companyentitylist;
	}









}
