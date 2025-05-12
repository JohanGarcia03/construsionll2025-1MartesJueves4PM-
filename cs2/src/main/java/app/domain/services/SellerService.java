package app.domain.services;

import app.domain.models.*;
import app.ports.*;

public class SellerService {
    private final InvoiceOrderPort invoicePort;
    private final MedicineOrderPort medicineOrderPort;
    private final UserPort userPort;

    public SellerService(InvoiceOrderPort invoicePort, MedicineOrderPort medicineOrderPort, UserPort userPort) {
        this.invoicePort = invoicePort;
        this.medicineOrderPort = medicineOrderPort;
        this.userPort = userPort;
    }

    public InvoiceOrder processSale(long sellerId, InvoiceOrder invoice) throws Exception {
        User seller = userPort.findById(sellerId)
                .orElseThrow(() -> new Exception("Vendedor no encontrado"));

        if (!seller.isSeller()) {
            throw new Exception("Solo vendedores pueden procesar ventas");
        }

        // Validar órdenes médicas si es medicamento
        if (invoice.getIdOrder() != 0) {
            OrderMedicine order = medicineOrderPort.findOrderById(invoice.getIdOrder())
                    .orElseThrow(() -> new Exception("Orden médica requerida no encontrada"));

            if (order.isVoidedOrder()) {
                throw new Exception("La orden médica está anulada");
            }
        }

        invoice.setDate(new java.sql.Date(System.currentTimeMillis()));
        return invoicePort.save(invoice);
    }

}