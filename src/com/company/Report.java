package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Report {
    private final String directory ="Report";
    private final String fileExtension = "html";

    private String fileName;
    private File file;

    public Report(List<Subscription> subs, List<User> users) {
        try {
            System.out.println("Creating platform report.");
            new File(directory).mkdirs();
            fileName = String.format("%s/PlatformReport-%s.%s",
                    directory,
                    LocalDate.now(),
                    fileExtension);

            file = CreateFile(fileName);
            String content = CreatePlatformReport(subs, users);
            Write(content);
            System.out.println("Report created: " + file.getName());

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

    public Report(User user) {
        try {
            System.out.println("Creating user report.");
            fileName = String.format("UserReport-%s.%s",
                    LocalDate.now(),
                    fileExtension);

            file = CreateFile(fileName);
            String content = CreateUserReport(user);
            Write(content);
            System.out.println("Report created: " + file.getName());


        } catch (IOException e) {
            System.out.println("An error occurred with file creation.");
            e.printStackTrace();
        }
    }


    private String CreatePlatformReport(List<Subscription> subs, List<User> users) {

        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<title>Platform Report</title>\n" +
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
                "  <h1>Platform Report</h1>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"bar\"></div>\n" +
                "\n" +
                "<div class=\"main\">\n";

        String c = "";

        for (int i = 0; i < 4; i++) {
            int renewalCounter = 0, currentSubCount = 0;
            for (Subscription sub : subs) {
                if (sub.getSubscriptionPlan() == SubscriptionPlan.values()[i])
                    renewalCounter++;
            }
            for (User u : users) {
                if (u.getSubscriptions().size() > 0) {
                    if (u.getLatestSubscription().getSubscriptionPlan() == SubscriptionPlan.values()[i])
                        currentSubCount++;
                }
            }

            c += "    <h3>Subscription plan: " + SubscriptionPlan.values()[i] + "</h3>\n" +
                    "    <h3>Current number of subscribers: " + currentSubCount + "</h3>\n" +
                    "    <h3>Total number of renewals: " + renewalCounter + "</h3>\n" +
                    "    <br>\n";
        }


        content += c;
        content += " </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

        return content;
    }

    private String CreateUserReport(User user) {

        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<title>User Report</title>\n" +
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
                "  <h1>User Report</h1>\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"bar\"></div>\n" +
                "\n" +
                "<div class=\"main\">\n" +
                "    <h3>Username: " + user.getUsername() + "</h3>\n" +
                "    <h3>Subscription plan: " + user.getLatestSubscription().getSubscriptionPlan() + "</h3>\n" +
                "    <h3>Parental control: " + (user.isParentalControlOn() ? "On" : "Off") + "</h3>\n" +
                "    <h3>Number of renewals: " + user.getSubscriptions().size() + "</h3>\n" +
                "    <br>\n" +
                "    <h3>Older plans:</h3>   \n" +
                "    <br>\n";
        String c = "";

        for (int i = 0; i < 4; i++) {
            int renewalCounter = 0;
            for (Subscription sub : user.getSubscriptions()) {
                if (sub.getSubscriptionPlan() == SubscriptionPlan.values()[i])
                    renewalCounter++;
            }

            c += "    <h3>Subscription plan: " + SubscriptionPlan.values()[i] + "</h3>\n" +
                    "    <h3>Total number of renewals: " + renewalCounter + "</h3>\n" +
                    "    <h3>Renewal dates:</h3>\n";
            for (Subscription sub : user.getSubscriptions()) {
                if (sub.getSubscriptionPlan() == SubscriptionPlan.values()[i])
                    c += "<h3>" + sub.getDate() + "</h3>\n";
            }

            c += "<br>\n";
        }

        content += c;
        content += "  </div>\n" +
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
