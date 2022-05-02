package ru.anarchyghost.basketball.modules.sending.infrastructure.thymeleaf

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.templatemode.TemplateMode
import java.nio.charset.StandardCharsets

@Configuration
internal class ThymeleafTemplateConfig {
	@Bean
	fun notificationTemplateEngine(): SpringTemplateEngine {
		val templateEngine = SpringTemplateEngine()
		templateEngine.enableSpringELCompiler = true
		templateEngine.setTemplateResolver(notificationTemplateResolver())
		templateEngine.addDialect(Nl2brDialect())
		return templateEngine
	}

	@Bean
	fun notificationTemplateResolver(): SpringResourceTemplateResolver {
		val emailTemplateResolver = SpringResourceTemplateResolver()
		emailTemplateResolver.prefix = "classpath:/templates/"
		emailTemplateResolver.templateMode = TemplateMode.HTML
		emailTemplateResolver.characterEncoding = StandardCharsets.UTF_8.name()
		return emailTemplateResolver
	}

}