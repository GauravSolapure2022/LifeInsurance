package com.cybage.genworth.insurance.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.genworth.insurance.exception.PolicyTypeException;
import com.cybage.genworth.insurance.model.Policy;
import com.cybage.genworth.insurance.service.PolicyService;

/**
 *
 * @author Gaurav
 *
 */
@RestController
@RequestMapping("/policy")
public class PolicyController {

	private static final Logger logger = LoggerFactory.getLogger(PolicyController.class);

	@Autowired
	private PolicyService policyService; //policyService is the reference

	/**
	 * This method is used to get all the policy details.
	 * 
	 */
	
	@GetMapping("/getAll")
	public @ResponseBody Iterable<Policy> getAllPolicy() throws Exception {
		logger.info("Policy Rest Controller Implementation : getAllPolicy() method");
		return policyService.getAllPolicy();  //here we are calling //
	}

	/*
	 * This method is used to store the policy details for particular user.
	 */
	
	@PostMapping("/save")
	public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy, HttpServletRequest request)
			throws Exception {
		Policy createdPolicy = this.policyService.savePolicy(policy);
		logger.info("Policy Rest Controller Implementation : createPolicy() method");
		return ResponseEntity.ok().body(createdPolicy);
	}

	/*
	 * This method is used to get the policy details of user by using user id.
	 */
	@GetMapping("/get/{id}")
	public ResponseEntity<Policy> getPolicyById(@PathVariable("id") Integer id) {
		Policy policy = policyService.getPolicyById(id);
		logger.info("Policy Rest Controller Implementation : getPolicyById() method");
		return ResponseEntity.ok().body(policy);
	}

	/*
	 * This method is used to delete the policy details of user by using id.
	 */
	
	@DeleteMapping("/delete/{id}")
	public void deletePolicyById(@PathVariable("id") Integer id) throws PolicyTypeException {
		logger.info("Policy Rest Controller Implementation : deletePolicyById() method");
		policyService.deletePolicy(id);
	}
}
