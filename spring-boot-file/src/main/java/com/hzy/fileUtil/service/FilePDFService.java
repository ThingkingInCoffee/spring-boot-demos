package com.hzy.fileUtil.service;

import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FilePDFService {

    void createSimpleTable() throws DocumentException, FileNotFoundException;

    public void createTablePdf() throws IOException, DocumentException;

    public void createPICCPdf() throws IOException, DocumentException;
}
