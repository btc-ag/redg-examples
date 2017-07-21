package com.btc.redg.example.dataextraction;

import com.btc.redg.extractor.CodeGenerator;
import com.btc.redg.extractor.DataExtractor;
import com.btc.redg.extractor.filter.TransitiveEntityFilter;
import com.btc.redg.extractor.generationmodes.EntityInclusionMode;
import com.btc.redg.extractor.model.EntityModel;
import com.btc.redg.extractor.tablemodelextractor.TableModelExtractor;
import com.btc.redg.generated.*;
import com.btc.redg.models.TableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;

public class DataExtraction {

    private static final Logger LOG = LoggerFactory.getLogger(DataExtraction.class);

    /**
     * If you are running this from your IDE, set the working directory to redg-examples\redg-example-data-extraction. Otherwise
     * you might not be using the database prepared by RedG.
     *
     * @param args The cmd args
     * @throws Exception Boom! Something went wrong!
     */
    public static void main(String[] args) throws Exception {
        LOG.info("Connecting to database");
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:file:./database", "", "");
        LOG.info("Connected!");

        extractAll(connection);
        extractPartiallyOnlySomeModels(connection);
        extractPartiallyWithCustomFilter(connection);

    }


    private static void extractAll(final Connection connection) throws Exception {
        LOG.info("---------------------------------");
        LOG.info("Extracting all data from database");
        LOG.info("---------------------------------");
        LOG.info("Extracting all table models from source code...");
        List<TableModel> tableModels = TableModelExtractor.extractTableModelsFromSourceCode(Paths.get("target", "generated-sources", "redg"), "com.btc.redg.generated", "G");
        LOG.info("Table models extracted.");

        LOG.info("Extracting all data from database...");
        DataExtractor extractor = new DataExtractor();
        List<EntityModel> entityModels = extractor.extractAllData(connection, tableModels);
        LOG.info("Extraction done.");
        LOG.info("Generating code...");
        CodeGenerator codeGenerator = new CodeGenerator();
        LOG.info(codeGenerator.generateCode("com.btc.example.generated", "RedG", "MyDataSet", entityModels));

    }

    private static void extractPartiallyOnlySomeModels(final Connection connection) throws Exception {
        LOG.info("---------------------------------");
        LOG.info("Extracting all data from some tables");
        LOG.info("---------------------------------");
        LOG.info("Collecting the table models for the tables we want the data from...");
        List<TableModel> tableModels = Arrays.asList(
                GCustomer.getTableModel(),
                GAddress.getTableModel(),
                GCustomerAddress.getTableModel()
        );
        LOG.info("Table models collected.");
        LOG.info("Extracting all data from database...");
        DataExtractor extractor = new DataExtractor();
        List<EntityModel> entityModels = extractor.extractAllData(connection, tableModels);
        LOG.info("Extraction done.");
        LOG.info("Generating Code...");

        CodeGenerator codeGenerator = new CodeGenerator();
        LOG.info(codeGenerator.generateCode("com.btc.example.generated", "RedG", "MyDataSet", entityModels));

    }


    private static void extractPartiallyWithCustomFilter(final Connection connection) throws Exception {
        LOG.info("---------------------------------");
        LOG.info("Extracting some data from the database with a custom filter");
        LOG.info("---------------------------------");
        LOG.info("Extracting all table models from source code...");
        List<TableModel> tableModels = TableModelExtractor.extractTableModelsFromSourceCode(Paths.get("target", "generated-sources", "redg"), "com.btc.redg.generated", "G");
        LOG.info("Table models extracted.");

        LOG.info("Extracting filtered data from database...");
        DataExtractor extractor = new DataExtractor();
        extractor.setEntityModeDecider(entityModel -> {
            if (entityModel.getTableModel().getSqlName().equals("ADDRESS")) {
                return EntityInclusionMode.USE_EXISTING;
            }
            return EntityInclusionMode.ADD_NEW;
        });

        List<EntityModel> entityModels = extractor.extractAllData(connection, tableModels);
        LOG.info("Extraction done.");
        LOG.info("Generating code...");
        CodeGenerator codeGenerator = new CodeGenerator();
        LOG.info(codeGenerator.generateCode("com.btc.example.generated", "RedG", "MyDataSet", entityModels));

    }


}
