package org.SUVote;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.agroal.api.AgroalDataSource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;

@Path("/home") // Changed to lowercase
public class NewResource {

    private AgroalDataSource dataSource;

    @Inject
    public NewResource(AgroalDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String home() {
        String page = loadHtmlContent("src/main/resources/META-INF/resources/Index.html");
        return page;
    }

    @Path("/contact")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String contact() {
        return loadHtmlContent("src/main/resources/META-INF/resources/contact.html");
    }

    // Commented out for now - add back when you have a proper SQL statement
    // public String insert() throws SQLException {
    //     try (Connection connection = dataSource.getConnection();
    //             Statement statement = connection.createStatement()) {
    //         statement.execute("YOUR_SQL_STATEMENT_HERE");
    //         return "Inserted";
    //     }
    // }

    private String loadHtmlContent(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return "<html><body><h1>Error loading page</h1></body></html>";
        }
    }
}