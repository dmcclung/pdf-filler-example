package io.basereport.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class PdfRequestHandlerTest {
    @Test public void testOneFieldMap() {
        Request mockRequest = mock(Request.class);
        Context mockContext = mock(Context.class);

        PdfRequestHandler requestHandler = new PdfRequestHandler();
        requestHandler.handleRequest(mockRequest, mockContext);
        // TODO: See how amazon request handlers are tested

        fail("Not implemented yet");
    }
}
