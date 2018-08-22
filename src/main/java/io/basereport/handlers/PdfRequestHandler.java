package io.basereport.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PdfRequestHandler implements RequestHandler<Request, Response> {
    private static Logger log = Logger.getLogger(PdfRequestHandler.class);

    @Override
    public Response handleRequest(Request req, Context context) {
        String formTemplate = "src/main/resources/io/basereport/handlers/exampleForm.pdf";

        try {
            PDDocument pdfDocument = PDDocument.load(new File(formTemplate));

            // get the document catalog
            PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

            // as there might not be an AcroForm entry a null check is necessary
            if (acroForm != null) {
                if (log.isDebugEnabled()) {
                    for (PDField field : acroForm.getFields()) {
                        log.debug("PDF field mapping: " + field.getMappingName());
                    }
                }

                List<ClientEntity> entities = req.getEntities();
                for (ClientEntity entity : entities) {

                    // Retrieve an individual field and set its value.
                    PDTextField field = (PDTextField) acroForm.getField( "sampleField" );
                    field.setValue(entity.getId());

                    // If a field is nested within the form tree a fully qualified name
                    // might be provided to access the field.
                    field = (PDTextField) acroForm.getField( "fieldsContainer.nestedSampleField" );
                    field.setValue(entity.getDescription());

                    // Save and close the filled out form.
                    // TODO: Save this document to s3 or stream back?
                    pdfDocument.save("target/FillFormField.pdf");
                }
            } else {
                // TODO: Throw error
            }
        } catch (InvalidPasswordException e) {
            // TODO: Figure out how to handle error handling
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Response();
    }
}