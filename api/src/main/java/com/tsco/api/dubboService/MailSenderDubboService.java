package com.tsco.api.dubboService;

import com.tsco.api.domain.dto.MailSenderDTO;

public interface MailSenderDubboService {

    void sendSimpleMail(MailSenderDTO mailSenderDTO);

    void sendHtmlEmailWithTemplate(MailSenderDTO mailSenderDTO);

}
