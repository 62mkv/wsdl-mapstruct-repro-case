package ee.mkv.wsdlmapstructdemo.mapper;

import org.mapstruct.Mapper;

import javax.xml.bind.JAXBElement;

@Mapper(uses = {
        org.system.wsdl.legacy.ObjectFactory.class
})
public interface WsMapper {

    /**
     * If this interface have only THIS method declared, then it will be generated, no problems
     * @param collection
     * @return
     */
    org.system.wsdl.legacy.GarlicParameterCollection garlicParameterCollectionToGarlicParameterCollection(org.system.wsdl.cloud.GarlicParameterCollection collection);

    /**
     * but as soon as this one is declared, (either with or without the first one above), it won't compile
     * unless I add a "default" method as well (see below)
     * @param request
     * @return
     */
    org.system.wsdl.legacy.SellGarlicRequest fromCloud(org.system.wsdl.cloud.SellGarlicRequest request);

    /**
     * why it does not work without this default method ?
     *
     * it fails with an error message:
     * <pre>
     *
     * error: Can't map property "javax.xml.bind.JAXBElement<org.system.wsdl.cloud.GarlicParameterCollection> inputParameters" to "javax.xml.bind.JAXBElement<org.system.wsdl.legacy.GarlicParameterCollection> inputParameters". Consider to declare/implement a mapping method: "javax.xml.bind.JAXBElement<org.system.wsdl.legacy.GarlicParameterCollection> map(javax.xml.bind.JAXBElement<org.system.wsdl.cloud.GarlicParameterCollection> value)".
     *     org.system.wsdl.legacy.SellGarlicRequest fromCloud(org.system.wsdl.cloud.SellGarlicRequest request);
     *     </pre>
     */
    default JAXBElement<org.system.wsdl.legacy.GarlicParameterCollection> garlicParameterCollectionToGarlicParameterCollection(JAXBElement<org.system.wsdl.cloud.GarlicParameterCollection> parameterCollectionJAXBElement) {
        return new org.system.wsdl.legacy.ObjectFactory().createSellGarlicRequestInputParameters(
                this.garlicParameterCollectionToGarlicParameterCollection(parameterCollectionJAXBElement.getValue())
        );
    }
}
