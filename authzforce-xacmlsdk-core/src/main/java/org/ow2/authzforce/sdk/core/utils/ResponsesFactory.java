package org.ow2.authzforce.sdk.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.ow2.authzforce.sdk.core.Utils;
import org.ow2.authzforce.sdk.core.schema.Attribute;
import org.ow2.authzforce.sdk.core.schema.Response;
import org.ow2.authzforce.sdk.core.schema.Responses;
import org.ow2.authzforce.xacml.identifiers.XACMLAttributeId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author romain.ferrari[at]thalesgroup.com
 * 
 * Not Implemented yet. 
 * This class will allow high level computation around xacml responses. Like sorting, grouping, etc...
 *
 */
public final class ResponsesFactory extends Responses {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResponsesFactory.class);
	
	private String filterAttribute;
	
	public ResponsesFactory (Responses responses) {
		this.getResponses().addAll(responses.getResponses());
	}
	
	public Responses getResponseGroupBySubject() {
		return getResponseGroupBy(XACMLAttributeId.XACML_SUBJECT_SUBJECT_ID);		
	}
	
	public Responses getResponseGroupByResource() {
		return getResponseGroupBy(XACMLAttributeId.XACML_RESOURCE_RESOURCE_ID);		
	}
	
	public Responses getResponseGroupByAction() {
		return getResponseGroupBy(XACMLAttributeId.XACML_ACTION_ACTION_ID);		
	}
	
	private Responses getResponseGroupBy(XACMLAttributeId id2GroupBy) {
		this.filterAttribute = id2GroupBy.value();
		Response sortedResponses = new Response();
		Responses responses = new Responses();
		List<Response> arrayFinal = new ArrayList<Response>();
		for (Response response : this.getResponses()) {	
			for (Attribute attr : response.getAttributes()) {
				if(attr.getAttributeId().equals(id2GroupBy.value())) {
					sortedResponses.getAttributes().addAll(response.getAttributes());
					this.filterAttribute = String.valueOf(attr.getAttributeValues().get(0).getContent()); 
				}
				sortedResponses.setDecision(response.getDecision());
			}
			
			arrayFinal.add(sortedResponses);
		}		
		
		responses.setResponses(arrayFinal);
		
		return responses;
	}
}