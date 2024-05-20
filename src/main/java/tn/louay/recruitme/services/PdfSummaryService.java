package tn.louay.recruitme.services;

import java.io.IOException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
                        importnant info from it.
                        Make sure to not use any text formatting.
                        and don't begin by 'Here is the summarized information:' or similar phrases.
                        just directly begin with the details.

                        these contain: name, contact, experience, education, skills, spoken languages and any other relevant info.
                        "
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

        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(res, JsonObject.class);

        // Get the "choices" array
        JsonElement choicesElement = jsonObject.get("choices");
        if (choicesElement != null && choicesElement.isJsonArray()) {
            JsonElement choiceElement = choicesElement.getAsJsonArray().get(0);
            if (choiceElement != null && choiceElement.isJsonObject()) {
                JsonObject choiceObject = choiceElement.getAsJsonObject();

                // Get the "message" object
                JsonElement messageElement = choiceObject.get("message");
                if (messageElement != null && messageElement.isJsonObject()) {
                    JsonObject messageObject = messageElement.getAsJsonObject();

                    // Get the "content" value
                    JsonElement contentElement = messageObject.get("content");
                    if (contentElement != null && contentElement.isJsonPrimitive()) {
                        res = contentElement.getAsString();
                    }
                }
            }
        }

        return res;
    }
}