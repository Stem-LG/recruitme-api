package tn.louay.recruitme.services;

import java.io.IOException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import kong.unirest.core.ContentType;
import kong.unirest.core.Unirest;

@Service
public class PdfSummaryService {

    static public String generateSummary(@RequestParam("file") MultipartFile file) throws IOException {
        // Extract text from PDF using PDFBox
        PDDocument pdfDocument = Loader.loadPDF(file.getBytes());

        String pdfText = new PDFTextStripper().getText(pdfDocument);

        // Generate summary using LLM API
        String prompt = "Summarize the following Resume: " + pdfText;
        String summary = generate(prompt);

        return summary;
    }

    static private String generate(String prompt) {

        String query = String.format(
                """
                        {
                        "messages": [
                        {
                        "role": "system",
                        "content": "you are a resume summarizer, the user will request summarizing a
                        text extracted from a pdf resume and your job is to summarize and extract
                        importnant info from it. Make sure to not use any text formatting and reply directly only with the summary, dont say here's your summary or anything of that kind, just replay with the summary."
                        },
                        {
                        "role": "user",
                        "content": "summarize the following resume text: %s"
                        }
                        ],
                        "model": "llama3-8b-8192",
                        "temperature": 1,
                        "max_tokens": 1024,
                        "top_p": 1,
                        "stream": false,
                        "stop": null
                        }
                        """,
                prompt).replace("\n", "");

        String res = Unirest.post("https://api.groq.com/openai/v1/chat/completions")
                .accept(ContentType.APPLICATION_JSON)
                .header("Authorization", "Bearer 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")
                .body(query)
                .asString()
                .getBody();

        return res;
    }
}