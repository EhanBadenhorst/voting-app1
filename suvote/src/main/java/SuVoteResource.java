// File: WIL%20Stellenbosch%20UNI/SU_Vote_Main/suvote/src/main/java/org/SUVote/SuVoteResource.java


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

// Resource class
@Path("/home")
public class SuVoteResource {
    @GET
    @Produces(MediaType.TEXT_HTML)
    // Method to serve the homepage
    public String homepage() {
        String headerHP = header();
        String homepage = loadHtmlContent("src/main/resources/META-INF/resources/index.html");
        String footerHP = footer();

        return headerHP + homepage + footerHP;
    }

    // Method to load header HTML content
    private String header() {
        String head = loadHtmlContent("src/main/resources/META-INF/resources/header.html");
        return head;
    }

    // Method to load footer HTML content
    private String footer() {
        String footer = loadHtmlContent("src/main/resources/META-INF/resources/footer.html");
        return footer;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/contact us")
    public String contactUs() {
        String headerCU = header();
        String contactUsPage = loadHtmlContent("src/main/resources/META-INF/resources/contact-us.html");
        String footerCU = footer();

        return headerCU + contactUsPage + footerCU;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/settings")
    public String settings() {
        String headerS = header();
        String settingsPage = loadHtmlContent("src/main/resources/META-INF/resources/settings.html");
        String footerS = footer();

        return headerS + settingsPage + footerS;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/results")
    public String results() {
        String headerR = header();
        String resultsPage = loadHtmlContent("src/main/resources/META-INF/resources/results.html");
        String footerR = footer();

        return headerR + resultsPage + footerR;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/login")
    public String login() {
        return loadHtmlContent("src/main/resources/META-INF/resources/login.html");
    }

    // Method to load HTML content from a file
    private String loadHtmlContent(String filePath) {
        // Read the HTML file content and return it as a string
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            return new String(bytes, StandardCharsets.UTF_8);
            // Catch and handle any IO exceptions
        } catch (IOException e) {
            e.printStackTrace();
            return "<html><body><h1>Error loading page</h1></body></html>";
        }
    }
}
