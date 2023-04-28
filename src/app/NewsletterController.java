package app;

import http.Request;
import http.Server;
import http.response.HtmlResponse;
import http.response.RedirectResponse;
import http.response.Response;

public class NewsletterController extends Controller {

    public NewsletterController(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {

        String quotesHtmlString = "";
        for (int i = 0; i < Server.quotes.size(); i++) {
            // create an HTML string for each quote and add it to the output string
            quotesHtmlString += "<p>" + Server.quotes.get(i) + "</p>";
        }

        String htmlBody = "" +
                "<form method=\"POST\" action=\"/save-quote\">" +
                "<label>Author: </label><input name=\"author\" type=\"text\"><br><br>" +
                "<label>Quote: </label><input name=\"quote\" type=\"text\"><br><br>" +
                "<button>Save Quote</button><br>" +
                "</form>" +
                "<label><h2>Quote of the day: </h2></label>" +
                "<label>Some Quote</label><br>" +
                "<label><h2>Saved Quotes </h2></label>" +
                quotesHtmlString;

        String content = "<html><head><title>Odgovor servera</title></head>\n";
        content += "<body>" + htmlBody + "</body></html>";

        return new HtmlResponse(content);
    }

    @Override
    public Response doPost() {
        // TODO: obradi POST zahtev - Sacuvaj quotes i ispisi ih
        System.out.println("Quota sacuvana");
        return new RedirectResponse("/quotes");
//        return null;
    }
}
