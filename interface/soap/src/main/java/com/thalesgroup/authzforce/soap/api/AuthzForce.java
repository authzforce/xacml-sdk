
package com.thalesgroup.authzforce.soap.api;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import oasis.names.tc.xacml._2_0.context.schema.os.RequestType;
import oasis.names.tc.xacml._2_0.context.schema.os.ResponseType;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "AuthzForce", targetNamespace = "http://pdp.thalesgroup.com/")
@XmlSeeAlso({
    com.thalesgroup.authzforce.soap.api.ObjectFactory.class,
    oasis.names.tc.xacml._2_0.policy.schema.os.ObjectFactory.class,
    oasis.names.tc.xacml._2_0.context.schema.os.ObjectFactory.class
})
public interface AuthzForce {


    /**
     * 
     * @param xacmlAuthzDecisionRequest
     * @return
     *     returns oasis.names.tc.xacml._2_0.context.schema.os.ResponseType
     */
    @WebMethod(operationName = "XACMLAuthzDecision", action = "http://pdp.thalesgroup.com/XACMLAuthzDecision")
    @WebResult(name = "Response", targetNamespace = "urn:oasis:names:tc:xacml:2.0:context:schema:os", partName = "XACMLAuthzDecisionResponse")
    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    public ResponseType xacmlAuthzDecision(
        @WebParam(name = "Request", targetNamespace = "urn:oasis:names:tc:xacml:2.0:context:schema:os", partName = "XACMLAuthzDecisionRequest")
        RequestType xacmlAuthzDecisionRequest);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://pdp.thalesgroup.com/reloadPolicies")
    @WebResult(name = "out", targetNamespace = "")
    @RequestWrapper(localName = "reloadPolicies", targetNamespace = "http://pdp.thalesgroup.com/", className = "com.thalesgroup.pdp.ReloadPolicies")
    @ResponseWrapper(localName = "reloadPoliciesResponse", targetNamespace = "http://pdp.thalesgroup.com/", className = "com.thalesgroup.pdp.ReloadPoliciesResponse")
    public String reloadPolicies();

}