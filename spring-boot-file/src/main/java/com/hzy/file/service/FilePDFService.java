package com.hzy.file.service;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FilePDFService {

    void createSimpleTable() throws DocumentException, FileNotFoundException;

    void createTablePdf() throws IOException, DocumentException;

    void createPICCPdf() throws IOException, DocumentException;
}
