package ru.otus.spring.hw03.services.i18n;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.hw03.services.l10n.LocaleProvider;

@Service
@RequiredArgsConstructor
public class I18nService {

    private final MessageSource messageSource;
    private final LocaleProvider localeProvider;

    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, localeProvider.getLocale());
    }
}
