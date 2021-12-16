package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Invoice {
    private final String directory ="Invoice";
    private final String fileExtension = "html";

    private String fileName;
    private File file;

    public Invoice(Subscription subscription) {
        try {
            System.out.println("Creating an invoice.");
            new File(directory).mkdirs();
            fileName = String.format("%s/%s %s-%s.%s",
                    directory,
                    subscription.getUsername(),
                    subscription.getSubscriptionPlan(),
                    subscription.getDate(),
                    fileExtension);
            file = CreateFile(fileName);

            String content = CreateContent(subscription);
            Write(content);
            System.out.println("Invoice created: " + file.getName());

        } catch (IOException e) {
            System.out.println("An error occurred with file creation.");
            e.printStackTrace();
        }
    }

    private File CreateFile(String fileName) throws IOException {
        File file = new File(fileName);

        if (file.exists()) {
            System.out.println("exists");
            file.delete();
            CreateFile(fileName);
        } else
            file.createNewFile();

        return file;
    }

    private String CreateContent(Subscription subscription) {

        String username = subscription.getUsername();
        String subscriptionPlan = subscription.getSubscriptionPlan().toString();
        String date = subscription.getDate().toString();
        String price = String.valueOf(subscription.getPrice());

        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<title>Invoice</title>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<style>\n" +
                "* {\n" +
                "  box-sizing: border-box;\n" +
                "}\n" +
                "body {\n" +
                "  font-family: Arial, Helvetica, sans-serif;\n" +
                "  margin: 0;\n" +
                "}\n" +
                ".header {\n" +
                "  padding: 40px;\n" +
                "  text-align: center;\n" +
                "  background: #414141;\n" +
                "  color: white;\n" +
                "}\n" +
                "\n" +
                ".header h1 {\n" +
                "  font-size: 40px;\n" +
                "}\n" +
                ".main {   \n" +
                "  -ms-flex: 70%;\n" +
                "  flex: 70%;\n" +
                "  text-align: center;\n" +
                "  background: #414141;\n" +
                "  color: white;\n" +
                "  padding: 20px; \n" +
                "}\n" +
                ".bar {\n" +
                "  background-color: #292929;\n" +
                "  width: 100%;\n" +
                "  padding: 20px;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"header\">\n" +
                "  <h1>Invoice</h1>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"bar\"></div>\n" +
                "\n" +
                "<div class=\"main\">\n" +
                "    <h3>Username: " + username + "</h3>\n" +
                "    <h3>Subscription plan: " + subscriptionPlan + "</h3>\n" +
                "    <h3>Renewal date: " + date + "</h3>\n" +
                "    <h3>Price: " + price + " TRY</h3>\n" +
                "  </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        return content;
    }

    private void Write(String content) {
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
