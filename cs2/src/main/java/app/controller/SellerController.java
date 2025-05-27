package app.controller;

import app.domain.models.InvoiceOrder;
import app.domain.models.OrderMedicine;
import app.domain.models.User;
import app.domain.services.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import app.ports.UserPort;
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

    @PostMapping("/CreateInvoice")
    public ResponseEntity<?> createInvoice(
            @RequestParam long cedulaVendedor,
            @RequestBody InvoiceOrder invoiceOrder
    ) {
        try {
            InvoiceOrder generate = sellerService.processSale(cedulaVendedor, invoiceOrder);
            return ResponseEntity.ok("✅ Factura generada con ID: " + generate.getIdFacture());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al generar factura: " + e.getMessage());

        }

    }
    // 🧾 Registrar una factura
    @PostMapping("/Invoices")
    public ResponseEntity<?> GeneratedInvoice(
            @RequestBody InvoiceOrder Invoices,
            @RequestParam long cedulaVendedor
    ) {
        try {
            User seller = userPort.findById(cedulaVendedor)
                    .orElseThrow(() -> new Exception("❌ Vendedor no encontrado"));

            InvoiceOrder invoiceOrderGenerated = sellerService.processSale(cedulaVendedor, Invoices);
            return ResponseEntity.ok(invoiceOrderGenerated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al generar factura: " + e.getMessage());
        }
    }
    // 📋 Consultar órdenes médicas (solo consulta, no puede crear/anular)
    @GetMapping("/orders")
    public ResponseEntity<?> ListOrders(@RequestParam long cedulaVendedor) {
        try {
            User seller = userPort.findByCedula(cedulaVendedor)
                    .orElseThrow(() -> new Exception("❌ Usuario no encontrado"));

            // Validar que sea un vendedor
            if (!seller.isSeller()) {
                throw new Exception("🔒 Solo los vendedores pueden acceder a esta consulta.");
            }

            List<OrderMedicine> orders = sellerService.getAllMedicalOrders(seller);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("❌ Error al consultar órdenes: " + e.getMessage());
        }
    }


}
