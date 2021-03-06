/**
 * Copyright (C) 2013-2014 Thales Services - ThereSIS - All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.ow2.authzforce.sdk.impl;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.Response;
import org.ow2.authzforce.sdk.XacmlSdk;
import org.ow2.authzforce.sdk.core.Net;
import org.ow2.authzforce.sdk.core.Utils;
import org.ow2.authzforce.sdk.core.schema.Request;
import org.ow2.authzforce.sdk.core.schema.category.ActionCategory;
import org.ow2.authzforce.sdk.core.schema.category.EnvironmentCategory;
import org.ow2.authzforce.sdk.core.schema.category.ResourceCategory;
import org.ow2.authzforce.sdk.core.schema.category.SubjectCategory;
import org.ow2.authzforce.sdk.core.utils.ResponsesFactory;
import org.ow2.authzforce.sdk.exceptions.XacmlSdkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * This Library is about XACML and XML Processing tools to make the developers'
 * life easier.
 * 
 * @author Romain FERRARI, romain.ferrari[AT]thalesgroup.com
 * @version 0.5
 * 
 */
public class XacmlSdkImpl implements XacmlSdk {

	private static final Logger LOGGER = LoggerFactory.getLogger(XacmlSdkImpl.class);

	public final Net networkHandler;

	/**
	 * This constructor is multi tenant enabled. The final endpoint will be
	 * something like: http://serverEndpoint/domains/{domainId}/pdp
	 *  @param serverEndpoint
	 *            is the PDP endpoint
	 * @param domainId the UUID of the target domain (or domain alias if doDomainIdTranslation is true)
	 * @param doDomainIdTranslation if set to true domainId will be taken as an alias instead of UUID
	 */
	public XacmlSdkImpl(URI serverEndpoint, String domainId, MultivaluedMap<String, String> customHeaders, boolean doDomainIdTranslation) {
		networkHandler = new Net(serverEndpoint, domainId, customHeaders, doDomainIdTranslation);
	}

	public XacmlSdkImpl(URI serverEndpoint, String domainId, MultivaluedMap<String, String> customHeaders) {
		this(serverEndpoint, domainId, customHeaders, false);
	}


	public XacmlSdkImpl(URI serverEndpoint, String domainId) {
		networkHandler = new Net(serverEndpoint, domainId, null, false);
	}

	public ResponsesFactory getAuthZ(List<SubjectCategory> subject, List<ResourceCategory> resources,
			List<ActionCategory> actions, List<EnvironmentCategory> environment) throws XacmlSdkException {
		// XACML Request creation
			final Request request = Utils.createXacmlRequest(subject, resources, actions, environment);

			try {
				LOGGER.debug("Calling PDP using network handler: {}", networkHandler);
				final Response rawResponse = networkHandler.getMyDomainResource().getPdpResource().requestPolicyDecision(request);
				if (LOGGER.isDebugEnabled()) {
					Utils.logRawResponse(rawResponse);
				}
				return Utils.extractResponse(rawResponse);
			} catch (javax.ws.rs.NotFoundException e) {
				throw new XacmlSdkException("HTTP 404: Authorization server not found", e);
			} catch (javax.ws.rs.BadRequestException e) {
				throw new XacmlSdkException("HTTP 400: Bad Request", e);
			} catch (javax.ws.rs.InternalServerErrorException e) {
				throw new XacmlSdkException("HTTP 500: Internal Server Error", e);
			} catch (javax.ws.rs.ServerErrorException e) {
				throw new XacmlSdkException(e);
			} catch (Exception e) {
				throw new XacmlSdkException(e);
			}
	}

	public ResponsesFactory getAuthZ(SubjectCategory subject, ResourceCategory resources, ActionCategory actions,
			EnvironmentCategory environment) throws XacmlSdkException {
		return this.getAuthZ(Arrays.asList(subject), Arrays.asList(resources), Arrays.asList(actions),
				Arrays.asList(environment));
	}

	public ResponsesFactory getAuthZ(List<SubjectCategory> subject, ResourceCategory resources, ActionCategory actions,
			EnvironmentCategory environment) throws XacmlSdkException {
		return this.getAuthZ(subject, Arrays.asList(resources), Arrays.asList(actions), Arrays.asList(environment));
	}

	public ResponsesFactory getAuthZ(SubjectCategory subject, List<ResourceCategory> resources, ActionCategory actions,
			EnvironmentCategory environment) throws XacmlSdkException {
		return this.getAuthZ(Arrays.asList(subject), resources, Arrays.asList(actions), Arrays.asList(environment));
	}

	public ResponsesFactory getAuthZ(SubjectCategory subject, ResourceCategory resources, List<ActionCategory> actions,
			EnvironmentCategory environment) throws XacmlSdkException {
		return this.getAuthZ(Arrays.asList(subject), Arrays.asList(resources), actions, Arrays.asList(environment));
	}

	public ResponsesFactory getAuthZ(SubjectCategory subject, ResourceCategory resources, ActionCategory actions,
			List<EnvironmentCategory> environment) throws XacmlSdkException {
		return this.getAuthZ(Arrays.asList(subject), Arrays.asList(resources), Arrays.asList(actions), environment);
	}
}
