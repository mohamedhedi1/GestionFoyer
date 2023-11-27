package com.example.gestionfoyer.services;

import com.example.gestionfoyer.config.JwtService;
import com.example.gestionfoyer.entities.Etudiant;
import com.example.gestionfoyer.exceptions.ResourceNotFoundException;
import com.example.gestionfoyer.repositories.EtudiantRepository;
import com.example.gestionfoyer.user.Role;
import com.example.gestionfoyer.user.User;
import com.example.gestionfoyer.user.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class IEtudiantServiceImp implements IEtudiantService {
    private final UserRepository userRepository;
    private final EtudiantRepository etudiantRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final JwtService jwtService;
    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return (List<Etudiant>) this.etudiantRepository.findAll();
    }

    @Override
    public List<Etudiant> addEtudiants(List<Etudiant> etudiants) {

        return (List<Etudiant>) this.etudiantRepository.saveAll(etudiants);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return this.etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(long idEtudiant)  {
        return this.etudiantRepository.findById(idEtudiant).orElseThrow(() -> new ResourceNotFoundException("etudiant","id", Long.toString(idEtudiant)));
    }

    @Override
    public void removeEtudiant(long idEtudiant) {
        this.etudiantRepository.deleteById(idEtudiant);

    }

    @Override
    public Etudiant addEtudiant(Etudiant etudiant)  throws MessagingException {
        String password = generatePassword();
        var user = User.builder()
                .firstname(etudiant.getPrenomEt())
                .lastname(etudiant.getNomEt())
                .email(etudiant.getEmail())
                .password(passwordEncoder.encode(password))
                .role(Role.ETUDIANT)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        MimeMessage mailMessage = mailSender.createMimeMessage();
        mailMessage.setFrom("administration@foyer.com");
        mailMessage.setRecipients(MimeMessage.RecipientType.TO, etudiant.getEmail());
        mailMessage.setSubject("Your Password");
        String htmlTemplate =
                "<html>"+ "\n" +
                        "<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
                        "    <!--100% body table-->\n" +
                        "    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
                        "        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
                        "        <tr>\n" +
                        "            <td>\n" +
                        "                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
                        "                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                        "                    <tr>\n" +
                        "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td style=\"text-align:center;\">\n" +
                        "                          <a href=\""+"\" title=\"logo\" target=\"_blank\">\n" +
                        "                            <img width=\"60\" src=\"https://i.ibb.co/hL4XZp2/android-chrome-192x192.png\" title=\"logo\" alt=\"logo\">\n" +
                        "                          </a>\n" +
                        "                        </td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td>\n" +
                        "                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                        "                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
                        "                                <tr>\n" +
                        "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                        "                                </tr>\n" +
                        "                                <tr>\n" +
                        "                                    <td style=\"padding:0 35px;\">\n" +
                        "                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">\n" +
                        "                                            Welcome to foyer</h1>\n" +
                        "                                        <span\n" +
                        "                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
                        "                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n" +
                        " Hello,\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "this is your password : "+password+".\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "                                        </p>\n" +
                        "                                    </td>\n" +
                        "                                </tr>\n" +
                        "                                <tr>\n" +
                        "                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
                        "                                </tr>\n" +
                        "                            </table>\n" +
                        "                        </td>\n" +
                        "                    <tr>\n" +
                        "                        <td style=\"height:20px;\">&nbsp;</td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td style=\"text-align:center;\">\n" +
                        "                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>Foyer</strong></p>\n" +
                        "                        </td>\n" +
                        "                    </tr>\n" +
                        "                    <tr>\n" +
                        "                        <td style=\"height:80px;\">&nbsp;</td>\n" +
                        "                    </tr>\n" +
                        "                </table>\n" +
                        "            </td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "    <!--/100% body table-->\n" +
                        "</body>\n" +
                        "\n" +
                        "</html>";
        mailMessage.setContent(htmlTemplate, "text/html; charset=utf-8");
        mailSender.send(mailMessage);

        return this.etudiantRepository.save(etudiant);
    }

    public  String generatePassword() {
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            char letter = (char) (random.nextInt(26) + 'A');
            randomString.append(letter);
        }
        for (int i = 0; i < 4; i++) {
            int digit = random.nextInt(10);
            randomString.append(digit);
        }
        System.out.println(randomString.toString());
        return randomString.toString();
    }
}
