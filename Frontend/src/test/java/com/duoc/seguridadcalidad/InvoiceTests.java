package com.duoc.seguridadcalidad;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceTests {

    @Test
    void constructorCompletoDebeAsignarTodosLosCampos() {
        LocalDate fecha = LocalDate.of(2026, 1, 15);
        List<InvoiceLineItem> items = List.of();
        Invoice invoice = new Invoice(1L, 2L, fecha, new BigDecimal("0.19"),
                new BigDecimal("100"), new BigDecimal("19"), new BigDecimal("119"), "nota", items);

        assertEquals(1L, invoice.getId());
        assertEquals(2L, invoice.getAppointmentId());
        assertEquals(fecha, invoice.getIssueDate());
        assertEquals(new BigDecimal("0.19"), invoice.getVatRate());
        assertEquals(new BigDecimal("100"), invoice.getSubtotal());
        assertEquals(new BigDecimal("19"), invoice.getVatAmount());
        assertEquals(new BigDecimal("119"), invoice.getTotal());
        assertEquals("nota", invoice.getNotes());
        assertEquals(items, invoice.getItems());
    }

    @Test
    void constructorVacioDebeCrearInstancia() {
        Invoice invoice = new Invoice();
        assertNotNull(invoice);
        assertNotNull(invoice.getItems());
    }

    @Test
    void settersDebenActualizarValores() {
        Invoice invoice = new Invoice();
        LocalDate fecha = LocalDate.of(2026, 5, 1);

        invoice.setId(10L);
        invoice.setAppointmentId(20L);
        invoice.setIssueDate(fecha);
        invoice.setVatRate(new BigDecimal("0.19"));
        invoice.setSubtotal(new BigDecimal("200"));
        invoice.setVatAmount(new BigDecimal("38"));
        invoice.setTotal(new BigDecimal("238"));
        invoice.setNotes("test");
        invoice.setItems(List.of());

        assertEquals(10L, invoice.getId());
        assertEquals(20L, invoice.getAppointmentId());
        assertEquals(fecha, invoice.getIssueDate());
        assertEquals(new BigDecimal("0.19"), invoice.getVatRate());
        assertEquals(new BigDecimal("200"), invoice.getSubtotal());
        assertEquals(new BigDecimal("38"), invoice.getVatAmount());
        assertEquals(new BigDecimal("238"), invoice.getTotal());
        assertEquals("test", invoice.getNotes());
    }
}