package com.company.CompanyWebService.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.CompanyWebService.entity.CompanyEntity;
import com.company.CompanyWebService.model.APIResponse;
import com.company.CompanyWebService.model.Company;
import com.company.CompanyWebService.model.GenericResponse;
import com.company.CompanyWebService.service.CompanyService;



@RestController
//@RequestMapping("/company")
public class CompanyController {
	
	ArrayList errorMessageList= new ArrayList<>();
	@Autowired
	CompanyService companyservice;

	@PostMapping(value="/createCompany")
	public GenericResponse createCompany( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role,  @RequestBody Company company) {

		System.out.println("inside createCompany()");
		GenericResponse genericResponse;
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);
		if (role.equalsIgnoreCase("admin")) {
			genericResponse=companyservice.createCompanyService(company);
			return genericResponse;
		}
		else {
			String failuremessage = "Unauthorized";
			errorMessageList.add(failuremessage);
			genericResponse = new GenericResponse("FAILED", errorMessageList);
			return genericResponse;
		}
	}
		
	@GetMapping(value="/ViewCompanies", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public GenericResponse viewCompany( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);
	
		if (role.equalsIgnoreCase("admin")) {
			return companyservice.viewCompanyService();
			//return new ResponseEntity<>(companyservice.viewCompanyService(), HttpStatus.OK);
		}
		else {
			return null;	
			//return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);							
		}
			
	}
	

	@DeleteMapping(value="/DeleteCompany", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	//Delete using Request param
	public ResponseEntity<APIResponse> deleteCompany( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role, @RequestParam String id) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);


		if (role.equalsIgnoreCase("admin")) {
			return companyservice.deleteCompanyById(id);	
		}
		else {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);							
		}	
	}

	@DeleteMapping(value="/DeleteCompanyUsingPathVariable/{id}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	//Delete by passing Path variable
	public ResponseEntity<APIResponse> deleteCompanyPathVariable( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role, @PathVariable("id") String id) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);

		if (role.equalsIgnoreCase("admin")) {
			return companyservice.deleteCompanyById(id);	
		}
		else {

			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);							
		}					
	}
	
	@PostMapping(value="/FindById/{id}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	//Delete by passing Path variable
	public Optional<CompanyEntity> findById( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role,  @RequestBody String address,  @PathVariable("id") String id) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);

		if (role.equalsIgnoreCase("admin")) {
			return companyservice.findById(id);	
		}
		else {
			return null;							
		}					
	}
	
	@PostMapping(value="/FindByName/{name}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	//Delete by passing Path variable
	public Optional<CompanyEntity> findByName( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role, @PathVariable("name") String name) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);

		if (role.equalsIgnoreCase("admin")) {
			return companyservice.findByName(name);	
		}
		else {
			return null;							
		}					
	}

	@PostMapping(value="/FindByIdAndName", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public List<Optional<CompanyEntity>> ByIdAndName( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role, @RequestBody CompanyEntity companyentity ) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);

		if (role.equalsIgnoreCase("admin")) {
			return companyservice.findByIdAndName(companyentity.getId(),companyentity.getName());	
		}
		else {
			return null;							
		}					
	}
	
	@PostMapping(value="/FindByNameAndAddress", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public List<Optional<CompanyEntity>> ByNameAndAddress( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role, @RequestBody CompanyEntity companyentity ) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);

		if (role.equalsIgnoreCase("admin")) {
			return companyservice.findByNameAndAddress(companyentity.getName(),companyentity.getAddress());	
		}
		else {
			return null;							
		}					
	}


	@PostMapping(value="/UpdateCompanyAddressWithid/{id}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	//Delete by passing Path variable
	public CompanyEntity updateCompanyAddressWithid( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role,  @RequestParam String address,  @PathVariable("id") String id) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);

		if (role.equalsIgnoreCase("admin")) {
			return companyservice.updateCompany(id, address);	
		}
		else {
			return null;							
		}					
	}
	@PostMapping(value="/UpdateCompanyWithid/{id}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	//Delete by passing Path variable
	public CompanyEntity updateCompanyWithid( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role,  @RequestBody Company company,  @PathVariable("id") String id) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);

		if (role.equalsIgnoreCase("admin")) {
			return companyservice.updateCompany(id, company);	
		}
		else {
			return null;							
		}					
	}
	
	@PostMapping(value="/NameContaining/{value}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public List<Optional<CompanyEntity>> nameContaining( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role,  @PathVariable("value") String value) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);

		if (role.equalsIgnoreCase("admin")) {
			return companyservice.findNameContaining(value);	
		}
		else {
			return null;							
		}					
	}
	
	@PostMapping(value="/StipendMoreThan/{value}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public List<Optional<CompanyEntity>> stipendMoreThan( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role,  @PathVariable("value") int value) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);

		if (role.equalsIgnoreCase("admin")) {
			return companyservice.stipendMoreThan(value);	
		}
		else {
			return null;							
		}					
	}
	
	@PostMapping(value="/StipendLessThan/{value}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public List<Optional<CompanyEntity>> stipendLessThan( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role,  @PathVariable("value") int value) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);

		if (role.equalsIgnoreCase("admin")) {
			return companyservice.stipendLessThan(value);	
		}
		else {
			return null;							
		}					
	}
	
	@PostMapping(value="/StipendBetween/{value1}/{value2}", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE},
			produces = { MediaType.APPLICATION_JSON_VALUE, 
					MediaType.APPLICATION_XML_VALUE})
	public List<Optional<CompanyEntity>> stipendBetween( @RequestHeader("Authorization") String authorization,@RequestHeader("Role") String role,  @PathVariable("value1") int value1, @PathVariable("value2") int value2) {
		System.out.println("Authorization: "+authorization);
		System.out.println("Role: "+role);

		if (role.equalsIgnoreCase("admin")) {
			return companyservice.stipendBetween(value1, value2);	
		}
		else {
			return null;							
		}					
	}


}
