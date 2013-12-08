package ca.ulaval.glo4003.service.mailsender;

import javax.inject.Inject;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.context.SecurityContextHolder;

import ca.ulaval.glo4003.domain.user.User;
import ca.ulaval.glo4003.domain.user.UserRepository;

public class MailSender {

    private static final String DEFAULT_SENDER = "userglo4003@gmail.com";
    private static final String DEFAULT_SUBJECT = "Transaction";
    private static final String MESSAGE_TEMPLATE = "Thanks for buying!\nYour confirmation number is: %d";

    @Inject
    JavaMailSenderImpl mailServer;

    @Inject
    UserRepository userRepository;

    SimpleMailMessageBuilder simpleMailMessageBuilder;

    public MailSender(SimpleMailMessageBuilder simpleMailMessageBuilder) {
        this.simpleMailMessageBuilder = simpleMailMessageBuilder;
    }

    public void sendPurchaseConfirmation(long confirmationNumber) {
        User currentUser = userRepository.getUser(SecurityContextHolder.getContext().getAuthentication().getName());

        simpleMailMessageBuilder.createSender(DEFAULT_SENDER);
        simpleMailMessageBuilder.createDestination(currentUser.getEmailAddress());
        simpleMailMessageBuilder.createSubject(DEFAULT_SUBJECT);
        simpleMailMessageBuilder.createBody(String.format(MESSAGE_TEMPLATE, confirmationNumber));
        SimpleMailMessage transactionMessage = simpleMailMessageBuilder.build();

        sendEmail(transactionMessage);
    }

    private void sendEmail(SimpleMailMessage message) {
        try {
            mailServer.send(message);
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }

    // For test purpose only
    protected MailSender(JavaMailSenderImpl mailServer, UserRepository userRepository,
                         SimpleMailMessageBuilder simpleMailMessageBuilder) {
        this.mailServer = mailServer;
        this.userRepository = userRepository;
        this.simpleMailMessageBuilder = simpleMailMessageBuilder;
    }
}
