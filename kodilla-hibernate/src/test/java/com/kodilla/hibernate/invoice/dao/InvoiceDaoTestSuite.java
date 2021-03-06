package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InvoiceDaoTestSuite {

    @Autowired
    private InvoiceDao invoiceDao;

    @Test
    void testInvoiceDaoSave(){
        //Given
        Invoice invoice = new Invoice(21312);

        Item item1 = new Item(new Product("Computer-samsung"), new BigDecimal(4000), 1, new BigDecimal(4000));
        Item item2 = new Item(new Product("Computer-lenovo"), new BigDecimal(2500), 3, new BigDecimal(3000));
        Item item3 = new Item(new Product("Computer-toshiba"), new BigDecimal(3100), 2, new BigDecimal(3500));
        Item item4 = new Item(new Product("Computer-asus"), new BigDecimal(3000), 5, new BigDecimal(4000));

        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        itemList.add(item4);

        //When
        invoiceDao.save(invoice);
        int id = invoice.getId();

        //Then
        assertNotEquals(0, id);

        //CleanUp
        invoiceDao.deleteById(id);

    }
}
