package app.controller;

import app.domain.exceptions.BusinessException;
import app.domain.exceptions.NotFoundException;
import app.domain.models.InvoiceOrder;
import app.domain.models.MedicineOrder;
import app.domain.models.User;
import app.domain.services.SellerService;
import app.ports.UserPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final UserPort userPort;
    private final SellerService sellerService;

    public SellerController(UserPort userPort, SellerService sellerService) {
        this.userPort = userPort;
        this.sellerService = sellerService;
    }

    // 📥 factura
    @PostMapping("/invoices/message")
    public ResponseEntity<?> createInvoice(
            @RequestParam long cedulaVendedor,
            @RequestBody InvoiceOrder invoiceOrder
    ) {
        try {
            InvoiceOrder generate = sellerService.processSale(cedulaVendedor, invoiceOrder);
            return ResponseEntity.ok("✅ Factura generada con ID: " + generate.getIdFacture());
        } catch (NotFoundException | BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Error inesperado: " + e.getMessage());
        }
    }

    // 🧾 Registrar factura
    @PostMapping("/invoices")
    public ResponseEntity<?> GeneratedInvoice(
            @RequestBody InvoiceOrder invoice,
            @RequestParam long cedulaVendedor
    ) {
        try {
            User seller = userPort.findByCedula(cedulaVendedor)
                    .orElseThrow(() -> new NotFoundException("❌ Vendedor no encontrado"));

            InvoiceOrder invoiceOrderGenerated = sellerService.processSale(cedulaVendedor, invoice);
            return ResponseEntity.ok(invoiceOrderGenerated);
        } catch (NotFoundException | BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Error inesperado: " + e.getMessage());
        }
    }

    // 📋 Consultar órdenes médicas
    @GetMapping("/orders")
    public ResponseEntity<?> ListOrders(@RequestParam long cedulaVendedor) {
        try {
            User seller = userPort.findByCedula(cedulaVendedor)
                    .orElseThrow(() -> new NotFoundException("❌ Usuario no encontrado"));

            List<MedicineOrder> orders = sellerService.getAllMedicalOrders(seller);
            return ResponseEntity.ok(orders);
        } catch (NotFoundException | BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Error inesperado: " + e.getMessage());
        }
    }

}
