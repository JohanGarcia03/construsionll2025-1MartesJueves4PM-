package app.domain.services;

import app.domain.exceptions.BusinessException;
import app.domain.exceptions.NotFoundException;
import app.domain.models.InvoiceOrder;
import app.domain.models.MedicineOrder;
import app.domain.models.User;
import app.ports.InvoiceOrderPort;
import app.ports.MedicineOrderPort;
import app.ports.UserPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private final InvoiceOrderPort invoicePort;
    private final MedicineOrderPort medicineOrderPort;
    private final UserPort userPort;

    public SellerService(InvoiceOrderPort invoicePort, MedicineOrderPort medicineOrderPort, UserPort userPort) {
        this.invoicePort = invoicePort;
        this.medicineOrderPort = medicineOrderPort;
        this.userPort = userPort;
    }

    public InvoiceOrder processSale(long sellerId, InvoiceOrder invoice) throws NotFoundException, BusinessException {
        User seller = userPort.findByCedula(sellerId)
                .orElseThrow(() -> new NotFoundException("❌ Vendedor no encontrado con cédula: " + sellerId));

        if (!seller.isSeller()) {
            throw new BusinessException("🔒 Solo los vendedores pueden procesar ventas.");
        }

        // Validar orden médica si aplica
        if (invoice.getIdOrder() != 0) {
            MedicineOrder order = medicineOrderPort.findOrderById(invoice.getIdOrder())
                    .orElseThrow(() -> new NotFoundException("❌ Orden médica con ID " + invoice.getIdOrder() + " no encontrada."));

            if (order.isCancelled()) {
                throw new BusinessException("⚠️ La orden médica está anulada y no se puede utilizar.");
            }
        }

        invoice.setDate(new java.sql.Date(System.currentTimeMillis()));
        return invoicePort.save(invoice);
    }

    public List<MedicineOrder> getAllMedicalOrders(User seller) throws BusinessException {
        if (!seller.isSeller()) {
            throw new BusinessException("🔒 Acceso restringido. Solo los vendedores pueden consultar órdenes médicas.");
        }
        return medicineOrderPort.findAllOrders();
    }
}
