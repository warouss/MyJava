
package com.warouss.webservice.client.gen;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PersonService", targetNamespace = "http://www.person.com/", wsdlLocation = "http://localhost:8888/person?wsdl")
public class PersonService_Service
    extends Service
{

    private final static URL PERSONSERVICE_WSDL_LOCATION;
    private final static WebServiceException PERSONSERVICE_EXCEPTION;
    private final static QName PERSONSERVICE_QNAME = new QName("http://www.person.com/", "PersonService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8888/person?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PERSONSERVICE_WSDL_LOCATION = url;
        PERSONSERVICE_EXCEPTION = e;
    }

    public PersonService_Service() {
        super(__getWsdlLocation(), PERSONSERVICE_QNAME);
    }

    public PersonService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), PERSONSERVICE_QNAME, features);
    }

    public PersonService_Service(URL wsdlLocation) {
        super(wsdlLocation, PERSONSERVICE_QNAME);
    }

    public PersonService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PERSONSERVICE_QNAME, features);
    }

    public PersonService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PersonService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PersonService
     */
    @WebEndpoint(name = "PersonServiceImplPort")
    public PersonService getPersonServiceImplPort() {
        return super.getPort(new QName("http://www.person.com/", "PersonServiceImplPort"), PersonService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PersonService
     */
    @WebEndpoint(name = "PersonServiceImplPort")
    public PersonService getPersonServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.person.com/", "PersonServiceImplPort"), PersonService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PERSONSERVICE_EXCEPTION!= null) {
            throw PERSONSERVICE_EXCEPTION;
        }
        return PERSONSERVICE_WSDL_LOCATION;
    }

}
