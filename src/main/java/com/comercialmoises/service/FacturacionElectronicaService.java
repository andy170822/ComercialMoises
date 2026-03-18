package com.comercialmoises.service;

import com.comercialmoises.model.ComprobanteElectronico;
import com.comercialmoises.model.Venta;
import com.comercialmoises.repository.ComprobanteElectronicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FacturacionElectronicaService {

    @Autowired
    private ComprobanteElectronicoRepository comprobanteRepository;

    /**
     * Simulates the process of generating an XML, signing it, 
     * and sending it to a tax agency (like SUNAT/SRI/AFIP).
     */
    public ComprobanteElectronico procesarFacturacionVenta(Venta venta) {
        
        // 1. Simulate XML generation based on Venta object
        String xmlSimulado = "<Invoice><Id>" + venta.getTipoComprobante() + "-" + 
                             venta.getSerie() + "-" + venta.getNumeroComprobante() + 
                             "</Id><Total>" + venta.getTotal() + "</Total></Invoice>";
                             
        // 2. Simulate Digital Signature Hash
        String hashSimulado = UUID.randomUUID().toString();
        
        ComprobanteElectronico comprobante = new ComprobanteElectronico();
        comprobante.setVenta(venta);
        comprobante.setXmlGenerado(xmlSimulado);
        comprobante.setHashFirma(hashSimulado);
        
        // 3. Simulate Agency Response (e.g. SUNAT CDR)
        // In a real scenario, this involves a SOAP/REST call. We simulate success.
        comprobante.setCodigoRespuestaSunat("0");
        comprobante.setMensajeRespuestaSunat("La Factura numero " + venta.getSerie() + "-" + venta.getNumeroComprobante() + " ha sido aceptada");
        comprobante.setAceptado(true);
        
        // 4. Save to Database
        return comprobanteRepository.save(comprobante);
    }
}
