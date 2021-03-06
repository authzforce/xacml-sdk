package com.thalesgroup.authzforce.sdk.tests;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;
import org.ow2.authzforce.sdk.core.Utils;
import org.ow2.authzforce.sdk.core.schema.Response;
import org.ow2.authzforce.sdk.core.schema.Responses;
import org.ow2.authzforce.sdk.exceptions.XacmlSdkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestResponseFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(TestResponseFactory.class);
	private static final String REQUEST_FILES_PATH = "src/test/resources/requests";
	private static final String RESPONSE_FILES_PATH = "src/test/resources/responses";

//	@Test
//	public void testGetResponseGroupBySubject() throws FileNotFoundException, JAXBException, XacmlSdkException {
//		Responses responses = Utils.extractResponse(com.thalesgroup.authzforce.sdk.tests.utils.Utils.createResponse(RESPONSE_FILES_PATH + "/TestResponseFactoryGetResponseGroupBySubject.xml")).getResponseGroupBySubject();		
//		for (Response response : responses.getResponses()) {
//			LOGGER.debug(response.getSubjectId());
//			Assert.assertEquals("thales", response.getSubjectId());
//			if(response.getActionId().equals("HEAD")) {
//				Assert.assertEquals("Deny", response.getDecision().value());
//			} else {
//				Assert.assertEquals("Permit", response.getDecision().value());
//			}			
//			Assert.assertEquals("http://www.thalesgroup.com", response.getResourceId());			
//		}		
//	}
//	
//	@Test
//	public void testGetResponseGroupByResource() {
//		Assert.fail("Not implemented");
//	}
//	
//	@Test
//	public void testGetResponseGroupByAction() {
//		Assert.fail("Not implemented");
//	}
}
