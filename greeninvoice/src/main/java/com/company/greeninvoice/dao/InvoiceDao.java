package com.company.greeninvoice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.greeninvoice.dto.Invoice;

/**
 * InvoiceDao interface extends JpaRepository.
 * @author Venkatraman
 *
 */
@Repository
public interface InvoiceDao extends JpaRepository<Invoice, String>{

}
