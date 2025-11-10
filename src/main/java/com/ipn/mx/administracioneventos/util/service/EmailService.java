package com.ipn.mx.administracioneventos.util.service;

public interface EmailService {
    public void sendEmail(String to, String subject, String text);
}
