package io.demo.credit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.demo.credit.util.Constants;
import io.demo.credit.util.Messages;
import io.demo.credit.exception.RestObjectNotFoundException;
import io.demo.credit.model.CreditCard;
import io.demo.credit.service.CreditCardService;

@Validated
@RestController
public class CreditController extends CommonController{

	private static final Logger LOG = LoggerFactory.getLogger(CreditController.class);
	
	@Autowired
	private CreditCardService creditCardService;
	
	/*
	 * Get credit card by id
	 */
	@GetMapping (Constants.URI_API_CREDIT_CARD)
	public ResponseEntity<?> getCreditCard(@PathVariable(Constants.PATH_VARIABLE_ID) Long id){
		
		LOG.debug("Get Credit Card with id = " + id);
		
		CreditCard card = creditCardService.getCreditCard(id);
		
		if (card != null) {
			return ResponseEntity.ok(card);
		} else {
			throw new RestObjectNotFoundException (Messages.OBJECT_NOT_FOUND + id);
		}
	}
	
	
	
}
