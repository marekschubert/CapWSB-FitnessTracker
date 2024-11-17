package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.EmailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.EmailSender;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingProvider;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserProvider;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class MailScheduler {

    private final EmailSender emailSender;
    private final UserProvider userProvider;
    private final TrainingProvider trainingProvider;

    public MailScheduler(EmailSender emailSender, UserProvider userProvider, TrainingProvider trainingProvider) {
        this.emailSender = emailSender;
        this.userProvider = userProvider;
        this.trainingProvider = trainingProvider;
    }

    @Scheduled(cron = "@monthly")
    //@Scheduled(fixedRate = 50000)
    public void sendMonthlyEmails() {
        List<User> users = userProvider.findAllUsers();

        for (User user : users) {
            List<Training> userTrainings = trainingProvider.getUserTrainings(user.getId());
            sendEmail(user, userTrainings);
        }
    }

    private void sendEmail(User user, List<Training> trainings){
        String recipient = user.getEmail();
        String subject = "Monthly Report";
        StringBuilder textBuilder = new StringBuilder();

        textBuilder
                .append("In summary you've trained: ")
                .append(trainings.size())
                .append(" times!")
                .append("\n\n")
                .append("Below is full list of your trainings! \n\n");

        for (Training training : trainings) {
            textBuilder.append(GetTrainingText(training));
        }

        emailSender.send(new EmailDto(recipient, subject, textBuilder.toString()));
    }

    private String GetTrainingText(Training training){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = dateFormat.format(training.getStartTime());
        String endTime = dateFormat.format(training.getEndTime());


        return "Start: " + startTime + ", End: " + endTime + "\n" +
                "Activity: " + training.getActivityType() + ", " +
                "Avg Speed: " + training.getAverageSpeed() + " km/h\n\n";
    }
}
