package com.deofis.tiendaapirestdemo.autenticacion.services;

import com.deofis.tiendaapirestdemo.autenticacion.domain.Usuario;
import com.deofis.tiendaapirestdemo.autenticacion.domain.VerificationToken;
import com.deofis.tiendaapirestdemo.autenticacion.dto.CambiarPasswordRequest;
import com.deofis.tiendaapirestdemo.autenticacion.exceptions.AutenticacionException;
import com.deofis.tiendaapirestdemo.autenticacion.repositories.UsuarioRepository;
import com.deofis.tiendaapirestdemo.emails.dto.NotificationEmail;
import com.deofis.tiendaapirestdemo.emails.services.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class PasswordServiceImpl implements PasswordService {

    private final AutenticacionService autenticacionService;
    private final UsuarioRepository usuarioRepository;
    private final VerificationTokenService verificationTokenService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final String clientUrl;

    @Transactional
    @Override
    public Usuario cambiarPassword(CambiarPasswordRequest passwordRequest) {

        if (this.autenticacionService.estaLogueado()) {
            log.info("LOGUEADO");

            Usuario usuario = this.autenticacionService.getUsuarioActual();
            usuario.setPassword(passwordEncoder.encode(passwordRequest.getPassword()));
            return this.usuarioRepository.save(usuario);
        } else {
            log.info("NO LOGUEADO");
            throw new AutenticacionException("Usuario no logueado en el sistema");
        }

    }

    @Transactional
    @Override
    public Usuario cambiarPassword(String token, CambiarPasswordRequest cambiarPasswordRequest) {
        VerificationToken verificationToken = this.verificationTokenService.getVerificationToken(token);

        String usuarioEmail = verificationToken.getUsuario().getEmail();
        Usuario usuarioCambiarPass = this.usuarioRepository.findByEmail(usuarioEmail)
                .orElseThrow(() -> new AutenticacionException("No se encontro el usuario: " + usuarioEmail));

        usuarioCambiarPass.setPassword(passwordEncoder.encode(cambiarPasswordRequest.getPassword()));
        this.verificationTokenService.delete(verificationToken);

        return this.usuarioRepository.save(usuarioCambiarPass);
    }

    @Transactional
    @Override
    public void recuperarPassword(String userEmail) {
        Usuario usuario = this.usuarioRepository.findByEmail(userEmail)
                .orElseThrow(() -> new AutenticacionException("No se encontro el usuario: " + userEmail));

        String token = this.verificationTokenService.generarVerificationToken(usuario);

        NotificationEmail notificationEmail = new NotificationEmail();
        String mailBody = "Se ha registrado un intento de reinicio de contraseña. Si fue usted quien lo solicitó" +
                "\nporfavor haga click en el enlace de aquí abajo. Si no fue usted, ignore este correo";
        notificationEmail.setSubject("Recuperar contraseña");
        notificationEmail.setRecipient(userEmail);
        notificationEmail.setBody(mailBody);

        String URL_REDIRIGIR = this.clientUrl.concat("/recuperar-password/");
        this.mailService.sendEmail(notificationEmail,URL_REDIRIGIR + token);
    }
}
